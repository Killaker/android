package android.support.v7.widget;

import android.content.*;
import android.util.*;
import android.os.*;
import android.graphics.drawable.*;
import android.widget.*;
import android.support.v7.view.menu.*;
import android.view.*;
import android.support.v4.view.*;

public interface DecorToolbar
{
    void animateToVisibility(final int p0);
    
    boolean canShowOverflowMenu();
    
    void collapseActionView();
    
    void dismissPopupMenus();
    
    Context getContext();
    
    View getCustomView();
    
    int getDisplayOptions();
    
    int getDropdownItemCount();
    
    int getDropdownSelectedPosition();
    
    int getHeight();
    
    Menu getMenu();
    
    int getNavigationMode();
    
    CharSequence getSubtitle();
    
    CharSequence getTitle();
    
    ViewGroup getViewGroup();
    
    int getVisibility();
    
    boolean hasEmbeddedTabs();
    
    boolean hasExpandedActionView();
    
    boolean hasIcon();
    
    boolean hasLogo();
    
    boolean hideOverflowMenu();
    
    void initIndeterminateProgress();
    
    void initProgress();
    
    boolean isOverflowMenuShowPending();
    
    boolean isOverflowMenuShowing();
    
    boolean isTitleTruncated();
    
    void restoreHierarchyState(final SparseArray<Parcelable> p0);
    
    void saveHierarchyState(final SparseArray<Parcelable> p0);
    
    void setBackgroundDrawable(final Drawable p0);
    
    void setCollapsible(final boolean p0);
    
    void setCustomView(final View p0);
    
    void setDefaultNavigationContentDescription(final int p0);
    
    void setDefaultNavigationIcon(final Drawable p0);
    
    void setDisplayOptions(final int p0);
    
    void setDropdownParams(final SpinnerAdapter p0, final AdapterView$OnItemSelectedListener p1);
    
    void setDropdownSelectedPosition(final int p0);
    
    void setEmbeddedTabView(final ScrollingTabContainerView p0);
    
    void setHomeButtonEnabled(final boolean p0);
    
    void setIcon(final int p0);
    
    void setIcon(final Drawable p0);
    
    void setLogo(final int p0);
    
    void setLogo(final Drawable p0);
    
    void setMenu(final Menu p0, final MenuPresenter.Callback p1);
    
    void setMenuCallbacks(final MenuPresenter.Callback p0, final MenuBuilder.Callback p1);
    
    void setMenuPrepared();
    
    void setNavigationContentDescription(final int p0);
    
    void setNavigationContentDescription(final CharSequence p0);
    
    void setNavigationIcon(final int p0);
    
    void setNavigationIcon(final Drawable p0);
    
    void setNavigationMode(final int p0);
    
    void setSubtitle(final CharSequence p0);
    
    void setTitle(final CharSequence p0);
    
    void setVisibility(final int p0);
    
    void setWindowCallback(final Window$Callback p0);
    
    void setWindowTitle(final CharSequence p0);
    
    ViewPropertyAnimatorCompat setupAnimatorToVisibility(final int p0, final long p1);
    
    boolean showOverflowMenu();
}
