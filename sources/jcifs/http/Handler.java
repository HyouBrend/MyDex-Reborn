package jcifs.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/* loaded from: classes2.dex */
public class Handler extends URLStreamHandler {
    public static final int DEFAULT_HTTP_PORT = 80;
    private static final String HANDLER_PKGS_PROPERTY = "java.protocol.handler.pkgs";
    private static URLStreamHandlerFactory factory;
    private static final Map PROTOCOL_HANDLERS = new HashMap();
    private static final String[] JVM_VENDOR_DEFAULT_PKGS = {"sun.net.www.protocol"};

    @Override // java.net.URLStreamHandler
    protected int getDefaultPort() {
        return 80;
    }

    public static void setURLStreamHandlerFactory(URLStreamHandlerFactory uRLStreamHandlerFactory) {
        Map map = PROTOCOL_HANDLERS;
        synchronized (map) {
            if (factory != null) {
                throw new IllegalStateException("URLStreamHandlerFactory already set.");
            }
            map.clear();
            factory = uRLStreamHandlerFactory;
        }
    }

    @Override // java.net.URLStreamHandler
    protected URLConnection openConnection(URL url) throws IOException {
        return new NtlmHttpURLConnection((HttpURLConnection) new URL(url, url.toExternalForm(), getDefaultStreamHandler(url.getProtocol())).openConnection());
    }

    private static URLStreamHandler getDefaultStreamHandler(String str) throws IOException {
        Class<?> cls;
        Class<?> cls2;
        Map map = PROTOCOL_HANDLERS;
        synchronized (map) {
            URLStreamHandler uRLStreamHandler = (URLStreamHandler) map.get(str);
            if (uRLStreamHandler != null) {
                return uRLStreamHandler;
            }
            URLStreamHandlerFactory uRLStreamHandlerFactory = factory;
            if (uRLStreamHandlerFactory != null) {
                uRLStreamHandler = uRLStreamHandlerFactory.createURLStreamHandler(str);
            }
            if (uRLStreamHandler == null) {
                StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty(HANDLER_PKGS_PROPERTY), "|");
                while (true) {
                    if (!stringTokenizer.hasMoreTokens()) {
                        break;
                    }
                    String trim = stringTokenizer.nextToken().trim();
                    if (!trim.equals("jcifs")) {
                        String str2 = trim + "." + str + ".Handler";
                        try {
                            cls2 = Class.forName(str2);
                        } catch (Exception unused) {
                            cls2 = null;
                        }
                        if (cls2 == null) {
                            try {
                                cls2 = ClassLoader.getSystemClassLoader().loadClass(str2);
                            } catch (Exception unused2) {
                                continue;
                            }
                        }
                        uRLStreamHandler = (URLStreamHandler) cls2.newInstance();
                        break;
                    }
                }
            }
            if (uRLStreamHandler == null) {
                int i = 0;
                while (true) {
                    String[] strArr = JVM_VENDOR_DEFAULT_PKGS;
                    if (i >= strArr.length) {
                        break;
                    }
                    String str3 = strArr[i] + "." + str + ".Handler";
                    try {
                        cls = Class.forName(str3);
                    } catch (Exception unused3) {
                        cls = null;
                    }
                    if (cls == null) {
                        try {
                            cls = ClassLoader.getSystemClassLoader().loadClass(str3);
                        } catch (Exception unused4) {
                        }
                    }
                    uRLStreamHandler = (URLStreamHandler) cls.newInstance();
                    if (uRLStreamHandler != null) {
                        break;
                    }
                    i++;
                }
            }
            if (uRLStreamHandler == null) {
                throw new IOException("Unable to find default handler for protocol: " + str);
            }
            PROTOCOL_HANDLERS.put(str, uRLStreamHandler);
            return uRLStreamHandler;
        }
    }
}
