package com.facebook.share.widget;

import com.facebook.internal.*;
import android.content.*;
import com.facebook.share.internal.*;

class JoinAppGroupDialog$2 implements Callback {
    final /* synthetic */ ResultProcessor val$resultProcessor;
    
    @Override
    public boolean onActivityResult(final int n, final Intent intent) {
        return ShareInternalUtility.handleActivityResult(JoinAppGroupDialog.this.getRequestCode(), n, intent, this.val$resultProcessor);
    }
}