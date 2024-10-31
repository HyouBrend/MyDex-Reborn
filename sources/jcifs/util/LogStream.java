package jcifs.util;

import java.io.PrintStream;

/* loaded from: classes2.dex */
public class LogStream extends PrintStream {
    private static LogStream inst = null;
    public static int level = 1;

    public LogStream(PrintStream printStream) {
        super(printStream);
    }

    public static void setLevel(int i) {
        level = i;
    }

    public static void setInstance(PrintStream printStream) {
        inst = new LogStream(printStream);
    }

    public static LogStream getInstance() {
        if (inst == null) {
            setInstance(System.err);
        }
        return inst;
    }
}
