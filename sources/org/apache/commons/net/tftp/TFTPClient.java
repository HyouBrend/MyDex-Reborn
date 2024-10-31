package org.apache.commons.net.tftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes2.dex */
public class TFTPClient extends TFTP {
    public static final int DEFAULT_MAX_TIMEOUTS = 5;
    private int __maxTimeouts = 5;

    public void setMaxTimeouts(int i) {
        if (i < 1) {
            this.__maxTimeouts = 1;
        } else {
            this.__maxTimeouts = i;
        }
    }

    public int getMaxTimeouts() {
        return this.__maxTimeouts;
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x012c, code lost:
    
        bufferedSend(new org.apache.commons.net.tftp.TFTPErrorPacket(r3.getAddress(), r3.getPort(), 5, "Unexpected host or port."));
        r0 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int receiveFile(java.lang.String r17, int r18, java.io.OutputStream r19, java.net.InetAddress r20, int r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.tftp.TFTPClient.receiveFile(java.lang.String, int, java.io.OutputStream, java.net.InetAddress, int):int");
    }

    public int receiveFile(String str, int i, OutputStream outputStream, String str2, int i2) throws UnknownHostException, IOException {
        return receiveFile(str, i, outputStream, InetAddress.getByName(str2), i2);
    }

    public int receiveFile(String str, int i, OutputStream outputStream, InetAddress inetAddress) throws IOException {
        return receiveFile(str, i, outputStream, inetAddress, 69);
    }

    public int receiveFile(String str, int i, OutputStream outputStream, String str2) throws UnknownHostException, IOException {
        return receiveFile(str, i, outputStream, InetAddress.getByName(str2), 69);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x014b, code lost:
    
        if (r13 > 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x014d, code lost:
    
        if (r9 != false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x014f, code lost:
    
        endBufferedOps();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0152, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0137, code lost:
    
        r16 = r2;
        bufferedSend(new org.apache.commons.net.tftp.TFTPErrorPacket(r7.getAddress(), r7.getPort(), 5, "Unexpected host or port."));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendFile(java.lang.String r18, int r19, java.io.InputStream r20, java.net.InetAddress r21, int r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.tftp.TFTPClient.sendFile(java.lang.String, int, java.io.InputStream, java.net.InetAddress, int):void");
    }

    public void sendFile(String str, int i, InputStream inputStream, String str2, int i2) throws UnknownHostException, IOException {
        sendFile(str, i, inputStream, InetAddress.getByName(str2), i2);
    }

    public void sendFile(String str, int i, InputStream inputStream, InetAddress inetAddress) throws IOException {
        sendFile(str, i, inputStream, inetAddress, 69);
    }

    public void sendFile(String str, int i, InputStream inputStream, String str2) throws UnknownHostException, IOException {
        sendFile(str, i, inputStream, InetAddress.getByName(str2), 69);
    }
}
