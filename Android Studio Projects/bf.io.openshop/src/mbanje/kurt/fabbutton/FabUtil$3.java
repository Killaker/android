package mbanje.kurt.fabbutton;

import android.view.*;
import android.animation.*;

static final class FabUtil$3 implements ValueAnimator$AnimatorUpdateListener {
    final /* synthetic */ OnFabValueCallback val$callback;
    final /* synthetic */ View val$view;
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.val$callback.onIndeterminateValuesChanged((float)valueAnimator.getAnimatedValue(), -1.0f, -1.0f, -1.0f);
        this.val$view.invalidate();
    }
}