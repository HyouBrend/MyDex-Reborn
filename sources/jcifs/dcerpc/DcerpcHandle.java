package jcifs.dcerpc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.security.Principal;
import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.smb.BufferCache;
import jcifs.smb.NtlmPasswordAuthentication;

/* loaded from: classes2.dex */
public abstract class DcerpcHandle implements DcerpcConstants {
    private static int call_id = 1;
    protected DcerpcBinding binding;
    protected int max_xmit = 4280;
    protected int max_recv = 4280;
    protected int state = 0;
    protected DcerpcSecurityProvider securityProvider = null;

    public abstract void close() throws IOException;

    protected abstract void doReceiveFragment(byte[] bArr, boolean z) throws IOException;

    protected abstract void doSendFragment(byte[] bArr, int i, int i2, boolean z) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:34:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static jcifs.dcerpc.DcerpcBinding parseBinding(java.lang.String r13) throws jcifs.dcerpc.DcerpcException {
        /*
            char[] r0 = r13.toCharArray()
            r1 = 0
            r2 = 0
            r5 = r1
            r6 = r5
            r7 = r6
            r3 = 0
            r4 = 0
        Lb:
            char r8 = r0[r2]
            r9 = 5
            r10 = 1
            if (r3 == 0) goto L65
            r11 = 2
            if (r3 == r10) goto L40
            if (r3 == r11) goto L48
            if (r3 == r9) goto L1a
            int r2 = r0.length
            goto L71
        L1a:
            r9 = 61
            if (r8 != r9) goto L27
            java.lang.String r4 = r13.substring(r4, r2)
            java.lang.String r7 = r4.trim()
            goto L44
        L27:
            r9 = 44
            if (r8 == r9) goto L2f
            r9 = 93
            if (r8 != r9) goto L71
        L2f:
            java.lang.String r8 = r13.substring(r4, r2)
            java.lang.String r8 = r8.trim()
            if (r7 != 0) goto L3b
            java.lang.String r7 = "endpoint"
        L3b:
            r6.setOption(r7, r8)
            r7 = r1
            goto L71
        L40:
            r12 = 92
            if (r8 != r12) goto L47
        L44:
            int r4 = r2 + 1
            goto L71
        L47:
            r3 = 2
        L48:
            r11 = 91
            if (r8 != r11) goto L71
            java.lang.String r3 = r13.substring(r4, r2)
            java.lang.String r3 = r3.trim()
            r3.length()
            jcifs.dcerpc.DcerpcBinding r6 = new jcifs.dcerpc.DcerpcBinding
            java.lang.String r3 = r13.substring(r4, r2)
            r6.<init>(r5, r3)
            int r3 = r2 + 1
            r4 = r3
            r3 = 5
            goto L71
        L65:
            r9 = 58
            if (r8 != r9) goto L71
            java.lang.String r5 = r13.substring(r4, r2)
            int r3 = r2 + 1
            r4 = r3
            r3 = 1
        L71:
            int r2 = r2 + r10
            int r8 = r0.length
            if (r2 < r8) goto Lb
            if (r6 == 0) goto L7c
            java.lang.String r0 = r6.endpoint
            if (r0 == 0) goto L7c
            return r6
        L7c:
            jcifs.dcerpc.DcerpcException r0 = new jcifs.dcerpc.DcerpcException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid binding URL: "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.dcerpc.DcerpcHandle.parseBinding(java.lang.String):jcifs.dcerpc.DcerpcBinding");
    }

    public static DcerpcHandle getHandle(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws UnknownHostException, MalformedURLException, DcerpcException {
        if (str.startsWith("ncacn_np:")) {
            return new DcerpcPipeHandle(str, ntlmPasswordAuthentication);
        }
        throw new DcerpcException("DCERPC transport not supported: " + str);
    }

    public void bind() throws DcerpcException, IOException {
        synchronized (this) {
            try {
                try {
                    this.state = 1;
                    sendrecv(new DcerpcBind(this.binding, this));
                } catch (IOException e) {
                    this.state = 0;
                    throw e;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void sendrecv(DcerpcMessage dcerpcMessage) throws DcerpcException, IOException {
        if (this.state == 0) {
            bind();
        }
        byte[] buffer = BufferCache.getBuffer();
        try {
            NdrBuffer ndrBuffer = new NdrBuffer(buffer, 0);
            dcerpcMessage.flags = 3;
            int i = call_id;
            call_id = i + 1;
            dcerpcMessage.call_id = i;
            dcerpcMessage.encode(ndrBuffer);
            if (this.securityProvider != null) {
                ndrBuffer.setIndex(0);
                this.securityProvider.wrap(ndrBuffer);
            }
            int length = ndrBuffer.getLength() - 24;
            boolean z = true;
            int i2 = 0;
            while (i2 < length) {
                int i3 = length - i2;
                if (i3 + 24 > this.max_xmit) {
                    dcerpcMessage.flags &= -3;
                    i3 = this.max_xmit - 24;
                } else {
                    dcerpcMessage.flags |= 2;
                    dcerpcMessage.alloc_hint = i3;
                    z = false;
                }
                dcerpcMessage.length = i3 + 24;
                if (i2 > 0) {
                    dcerpcMessage.flags &= -2;
                }
                if ((dcerpcMessage.flags & 3) != 3) {
                    ndrBuffer.start = i2;
                    ndrBuffer.reset();
                    dcerpcMessage.encode_header(ndrBuffer);
                    ndrBuffer.enc_ndr_long(dcerpcMessage.alloc_hint);
                    ndrBuffer.enc_ndr_short(0);
                    ndrBuffer.enc_ndr_short(dcerpcMessage.getOpnum());
                }
                doSendFragment(buffer, i2, dcerpcMessage.length, z);
                i2 += i3;
            }
            doReceiveFragment(buffer, z);
            ndrBuffer.reset();
            ndrBuffer.setIndex(8);
            ndrBuffer.setLength(ndrBuffer.dec_ndr_short());
            DcerpcSecurityProvider dcerpcSecurityProvider = this.securityProvider;
            if (dcerpcSecurityProvider != null) {
                dcerpcSecurityProvider.unwrap(ndrBuffer);
            }
            ndrBuffer.setIndex(0);
            dcerpcMessage.decode_header(ndrBuffer);
            int i4 = (dcerpcMessage.ptype != 2 || dcerpcMessage.isFlagSet(2)) ? 24 : dcerpcMessage.length;
            byte[] bArr = null;
            NdrBuffer ndrBuffer2 = null;
            while (!dcerpcMessage.isFlagSet(2)) {
                if (bArr == null) {
                    bArr = new byte[this.max_recv];
                    ndrBuffer2 = new NdrBuffer(bArr, 0);
                }
                doReceiveFragment(bArr, z);
                ndrBuffer2.reset();
                ndrBuffer2.setIndex(8);
                ndrBuffer2.setLength(ndrBuffer2.dec_ndr_short());
                DcerpcSecurityProvider dcerpcSecurityProvider2 = this.securityProvider;
                if (dcerpcSecurityProvider2 != null) {
                    dcerpcSecurityProvider2.unwrap(ndrBuffer2);
                }
                ndrBuffer2.reset();
                dcerpcMessage.decode_header(ndrBuffer2);
                int i5 = dcerpcMessage.length - 24;
                int i6 = i4 + i5;
                if (i6 > buffer.length) {
                    byte[] bArr2 = new byte[i6];
                    System.arraycopy(buffer, 0, bArr2, 0, i4);
                    buffer = bArr2;
                }
                System.arraycopy(bArr, 24, buffer, i4, i5);
                i4 = i6;
            }
            dcerpcMessage.decode(new NdrBuffer(buffer, 0));
            BufferCache.releaseBuffer(buffer);
            DcerpcException result = dcerpcMessage.getResult();
            if (result != null) {
                throw result;
            }
        } catch (Throwable th) {
            BufferCache.releaseBuffer(buffer);
            throw th;
        }
    }

    public void setDcerpcSecurityProvider(DcerpcSecurityProvider dcerpcSecurityProvider) {
        this.securityProvider = dcerpcSecurityProvider;
    }

    public String getServer() {
        if (this instanceof DcerpcPipeHandle) {
            return ((DcerpcPipeHandle) this).pipe.getServer();
        }
        return null;
    }

    public Principal getPrincipal() {
        if (this instanceof DcerpcPipeHandle) {
            return ((DcerpcPipeHandle) this).pipe.getPrincipal();
        }
        return null;
    }

    public String toString() {
        return this.binding.toString();
    }
}
