package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

private static class zza implements zzt
{
    private IBinder zzoz;
    
    zza(final IBinder zzoz) {
        this.zzoz = zzoz;
    }
    
    public IBinder asBinder() {
        return this.zzoz;
    }
    
    @Override
    public void zza(final PointOfInterest pointOfInterest) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnPoiClickListener");
            if (pointOfInterest != null) {
                obtain.writeInt(1);
                pointOfInterest.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zzoz.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
