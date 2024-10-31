package org.apache.commons.net.smtp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;

/* loaded from: classes2.dex */
public class SMTP extends SocketClient {
    public static final int DEFAULT_PORT = 25;
    private static final String __DEFAULT_ENCODING = "ISO-8859-1";
    private StringBuffer __commandBuffer;
    protected ProtocolCommandSupport _commandSupport_;
    boolean _newReplyString;
    BufferedReader _reader;
    int _replyCode;
    ArrayList<String> _replyLines;
    String _replyString;
    BufferedWriter _writer;
    private String encoding;

    public SMTP() {
        this.encoding = "ISO-8859-1";
        setDefaultPort(25);
        this.__commandBuffer = new StringBuffer();
        this._replyLines = new ArrayList<>();
        this._newReplyString = false;
        this._replyString = null;
        this._commandSupport_ = new ProtocolCommandSupport(this);
    }

    public SMTP(String str) {
        this();
        this.encoding = str;
    }

    private int __sendCommand(String str, String str2, boolean z) throws IOException {
        this.__commandBuffer.setLength(0);
        this.__commandBuffer.append(str);
        if (str2 != null) {
            if (z) {
                this.__commandBuffer.append(' ');
            }
            this.__commandBuffer.append(str2);
        }
        this.__commandBuffer.append(SocketClient.NETASCII_EOL);
        BufferedWriter bufferedWriter = this._writer;
        String stringBuffer = this.__commandBuffer.toString();
        bufferedWriter.write(stringBuffer);
        this._writer.flush();
        if (this._commandSupport_.getListenerCount() > 0) {
            this._commandSupport_.fireCommandSent(str, stringBuffer);
        }
        __getReply();
        return this._replyCode;
    }

    private int __sendCommand(int i, String str, boolean z) throws IOException {
        return __sendCommand(SMTPCommand._commands[i], str, z);
    }

    private void __getReply() throws IOException {
        this._newReplyString = true;
        this._replyLines.clear();
        String readLine = this._reader.readLine();
        if (readLine == null) {
            throw new SMTPConnectionClosedException("Connection closed without indication.");
        }
        int length = readLine.length();
        if (length < 3) {
            throw new MalformedServerReplyException("Truncated server reply: " + readLine);
        }
        try {
            this._replyCode = Integer.parseInt(readLine.substring(0, 3));
            this._replyLines.add(readLine);
            if (length > 3 && readLine.charAt(3) == '-') {
                while (true) {
                    String readLine2 = this._reader.readLine();
                    if (readLine2 == null) {
                        throw new SMTPConnectionClosedException("Connection closed without indication.");
                    }
                    this._replyLines.add(readLine2);
                    if (readLine2.length() >= 4 && readLine2.charAt(3) != '-' && Character.isDigit(readLine2.charAt(0))) {
                        break;
                    }
                }
            }
            if (this._commandSupport_.getListenerCount() > 0) {
                this._commandSupport_.fireReplyReceived(this._replyCode, getReplyString());
            }
            if (this._replyCode == 421) {
                throw new SMTPConnectionClosedException("SMTP response 421 received.  Server closed connection.");
            }
        } catch (NumberFormatException unused) {
            throw new MalformedServerReplyException("Could not parse response code.\nServer Reply: " + readLine);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.SocketClient
    public void _connectAction_() throws IOException {
        super._connectAction_();
        this._reader = new BufferedReader(new InputStreamReader(this._input_, this.encoding));
        this._writer = new BufferedWriter(new OutputStreamWriter(this._output_, this.encoding));
        __getReply();
    }

    public void addProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this._commandSupport_.addProtocolCommandListener(protocolCommandListener);
    }

    public void removeProtocolCommandistener(ProtocolCommandListener protocolCommandListener) {
        this._commandSupport_.removeProtocolCommandListener(protocolCommandListener);
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        this._reader = null;
        this._writer = null;
        this._replyString = null;
        this._replyLines.clear();
        this._newReplyString = false;
    }

    public int sendCommand(String str, String str2) throws IOException {
        return __sendCommand(str, str2, true);
    }

    public int sendCommand(int i, String str) throws IOException {
        return sendCommand(SMTPCommand._commands[i], str);
    }

    public int sendCommand(String str) throws IOException {
        return sendCommand(str, (String) null);
    }

    public int sendCommand(int i) throws IOException {
        return sendCommand(i, (String) null);
    }

    public int getReplyCode() {
        return this._replyCode;
    }

    public int getReply() throws IOException {
        __getReply();
        return this._replyCode;
    }

    public String[] getReplyStrings() {
        ArrayList<String> arrayList = this._replyLines;
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String getReplyString() {
        if (!this._newReplyString) {
            return this._replyString;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this._replyLines.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(SocketClient.NETASCII_EOL);
        }
        this._newReplyString = false;
        String sb2 = sb.toString();
        this._replyString = sb2;
        return sb2;
    }

    public int helo(String str) throws IOException {
        return sendCommand(0, str);
    }

    public int mail(String str) throws IOException {
        return __sendCommand(1, str, false);
    }

    public int rcpt(String str) throws IOException {
        return __sendCommand(2, str, false);
    }

    public int data() throws IOException {
        return sendCommand(3);
    }

    public int send(String str) throws IOException {
        return sendCommand(4, str);
    }

    public int soml(String str) throws IOException {
        return sendCommand(5, str);
    }

    public int saml(String str) throws IOException {
        return sendCommand(6, str);
    }

    public int rset() throws IOException {
        return sendCommand(7);
    }

    public int vrfy(String str) throws IOException {
        return sendCommand(8, str);
    }

    public int expn(String str) throws IOException {
        return sendCommand(9, str);
    }

    public int help() throws IOException {
        return sendCommand(10);
    }

    public int help(String str) throws IOException {
        return sendCommand(10, str);
    }

    public int noop() throws IOException {
        return sendCommand(11);
    }

    public int turn() throws IOException {
        return sendCommand(12);
    }

    public int quit() throws IOException {
        return sendCommand(13);
    }
}
