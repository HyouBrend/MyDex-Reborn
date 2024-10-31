package jcifs.smb;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import jcifs.dcerpc.DcerpcHandle;
import jcifs.dcerpc.UnicodeString;
import jcifs.dcerpc.msrpc.LsaPolicyHandle;
import jcifs.dcerpc.msrpc.MsrpcEnumerateAliasesInDomain;
import jcifs.dcerpc.msrpc.MsrpcGetMembersInAlias;
import jcifs.dcerpc.msrpc.MsrpcLookupSids;
import jcifs.dcerpc.msrpc.MsrpcQueryInformationPolicy;
import jcifs.dcerpc.msrpc.SamrAliasHandle;
import jcifs.dcerpc.msrpc.SamrDomainHandle;
import jcifs.dcerpc.msrpc.SamrPolicyHandle;
import jcifs.dcerpc.msrpc.lsarpc;
import jcifs.dcerpc.msrpc.samr;
import jcifs.dcerpc.rpc;
import jcifs.util.Encdec;
import jcifs.util.Hexdump;
import kotlin.UByte;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public class SID extends rpc.sid_t {
    public static SID CREATOR_OWNER = null;
    public static SID EVERYONE = null;
    public static final int SID_FLAG_RESOLVE_SIDS = 1;
    public static final int SID_TYPE_ALIAS = 4;
    public static final int SID_TYPE_DELETED = 6;
    public static final int SID_TYPE_DOMAIN = 3;
    public static final int SID_TYPE_DOM_GRP = 2;
    public static final int SID_TYPE_INVALID = 7;
    static final String[] SID_TYPE_NAMES = {"0", "User", "Domain group", "Domain", "Local group", "Builtin group", "Deleted", "Invalid", "Unknown"};
    public static final int SID_TYPE_UNKNOWN = 8;
    public static final int SID_TYPE_USER = 1;
    public static final int SID_TYPE_USE_NONE = 0;
    public static final int SID_TYPE_WKN_GRP = 5;
    public static SID SYSTEM;
    static Map sid_cache;
    String acctName;
    String domainName;
    NtlmPasswordAuthentication origin_auth;
    String origin_server;
    int type;

    static {
        EVERYONE = null;
        CREATOR_OWNER = null;
        SYSTEM = null;
        try {
            EVERYONE = new SID("S-1-1-0");
            CREATOR_OWNER = new SID("S-1-3-0");
            SYSTEM = new SID("S-1-5-18");
        } catch (SmbException unused) {
        }
        sid_cache = new HashMap();
    }

    static void resolveSids(DcerpcHandle dcerpcHandle, LsaPolicyHandle lsaPolicyHandle, SID[] sidArr) throws IOException {
        MsrpcLookupSids msrpcLookupSids = new MsrpcLookupSids(lsaPolicyHandle, sidArr);
        dcerpcHandle.sendrecv(msrpcLookupSids);
        int i = msrpcLookupSids.retval;
        if (i != -1073741709 && i != 0 && i != 263) {
            throw new SmbException(msrpcLookupSids.retval, false);
        }
        for (int i2 = 0; i2 < sidArr.length; i2++) {
            sidArr[i2].type = msrpcLookupSids.names.names[i2].sid_type;
            SID sid = sidArr[i2];
            sid.domainName = null;
            int i3 = sid.type;
            if (i3 == 1 || i3 == 2 || i3 == 3 || i3 == 4 || i3 == 5) {
                rpc.unicode_string unicode_stringVar = msrpcLookupSids.domains.domains[msrpcLookupSids.names.names[i2].sid_index].name;
                sidArr[i2].domainName = new UnicodeString(unicode_stringVar, false).toString();
            }
            sidArr[i2].acctName = new UnicodeString(msrpcLookupSids.names.names[i2].name, false).toString();
            SID sid2 = sidArr[i2];
            sid2.origin_server = null;
            sid2.origin_auth = null;
        }
    }

    static void resolveSids0(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication, SID[] sidArr) throws IOException {
        LsaPolicyHandle lsaPolicyHandle;
        synchronized (sid_cache) {
            DcerpcHandle dcerpcHandle = null;
            try {
                DcerpcHandle handle = DcerpcHandle.getHandle("ncacn_np:" + str + "[\\PIPE\\lsarpc]", ntlmPasswordAuthentication);
                try {
                    int indexOf = str.indexOf(46);
                    if (indexOf > 0 && !Character.isDigit(str.charAt(0))) {
                        str = str.substring(0, indexOf);
                    }
                    lsaPolicyHandle = new LsaPolicyHandle(handle, "\\\\" + str, 2048);
                } catch (Throwable th) {
                    th = th;
                    lsaPolicyHandle = null;
                }
                try {
                    resolveSids(handle, lsaPolicyHandle, sidArr);
                    if (handle != null) {
                        lsaPolicyHandle.close();
                        handle.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    dcerpcHandle = handle;
                    if (dcerpcHandle != null) {
                        if (lsaPolicyHandle != null) {
                            lsaPolicyHandle.close();
                        }
                        dcerpcHandle.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                lsaPolicyHandle = null;
            }
        }
    }

    public static void resolveSids(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication, SID[] sidArr, int i, int i2) throws IOException {
        ArrayList arrayList = new ArrayList(sidArr.length);
        synchronized (sid_cache) {
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = i + i3;
                SID sid = (SID) sid_cache.get(sidArr[i4]);
                if (sid != null) {
                    SID sid2 = sidArr[i4];
                    sid2.type = sid.type;
                    sid2.domainName = sid.domainName;
                    sid2.acctName = sid.acctName;
                } else {
                    arrayList.add(sidArr[i4]);
                }
            }
            if (arrayList.size() > 0) {
                SID[] sidArr2 = (SID[]) arrayList.toArray(new SID[0]);
                resolveSids0(str, ntlmPasswordAuthentication, sidArr2);
                for (SID sid3 : sidArr2) {
                    sid_cache.put(sid3, sid3);
                }
            }
        }
    }

    public static void resolveSids(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication, SID[] sidArr) throws IOException {
        ArrayList arrayList = new ArrayList(sidArr.length);
        synchronized (sid_cache) {
            for (int i = 0; i < sidArr.length; i++) {
                SID sid = (SID) sid_cache.get(sidArr[i]);
                if (sid != null) {
                    SID sid2 = sidArr[i];
                    sid2.type = sid.type;
                    sid2.domainName = sid.domainName;
                    sid2.acctName = sid.acctName;
                } else {
                    arrayList.add(sidArr[i]);
                }
            }
            if (arrayList.size() > 0) {
                SID[] sidArr2 = (SID[]) arrayList.toArray(new SID[0]);
                resolveSids0(str, ntlmPasswordAuthentication, sidArr2);
                for (SID sid3 : sidArr2) {
                    sid_cache.put(sid3, sid3);
                }
            }
        }
    }

    public static SID getServerSid(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws IOException {
        LsaPolicyHandle lsaPolicyHandle;
        SID sid;
        lsarpc.LsarDomainInfo lsarDomainInfo = new lsarpc.LsarDomainInfo();
        synchronized (sid_cache) {
            DcerpcHandle dcerpcHandle = null;
            try {
                DcerpcHandle handle = DcerpcHandle.getHandle("ncacn_np:" + str + "[\\PIPE\\lsarpc]", ntlmPasswordAuthentication);
                try {
                    lsaPolicyHandle = new LsaPolicyHandle(handle, null, 1);
                    try {
                        MsrpcQueryInformationPolicy msrpcQueryInformationPolicy = new MsrpcQueryInformationPolicy(lsaPolicyHandle, (short) 5, lsarDomainInfo);
                        handle.sendrecv(msrpcQueryInformationPolicy);
                        if (msrpcQueryInformationPolicy.retval != 0) {
                            throw new SmbException(msrpcQueryInformationPolicy.retval, false);
                        }
                        sid = new SID(lsarDomainInfo.sid, 3, new UnicodeString(lsarDomainInfo.name, false).toString(), null, false);
                        if (handle != null) {
                            lsaPolicyHandle.close();
                            handle.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        dcerpcHandle = handle;
                        if (dcerpcHandle != null) {
                            if (lsaPolicyHandle != null) {
                                lsaPolicyHandle.close();
                            }
                            dcerpcHandle.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    lsaPolicyHandle = null;
                }
            } catch (Throwable th3) {
                th = th3;
                lsaPolicyHandle = null;
            }
        }
        return sid;
    }

    public static byte[] toByteArray(rpc.sid_t sid_tVar) {
        int i = 8;
        byte[] bArr = new byte[(sid_tVar.sub_authority_count * 4) + 8];
        bArr[0] = sid_tVar.revision;
        bArr[1] = sid_tVar.sub_authority_count;
        System.arraycopy(sid_tVar.identifier_authority, 0, bArr, 2, 6);
        for (int i2 = 0; i2 < sid_tVar.sub_authority_count; i2++) {
            Encdec.enc_uint32le(sid_tVar.sub_authority[i2], bArr, i);
            i += 4;
        }
        return bArr;
    }

    public SID(byte[] bArr, int i) {
        this.domainName = null;
        this.acctName = null;
        this.origin_server = null;
        this.origin_auth = null;
        int i2 = i + 1;
        this.revision = bArr[i];
        int i3 = i2 + 1;
        this.sub_authority_count = bArr[i2];
        this.identifier_authority = new byte[6];
        System.arraycopy(bArr, i3, this.identifier_authority, 0, 6);
        int i4 = i3 + 6;
        if (this.sub_authority_count > 100) {
            throw new RuntimeException("Invalid SID sub_authority_count");
        }
        this.sub_authority = new int[this.sub_authority_count];
        for (int i5 = 0; i5 < this.sub_authority_count; i5++) {
            this.sub_authority[i5] = ServerMessageBlock.readInt4(bArr, i4);
            i4 += 4;
        }
    }

    public SID(String str) throws SmbException {
        long parseLong;
        this.domainName = null;
        this.acctName = null;
        this.origin_server = null;
        this.origin_auth = null;
        StringTokenizer stringTokenizer = new StringTokenizer(str, "-");
        if (stringTokenizer.countTokens() < 3 || !stringTokenizer.nextToken().equals(ExifInterface.LATITUDE_SOUTH)) {
            throw new SmbException("Bad textual SID format: " + str);
        }
        this.revision = Byte.parseByte(stringTokenizer.nextToken());
        String nextToken = stringTokenizer.nextToken();
        if (nextToken.startsWith("0x")) {
            parseLong = Long.parseLong(nextToken.substring(2), 16);
        } else {
            parseLong = Long.parseLong(nextToken);
        }
        this.identifier_authority = new byte[6];
        int i = 5;
        while (parseLong > 0) {
            this.identifier_authority[i] = (byte) (parseLong % 256);
            parseLong >>= 8;
            i--;
        }
        this.sub_authority_count = (byte) stringTokenizer.countTokens();
        if (this.sub_authority_count > 0) {
            this.sub_authority = new int[this.sub_authority_count];
            for (int i2 = 0; i2 < this.sub_authority_count; i2++) {
                this.sub_authority[i2] = (int) (Long.parseLong(stringTokenizer.nextToken()) & InternalZipConstants.ZIP_64_LIMIT);
            }
        }
    }

    public SID(SID sid, int i) {
        this.domainName = null;
        this.acctName = null;
        this.origin_server = null;
        this.origin_auth = null;
        this.revision = sid.revision;
        this.identifier_authority = sid.identifier_authority;
        this.sub_authority_count = (byte) (sid.sub_authority_count + 1);
        this.sub_authority = new int[this.sub_authority_count];
        int i2 = 0;
        while (i2 < sid.sub_authority_count) {
            this.sub_authority[i2] = sid.sub_authority[i2];
            i2++;
        }
        this.sub_authority[i2] = i;
    }

    public SID(rpc.sid_t sid_tVar, int i, String str, String str2, boolean z) {
        this.domainName = null;
        this.acctName = null;
        this.origin_server = null;
        this.origin_auth = null;
        this.revision = sid_tVar.revision;
        this.sub_authority_count = sid_tVar.sub_authority_count;
        this.identifier_authority = sid_tVar.identifier_authority;
        this.sub_authority = sid_tVar.sub_authority;
        this.type = i;
        this.domainName = str;
        this.acctName = str2;
        if (z) {
            this.sub_authority_count = (byte) (this.sub_authority_count - 1);
            this.sub_authority = new int[this.sub_authority_count];
            for (int i2 = 0; i2 < this.sub_authority_count; i2++) {
                this.sub_authority[i2] = sid_tVar.sub_authority[i2];
            }
        }
    }

    public SID getDomainSid() {
        return new SID(this, 3, this.domainName, null, getType() != 3);
    }

    public int getRid() {
        if (getType() == 3) {
            throw new IllegalArgumentException("This SID is a domain sid");
        }
        return this.sub_authority[this.sub_authority_count - 1];
    }

    public int getType() {
        if (this.origin_server != null) {
            resolveWeak();
        }
        return this.type;
    }

    public String getTypeText() {
        if (this.origin_server != null) {
            resolveWeak();
        }
        return SID_TYPE_NAMES[this.type];
    }

    public String getDomainName() {
        if (this.origin_server != null) {
            resolveWeak();
        }
        if (this.type == 8) {
            return toString().substring(0, (r0.length() - getAccountName().length()) - 1);
        }
        return this.domainName;
    }

    public String getAccountName() {
        if (this.origin_server != null) {
            resolveWeak();
        }
        int i = this.type;
        if (i != 8) {
            return i == 3 ? "" : this.acctName;
        }
        return "" + this.sub_authority[this.sub_authority_count - 1];
    }

    public int hashCode() {
        int i = this.identifier_authority[5];
        for (int i2 = 0; i2 < this.sub_authority_count; i2++) {
            i += this.sub_authority[i2] * 65599;
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SID)) {
            return false;
        }
        SID sid = (SID) obj;
        if (sid == this) {
            return true;
        }
        if (sid.sub_authority_count != this.sub_authority_count) {
            return false;
        }
        int i = this.sub_authority_count;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                for (int i3 = 0; i3 < 6; i3++) {
                    if (sid.identifier_authority[i3] != this.identifier_authority[i3]) {
                        return false;
                    }
                }
                return sid.revision == this.revision;
            }
            if (sid.sub_authority[i2] != this.sub_authority[i2]) {
                return false;
            }
            i = i2;
        }
    }

    public String toString() {
        String str;
        String str2 = "S-" + (this.revision & UByte.MAX_VALUE) + "-";
        if (this.identifier_authority[0] == 0 && this.identifier_authority[1] == 0) {
            long j = 0;
            long j2 = 0;
            for (int i = 5; i > 1; i--) {
                j += (this.identifier_authority[i] & 255) << ((int) j2);
                j2 += 8;
            }
            str = str2 + j;
        } else {
            str = (str2 + "0x") + Hexdump.toHexString(this.identifier_authority, 0, 6);
        }
        for (int i2 = 0; i2 < this.sub_authority_count; i2++) {
            str = str + "-" + (this.sub_authority[i2] & InternalZipConstants.ZIP_64_LIMIT);
        }
        return str;
    }

    public String toDisplayString() {
        if (this.origin_server != null) {
            resolveWeak();
        }
        String str = this.domainName;
        if (str != null) {
            int i = this.type;
            if (i == 3) {
                return str;
            }
            if (i == 5 || str.equals("BUILTIN")) {
                if (this.type == 8) {
                    return toString();
                }
                return this.acctName;
            }
            return this.domainName + "\\" + this.acctName;
        }
        return toString();
    }

    public void resolve(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication) throws IOException {
        resolveSids(str, ntlmPasswordAuthentication, new SID[]{this});
    }

    void resolveWeak() {
        String str = this.origin_server;
        if (str != null) {
            try {
                resolve(str, this.origin_auth);
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.origin_server = null;
                this.origin_auth = null;
                throw th;
            }
            this.origin_server = null;
            this.origin_auth = null;
        }
    }

    static SID[] getGroupMemberSids0(DcerpcHandle dcerpcHandle, SamrDomainHandle samrDomainHandle, SID sid, int i, int i2) throws IOException {
        lsarpc.LsarSidArray lsarSidArray = new lsarpc.LsarSidArray();
        SamrAliasHandle samrAliasHandle = null;
        try {
            SamrAliasHandle samrAliasHandle2 = new SamrAliasHandle(dcerpcHandle, samrDomainHandle, 131084, i);
            try {
                MsrpcGetMembersInAlias msrpcGetMembersInAlias = new MsrpcGetMembersInAlias(samrAliasHandle2, lsarSidArray);
                dcerpcHandle.sendrecv(msrpcGetMembersInAlias);
                if (msrpcGetMembersInAlias.retval != 0) {
                    throw new SmbException(msrpcGetMembersInAlias.retval, false);
                }
                int i3 = msrpcGetMembersInAlias.sids.num_sids;
                SID[] sidArr = new SID[i3];
                String server = dcerpcHandle.getServer();
                NtlmPasswordAuthentication ntlmPasswordAuthentication = (NtlmPasswordAuthentication) dcerpcHandle.getPrincipal();
                for (int i4 = 0; i4 < i3; i4++) {
                    SID sid2 = new SID(msrpcGetMembersInAlias.sids.sids[i4].sid, 0, null, null, false);
                    sidArr[i4] = sid2;
                    sid2.origin_server = server;
                    sid2.origin_auth = ntlmPasswordAuthentication;
                }
                if (i3 > 0 && (i2 & 1) != 0) {
                    resolveSids(server, ntlmPasswordAuthentication, sidArr);
                }
                samrAliasHandle2.close();
                return sidArr;
            } catch (Throwable th) {
                th = th;
                samrAliasHandle = samrAliasHandle2;
                if (samrAliasHandle != null) {
                    samrAliasHandle.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public SID[] getGroupMemberSids(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication, int i) throws IOException {
        SamrDomainHandle samrDomainHandle;
        SamrPolicyHandle samrPolicyHandle;
        DcerpcHandle handle;
        SID[] groupMemberSids0;
        int i2 = this.type;
        if (i2 != 2 && i2 != 4) {
            return new SID[0];
        }
        SID domainSid = getDomainSid();
        synchronized (sid_cache) {
            DcerpcHandle dcerpcHandle = null;
            try {
                handle = DcerpcHandle.getHandle("ncacn_np:" + str + "[\\PIPE\\samr]", ntlmPasswordAuthentication);
                try {
                    samrPolicyHandle = new SamrPolicyHandle(handle, str, 48);
                    try {
                        samrDomainHandle = new SamrDomainHandle(handle, samrPolicyHandle, 512, domainSid);
                    } catch (Throwable th) {
                        th = th;
                        samrDomainHandle = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    samrDomainHandle = null;
                    samrPolicyHandle = null;
                }
            } catch (Throwable th3) {
                th = th3;
                samrDomainHandle = null;
                samrPolicyHandle = null;
            }
            try {
                groupMemberSids0 = getGroupMemberSids0(handle, samrDomainHandle, domainSid, getRid(), i);
                if (handle != null) {
                    samrDomainHandle.close();
                    samrPolicyHandle.close();
                    handle.close();
                }
            } catch (Throwable th4) {
                th = th4;
                dcerpcHandle = handle;
                if (dcerpcHandle != null) {
                    if (samrPolicyHandle != null) {
                        if (samrDomainHandle != null) {
                            samrDomainHandle.close();
                        }
                        samrPolicyHandle.close();
                    }
                    dcerpcHandle.close();
                }
                throw th;
            }
        }
        return groupMemberSids0;
    }

    static Map getLocalGroupsMap(String str, NtlmPasswordAuthentication ntlmPasswordAuthentication, int i) throws IOException {
        SamrDomainHandle samrDomainHandle;
        SamrPolicyHandle samrPolicyHandle;
        DcerpcHandle handle;
        HashMap hashMap;
        SID serverSid = getServerSid(str, ntlmPasswordAuthentication);
        samr.SamrSamArray samrSamArray = new samr.SamrSamArray();
        synchronized (sid_cache) {
            DcerpcHandle dcerpcHandle = null;
            try {
                handle = DcerpcHandle.getHandle("ncacn_np:" + str + "[\\PIPE\\samr]", ntlmPasswordAuthentication);
                try {
                    samrPolicyHandle = new SamrPolicyHandle(handle, str, 33554432);
                    try {
                        samrDomainHandle = new SamrDomainHandle(handle, samrPolicyHandle, 33554432, serverSid);
                    } catch (Throwable th) {
                        th = th;
                        samrDomainHandle = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    samrDomainHandle = null;
                    samrPolicyHandle = null;
                }
            } catch (Throwable th3) {
                th = th3;
                samrDomainHandle = null;
                samrPolicyHandle = null;
            }
            try {
                MsrpcEnumerateAliasesInDomain msrpcEnumerateAliasesInDomain = new MsrpcEnumerateAliasesInDomain(samrDomainHandle, 65535, samrSamArray);
                handle.sendrecv(msrpcEnumerateAliasesInDomain);
                if (msrpcEnumerateAliasesInDomain.retval != 0) {
                    throw new SmbException(msrpcEnumerateAliasesInDomain.retval, false);
                }
                hashMap = new HashMap();
                for (int i2 = 0; i2 < msrpcEnumerateAliasesInDomain.sam.count; i2++) {
                    samr.SamrSamEntry samrSamEntry = msrpcEnumerateAliasesInDomain.sam.entries[i2];
                    SID[] groupMemberSids0 = getGroupMemberSids0(handle, samrDomainHandle, serverSid, samrSamEntry.idx, i);
                    SID sid = new SID(serverSid, samrSamEntry.idx);
                    sid.type = 4;
                    sid.domainName = serverSid.getDomainName();
                    sid.acctName = new UnicodeString(samrSamEntry.name, false).toString();
                    for (int i3 = 0; i3 < groupMemberSids0.length; i3++) {
                        ArrayList arrayList = (ArrayList) hashMap.get(groupMemberSids0[i3]);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            hashMap.put(groupMemberSids0[i3], arrayList);
                        }
                        if (!arrayList.contains(sid)) {
                            arrayList.add(sid);
                        }
                    }
                }
                if (handle != null) {
                    samrDomainHandle.close();
                    samrPolicyHandle.close();
                    handle.close();
                }
            } catch (Throwable th4) {
                th = th4;
                dcerpcHandle = handle;
                if (dcerpcHandle != null) {
                    if (samrPolicyHandle != null) {
                        if (samrDomainHandle != null) {
                            samrDomainHandle.close();
                        }
                        samrPolicyHandle.close();
                    }
                    dcerpcHandle.close();
                }
                throw th;
            }
        }
        return hashMap;
    }
}
