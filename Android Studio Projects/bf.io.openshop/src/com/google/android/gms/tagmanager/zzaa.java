package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.*;
import com.google.android.gms.internal.*;
import android.provider.*;

class zzaa extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbY.toString();
    }
    
    public zzaa(final Context mContext) {
        super(zzaa.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public boolean zzFW() {
        return true;
    }
    
    @Override
    public zzag.zza zzP(final Map<String, zzag.zza> map) {
        final String zzaY = this.zzaY(this.mContext);
        if (zzaY == null) {
            return zzdf.zzHF();
        }
        return zzdf.zzR(zzaY);
    }
    
    protected String zzaY(final Context context) {
        return Settings$Secure.getString(context.getContentResolver(), "android_id");
    }
}
