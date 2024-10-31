package org.apache.commons.net;

import java.io.PrintWriter;

/* loaded from: classes2.dex */
public class PrintCommandListener implements ProtocolCommandListener {
    private final PrintWriter __writer;

    public PrintCommandListener(PrintWriter printWriter) {
        this.__writer = printWriter;
    }

    @Override // org.apache.commons.net.ProtocolCommandListener
    public void protocolCommandSent(ProtocolCommandEvent protocolCommandEvent) {
        this.__writer.print(protocolCommandEvent.getMessage());
        this.__writer.flush();
    }

    @Override // org.apache.commons.net.ProtocolCommandListener
    public void protocolReplyReceived(ProtocolCommandEvent protocolCommandEvent) {
        this.__writer.print(protocolCommandEvent.getMessage());
        this.__writer.flush();
    }
}
