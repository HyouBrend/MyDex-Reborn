package jcifs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.StringTokenizer;
import jcifs.util.LogStream;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public class Config {
    public static String DEFAULT_OEM_ENCODING;
    public static int socketCount;
    private static Properties prp = new Properties();
    private static LogStream log = LogStream.getInstance();

    static {
        DEFAULT_OEM_ENCODING = InternalZipConstants.CHARSET_CP850;
        try {
            String property = System.getProperty("jcifs.properties");
            FileInputStream fileInputStream = (property == null || property.length() <= 1) ? null : new FileInputStream(property);
            load(fileInputStream);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (IOException e) {
            if (LogStream.level > 0) {
                e.printStackTrace(log);
            }
        }
        int i = getInt("jcifs.util.loglevel", -1);
        if (i != -1) {
            LogStream.setLevel(i);
        }
        try {
            "".getBytes(DEFAULT_OEM_ENCODING);
        } catch (UnsupportedEncodingException unused) {
            if (LogStream.level >= 2) {
                log.println("WARNING: The default OEM encoding " + DEFAULT_OEM_ENCODING + " does not appear to be supported by this JRE. The default encoding will be US-ASCII.");
            }
            DEFAULT_OEM_ENCODING = "US-ASCII";
        }
        if (LogStream.level >= 4) {
            try {
                prp.store(log, "JCIFS PROPERTIES");
            } catch (IOException unused2) {
            }
        }
    }

    public static void registerSmbURLHandler() {
        String property = System.getProperty("java.version");
        if (property.startsWith("1.1.") || property.startsWith("1.2.")) {
            throw new RuntimeException("jcifs-0.7.0b4+ requires Java 1.3 or above. You are running " + property);
        }
        String property2 = System.getProperty("java.protocol.handler.pkgs");
        if (property2 == null) {
            System.setProperty("java.protocol.handler.pkgs", "jcifs");
        } else if (property2.indexOf("jcifs") == -1) {
            System.setProperty("java.protocol.handler.pkgs", property2 + "|jcifs");
        }
    }

    Config() {
    }

    public static void setProperties(Properties properties) {
        Properties properties2 = new Properties(properties);
        prp = properties2;
        try {
            properties2.putAll(System.getProperties());
        } catch (SecurityException unused) {
            if (LogStream.level > 1) {
                log.println("SecurityException: jcifs will ignore System properties");
            }
        }
    }

    public static void load(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            prp.load(inputStream);
        }
        try {
            prp.putAll(System.getProperties());
        } catch (SecurityException unused) {
            if (LogStream.level > 1) {
                log.println("SecurityException: jcifs will ignore System properties");
            }
        }
    }

    public static void store(OutputStream outputStream, String str) throws IOException {
        prp.store(outputStream, str);
    }

    public static void list(PrintStream printStream) throws IOException {
        prp.list(printStream);
    }

    public static Object setProperty(String str, String str2) {
        return prp.setProperty(str, str2);
    }

    public static Object get(String str) {
        return prp.get(str);
    }

    public static String getProperty(String str, String str2) {
        return prp.getProperty(str, str2);
    }

    public static String getProperty(String str) {
        return prp.getProperty(str);
    }

    public static int getInt(String str, int i) {
        String property = prp.getProperty(str);
        if (property == null) {
            return i;
        }
        try {
            return Integer.parseInt(property);
        } catch (NumberFormatException e) {
            if (LogStream.level <= 0) {
                return i;
            }
            e.printStackTrace(log);
            return i;
        }
    }

    public static int getInt(String str) {
        String property = prp.getProperty(str);
        if (property != null) {
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
                if (LogStream.level > 0) {
                    e.printStackTrace(log);
                }
            }
        }
        return -1;
    }

    public static long getLong(String str, long j) {
        String property = prp.getProperty(str);
        if (property == null) {
            return j;
        }
        try {
            return Long.parseLong(property);
        } catch (NumberFormatException e) {
            if (LogStream.level <= 0) {
                return j;
            }
            e.printStackTrace(log);
            return j;
        }
    }

    public static InetAddress getInetAddress(String str, InetAddress inetAddress) {
        String property = prp.getProperty(str);
        if (property == null) {
            return inetAddress;
        }
        try {
            return InetAddress.getByName(property);
        } catch (UnknownHostException e) {
            if (LogStream.level <= 0) {
                return inetAddress;
            }
            log.println(property);
            e.printStackTrace(log);
            return inetAddress;
        }
    }

    public static InetAddress getLocalHost() {
        String property = prp.getProperty("jcifs.smb.client.laddr");
        if (property == null) {
            return null;
        }
        try {
            return InetAddress.getByName(property);
        } catch (UnknownHostException e) {
            if (LogStream.level <= 0) {
                return null;
            }
            log.println("Ignoring jcifs.smb.client.laddr address: " + property);
            e.printStackTrace(log);
            return null;
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        String property = getProperty(str);
        return property != null ? property.toLowerCase().equals("true") : z;
    }

    public static InetAddress[] getInetAddressArray(String str, String str2, InetAddress[] inetAddressArr) {
        String property = getProperty(str);
        if (property == null) {
            return inetAddressArr;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(property, str2);
        int countTokens = stringTokenizer.countTokens();
        InetAddress[] inetAddressArr2 = new InetAddress[countTokens];
        for (int i = 0; i < countTokens; i++) {
            String nextToken = stringTokenizer.nextToken();
            try {
                inetAddressArr2[i] = InetAddress.getByName(nextToken);
            } catch (UnknownHostException e) {
                if (LogStream.level > 0) {
                    log.println(nextToken);
                    e.printStackTrace(log);
                }
                return inetAddressArr;
            }
        }
        return inetAddressArr2;
    }
}
