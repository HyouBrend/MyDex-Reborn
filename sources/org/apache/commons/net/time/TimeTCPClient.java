package org.apache.commons.net.time;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.commons.net.SocketClient;

/* loaded from: classes2.dex */
public final class TimeTCPClient extends SocketClient {
    public static final int DEFAULT_PORT = 37;
    public static final long SECONDS_1900_TO_1970 = 2208988800L;

    public TimeTCPClient() {
        setDefaultPort(37);
    }

    public long getTime() throws IOException {
        return new DataInputStream(this._input_).readInt() & InternalZipConstants.ZIP_64_LIMIT;
    }

    public Date getDate() throws IOException {
        return new Date((getTime() - 2208988800L) * 1000);
    }
}
