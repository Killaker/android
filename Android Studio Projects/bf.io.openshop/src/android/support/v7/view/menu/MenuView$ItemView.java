package android.support.v7.view.menu;

import android.graphics.drawable.*;

public interface ItemView
{
    MenuItemImpl getItemData();
    
    void initialize(final MenuItemImpl p0, final int p1);
    
    boolean prefersCondensedTitle();
    
    void setCheckable(final boolean p0);
    
    void setChecked(final boolean p0);
    
    void setEnabled(final boolean p0);
    
    void setIcon(final Drawable p0);
    
    void setShortcut(final boolean p0, final char p1);
    
    void setTitle(final CharSequence p0);
    
    boolean showsIcon();
}
