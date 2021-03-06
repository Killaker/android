package com.google.android.gms.common.internal.safeparcel;

import android.os.*;
import java.util.*;

public class zzb
{
    private static int zzG(final Parcel parcel, final int n) {
        parcel.writeInt(0xFFFF0000 | n);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }
    
    private static void zzH(final Parcel parcel, final int n) {
        final int dataPosition = parcel.dataPosition();
        final int n2 = dataPosition - n;
        parcel.setDataPosition(n - 4);
        parcel.writeInt(n2);
        parcel.setDataPosition(dataPosition);
    }
    
    public static void zzI(final Parcel parcel, final int n) {
        zzH(parcel, n);
    }
    
    public static void zza(final Parcel parcel, final int n, final byte b) {
        zzb(parcel, n, 4);
        parcel.writeInt((int)b);
    }
    
    public static void zza(final Parcel parcel, final int n, final double n2) {
        zzb(parcel, n, 8);
        parcel.writeDouble(n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final float n2) {
        zzb(parcel, n, 4);
        parcel.writeFloat(n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final long n2) {
        zzb(parcel, n, 8);
        parcel.writeLong(n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final Bundle bundle, final boolean b) {
        if (bundle == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeBundle(bundle);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final IBinder binder, final boolean b) {
        if (binder == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeStrongBinder(binder);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final Parcel parcel2, final boolean b) {
        if (parcel2 == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.appendFrom(parcel2, 0, parcel2.dataSize());
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final Parcelable parcelable, final int n2, final boolean b) {
        if (parcelable == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcelable.writeToParcel(parcel, n2);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final Boolean b, final boolean b2) {
        if (b == null) {
            if (b2) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 4);
        final boolean booleanValue = b;
        int n2 = 0;
        if (booleanValue) {
            n2 = 1;
        }
        parcel.writeInt(n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final Float n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 4);
        parcel.writeFloat((float)n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final Integer n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 4);
        parcel.writeInt((int)n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final Long n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 8);
        parcel.writeLong((long)n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final String s, final boolean b) {
        if (s == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeString(s);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final List<Integer> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        final int size = list.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; ++i) {
            parcel.writeInt((int)list.get(i));
        }
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final short n2) {
        zzb(parcel, n, 4);
        parcel.writeInt((int)n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final boolean b) {
        zzb(parcel, n, 4);
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        parcel.writeInt(n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final byte[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeByteArray(array);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final float[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeFloatArray(array);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final int[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeIntArray(array);
        zzH(parcel, zzG);
    }
    
    public static <T extends Parcelable> void zza(final Parcel parcel, final int n, final T[] array, final int n2, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        final int length = array.length;
        parcel.writeInt(length);
        for (final Parcelable parcelable : array) {
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                zza(parcel, parcelable, n2);
            }
        }
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final String[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeStringArray(array);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final boolean[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeBooleanArray(array);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, final int n, final byte[][] array, final boolean b) {
        int i = 0;
        if (array == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        final int length = array.length;
        parcel.writeInt(length);
        while (i < length) {
            parcel.writeByteArray(array[i]);
            ++i;
        }
        zzH(parcel, zzG);
    }
    
    private static <T extends Parcelable> void zza(final Parcel parcel, final T t, final int n) {
        final int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        final int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, n);
        final int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }
    
    public static int zzav(final Parcel parcel) {
        return zzG(parcel, 20293);
    }
    
    private static void zzb(final Parcel parcel, final int n, final int n2) {
        if (n2 >= 65535) {
            parcel.writeInt(0xFFFF0000 | n);
            parcel.writeInt(n2);
            return;
        }
        parcel.writeInt(n | n2 << 16);
    }
    
    public static void zzb(final Parcel parcel, final int n, final List<String> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeStringList((List)list);
        zzH(parcel, zzG);
    }
    
    public static void zzc(final Parcel parcel, final int n, final int n2) {
        zzb(parcel, n, 4);
        parcel.writeInt(n2);
    }
    
    public static <T extends Parcelable> void zzc(final Parcel parcel, final int n, final List<T> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        final int size = list.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; ++i) {
            final Parcelable parcelable = list.get(i);
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                zza(parcel, parcelable, 0);
            }
        }
        zzH(parcel, zzG);
    }
    
    public static void zzd(final Parcel parcel, final int n, final List list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, n);
        parcel.writeList(list);
        zzH(parcel, zzG);
    }
}
