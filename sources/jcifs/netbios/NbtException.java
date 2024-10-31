package jcifs.netbios;

import java.io.IOException;

/* loaded from: classes2.dex */
public class NbtException extends IOException {
    public static final int ACT_ERR = 6;
    public static final int CALLED_NOT_PRESENT = 130;
    public static final int CFT_ERR = 7;
    public static final int CONNECTION_REFUSED = -1;
    public static final int ERR_NAM_SRVC = 1;
    public static final int ERR_SSN_SRVC = 2;
    public static final int FMT_ERR = 1;
    public static final int IMP_ERR = 4;
    public static final int NOT_LISTENING_CALLED = 128;
    public static final int NOT_LISTENING_CALLING = 129;
    public static final int NO_RESOURCES = 131;
    public static final int RFS_ERR = 5;
    public static final int SRV_ERR = 2;
    public static final int SUCCESS = 0;
    public static final int UNSPECIFIED = 143;
    public int errorClass;
    public int errorCode;

    public static String getErrorString(int i, int i2) {
        if (i == 0) {
            return "SUCCESS";
        }
        if (i == 1) {
            String str = "ERR_NAM_SRVC/";
            if (i2 == 1) {
                str = "ERR_NAM_SRVC/FMT_ERR: Format Error";
            }
            return str + "Unknown error code: " + i2;
        }
        if (i != 2) {
            return "unknown error class: " + i;
        }
        if (i2 == -1) {
            return "ERR_SSN_SRVC/Connection refused";
        }
        if (i2 != 143) {
            switch (i2) {
                case 128:
                    return "ERR_SSN_SRVC/Not listening on called name";
                case NOT_LISTENING_CALLING /* 129 */:
                    return "ERR_SSN_SRVC/Not listening for calling name";
                case 130:
                    return "ERR_SSN_SRVC/Called name not present";
                case 131:
                    return "ERR_SSN_SRVC/Called name present, but insufficient resources";
                default:
                    return "ERR_SSN_SRVC/Unknown error code: " + i2;
            }
        }
        return "ERR_SSN_SRVC/Unspecified error";
    }

    public NbtException(int i, int i2) {
        super(getErrorString(i, i2));
        this.errorClass = i;
        this.errorCode = i2;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return new String("errorClass=" + this.errorClass + ",errorCode=" + this.errorCode + ",errorString=" + getErrorString(this.errorClass, this.errorCode));
    }
}
