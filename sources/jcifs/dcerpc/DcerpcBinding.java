package jcifs.dcerpc;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.HashMap;
import jcifs.dcerpc.msrpc.lsarpc;
import jcifs.dcerpc.msrpc.netdfs;
import jcifs.dcerpc.msrpc.samr;
import jcifs.dcerpc.msrpc.srvsvc;

/* loaded from: classes2.dex */
public class DcerpcBinding {
    private static HashMap INTERFACES;
    int major;
    int minor;
    String proto;
    String server;
    String endpoint = null;
    HashMap options = null;
    UUID uuid = null;

    static {
        HashMap hashMap = new HashMap();
        INTERFACES = hashMap;
        hashMap.put("srvsvc", srvsvc.getSyntax());
        INTERFACES.put("lsarpc", lsarpc.getSyntax());
        INTERFACES.put("samr", samr.getSyntax());
        INTERFACES.put("netdfs", netdfs.getSyntax());
    }

    public static void addInterface(String str, String str2) {
        INTERFACES.put(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DcerpcBinding(String str, String str2) {
        this.proto = str;
        this.server = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOption(String str, Object obj) throws DcerpcException {
        String str2;
        if (str.equals("endpoint")) {
            String lowerCase = obj.toString().toLowerCase();
            this.endpoint = lowerCase;
            if (lowerCase.startsWith("\\pipe\\") && (str2 = (String) INTERFACES.get(this.endpoint.substring(6))) != null) {
                int indexOf = str2.indexOf(58);
                int i = indexOf + 1;
                int indexOf2 = str2.indexOf(46, i);
                this.uuid = new UUID(str2.substring(0, indexOf));
                this.major = Integer.parseInt(str2.substring(i, indexOf2));
                this.minor = Integer.parseInt(str2.substring(indexOf2 + 1));
                return;
            }
            throw new DcerpcException("Bad endpoint: " + this.endpoint);
        }
        if (this.options == null) {
            this.options = new HashMap();
        }
        this.options.put(str, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getOption(String str) {
        if (str.equals("endpoint")) {
            return this.endpoint;
        }
        HashMap hashMap = this.options;
        if (hashMap != null) {
            return hashMap.get(str);
        }
        return null;
    }

    public String toString() {
        String str = this.proto + ":" + this.server + "[" + this.endpoint;
        HashMap hashMap = this.options;
        if (hashMap != null) {
            for (Object obj : hashMap.keySet()) {
                str = str + "," + obj + SimpleComparison.EQUAL_TO_OPERATION + this.options.get(obj);
            }
        }
        return str + "]";
    }
}
