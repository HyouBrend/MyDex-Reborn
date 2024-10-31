package jcifs.smb;

/* loaded from: classes2.dex */
public interface FileEntry {
    long createTime();

    int getAttributes();

    String getName();

    int getType();

    long lastModified();

    long length();
}
