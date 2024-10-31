package net.lingala.zip4j.core;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.SplitOutputStream;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip64EndCentralDirLocator;
import net.lingala.zip4j.model.Zip64EndCentralDirRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: classes2.dex */
public class HeaderWriter {
    private final int ZIP64_EXTRA_BUF = 50;

    public int writeLocalFileHeader(ZipModel zipModel, LocalFileHeader localFileHeader, OutputStream outputStream) throws ZipException {
        boolean z;
        if (localFileHeader == null) {
            throw new ZipException("input parameters are null, cannot write local file header");
        }
        try {
            ArrayList arrayList = new ArrayList();
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[8];
            byte[] bArr4 = {0, 0, 0, 0, 0, 0, 0, 0};
            Raw.writeIntLittleEndian(bArr2, 0, localFileHeader.getSignature());
            copyByteArrayToArrayList(bArr2, arrayList);
            Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader.getVersionNeededToExtract());
            copyByteArrayToArrayList(bArr, arrayList);
            copyByteArrayToArrayList(localFileHeader.getGeneralPurposeFlag(), arrayList);
            Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader.getCompressionMethod());
            copyByteArrayToArrayList(bArr, arrayList);
            Raw.writeIntLittleEndian(bArr2, 0, localFileHeader.getLastModFileTime());
            copyByteArrayToArrayList(bArr2, arrayList);
            Raw.writeIntLittleEndian(bArr2, 0, (int) localFileHeader.getCrc32());
            copyByteArrayToArrayList(bArr2, arrayList);
            if (localFileHeader.getUncompressedSize() + 50 >= InternalZipConstants.ZIP_64_LIMIT) {
                Raw.writeLongLittleEndian(bArr3, 0, InternalZipConstants.ZIP_64_LIMIT);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, arrayList);
                copyByteArrayToArrayList(bArr2, arrayList);
                zipModel.setZip64Format(true);
                localFileHeader.setWriteComprSizeInZip64ExtraRecord(true);
                z = true;
            } else {
                Raw.writeLongLittleEndian(bArr3, 0, localFileHeader.getCompressedSize());
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, arrayList);
                Raw.writeLongLittleEndian(bArr3, 0, localFileHeader.getUncompressedSize());
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, arrayList);
                localFileHeader.setWriteComprSizeInZip64ExtraRecord(false);
                z = false;
            }
            Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader.getFileNameLength());
            copyByteArrayToArrayList(bArr, arrayList);
            int i = z ? 20 : 0;
            if (localFileHeader.getAesExtraDataRecord() != null) {
                i += 11;
            }
            Raw.writeShortLittleEndian(bArr, 0, (short) i);
            copyByteArrayToArrayList(bArr, arrayList);
            if (Zip4jUtil.isStringNotNullAndNotEmpty(zipModel.getFileNameCharset())) {
                byte[] bytes = localFileHeader.getFileName().getBytes(zipModel.getFileNameCharset());
                copyByteArrayToArrayList(bytes, arrayList);
                int length = bytes.length;
            } else {
                copyByteArrayToArrayList(Zip4jUtil.convertCharset(localFileHeader.getFileName()), arrayList);
                Zip4jUtil.getEncodedStringLength(localFileHeader.getFileName());
            }
            if (z) {
                Raw.writeShortLittleEndian(bArr, 0, (short) 1);
                copyByteArrayToArrayList(bArr, arrayList);
                Raw.writeShortLittleEndian(bArr, 0, (short) 16);
                copyByteArrayToArrayList(bArr, arrayList);
                Raw.writeLongLittleEndian(bArr3, 0, localFileHeader.getUncompressedSize());
                copyByteArrayToArrayList(bArr3, arrayList);
                copyByteArrayToArrayList(bArr4, arrayList);
            }
            if (localFileHeader.getAesExtraDataRecord() != null) {
                AESExtraDataRecord aesExtraDataRecord = localFileHeader.getAesExtraDataRecord();
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getSignature());
                copyByteArrayToArrayList(bArr, arrayList);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getDataSize());
                copyByteArrayToArrayList(bArr, arrayList);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getVersionNumber());
                copyByteArrayToArrayList(bArr, arrayList);
                copyByteArrayToArrayList(aesExtraDataRecord.getVendorID().getBytes(), arrayList);
                copyByteArrayToArrayList(new byte[]{(byte) aesExtraDataRecord.getAesStrength()}, arrayList);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getCompressionMethod());
                copyByteArrayToArrayList(bArr, arrayList);
            }
            byte[] byteArrayListToByteArray = byteArrayListToByteArray(arrayList);
            outputStream.write(byteArrayListToByteArray);
            return byteArrayListToByteArray.length;
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    public int writeExtendedLocalHeader(LocalFileHeader localFileHeader, OutputStream outputStream) throws ZipException, IOException {
        if (localFileHeader == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot write extended local header");
        }
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[4];
        Raw.writeIntLittleEndian(bArr, 0, 134695760);
        copyByteArrayToArrayList(bArr, arrayList);
        Raw.writeIntLittleEndian(bArr, 0, (int) localFileHeader.getCrc32());
        copyByteArrayToArrayList(bArr, arrayList);
        long compressedSize = localFileHeader.getCompressedSize();
        if (compressedSize >= 2147483647L) {
            compressedSize = 2147483647L;
        }
        Raw.writeIntLittleEndian(bArr, 0, (int) compressedSize);
        copyByteArrayToArrayList(bArr, arrayList);
        long uncompressedSize = localFileHeader.getUncompressedSize();
        Raw.writeIntLittleEndian(bArr, 0, (int) (uncompressedSize < 2147483647L ? uncompressedSize : 2147483647L));
        copyByteArrayToArrayList(bArr, arrayList);
        byte[] byteArrayListToByteArray = byteArrayListToByteArray(arrayList);
        outputStream.write(byteArrayListToByteArray);
        return byteArrayListToByteArray.length;
    }

    public void finalizeZipFile(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot finalize zip file");
        }
        try {
            long offsetOfStartOfCentralDir = zipModel.getEndCentralDirRecord().getOffsetOfStartOfCentralDir();
            int writeCentralDirectory = writeCentralDirectory(zipModel, outputStream);
            if (zipModel.isZip64Format()) {
                if (zipModel.getZip64EndCentralDirRecord() == null) {
                    zipModel.setZip64EndCentralDirRecord(new Zip64EndCentralDirRecord());
                }
                if (zipModel.getZip64EndCentralDirLocator() == null) {
                    zipModel.setZip64EndCentralDirLocator(new Zip64EndCentralDirLocator());
                }
                zipModel.getZip64EndCentralDirLocator().setOffsetZip64EndOfCentralDirRec(writeCentralDirectory + offsetOfStartOfCentralDir);
                if (outputStream instanceof SplitOutputStream) {
                    zipModel.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(((SplitOutputStream) outputStream).getCurrSplitFileCounter());
                    zipModel.getZip64EndCentralDirLocator().setTotNumberOfDiscs(((SplitOutputStream) outputStream).getCurrSplitFileCounter() + 1);
                } else {
                    zipModel.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(0);
                    zipModel.getZip64EndCentralDirLocator().setTotNumberOfDiscs(1);
                }
                writeZip64EndOfCentralDirectoryRecord(zipModel, outputStream, writeCentralDirectory, offsetOfStartOfCentralDir);
                writeZip64EndOfCentralDirectoryLocator(zipModel, outputStream);
            }
            writeEndOfCentralDirectoryRecord(zipModel, outputStream, writeCentralDirectory, offsetOfStartOfCentralDir);
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    public void finalizeZipFileWithoutValidations(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot finalize zip file without validations");
        }
        long offsetOfStartOfCentralDir = zipModel.getEndCentralDirRecord().getOffsetOfStartOfCentralDir();
        int writeCentralDirectory = writeCentralDirectory(zipModel, outputStream);
        if (zipModel.isZip64Format()) {
            if (zipModel.getZip64EndCentralDirRecord() == null) {
                zipModel.setZip64EndCentralDirRecord(new Zip64EndCentralDirRecord());
            }
            if (zipModel.getZip64EndCentralDirLocator() == null) {
                zipModel.setZip64EndCentralDirLocator(new Zip64EndCentralDirLocator());
            }
            zipModel.getZip64EndCentralDirLocator().setOffsetZip64EndOfCentralDirRec(writeCentralDirectory + offsetOfStartOfCentralDir);
            writeZip64EndOfCentralDirectoryRecord(zipModel, outputStream, writeCentralDirectory, offsetOfStartOfCentralDir);
            writeZip64EndOfCentralDirectoryLocator(zipModel, outputStream);
        }
        writeEndOfCentralDirectoryRecord(zipModel, outputStream, writeCentralDirectory, offsetOfStartOfCentralDir);
    }

    private int writeCentralDirectory(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot write central directory");
        }
        if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null || zipModel.getCentralDirectory().getFileHeaders().size() <= 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < zipModel.getCentralDirectory().getFileHeaders().size(); i2++) {
            i += writeFileHeader(zipModel, (FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(i2), outputStream);
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00e6, code lost:
    
        r5 = 20;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00ca A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f2 A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0112 A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x012d A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0154 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01a3 A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x016c A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0178 A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0191 A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x013e A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x011a A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ea A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d2 A[Catch: Exception -> 0x01f6, TryCatch #0 {Exception -> 0x01f6, blocks: (B:5:0x0008, B:7:0x007f, B:10:0x008c, B:11:0x00b5, B:13:0x00ca, B:18:0x00ec, B:20:0x00f2, B:21:0x00f4, B:23:0x0112, B:24:0x011e, B:26:0x012d, B:27:0x0151, B:30:0x019d, B:32:0x01a3, B:33:0x01ee, B:37:0x0156, B:41:0x016c, B:42:0x016e, B:44:0x0178, B:46:0x0191, B:48:0x013e, B:49:0x011a, B:53:0x00ea, B:54:0x00d2, B:55:0x00a8), top: B:4:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int writeFileHeader(net.lingala.zip4j.model.ZipModel r19, net.lingala.zip4j.model.FileHeader r20, java.io.OutputStream r21) throws net.lingala.zip4j.exception.ZipException {
        /*
            Method dump skipped, instructions count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.core.HeaderWriter.writeFileHeader(net.lingala.zip4j.model.ZipModel, net.lingala.zip4j.model.FileHeader, java.io.OutputStream):int");
    }

    private void writeZip64EndOfCentralDirectoryRecord(ZipModel zipModel, OutputStream outputStream, int i, long j) throws ZipException {
        int i2;
        if (zipModel == null || outputStream == null) {
            throw new ZipException("zip model or output stream is null, cannot write zip64 end of central directory record");
        }
        try {
            ArrayList arrayList = new ArrayList();
            byte[] bArr = new byte[2];
            byte[] bArr2 = {0, 0};
            byte[] bArr3 = new byte[4];
            byte[] bArr4 = new byte[8];
            Raw.writeIntLittleEndian(bArr3, 0, 101075792);
            copyByteArrayToArrayList(bArr3, arrayList);
            Raw.writeLongLittleEndian(bArr4, 0, 44L);
            copyByteArrayToArrayList(bArr4, arrayList);
            if (zipModel.getCentralDirectory() != null && zipModel.getCentralDirectory().getFileHeaders() != null && zipModel.getCentralDirectory().getFileHeaders().size() > 0) {
                Raw.writeShortLittleEndian(bArr, 0, (short) ((FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(0)).getVersionMadeBy());
                copyByteArrayToArrayList(bArr, arrayList);
                Raw.writeShortLittleEndian(bArr, 0, (short) ((FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(0)).getVersionNeededToExtract());
                copyByteArrayToArrayList(bArr, arrayList);
            } else {
                copyByteArrayToArrayList(bArr2, arrayList);
                copyByteArrayToArrayList(bArr2, arrayList);
            }
            Raw.writeIntLittleEndian(bArr3, 0, zipModel.getEndCentralDirRecord().getNoOfThisDisk());
            copyByteArrayToArrayList(bArr3, arrayList);
            Raw.writeIntLittleEndian(bArr3, 0, zipModel.getEndCentralDirRecord().getNoOfThisDiskStartOfCentralDir());
            copyByteArrayToArrayList(bArr3, arrayList);
            if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null) {
                throw new ZipException("invalid central directory/file headers, cannot write end of central directory record");
            }
            int size = zipModel.getCentralDirectory().getFileHeaders().size();
            if (zipModel.isSplitArchive()) {
                countNumberOfFileHeaderEntriesOnDisk(zipModel.getCentralDirectory().getFileHeaders(), zipModel.getEndCentralDirRecord().getNoOfThisDisk());
                i2 = 0;
            } else {
                i2 = size;
            }
            Raw.writeLongLittleEndian(bArr4, 0, i2);
            copyByteArrayToArrayList(bArr4, arrayList);
            Raw.writeLongLittleEndian(bArr4, 0, size);
            copyByteArrayToArrayList(bArr4, arrayList);
            Raw.writeLongLittleEndian(bArr4, 0, i);
            copyByteArrayToArrayList(bArr4, arrayList);
            Raw.writeLongLittleEndian(bArr4, 0, j);
            copyByteArrayToArrayList(bArr4, arrayList);
            outputStream.write(byteArrayListToByteArray(arrayList));
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            throw new ZipException(e2);
        }
    }

    private void writeZip64EndOfCentralDirectoryLocator(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("zip model or output stream is null, cannot write zip64 end of central directory locator");
        }
        try {
            ArrayList arrayList = new ArrayList();
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[8];
            Raw.writeIntLittleEndian(bArr, 0, 117853008);
            copyByteArrayToArrayList(bArr, arrayList);
            Raw.writeIntLittleEndian(bArr, 0, zipModel.getZip64EndCentralDirLocator().getNoOfDiskStartOfZip64EndOfCentralDirRec());
            copyByteArrayToArrayList(bArr, arrayList);
            Raw.writeLongLittleEndian(bArr2, 0, zipModel.getZip64EndCentralDirLocator().getOffsetZip64EndOfCentralDirRec());
            copyByteArrayToArrayList(bArr2, arrayList);
            Raw.writeIntLittleEndian(bArr, 0, zipModel.getZip64EndCentralDirLocator().getTotNumberOfDiscs());
            copyByteArrayToArrayList(bArr, arrayList);
            outputStream.write(byteArrayListToByteArray(arrayList));
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            throw new ZipException(e2);
        }
    }

    private void writeEndOfCentralDirectoryRecord(ZipModel zipModel, OutputStream outputStream, int i, long j) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("zip model or output stream is null, cannot write end of central directory record");
        }
        try {
            ArrayList arrayList = new ArrayList();
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[8];
            Raw.writeIntLittleEndian(bArr2, 0, (int) zipModel.getEndCentralDirRecord().getSignature());
            copyByteArrayToArrayList(bArr2, arrayList);
            Raw.writeShortLittleEndian(bArr, 0, (short) zipModel.getEndCentralDirRecord().getNoOfThisDisk());
            copyByteArrayToArrayList(bArr, arrayList);
            Raw.writeShortLittleEndian(bArr, 0, (short) zipModel.getEndCentralDirRecord().getNoOfThisDiskStartOfCentralDir());
            copyByteArrayToArrayList(bArr, arrayList);
            if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null) {
                throw new ZipException("invalid central directory/file headers, cannot write end of central directory record");
            }
            int size = zipModel.getCentralDirectory().getFileHeaders().size();
            Raw.writeShortLittleEndian(bArr, 0, (short) (zipModel.isSplitArchive() ? countNumberOfFileHeaderEntriesOnDisk(zipModel.getCentralDirectory().getFileHeaders(), zipModel.getEndCentralDirRecord().getNoOfThisDisk()) : size));
            copyByteArrayToArrayList(bArr, arrayList);
            Raw.writeShortLittleEndian(bArr, 0, (short) size);
            copyByteArrayToArrayList(bArr, arrayList);
            Raw.writeIntLittleEndian(bArr2, 0, i);
            copyByteArrayToArrayList(bArr2, arrayList);
            if (j > InternalZipConstants.ZIP_64_LIMIT) {
                Raw.writeLongLittleEndian(bArr3, 0, InternalZipConstants.ZIP_64_LIMIT);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, arrayList);
            } else {
                Raw.writeLongLittleEndian(bArr3, 0, j);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, arrayList);
            }
            int length = zipModel.getEndCentralDirRecord().getComment() != null ? zipModel.getEndCentralDirRecord().getComment().length() : 0;
            Raw.writeShortLittleEndian(bArr, 0, (short) length);
            copyByteArrayToArrayList(bArr, arrayList);
            if (length > 0) {
                copyByteArrayToArrayList(zipModel.getEndCentralDirRecord().getComment().getBytes(), arrayList);
            }
            outputStream.write(byteArrayListToByteArray(arrayList));
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    public void updateLocalFileHeader(LocalFileHeader localFileHeader, long j, int i, ZipModel zipModel, byte[] bArr, int i2, SplitOutputStream splitOutputStream) throws ZipException {
        SplitOutputStream splitOutputStream2;
        boolean z;
        String str;
        if (localFileHeader == null || j < 0 || zipModel == null) {
            throw new ZipException("invalid input parameters, cannot update local file header");
        }
        try {
            if (i2 != splitOutputStream.getCurrSplitFileCounter()) {
                File file = new File(zipModel.getZipFile());
                String parent = file.getParent();
                String zipFileNameWithoutExt = Zip4jUtil.getZipFileNameWithoutExt(file.getName());
                String str2 = parent + System.getProperty("file.separator");
                if (i2 < 9) {
                    str = str2 + zipFileNameWithoutExt + ".z0" + (i2 + 1);
                } else {
                    str = str2 + zipFileNameWithoutExt + ".z" + (i2 + 1);
                }
                splitOutputStream2 = new SplitOutputStream(new File(str));
                z = true;
            } else {
                splitOutputStream2 = splitOutputStream;
                z = false;
            }
            long filePointer = splitOutputStream2.getFilePointer();
            if (splitOutputStream2 == null) {
                throw new ZipException("invalid output stream handler, cannot update local file header");
            }
            if (i == 14) {
                splitOutputStream2.seek(j + i);
                splitOutputStream2.write(bArr);
            } else if (i == 18) {
                updateCompressedSizeInLocalFileHeader(splitOutputStream2, localFileHeader, j, i, bArr, zipModel.isZip64Format());
            }
            if (z) {
                splitOutputStream2.close();
            } else {
                splitOutputStream.seek(filePointer);
            }
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    private void updateCompressedSizeInLocalFileHeader(SplitOutputStream splitOutputStream, LocalFileHeader localFileHeader, long j, long j2, byte[] bArr, boolean z) throws ZipException {
        if (splitOutputStream == null) {
            throw new ZipException("invalid output stream, cannot update compressed size for local file header");
        }
        try {
            if (localFileHeader.isWriteComprSizeInZip64ExtraRecord()) {
                if (bArr.length != 8) {
                    throw new ZipException("attempting to write a non 8-byte compressed size block for a zip64 file");
                }
                splitOutputStream.seek(j + j2 + 4 + 4 + 2 + 2 + localFileHeader.getFileNameLength() + 2 + 2 + 8);
                splitOutputStream.write(bArr);
                return;
            }
            splitOutputStream.seek(j + j2);
            splitOutputStream.write(bArr);
        } catch (IOException e) {
            throw new ZipException(e);
        }
    }

    private void copyByteArrayToArrayList(byte[] bArr, ArrayList arrayList) throws ZipException {
        if (arrayList == null || bArr == null) {
            throw new ZipException("one of the input parameters is null, cannot copy byte array to array list");
        }
        for (byte b : bArr) {
            arrayList.add(Byte.toString(b));
        }
    }

    private byte[] byteArrayListToByteArray(ArrayList arrayList) throws ZipException {
        if (arrayList == null) {
            throw new ZipException("input byte array list is null, cannot conver to byte array");
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        byte[] bArr = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            bArr[i] = Byte.parseByte((String) arrayList.get(i));
        }
        return bArr;
    }

    private int countNumberOfFileHeaderEntriesOnDisk(ArrayList arrayList, int i) throws ZipException {
        if (arrayList == null) {
            throw new ZipException("file headers are null, cannot calculate number of entries on this disk");
        }
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (((FileHeader) arrayList.get(i3)).getDiskNumberStart() == i - 1) {
                i2++;
            }
        }
        return i2;
    }
}
