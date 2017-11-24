package android.support.design.widget;

import android.view.*;
import android.content.res.*;
import android.support.annotation.*;
import android.support.design.*;
import android.graphics.*;
import android.support.v4.graphics.drawable.*;
import android.graphics.drawable.*;
import android.view.animation.*;

class FloatingActionButtonEclairMr1 extends FloatingActionButtonImpl
{
    private int mAnimationDuration;
    private boolean mIsHiding;
    ShadowDrawableWrapper mShadowDrawable;
    private StateListAnimator mStateListAnimator;
    
    FloatingActionButtonEclairMr1(final VisibilityAwareImageButton target, final ShadowViewDelegate shadowViewDelegate) {
        super(target, shadowViewDelegate);
        this.mAnimationDuration = target.getResources().getInteger(17694720);
        (this.mStateListAnimator = new StateListAnimator()).setTarget((View)target);
        this.mStateListAnimator.addState(FloatingActionButtonEclairMr1.PRESSED_ENABLED_STATE_SET, this.setupAnimation(new ElevateToTranslationZAnimation()));
        this.mStateListAnimator.addState(FloatingActionButtonEclairMr1.FOCUSED_ENABLED_STATE_SET, this.setupAnimation(new ElevateToTranslationZAnimation()));
        this.mStateListAnimator.addState(FloatingActionButtonEclairMr1.EMPTY_STATE_SET, this.setupAnimation(new ResetElevationAnimation()));
    }
    
    private static ColorStateList createColorStateList(final int n) {
        final int[][] array = new int[3][];
        final int[] array2 = new int[3];
        array[0] = FloatingActionButtonEclairMr1.FOCUSED_ENABLED_STATE_SET;
        array2[0] = n;
        final int n2 = 0 + 1;
        array[n2] = FloatingActionButtonEclairMr1.PRESSED_ENABLED_STATE_SET;
        array2[n2] = n;
        final int n3 = n2 + 1;
        array[n3] = new int[0];
        array2[n3] = 0;
        return new ColorStateList(array, array2);
    }
    
    private Animation setupAnimation(final Animation animation) {
        animation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        animation.setDuration((long)this.mAnimationDuration);
        return animation;
    }
    
    @Override
    float getElevation() {
        return this.mElevation;
    }
    
    @Override
    void getPadding(final Rect rect) {
        this.mShadowDrawable.getPadding(rect);
    }
    
    @Override
    void hide(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean b) {
        if (this.mIsHiding || this.mView.getVisibility() != 0) {
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
            }
            return;
        }
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.design_fab_out);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        loadAnimation.setDuration(200L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new AnimationUtils.AnimationListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animation animation) {
                FloatingActionButtonEclairMr1.this.mIsHiding = false;
                FloatingActionButtonEclairMr1.this.mView.internalSetVisibility(8, b);
                if (internalVisibilityChangedListener != null) {
                    internalVisibilityChangedListener.onHidden();
                }
            }
            
            @Override
            public void onAnimationStart(final Animation animation) {
                FloatingActionButtonEclairMr1.this.mIsHiding = true;
            }
        });
        this.mView.startAnimation(loadAnimation);
    }
    
    @Override
    void jumpDrawableToCurrentState() {
        this.mStateListAnimator.jumpToCurrentState();
    }
    
    @Override
    void onCompatShadowChanged() {
    }
    
    @Override
    void onDrawableStateChanged(final int[] state) {
        this.mStateListAnimator.setState(state);
    }
    
    @Override
    void onElevationChanged(final float n) {
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setShadowSize(n, n + this.mPressedTranslationZ);
            this.updatePadding();
        }
    }
    
    @Override
    void onTranslationZChanged(final float n) {
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setMaxShadowSize(n + this.mElevation);
            this.updatePadding();
        }
    }
    
    @Override
    void setBackgroundDrawable(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int n, final int n2) {
        DrawableCompat.setTintList(this.mShapeDrawable = DrawableCompat.wrap((Drawable)this.createShapeDrawable()), list);
        if (porterDuff$Mode != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
        }
        DrawableCompat.setTintList(this.mRippleDrawable = DrawableCompat.wrap((Drawable)this.createShapeDrawable()), createColorStateList(n));
        Drawable[] array;
        if (n2 > 0) {
            this.mBorderDrawable = this.createBorderDrawable(n2, list);
            array = new Drawable[] { this.mBorderDrawable, this.mShapeDrawable, this.mRippleDrawable };
        }
        else {
            this.mBorderDrawable = null;
            array = new Drawable[] { this.mShapeDrawable, this.mRippleDrawable };
        }
        this.mContentBackground = (Drawable)new LayerDrawable(array);
        (this.mShadowDrawable = new ShadowDrawableWrapper(this.mView.getResources(), this.mContentBackground, this.mShadowViewDelegate.getRadius(), this.mElevation, this.mElevation + this.mPressedTranslationZ)).setAddPaddingForCorners(false);
        this.mShadowViewDelegate.setBackgroundDrawable(this.mShadowDrawable);
    }
    
    @Override
    void setBackgroundTintList(final ColorStateList borderTint) {
        if (this.mShapeDrawable != null) {
            DrawableCompat.setTintList(this.mShapeDrawable, borderTint);
        }
        if (this.mBorderDrawable != null) {
            this.mBorderDrawable.setBorderTint(borderTint);
        }
    }
    
    @Override
    void setBackgroundTintMode(final PorterDuff$Mode porterDuff$Mode) {
        if (this.mShapeDrawable != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
        }
    }
    
    @Override
    void setRippleColor(final int n) {
        if (this.mRippleDrawable != null) {
            DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(n));
        }
    }
    
    @Override
    void show(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean b) {
        if (this.mView.getVisibility() != 0 || this.mIsHiding) {
            this.mView.clearAnimation();
            this.mView.internalSetVisibility(0, b);
            final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.design_fab_in);
            loadAnimation.setDuration(200L);
            loadAnimation.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
            loadAnimation.setAnimationListener((Animation$AnimationListener)new AnimationUtils.AnimationListenerAdapter() {
                @Override
                public void onAnimationEnd(final Animation animation) {
                    if (internalVisibilityChangedListener != null) {
                        internalVisibilityChangedListener.onShown();
                    }
                }
            });
            this.mView.startAnimation(loadAnimation);
        }
        else if (internalVisibilityChangedListener != null) {
            internalVisibilityChangedListener.onShown();
        }
    }
    
    private abstract class BaseShadowAnimation extends Animation
    {
        private float mShadowSizeDiff;
        private float mShadowSizeStart;
        
        protected void applyTransformation(final float n, final Transformation transformation) {
            FloatingActionButtonEclairMr1.this.mShadowDrawable.setShadowSize(this.mShadowSizeStart + n * this.mShadowSizeDiff);
        }
        
        protected abstract float getTargetShadowSize();
        
        public void reset() {
            super.reset();
            this.mShadowSizeStart = FloatingActionButtonEclairMr1.this.mShadowDrawable.getShadowSize();
            this.mShadowSizeDiff = this.getTargetShadowSize() - this.mShadowSizeStart;
        }
    }
    
    private class ElevateToTranslationZAnimation extends BaseShadowAnimation
    {
        @Override
        protected float getTargetShadowSize() {
            return FloatingActionButtonEclairMr1.this.mElevation + FloatingActionButtonEclairMr1.this.mPressedTranslationZ;
        }
    }
    
    private class ResetElevationAnimation extends BaseShadowAnimation
    {
        @Override
        protected float getTargetShadowSize() {
            return FloatingActionButtonEclairMr1.this.mElevation;
        }
    }
}
