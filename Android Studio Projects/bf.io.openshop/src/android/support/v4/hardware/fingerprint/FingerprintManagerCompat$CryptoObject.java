package android.support.v4.hardware.fingerprint;

import javax.crypto.*;
import java.security.*;

public static class CryptoObject
{
    private final Cipher mCipher;
    private final Mac mMac;
    private final Signature mSignature;
    
    public CryptoObject(final Signature mSignature) {
        this.mSignature = mSignature;
        this.mCipher = null;
        this.mMac = null;
    }
    
    public CryptoObject(final Cipher mCipher) {
        this.mCipher = mCipher;
        this.mSignature = null;
        this.mMac = null;
    }
    
    public CryptoObject(final Mac mMac) {
        this.mMac = mMac;
        this.mCipher = null;
        this.mSignature = null;
    }
    
    public Cipher getCipher() {
        return this.mCipher;
    }
    
    public Mac getMac() {
        return this.mMac;
    }
    
    public Signature getSignature() {
        return this.mSignature;
    }
}
