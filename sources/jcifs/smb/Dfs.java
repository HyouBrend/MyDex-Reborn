package jcifs.smb;

import androidx.work.WorkRequest;
import java.io.IOException;
import java.util.HashMap;
import jcifs.Config;
import jcifs.UniAddress;
import jcifs.util.LogStream;

/* loaded from: classes2.dex */
public class Dfs {
    protected CacheEntry _domains = null;
    protected CacheEntry referrals = null;
    static LogStream log = LogStream.getInstance();
    static final boolean strictView = Config.getBoolean("jcifs.smb.client.dfs.strictView", false);
    static final long TTL = Config.getLong("jcifs.smb.client.dfs.ttl", 300);
    static final boolean DISABLED = Config.getBoolean("jcifs.smb.client.dfs.disabled", false);
    protected static CacheEntry FALSE_ENTRY = new CacheEntry(0);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class CacheEntry {
        long expiration;
        HashMap map;

        CacheEntry(long j) {
            this.expiration = System.currentTimeMillis() + ((j == 0 ? Dfs.TTL : j) * 1000);
            this.map = new HashMap();
        }
    }

    public HashMap getTrustedDomains(NtlmPasswordAuthentication ntlmPasswordAuthentication) throws SmbAuthException {
        if (!DISABLED && ntlmPasswordAuthentication.domain != "?") {
            if (this._domains != null && System.currentTimeMillis() > this._domains.expiration) {
                this._domains = null;
            }
            CacheEntry cacheEntry = this._domains;
            if (cacheEntry != null) {
                return cacheEntry.map;
            }
            try {
                SmbTransport smbTransport = SmbTransport.getSmbTransport(UniAddress.getByName(ntlmPasswordAuthentication.domain, true), 0);
                CacheEntry cacheEntry2 = new CacheEntry(TTL * 10);
                DfsReferral dfsReferrals = smbTransport.getDfsReferrals(ntlmPasswordAuthentication, "", 0);
                if (dfsReferrals != null) {
                    DfsReferral dfsReferral = dfsReferrals;
                    do {
                        cacheEntry2.map.put(dfsReferral.server.toLowerCase(), new HashMap());
                        dfsReferral = dfsReferral.next;
                    } while (dfsReferral != dfsReferrals);
                    this._domains = cacheEntry2;
                    return cacheEntry2.map;
                }
            } catch (IOException e) {
                if (LogStream.level >= 3) {
                    e.printStackTrace(log);
                }
                if (strictView && (e instanceof SmbAuthException)) {
                    throw ((SmbAuthException) e);
                }
            }
        }
        return null;
    }

