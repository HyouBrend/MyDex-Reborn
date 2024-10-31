package org.apache.commons.net.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory;
import org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory;
import org.apache.commons.net.io.FromNetASCIIInputStream;
import org.apache.commons.net.io.SocketInputStream;
import org.apache.commons.net.io.SocketOutputStream;
import org.apache.commons.net.io.ToNetASCIIOutputStream;
import org.apache.commons.net.io.Util;

/* loaded from: classes2.dex */
public class FTPClient extends FTP implements Configurable {
    public static final int ACTIVE_LOCAL_DATA_CONNECTION_MODE = 0;
    public static final int ACTIVE_REMOTE_DATA_CONNECTION_MODE = 1;
    public static final int PASSIVE_LOCAL_DATA_CONNECTION_MODE = 2;
    public static final int PASSIVE_REMOTE_DATA_CONNECTION_MODE = 3;
    private static final String __parms = "\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}";
    private static final Pattern __parms_pat = Pattern.compile(__parms);
    private InetAddress __activeExternalHost;
    private int __activeMaxPort;
    private int __activeMinPort;
    private int __bufferSize;
    private FTPClientConfig __configuration;
    private int __dataConnectionMode;
    private int __dataTimeout;
    private FTPFileEntryParser __entryParser;
    private String __entryParserKey;
    private int __fileFormat;
    private int __fileStructure;
    private int __fileTransferMode;
    private int __fileType;
    private boolean __listHiddenFiles;
    private FTPFileEntryParserFactory __parserFactory;
    private String __passiveHost;
    private int __passivePort;
    private final Random __random;
    private boolean __remoteVerificationEnabled;
    private long __restartOffset;
    private String __systemName;
    private boolean __useEPSVwithIPv4;

    public FTPClient() {
        __initDefaults();
        this.__dataTimeout = -1;
        this.__remoteVerificationEnabled = true;
        this.__parserFactory = new DefaultFTPFileEntryParserFactory();
        this.__configuration = null;
        this.__listHiddenFiles = false;
        this.__useEPSVwithIPv4 = false;
        this.__random = new Random();
    }

    private void __initDefaults() {
        this.__dataConnectionMode = 0;
        this.__passiveHost = null;
        this.__passivePort = -1;
        this.__activeExternalHost = null;
        this.__activeMinPort = 0;
        this.__activeMaxPort = 0;
        this.__fileType = 0;
        this.__fileStructure = 7;
        this.__fileFormat = 4;
        this.__fileTransferMode = 10;
        this.__restartOffset = 0L;
        this.__systemName = null;
        this.__entryParser = null;
        this.__entryParserKey = "";
        this.__bufferSize = 1024;
    }

    private String __parsePathname(String str) {
        int indexOf = str.indexOf(34) + 1;
        return str.substring(indexOf, str.indexOf(34, indexOf));
    }

    private void __parsePassiveModeReply(String str) throws MalformedServerReplyException {
        Matcher matcher = __parms_pat.matcher(str);
        if (!matcher.find()) {
            throw new MalformedServerReplyException("Could not parse passive host information.\nServer Reply: " + str);
        }
        String group = matcher.group();
        String[] split = matcher.group().split(",");
        this.__passiveHost = split[0] + '.' + split[1] + '.' + split[2] + '.' + split[3];
        try {
            this.__passivePort = Integer.parseInt(split[5]) | (Integer.parseInt(split[4]) << 8);
        } catch (NumberFormatException unused) {
            throw new MalformedServerReplyException("Could not parse passive host information.\nServer Reply: " + group);
        }
    }

    private void __parseExtendedPassiveModeReply(String str) throws MalformedServerReplyException {
        String trim = str.substring(str.indexOf(40) + 1, str.indexOf(41)).trim();
        char charAt = trim.charAt(0);
        char charAt2 = trim.charAt(1);
        char charAt3 = trim.charAt(2);
        char charAt4 = trim.charAt(trim.length() - 1);
        if (charAt != charAt2 || charAt2 != charAt3 || charAt3 != charAt4) {
            throw new MalformedServerReplyException("Could not parse extended passive host information.\nServer Reply: " + trim);
        }
        try {
            int parseInt = Integer.parseInt(trim.substring(3, trim.length() - 1));
            this.__passiveHost = getRemoteAddress().getHostAddress();
            this.__passivePort = parseInt;
        } catch (NumberFormatException unused) {
            throw new MalformedServerReplyException("Could not parse extended passive host information.\nServer Reply: " + trim);
        }
    }

