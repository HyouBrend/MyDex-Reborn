package org.apache.commons.net.ftp;

import androidx.exifinterface.media.ExifInterface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public class FTPSClient extends FTPClient {
    private static final String DEFAULT_PROTOCOL = "TLS";

    @Deprecated
    public static String KEYSTORE_ALGORITHM;

    @Deprecated
    public static String PROVIDER;

    @Deprecated
    public static String STORE_TYPE;

    @Deprecated
    public static String TRUSTSTORE_ALGORITHM;
    private String auth;
    private SSLContext context;
    private boolean isClientMode;
    private boolean isCreation;
    private final boolean isImplicit;
    private boolean isNeedClientAuth;
    private boolean isWantClientAuth;
    private KeyManager keyManager;
    private Socket plainSocket;
    private final String protocol;
    private String[] protocols;
    private String[] suites;
    private TrustManager trustManager;
    private static final String DEFAULT_PROT = "C";
    private static final String[] PROT_COMMAND_VALUE = {DEFAULT_PROT, ExifInterface.LONGITUDE_EAST, ExifInterface.LATITUDE_SOUTH, "P"};

    public FTPSClient() throws NoSuchAlgorithmException {
        this.auth = DEFAULT_PROTOCOL;
        this.isCreation = true;
        this.isClientMode = true;
        this.isNeedClientAuth = false;
        this.isWantClientAuth = false;
        this.suites = null;
        this.protocols = null;
        this.trustManager = new FTPSTrustManager();
        this.protocol = DEFAULT_PROTOCOL;
        this.isImplicit = false;
    }

    public FTPSClient(boolean z) throws NoSuchAlgorithmException {
        this.auth = DEFAULT_PROTOCOL;
        this.isCreation = true;
        this.isClientMode = true;
        this.isNeedClientAuth = false;
        this.isWantClientAuth = false;
        this.suites = null;
        this.protocols = null;
        this.trustManager = new FTPSTrustManager();
        this.protocol = DEFAULT_PROTOCOL;
        this.isImplicit = z;
    }

    public FTPSClient(String str) throws NoSuchAlgorithmException {
        this.auth = DEFAULT_PROTOCOL;
        this.isCreation = true;
        this.isClientMode = true;
        this.isNeedClientAuth = false;
        this.isWantClientAuth = false;
        this.suites = null;
        this.protocols = null;
        this.trustManager = new FTPSTrustManager();
        this.protocol = str;
        this.isImplicit = false;
    }

    public FTPSClient(String str, boolean z) throws NoSuchAlgorithmException {
        this.auth = DEFAULT_PROTOCOL;
        this.isCreation = true;
        this.isClientMode = true;
        this.isNeedClientAuth = false;
        this.isWantClientAuth = false;
        this.suites = null;
        this.protocols = null;
        this.trustManager = new FTPSTrustManager();
        this.protocol = str;
        this.isImplicit = z;
    }

    public FTPSClient(boolean z, SSLContext sSLContext) {
        this.auth = DEFAULT_PROTOCOL;
        this.isCreation = true;
        this.isClientMode = true;
        this.isNeedClientAuth = false;
        this.isWantClientAuth = false;
        this.suites = null;
        this.protocols = null;
        this.trustManager = new FTPSTrustManager();
        this.isImplicit = z;
        this.context = sSLContext;
        this.protocol = DEFAULT_PROTOCOL;
    }

    public FTPSClient(SSLContext sSLContext) {
        this(false, sSLContext);
    }

    public void setAuthValue(String str) {
        this.auth = str;
    }

    public String getAuthValue() {
        return this.auth;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.ftp.FTPClient, org.apache.commons.net.ftp.FTP, org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        if (this.isImplicit) {
            sslNegotiation();
        }
        super._connectAction_();
        if (this.isImplicit) {
            return;
        }
        execAUTH();
        sslNegotiation();
    }

    private void execAUTH() throws SSLException, IOException {
        int sendCommand = sendCommand(FTPSCommand._commands[0], this.auth);
        if (334 != sendCommand && 234 != sendCommand) {
            throw new SSLException(getReplyString());
        }
    }

    private void initSslContext() throws IOException {
        if (this.context == null) {
            try {
                SSLContext sSLContext = SSLContext.getInstance(this.protocol);
                this.context = sSLContext;
                sSLContext.init(new KeyManager[]{getKeyManager()}, new TrustManager[]{getTrustManager()}, null);
            } catch (KeyManagementException e) {
                IOException iOException = new IOException("Could not initialize SSL context");
                iOException.initCause(e);
                throw iOException;
            } catch (NoSuchAlgorithmException e2) {
                IOException iOException2 = new IOException("Could not initialize SSL context");
                iOException2.initCause(e2);
                throw iOException2;
            }
        }
    }

    private void sslNegotiation() throws IOException {
        this.plainSocket = this._socket_;
        initSslContext();
        SSLSocket sSLSocket = (SSLSocket) this.context.getSocketFactory().createSocket(this._socket_, this._socket_.getInetAddress().getHostAddress(), this._socket_.getPort(), true);
        sSLSocket.setEnableSessionCreation(this.isCreation);
        sSLSocket.setUseClientMode(this.isClientMode);
        if (!this.isClientMode) {
            sSLSocket.setNeedClientAuth(this.isNeedClientAuth);
            sSLSocket.setWantClientAuth(this.isWantClientAuth);
        }
        String[] strArr = this.protocols;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = this.suites;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
        sSLSocket.startHandshake();
        this._socket_ = sSLSocket;
        this._controlInput_ = new BufferedReader(new InputStreamReader(sSLSocket.getInputStream(), getControlEncoding()));
        this._controlOutput_ = new BufferedWriter(new OutputStreamWriter(sSLSocket.getOutputStream(), getControlEncoding()));
    }

    private KeyManager getKeyManager() {
        return this.keyManager;
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    public void setEnabledSessionCreation(boolean z) {
        this.isCreation = z;
    }

    public boolean getEnableSessionCreation() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getEnableSessionCreation();
        }
        return false;
    }

    public void setNeedClientAuth(boolean z) {
        this.isNeedClientAuth = z;
    }

    public boolean getNeedClientAuth() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getNeedClientAuth();
        }
        return false;
    }

    public void setWantClientAuth(boolean z) {
        this.isWantClientAuth = z;
    }

    public boolean getWantClientAuth() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getWantClientAuth();
        }
        return false;
    }

    public void setUseClientMode(boolean z) {
        this.isClientMode = z;
    }

    public boolean getUseClientMode() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getUseClientMode();
        }
        return false;
    }

    public void setEnabledCipherSuites(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        this.suites = strArr2;
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
    }

    public String[] getEnabledCipherSuites() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getEnabledCipherSuites();
        }
        return null;
    }

    public void setEnabledProtocols(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        this.protocols = strArr2;
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
    }

    public String[] getEnabledProtocols() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getEnabledProtocols();
        }
        return null;
    }

    public void execPBSZ(long j) throws SSLException, IOException {
        if (j < 0 || InternalZipConstants.ZIP_64_LIMIT < j) {
            throw new IllegalArgumentException();
        }
        if (200 != sendCommand(FTPSCommand._commands[2], String.valueOf(j))) {
            throw new SSLException(getReplyString());
        }
    }

    public void execPROT(String str) throws SSLException, IOException {
        if (str == null) {
            str = DEFAULT_PROT;
        }
        if (!checkPROTValue(str)) {
            throw new IllegalArgumentException();
        }
        if (200 != sendCommand(FTPSCommand._commands[3], str)) {
            throw new SSLException(getReplyString());
        }
        if (DEFAULT_PROT.equals(str)) {
            setSocketFactory(null);
            setServerSocketFactory(null);
        } else {
            setSocketFactory(new FTPSSocketFactory(this.context));
            setServerSocketFactory(new FTPSServerSocketFactory(this.context));
            initSslContext();
        }
    }

    private boolean checkPROTValue(String str) {
        int i = 0;
        while (true) {
            String[] strArr = PROT_COMMAND_VALUE;
            if (i >= strArr.length) {
                return false;
            }
            if (strArr[i].equals(str)) {
                return true;
            }
            i++;
        }
    }

    @Override // org.apache.commons.net.ftp.FTP
    public int sendCommand(String str, String str2) throws IOException {
        int sendCommand = super.sendCommand(str, str2);
        if (FTPSCommand._commands[4].equals(str)) {
            if (200 == sendCommand) {
                this._socket_ = this.plainSocket;
                this._controlInput_ = new BufferedReader(new InputStreamReader(this._socket_.getInputStream(), getControlEncoding()));
                this._controlOutput_ = new BufferedWriter(new OutputStreamWriter(this._socket_.getOutputStream(), getControlEncoding()));
                setSocketFactory(null);
            } else {
                throw new SSLException(getReplyString());
            }
        }
        return sendCommand;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.ftp.FTPClient
    public Socket _openDataConnection_(int i, String str) throws IOException {
        Socket _openDataConnection_ = super._openDataConnection_(i, str);
        if (_openDataConnection_ != null && (_openDataConnection_ instanceof SSLSocket)) {
            SSLSocket sSLSocket = (SSLSocket) _openDataConnection_;
            sSLSocket.setUseClientMode(this.isClientMode);
            sSLSocket.setEnableSessionCreation(this.isCreation);
            if (!this.isClientMode) {
                sSLSocket.setNeedClientAuth(this.isNeedClientAuth);
                sSLSocket.setWantClientAuth(this.isWantClientAuth);
            }
            String[] strArr = this.suites;
            if (strArr != null) {
                sSLSocket.setEnabledCipherSuites(strArr);
            }
            String[] strArr2 = this.protocols;
            if (strArr2 != null) {
                sSLSocket.setEnabledProtocols(strArr2);
            }
            sSLSocket.startHandshake();
        }
        return _openDataConnection_;
    }

    public TrustManager getTrustManager() {
        return this.trustManager;
    }

    public void setTrustManager(TrustManager trustManager) {
        this.trustManager = trustManager;
    }
}
