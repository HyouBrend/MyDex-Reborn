package jcifs.http;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jcifs.ntlmssp.NtlmFlags;
import jcifs.ntlmssp.Type1Message;
import jcifs.ntlmssp.Type2Message;
import jcifs.ntlmssp.Type3Message;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.util.Base64;

/* loaded from: classes2.dex */
public class NtlmSsp implements NtlmFlags {
    public NtlmPasswordAuthentication doAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, byte[] bArr) throws IOException, ServletException {
        return authenticate(httpServletRequest, httpServletResponse, bArr);
    }

    public static NtlmPasswordAuthentication authenticate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, byte[] bArr) throws IOException, ServletException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && header.startsWith("NTLM ")) {
            byte[] decode = Base64.decode(header.substring(5));
            byte b = decode[8];
            if (b == 1) {
                httpServletResponse.setHeader("WWW-Authenticate", "NTLM " + Base64.encode(new Type2Message(new Type1Message(decode), bArr, (String) null).toByteArray()));
            } else if (b == 3) {
                Type3Message type3Message = new Type3Message(decode);
                byte[] lMResponse = type3Message.getLMResponse();
                if (lMResponse == null) {
                    lMResponse = new byte[0];
                }
                byte[] bArr2 = lMResponse;
                byte[] nTResponse = type3Message.getNTResponse();
                if (nTResponse == null) {
                    nTResponse = new byte[0];
                }
                return new NtlmPasswordAuthentication(type3Message.getDomain(), type3Message.getUser(), bArr, bArr2, nTResponse);
            }
        } else {
            httpServletResponse.setHeader("WWW-Authenticate", "NTLM");
        }
        httpServletResponse.setStatus(401);
        httpServletResponse.setContentLength(0);
        httpServletResponse.flushBuffer();
        return null;
    }
}
