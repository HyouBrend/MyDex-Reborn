package com.j256.ormlite.misc;

import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;

/* loaded from: classes2.dex */
public class VersionUtils {
    private static final String CORE_VERSION = "VERSION__4.45-SNAPSHOT__";
    private static String coreVersion = "VERSION__4.45-SNAPSHOT__";
    private static Logger logger = null;
    private static boolean thrownOnErrors = false;

    private VersionUtils() {
    }

    public static final void checkCoreVersusJdbcVersions(String str) {
        logVersionWarnings("core", coreVersion, "jdbc", str);
    }

    public static final void checkCoreVersusAndroidVersions(String str) {
        logVersionWarnings("core", coreVersion, "android", str);
    }

    public static String getCoreVersion() {
        return coreVersion;
    }

    static void setThrownOnErrors(boolean z) {
        thrownOnErrors = z;
    }

    private static void logVersionWarnings(String str, String str2, String str3, String str4) {
        if (str2 == null) {
            if (str4 != null) {
                warning(null, "Unknown version", " for {}, version for {} is '{}'", new Object[]{str, str3, str4});
            }
        } else if (str4 == null) {
            warning(null, "Unknown version", " for {}, version for {} is '{}'", new Object[]{str3, str, str2});
        } else {
            if (str2.equals(str4)) {
                return;
            }
            warning(null, "Mismatched versions", ": {} is '{}', while {} is '{}'", new Object[]{str, str2, str3, str4});
        }
    }

    private static void warning(Throwable th, String str, String str2, Object[] objArr) {
        getLogger().warn(th, str + str2, objArr);
        if (thrownOnErrors) {
            throw new IllegalStateException("See error log for details:" + str);
        }
    }

    private static Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger((Class<?>) VersionUtils.class);
        }
        return logger;
    }
}
