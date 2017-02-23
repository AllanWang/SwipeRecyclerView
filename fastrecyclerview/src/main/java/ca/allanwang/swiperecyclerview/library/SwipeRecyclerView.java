package ca.allanwang.swiperecyclerview.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

/**
 * Created by Allan Wang on 2017-02-06.
 */

public class SwipeRecyclerView extends FrameLayout implements SwipeRefreshBase.ISwipeRefresh {

    private Context mContext;
    private SwipeRefreshBase mSwipe;
    private RecyclerView mRecycler;

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
        mAdapter = getFastAdapter();
        setAdapter(mAdapter);
    }

    @Override
    public boolean shouldConsumeTouch(MotionEvent ev) {
        return canSwipe && !mRecycler.canScrollVertically(-1);
    }
}
