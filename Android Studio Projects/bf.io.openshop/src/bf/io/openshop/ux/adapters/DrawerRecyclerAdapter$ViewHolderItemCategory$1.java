package bf.io.openshop.ux.adapters;

import bf.io.openshop.listeners.*;
import bf.io.openshop.interfaces.*;
import android.view.*;

class DrawerRecyclerAdapter$ViewHolderItemCategory$1 extends OnSingleClickListener {
    final /* synthetic */ DrawerRecyclerInterface val$drawerRecyclerInterface;
    
    @Override
    public void onSingleClick(final View view) {
        this.val$drawerRecyclerInterface.onCategorySelected(view, ViewHolderItemCategory.access$200(ViewHolderItemCategory.this));
    }
}