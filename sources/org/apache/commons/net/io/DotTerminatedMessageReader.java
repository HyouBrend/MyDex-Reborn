package org.apache.commons.net.io;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

/* loaded from: classes2.dex */
public final class DotTerminatedMessageReader extends Reader {
    private static final String LS = System.getProperty("line.separator");
    char[] LS_CHARS;
    private boolean atBeginning;
    private boolean eof;
    private char[] internalBuffer;
    private PushbackReader internalReader;
    private int pos;

    public DotTerminatedMessageReader(Reader reader) {
        super(reader);
        char[] charArray = LS.toCharArray();
        this.LS_CHARS = charArray;
        char[] cArr = new char[charArray.length + 3];
        this.internalBuffer = cArr;
        this.pos = cArr.length;
        this.atBeginning = true;
        this.eof = false;
        this.internalReader = new PushbackReader(reader);
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        synchronized (this.lock) {
            int i = this.pos;
            char[] cArr = this.internalBuffer;
            if (i < cArr.length) {
                this.pos = i + 1;
                return cArr[i];
            }
            if (this.eof) {
                return -1;
            }
            int read = this.internalReader.read();
            if (read == -1) {
                this.eof = true;
                return -1;
            }
            if (this.atBeginning) {
                this.atBeginning = false;
                if (read == 46) {
                    if (this.internalReader.read() == 46) {
                        return 46;
                    }
                    this.eof = true;
                    this.internalReader.read();
                    return -1;
                }
            }
            int i2 = read;
            if (read == 13) {
                int read2 = this.internalReader.read();
                if (read2 == 10) {
                    int read3 = this.internalReader.read();
                    if (read3 == 46) {
                        int read4 = this.internalReader.read();
                        if (read4 != 46) {
                            this.internalReader.read();
                            this.eof = true;
                        } else {
                            char[] cArr2 = this.internalBuffer;
                            int i3 = this.pos - 1;
                            this.pos = i3;
                            cArr2[i3] = (char) read4;
                        }
                    } else {
                        this.internalReader.unread(read3);
                    }
                    int i4 = this.pos;
                    char[] cArr3 = this.LS_CHARS;
                    int length = i4 - cArr3.length;
                    this.pos = length;
                    System.arraycopy(cArr3, 0, this.internalBuffer, length, cArr3.length);
                    char[] cArr4 = this.internalBuffer;
                    int i5 = this.pos;
                    this.pos = i5 + 1;
                    i2 = cArr4[i5];
                } else if (read2 == 13) {
                    this.internalReader.unread(read2);
                    i2 = read2;
                } else {
                    char[] cArr5 = this.internalBuffer;
                    int i6 = this.pos - 1;
                    this.pos = i6;
                    cArr5[i6] = (char) read2;
                    return 13;
                }
            }
            return i2;
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr) throws IOException {
        return read(cArr, 0, cArr.length);
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        synchronized (this.lock) {
            if (i2 < 1) {
                return 0;
            }
            int read = read();
            if (read == -1) {
                return -1;
            }
            int i4 = i;
            while (true) {
                i3 = i4 + 1;
                cArr[i4] = (char) read;
                i2--;
                if (i2 <= 0 || (read = read()) == -1) {
                    break;
                }
                i4 = i3;
            }
            return i3 - i;
        }
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        boolean z;
        synchronized (this.lock) {
            z = this.pos < this.internalBuffer.length || this.internalReader.ready();
        }
        return z;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.lock) {
            if (this.internalReader == null) {
                return;
            }
            if (!this.eof) {
                do {
                } while (read() != -1);
            }
            this.eof = true;
            this.atBeginning = false;
            this.pos = this.internalBuffer.length;
            this.internalReader = null;
        }
    }
}
