package jcifs.smb;

/* loaded from: classes2.dex */
public interface SmbFileFilter {
    boolean accept(SmbFile smbFile) throws SmbException;
}
