package android.support.v7.view.menu;

import android.view.*;
import android.content.*;
import android.os.*;

public interface MenuPresenter
{
    boolean collapseItemActionView(final MenuBuilder p0, final MenuItemImpl p1);
    
    boolean expandItemActionView(final MenuBuilder p0, final MenuItemImpl p1);
    
    boolean flagActionItems();
    
    int getId();
    
    MenuView getMenuView(final ViewGroup p0);
    
    void initForMenu(final Context p0, final MenuBuilder p1);
    
    void onCloseMenu(final MenuBuilder p0, final boolean p1);
    
    void onRestoreInstanceState(final Parcelable p0);
    
    Parcelable onSaveInstanceState();
    
    boolean onSubMenuSelected(final SubMenuBuilder p0);
    
    void setCallback(final Callback p0);
    
    void updateMenuView(final boolean p0);
    
    public interface Callback
    {
        void onCloseMenu(final MenuBuilder p0, final boolean p1);
        
        boolean onOpenSubMenu(final MenuBuilder p0);
    }
}
