package bf.io.openshop.ux.fragments;

import bf.io.openshop.listeners.*;
import android.view.*;
import bf.io.openshop.ux.*;
import android.support.v4.app.*;

class BannersFragment$3 extends OnSingleClickListener {
    @Override
    public void onSingleClick(final View view) {
        final FragmentActivity activity = BannersFragment.this.getActivity();
        if (activity instanceof MainActivity) {
            final MainActivity mainActivity = (MainActivity)activity;
            if (mainActivity.drawerFragment != null) {
                mainActivity.drawerFragment.toggleDrawerMenu();
            }
        }
    }
}