    private boolean __storeFile(int i, String str, InputStream inputStream) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(i, str);
        if (_openDataConnection_ == null) {
            return false;
        }
        OutputStream bufferedOutputStream = new BufferedOutputStream(_openDataConnection_.getOutputStream(), getBufferSize());
        if (this.__fileType == 0) {
            bufferedOutputStream = new ToNetASCIIOutputStream(bufferedOutputStream);
        }
        try {
            Util.copyStream(inputStream, bufferedOutputStream, getBufferSize(), -1L, null, false);
            bufferedOutputStream.close();
            _openDataConnection_.close();
            return completePendingCommand();
        } catch (IOException e) {
            try {
                _openDataConnection_.close();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    private OutputStream __storeFileStream(int i, String str) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(i, str);
        if (_openDataConnection_ == null) {
            return null;
        }
        OutputStream outputStream = _openDataConnection_.getOutputStream();
        if (this.__fileType == 0) {
            outputStream = new ToNetASCIIOutputStream(new BufferedOutputStream(outputStream, getBufferSize()));
        }
        return new SocketOutputStream(_openDataConnection_, outputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Socket _openDataConnection_(int i, String str) throws IOException {
        Socket socket;
        int i2 = this.__dataConnectionMode;
        if (i2 != 0 && i2 != 2) {
            return null;
        }
        boolean z = getRemoteAddress() instanceof Inet6Address;
        boolean z2 = true;
        if (this.__dataConnectionMode == 0) {
            ServerSocket createServerSocket = this._serverSocketFactory_.createServerSocket(getActivePort(), 1, getHostAddress());
            if (z) {
                if (!FTPReply.isPositiveCompletion(eprt(getHostAddress(), createServerSocket.getLocalPort()))) {
                    return null;
                }
            } else if (!FTPReply.isPositiveCompletion(port(getHostAddress(), createServerSocket.getLocalPort()))) {
                return null;
            }
            long j = this.__restartOffset;
            if (j > 0 && !restart(j)) {
                return null;
            }
            if (!FTPReply.isPositivePreliminary(sendCommand(i, str))) {
                return null;
            }
            int i3 = this.__dataTimeout;
            if (i3 >= 0) {
                createServerSocket.setSoTimeout(i3);
            }
            try {
                socket = createServerSocket.accept();
            } finally {
                createServerSocket.close();
            }
        } else {
            if (!isUseEPSVwithIPv4() && !z) {
                z2 = false;
            }
            if (z2 && epsv() == 229) {
                __parseExtendedPassiveModeReply(this._replyLines.get(0));
            } else {
                if (z || pasv() != 227) {
                    return null;
                }
                __parsePassiveModeReply(this._replyLines.get(0));
            }
            Socket createSocket = this._socketFactory_.createSocket(this.__passiveHost, this.__passivePort);
            long j2 = this.__restartOffset;
            if (j2 > 0 && !restart(j2)) {
                createSocket.close();
                return null;
            }
            if (!FTPReply.isPositivePreliminary(sendCommand(i, str))) {
                createSocket.close();
                return null;
            }
            socket = createSocket;
        }
        if (this.__remoteVerificationEnabled && !verifyRemote(socket)) {
            InetAddress inetAddress = socket.getInetAddress();
            InetAddress remoteAddress = getRemoteAddress();
            socket.close();
            throw new IOException("Host attempting data connection " + inetAddress.getHostAddress() + " is not same as server " + remoteAddress.getHostAddress());
        }
        int i4 = this.__dataTimeout;
        if (i4 >= 0) {
            socket.setSoTimeout(i4);
        }
        return socket;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.ftp.FTP, org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        super._connectAction_();
        __initDefaults();
    }

    public void setDataTimeout(int i) {
        this.__dataTimeout = i;
    }

    public void setParserFactory(FTPFileEntryParserFactory fTPFileEntryParserFactory) {
        this.__parserFactory = fTPFileEntryParserFactory;
    }

    @Override // org.apache.commons.net.ftp.FTP, org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        __initDefaults();
    }

    public void setRemoteVerificationEnabled(boolean z) {
        this.__remoteVerificationEnabled = z;
    }

    public boolean isRemoteVerificationEnabled() {
        return this.__remoteVerificationEnabled;
    }

    public boolean login(String str, String str2) throws IOException {
        user(str);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (FTPReply.isPositiveIntermediate(this._replyCode)) {
            return FTPReply.isPositiveCompletion(pass(str2));
        }
        return false;
    }

    public boolean login(String str, String str2, String str3) throws IOException {
        user(str);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (!FTPReply.isPositiveIntermediate(this._replyCode)) {
            return false;
        }
        pass(str2);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (FTPReply.isPositiveIntermediate(this._replyCode)) {
            return FTPReply.isPositiveCompletion(acct(str3));
        }
        return false;
    }

    public boolean logout() throws IOException {
        return FTPReply.isPositiveCompletion(quit());
    }

    public boolean changeWorkingDirectory(String str) throws IOException {
        return FTPReply.isPositiveCompletion(cwd(str));
    }

    public boolean changeToParentDirectory() throws IOException {
        return FTPReply.isPositiveCompletion(cdup());
    }

    public boolean structureMount(String str) throws IOException {
        return FTPReply.isPositiveCompletion(smnt(str));
    }

    boolean reinitialize() throws IOException {
        rein();
        if (!FTPReply.isPositiveCompletion(this._replyCode) && (!FTPReply.isPositivePreliminary(this._replyCode) || !FTPReply.isPositiveCompletion(getReply()))) {
            return false;
        }
        __initDefaults();
        return true;
    }

    public void enterLocalActiveMode() {
        this.__dataConnectionMode = 0;
        this.__passiveHost = null;
        this.__passivePort = -1;
    }

    public void enterLocalPassiveMode() {
        this.__dataConnectionMode = 2;
        this.__passiveHost = null;
        this.__passivePort = -1;
    }

    public boolean enterRemoteActiveMode(InetAddress inetAddress, int i) throws IOException {
        if (!FTPReply.isPositiveCompletion(port(inetAddress, i))) {
            return false;
        }
        this.__dataConnectionMode = 1;
        this.__passiveHost = null;
        this.__passivePort = -1;
        return true;
    }

    public boolean enterRemotePassiveMode() throws IOException {
        if (pasv() != 227) {
            return false;
        }
        this.__dataConnectionMode = 3;
        __parsePassiveModeReply(this._replyLines.get(0));
        return true;
    }

    public String getPassiveHost() {
        return this.__passiveHost;
    }

    public int getPassivePort() {
        return this.__passivePort;
    }

    public int getDataConnectionMode() {
        return this.__dataConnectionMode;
    }

    private int getActivePort() {
        int i;
        int i2 = this.__activeMinPort;
        if (i2 <= 0 || (i = this.__activeMaxPort) < i2) {
            return 0;
        }
        return i == i2 ? i : this.__random.nextInt((i - i2) + 1) + this.__activeMinPort;
    }

    private InetAddress getHostAddress() {
        InetAddress inetAddress = this.__activeExternalHost;
        return inetAddress != null ? inetAddress : getLocalAddress();
    }

    public void setActivePortRange(int i, int i2) {
        this.__activeMinPort = i;
        this.__activeMaxPort = i2;
    }

    public void setActiveExternalIPAddress(String str) throws UnknownHostException {
        this.__activeExternalHost = InetAddress.getByName(str);
    }

    public boolean setFileType(int i) throws IOException {
        if (!FTPReply.isPositiveCompletion(type(i))) {
            return false;
        }
        this.__fileType = i;
        this.__fileFormat = 4;
        return true;
    }

    public boolean setFileType(int i, int i2) throws IOException {
        if (!FTPReply.isPositiveCompletion(type(i, i2))) {
            return false;
        }
        this.__fileType = i;
        this.__fileFormat = i2;
        return true;
    }

    public boolean setFileStructure(int i) throws IOException {
        if (!FTPReply.isPositiveCompletion(stru(i))) {
            return false;
        }
        this.__fileStructure = i;
        return true;
    }

    public boolean setFileTransferMode(int i) throws IOException {
        if (!FTPReply.isPositiveCompletion(mode(i))) {
            return false;
        }
        this.__fileTransferMode = i;
        return true;
    }

    public boolean remoteRetrieve(String str) throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(retr(str));
        }
        return false;
    }

