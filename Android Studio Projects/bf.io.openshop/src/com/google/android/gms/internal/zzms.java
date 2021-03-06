package com.google.android.gms.internal;

import android.database.*;
import android.text.*;

public final class zzms
{
    public static void zzb(final String s, final CharArrayBuffer charArrayBuffer) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            charArrayBuffer.sizeCopied = 0;
        }
        else if (charArrayBuffer.data == null || charArrayBuffer.data.length < s.length()) {
            charArrayBuffer.data = s.toCharArray();
        }
        else {
            s.getChars(0, s.length(), charArrayBuffer.data, 0);
        }
        charArrayBuffer.sizeCopied = s.length();
    }
}
