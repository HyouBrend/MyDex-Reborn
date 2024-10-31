package org.apache.commons.net.ftp;

import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.util.Base64;

/* loaded from: classes2.dex */
public class FTPHTTPClient extends FTPClient {
    private final byte[] CRLF;
    private final Base64 base64;
    private String host;
    private int port;
    private final String proxyHost;
    private final String proxyPassword;
    private final int proxyPort;
    private final String proxyUsername;

    public FTPHTTPClient(String str, int i, String str2, String str3) {
        this.base64 = new Base64();
        this.proxyHost = str;
        this.proxyPort = i;
        this.proxyUsername = str2;
        this.proxyPassword = str3;
        try {
            this.CRLF = SocketClient.NETASCII_EOL.getBytes(getControlEncoding());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public FTPHTTPClient(String str, int i) {
        this(str, i, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.ftp.FTPClient
    public Socket _openDataConnection_(int i, String str) throws IOException {
        Socket socket = new Socket(this.host, this.port);
        tunnelHandshake(this.host, this.port, socket.getInputStream(), socket.getOutputStream());
        return socket;
    }

    @Override // org.apache.commons.net.SocketClient
    public void connect(String str, int i) throws SocketException, IOException {
        this.host = str;
        this.port = i;
        this._socket_ = new Socket(this.proxyHost, this.proxyPort);
        this._input_ = this._socket_.getInputStream();
        this._output_ = this._socket_.getOutputStream();
        try {
            tunnelHandshake(str, i, this._input_, this._output_);
        } catch (Exception e) {
            IOException iOException = new IOException("Could not connect to " + str);
            iOException.initCause(e);
            throw iOException;
        }
    }

    private void tunnelHandshake(String str, int i, InputStream inputStream, OutputStream outputStream) throws IOException, UnsupportedEncodingException {
        this._output_.write(("CONNECT " + str + ":" + i + " HTTP/1.1").getBytes(getControlEncoding()));
        this._output_.write(this.CRLF);
        if (this.proxyUsername == null || this.proxyPassword == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Proxy-Authorization: Basic ");
        sb.append(this.base64.encode(this.proxyUsername + ":" + this.proxyPassword));
        sb.append(SocketClient.NETASCII_EOL);
        this._output_.write(sb.toString().getBytes(Key.STRING_CHARSET_NAME));
        this._output_.write(this.CRLF);
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this._input_));
        for (String readLine = bufferedReader.readLine(); readLine != null && readLine.length() > 0; readLine = bufferedReader.readLine()) {
            arrayList.add(readLine);
        }
        if (arrayList.size() == 0) {
            throw new IOException("No response from proxy");
        }
        String str2 = (String) arrayList.get(0);
        if (!str2.startsWith("HTTP/") || str2.length() < 12) {
            throw new IOException("Invalid response from proxy: " + str2);
        }
        if ("200".equals(str2.substring(9, 12))) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("HTTPTunnelConnector: connection failed\r\n");
        sb2.append("Response received from the proxy:\r\n");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb2.append((String) it.next());
            sb2.append(SocketClient.NETASCII_EOL);
        }
        throw new IOException(sb2.toString());
    }
}
