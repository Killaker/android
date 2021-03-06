package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.*;
import android.os.*;

public abstract static class zza extends Binder implements zzb
{
    public static zzb zzde(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzb) {
            return (zzb)queryLocalInterface;
        }
        return new zzb.zza.zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.ICircleDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.remove();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final String id = this.getId();
                parcel2.writeNoException();
                parcel2.writeString(id);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                LatLng zzfz;
                if (parcel.readInt() != 0) {
                    zzfz = LatLng.CREATOR.zzfz(parcel);
                }
                else {
                    zzfz = null;
                }
                this.setCenter(zzfz);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final LatLng center = this.getCenter();
                parcel2.writeNoException();
                if (center != null) {
                    parcel2.writeInt(1);
                    center.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setRadius(parcel.readDouble());
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final double radius = this.getRadius();
                parcel2.writeNoException();
                parcel2.writeDouble(radius);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setStrokeWidth(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final float strokeWidth = this.getStrokeWidth();
                parcel2.writeNoException();
                parcel2.writeFloat(strokeWidth);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setStrokeColor(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final int strokeColor = this.getStrokeColor();
                parcel2.writeNoException();
                parcel2.writeInt(strokeColor);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setFillColor(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final int fillColor = this.getFillColor();
                parcel2.writeNoException();
                parcel2.writeInt(fillColor);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setZIndex(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final float zIndex = this.getZIndex();
                parcel2.writeNoException();
                parcel2.writeFloat(zIndex);
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final int int1 = parcel.readInt();
                boolean visible = false;
                if (int1 != 0) {
                    visible = true;
                }
                this.setVisible(visible);
                parcel2.writeNoException();
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final boolean visible2 = this.isVisible();
                parcel2.writeNoException();
                int n3 = 0;
                if (visible2) {
                    n3 = 1;
                }
                parcel2.writeInt(n3);
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final boolean zza = this.zza(zzde(parcel.readStrongBinder()));
                parcel2.writeNoException();
                int n4 = 0;
                if (zza) {
                    n4 = 1;
                }
                parcel2.writeInt(n4);
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final int hashCodeRemote = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
        }
    }
    
    private static class zza implements zzb
    {
        private IBinder zzoz;
        
        zza(final IBinder zzoz) {
            this.zzoz = zzoz;
        }
        
        public IBinder asBinder() {
            return this.zzoz;
        }
        
        @Override
        public LatLng getCenter() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(4, obtain, obtain2, 0);
                obtain2.readException();
                LatLng zzfz;
                if (obtain2.readInt() != 0) {
                    zzfz = LatLng.CREATOR.zzfz(obtain2);
                }
                else {
                    zzfz = null;
                }
                return zzfz;
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public int getFillColor() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(12, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public String getId() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public double getRadius() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(6, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readDouble();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public int getStrokeColor() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(10, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public float getStrokeWidth() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(8, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readFloat();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public float getZIndex() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(14, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readFloat();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public int hashCodeRemote() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(18, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public boolean isVisible() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(16, obtain, obtain2, 0);
                obtain2.readException();
                final int int1 = obtain2.readInt();
                boolean b = false;
                if (int1 != 0) {
                    b = true;
                }
                return b;
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void remove() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.zzoz.transact(1, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setCenter(final LatLng latLng) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                if (latLng != null) {
                    obtain.writeInt(1);
                    latLng.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zzoz.transact(3, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setFillColor(final int n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                obtain.writeInt(n);
                this.zzoz.transact(11, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setRadius(final double n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                obtain.writeDouble(n);
                this.zzoz.transact(5, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setStrokeColor(final int n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                obtain.writeInt(n);
                this.zzoz.transact(9, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setStrokeWidth(final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                obtain.writeFloat(n);
                this.zzoz.transact(7, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setVisible(final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                int n = 0;
                if (b) {
                    n = 1;
                }
                obtain.writeInt(n);
                this.zzoz.transact(15, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public void setZIndex(final float n) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                obtain.writeFloat(n);
                this.zzoz.transact(13, obtain, obtain2, 0);
                obtain2.readException();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        @Override
        public boolean zza(final zzb zzb) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                IBinder binder;
                if (zzb != null) {
                    binder = zzb.asBinder();
                }
                else {
                    binder = null;
                }
                obtain.writeStrongBinder(binder);
                this.zzoz.transact(17, obtain, obtain2, 0);
                obtain2.readException();
                final int int1 = obtain2.readInt();
                boolean b = false;
                if (int1 != 0) {
                    b = true;
                }
                return b;
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
