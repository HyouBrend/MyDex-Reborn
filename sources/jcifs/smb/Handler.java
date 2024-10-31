package jcifs.smb;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/* loaded from: classes2.dex */
public class Handler extends URLStreamHandler {
    static final URLStreamHandler SMB_HANDLER = new Handler();

    @Override // java.net.URLStreamHandler
    protected int getDefaultPort() {
        return SmbConstants.DEFAULT_PORT;
    }

    @Override // java.net.URLStreamHandler
    public URLConnection openConnection(URL url) throws IOException {
        return new SmbFile(url);
    }

    @Override // java.net.URLStreamHandler
    protected void parseURL(URL url, String str, int i, int i2) {
        String host = url.getHost();
        if (str.equals("smb://")) {
            i2 += 2;
            str = "smb:////";
        } else if (!str.startsWith("smb://") && host != null && host.length() == 0) {
            str = "//" + str;
            i2 += 2;
        }
        super.parseURL(url, str, i, i2);
        String path = url.getPath();
        String ref = url.getRef();
        if (ref != null) {
            path = path + '#' + ref;
        }
        String str2 = path;
        int port = url.getPort();
        if (port == -1) {
            port = getDefaultPort();
        }
        setURL(url, "smb", url.getHost(), port, url.getAuthority(), url.getUserInfo(), str2, url.getQuery(), null);
    }
}
