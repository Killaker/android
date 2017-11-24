package android.support.v7.app;

import android.database.*;
import android.view.*;
import android.graphics.drawable.*;
import android.util.*;
import android.widget.*;
import android.content.*;

public static class Builder
{
    private final AlertController.AlertParams P;
    private int mTheme;
    
    public Builder(final Context context) {
        this(context, AlertDialog.resolveDialogTheme(context, 0));
    }
    
    public Builder(final Context context, final int mTheme) {
        this.P = new AlertController.AlertParams((Context)new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, mTheme)));
        this.mTheme = mTheme;
    }
    
    public AlertDialog create() {
        final AlertDialog alertDialog = new AlertDialog(this.P.mContext, this.mTheme, false);
        this.P.apply(AlertDialog.access$000(alertDialog));
        alertDialog.setCancelable(this.P.mCancelable);
        if (this.P.mCancelable) {
            alertDialog.setCanceledOnTouchOutside(true);
        }
        alertDialog.setOnCancelListener(this.P.mOnCancelListener);
        alertDialog.setOnDismissListener(this.P.mOnDismissListener);
        if (this.P.mOnKeyListener != null) {
            alertDialog.setOnKeyListener(this.P.mOnKeyListener);
        }
        return alertDialog;
    }
    
    public Context getContext() {
        return this.P.mContext;
    }
    
    public Builder setAdapter(final ListAdapter mAdapter, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mAdapter = mAdapter;
        this.P.mOnClickListener = mOnClickListener;
        return this;
    }
    
    public Builder setCancelable(final boolean mCancelable) {
        this.P.mCancelable = mCancelable;
        return this;
    }
    
    public Builder setCursor(final Cursor mCursor, final DialogInterface$OnClickListener mOnClickListener, final String mLabelColumn) {
        this.P.mCursor = mCursor;
        this.P.mLabelColumn = mLabelColumn;
        this.P.mOnClickListener = mOnClickListener;
        return this;
    }
    
    public Builder setCustomTitle(final View mCustomTitleView) {
        this.P.mCustomTitleView = mCustomTitleView;
        return this;
    }
    
    public Builder setIcon(final int mIconId) {
        this.P.mIconId = mIconId;
        return this;
    }
    
    public Builder setIcon(final Drawable mIcon) {
        this.P.mIcon = mIcon;
        return this;
    }
    
    public Builder setIconAttribute(final int n) {
        final TypedValue typedValue = new TypedValue();
        this.P.mContext.getTheme().resolveAttribute(n, typedValue, true);
        this.P.mIconId = typedValue.resourceId;
        return this;
    }
    
    public Builder setInverseBackgroundForced(final boolean mForceInverseBackground) {
        this.P.mForceInverseBackground = mForceInverseBackground;
        return this;
    }
    
    public Builder setItems(final int n, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = this.P.mContext.getResources().getTextArray(n);
        this.P.mOnClickListener = mOnClickListener;
        return this;
    }
    
    public Builder setItems(final CharSequence[] mItems, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = mItems;
        this.P.mOnClickListener = mOnClickListener;
        return this;
    }
    
    public Builder setMessage(final int n) {
        this.P.mMessage = this.P.mContext.getText(n);
        return this;
    }
    
    public Builder setMessage(final CharSequence mMessage) {
        this.P.mMessage = mMessage;
        return this;
    }
    
    public Builder setMultiChoiceItems(final int n, final boolean[] mCheckedItems, final DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener) {
        this.P.mItems = this.P.mContext.getResources().getTextArray(n);
        this.P.mOnCheckboxClickListener = mOnCheckboxClickListener;
        this.P.mCheckedItems = mCheckedItems;
        this.P.mIsMultiChoice = true;
        return this;
    }
    
    public Builder setMultiChoiceItems(final Cursor mCursor, final String mIsCheckedColumn, final String mLabelColumn, final DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener) {
        this.P.mCursor = mCursor;
        this.P.mOnCheckboxClickListener = mOnCheckboxClickListener;
        this.P.mIsCheckedColumn = mIsCheckedColumn;
        this.P.mLabelColumn = mLabelColumn;
        this.P.mIsMultiChoice = true;
        return this;
    }
    
    public Builder setMultiChoiceItems(final CharSequence[] mItems, final boolean[] mCheckedItems, final DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener) {
        this.P.mItems = mItems;
        this.P.mOnCheckboxClickListener = mOnCheckboxClickListener;
        this.P.mCheckedItems = mCheckedItems;
        this.P.mIsMultiChoice = true;
        return this;
    }
    
    public Builder setNegativeButton(final int n, final DialogInterface$OnClickListener mNegativeButtonListener) {
        this.P.mNegativeButtonText = this.P.mContext.getText(n);
        this.P.mNegativeButtonListener = mNegativeButtonListener;
        return this;
    }
    
    public Builder setNegativeButton(final CharSequence mNegativeButtonText, final DialogInterface$OnClickListener mNegativeButtonListener) {
        this.P.mNegativeButtonText = mNegativeButtonText;
        this.P.mNegativeButtonListener = mNegativeButtonListener;
        return this;
    }
    
    public Builder setNeutralButton(final int n, final DialogInterface$OnClickListener mNeutralButtonListener) {
        this.P.mNeutralButtonText = this.P.mContext.getText(n);
        this.P.mNeutralButtonListener = mNeutralButtonListener;
        return this;
    }
    
    public Builder setNeutralButton(final CharSequence mNeutralButtonText, final DialogInterface$OnClickListener mNeutralButtonListener) {
        this.P.mNeutralButtonText = mNeutralButtonText;
        this.P.mNeutralButtonListener = mNeutralButtonListener;
        return this;
    }
    
    public Builder setOnCancelListener(final DialogInterface$OnCancelListener mOnCancelListener) {
        this.P.mOnCancelListener = mOnCancelListener;
        return this;
    }
    
    public Builder setOnDismissListener(final DialogInterface$OnDismissListener mOnDismissListener) {
        this.P.mOnDismissListener = mOnDismissListener;
        return this;
    }
    
    public Builder setOnItemSelectedListener(final AdapterView$OnItemSelectedListener mOnItemSelectedListener) {
        this.P.mOnItemSelectedListener = mOnItemSelectedListener;
        return this;
    }
    
    public Builder setOnKeyListener(final DialogInterface$OnKeyListener mOnKeyListener) {
        this.P.mOnKeyListener = mOnKeyListener;
        return this;
    }
    
    public Builder setPositiveButton(final int n, final DialogInterface$OnClickListener mPositiveButtonListener) {
        this.P.mPositiveButtonText = this.P.mContext.getText(n);
        this.P.mPositiveButtonListener = mPositiveButtonListener;
        return this;
    }
    
    public Builder setPositiveButton(final CharSequence mPositiveButtonText, final DialogInterface$OnClickListener mPositiveButtonListener) {
        this.P.mPositiveButtonText = mPositiveButtonText;
        this.P.mPositiveButtonListener = mPositiveButtonListener;
        return this;
    }
    
    public Builder setRecycleOnMeasureEnabled(final boolean mRecycleOnMeasure) {
        this.P.mRecycleOnMeasure = mRecycleOnMeasure;
        return this;
    }
    
    public Builder setSingleChoiceItems(final int n, final int mCheckedItem, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = this.P.mContext.getResources().getTextArray(n);
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public Builder setSingleChoiceItems(final Cursor mCursor, final int mCheckedItem, final String mLabelColumn, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mCursor = mCursor;
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mLabelColumn = mLabelColumn;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public Builder setSingleChoiceItems(final ListAdapter mAdapter, final int mCheckedItem, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mAdapter = mAdapter;
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public Builder setSingleChoiceItems(final CharSequence[] mItems, final int mCheckedItem, final DialogInterface$OnClickListener mOnClickListener) {
        this.P.mItems = mItems;
        this.P.mOnClickListener = mOnClickListener;
        this.P.mCheckedItem = mCheckedItem;
        this.P.mIsSingleChoice = true;
        return this;
    }
    
    public Builder setTitle(final int n) {
        this.P.mTitle = this.P.mContext.getText(n);
        return this;
    }
    
    public Builder setTitle(final CharSequence mTitle) {
        this.P.mTitle = mTitle;
        return this;
    }
    
    public Builder setView(final int mViewLayoutResId) {
        this.P.mView = null;
        this.P.mViewLayoutResId = mViewLayoutResId;
        this.P.mViewSpacingSpecified = false;
        return this;
    }
    
    public Builder setView(final View mView) {
        this.P.mView = mView;
        this.P.mViewLayoutResId = 0;
        this.P.mViewSpacingSpecified = false;
        return this;
    }
    
    public Builder setView(final View mView, final int mViewSpacingLeft, final int mViewSpacingTop, final int mViewSpacingRight, final int mViewSpacingBottom) {
        this.P.mView = mView;
        this.P.mViewLayoutResId = 0;
        this.P.mViewSpacingSpecified = true;
        this.P.mViewSpacingLeft = mViewSpacingLeft;
        this.P.mViewSpacingTop = mViewSpacingTop;
        this.P.mViewSpacingRight = mViewSpacingRight;
        this.P.mViewSpacingBottom = mViewSpacingBottom;
        return this;
    }
    
    public AlertDialog show() {
        final AlertDialog create = this.create();
        create.show();
        return create;
    }
}
