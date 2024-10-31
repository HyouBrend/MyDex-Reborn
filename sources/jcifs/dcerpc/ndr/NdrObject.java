package jcifs.dcerpc.ndr;

/* loaded from: classes2.dex */
public abstract class NdrObject {
    public abstract void decode(NdrBuffer ndrBuffer) throws NdrException;

    public abstract void encode(NdrBuffer ndrBuffer) throws NdrException;
}