    public boolean remoteStore(String str) throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stor(str));
        }
        return false;
    }

    public boolean remoteStoreUnique(String str) throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stou(str));
        }
        return false;
    }

    public boolean remoteStoreUnique() throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stou());
        }
        return false;
    }

    public boolean remoteAppend(String str) throws IOException {
        int i = this.__dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(appe(str));
        }
        return false;
    }

    public boolean completePendingCommand() throws IOException {
        return FTPReply.isPositiveCompletion(getReply());
    }

    public boolean retrieveFile(String str, OutputStream outputStream) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(13, str);
        if (_openDataConnection_ == null) {
            return false;
        }
        FilterInputStream bufferedInputStream = new BufferedInputStream(_openDataConnection_.getInputStream(), getBufferSize());
        try {
            Util.copyStream(this.__fileType == 0 ? new FromNetASCIIInputStream(bufferedInputStream) : bufferedInputStream, outputStream, getBufferSize(), -1L, null, false);
            _openDataConnection_.close();
            return completePendingCommand();
        } catch (IOException e) {
            try {
                _openDataConnection_.close();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    public InputStream retrieveFileStream(String str) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(13, str);
        if (_openDataConnection_ == null) {
            return null;
        }
        InputStream inputStream = _openDataConnection_.getInputStream();
        if (this.__fileType == 0) {
            inputStream = new FromNetASCIIInputStream(new BufferedInputStream(inputStream, getBufferSize()));
        }
        return new SocketInputStream(_openDataConnection_, inputStream);
    }

    public boolean storeFile(String str, InputStream inputStream) throws IOException {
        return __storeFile(14, str, inputStream);
    }

    public OutputStream storeFileStream(String str) throws IOException {
        return __storeFileStream(14, str);
    }

    public boolean appendFile(String str, InputStream inputStream) throws IOException {
        return __storeFile(16, str, inputStream);
    }

    public OutputStream appendFileStream(String str) throws IOException {
        return __storeFileStream(16, str);
    }

    public boolean storeUniqueFile(String str, InputStream inputStream) throws IOException {
        return __storeFile(15, str, inputStream);
    }

    public OutputStream storeUniqueFileStream(String str) throws IOException {
        return __storeFileStream(15, str);
    }

    public boolean storeUniqueFile(InputStream inputStream) throws IOException {
        return __storeFile(15, null, inputStream);
    }

    public OutputStream storeUniqueFileStream() throws IOException {
        return __storeFileStream(15, null);
    }

    public boolean allocate(int i) throws IOException {
        return FTPReply.isPositiveCompletion(allo(i));
    }

    public boolean features() throws IOException {
        return FTPReply.isPositiveCompletion(feat());
    }

    public boolean allocate(int i, int i2) throws IOException {
        return FTPReply.isPositiveCompletion(allo(i, i2));
    }

    private boolean restart(long j) throws IOException {
        this.__restartOffset = 0L;
        return FTPReply.isPositiveIntermediate(rest(Long.toString(j)));
    }

    public void setRestartOffset(long j) {
        if (j >= 0) {
            this.__restartOffset = j;
        }
    }

    public long getRestartOffset() {
        return this.__restartOffset;
    }

    public boolean rename(String str, String str2) throws IOException {
        if (FTPReply.isPositiveIntermediate(rnfr(str))) {
            return FTPReply.isPositiveCompletion(rnto(str2));
        }
        return false;
    }

    public boolean abort() throws IOException {
        return FTPReply.isPositiveCompletion(abor());
    }

    public boolean deleteFile(String str) throws IOException {
        return FTPReply.isPositiveCompletion(dele(str));
    }

    public boolean removeDirectory(String str) throws IOException {
        return FTPReply.isPositiveCompletion(rmd(str));
    }

    public boolean makeDirectory(String str) throws IOException {
        return FTPReply.isPositiveCompletion(mkd(str));
    }

    public String printWorkingDirectory() throws IOException {
        if (pwd() != 257) {
            return null;
        }
        return __parsePathname(this._replyLines.get(this._replyLines.size() - 1));
    }

    public boolean sendSiteCommand(String str) throws IOException {
        return FTPReply.isPositiveCompletion(site(str));
    }

    @Deprecated
    public String getSystemName() throws IOException {
        if (this.__systemName == null && FTPReply.isPositiveCompletion(syst())) {
            this.__systemName = this._replyLines.get(this._replyLines.size() - 1).substring(4);
        }
        return this.__systemName;
    }

    public String getSystemType() throws IOException {
        if (this.__systemName == null) {
            if (FTPReply.isPositiveCompletion(syst())) {
                this.__systemName = this._replyLines.get(this._replyLines.size() - 1).substring(4);
            } else {
                throw new IOException("Unable to determine system type - response: " + getReplyString());
            }
        }
        return this.__systemName;
    }

    public String listHelp() throws IOException {
        if (FTPReply.isPositiveCompletion(help())) {
            return getReplyString();
        }
        return null;
    }

    public String listHelp(String str) throws IOException {
        if (FTPReply.isPositiveCompletion(help(str))) {
            return getReplyString();
        }
        return null;
    }

    public boolean sendNoOp() throws IOException {
        return FTPReply.isPositiveCompletion(noop());
    }

    public String[] listNames(String str) throws IOException {
        Socket _openDataConnection_ = _openDataConnection_(27, str);
        if (_openDataConnection_ == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(_openDataConnection_.getInputStream(), getControlEncoding()));
        ArrayList arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            arrayList.add(readLine);
        }
        bufferedReader.close();
        _openDataConnection_.close();
        if (completePendingCommand()) {
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return null;
    }

    public String[] listNames() throws IOException {
        return listNames(null);
    }

    public FTPFile[] listFiles(String str) throws IOException {
        return initiateListParsing((String) null, str).getFiles();
    }

    public FTPFile[] listFiles() throws IOException {
        return listFiles(null);
    }

    public FTPFile[] listFiles(String str, FTPFileFilter fTPFileFilter) throws IOException {
        return initiateListParsing((String) null, str).getFiles(fTPFileFilter);
    }

    public FTPListParseEngine initiateListParsing() throws IOException {
        return initiateListParsing(null);
    }

    public FTPListParseEngine initiateListParsing(String str) throws IOException {
        return initiateListParsing((String) null, str);
    }

    public FTPListParseEngine initiateListParsing(String str, String str2) throws IOException {
        if (this.__entryParser == null || !this.__entryParserKey.equals(str)) {
            if (str != null) {
                this.__entryParser = this.__parserFactory.createFileEntryParser(str);
                this.__entryParserKey = str;
            } else {
                FTPClientConfig fTPClientConfig = this.__configuration;
                if (fTPClientConfig != null) {
                    this.__entryParser = this.__parserFactory.createFileEntryParser(fTPClientConfig);
                    this.__entryParserKey = this.__configuration.getServerSystemKey();
                } else {
                    String systemType = getSystemType();
                    this.__entryParser = this.__parserFactory.createFileEntryParser(systemType);
                    this.__entryParserKey = systemType;
                }
            }
        }
        return initiateListParsing(this.__entryParser, str2);
    }

    private FTPListParseEngine initiateListParsing(FTPFileEntryParser fTPFileEntryParser, String str) throws IOException {
        FTPListParseEngine fTPListParseEngine = new FTPListParseEngine(fTPFileEntryParser);
        Socket _openDataConnection_ = _openDataConnection_(26, getListArguments(str));
        if (_openDataConnection_ == null) {
            return fTPListParseEngine;
        }
        try {
            fTPListParseEngine.readServerList(_openDataConnection_.getInputStream(), getControlEncoding());
            _openDataConnection_.close();
            completePendingCommand();
            return fTPListParseEngine;
        } catch (Throwable th) {
            _openDataConnection_.close();
            throw th;
        }
    }

    protected String getListArguments(String str) {
        if (!getListHiddenFiles()) {
            return str;
        }
        if (str == null) {
            return "-a";
        }
        StringBuilder sb = new StringBuilder(str.length() + 3);
        sb.append("-a ");
        sb.append(str);
        return sb.toString();
    }

    public String getStatus() throws IOException {
        if (FTPReply.isPositiveCompletion(stat())) {
            return getReplyString();
        }
        return null;
    }

    public String getStatus(String str) throws IOException {
        if (FTPReply.isPositiveCompletion(stat(str))) {
            return getReplyString();
        }
        return null;
    }

    public String getModificationTime(String str) throws IOException {
        if (FTPReply.isPositiveCompletion(mdtm(str))) {
            return getReplyString();
        }
        return null;
    }

    public boolean setModificationTime(String str, String str2) throws IOException {
        return FTPReply.isPositiveCompletion(mfmt(str, str2));
    }

    public void setBufferSize(int i) {
        this.__bufferSize = i;
    }

    public int getBufferSize() {
        return this.__bufferSize;
    }

    @Override // org.apache.commons.net.ftp.Configurable
    public void configure(FTPClientConfig fTPClientConfig) {
        this.__configuration = fTPClientConfig;
    }

    public void setListHiddenFiles(boolean z) {
        this.__listHiddenFiles = z;
    }

    public boolean getListHiddenFiles() {
        return this.__listHiddenFiles;
    }

    public boolean isUseEPSVwithIPv4() {
        return this.__useEPSVwithIPv4;
    }

    public void setUseEPSVwithIPv4(boolean z) {
        this.__useEPSVwithIPv4 = z;
    }
}
