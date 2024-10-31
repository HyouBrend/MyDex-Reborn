package org.apache.commons.net.nntp;

import java.util.ArrayList;
import java.util.StringTokenizer;

/* loaded from: classes2.dex */
public class Article implements Threadable {
    private String articleId;
    private int articleNumber;
    private String date;
    private String from;
    public Article kid;
    public Article next;
    private StringBuffer references;
    private String simplifiedSubject;
    private String subject;
    private boolean isReply = false;
    private StringBuffer header = new StringBuffer();

    public void addHeaderField(String str, String str2) {
        this.header.append(str);
        this.header.append(": ");
        this.header.append(str2);
        this.header.append('\n');
    }

    public void addReference(String str) {
        if (this.references == null) {
            StringBuffer stringBuffer = new StringBuffer();
            this.references = stringBuffer;
            stringBuffer.append("References: ");
        }
        this.references.append(str);
        this.references.append("\t");
    }

    public String[] getReferences() {
        if (this.references == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(this.references.substring(this.references.toString().indexOf(58)), "\t");
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void simplifySubject() {
        boolean z;
        String subject = getSubject();
        int length = subject.length();
        int i = 0;
        for (boolean z2 = false; !z2; z2 = z) {
            while (i < length && subject.charAt(i) == ' ') {
                i++;
            }
            int i2 = length - 2;
            z = true;
            if (i < i2 && (subject.charAt(i) == 'r' || subject.charAt(i) == 'R')) {
                int i3 = i + 1;
                if (subject.charAt(i3) == 'e' || subject.charAt(i3) == 'E') {
                    int i4 = i + 2;
                    if (subject.charAt(i4) == ':') {
                        i += 3;
                        this.isReply = true;
                    } else if (i < i2 && (subject.charAt(i4) == '[' || subject.charAt(i4) == '(')) {
                        int i5 = i + 3;
                        while (i5 < length && subject.charAt(i5) >= '0' && subject.charAt(i5) <= '9') {
                            i5++;
                        }
                        if (i5 < length - 1 && ((subject.charAt(i5) == ']' || subject.charAt(i5) == ')') && subject.charAt(i5 + 1) == ':')) {
                            this.isReply = true;
                            i = i5 + 2;
                        }
                    }
                    z = false;
                }
            }
            if ("(no subject)".equals(this.simplifiedSubject)) {
                this.simplifiedSubject = "";
            }
            int i6 = length;
            while (i6 > i && subject.charAt(i6 - 1) < ' ') {
                i6--;
            }
            if (i == 0 && i6 == length) {
                this.simplifiedSubject = subject;
            } else {
                this.simplifiedSubject = subject.substring(i, i6);
            }
        }
    }

    public static void printThread(Article article, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            System.out.print("==>");
        }
        System.out.println(article.getSubject() + "\t" + article.getFrom());
        Article article2 = article.kid;
        if (article2 != null) {
            printThread(article2, i + 1);
        }
        Article article3 = article.next;
        if (article3 != null) {
            printThread(article3, i);
        }
    }

    public String getArticleId() {
        return this.articleId;
    }

    public int getArticleNumber() {
        return this.articleNumber;
    }

    public String getDate() {
        return this.date;
    }

    public String getFrom() {
        return this.from;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setArticleId(String str) {
        this.articleId = str;
    }

    public void setArticleNumber(int i) {
        this.articleNumber = i;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public boolean isDummy() {
        return getSubject() == null;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public String messageThreadId() {
        return this.articleId;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public String[] messageThreadReferences() {
        return getReferences();
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public String simplifiedSubject() {
        if (this.simplifiedSubject == null) {
            simplifySubject();
        }
        return this.simplifiedSubject;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public boolean subjectIsReply() {
        if (this.simplifiedSubject == null) {
            simplifySubject();
        }
        return this.isReply;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public void setChild(Threadable threadable) {
        this.kid = (Article) threadable;
        flushSubjectCache();
    }

    private void flushSubjectCache() {
        this.simplifiedSubject = null;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public void setNext(Threadable threadable) {
        this.next = (Article) threadable;
        flushSubjectCache();
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public Threadable makeDummy() {
        return new Article();
    }
}
