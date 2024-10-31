package jcifs.dcerpc.ndr;

import java.io.IOException;

/* loaded from: classes2.dex */
public class NdrException extends IOException {
    public static final String INVALID_CONFORMANCE = "invalid array conformance";
    public static final String NO_NULL_REF = "ref pointer cannot be null";

    public NdrException(String str) {
        super(str);
    }
}
