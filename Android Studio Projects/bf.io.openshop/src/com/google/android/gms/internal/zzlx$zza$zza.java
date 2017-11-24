package com.google.android.gms.internal;

import com.google.android.gms.common.api.*;
import android.os.*;

private static class zza implements zzlx
{
    private IBinder zzoz;
    
    zza(final IBinder zzoz) {
        this.zzoz = zzoz;
    }
    
    public IBinder asBinder() {
        return this.zzoz;
    }
    
    @Override
    public void zzv(final Status status) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
            if (status != null) {
                obtain.writeInt(1);
                status.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zzoz.transact(1, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
}
