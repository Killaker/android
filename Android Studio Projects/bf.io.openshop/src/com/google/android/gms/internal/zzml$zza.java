package com.google.android.gms.internal;

import android.os.*;

public abstract static class zza extends Binder implements zzml
{
    public static zzml zzaY(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzml) {
            return (zzml)queryLocalInterface;
        }
        return new zzml.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.common.internal.service.ICommonService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.common.internal.service.ICommonService");
                this.zza(zzmk.zza.zzaX(parcel.readStrongBinder()));
                return true;
            }
        }
    }
    
    private static class zza implements zzml
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public void zza(final zzmk zzmk) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonService");
                IBinder binder = null;
                if (zzmk != null) {
                    binder = zzmk.asBinder();
                }
                obtain.writeStrongBinder(binder);
                this.zzoz.transact(1, obtain, (Parcel)null, 1);
            }
            finally {
                obtain.recycle();
            }
        }
    }
}
