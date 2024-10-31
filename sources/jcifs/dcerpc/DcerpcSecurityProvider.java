package jcifs.dcerpc;

import jcifs.dcerpc.ndr.NdrBuffer;

/* loaded from: classes2.dex */
public interface DcerpcSecurityProvider {
    void unwrap(NdrBuffer ndrBuffer) throws DcerpcException;

    void wrap(NdrBuffer ndrBuffer) throws DcerpcException;
}
