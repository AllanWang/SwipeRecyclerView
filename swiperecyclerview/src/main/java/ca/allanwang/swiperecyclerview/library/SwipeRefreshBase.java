package ca.allanwang.swiperecyclerview.library;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Allan Wang on 2017-02-23.
 */

class SwipeRefreshBase extends SwipeRefreshLayout {

    private ISwipeRefresh mISwipe;

    interface ISwipeRefresh {
        boolean shouldConsumeTouch(MotionEvent ev);
    }

    public SwipeRefreshBase(Context context) {
        super(context);
    }

    public SwipeRefreshBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    void setISwipe(ISwipeRefresh iSwipe) {
        mISwipe = iSwipe;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mISwipe.shouldConsumeTouch(ev) && super.onInterceptTouchEvent(ev);
    }
}
