package android.support.v7.widget;

import java.lang.reflect.*;
import android.content.*;
import android.graphics.drawable.*;
import android.database.*;
import android.graphics.*;
import android.support.v7.appcompat.*;
import android.support.v4.text.*;
import android.content.res.*;
import android.util.*;
import android.support.v4.widget.*;
import android.view.*;
import android.os.*;
import android.widget.*;
import android.support.v4.view.*;

public class ListPopupWindow
{
    private static final boolean DEBUG = false;
    private static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    private DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private boolean mForceIgnoreOutsideTouch;
    private final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private AdapterView$OnItemClickListener mItemClickListener;
    private AdapterView$OnItemSelectedListener mItemSelectedListener;
    private int mLayoutDirection;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    private final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: anewarray       Ljava/lang/Class;
        //     4: astore          5
        //     6: aload           5
        //     8: iconst_0       
        //     9: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    12: aastore        
        //    13: ldc             Landroid/widget/PopupWindow;.class
        //    15: ldc             "setClipToScreenEnabled"
        //    17: aload           5
        //    19: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    22: putstatic       android/support/v7/widget/ListPopupWindow.sClipToWindowEnabledMethod:Ljava/lang/reflect/Method;
        //    25: iconst_3       
        //    26: anewarray       Ljava/lang/Class;
        //    29: astore          4
        //    31: aload           4
        //    33: iconst_0       
        //    34: ldc             Landroid/view/View;.class
        //    36: aastore        
        //    37: aload           4
        //    39: iconst_1       
        //    40: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //    43: aastore        
        //    44: aload           4
        //    46: iconst_2       
        //    47: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    50: aastore        
        //    51: ldc             Landroid/widget/PopupWindow;.class
        //    53: ldc             "getMaxAvailableHeight"
        //    55: aload           4
        //    57: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    60: putstatic       android/support/v7/widget/ListPopupWindow.sGetMaxAvailableHeightMethod:Ljava/lang/reflect/Method;
        //    63: return         
        //    64: astore_0       
        //    65: ldc             "ListPopupWindow"
        //    67: ldc             "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well."
        //    69: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    72: pop            
        //    73: goto            25
        //    76: astore_2       
        //    77: ldc             "ListPopupWindow"
        //    79: ldc             "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well."
        //    81: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    84: pop            
        //    85: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      25     64     76     Ljava/lang/NoSuchMethodException;
        //  25     63     76     86     Ljava/lang/NoSuchMethodException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public ListPopupWindow(final Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }
    
    public ListPopupWindow(final Context context, final AttributeSet set) {
        this(context, set, R.attr.listPopupWindowStyle);
    }
    
    public ListPopupWindow(final Context context, final AttributeSet set, final int n) {
        this(context, set, n, 0);
    }
    
