package org.apache.commons.net.ntp;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public class TimeStamp implements Serializable, Comparable<TimeStamp> {
    public static final String NTP_DATE_FORMAT = "EEE, MMM dd yyyy HH:mm:ss.SSS";
    protected static final long msb0baseTime = 2085978496000L;
    protected static final long msb1baseTime = -2208988800000L;
    private static final long serialVersionUID = 8139806907588338737L;
    private static SoftReference<DateFormat> simpleFormatter;
    private static SoftReference<DateFormat> utcFormatter;
    private final long ntpTime;

    public TimeStamp(long j) {
        this.ntpTime = j;
    }

    public TimeStamp(String str) throws NumberFormatException {
        this.ntpTime = decodeNtpHexString(str);
    }

    public TimeStamp(Date date) {
        this.ntpTime = date == null ? 0L : toNtpTime(date.getTime());
    }

    public long ntpValue() {
        return this.ntpTime;
    }

    public long getSeconds() {
        return (this.ntpTime >>> 32) & InternalZipConstants.ZIP_64_LIMIT;
    }

    public long getFraction() {
        return this.ntpTime & InternalZipConstants.ZIP_64_LIMIT;
    }

    public long getTime() {
        return getTime(this.ntpTime);
    }

    public Date getDate() {
        return new Date(getTime(this.ntpTime));
    }

    public static long getTime(long j) {
        long j2 = (j >>> 32) & InternalZipConstants.ZIP_64_LIMIT;
        return (j2 * 1000) + ((2147483648L & j2) == 0 ? msb0baseTime : msb1baseTime) + Math.round(((j & InternalZipConstants.ZIP_64_LIMIT) * 1000.0d) / 4.294967296E9d);
    }

    public static TimeStamp getNtpTime(long j) {
        return new TimeStamp(toNtpTime(j));
    }

    public static TimeStamp getCurrentTime() {
        return getNtpTime(System.currentTimeMillis());
    }

    protected static long decodeNtpHexString(String str) throws NumberFormatException {
        if (str == null) {
            throw new NumberFormatException("null");
        }
        int indexOf = str.indexOf(46);
        if (indexOf == -1) {
            if (str.length() == 0) {
                return 0L;
            }
            return Long.parseLong(str, 16) << 32;
        }
        return (Long.parseLong(str.substring(0, indexOf), 16) << 32) | Long.parseLong(str.substring(indexOf + 1), 16);
    }

    public static TimeStamp parseNtpString(String str) throws NumberFormatException {
        return new TimeStamp(decodeNtpHexString(str));
    }

    protected static long toNtpTime(long j) {
        long j2 = msb0baseTime;
        boolean z = j < msb0baseTime;
        if (z) {
            j2 = msb1baseTime;
        }
        long j3 = j - j2;
        long j4 = j3 / 1000;
        long j5 = ((j3 % 1000) * 4294967296L) / 1000;
        if (z) {
            j4 |= 2147483648L;
        }
        return j5 | (j4 << 32);
    }

    public int hashCode() {
        long j = this.ntpTime;
        return (int) (j ^ (j >>> 32));
    }

    public boolean equals(Object obj) {
        return (obj instanceof TimeStamp) && this.ntpTime == ((TimeStamp) obj).ntpValue();
    }

    public String toString() {
        return toString(this.ntpTime);
    }

    private static void appendHexString(StringBuilder sb, long j) {
        String hexString = Long.toHexString(j);
        for (int length = hexString.length(); length < 8; length++) {
            sb.append('0');
        }
        sb.append(hexString);
    }

    public static String toString(long j) {
        StringBuilder sb = new StringBuilder();
        appendHexString(sb, (j >>> 32) & InternalZipConstants.ZIP_64_LIMIT);
        sb.append('.');
        appendHexString(sb, j & InternalZipConstants.ZIP_64_LIMIT);
        return sb.toString();
    }

    public String toDateString() {
        String format;
        SoftReference<DateFormat> softReference = simpleFormatter;
        DateFormat dateFormat = softReference != null ? softReference.get() : null;
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(NTP_DATE_FORMAT, Locale.US);
            dateFormat.setTimeZone(TimeZone.getDefault());
            simpleFormatter = new SoftReference<>(dateFormat);
        }
        Date date = getDate();
        synchronized (dateFormat) {
            format = dateFormat.format(date);
        }
        return format;
    }

    public String toUTCString() {
        String format;
        SoftReference<DateFormat> softReference = utcFormatter;
        DateFormat dateFormat = softReference != null ? softReference.get() : null;
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            utcFormatter = new SoftReference<>(dateFormat);
        }
        Date date = getDate();
        synchronized (dateFormat) {
            format = dateFormat.format(date);
        }
        return format;
    }

    @Override // java.lang.Comparable
    public int compareTo(TimeStamp timeStamp) {
        long j = this.ntpTime;
        long j2 = timeStamp.ntpTime;
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }
}
