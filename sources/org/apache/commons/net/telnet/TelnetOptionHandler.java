package org.apache.commons.net.telnet;

/* loaded from: classes2.dex */
public abstract class TelnetOptionHandler {
    private boolean acceptLocal;
    private boolean acceptRemote;
    private boolean initialLocal;
    private boolean initialRemote;
    private int optionCode;
    private boolean doFlag = false;
    private boolean willFlag = false;

    public abstract int[] answerSubnegotiation(int[] iArr, int i);

    public abstract int[] startSubnegotiationLocal();

    public abstract int[] startSubnegotiationRemote();

    public TelnetOptionHandler(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        this.optionCode = i;
        this.initialLocal = z;
        this.initialRemote = z2;
        this.acceptLocal = z3;
        this.acceptRemote = z4;
    }

    public int getOptionCode() {
        return this.optionCode;
    }

    public boolean getAcceptLocal() {
        return this.acceptLocal;
    }

    public boolean getAcceptRemote() {
        return this.acceptRemote;
    }

    public void setAcceptLocal(boolean z) {
        this.acceptLocal = z;
    }

    public void setAcceptRemote(boolean z) {
        this.acceptRemote = z;
    }

    public boolean getInitLocal() {
        return this.initialLocal;
    }

    public boolean getInitRemote() {
        return this.initialRemote;
    }

    public void setInitLocal(boolean z) {
        this.initialLocal = z;
    }

    public void setInitRemote(boolean z) {
        this.initialRemote = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getWill() {
        return this.willFlag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWill(boolean z) {
        this.willFlag = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getDo() {
        return this.doFlag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDo(boolean z) {
        this.doFlag = z;
    }
}
