package jcifs.smb;

import jcifs.Config;

/* loaded from: classes2.dex */
public class BufferCache {
    private static final int MAX_BUFFERS;
    static Object[] cache;
    private static int freeBuffers;

    static {
        int i = Config.getInt("jcifs.smb.maxBuffers", 16);
        MAX_BUFFERS = i;
        cache = new Object[i];
        freeBuffers = 0;
    }

    public static byte[] getBuffer() {
        synchronized (cache) {
            if (freeBuffers > 0) {
                for (int i = 0; i < MAX_BUFFERS; i++) {
                    Object[] objArr = cache;
                    Object obj = objArr[i];
                    if (obj != null) {
                        byte[] bArr = (byte[]) obj;
                        objArr[i] = null;
                        freeBuffers--;
                        return bArr;
                    }
                }
            }
            return new byte[65535];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getBuffers(SmbComTransaction smbComTransaction, SmbComTransactionResponse smbComTransactionResponse) {
        synchronized (cache) {
            smbComTransaction.txn_buf = getBuffer();
            smbComTransactionResponse.txn_buf = getBuffer();
        }
    }

    public static void releaseBuffer(byte[] bArr) {
        synchronized (cache) {
            if (freeBuffers < MAX_BUFFERS) {
                for (int i = 0; i < MAX_BUFFERS; i++) {
                    Object[] objArr = cache;
                    if (objArr[i] == null) {
                        objArr[i] = bArr;
                        freeBuffers++;
                        return;
                    }
                }
            }
        }
    }
}
