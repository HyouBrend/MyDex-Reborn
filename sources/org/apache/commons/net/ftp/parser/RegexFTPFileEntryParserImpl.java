package org.apache.commons.net.ftp.parser;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;

/* loaded from: classes2.dex */
public abstract class RegexFTPFileEntryParserImpl extends FTPFileEntryParserImpl {
    private Pattern pattern = null;
    private MatchResult result = null;
    protected Matcher _matcher_ = null;

    public RegexFTPFileEntryParserImpl(String str) {
        setRegex(str);
    }

    public boolean matches(String str) {
        this.result = null;
        Matcher matcher = this.pattern.matcher(str);
        this._matcher_ = matcher;
        if (matcher.matches()) {
            this.result = this._matcher_.toMatchResult();
        }
        return this.result != null;
    }

    public int getGroupCnt() {
        MatchResult matchResult = this.result;
        if (matchResult == null) {
            return 0;
        }
        return matchResult.groupCount();
    }

    public String group(int i) {
        MatchResult matchResult = this.result;
        if (matchResult == null) {
            return null;
        }
        return matchResult.group(i);
    }

    public String getGroupsAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= this.result.groupCount(); i++) {
            sb.append(i);
            sb.append(") ");
            sb.append(this.result.group(i));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public boolean setRegex(String str) {
        try {
            Pattern compile = Pattern.compile(str);
            this.pattern = compile;
            return compile != null;
        } catch (PatternSyntaxException unused) {
            throw new IllegalArgumentException("Unparseable regex supplied: " + str);
        }
    }
}
