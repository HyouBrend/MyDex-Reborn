package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

/* loaded from: classes2.dex */
public class OS400FTPEntryParser extends ConfigurableFTPFileEntryParserImpl {
    private static final String DEFAULT_DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private static final String REGEX = "(\\S+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)\\s+(\\*\\S+)\\s+(\\S+/?)\\s*";

    public OS400FTPEntryParser() {
        this(null);
    }

    public OS400FTPEntryParser(FTPClientConfig fTPClientConfig) {
        super(REGEX);
        configure(fTPClientConfig);
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParser
    public FTPFile parseFTPEntry(String str) {
        FTPFile fTPFile = new FTPFile();
        fTPFile.setRawListing(str);
        if (!matches(str)) {
            return null;
        }
        String group = group(1);
        String group2 = group(2);
        StringBuilder sb = new StringBuilder();
        int i = 3;
        sb.append(group(3));
        sb.append(" ");
        sb.append(group(4));
        String sb2 = sb.toString();
        String group3 = group(5);
        String group4 = group(6);
        try {
            fTPFile.setTimestamp(super.parseTimestamp(sb2));
        } catch (ParseException unused) {
        }
        if (group3.equalsIgnoreCase("*STMF")) {
            i = 0;
        } else if (group3.equalsIgnoreCase("*DIR")) {
            i = 1;
        }
        fTPFile.setType(i);
        fTPFile.setUser(group);
        try {
            fTPFile.setSize(Long.parseLong(group2));
        } catch (NumberFormatException unused2) {
        }
        if (group4.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            group4 = group4.substring(0, group4.length() - 1);
        }
        int lastIndexOf = group4.lastIndexOf(47);
        if (lastIndexOf > -1) {
            group4 = group4.substring(lastIndexOf + 1);
        }
        fTPFile.setName(group4);
        return fTPFile;
    }

    @Override // org.apache.commons.net.ftp.parser.ConfigurableFTPFileEntryParserImpl
    protected FTPClientConfig getDefaultConfiguration() {
        return new FTPClientConfig(FTPClientConfig.SYST_OS400, DEFAULT_DATE_FORMAT, null, null, null, null);
    }
}
