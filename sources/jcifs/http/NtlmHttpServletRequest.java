package jcifs.http;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/* loaded from: classes2.dex */
class NtlmHttpServletRequest extends HttpServletRequestWrapper {
    Principal principal;

    @Override // javax.servlet.http.HttpServletRequestWrapper, javax.servlet.http.HttpServletRequest
    public String getAuthType() {
        return "NTLM";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NtlmHttpServletRequest(HttpServletRequest httpServletRequest, Principal principal) {
        super(httpServletRequest);
        this.principal = principal;
    }

    @Override // javax.servlet.http.HttpServletRequestWrapper, javax.servlet.http.HttpServletRequest
    public String getRemoteUser() {
        return this.principal.getName();
    }

    @Override // javax.servlet.http.HttpServletRequestWrapper, javax.servlet.http.HttpServletRequest
    public Principal getUserPrincipal() {
        return this.principal;
    }
}
