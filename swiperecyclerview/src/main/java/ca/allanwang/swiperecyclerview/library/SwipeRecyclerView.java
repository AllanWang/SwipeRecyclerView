package ca.allanwang.swiperecyclerview.library;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.mikepenz.fastadapter.FastAdapter;

import ca.allanwang.swiperecyclerview.library.interfaces.ISwipeRecycler;
import ca.allanwang.swiperecyclerview.library.logging.RLog;

/**
 * Created by Allan Wang on 2017-02-06.
 */

public class SwipeRecyclerView extends FrameLayout implements SwipeRefreshBase.ISwipeRefresh, SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;
    private SwipeRefreshBase mSwipe;
    private RecyclerView mRecycler;

    private ISwipeRecycler.OnRefreshListener mRefreshListener;
    private ISwipeRecycler.OnRefreshStatus mRefreshStatus = getDefaultRefreshStatus();

    /**
     * Generate default refresh callbacks
     *
     * @return OnRefreshStatus
     */
    private ISwipeRecycler.OnRefreshStatus getDefaultRefreshStatus() {
        return new ISwipeRecycler.OnRefreshStatus() {
            @Override
            public void onSuccess() {
                hideRefresh();
            }

            @Override
            public void onFailure() {
                hideRefresh();
            }
        };
    }

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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.srv_base, this);
        mSwipe = (SwipeRefreshBase) findViewById(R.id.srv_swipe);
        mSwipe.setISwipe(this); //handle touch events
        mSwipe.setOnRefreshListener(this);
        disableRefresh(); //disable refresh until listener is added
        mRecycler = (RecyclerView) findViewById(R.id.srv_recycler);
    }

    public SwipeRecyclerView enableRefresh() {
        if (mRefreshListener == null) {
            RLog.w("No Refresh Listener added; disabling refresh pull down");
            return disableRefresh();
        }
        mSwipe.setEnabled(true);
        return this;
    }

    public SwipeRecyclerView disableRefresh() {
        mSwipe.setEnabled(false);
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
     * Sets adapter and sets layout manager is not present.
     *
     * @param adapter the adapter
     * @return SRV
     */
    public SwipeRecyclerView setAdapter(FastAdapter adapter) {
        if (mRecycler.getLayoutManager() == null)
            mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setAdapter(adapter);
        return this;
    }

    /**
     * Sets adapter and layout manager
     *
     * @param adapter the adapter
     * @param columns the # of columns
     * @return SRV
     */
    public SwipeRecyclerView setAdapter(FastAdapter adapter, int columns) {
        if (columns == 1) mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        else mRecycler.setLayoutManager(new GridLayoutManager(mContext, columns));
        mRecycler.setAdapter(adapter);
        return this;
    }

    /**
     * Sets on refresh listener.
     *
     * @param listener the listener
     * @return the on refresh listener
     */
    public SwipeRecyclerView setOnRefreshListener(@NonNull ISwipeRecycler.OnRefreshListener listener) {
        mRefreshListener = listener;
        enableRefresh();
        return this;
    }

    /**
     * Define refresh complete and disabled behaviour
     * Setting as null will produce the default behaviour of
     * hiding the refresh spinner when completed
     *
     * @param callback onComplete and onFailure
     * @return this on refresh status
     */
    public SwipeRecyclerView setOnRefreshStatus(@Nullable ISwipeRecycler.OnRefreshStatus callback) {
        if (callback == null) mRefreshStatus = getDefaultRefreshStatus();
        else mRefreshStatus = callback;
        return this;
    }

    /**
     * Refresh swipe recycler view, calling the listener if it exists
     *
     * @return the swipe recycler view
     */
    public SwipeRecyclerView refresh() {
        mSwipe.setRefreshing(true);
        onRefresh();
        return this;
    }

    @Override
    public boolean shouldConsumeTouch(MotionEvent ev) {
        return !mRecycler.canScrollVertically(-1);
    }

    @Override
    public final void onRefresh() {
        if (mRefreshListener != null) mRefreshListener.onRefresh(mRefreshStatus);
        else RLog.w("Refresh Listener is null");
    }

    /**
     * Gets recycler view.
     *
     * @return recycler view
     */
    public RecyclerView getRecyclerView() {
        return mRecycler;
    }

    /**
     * Gets swipe refresh layout.
     *
     * @return the swipe refresh layout
     */
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipe;
    }
}