package jcifs.smb;

/* loaded from: classes2.dex */
public abstract class NtlmAuthenticator {
    private static NtlmAuthenticator auth;
    private SmbAuthException sae;
    private String url;

    protected NtlmPasswordAuthentication getNtlmPasswordAuthentication() {
        return null;
    }

    private void reset() {
        this.url = null;
        this.sae = null;
    }

    public static synchronized void setDefault(NtlmAuthenticator ntlmAuthenticator) {
        synchronized (NtlmAuthenticator.class) {
            if (auth != null) {
                return;
            }
            auth = ntlmAuthenticator;
        }
    }

    protected final String getRequestingURL() {
        return this.url;
    }

    protected final SmbAuthException getRequestingException() {
        return this.sae;
    }

    public static NtlmPasswordAuthentication requestNtlmPasswordAuthentication(String str, SmbAuthException smbAuthException) {
        NtlmPasswordAuthentication ntlmPasswordAuthentication;
        NtlmAuthenticator ntlmAuthenticator = auth;
        if (ntlmAuthenticator == null) {
            return null;
        }
        synchronized (ntlmAuthenticator) {
            NtlmAuthenticator ntlmAuthenticator2 = auth;
            ntlmAuthenticator2.url = str;
            ntlmAuthenticator2.sae = smbAuthException;
            ntlmPasswordAuthentication = ntlmAuthenticator2.getNtlmPasswordAuthentication();
        }
        return ntlmPasswordAuthentication;
    }
}
