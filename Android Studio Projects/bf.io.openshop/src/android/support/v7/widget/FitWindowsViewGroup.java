package android.support.v7.widget;

import android.graphics.*;

public interface FitWindowsViewGroup
{
    void setOnFitSystemWindowsListener(final OnFitSystemWindowsListener p0);
    
    public interface OnFitSystemWindowsListener
    {
        void onFitSystemWindows(final Rect p0);
    }
}