    public boolean isTrustedDomain(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws SmbAuthException {
        HashMap trustedDomains = getTrustedDomains(ntlmPasswordAuthentication);
        return (trustedDomains == null || trustedDomains.get(str.toLowerCase()) == null) ? false : true;
    }

    public SmbTransport getDc(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws SmbAuthException {
        if (DISABLED) {
            return null;
        }
        try {
            DfsReferral dfsReferrals = SmbTransport.getSmbTransport(UniAddress.getByName(str, true), 0).getDfsReferrals(ntlmPasswordAuthentication, "\\" + str, 1);
            if (dfsReferrals != null) {
                DfsReferral dfsReferral = dfsReferrals;
                do {
                    try {
                        return SmbTransport.getSmbTransport(UniAddress.getByName(dfsReferral.server), 0);
                    } catch (IOException e) {
                        dfsReferral = dfsReferral.next;
                    }
                } while (dfsReferral != dfsReferrals);
                throw e;
            }
        } catch (IOException e2) {
            if (LogStream.level >= 3) {
                e2.printStackTrace(log);
            }
            if (strictView && (e2 instanceof SmbAuthException)) {
                throw ((SmbAuthException) e2);
            }
        }
        return null;
    }

    public DfsReferral getReferral(SmbTransport smbTransport, String str, String str2, String str3, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws SmbAuthException {
        DfsReferral dfsReferrals;
        if (DISABLED) {
            return null;
        }
        try {
            String str4 = "\\" + str + "\\" + str2;
            if (str3 != null) {
                str4 = str4 + str3;
            }
            dfsReferrals = smbTransport.getDfsReferrals(ntlmPasswordAuthentication, str4, 0);
        } catch (IOException e) {
            if (LogStream.level >= 4) {
                e.printStackTrace(log);
            }
            if (strictView && (e instanceof SmbAuthException)) {
                throw ((SmbAuthException) e);
            }
        }
        if (dfsReferrals != null) {
            return dfsReferrals;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0114 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0127 A[Catch: all -> 0x01b3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x0010, B:9:0x001c, B:11:0x0025, B:13:0x0032, B:15:0x003e, B:17:0x0044, B:19:0x004c, B:25:0x0063, B:27:0x0069, B:29:0x007c, B:30:0x0084, B:33:0x008d, B:35:0x0091, B:36:0x0098, B:38:0x00bb, B:40:0x00c7, B:42:0x00cd, B:45:0x00d7, B:51:0x00ee, B:53:0x00f4, B:56:0x0116, B:58:0x011a, B:60:0x0120, B:61:0x0123, B:63:0x0127, B:64:0x0130, B:66:0x0151, B:67:0x0160, B:68:0x0170, B:70:0x0176, B:72:0x0187, B:75:0x01a3, B:79:0x018c, B:81:0x0192, B:83:0x0198, B:95:0x00a7, B:96:0x00ad), top: B:3:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0151 A[Catch: all -> 0x01b3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x0010, B:9:0x001c, B:11:0x0025, B:13:0x0032, B:15:0x003e, B:17:0x0044, B:19:0x004c, B:25:0x0063, B:27:0x0069, B:29:0x007c, B:30:0x0084, B:33:0x008d, B:35:0x0091, B:36:0x0098, B:38:0x00bb, B:40:0x00c7, B:42:0x00cd, B:45:0x00d7, B:51:0x00ee, B:53:0x00f4, B:56:0x0116, B:58:0x011a, B:60:0x0120, B:61:0x0123, B:63:0x0127, B:64:0x0130, B:66:0x0151, B:67:0x0160, B:68:0x0170, B:70:0x0176, B:72:0x0187, B:75:0x01a3, B:79:0x018c, B:81:0x0192, B:83:0x0198, B:95:0x00a7, B:96:0x00ad), top: B:3:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0176 A[Catch: all -> 0x01b3, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x0010, B:9:0x001c, B:11:0x0025, B:13:0x0032, B:15:0x003e, B:17:0x0044, B:19:0x004c, B:25:0x0063, B:27:0x0069, B:29:0x007c, B:30:0x0084, B:33:0x008d, B:35:0x0091, B:36:0x0098, B:38:0x00bb, B:40:0x00c7, B:42:0x00cd, B:45:0x00d7, B:51:0x00ee, B:53:0x00f4, B:56:0x0116, B:58:0x011a, B:60:0x0120, B:61:0x0123, B:63:0x0127, B:64:0x0130, B:66:0x0151, B:67:0x0160, B:68:0x0170, B:70:0x0176, B:72:0x0187, B:75:0x01a3, B:79:0x018c, B:81:0x0192, B:83:0x0198, B:95:0x00a7, B:96:0x00ad), top: B:3:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized jcifs.smb.DfsReferral resolve(java.lang.String r19, java.lang.String r20, java.lang.String r21, jcifs.smb.NtlmPasswordAuthentication r22) throws jcifs.smb.SmbAuthException {
        /*
            Method dump skipped, instructions count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jcifs.smb.Dfs.resolve(java.lang.String, java.lang.String, java.lang.String, jcifs.smb.NtlmPasswordAuthentication):jcifs.smb.DfsReferral");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void insert(String str, DfsReferral dfsReferral) {
        if (DISABLED) {
            return;
        }
        int indexOf = str.indexOf(92, 1);
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(92, i);
        String substring = str.substring(1, indexOf);
        String substring2 = str.substring(i, indexOf2);
        String lowerCase = str.substring(0, dfsReferral.pathConsumed).toLowerCase();
        int length = lowerCase.length();
        while (length > 1 && lowerCase.charAt(length - 1) == '\\') {
            length--;
        }
        if (length < lowerCase.length()) {
            lowerCase = lowerCase.substring(0, length);
        }
        dfsReferral.pathConsumed -= ((substring.length() + 1) + 1) + substring2.length();
        if (this.referrals != null && System.currentTimeMillis() + WorkRequest.MIN_BACKOFF_MILLIS > this.referrals.expiration) {
            this.referrals = null;
        }
        if (this.referrals == null) {
            this.referrals = new CacheEntry(0L);
        }
        this.referrals.map.put(lowerCase, dfsReferral);
    }
}
