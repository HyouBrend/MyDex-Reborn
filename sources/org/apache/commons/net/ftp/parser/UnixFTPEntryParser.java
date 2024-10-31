package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPClientConfig;

/* loaded from: classes2.dex */
public class UnixFTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    static final String DEFAULT_DATE_FORMAT = "MMM d yyyy";
    static final String DEFAULT_RECENT_DATE_FORMAT = "MMM d HH:mm";
    private static final String REGEX = "([bcdelfmpSs-])(((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-])))\\+?\\s*(\\d+)\\s+(?:(\\S+(?:\\s\\S+)*?)\\s+)?(?:(\\S+(?:\\s\\S+)*)\\s+)?(\\d+(?:,\\s*\\d+)?)\\s+((?:\\d+[-/]\\d+[-/]\\d+)|(?:[a-zA-Z]{3}\\s+\\d{1,2})|(?:\\d{1,2}\\s+[a-zA-Z]{3}))\\s+(\\d+(?::\\d+)?)\\s+(\\S*)(\\s*.*)";
    static final String NUMERIC_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final FTPClientConfig NUMERIC_DATE_CONFIG = new FTPClientConfig(FTPClientConfig.SYST_UNIX, NUMERIC_DATE_FORMAT, null, null, null, null);

    public UnixFTPEntryParser() {
        this(null);
    }

    public UnixFTPEntryParser(FTPClientConfig fTPClientConfig) {
        super(REGEX);
        configure(fTPClientConfig);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:3|(2:4|5)|6|(12:8|(10:10|12|(3:14|(2:21|22)(2:18|19)|20)|23|(2:41|42)|25|26|27|(1:29)(2:32|(2:34|(1:36)(1:37))(1:38))|30)|48|12|(0)|23|(0)|25|26|27|(0)(0)|30)|49|47|12|(0)|23|(0)|25|26|27|(0)(0)|30) */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.commons.net.ftp.FTPFile parseFTPEntry(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.ftp.parser.UnixFTPEntryParser.parseFTPEntry(java.lang.String):org.apache.commons.net.ftp.FTPFile");
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_UNIX, "MMM d yyyy", "MMM d HH:mm", null, null, null);
    }
}
