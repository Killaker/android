package android.support.v7.app;

import android.content.*;
import android.app.*;
import android.util.*;
import android.view.*;
import android.content.res.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.widget.*;
import android.support.v7.view.*;

public abstract class AppCompatDelegate
{
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_SUPPORT_ACTION_BAR = 108;
    public static final int FEATURE_SUPPORT_ACTION_BAR_OVERLAY = 109;
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_FOLLOW_SYSTEM = -1;
    public static final int MODE_NIGHT_NO = 1;
    static final int MODE_NIGHT_UNSPECIFIED = -100;
    public static final int MODE_NIGHT_YES = 2;
    static final String TAG = "AppCompatDelegate";
    private static int sDefaultNightMode;
    
    static {
        AppCompatDelegate.sDefaultNightMode = -1;
    }
    
    public static AppCompatDelegate create(final Activity activity, final AppCompatCallback appCompatCallback) {
        return create((Context)activity, activity.getWindow(), appCompatCallback);
    }
    
    public static AppCompatDelegate create(final Dialog dialog, final AppCompatCallback appCompatCallback) {
        return create(dialog.getContext(), dialog.getWindow(), appCompatCallback);
    }
    
    private static AppCompatDelegate create(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 23) {
            return new AppCompatDelegateImplV23(context, window, appCompatCallback);
        }
        if (sdk_INT >= 14) {
            return new AppCompatDelegateImplV14(context, window, appCompatCallback);
        }
        if (sdk_INT >= 11) {
            return new AppCompatDelegateImplV11(context, window, appCompatCallback);
        }
        return new AppCompatDelegateImplV7(context, window, appCompatCallback);
    }
    
    public static int getDefaultNightMode() {
        return AppCompatDelegate.sDefaultNightMode;
    }
    
    public static void setDefaultNightMode(final int sDefaultNightMode) {
        switch (sDefaultNightMode) {
            default: {
                Log.d("AppCompatDelegate", "setDefaultNightMode() called with an unknown mode");
            }
            case -1:
            case 0:
            case 1:
            case 2: {
                AppCompatDelegate.sDefaultNightMode = sDefaultNightMode;
            }
        }
    }
    
    public abstract void addContentView(final View p0, final ViewGroup$LayoutParams p1);
    
    public abstract boolean applyDayNight();
    
    public abstract View createView(@Nullable final View p0, final String p1, @NonNull final Context p2, @NonNull final AttributeSet p3);
    
    @Nullable
    public abstract View findViewById(@IdRes final int p0);
    
    @Nullable
    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
    
    public abstract MenuInflater getMenuInflater();
    
    @Nullable
    public abstract ActionBar getSupportActionBar();
    
    public abstract boolean hasWindowFeature(final int p0);
    
    public abstract void installViewFactory();
    
    public abstract void invalidateOptionsMenu();
    
    public abstract boolean isHandleNativeActionModesEnabled();
    
    public abstract void onConfigurationChanged(final Configuration p0);
    
    public abstract void onCreate(final Bundle p0);
    
    public abstract void onDestroy();
    
    public abstract void onPostCreate(final Bundle p0);
    
    public abstract void onPostResume();
    
    public abstract void onSaveInstanceState(final Bundle p0);
    
    public abstract void onStop();
    
    public abstract boolean requestWindowFeature(final int p0);
    
    public abstract void setContentView(@LayoutRes final int p0);
    
    public abstract void setContentView(final View p0);
    
    public abstract void setContentView(final View p0, final ViewGroup$LayoutParams p1);
    
    public abstract void setHandleNativeActionModesEnabled(final boolean p0);
    
    public abstract void setLocalNightMode(final int p0);
    
    public abstract void setSupportActionBar(@Nullable final Toolbar p0);
    
    public abstract void setTitle(@Nullable final CharSequence p0);
    
    @Nullable
    public abstract ActionMode startSupportActionMode(@NonNull final ActionMode.Callback p0);
}
