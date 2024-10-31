package org.apache.commons.net.ftp.parser;

import java.util.Locale;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFileEntryParser;

/* loaded from: classes2.dex */
public class DefaultFTPFileEntryParserFactory implements FTPFileEntryParserFactory {
    private FTPClientConfig config = null;

    @Override // org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory
    public FTPFileEntryParser createFileEntryParser(String str) {
        FTPFileEntryParser createOS400FTPEntryParser;
        FTPFileEntryParser fTPFileEntryParser;
        Class<?> cls;
        if (str == null) {
            throw new ParserInitializationException("Parser key cannot be null");
        }
        try {
            try {
                cls = Class.forName(str);
            } catch (ClassNotFoundException unused) {
                String upperCase = str.toUpperCase(Locale.ENGLISH);
                if (upperCase.indexOf(FTPClientConfig.SYST_UNIX) >= 0) {
                    createOS400FTPEntryParser = createUnixFTPEntryParser();
                } else if (upperCase.indexOf(FTPClientConfig.SYST_VMS) >= 0) {
                    createOS400FTPEntryParser = createVMSVersioningFTPEntryParser();
                } else if (upperCase.indexOf(FTPClientConfig.SYST_NT) >= 0) {
                    createOS400FTPEntryParser = createNTFTPEntryParser();
                } else if (upperCase.indexOf(FTPClientConfig.SYST_OS2) >= 0) {
                    createOS400FTPEntryParser = createOS2FTPEntryParser();
                } else {
                    if (upperCase.indexOf(FTPClientConfig.SYST_OS400) < 0 && upperCase.indexOf(FTPClientConfig.SYST_AS400) < 0) {
                        if (upperCase.indexOf(FTPClientConfig.SYST_MVS) >= 0) {
                            createOS400FTPEntryParser = createMVSEntryParser();
                        } else if (upperCase.indexOf(FTPClientConfig.SYST_NETWARE) >= 0) {
                            createOS400FTPEntryParser = createNetwareFTPEntryParser();
                        } else if (upperCase.indexOf(FTPClientConfig.SYST_L8) >= 0) {
                            createOS400FTPEntryParser = createUnixFTPEntryParser();
                        } else {
                            throw new ParserInitializationException("Unknown parser type: " + str);
                        }
                    }
                    createOS400FTPEntryParser = createOS400FTPEntryParser();
                }
                fTPFileEntryParser = createOS400FTPEntryParser;
            } catch (NoClassDefFoundError e) {
                throw new ParserInitializationException("Error initializing parser", e);
            } catch (ParserInitializationException e2) {
                throw e2;
            } catch (Throwable th) {
                throw new ParserInitializationException("Error initializing parser", th);
            }
            try {
                fTPFileEntryParser = (FTPFileEntryParser) cls.newInstance();
                if (fTPFileEntryParser instanceof Configurable) {
                    ((Configurable) fTPFileEntryParser).configure(this.config);
                }
                return fTPFileEntryParser;
            } catch (ClassCastException e3) {
                throw new ParserInitializationException(cls.getName() + " does not implement the interface org.apache.commons.net.ftp.FTPFileEntryParser.", e3);
            }
        } catch (NoClassDefFoundError e4) {
            throw new ParserInitializationException("Error initializing parser", e4);
        }
    }

    @Override // org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory
    public FTPFileEntryParser createFileEntryParser(FTPClientConfig fTPClientConfig) throws ParserInitializationException {
        this.config = fTPClientConfig;
        return createFileEntryParser(fTPClientConfig.getServerSystemKey());
    }

    public FTPFileEntryParser createUnixFTPEntryParser() {
        return new UnixFTPEntryParser();
    }

    public FTPFileEntryParser createVMSVersioningFTPEntryParser() {
        return new VMSVersioningFTPEntryParser();
    }

    public FTPFileEntryParser createNetwareFTPEntryParser() {
        return new NetwareFTPEntryParser();
    }

    public FTPFileEntryParser createNTFTPEntryParser() {
        FTPClientConfig fTPClientConfig = this.config;
        if (fTPClientConfig != null && FTPClientConfig.SYST_NT.equals(fTPClientConfig.getServerSystemKey())) {
            return new NTFTPEntryParser();
        }
        return new CompositeFileEntryParser(new FTPFileEntryParser[]{new NTFTPEntryParser(), new UnixFTPEntryParser()});
    }

    public FTPFileEntryParser createOS2FTPEntryParser() {
        return new OS2FTPEntryParser();
    }

    public FTPFileEntryParser createOS400FTPEntryParser() {
        FTPClientConfig fTPClientConfig = this.config;
        if (fTPClientConfig != null && FTPClientConfig.SYST_OS400.equals(fTPClientConfig.getServerSystemKey())) {
            return new OS400FTPEntryParser();
        }
        return new CompositeFileEntryParser(new FTPFileEntryParser[]{new OS400FTPEntryParser(), new UnixFTPEntryParser()});
    }

    public FTPFileEntryParser createMVSEntryParser() {
        return new MVSFTPEntryParser();
    }
}
