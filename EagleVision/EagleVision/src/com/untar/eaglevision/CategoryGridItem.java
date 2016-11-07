package com.untar.eaglevision;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CategoryGridItem extends LinearLayout implements Checkable {

	public CategoryGridItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CategoryGridItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CategoryGridItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private static final int[] CheckedStateSet = {
	    android.R.attr.state_checked,
	};
	
	private boolean mChecked = false;

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        refreshDrawableState();
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void toggle() {
        setChecked(!mChecked);
    }

	@Override
	protected int[] onCreateDrawableState(int extraSpace) {
	    final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
	    if (isChecked()) {
	        mergeDrawableStates(drawableState, CheckedStateSet);
	    }
	    return drawableState;
	}

	@Override
	public boolean performClick() {
	    toggle();
	    return super.performClick();
	}
	
}
