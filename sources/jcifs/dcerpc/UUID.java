package jcifs.dcerpc;

import jcifs.dcerpc.rpc;

/* loaded from: classes2.dex */
public class UUID extends rpc.uuid_t {
    static final char[] HEXCHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static byte B(int i) {
        return (byte) (i & 255);
    }

    private static short S(int i) {
        return (short) (i & 65535);
    }

    public static int hex_to_bin(char[] cArr, int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = i; i7 < cArr.length && i5 < i2; i7++) {
            int i8 = i6 << 4;
            char c = cArr[i7];
            switch (c) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    i3 = c - '0';
                    break;
                default:
                    switch (c) {
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            i4 = c - 'A';
                            break;
                        default:
                            switch (c) {
                                case 'a':
                                case 'b':
                                case 'c':
                                case 'd':
                                case 'e':
                                case 'f':
                                    i4 = c - 'a';
                                    break;
                                default:
                                    throw new IllegalArgumentException(new String(cArr, i, i2));
                            }
                    }
                    i3 = i4 + 10;
                    break;
            }
            i6 = i8 + i3;
            i5++;
        }
        return i6;
    }

    public static String bin_to_hex(int i, int i2) {
        char[] cArr = new char[i2];
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                cArr[i3] = HEXCHARS[i & 15];
                i >>>= 4;
                i2 = i3;
            } else {
                return new String(cArr);
            }
        }
    }

    public UUID(rpc.uuid_t uuid_tVar) {
        this.time_low = uuid_tVar.time_low;
        this.time_mid = uuid_tVar.time_mid;
        this.time_hi_and_version = uuid_tVar.time_hi_and_version;
        this.clock_seq_hi_and_reserved = uuid_tVar.clock_seq_hi_and_reserved;
        this.clock_seq_low = uuid_tVar.clock_seq_low;
        this.node = new byte[6];
        this.node[0] = uuid_tVar.node[0];
        this.node[1] = uuid_tVar.node[1];
        this.node[2] = uuid_tVar.node[2];
        this.node[3] = uuid_tVar.node[3];
        this.node[4] = uuid_tVar.node[4];
        this.node[5] = uuid_tVar.node[5];
    }

    public UUID(String str) {
        char[] charArray = str.toCharArray();
        this.time_low = hex_to_bin(charArray, 0, 8);
        this.time_mid = S(hex_to_bin(charArray, 9, 4));
        this.time_hi_and_version = S(hex_to_bin(charArray, 14, 4));
        this.clock_seq_hi_and_reserved = B(hex_to_bin(charArray, 19, 2));
        this.clock_seq_low = B(hex_to_bin(charArray, 21, 2));
        this.node = new byte[6];
        this.node[0] = B(hex_to_bin(charArray, 24, 2));
        this.node[1] = B(hex_to_bin(charArray, 26, 2));
        this.node[2] = B(hex_to_bin(charArray, 28, 2));
        this.node[3] = B(hex_to_bin(charArray, 30, 2));
        this.node[4] = B(hex_to_bin(charArray, 32, 2));
        this.node[5] = B(hex_to_bin(charArray, 34, 2));
    }

    public String toString() {
        return bin_to_hex(this.time_low, 8) + '-' + bin_to_hex(this.time_mid, 4) + '-' + bin_to_hex(this.time_hi_and_version, 4) + '-' + bin_to_hex(this.clock_seq_hi_and_reserved, 2) + bin_to_hex(this.clock_seq_low, 2) + '-' + bin_to_hex(this.node[0], 2) + bin_to_hex(this.node[1], 2) + bin_to_hex(this.node[2], 2) + bin_to_hex(this.node[3], 2) + bin_to_hex(this.node[4], 2) + bin_to_hex(this.node[5], 2);
    }
}
