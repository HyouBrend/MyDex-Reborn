package com.myor.mydex.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import androidx.room.RoomMasterTable;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.myor.mydex.R;
import com.myor.mydex.database.DatabaseHelper;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public class UtilsTools {
    static final String KEY_REQUESTING_LOCATION_UPDATES = "requesting_location_updates";
    DecimalFormatSymbols dfs;
    boolean isActive = false;

    static String getLocationTitle(Context context) {
        return "";
    }

    public int countTotalQty(int i, int i2, int i3, int i4, int i5) {
        return (i3 * i5) + (i2 * i4) + i;
    }

    public int getTotalQty(int i, int i2, int i3, int i4, int i5) {
        return (i3 * i5) + (i2 * i4) + i;
    }

    public void gpsOFF(Context context) {
    }

    public void gpsON(Context context) {
    }

    public static String getSysDate() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public String getSysDate2() {
        return new SimpleDateFormat("E, dd MMMM yyyy HH:mm:ss").format(new Date());
    }

    public static int calculateNoOfColumns(Context context, float f) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((displayMetrics.widthPixels / displayMetrics.density) / f) + 0.5d);
    }

    public static String getTitleName(String str) {
        String str2 = str.equalsIgnoreCase("1") ? "Supervisor" : "";
        if (str.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
            str2 = "RSM";
        }
        if (str.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
            str2 = "GRSM";
        }
        if (str.equalsIgnoreCase("4")) {
            str2 = "NSM";
        }
        if (str.equalsIgnoreCase("5")) {
            str2 = "Sales Director";
        }
        if (str.equalsIgnoreCase("6")) {
            str2 = "Sales & Marketing Director";
        }
        return str.equalsIgnoreCase("7") ? "BOD" : str2;
    }

    public String getCurrentDateSample1() {
        return new SimpleDateFormat("EEE, MMM dd, yyyy HH:mm:ss").format(new Date());
    }

    public String getCurrentDateSample2() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public String getCurrentDateSample11() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getCurrentDateSample10() {
        return new SimpleDateFormat("yyyy-MM-dd'T'00:00:00").format(new Date());
    }

    public String getCurrentDateSample12() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public Toast toast(Context context, String str) {
        Toast toast = new Toast(context);
        Toast.makeText(context, str, 1).show();
        return toast;
    }

    public String getCurrentDateSample3() {
        return new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    }

    public String getCurrentDateSample4() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public String getCurrentDateSample5() {
        return new SimpleDateFormat("ddMMyyyy").format(new Date());
    }

    public String getCurrentDateSample6() {
        return new SimpleDateFormat("yyMMdd").format(new Date());
    }

    public String getCurrentDateSample7() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public String getCurrentDateSample8() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public String getCurrentDateSample9() {
        return new SimpleDateFormat("EEE, MMM dd, yyyy").format(new Date());
    }

    public String getCurrentDateTwoDaysAgo(Context context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -7);
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getCurrentDay() {
        return new SimpleDateFormat("EEEE").format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public String getCurrentTimeSample2() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    public String getCurrentDateExpired(int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, i);
        return simpleDateFormat.format(calendar.getTime());
    }

    public String formatTanggal(String str) {
        String[] strArr = {str.split(InternalZipConstants.ZIP_FILE_SEPARATOR)[0], str.split(InternalZipConstants.ZIP_FILE_SEPARATOR)[1], str.split(InternalZipConstants.ZIP_FILE_SEPARATOR)[2]};
        if (strArr[0].length() == 1) {
            strArr[0] = "0" + strArr[0];
        }
        if (str.split(InternalZipConstants.ZIP_FILE_SEPARATOR)[1].length() == 1) {
            strArr[1] = "0" + strArr[1];
        }
        return strArr[0] + InternalZipConstants.ZIP_FILE_SEPARATOR + strArr[1] + InternalZipConstants.ZIP_FILE_SEPARATOR + strArr[2];
    }

    public String getDateFormat(String str, String str2, String str3, Context context) throws ParseException {
        Date parse;
        if (str == null) {
            new DateFormat();
            parse = DateFormat.getDateFormat(context).parse(str2);
        } else {
            parse = new SimpleDateFormat(str).parse(str2);
        }
        if (str3 == null) {
            new DateFormat();
            return DateFormat.getDateFormat(context).format(parse);
        }
        return new SimpleDateFormat(str3).format(parse);
    }

    public String setDayFirstDate(String str) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(str));
    }

    public String setMonthFirstDate(String str) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(str));
    }

    public String getNextSeventhDates(String str) throws ParseException {
        Date parse = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        calendar.add(5, 7);
        return new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(calendar.getTime());
    }

    public String getNextDate(String str) throws ParseException {
        Date parse = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(str);
        String format = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(parse);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        if (format.equals("Saturday")) {
            calendar.add(5, 2);
        } else {
            calendar.add(5, 1);
        }
        return new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(calendar.getTime());
    }

    public String format2f(double d) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        this.dfs = decimalFormatSymbols;
        decimalFormatSymbols.setDecimalSeparator('.');
        this.dfs.setGroupingSeparator(',');
        return new DecimalFormat("##,##0.00", this.dfs).format(d);
    }

    public String format2fBulat(double d) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        this.dfs = decimalFormatSymbols;
        decimalFormatSymbols.setDecimalSeparator('.');
        this.dfs.setGroupingSeparator(',');
        return new DecimalFormat("##,##0", this.dfs).format(d);
    }

    public String cekEnter(String str) {
        return str.replace("\n", " ");
    }

    public String removeEnter(String str) {
        return str.replace("\n", "");
    }

    public String removePetik(String str) {
        return str.replace("'", " ");
    }

    public String format1000f(double d) {
        return NumberFormat.getNumberInstance(Locale.GERMAN).format(d);
    }

    public String get2f(double d) {
        return new DecimalFormat("##,##0.00", this.dfs).format(d);
    }

    public String get1000f(double d) {
        return NumberFormat.getNumberInstance(Locale.US).format(d);
    }

    public String changeToPoint(String str) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        String ch = Character.toString(decimalFormatSymbols.getDecimalSeparator());
        String ch2 = Character.toString(decimalFormatSymbols.getGroupingSeparator());
        if (str.contains(ch2)) {
            str = str.replace(ch2, "");
        }
        return str.contains(ch) ? str.replace(ch, ".") : str;
    }

    public String changeFormatUnit5Digit(String str) {
        if (str.length() == 1) {
            return "0000" + str;
        }
        if (str.length() == 2) {
            return "000" + str;
        }
        if (str.length() == 3) {
            return "00" + str;
        }
        if (str.length() != 4) {
            return str;
        }
        return "0" + str;
    }

    public String changeFormatUnit3Digit(String str) {
        if (str.length() == 1) {
            return "00" + str;
        }
        if (str.length() != 2) {
            return str;
        }
        return "0" + str;
    }

    public String getNumber(char c) {
        return String.valueOf(c).equals("a") ? "0" : String.valueOf(c).equals("b") ? "1" : String.valueOf(c).equals("c") ? ExifInterface.GPS_MEASUREMENT_2D : String.valueOf(c).equals("d") ? ExifInterface.GPS_MEASUREMENT_3D : String.valueOf(c).equals("e") ? "4" : String.valueOf(c).equals("f") ? "5" : String.valueOf(c).equals("g") ? "6" : String.valueOf(c).equals("h") ? "7" : String.valueOf(c).equals("i") ? "8" : String.valueOf(c).equals("j") ? "9" : String.valueOf(c).equals("k") ? "10" : String.valueOf(c).equals("l") ? "11" : String.valueOf(c).equals("m") ? "12" : String.valueOf(c).equals("n") ? "13" : String.valueOf(c).equals("o") ? "14" : String.valueOf(c).equals("p") ? "15" : String.valueOf(c).equals("q") ? "16" : String.valueOf(c).equals(InternalZipConstants.READ_MODE) ? "17" : String.valueOf(c).equals("s") ? "18" : String.valueOf(c).equals("t") ? "19" : String.valueOf(c).equals("u") ? "20" : String.valueOf(c).equals("v") ? "21" : String.valueOf(c).equals("w") ? "22" : String.valueOf(c).equals("x") ? "23" : String.valueOf(c).equals("y") ? "24" : String.valueOf(c).equals("z") ? "25" : String.valueOf(c).equals("0") ? "26" : String.valueOf(c).equals("1") ? "27" : String.valueOf(c).equals(ExifInterface.GPS_MEASUREMENT_2D) ? "28" : String.valueOf(c).equals(ExifInterface.GPS_MEASUREMENT_3D) ? "29" : String.valueOf(c).equals("4") ? "30" : String.valueOf(c).equals("5") ? "31" : String.valueOf(c).equals("6") ? "32" : String.valueOf(c).equals("7") ? "33" : String.valueOf(c).equals("8") ? "34" : String.valueOf(c).equals("9") ? "35" : String.valueOf(c).equals(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) ? "36" : String.valueOf(c).equals("B") ? "37" : String.valueOf(c).equals("C") ? "38" : String.valueOf(c).equals("D") ? "39" : String.valueOf(c).equals(ExifInterface.LONGITUDE_EAST) ? "40" : String.valueOf(c).equals("F") ? "41" : String.valueOf(c).equals("G") ? RoomMasterTable.DEFAULT_ID : String.valueOf(c).equals("H") ? "43" : String.valueOf(c).equals("I") ? "44" : String.valueOf(c).equals("J") ? "45" : String.valueOf(c).equals("K") ? "46" : String.valueOf(c).equals("L") ? "47" : String.valueOf(c).equals("M") ? "48" : String.valueOf(c).equals("N") ? "49" : String.valueOf(c).equals("O") ? "50" : String.valueOf(c).equals("P") ? "51" : String.valueOf(c).equals("Q") ? "52" : String.valueOf(c).equals("R") ? "53" : String.valueOf(c).equals(ExifInterface.LATITUDE_SOUTH) ? "54" : String.valueOf(c).equals(ExifInterface.GPS_DIRECTION_TRUE) ? "55" : String.valueOf(c).equals("U") ? "56" : String.valueOf(c).equals(ExifInterface.GPS_MEASUREMENT_INTERRUPTED) ? "57" : String.valueOf(c).equals(ExifInterface.LONGITUDE_WEST) ? "58" : String.valueOf(c).equals("X") ? "59" : String.valueOf(c).equals("Y") ? "60" : String.valueOf(c).equals("Z") ? "61" : String.valueOf(c);
    }

    public String getString(int i) {
        switch (i) {
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            case 7:
                return "h";
            case 8:
                return "i";
            case 9:
                return "j";
            case 10:
                return "k";
            case 11:
                return "l";
            case 12:
                return "m";
            case 13:
                return "n";
            case 14:
                return "o";
            case 15:
                return "p";
            case 16:
                return "q";
            case 17:
                return InternalZipConstants.READ_MODE;
            case 18:
                return "s";
            case 19:
                return "t";
            case 20:
                return "u";
            case 21:
                return "v";
            case 22:
                return "w";
            case 23:
                return "x";
            case 24:
                return "y";
            case 25:
                return "z";
            case 26:
                return "0";
            case 27:
                return "1";
            case 28:
                return ExifInterface.GPS_MEASUREMENT_2D;
            case 29:
                return ExifInterface.GPS_MEASUREMENT_3D;
            case 30:
                return "4";
            case 31:
                return "5";
            case 32:
                return "6";
            case 33:
                return "7";
            case 34:
                return "8";
            case 35:
                return "9";
            case 36:
                return ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
            case 37:
                return "B";
            case 38:
                return "C";
            case 39:
                return "D";
            case 40:
                return ExifInterface.LONGITUDE_EAST;
            case 41:
                return "F";
            case 42:
                return "G";
            case 43:
                return "H";
            case 44:
                return "I";
            case 45:
                return "J";
            case 46:
                return "K";
            case 47:
                return "L";
            case 48:
                return "M";
            case 49:
                return "N";
            case 50:
                return "O";
            case 51:
                return "P";
            case 52:
                return "Q";
            case 53:
                return "R";
            case 54:
                return ExifInterface.LATITUDE_SOUTH;
            case 55:
                return ExifInterface.GPS_DIRECTION_TRUE;
            case 56:
                return "U";
            case 57:
                return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
            case 58:
                return ExifInterface.LONGITUDE_WEST;
            case 59:
                return "X";
            case 60:
                return "Y";
            case 61:
                return "Z";
            default:
                return String.valueOf(i);
        }
    }

    public String encrypt(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            try {
                sb.append(getString((Integer.valueOf(getNumber(str.charAt(i))).intValue() + Integer.valueOf(getNumber(str2.charAt(i))).intValue()) % 62));
            } catch (Exception unused) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public String decrypt(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            try {
                int intValue = (Integer.valueOf(getNumber(str.charAt(i))).intValue() - Integer.valueOf(getNumber(str2.charAt(i))).intValue()) % 62;
                if (intValue < 0) {
                    intValue += 62;
                }
                sb.append(getString(intValue));
            } catch (Exception unused) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public boolean isExpired(String str) throws ParseException {
        if (!str.equals("null")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if ((new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(str).getTime() - new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(simpleDateFormat.format(new Date())).getTime()) / 86400000 <= 0) {
                return true;
            }
        }
        return false;
    }

    public String placeText(int i, String str) {
        String str2 = "";
        for (int i2 = 0; i2 < i - str.length(); i2++) {
            str2 = str2 + " ";
        }
        return str2 + str;
    }

    static boolean requestingLocationUpdates(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    static void setRequestingLocationUpdates(Context context, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(KEY_REQUESTING_LOCATION_UPDATES, z).apply();
    }

    static String getLocationText(Location location) {
        if (location == null) {
            return "Unknown location";
        }
        return "(" + location.getLatitude() + ", " + location.getLongitude() + ")";
    }

    public boolean initCheckDate(Context context) throws Settings.SettingNotFoundException {
        return Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(context.getContentResolver(), "auto_time_zone") == 1 : Settings.System.getInt(context.getContentResolver(), "auto_time_zone", 0) == 1;
    }

    public int getQty3(int i, int i2, int i3, int i4, int i5) {
        return (int) Math.floor(getTotalQty(i, i2, i3, i4, i5) / i5);
    }

    public int getQty3(int i, int i2) {
        return (int) Math.floor(i / i2);
    }

    public int getQty2(int i, int i2, int i3, int i4, int i5) {
        return (int) Math.floor((getTotalQty(i, i2, i3, i4, i5) % i5) / i4);
    }

    public int getQty2(int i, int i2, int i3) {
        return (int) Math.floor((i % i3) / i2);
    }

    public int getQty1(int i, int i2, int i3, int i4, int i5) {
        return (getTotalQty(i, i2, i3, i4, i5) % i5) % i4;
    }

    public int getQty1(int i, int i2, int i3) {
        return (i % i3) % i2;
    }

    public String stringToDateYYYYMMDD(String str) {
        try {
            return new SimpleDateFormat("E, dd MMMM yyyy").format(new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String removeSpecialCharacter(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > '@' && str.charAt(i) <= 'z') {
                str2 = str2 + str.charAt(i);
            }
        }
        return str2;
    }

    public static String getCurrentTime2() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    public String getCurrentDateddMonyyyy() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
    }

    public String diffTwoDateddMonyyyy(String str, String str2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return String.valueOf(TimeUnit.DAYS.convert(Math.abs(simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime()), TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            e.printStackTrace();
            return "NaN";
        }
    }

    public String convertDate1(String str) {
        try {
            return new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault()).format(new SimpleDateFormat("yyyyMMdd HHmmss", Locale.getDefault()).parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void deleteFileTemp(Context context) {
        new File(DatabaseHelper.PHOTO_PATH_SDCARD).listFiles();
        Calendar.getInstance().add(6, 0);
    }

    public boolean isDeveloperOptionsEnabled(ContentResolver contentResolver) {
        return Settings.Secure.getInt(contentResolver, "development_settings_enabled", 0) != 0;
    }

    public boolean detectMockLocation(Context context) {
        if (Settings.Secure.getInt(context.getContentResolver(), "mock_location", 0) == 1) {
            return true;
        }
        for (String str : ((LocationManager) context.getSystemService("location")).getAllProviders()) {
            if ("mockgps".equalsIgnoreCase(str) || "other_suspicious_provider".equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMockLocationAndDeveloperOption(Context context) {
        if (detectMockLocation(context)) {
            this.isActive = true;
        }
        if (this.isActive) {
            return false;
        }
        if (Settings.Secure.getString(context.getContentResolver(), "mock_location").equals("0")) {
            if (!isDeveloperOptionsEnabled(context.getContentResolver())) {
                return false;
            }
            this.isActive = true;
            return false;
        }
        this.isActive = true;
        return false;
    }

    public void dialogDisableFakeGps(Context context, final Context context2) {
        new MaterialAlertDialogBuilder(context).setTitle((CharSequence) context2.getString(R.string.gps_location_is_not_valid)).setMessage((CharSequence) context2.getString(R.string.application_will_be_terminate)).setPositiveButton((CharSequence) context2.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.util.UtilsTools.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.setFlags(268435456);
                context2.startActivity(intent);
            }
        }).show();
    }

    public void dialogMenuIsNotActive(Context context, Context context2) {
        new MaterialAlertDialogBuilder(context).setTitle((CharSequence) context2.getString(R.string.informasi)).setMessage((CharSequence) context2.getString(R.string.menu_yang_anda_akses_sudah_tidak_dapat_diakses_kembali)).setPositiveButton((CharSequence) context2.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.util.UtilsTools.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }
}
