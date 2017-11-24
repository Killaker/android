package android.support.design.widget;

import android.widget.*;
import android.content.*;
import android.util.*;

class VisibilityAwareImageButton extends ImageButton
{
    private int mUserSetVisibility;
    
    public VisibilityAwareImageButton(final Context context) {
        this(context, null);
    }
    
    public VisibilityAwareImageButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public VisibilityAwareImageButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mUserSetVisibility = this.getVisibility();
    }
    
    final int getUserSetVisibility() {
        return this.mUserSetVisibility;
    }
    
    final void internalSetVisibility(final int n, final boolean b) {
        super.setVisibility(n);
        if (b) {
            this.mUserSetVisibility = n;
        }
    }
    
    public void setVisibility(final int n) {
        this.internalSetVisibility(n, true);
    }
}
