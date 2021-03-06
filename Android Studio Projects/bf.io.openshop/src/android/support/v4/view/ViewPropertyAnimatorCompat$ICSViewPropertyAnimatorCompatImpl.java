package android.support.v4.view;

import java.util.*;
import android.view.*;
import android.view.animation.*;
import android.graphics.*;
import android.os.*;

static class ICSViewPropertyAnimatorCompatImpl extends BaseViewPropertyAnimatorCompatImpl
{
    WeakHashMap<View, Integer> mLayerMap;
    
    ICSViewPropertyAnimatorCompatImpl() {
        this.mLayerMap = null;
    }
    
    @Override
    public void alpha(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.alpha(view, n);
    }
    
    @Override
    public void alphaBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.alphaBy(view, n);
    }
    
    @Override
    public void cancel(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        ViewPropertyAnimatorCompatICS.cancel(view);
    }
    
    @Override
    public long getDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return ViewPropertyAnimatorCompatICS.getDuration(view);
    }
    
    @Override
    public long getStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        return ViewPropertyAnimatorCompatICS.getStartDelay(view);
    }
    
    @Override
    public void rotation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.rotation(view, n);
    }
    
    @Override
    public void rotationBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.rotationBy(view, n);
    }
    
    @Override
    public void rotationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.rotationX(view, n);
    }
    
    @Override
    public void rotationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.rotationXBy(view, n);
    }
    
    @Override
    public void rotationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.rotationY(view, n);
    }
    
    @Override
    public void rotationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.rotationYBy(view, n);
    }
    
    @Override
    public void scaleX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.scaleX(view, n);
    }
    
    @Override
    public void scaleXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.scaleXBy(view, n);
    }
    
    @Override
    public void scaleY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.scaleY(view, n);
    }
    
    @Override
    public void scaleYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.scaleYBy(view, n);
    }
    
    @Override
    public void setDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
        ViewPropertyAnimatorCompatICS.setDuration(view, n);
    }
    
    @Override
    public void setInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Interpolator interpolator) {
        ViewPropertyAnimatorCompatICS.setInterpolator(view, interpolator);
    }
    
    @Override
    public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
        ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
    }
    
    @Override
    public void setStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
        ViewPropertyAnimatorCompatICS.setStartDelay(view, n);
    }
    
    @Override
    public void start(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        ViewPropertyAnimatorCompatICS.start(view);
    }
    
    @Override
    public void translationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.translationX(view, n);
    }
    
    @Override
    public void translationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.translationXBy(view, n);
    }
    
    @Override
    public void translationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.translationY(view, n);
    }
    
    @Override
    public void translationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.translationYBy(view, n);
    }
    
    @Override
    public void withEndAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable runnable) {
        ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
        ViewPropertyAnimatorCompat.access$002(viewPropertyAnimatorCompat, runnable);
    }
    
    @Override
    public void withLayer(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        ViewPropertyAnimatorCompat.access$402(viewPropertyAnimatorCompat, ViewCompat.getLayerType(view));
        ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
    }
    
    @Override
    public void withStartAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable runnable) {
        ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
        ViewPropertyAnimatorCompat.access$102(viewPropertyAnimatorCompat, runnable);
    }
    
    @Override
    public void x(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.x(view, n);
    }
    
    @Override
    public void xBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.xBy(view, n);
    }
    
    @Override
    public void y(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.y(view, n);
    }
    
    @Override
    public void yBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        ViewPropertyAnimatorCompatICS.yBy(view, n);
    }
    
    static class MyVpaListener implements ViewPropertyAnimatorListener
    {
        boolean mAnimEndCalled;
        ViewPropertyAnimatorCompat mVpa;
        
        MyVpaListener(final ViewPropertyAnimatorCompat mVpa) {
            this.mVpa = mVpa;
        }
        
        @Override
        public void onAnimationCancel(final View view) {
            final Object tag = view.getTag(2113929216);
            final boolean b = tag instanceof ViewPropertyAnimatorListener;
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
            if (b) {
                viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
            }
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationCancel(view);
            }
        }
        
        @Override
        public void onAnimationEnd(final View view) {
            if (ViewPropertyAnimatorCompat.access$400(this.mVpa) >= 0) {
                ViewCompat.setLayerType(view, ViewPropertyAnimatorCompat.access$400(this.mVpa), null);
                ViewPropertyAnimatorCompat.access$402(this.mVpa, -1);
            }
            if (Build$VERSION.SDK_INT >= 16 || !this.mAnimEndCalled) {
                if (ViewPropertyAnimatorCompat.access$000(this.mVpa) != null) {
                    final Runnable access$000 = ViewPropertyAnimatorCompat.access$000(this.mVpa);
                    ViewPropertyAnimatorCompat.access$002(this.mVpa, null);
                    access$000.run();
                }
                final Object tag = view.getTag(2113929216);
                final boolean b = tag instanceof ViewPropertyAnimatorListener;
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
                if (b) {
                    viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
                }
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationEnd(view);
                }
                this.mAnimEndCalled = true;
            }
        }
        
        @Override
        public void onAnimationStart(final View view) {
            this.mAnimEndCalled = false;
            if (ViewPropertyAnimatorCompat.access$400(this.mVpa) >= 0) {
                ViewCompat.setLayerType(view, 2, null);
            }
            if (ViewPropertyAnimatorCompat.access$100(this.mVpa) != null) {
                final Runnable access$100 = ViewPropertyAnimatorCompat.access$100(this.mVpa);
                ViewPropertyAnimatorCompat.access$102(this.mVpa, null);
                access$100.run();
            }
            final Object tag = view.getTag(2113929216);
            final boolean b = tag instanceof ViewPropertyAnimatorListener;
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
            if (b) {
                viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
            }
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationStart(view);
            }
        }
    }
}