    public ListPopupWindow(final Context mContext, final AttributeSet set, final int n, final int n2) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mTempRect = new Rect();
        this.mContext = mContext;
        this.mHandler = new Handler(mContext.getMainLooper());
        final TypedArray obtainStyledAttributes = mContext.obtainStyledAttributes(set, R.styleable.ListPopupWindow, n, n2);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        (this.mPopup = new AppCompatPopupWindow(mContext, set, n)).setInputMethodMode(1);
        this.mLayoutDirection = TextUtilsCompat.getLayoutDirectionFromLocale(this.mContext.getResources().getConfiguration().locale);
    }
    
    private int buildDropDown() {
        int n;
        if (this.mDropDownList == null) {
            final Context mContext = this.mContext;
            this.mShowDropDownRunnable = new Runnable() {
                @Override
                public void run() {
                    final View anchorView = ListPopupWindow.this.getAnchorView();
                    if (anchorView != null && anchorView.getWindowToken() != null) {
                        ListPopupWindow.this.show();
                    }
                }
            };
            this.mDropDownList = new DropDownListView(mContext, !this.mModal);
            if (this.mDropDownListHighlight != null) {
                this.mDropDownList.setSelector(this.mDropDownListHighlight);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    if (n != -1) {
                        final DropDownListView access$600 = ListPopupWindow.this.mDropDownList;
                        if (access$600 != null) {
                            access$600.mListSelectionHidden = false;
                        }
                    }
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                }
            });
            this.mDropDownList.setOnScrollListener((AbsListView$OnScrollListener)this.mScrollListener);
            if (this.mItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
            }
            Object mDropDownList = this.mDropDownList;
            final View mPromptView = this.mPromptView;
            n = 0;
            if (mPromptView != null) {
                final LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setOrientation(1);
                final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, 0, 1.0f);
                switch (this.mPromptPosition) {
                    default: {
                        Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
                        break;
                    }
                    case 1: {
                        linearLayout.addView((View)mDropDownList, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                        linearLayout.addView(mPromptView);
                        break;
                    }
                    case 0: {
                        linearLayout.addView(mPromptView);
                        linearLayout.addView((View)mDropDownList, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                        break;
                    }
                }
                int n2;
                int mDropDownWidth;
                if (this.mDropDownWidth >= 0) {
                    n2 = Integer.MIN_VALUE;
                    mDropDownWidth = this.mDropDownWidth;
                }
                else {
                    n2 = 0;
                    mDropDownWidth = 0;
                }
                mPromptView.measure(View$MeasureSpec.makeMeasureSpec(mDropDownWidth, n2), 0);
                final LinearLayout$LayoutParams linearLayout$LayoutParams2 = (LinearLayout$LayoutParams)mPromptView.getLayoutParams();
                n = mPromptView.getMeasuredHeight() + linearLayout$LayoutParams2.topMargin + linearLayout$LayoutParams2.bottomMargin;
                mDropDownList = linearLayout;
            }
            this.mPopup.setContentView((View)mDropDownList);
        }
        else {
            final ViewGroup viewGroup = (ViewGroup)this.mPopup.getContentView();
            final View mPromptView2 = this.mPromptView;
            n = 0;
            if (mPromptView2 != null) {
                final LinearLayout$LayoutParams linearLayout$LayoutParams3 = (LinearLayout$LayoutParams)mPromptView2.getLayoutParams();
                n = mPromptView2.getMeasuredHeight() + linearLayout$LayoutParams3.topMargin + linearLayout$LayoutParams3.bottomMargin;
            }
        }
        final Drawable background = this.mPopup.getBackground();
        int n3;
        if (background != null) {
            background.getPadding(this.mTempRect);
            n3 = this.mTempRect.top + this.mTempRect.bottom;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
            }
        }
        else {
            this.mTempRect.setEmpty();
            n3 = 0;
        }
        final int maxAvailableHeight = this.getMaxAvailableHeight(this.getAnchorView(), this.mDropDownVerticalOffset, this.mPopup.getInputMethodMode() == 2);
        if (this.mDropDownAlwaysVisible || this.mDropDownHeight == -1) {
            return maxAvailableHeight + n3;
        }
        int n4 = 0;
        switch (this.mDropDownWidth) {
            default: {
                n4 = View$MeasureSpec.makeMeasureSpec(this.mDropDownWidth, 1073741824);
                break;
            }
            case -2: {
                n4 = View$MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), Integer.MIN_VALUE);
                break;
            }
            case -1: {
                n4 = View$MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), 1073741824);
                break;
            }
        }
        final int measureHeightOfChildrenCompat = this.mDropDownList.measureHeightOfChildrenCompat(n4, 0, -1, maxAvailableHeight - n, -1);
        if (measureHeightOfChildrenCompat > 0) {
            n += n3;
        }
        return measureHeightOfChildrenCompat + n;
    }
    
    private int getMaxAvailableHeight(final View view, final int n, final boolean b) {
        if (ListPopupWindow.sGetMaxAvailableHeightMethod != null) {
            try {
                return (int)ListPopupWindow.sGetMaxAvailableHeightMethod.invoke(this.mPopup, view, n, b);
            }
            catch (Exception ex) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.mPopup.getMaxAvailableHeight(view, n);
    }
    
    private static boolean isConfirmKey(final int n) {
        return n == 66 || n == 23;
    }
    
    private void removePromptView() {
        if (this.mPromptView != null) {
            final ViewParent parent = this.mPromptView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(this.mPromptView);
            }
        }
    }
    
    private void setPopupClipToScreenEnabled(final boolean b) {
        if (ListPopupWindow.sClipToWindowEnabledMethod == null) {
            return;
        }
        try {
            ListPopupWindow.sClipToWindowEnabledMethod.invoke(this.mPopup, b);
        }
        catch (Exception ex) {
            Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
    }
    
    public void clearListSelection() {
        final DropDownListView mDropDownList = this.mDropDownList;
        if (mDropDownList != null) {
            mDropDownList.mListSelectionHidden = true;
            mDropDownList.requestLayout();
        }
    }
    
    public View$OnTouchListener createDragToOpenListener(final View view) {
        return (View$OnTouchListener)new ForwardingListener(view) {
            @Override
            public ListPopupWindow getPopup() {
                return ListPopupWindow.this;
            }
        };
    }
    
    public void dismiss() {
        this.mPopup.dismiss();
        this.removePromptView();
        this.mPopup.setContentView((View)null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks((Runnable)this.mResizePopupRunnable);
    }
    
    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }
    
    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }
    
    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }
    
    public int getHeight() {
        return this.mDropDownHeight;
    }
    
    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }
    
    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }
    
    public ListView getListView() {
        return this.mDropDownList;
    }
    
    public int getPromptPosition() {
        return this.mPromptPosition;
    }
    
    public Object getSelectedItem() {
        if (!this.isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedItem();
    }
    
    public long getSelectedItemId() {
        if (!this.isShowing()) {
            return Long.MIN_VALUE;
        }
        return this.mDropDownList.getSelectedItemId();
    }
    
    public int getSelectedItemPosition() {
        if (!this.isShowing()) {
            return -1;
        }
        return this.mDropDownList.getSelectedItemPosition();
    }
    
    public View getSelectedView() {
        if (!this.isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedView();
    }
    
    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }
    
    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }
    
    public int getWidth() {
        return this.mDropDownWidth;
    }
    
    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }
    
    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }
    
    public boolean isModal() {
        return this.mModal;
    }
    
    public boolean isShowing() {
        return this.mPopup.isShowing();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (this.isShowing() && n != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !isConfirmKey(n))) {
            final int selectedItemPosition = this.mDropDownList.getSelectedItemPosition();
            boolean b;
            if (!this.mPopup.isAboveAnchor()) {
                b = true;
            }
            else {
                b = false;
            }
            final ListAdapter mAdapter = this.mAdapter;
            int lookForSelectablePosition = Integer.MAX_VALUE;
            int lookForSelectablePosition2 = Integer.MIN_VALUE;
            if (mAdapter != null) {
                final boolean allItemsEnabled = mAdapter.areAllItemsEnabled();
                if (allItemsEnabled) {
                    lookForSelectablePosition = 0;
                }
                else {
                    lookForSelectablePosition = this.mDropDownList.lookForSelectablePosition(0, true);
                }
                if (allItemsEnabled) {
                    lookForSelectablePosition2 = -1 + mAdapter.getCount();
                }
                else {
                    lookForSelectablePosition2 = this.mDropDownList.lookForSelectablePosition(-1 + mAdapter.getCount(), false);
                }
            }
            if ((b && n == 19 && selectedItemPosition <= lookForSelectablePosition) || (!b && n == 20 && selectedItemPosition >= lookForSelectablePosition2)) {
                this.clearListSelection();
                this.mPopup.setInputMethodMode(1);
                this.show();
            }
            else {
                this.mDropDownList.mListSelectionHidden = false;
                if (this.mDropDownList.onKeyDown(n, keyEvent)) {
                    this.mPopup.setInputMethodMode(2);
                    this.mDropDownList.requestFocusFromTouch();
                    this.show();
                    switch (n) {
                        case 19:
                        case 20:
                        case 23:
                        case 66: {
                            break;
                        }
                        default: {
                            return false;
                        }
                    }
                }
                else if (b && n == 20) {
                    if (selectedItemPosition == lookForSelectablePosition2) {
                        return true;
                    }
                    return false;
                }
                else {
                    if (!b && n == 19 && selectedItemPosition == lookForSelectablePosition) {
                        return true;
                    }
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean onKeyPreIme(final int n, final KeyEvent keyEvent) {
        if (n == 4 && this.isShowing()) {
            final View mDropDownAnchorView = this.mDropDownAnchorView;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                final KeyEvent$DispatcherState keyDispatcherState = mDropDownAnchorView.getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, (Object)this);
                }
                return true;
            }
            if (keyEvent.getAction() == 1) {
                final KeyEvent$DispatcherState keyDispatcherState2 = mDropDownAnchorView.getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    this.dismiss();
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        if (this.isShowing() && this.mDropDownList.getSelectedItemPosition() >= 0) {
            final boolean onKeyUp = this.mDropDownList.onKeyUp(n, keyEvent);
            if (onKeyUp && isConfirmKey(n)) {
                this.dismiss();
            }
            return onKeyUp;
        }
        return false;
    }
    
    public boolean performItemClick(final int n) {
        if (this.isShowing()) {
            if (this.mItemClickListener != null) {
                final DropDownListView mDropDownList = this.mDropDownList;
                this.mItemClickListener.onItemClick((AdapterView)mDropDownList, mDropDownList.getChildAt(n - mDropDownList.getFirstVisiblePosition()), n, mDropDownList.getAdapter().getItemId(n));
            }
            return true;
        }
        return false;
    }
    
    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }
    
    public void setAdapter(final ListAdapter mAdapter) {
        if (this.mObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        }
        else if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = mAdapter;
        if (this.mAdapter != null) {
            mAdapter.registerDataSetObserver(this.mObserver);
        }
        if (this.mDropDownList != null) {
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }
    
    public void setAnchorView(final View mDropDownAnchorView) {
        this.mDropDownAnchorView = mDropDownAnchorView;
    }
    
    public void setAnimationStyle(final int animationStyle) {
        this.mPopup.setAnimationStyle(animationStyle);
    }
    
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        this.mPopup.setBackgroundDrawable(backgroundDrawable);
    }
    
    public void setContentWidth(final int width) {
        final Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            this.mDropDownWidth = width + (this.mTempRect.left + this.mTempRect.right);
            return;
        }
        this.setWidth(width);
    }
    
    public void setDropDownAlwaysVisible(final boolean mDropDownAlwaysVisible) {
        this.mDropDownAlwaysVisible = mDropDownAlwaysVisible;
    }
    
    public void setDropDownGravity(final int mDropDownGravity) {
        this.mDropDownGravity = mDropDownGravity;
    }
    
    public void setForceIgnoreOutsideTouch(final boolean mForceIgnoreOutsideTouch) {
        this.mForceIgnoreOutsideTouch = mForceIgnoreOutsideTouch;
    }
    
    public void setHeight(final int mDropDownHeight) {
        this.mDropDownHeight = mDropDownHeight;
    }
    
    public void setHorizontalOffset(final int mDropDownHorizontalOffset) {
        this.mDropDownHorizontalOffset = mDropDownHorizontalOffset;
    }
    
    public void setInputMethodMode(final int inputMethodMode) {
        this.mPopup.setInputMethodMode(inputMethodMode);
    }
    
    void setListItemExpandMax(final int mListItemExpandMaximum) {
        this.mListItemExpandMaximum = mListItemExpandMaximum;
    }
    
    public void setListSelector(final Drawable mDropDownListHighlight) {
        this.mDropDownListHighlight = mDropDownListHighlight;
    }
    
    public void setModal(final boolean b) {
        this.mModal = b;
        this.mPopup.setFocusable(b);
    }
    
    public void setOnDismissListener(final PopupWindow$OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }
    
    public void setOnItemClickListener(final AdapterView$OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
    public void setOnItemSelectedListener(final AdapterView$OnItemSelectedListener mItemSelectedListener) {
        this.mItemSelectedListener = mItemSelectedListener;
    }
    
    public void setPromptPosition(final int mPromptPosition) {
        this.mPromptPosition = mPromptPosition;
    }
    
    public void setPromptView(final View mPromptView) {
        final boolean showing = this.isShowing();
        if (showing) {
            this.removePromptView();
        }
        this.mPromptView = mPromptView;
        if (showing) {
            this.show();
        }
    }
    
    public void setSelection(final int selection) {
        final DropDownListView mDropDownList = this.mDropDownList;
        if (this.isShowing() && mDropDownList != null) {
            mDropDownList.mListSelectionHidden = false;
            mDropDownList.setSelection(selection);
            if (Build$VERSION.SDK_INT >= 11 && mDropDownList.getChoiceMode() != 0) {
                mDropDownList.setItemChecked(selection, true);
            }
        }
    }
    
    public void setSoftInputMode(final int softInputMode) {
        this.mPopup.setSoftInputMode(softInputMode);
    }
    
    public void setVerticalOffset(final int mDropDownVerticalOffset) {
        this.mDropDownVerticalOffset = mDropDownVerticalOffset;
        this.mDropDownVerticalOffsetSet = true;
    }
    
    public void setWidth(final int mDropDownWidth) {
        this.mDropDownWidth = mDropDownWidth;
    }
    
    public void setWindowLayoutType(final int mDropDownWindowLayoutType) {
        this.mDropDownWindowLayoutType = mDropDownWindowLayoutType;
    }
    
    public void show() {
        boolean b = true;
        int n = -1;
        final int buildDropDown = this.buildDropDown();
        final boolean inputMethodNotNeeded = this.isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        if (this.mPopup.isShowing()) {
            int n2;
            if (this.mDropDownWidth == n) {
                n2 = -1;
            }
            else if (this.mDropDownWidth == -2) {
                n2 = this.getAnchorView().getWidth();
            }
            else {
                n2 = this.mDropDownWidth;
            }
            int mDropDownHeight;
            if (this.mDropDownHeight == n) {
                if (inputMethodNotNeeded) {
                    mDropDownHeight = buildDropDown;
                }
                else {
                    mDropDownHeight = n;
                }
                if (inputMethodNotNeeded) {
                    final PopupWindow mPopup = this.mPopup;
                    int width;
                    if (this.mDropDownWidth == n) {
                        width = n;
                    }
                    else {
                        width = 0;
                    }
                    mPopup.setWidth(width);
                    this.mPopup.setHeight(0);
                }
                else {
                    final PopupWindow mPopup2 = this.mPopup;
                    int width2;
                    if (this.mDropDownWidth == n) {
                        width2 = n;
                    }
                    else {
                        width2 = 0;
                    }
                    mPopup2.setWidth(width2);
                    this.mPopup.setHeight(n);
                }
            }
            else if (this.mDropDownHeight == -2) {
                mDropDownHeight = buildDropDown;
            }
            else {
                mDropDownHeight = this.mDropDownHeight;
            }
            final PopupWindow mPopup3 = this.mPopup;
            final boolean mForceIgnoreOutsideTouch = this.mForceIgnoreOutsideTouch;
            boolean outsideTouchable = false;
            if (!mForceIgnoreOutsideTouch) {
                final boolean mDropDownAlwaysVisible = this.mDropDownAlwaysVisible;
                outsideTouchable = false;
                if (!mDropDownAlwaysVisible) {
                    outsideTouchable = b;
                }
            }
            mPopup3.setOutsideTouchable(outsideTouchable);
            final PopupWindow mPopup4 = this.mPopup;
            final View anchorView = this.getAnchorView();
            final int mDropDownHorizontalOffset = this.mDropDownHorizontalOffset;
            final int mDropDownVerticalOffset = this.mDropDownVerticalOffset;
            int n3;
            if (n2 < 0) {
                n3 = n;
            }
            else {
                n3 = n2;
            }
            if (mDropDownHeight >= 0) {
                n = mDropDownHeight;
            }
            mPopup4.update(anchorView, mDropDownHorizontalOffset, mDropDownVerticalOffset, n3, n);
        }
        else {
            int width3;
            if (this.mDropDownWidth == n) {
                width3 = -1;
            }
            else if (this.mDropDownWidth == -2) {
                width3 = this.getAnchorView().getWidth();
            }
            else {
                width3 = this.mDropDownWidth;
            }
            int mDropDownHeight2;
            if (this.mDropDownHeight == n) {
                mDropDownHeight2 = -1;
            }
            else if (this.mDropDownHeight == -2) {
                mDropDownHeight2 = buildDropDown;
            }
            else {
                mDropDownHeight2 = this.mDropDownHeight;
            }
            this.mPopup.setWidth(width3);
            this.mPopup.setHeight(mDropDownHeight2);
            this.setPopupClipToScreenEnabled(b);
            final PopupWindow mPopup5 = this.mPopup;
            if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
                b = false;
            }
            mPopup5.setOutsideTouchable(b);
            this.mPopup.setTouchInterceptor((View$OnTouchListener)this.mTouchInterceptor);
            PopupWindowCompat.showAsDropDown(this.mPopup, this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.mDropDownList.setSelection(n);
            if (!this.mModal || this.mDropDownList.isInTouchMode()) {
                this.clearListSelection();
            }
            if (!this.mModal) {
                this.mHandler.post((Runnable)this.mHideSelector);
            }
        }
    }
    
    private static class DropDownListView extends ListViewCompat
    {
        private ViewPropertyAnimatorCompat mClickAnimation;
        private boolean mDrawsInPressedState;
        private boolean mHijackFocus;
        private boolean mListSelectionHidden;
        private ListViewAutoScrollHelper mScrollHelper;
        
        public DropDownListView(final Context context, final boolean mHijackFocus) {
            super(context, null, R.attr.dropDownListViewStyle);
            this.mHijackFocus = mHijackFocus;
            this.setCacheColorHint(0);
        }
        
        private void clearPressedItem() {
            this.setPressed(this.mDrawsInPressedState = false);
            this.drawableStateChanged();
            final View child = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
            if (child != null) {
                child.setPressed(false);
            }
            if (this.mClickAnimation != null) {
                this.mClickAnimation.cancel();
                this.mClickAnimation = null;
            }
        }
        
        private void clickPressedItem(final View view, final int n) {
            this.performItemClick(view, n, this.getItemIdAtPosition(n));
        }
        
        private void setPressedItem(final View view, final int mMotionPosition, final float n, final float n2) {
            this.mDrawsInPressedState = true;
            if (Build$VERSION.SDK_INT >= 21) {
                this.drawableHotspotChanged(n, n2);
            }
            if (!this.isPressed()) {
                this.setPressed(true);
            }
            this.layoutChildren();
            if (this.mMotionPosition != -1) {
                final View child = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
                if (child != null && child != view && child.isPressed()) {
                    child.setPressed(false);
                }
            }
            this.mMotionPosition = mMotionPosition;
            final float n3 = n - view.getLeft();
            final float n4 = n2 - view.getTop();
            if (Build$VERSION.SDK_INT >= 21) {
                view.drawableHotspotChanged(n3, n4);
            }
            if (!view.isPressed()) {
                view.setPressed(true);
            }
            this.positionSelectorLikeTouchCompat(mMotionPosition, view, n, n2);
            this.setSelectorEnabled(false);
            this.refreshDrawableState();
        }
        
        public boolean hasFocus() {
            return this.mHijackFocus || super.hasFocus();
        }
        
        public boolean hasWindowFocus() {
            return this.mHijackFocus || super.hasWindowFocus();
        }
        
        public boolean isFocused() {
            return this.mHijackFocus || super.isFocused();
        }
        
        public boolean isInTouchMode() {
            return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
        }
        
        public boolean onForwardedEvent(final MotionEvent motionEvent, final int n) {
            boolean b = true;
            final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            boolean b2 = false;
            switch (actionMasked) {
                case 3: {
                    b2 = false;
                    b = false;
                    break;
                }
                case 1: {
                    b = false;
                }
                case 2: {
                    final int pointerIndex = motionEvent.findPointerIndex(n);
                    if (pointerIndex < 0) {
                        b2 = false;
                        b = false;
                        break;
                    }
                    final int n2 = (int)motionEvent.getX(pointerIndex);
                    final int n3 = (int)motionEvent.getY(pointerIndex);
                    final int pointToPosition = this.pointToPosition(n2, n3);
                    if (pointToPosition == -1) {
                        b2 = true;
                        break;
                    }
                    final View child = this.getChildAt(pointToPosition - this.getFirstVisiblePosition());
                    this.setPressedItem(child, pointToPosition, n2, n3);
                    b = true;
                    b2 = false;
                    if (actionMasked == 1) {
                        this.clickPressedItem(child, pointToPosition);
                        b2 = false;
                        break;
                    }
                    break;
                }
            }
            if (!b || b2) {
                this.clearPressedItem();
            }
            if (b) {
                if (this.mScrollHelper == null) {
                    this.mScrollHelper = new ListViewAutoScrollHelper(this);
                }
                this.mScrollHelper.setEnabled(true);
                this.mScrollHelper.onTouch((View)this, motionEvent);
            }
            else if (this.mScrollHelper != null) {
                this.mScrollHelper.setEnabled(false);
                return b;
            }
            return b;
        }
        
        @Override
        protected boolean touchModeDrawsInPressedStateCompat() {
            return this.mDrawsInPressedState || super.touchModeDrawsInPressedStateCompat();
        }
    }
    
    public abstract static class ForwardingListener implements View$OnTouchListener
    {
        private int mActivePointerId;
        private Runnable mDisallowIntercept;
        private boolean mForwarding;
        private final int mLongPressTimeout;
        private final float mScaledTouchSlop;
        private final View mSrc;
        private final int mTapTimeout;
        private final int[] mTmpLocation;
        private Runnable mTriggerLongPress;
        private boolean mWasLongPress;
        
        public ForwardingListener(final View mSrc) {
            this.mTmpLocation = new int[2];
            this.mSrc = mSrc;
            this.mScaledTouchSlop = ViewConfiguration.get(mSrc.getContext()).getScaledTouchSlop();
            this.mTapTimeout = ViewConfiguration.getTapTimeout();
            this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
        }
        
        private void clearCallbacks() {
            if (this.mTriggerLongPress != null) {
                this.mSrc.removeCallbacks(this.mTriggerLongPress);
            }
            if (this.mDisallowIntercept != null) {
                this.mSrc.removeCallbacks(this.mDisallowIntercept);
            }
        }
        
        private void onLongPress() {
            this.clearCallbacks();
            final View mSrc = this.mSrc;
            if (mSrc.isEnabled() && !mSrc.isLongClickable() && this.onForwardingStarted()) {
                mSrc.getParent().requestDisallowInterceptTouchEvent(true);
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                mSrc.onTouchEvent(obtain);
                obtain.recycle();
                this.mForwarding = true;
                this.mWasLongPress = true;
            }
        }
        
        private boolean onTouchForwarded(final MotionEvent motionEvent) {
            boolean b = true;
            final View mSrc = this.mSrc;
            final ListPopupWindow popup = this.getPopup();
            if (popup != null && popup.isShowing()) {
                final DropDownListView access$600 = popup.mDropDownList;
                if (access$600 != null && access$600.isShown()) {
                    final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                    this.toGlobalMotionEvent(mSrc, obtainNoHistory);
                    this.toLocalMotionEvent((View)access$600, obtainNoHistory);
                    final boolean onForwardedEvent = access$600.onForwardedEvent(obtainNoHistory, this.mActivePointerId);
                    obtainNoHistory.recycle();
                    final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                    final boolean b2 = actionMasked != (b ? 1 : 0) && actionMasked != 3 && b;
                    if (!onForwardedEvent || !b2) {
                        b = false;
                    }
                    return b;
                }
            }
            return false;
        }
        
        private boolean onTouchObserved(final MotionEvent motionEvent) {
            final View mSrc = this.mSrc;
            if (mSrc.isEnabled()) {
                switch (MotionEventCompat.getActionMasked(motionEvent)) {
                    default: {
                        return false;
                    }
                    case 0: {
                        this.mActivePointerId = motionEvent.getPointerId(0);
                        this.mWasLongPress = false;
                        if (this.mDisallowIntercept == null) {
                            this.mDisallowIntercept = new DisallowIntercept();
                        }
                        mSrc.postDelayed(this.mDisallowIntercept, (long)this.mTapTimeout);
                        if (this.mTriggerLongPress == null) {
                            this.mTriggerLongPress = new TriggerLongPress();
                        }
                        mSrc.postDelayed(this.mTriggerLongPress, (long)this.mLongPressTimeout);
                        return false;
                    }
                    case 2: {
                        final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                        if (pointerIndex >= 0 && !pointInView(mSrc, motionEvent.getX(pointerIndex), motionEvent.getY(pointerIndex), this.mScaledTouchSlop)) {
                            this.clearCallbacks();
                            mSrc.getParent().requestDisallowInterceptTouchEvent(true);
                            return true;
                        }
                        break;
                    }
                    case 1:
                    case 3: {
                        this.clearCallbacks();
                        return false;
                    }
                }
            }
            return false;
        }
        
        private static boolean pointInView(final View view, final float n, final float n2, final float n3) {
            return n >= -n3 && n2 >= -n3 && n < n3 + (view.getRight() - view.getLeft()) && n2 < n3 + (view.getBottom() - view.getTop());
        }
        
        private boolean toGlobalMotionEvent(final View view, final MotionEvent motionEvent) {
            final int[] mTmpLocation = this.mTmpLocation;
            view.getLocationOnScreen(mTmpLocation);
            motionEvent.offsetLocation((float)mTmpLocation[0], (float)mTmpLocation[1]);
            return true;
        }
        
        private boolean toLocalMotionEvent(final View view, final MotionEvent motionEvent) {
            final int[] mTmpLocation = this.mTmpLocation;
            view.getLocationOnScreen(mTmpLocation);
            motionEvent.offsetLocation((float)(-mTmpLocation[0]), (float)(-mTmpLocation[1]));
            return true;
        }
        
        public abstract ListPopupWindow getPopup();
        
        protected boolean onForwardingStarted() {
            final ListPopupWindow popup = this.getPopup();
            if (popup != null && !popup.isShowing()) {
                popup.show();
            }
            return true;
        }
        
        protected boolean onForwardingStopped() {
            final ListPopupWindow popup = this.getPopup();
            if (popup != null && popup.isShowing()) {
                popup.dismiss();
            }
            return true;
        }
        
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            final boolean mForwarding = this.mForwarding;
            boolean onTouchForwarded;
            if (mForwarding) {
                if (this.mWasLongPress) {
                    onTouchForwarded = this.onTouchForwarded(motionEvent);
                }
                else {
                    onTouchForwarded = (this.onTouchForwarded(motionEvent) || !this.onForwardingStopped());
                }
            }
            else {
                onTouchForwarded = (this.onTouchObserved(motionEvent) && this.onForwardingStarted());
                if (onTouchForwarded) {
                    final long uptimeMillis = SystemClock.uptimeMillis();
                    final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    this.mSrc.onTouchEvent(obtain);
                    obtain.recycle();
                }
            }
            if (!(this.mForwarding = onTouchForwarded)) {
                final boolean b = false;
                if (!mForwarding) {
                    return b;
                }
            }
            return true;
        }
        
        private class DisallowIntercept implements Runnable
        {
            @Override
            public void run() {
                ForwardingListener.this.mSrc.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        
        private class TriggerLongPress implements Runnable
        {
            @Override
            public void run() {
                ForwardingListener.this.onLongPress();
            }
        }
    }
    
    private class ListSelectorHider implements Runnable
    {
        @Override
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }
    
    private class PopupDataSetObserver extends DataSetObserver
    {
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }
        
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }
    
    private class PopupScrollListener implements AbsListView$OnScrollListener
    {
        public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        }
        
        public void onScrollStateChanged(final AbsListView absListView, final int n) {
            if (n == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow.this.mHandler.removeCallbacks((Runnable)ListPopupWindow.this.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }
    }
    
    private class PopupTouchInterceptor implements View$OnTouchListener
    {
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            final int action = motionEvent.getAction();
            final int n = (int)motionEvent.getX();
            final int n2 = (int)motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && n >= 0 && n < ListPopupWindow.this.mPopup.getWidth() && n2 >= 0 && n2 < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow.this.mHandler.postDelayed((Runnable)ListPopupWindow.this.mResizePopupRunnable, 250L);
            }
            else if (action == 1) {
                ListPopupWindow.this.mHandler.removeCallbacks((Runnable)ListPopupWindow.this.mResizePopupRunnable);
            }
            return false;
        }
    }
    
    private class ResizePopupRunnable implements Runnable
    {
        @Override
        public void run() {
            if (ListPopupWindow.this.mDropDownList != null && ViewCompat.isAttachedToWindow((View)ListPopupWindow.this.mDropDownList) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
                ListPopupWindow.this.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }
}
