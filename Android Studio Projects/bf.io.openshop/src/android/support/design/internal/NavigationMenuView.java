package android.support.design.internal;

import android.content.*;
import android.util.*;
import android.support.v7.widget.*;
import android.support.v7.view.menu.*;

public class NavigationMenuView extends RecyclerView implements MenuView
{
    public NavigationMenuView(final Context context) {
        this(context, null);
    }
    
    public NavigationMenuView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NavigationMenuView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setLayoutManager((LayoutManager)new LinearLayoutManager(context, 1, false));
    }
    
    @Override
    public int getWindowAnimations() {
        return 0;
    }
    
    @Override
    public void initialize(final MenuBuilder menuBuilder) {
    }
}
