package jcifs.dcerpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import jcifs.smb.WinError;
import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
public class DcerpcException extends IOException implements DcerpcError, WinError {
    private int error;
    private Throwable rootCause;

    static String getMessageByDcerpcError(int i) {
        int length = DCERPC_FAULT_CODES.length;
        int i2 = 0;
        while (length >= i2) {
            int i3 = (i2 + length) / 2;
            if (i > DCERPC_FAULT_CODES[i3]) {
                i2 = i3 + 1;
            } else {
                if (i >= DCERPC_FAULT_CODES[i3]) {
                    return DCERPC_FAULT_MESSAGES[i3];
                }
                length = i3 - 1;
            }
        }
        return "0x" + Hexdump.toHexString(i, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DcerpcException(int i) {
        super(getMessageByDcerpcError(i));
        this.error = i;
    }

    public DcerpcException(String str) {
        super(str);
    }

    public DcerpcException(String str, Throwable th) {
        super(str);
        this.rootCause = th;
    }

    public int getErrorCode() {
        return this.error;
    }

    public Throwable getRootCause() {
        return this.rootCause;
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (this.rootCause != null) {
            StringWriter stringWriter = new StringWriter();
            this.rootCause.printStackTrace(new PrintWriter(stringWriter));
            return super.toString() + "\n" + stringWriter;
        }
        return super.toString();
    }
}
