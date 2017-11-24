package android.support.v4.app;

import android.graphics.drawable.*;
import android.view.*;
import android.graphics.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import java.util.*;

public abstract class SharedElementCallback
{
    private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
    private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
    private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
    private static int MAX_IMAGE_SIZE;
    private Matrix mTempMatrix;
    
    static {
        SharedElementCallback.MAX_IMAGE_SIZE = 1048576;
    }
    
    private static Bitmap createDrawableBitmap(final Drawable drawable) {
        final int intrinsicWidth = drawable.getIntrinsicWidth();
        final int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            return null;
        }
        final float min = Math.min(1.0f, SharedElementCallback.MAX_IMAGE_SIZE / (intrinsicWidth * intrinsicHeight));
        if (drawable instanceof BitmapDrawable && min == 1.0f) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        final int n = (int)(min * intrinsicWidth);
        final int n2 = (int)(min * intrinsicHeight);
        final Bitmap bitmap = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        final Rect bounds = drawable.getBounds();
        final int left = bounds.left;
        final int top = bounds.top;
        final int right = bounds.right;
        final int bottom = bounds.bottom;
        drawable.setBounds(0, 0, n, n2);
        drawable.draw(canvas);
        drawable.setBounds(left, top, right, bottom);
        return bitmap;
    }
    
    public Parcelable onCaptureSharedElementSnapshot(final View view, final Matrix matrix, final RectF rectF) {
        if (view instanceof ImageView) {
            final ImageView imageView = (ImageView)view;
            final Drawable drawable = imageView.getDrawable();
            final Drawable background = imageView.getBackground();
            if (drawable != null && background == null) {
                final Bitmap drawableBitmap = createDrawableBitmap(drawable);
                if (drawableBitmap != null) {
                    final Bundle bundle = new Bundle();
                    bundle.putParcelable("sharedElement:snapshot:bitmap", (Parcelable)drawableBitmap);
                    bundle.putString("sharedElement:snapshot:imageScaleType", imageView.getScaleType().toString());
                    if (imageView.getScaleType() == ImageView$ScaleType.MATRIX) {
                        final Matrix imageMatrix = imageView.getImageMatrix();
                        final float[] array = new float[9];
                        imageMatrix.getValues(array);
                        bundle.putFloatArray("sharedElement:snapshot:imageMatrix", array);
                    }
                    return (Parcelable)bundle;
                }
            }
        }
        final int round = Math.round(rectF.width());
        final int round2 = Math.round(rectF.height());
        Object bitmap = null;
        if (round > 0) {
            bitmap = null;
            if (round2 > 0) {
                final float min = Math.min(1.0f, SharedElementCallback.MAX_IMAGE_SIZE / (round * round2));
                final int n = (int)(min * round);
                final int n2 = (int)(min * round2);
                if (this.mTempMatrix == null) {
                    this.mTempMatrix = new Matrix();
                }
                this.mTempMatrix.set(matrix);
                this.mTempMatrix.postTranslate(-rectF.left, -rectF.top);
                this.mTempMatrix.postScale(min, min);
                bitmap = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
                final Canvas canvas = new Canvas((Bitmap)bitmap);
                canvas.concat(this.mTempMatrix);
                view.draw(canvas);
            }
        }
        return (Parcelable)bitmap;
    }
    
    public View onCreateSnapshotView(final Context context, final Parcelable parcelable) {
        ImageView imageView2;
        if (parcelable instanceof Bundle) {
            final Bundle bundle = (Bundle)parcelable;
            final Bitmap imageBitmap = (Bitmap)bundle.getParcelable("sharedElement:snapshot:bitmap");
            if (imageBitmap == null) {
                return null;
            }
            final ImageView imageView = imageView2 = new ImageView(context);
            imageView.setImageBitmap(imageBitmap);
            imageView.setScaleType(ImageView$ScaleType.valueOf(bundle.getString("sharedElement:snapshot:imageScaleType")));
            if (imageView.getScaleType() == ImageView$ScaleType.MATRIX) {
                final float[] floatArray = bundle.getFloatArray("sharedElement:snapshot:imageMatrix");
                final Matrix imageMatrix = new Matrix();
                imageMatrix.setValues(floatArray);
                imageView.setImageMatrix(imageMatrix);
            }
        }
        else {
            final boolean b = parcelable instanceof Bitmap;
            imageView2 = null;
            if (b) {
                final Bitmap imageBitmap2 = (Bitmap)parcelable;
                imageView2 = new ImageView(context);
                imageView2.setImageBitmap(imageBitmap2);
            }
        }
        return (View)imageView2;
    }
    
    public void onMapSharedElements(final List<String> list, final Map<String, View> map) {
    }
    
    public void onRejectSharedElements(final List<View> list) {
    }
    
    public void onSharedElementEnd(final List<String> list, final List<View> list2, final List<View> list3) {
    }
    
    public void onSharedElementStart(final List<String> list, final List<View> list2, final List<View> list3) {
    }
}
