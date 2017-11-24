package com.google.android.gms.common.internal;

import android.os.*;

public interface zzr extends IInterface
{
    void zza(final int p0, final IBinder p1, final Bundle p2) throws RemoteException;
    
    void zzb(final int p0, final Bundle p1) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzr
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.IGmsCallbacks");
        }
        
        public static zzr zzaR(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof zzr) {
                return (zzr)queryLocalInterface;
            }
            return new zzr.zza.zza(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.IGmsCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                    final int int1 = parcel.readInt();
                    final IBinder strongBinder = parcel.readStrongBinder();
                    final int int2 = parcel.readInt();
                    Bundle bundle = null;
                    if (int2 != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(int1, strongBinder, bundle);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                    final int int3 = parcel.readInt();
                    final int int4 = parcel.readInt();
                    Bundle bundle2 = null;
                    if (int4 != 0) {
                        bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.zzb(int3, bundle2);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class zza implements zzr
        {
            private IBinder zzoz;
            
            zza(final IBinder zzoz) {
                this.zzoz = zzoz;
            }
            
            public IBinder asBinder() {
                return this.zzoz;
            }
            
            @Override
            public void zza(final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsCallbacks");
                    obtain.writeInt(n);
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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
            
            @Override
            public void zzb(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsCallbacks");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzoz.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
