package jcifs.smb;

import java.util.Map;

/* loaded from: classes2.dex */
public class DfsReferral extends SmbException {
    public long expiration;
    public String link;
    Map map;
    public String path;
    public int pathConsumed;
    public boolean resolveHashes;
    public String server;
    public String share;
    public long ttl;
    String key = null;
    DfsReferral next = this;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void append(DfsReferral dfsReferral) {
        dfsReferral.next = this.next;
        this.next = dfsReferral;
    }

    @Override // jcifs.smb.SmbException, java.lang.Throwable
    public String toString() {
        return "DfsReferral[pathConsumed=" + this.pathConsumed + ",server=" + this.server + ",share=" + this.share + ",link=" + this.link + ",path=" + this.path + ",ttl=" + this.ttl + ",expiration=" + this.expiration + ",resolveHashes=" + this.resolveHashes + "]";
    }
}
