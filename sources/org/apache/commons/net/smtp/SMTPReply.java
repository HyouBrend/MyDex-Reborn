package org.apache.commons.net.smtp;

/* loaded from: classes2.dex */
public final class SMTPReply {
    public static final int ACTION_ABORTED = 451;
    public static final int ACTION_NOT_TAKEN = 450;
    public static final int ACTION_OK = 250;
    public static final int BAD_COMMAND_SEQUENCE = 503;
    public static final int CODE_211 = 211;
    public static final int CODE_214 = 214;
    public static final int CODE_215 = 215;
    public static final int CODE_220 = 220;
    public static final int CODE_221 = 221;
    public static final int CODE_250 = 250;
    public static final int CODE_251 = 251;
    public static final int CODE_354 = 354;
    public static final int CODE_421 = 421;
    public static final int CODE_450 = 450;
    public static final int CODE_451 = 451;
    public static final int CODE_452 = 452;
    public static final int CODE_500 = 500;
    public static final int CODE_501 = 501;
    public static final int CODE_502 = 502;
    public static final int CODE_503 = 503;
    public static final int CODE_504 = 504;
    public static final int CODE_550 = 550;
    public static final int CODE_551 = 551;
    public static final int CODE_552 = 552;
    public static final int CODE_553 = 553;
    public static final int CODE_554 = 554;
    public static final int COMMAND_NOT_IMPLEMENTED = 502;
    public static final int COMMAND_NOT_IMPLEMENTED_FOR_PARAMETER = 504;
    public static final int HELP_MESSAGE = 214;
    public static final int INSUFFICIENT_STORAGE = 452;
    public static final int MAILBOX_NAME_NOT_ALLOWED = 553;
    public static final int MAILBOX_UNAVAILABLE = 550;
    public static final int SERVICE_CLOSING_TRANSMISSION_CHANNEL = 221;
    public static final int SERVICE_NOT_AVAILABLE = 421;
    public static final int SERVICE_READY = 220;
    public static final int START_MAIL_INPUT = 354;
    public static final int STORAGE_ALLOCATION_EXCEEDED = 552;
    public static final int SYNTAX_ERROR_IN_ARGUMENTS = 501;
    public static final int SYSTEM_STATUS = 211;
    public static final int TRANSACTION_FAILED = 554;
    public static final int UNRECOGNIZED_COMMAND = 500;
    public static final int USER_NOT_LOCAL = 551;
    public static final int USER_NOT_LOCAL_WILL_FORWARD = 251;

    public static boolean isNegativePermanent(int i) {
        return i >= 500 && i < 600;
    }

    public static boolean isNegativeTransient(int i) {
        return i >= 400 && i < 500;
    }

    public static boolean isPositiveCompletion(int i) {
        return i >= 200 && i < 300;
    }

    public static boolean isPositiveIntermediate(int i) {
        return i >= 300 && i < 400;
    }

    public static boolean isPositivePreliminary(int i) {
        return i >= 100 && i < 200;
    }

    private SMTPReply() {
    }
}
