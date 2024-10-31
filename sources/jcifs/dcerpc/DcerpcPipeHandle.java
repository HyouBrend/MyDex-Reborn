package jcifs.dcerpc;

import com.google.android.libraries.places.api.model.PlaceTypes;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbNamedPipe;
import jcifs.util.Encdec;
import kotlin.UByte;

/* loaded from: classes2.dex */
public class DcerpcPipeHandle extends DcerpcHandle {
    SmbNamedPipe pipe;
    SmbFileInputStream in = null;
    SmbFileOutputStream out = null;
    boolean isStart = true;

    public DcerpcPipeHandle(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws UnknownHostException, MalformedURLException, DcerpcException {
        this.binding = DcerpcHandle.parseBinding(str);
        String str2 = "smb://" + this.binding.server + "/IPC$/" + this.binding.endpoint.substring(6);
        String str3 = (String) this.binding.getOption("server");
        String str4 = "";
        if (str3 != null) {
            str4 = "&server=" + str3;
        }
        String str5 = (String) this.binding.getOption(PlaceTypes.ADDRESS);
        if (str3 != null) {
            str4 = str4 + "&address=" + str5;
        }
        if (str4.length() > 0) {
            str2 = str2 + "?" + str4.substring(1);
        }
        this.pipe = new SmbNamedPipe(str2, 27198979, ntlmPasswordAuthentication);
    }

    @Override // jcifs.dcerpc.DcerpcHandle
    protected void doSendFragment(byte[] bArr, int i, int i2, boolean z) throws IOException {
        SmbFileOutputStream smbFileOutputStream = this.out;
        if (smbFileOutputStream != null && !smbFileOutputStream.isOpen()) {
            throw new IOException("DCERPC pipe is no longer open");
        }
        if (this.in == null) {
            this.in = (SmbFileInputStream) this.pipe.getNamedPipeInputStream();
        }
        if (this.out == null) {
            this.out = (SmbFileOutputStream) this.pipe.getNamedPipeOutputStream();
        }
        if (z) {
            this.out.writeDirect(bArr, i, i2, 1);
        } else {
            this.out.write(bArr, i, i2);
        }
    }

    @Override // jcifs.dcerpc.DcerpcHandle
    protected void doReceiveFragment(byte[] bArr, boolean z) throws IOException {
        int readDirect;
        if (bArr.length < this.max_recv) {
            throw new IllegalArgumentException("buffer too small");
        }
        if (this.isStart && !z) {
            readDirect = this.in.read(bArr, 0, 1024);
        } else {
            readDirect = this.in.readDirect(bArr, 0, bArr.length);
        }
        if (bArr[0] != 5 && bArr[1] != 0) {
            throw new IOException("Unexpected DCERPC PDU header");
        }
        this.isStart = ((bArr[3] & UByte.MAX_VALUE) & 2) == 2;
        short dec_uint16le = Encdec.dec_uint16le(bArr, 8);
        if (dec_uint16le <= this.max_recv) {
            while (readDirect < dec_uint16le) {
                readDirect += this.in.readDirect(bArr, readDirect, dec_uint16le - readDirect);
            }
        } else {
            throw new IOException("Unexpected fragment length: " + ((int) dec_uint16le));
        }
    }

    @Override // jcifs.dcerpc.DcerpcHandle
    public void close() throws IOException {
        this.state = 0;
        SmbFileOutputStream smbFileOutputStream = this.out;
        if (smbFileOutputStream != null) {
            smbFileOutputStream.close();
        }
    }
}
