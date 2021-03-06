package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.*;
import java.util.concurrent.*;

public abstract class zzm
{
    private static final ExecutorService zzaiv;
    
    static {
        zzaiv = Executors.newFixedThreadPool(2, new zznk("GAC_Executor"));
    }
    
    public static ExecutorService zzpN() {
        return zzm.zzaiv;
    }
}
