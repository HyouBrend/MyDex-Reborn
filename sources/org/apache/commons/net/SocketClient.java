package org.apache.commons.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;

/* loaded from: classes2.dex */
public abstract class SocketClient {
    private static final int DEFAULT_CONNECT_TIMEOUT = 0;
    public static final String NETASCII_EOL = "\r\n";
    private static final SocketFactory __DEFAULT_SOCKET_FACTORY = SocketFactory.getDefault();
    private static final ServerSocketFactory __DEFAULT_SERVER_SOCKET_FACTORY = ServerSocketFactory.getDefault();
    protected int connectTimeout = 0;
    int receiveBufferSize = -1;
    int sendBufferSize = -1;
    protected Socket _socket_ = null;
    protected InputStream _input_ = null;
    protected OutputStream _output_ = null;
    protected int _timeout_ = 0;
    protected int _defaultPort_ = 0;
    protected SocketFactory _socketFactory_ = __DEFAULT_SOCKET_FACTORY;
    protected ServerSocketFactory _serverSocketFactory_ = __DEFAULT_SERVER_SOCKET_FACTORY;

    /* JADX INFO: Access modifiers changed from: protected */
    public void _connectAction_() throws IOException {
        this._socket_.setSoTimeout(this._timeout_);
        this._input_ = this._socket_.getInputStream();
        this._output_ = this._socket_.getOutputStream();
    }

    public void connect(InetAddress inetAddress, int i) throws SocketException, IOException {
        Socket createSocket = this._socketFactory_.createSocket();
        this._socket_ = createSocket;
        int i2 = this.receiveBufferSize;
        if (i2 != -1) {
            createSocket.setReceiveBufferSize(i2);
        }
        int i3 = this.sendBufferSize;
        if (i3 != -1) {
            this._socket_.setSendBufferSize(i3);
        }
        this._socket_.connect(new InetSocketAddress(inetAddress, i), this.connectTimeout);
        _connectAction_();
    }

    public void connect(String str, int i) throws SocketException, IOException {
        connect(InetAddress.getByName(str), i);
    }

    public void connect(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws SocketException, IOException {
        Socket createSocket = this._socketFactory_.createSocket();
        this._socket_ = createSocket;
        int i3 = this.receiveBufferSize;
        if (i3 != -1) {
            createSocket.setReceiveBufferSize(i3);
        }
        int i4 = this.sendBufferSize;
        if (i4 != -1) {
            this._socket_.setSendBufferSize(i4);
        }
        this._socket_.bind(new InetSocketAddress(inetAddress2, i2));
        this._socket_.connect(new InetSocketAddress(inetAddress, i), this.connectTimeout);
        _connectAction_();
    }

    public void connect(String str, int i, InetAddress inetAddress, int i2) throws SocketException, IOException {
        connect(InetAddress.getByName(str), i, inetAddress, i2);
    }

    public void connect(InetAddress inetAddress) throws SocketException, IOException {
        connect(inetAddress, this._defaultPort_);
    }

    public void connect(String str) throws SocketException, IOException {
        connect(str, this._defaultPort_);
    }

    public void disconnect() throws IOException {
        closeQuietly(this._socket_);
        closeQuietly(this._input_);
        closeQuietly(this._output_);
        this._socket_ = null;
        this._input_ = null;
        this._output_ = null;
    }

    private void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
        }
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public boolean isConnected() {
        Socket socket = this._socket_;
        if (socket == null) {
            return false;
        }
        return socket.isConnected();
    }

    public void setDefaultPort(int i) {
        this._defaultPort_ = i;
    }

    public int getDefaultPort() {
        return this._defaultPort_;
    }

    public void setDefaultTimeout(int i) {
        this._timeout_ = i;
    }

    public int getDefaultTimeout() {
        return this._timeout_;
    }

    public void setSoTimeout(int i) throws SocketException {
        this._socket_.setSoTimeout(i);
    }

    public void setSendBufferSize(int i) throws SocketException {
        this.sendBufferSize = i;
    }

    public void setReceiveBufferSize(int i) throws SocketException {
        this.receiveBufferSize = i;
    }

    public int getSoTimeout() throws SocketException {
        return this._socket_.getSoTimeout();
    }

    public void setTcpNoDelay(boolean z) throws SocketException {
        this._socket_.setTcpNoDelay(z);
    }

    public boolean getTcpNoDelay() throws SocketException {
        return this._socket_.getTcpNoDelay();
    }

    public void setKeepAlive(boolean z) throws SocketException {
        this._socket_.setKeepAlive(z);
    }

    public boolean getKeepAlive() throws SocketException {
        return this._socket_.getKeepAlive();
    }

    public void setSoLinger(boolean z, int i) throws SocketException {
        this._socket_.setSoLinger(z, i);
    }

    public int getSoLinger() throws SocketException {
        return this._socket_.getSoLinger();
    }

    public int getLocalPort() {
        return this._socket_.getLocalPort();
    }

    public InetAddress getLocalAddress() {
        return this._socket_.getLocalAddress();
    }

    public int getRemotePort() {
        return this._socket_.getPort();
    }

    public InetAddress getRemoteAddress() {
        return this._socket_.getInetAddress();
    }

    public boolean verifyRemote(Socket socket) {
        return socket.getInetAddress().equals(getRemoteAddress());
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        if (socketFactory == null) {
            this._socketFactory_ = __DEFAULT_SOCKET_FACTORY;
        } else {
            this._socketFactory_ = socketFactory;
        }
    }

    public void setServerSocketFactory(ServerSocketFactory serverSocketFactory) {
        if (serverSocketFactory == null) {
            this._serverSocketFactory_ = __DEFAULT_SERVER_SOCKET_FACTORY;
        } else {
            this._serverSocketFactory_ = serverSocketFactory;
        }
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public ServerSocketFactory getServerSocketFactory() {
        return this._serverSocketFactory_;
    }
}
