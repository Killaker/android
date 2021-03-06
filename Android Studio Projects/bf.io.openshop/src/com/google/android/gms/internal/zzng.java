package com.google.android.gms.internal;

public final class zzng
{
    private static int zza(final StackTraceElement[] array, final StackTraceElement[] array2) {
        int n = 0;
        int length = array2.length;
        int length2 = array.length;
        while (--length2 >= 0 && --length >= 0 && array2[length].equals(array[length2])) {
            ++n;
        }
        return n;
    }
    
    public static String zzso() {
        final StringBuilder sb = new StringBuilder();
        final Throwable t = new Throwable();
        final StackTraceElement[] stackTrace = t.getStackTrace();
        sb.append("Async stack trace:");
        for (int length = stackTrace.length, i = 0; i < length; ++i) {
            sb.append("\n\tat ").append(stackTrace[i]);
        }
        final Throwable cause = t.getCause();
        StackTraceElement[] array = stackTrace;
        StackTraceElement[] stackTrace2;
        for (Throwable cause2 = cause; cause2 != null; cause2 = cause2.getCause(), array = stackTrace2) {
            sb.append("\nCaused by: ");
            sb.append(cause2);
            stackTrace2 = cause2.getStackTrace();
            final int zza = zza(stackTrace2, array);
            for (int j = 0; j < stackTrace2.length - zza; ++j) {
                sb.append("\n\tat " + stackTrace2[j]);
            }
            if (zza > 0) {
                sb.append("\n\t... " + zza + " more");
            }
        }
        return sb.toString();
    }
}
