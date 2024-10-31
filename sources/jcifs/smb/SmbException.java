package jcifs.smb;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import jcifs.dcerpc.msrpc.samr;
import jcifs.util.Hexdump;

/* loaded from: classes2.dex */
public class SmbException extends IOException implements NtStatus, DosError, WinError {
    private Throwable rootCause;
    private int status;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getMessageByCode(int i) {
        if (i == 0) {
            return "NT_STATUS_SUCCESS";
        }
        int i2 = 1;
        if ((i & samr.SE_GROUP_LOGON_ID) == -1073741824) {
            int length = NT_STATUS_CODES.length - 1;
            while (length >= i2) {
                int i3 = (i2 + length) / 2;
                if (i > NT_STATUS_CODES[i3]) {
                    i2 = i3 + 1;
                } else {
                    if (i >= NT_STATUS_CODES[i3]) {
                        return NT_STATUS_MESSAGES[i3];
                    }
                    length = i3 - 1;
                }
            }
        } else {
            int length2 = DOS_ERROR_CODES.length - 1;
            int i4 = 0;
            while (length2 >= i4) {
                int i5 = (i4 + length2) / 2;
                if (i > DOS_ERROR_CODES[i5][0]) {
                    i4 = i5 + 1;
                } else {
                    if (i >= DOS_ERROR_CODES[i5][0]) {
                        return DOS_ERROR_MESSAGES[i5];
                    }
                    length2 = i5 - 1;
                }
            }
        }
        return "0x" + Hexdump.toHexString(i, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getStatusByCode(int i) {
        if (((-1073741824) & i) != 0) {
            return i;
        }
        int length = DOS_ERROR_CODES.length - 1;
        int i2 = 0;
        while (length >= i2) {
            int i3 = (i2 + length) / 2;
            if (i > DOS_ERROR_CODES[i3][0]) {
                i2 = i3 + 1;
            } else {
                if (i >= DOS_ERROR_CODES[i3][0]) {
                    return DOS_ERROR_CODES[i3][1];
                }
                length = i3 - 1;
            }
        }
        return NtStatus.NT_STATUS_UNSUCCESSFUL;
    }

    static String getMessageByWinerrCode(int i) {
        int length = WINERR_CODES.length - 1;
        int i2 = 0;
        while (length >= i2) {
            int i3 = (i2 + length) / 2;
            if (i > WINERR_CODES[i3]) {
                i2 = i3 + 1;
            } else {
                if (i >= WINERR_CODES[i3]) {
                    return WINERR_MESSAGES[i3];
                }
                length = i3 - 1;
            }
        }
        return i + "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbException() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbException(int i, Throwable th) {
        super(getMessageByCode(i));
        this.status = getStatusByCode(i);
        this.rootCause = th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbException(String str) {
        super(str);
        this.status = NtStatus.NT_STATUS_UNSUCCESSFUL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmbException(String str, Throwable th) {
        super(str);
        this.rootCause = th;
        this.status = NtStatus.NT_STATUS_UNSUCCESSFUL;
    }

    public SmbException(int i, boolean z) {
        super(z ? getMessageByWinerrCode(i) : getMessageByCode(i));
        this.status = z ? i : getStatusByCode(i);
    }

    public int getNtStatus() {
        return this.status;
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
