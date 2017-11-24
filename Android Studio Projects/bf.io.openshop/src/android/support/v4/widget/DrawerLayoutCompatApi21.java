package android.support.v4.widget;

import android.view.*;
import android.content.*;
import android.graphics.drawable.*;
import android.content.res.*;

class DrawerLayoutCompatApi21
{
    private static final int[] THEME_ATTRS;
    
    static {
        THEME_ATTRS = new int[] { 16843828 };
    }
    
    public static void applyMarginInsets(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final Object o, final int n) {
        WindowInsets windowInsets = (WindowInsets)o;
        if (n == 3) {
            windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
        }
        else if (n == 5) {
            windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        viewGroup$MarginLayoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
        viewGroup$MarginLayoutParams.topMargin = windowInsets.getSystemWindowInsetTop();
        viewGroup$MarginLayoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
        viewGroup$MarginLayoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom();
    }
    
    public static void configureApplyInsets(final View view) {
        if (view instanceof DrawerLayoutImpl) {
            view.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)new InsetsListener());
            view.setSystemUiVisibility(1280);
        }
    }
    
    public static void dispatchChildInsets(final View view, final Object o, final int n) {
        WindowInsets windowInsets = (WindowInsets)o;
        if (n == 3) {
            windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), 0, windowInsets.getSystemWindowInsetBottom());
        }
        else if (n == 5) {
            windowInsets = windowInsets.replaceSystemWindowInsets(0, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        view.dispatchApplyWindowInsets(windowInsets);
    }
    
    public static Drawable getDefaultStatusBarBackground(final Context context) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(DrawerLayoutCompatApi21.THEME_ATTRS);
        try {
            return obtainStyledAttributes.getDrawable(0);
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    public static int getTopInset(final Object o) {
        if (o != null) {
            return ((WindowInsets)o).getSystemWindowInsetTop();
        }
        return 0;
    }
    
    static class InsetsListener implements View$OnApplyWindowInsetsListener
    {
        public WindowInsets onApplyWindowInsets(final View view, final WindowInsets windowInsets) {
            ((DrawerLayoutImpl)view).setChildInsets(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
            return windowInsets.consumeSystemWindowInsets();
        }
    }
}
