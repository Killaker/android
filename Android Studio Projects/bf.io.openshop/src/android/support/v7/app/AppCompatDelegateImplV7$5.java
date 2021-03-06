package android.support.v7.app;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.support.v7.appcompat.*;
import android.text.*;
import android.support.v7.view.*;
import android.support.v7.view.menu.*;
import android.graphics.drawable.*;
import android.view.accessibility.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.support.v4.view.*;

class AppCompatDelegateImplV7$5 implements Runnable {
    @Override
    public void run() {
        AppCompatDelegateImplV7.this.mActionModePopup.showAtLocation((View)AppCompatDelegateImplV7.this.mActionModeView, 55, 0, 0);
        AppCompatDelegateImplV7.access$500(AppCompatDelegateImplV7.this);
        ViewCompat.setAlpha((View)AppCompatDelegateImplV7.this.mActionModeView, 0.0f);
        (AppCompatDelegateImplV7.this.mFadeAnim = ViewCompat.animate((View)AppCompatDelegateImplV7.this.mActionModeView).alpha(1.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final View view) {
                ViewCompat.setAlpha((View)AppCompatDelegateImplV7.this.mActionModeView, 1.0f);
                AppCompatDelegateImplV7.this.mFadeAnim.setListener(null);
                AppCompatDelegateImplV7.this.mFadeAnim = null;
            }
            
            @Override
            public void onAnimationStart(final View view) {
                AppCompatDelegateImplV7.this.mActionModeView.setVisibility(0);
            }
        });
    }
}