package net.lingala.zip4j.util;

/* loaded from: classes2.dex */
public interface InternalZipConstants {
    public static final int AESSIG = 39169;
    public static final int AES_AUTH_LENGTH = 10;
    public static final int AES_BLOCK_SIZE = 16;
    public static final long ARCEXTDATREC = 134630224;
    public static final int BUFF_SIZE = 4096;
    public static final int CENATT = 36;
    public static final int CENATX = 38;
    public static final int CENCOM = 32;
    public static final int CENCRC = 16;
    public static final int CENDSK = 34;
    public static final int CENEXT = 30;
    public static final int CENFLG = 8;
    public static final int CENHDR = 46;
    public static final int CENHOW = 10;
    public static final int CENLEN = 24;
    public static final int CENNAM = 28;
    public static final int CENOFF = 42;
    public static final long CENSIG = 33639248;
    public static final int CENSIZ = 20;
    public static final int CENTIM = 12;
    public static final int CENVEM = 4;
    public static final int CENVER = 6;
    public static final String CHARSET_CP850 = "Cp850";
    public static final String CHARSET_UTF8 = "UTF8";
    public static final long DIGSIG = 84233040;
    public static final int ENDCOM = 20;
    public static final int ENDHDR = 22;
    public static final int ENDOFF = 16;
    public static final long ENDSIG = 101010256;
    public static final int ENDSIZ = 12;
    public static final int ENDSUB = 8;
    public static final int ENDTOT = 10;
    public static final int EXTCRC = 4;
    public static final int EXTHDR = 16;
    public static final int EXTLEN = 12;
    public static final int EXTRAFIELDZIP64LENGTH = 1;
    public static final long EXTSIG = 134695760;
    public static final int EXTSIZ = 8;
    public static final int FILE_MODE_ARCHIVE = 32;
    public static final int FILE_MODE_HIDDEN = 2;
    public static final int FILE_MODE_HIDDEN_ARCHIVE = 34;
    public static final int FILE_MODE_NONE = 0;
    public static final int FILE_MODE_READ_ONLY = 1;
    public static final int FILE_MODE_READ_ONLY_ARCHIVE = 33;
    public static final int FILE_MODE_READ_ONLY_HIDDEN = 3;
    public static final int FILE_MODE_READ_ONLY_HIDDEN_ARCHIVE = 35;
    public static final int FILE_MODE_SYSTEM = 38;
    public static final int FOLDER_MODE_ARCHIVE = 48;
    public static final int FOLDER_MODE_HIDDEN = 18;
    public static final int FOLDER_MODE_HIDDEN_ARCHIVE = 50;
    public static final int FOLDER_MODE_NONE = 16;
    public static final int LIST_TYPE_FILE = 1;
    public static final int LIST_TYPE_STRING = 2;
    public static final int LOCCRC = 14;
    public static final int LOCEXT = 28;
    public static final int LOCFLG = 6;
    public static final int LOCHDR = 30;
    public static final int LOCHOW = 8;
    public static final int LOCLEN = 22;
    public static final int LOCNAM = 26;
    public static final long LOCSIG = 67324752;
    public static final int LOCSIZ = 18;
    public static final int LOCTIM = 10;
    public static final int LOCVER = 4;
    public static final int MIN_SPLIT_LENGTH = 65536;
    public static final int MODE_UNZIP = 2;
    public static final int MODE_ZIP = 1;
    public static final String OFFSET_CENTRAL_DIR = "offsetCentralDir";
    public static final String READ_MODE = "r";
    public static final long SPLITSIG = 134695760;
    public static final int STD_DEC_HDR_SIZE = 12;
    public static final String THREAD_NAME = "Zip4j";
    public static final int UFT8_NAMES_FLAG = 2048;
    public static final int UPDATE_LFH_COMP_SIZE = 18;
    public static final int UPDATE_LFH_CRC = 14;
    public static final String VERSION = "1.2.4";
    public static final String WRITE_MODE = "rw";
    public static final long ZIP64ENDCENDIRLOC = 117853008;
    public static final long ZIP64ENDCENDIRREC = 101075792;
    public static final long ZIP_64_LIMIT = 4294967295L;
    public static final String ZIP_FILE_SEPARATOR = "/";
    public static final String CHARSET_DEFAULT = System.getProperty("file.encoding");
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
}
