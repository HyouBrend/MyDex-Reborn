package jcifs.netbios;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.UnsupportedEncodingException;
import jcifs.Config;
import jcifs.util.Hexdump;
import kotlin.UByte;

/* loaded from: classes2.dex */
public class Name {
    private static final String DEFAULT_SCOPE = Config.getProperty("jcifs.netbios.scope");
    static final String OEM_ENCODING = Config.getProperty("jcifs.encoding", System.getProperty("file.encoding"));
    private static final int SCOPE_OFFSET = 33;
    private static final int TYPE_OFFSET = 31;
    public int hexCode;
    public String name;
    public String scope;
    int srcHashCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Name() {
    }

    public Name(String str, int i, String str2) {
        this.name = (str.length() > 15 ? str.substring(0, 15) : str).toUpperCase();
        this.hexCode = i;
        this.scope = (str2 == null || str2.length() <= 0) ? DEFAULT_SCOPE : str2;
        this.srcHashCode = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeWireFormat(byte[] bArr, int i) {
        bArr[i] = 32;
        try {
            byte[] bytes = this.name.getBytes(OEM_ENCODING);
            int i2 = 0;
            while (i2 < bytes.length) {
                int i3 = i2 * 2;
                bArr[i3 + 1 + i] = (byte) (((bytes[i2] & 240) >> 4) + 65);
                bArr[i3 + 2 + i] = (byte) ((15 & bytes[i2]) + 65);
                i2++;
            }
            while (i2 < 15) {
                int i4 = i2 * 2;
                bArr[i4 + 1 + i] = 67;
                bArr[i4 + 2 + i] = 65;
                i2++;
            }
            int i5 = i + 31;
            int i6 = this.hexCode;
            bArr[i5] = (byte) (((i6 & 240) >> 4) + 65);
            bArr[i5 + 1] = (byte) ((i6 & 15) + 65);
        } catch (UnsupportedEncodingException unused) {
        }
        return writeScopeWireFormat(bArr, i + 33) + 33;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readWireFormat(byte[] bArr, int i) {
        byte[] bArr2 = new byte[33];
        int i2 = 15;
        for (int i3 = 0; i3 < 15; i3++) {
            int i4 = i3 * 2;
            byte b = (byte) (((bArr[(i4 + 1) + i] & UByte.MAX_VALUE) - 65) << 4);
            bArr2[i3] = b;
            byte b2 = (byte) (((byte) (((bArr[(i4 + 2) + i] & UByte.MAX_VALUE) - 65) & 15)) | b);
            bArr2[i3] = b2;
            if (b2 != 32) {
                i2 = i3 + 1;
            }
        }
        try {
            this.name = new String(bArr2, 0, i2, OEM_ENCODING);
        } catch (UnsupportedEncodingException unused) {
        }
        int i5 = i + 31;
        this.hexCode = (((bArr[i5 + 1] & UByte.MAX_VALUE) - 65) & 15) | (((bArr[i5] & UByte.MAX_VALUE) - 65) << 4);
        return readScopeWireFormat(bArr, i + 33) + 33;
    }

    int writeScopeWireFormat(byte[] bArr, int i) {
        String str = this.scope;
        if (str == null) {
            bArr[i] = 0;
            return 1;
        }
        int i2 = i + 1;
        bArr[i] = 46;
        try {
            System.arraycopy(str.getBytes(OEM_ENCODING), 0, bArr, i2, this.scope.length());
        } catch (UnsupportedEncodingException unused) {
        }
        int length = i2 + this.scope.length();
        bArr[length] = 0;
        int i3 = (length + 1) - 2;
        int length2 = i3 - this.scope.length();
        int i4 = 0;
        while (true) {
            if (bArr[i3] == 46) {
                bArr[i3] = (byte) i4;
                i4 = 0;
            } else {
                i4++;
            }
            int i5 = i3 - 1;
            if (i3 <= length2) {
                return this.scope.length() + 2;
            }
            i3 = i5;
        }
    }

    int readScopeWireFormat(byte[] bArr, int i) {
        int i2;
        int i3 = i + 1;
        int i4 = bArr[i] & 255;
        if (i4 == 0) {
            this.scope = null;
            return 1;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer(new String(bArr, i3, i4, OEM_ENCODING));
            int i5 = i3 + i4;
            while (true) {
                i2 = i5 + 1;
                try {
                    int i6 = bArr[i5] & 255;
                    if (i6 == 0) {
                        break;
                    }
                    stringBuffer.append('.').append(new String(bArr, i2, i6, OEM_ENCODING));
                    i5 = i6 + i2;
                } catch (UnsupportedEncodingException unused) {
                    i3 = i2;
                    i2 = i3;
                    return i2 - i;
                }
            }
            this.scope = stringBuffer.toString();
        } catch (UnsupportedEncodingException unused2) {
        }
        return i2 - i;
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() + (this.hexCode * 65599) + (this.srcHashCode * 65599);
        String str = this.scope;
        return (str == null || str.length() == 0) ? hashCode : hashCode + this.scope.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Name)) {
            return false;
        }
        Name name = (Name) obj;
        return (this.scope == null && name.scope == null) ? this.name.equals(name.name) && this.hexCode == name.hexCode : this.name.equals(name.name) && this.hexCode == name.hexCode && this.scope.equals(name.scope);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.name;
        if (str == null) {
            str = "null";
        } else if (str.charAt(0) == 1) {
            char[] charArray = str.toCharArray();
            charArray[0] = '.';
            charArray[1] = '.';
            charArray[14] = '.';
            str = new String(charArray);
        }
        stringBuffer.append(str).append(SimpleComparison.LESS_THAN_OPERATION).append(Hexdump.toHexString(this.hexCode, 2)).append(SimpleComparison.GREATER_THAN_OPERATION);
        if (this.scope != null) {
            stringBuffer.append(".").append(this.scope);
        }
        return stringBuffer.toString();
    }
}
