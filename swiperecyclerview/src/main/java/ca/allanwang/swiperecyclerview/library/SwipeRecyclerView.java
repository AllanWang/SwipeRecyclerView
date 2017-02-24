package ca.allanwang.swiperecyclerview.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import ca.allanwang.fastrecyclerview.library.R;

/**
 * Created by Allan Wang on 2017-02-06.
 */

public class SwipeRecyclerView extends FrameLayout implements SwipeRefreshBase.ISwipeRefresh {

    private Context mContext;
    private SwipeRefreshBase mSwipe;
    private RecyclerView mRecycler;

    private SwipeRefreshLayout.OnRefreshListener mRefreshListener;

    private boolean canSwipe = false;

    public SwipeRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mSwipe = (SwipeRefreshBase) findViewById(R.id.srv_swipe);
        mRecycler = (RecyclerView) findViewById(R.id.srv_recycler);
    }

    /**
     * Gets recycler view.
     *
     * @return recycler view
     */
    public RecyclerView getRecyclerView() {
        return mRecycler;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipe;
    }

    public SwipeRecyclerView enableRefresh() {
        canSwipe = true;
        return this;
    }

    public SwipeRecyclerView disableRefresh() {
        canSwipe = false;
        return this;
    }

    public SwipeRecyclerView setRefreshing(boolean refreshing) {
        mSwipe.setRefreshing(refreshing);
        return this;
    }

    public SwipeRecyclerView showRefresh() {
        return setRefreshing(true);
    }

    public SwipeRecyclerView hideRefresh() {
        return setRefreshing(false);
    }

    /**
     * Sets on refresh listener.
     *
     * @param listener the listener
     * @return the on refresh listener
     */
    public SwipeRecyclerView setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mRefreshListener = listener;
        mSwipe.setOnRefreshListener(mRefreshListener);
        return this;
    }

    /**
     * Refresh swipe recycler view, calling the listener if it exists
     *
     * @return the swipe recycler view
     */
    public SwipeRecyclerView refresh() {
        mSwipe.setRefreshing(true);
        if (mRefreshListener != null) mRefreshListener.onRefresh();
        return this;
    }

    @Override
    public boolean shouldConsumeTouch(MotionEvent ev) {
        return canSwipe && !mRecycler.canScrollVertically(-1);
    }
}
