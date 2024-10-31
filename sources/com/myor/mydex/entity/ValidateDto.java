package com.myor.mydex.entity;

/* loaded from: classes2.dex */
public class ValidateDto {
    private boolean validasi;
    private String validasiMessage;

    public ValidateDto(boolean z, String str) {
        this.validasi = z;
        this.validasiMessage = str;
    }

    public boolean isValidasi() {
        return this.validasi;
    }

    public void setValidasi(boolean z) {
        this.validasi = z;
    }

    public String getValidasiMessage() {
        return this.validasiMessage;
    }

    public void setValidasiMessage(String str) {
        this.validasiMessage = str;
    }
}
