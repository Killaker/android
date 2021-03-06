package android.support.v7.view;

import android.view.animation.*;
import android.support.v4.view.*;
import android.view.*;
import java.util.*;

public class ViewPropertyAnimatorCompatSet
{
    private final ArrayList<ViewPropertyAnimatorCompat> mAnimators;
    private long mDuration;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    private ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener;
    
    public ViewPropertyAnimatorCompatSet() {
        this.mDuration = -1L;
        this.mProxyListener = new ViewPropertyAnimatorListenerAdapter() {
            private int mProxyEndCount = 0;
            private boolean mProxyStarted = false;
            
            @Override
            public void onAnimationEnd(final View view) {
                final int mProxyEndCount = 1 + this.mProxyEndCount;
                this.mProxyEndCount = mProxyEndCount;
                if (mProxyEndCount == ViewPropertyAnimatorCompatSet.this.mAnimators.size()) {
                    if (ViewPropertyAnimatorCompatSet.this.mListener != null) {
                        ViewPropertyAnimatorCompatSet.this.mListener.onAnimationEnd(null);
                    }
                    this.onEnd();
                }
            }
            
            @Override
            public void onAnimationStart(final View view) {
                if (!this.mProxyStarted) {
                    this.mProxyStarted = true;
                    if (ViewPropertyAnimatorCompatSet.this.mListener != null) {
                        ViewPropertyAnimatorCompatSet.this.mListener.onAnimationStart(null);
                    }
                }
            }
            
            void onEnd() {
                this.mProxyEndCount = 0;
                this.mProxyStarted = false;
                ViewPropertyAnimatorCompatSet.this.onAnimationsEnded();
            }
        };
        this.mAnimators = new ArrayList<ViewPropertyAnimatorCompat>();
    }
    
    private void onAnimationsEnded() {
        this.mIsStarted = false;
    }
    
    public void cancel() {
        if (!this.mIsStarted) {
            return;
        }
        final Iterator<ViewPropertyAnimatorCompat> iterator = this.mAnimators.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel();
        }
        this.mIsStarted = false;
    }
    
    public ViewPropertyAnimatorCompatSet play(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        if (!this.mIsStarted) {
            this.mAnimators.add(viewPropertyAnimatorCompat);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompatSet playSequentially(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2) {
        this.mAnimators.add(viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompat2.setStartDelay(viewPropertyAnimatorCompat.getDuration());
        this.mAnimators.add(viewPropertyAnimatorCompat2);
        return this;
    }
    
    public ViewPropertyAnimatorCompatSet setDuration(final long mDuration) {
        if (!this.mIsStarted) {
            this.mDuration = mDuration;
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompatSet setInterpolator(final Interpolator mInterpolator) {
        if (!this.mIsStarted) {
            this.mInterpolator = mInterpolator;
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompatSet setListener(final ViewPropertyAnimatorListener mListener) {
        if (!this.mIsStarted) {
            this.mListener = mListener;
        }
        return this;
    }
    
    public void start() {
        if (this.mIsStarted) {
            return;
        }
        for (final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat : this.mAnimators) {
            if (this.mDuration >= 0L) {
                viewPropertyAnimatorCompat.setDuration(this.mDuration);
            }
            if (this.mInterpolator != null) {
                viewPropertyAnimatorCompat.setInterpolator(this.mInterpolator);
            }
            if (this.mListener != null) {
                viewPropertyAnimatorCompat.setListener(this.mProxyListener);
            }
            viewPropertyAnimatorCompat.start();
        }
        this.mIsStarted = true;
    }
}
