package org.apache.commons.net.ftp.parser;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;

/* loaded from: classes2.dex */
public class FTPTimestampParserImpl implements FTPTimestampParser, Configurable {
    private SimpleDateFormat defaultDateFormat;
    private boolean lenientFutureDates = false;
    private SimpleDateFormat recentDateFormat;

    public FTPTimestampParserImpl() {
        setDefaultDateFormat(FTPTimestampParser.DEFAULT_SDF);
        setRecentDateFormat(FTPTimestampParser.DEFAULT_RECENT_SDF);
    }

    @Override // org.apache.commons.net.ftp.parser.FTPTimestampParser
    public Calendar parseTimestamp(String str) throws ParseException {
        return parseTimestamp(str, Calendar.getInstance());
    }

    public Calendar parseTimestamp(String str, Calendar calendar) throws ParseException {
        Date date;
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.setTimeZone(getServerTimeZone());
        Calendar calendar3 = (Calendar) calendar2.clone();
        calendar3.setTimeZone(getServerTimeZone());
        ParsePosition parsePosition = new ParsePosition(0);
        if (this.recentDateFormat != null) {
            if (this.lenientFutureDates) {
                calendar2.add(5, 1);
            }
            date = this.recentDateFormat.parse(str, parsePosition);
        } else {
            date = null;
        }
        if (date != null && parsePosition.getIndex() == str.length()) {
            calendar3.setTime(date);
            calendar3.set(1, calendar2.get(1));
            if (calendar3.after(calendar2)) {
                calendar3.add(1, -1);
            }
        } else {
            if (this.recentDateFormat != null) {
                parsePosition = new ParsePosition(0);
                String str2 = str + " " + calendar2.get(1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.recentDateFormat.toPattern() + " yyyy", this.recentDateFormat.getDateFormatSymbols());
                simpleDateFormat.setLenient(false);
                simpleDateFormat.setTimeZone(this.recentDateFormat.getTimeZone());
                date = simpleDateFormat.parse(str2, parsePosition);
            }
            if (date != null && parsePosition.getIndex() == str.length() + 5) {
                calendar3.setTime(date);
            } else {
                ParsePosition parsePosition2 = new ParsePosition(0);
                Date parse = this.defaultDateFormat.parse(str, parsePosition2);
                if (parse != null && parsePosition2.getIndex() == str.length()) {
                    calendar3.setTime(parse);
                } else {
                    throw new ParseException("Timestamp could not be parsed with older or recent DateFormat", parsePosition2.getIndex());
                }
            }
        }
        return calendar3;
    }

    public SimpleDateFormat getDefaultDateFormat() {
        return this.defaultDateFormat;
    }

    public String getDefaultDateFormatString() {
        return this.defaultDateFormat.toPattern();
    }

    private void setDefaultDateFormat(String str) {
        if (str != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            this.defaultDateFormat = simpleDateFormat;
            simpleDateFormat.setLenient(false);
        }
    }

    public SimpleDateFormat getRecentDateFormat() {
        return this.recentDateFormat;
    }

    public String getRecentDateFormatString() {
        return this.recentDateFormat.toPattern();
    }

    private void setRecentDateFormat(String str) {
        if (str != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            this.recentDateFormat = simpleDateFormat;
            simpleDateFormat.setLenient(false);
        }
    }

    public String[] getShortMonths() {
        return this.defaultDateFormat.getDateFormatSymbols().getShortMonths();
    }

    public TimeZone getServerTimeZone() {
        return this.defaultDateFormat.getTimeZone();
    }

    private void setServerTimeZone(String str) {
        TimeZone timeZone = TimeZone.getDefault();
        if (str != null) {
            timeZone = TimeZone.getTimeZone(str);
        }
        this.defaultDateFormat.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat = this.recentDateFormat;
        if (simpleDateFormat != null) {
            simpleDateFormat.setTimeZone(timeZone);
        }
    }

    @Override // org.apache.commons.net.ftp.Configurable
    public void configure(FTPClientConfig fTPClientConfig) {
        DateFormatSymbols lookupDateFormatSymbols;
        String serverLanguageCode = fTPClientConfig.getServerLanguageCode();
        String shortMonthNames = fTPClientConfig.getShortMonthNames();
        if (shortMonthNames != null) {
            lookupDateFormatSymbols = FTPClientConfig.getDateFormatSymbols(shortMonthNames);
        } else if (serverLanguageCode != null) {
            lookupDateFormatSymbols = FTPClientConfig.lookupDateFormatSymbols(serverLanguageCode);
        } else {
            lookupDateFormatSymbols = FTPClientConfig.lookupDateFormatSymbols("en");
        }
        String recentDateFormatStr = fTPClientConfig.getRecentDateFormatStr();
        if (recentDateFormatStr == null) {
            this.recentDateFormat = null;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(recentDateFormatStr, lookupDateFormatSymbols);
            this.recentDateFormat = simpleDateFormat;
            simpleDateFormat.setLenient(false);
        }
        String defaultDateFormatStr = fTPClientConfig.getDefaultDateFormatStr();
        if (defaultDateFormatStr == null) {
            throw new IllegalArgumentException("defaultFormatString cannot be null");
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(defaultDateFormatStr, lookupDateFormatSymbols);
        this.defaultDateFormat = simpleDateFormat2;
        simpleDateFormat2.setLenient(false);
        setServerTimeZone(fTPClientConfig.getServerTimeZoneId());
        this.lenientFutureDates = fTPClientConfig.isLenientFutureDates();
    }

    boolean isLenientFutureDates() {
        return this.lenientFutureDates;
    }

    void setLenientFutureDates(boolean z) {
        this.lenientFutureDates = z;
    }
}
