package ca.allanwang.swiperecyclerview.sample.fragments;

import android.os.Handler;
import android.support.annotation.Nullable;

import com.mikepenz.fastadapter.IItem;

import java.util.List;

import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.fragments.CapsuleSRVFragment;
import ca.allanwang.swiperecyclerview.library.SwipeRecyclerView;
import ca.allanwang.swiperecyclerview.library.adapters.AnimationAdapter;
import ca.allanwang.swiperecyclerview.library.interfaces.ISwipeRecycler;

/**
 * Created by Allan Wang on 2017-02-24.
 */

public abstract class BaseFragment<I extends IItem> extends CapsuleSRVFragment<I> {

    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return null;
    }

    @Override
    protected void configAdapter(AnimationAdapter<I> adapter) {
        adapter.add(generateList());
    }

    @Override
    protected void configSRV(SwipeRecyclerView srv) {
    }

    protected abstract List<I> generateList();

    @Override
    public void onRefresh(final ISwipeRecycler.OnRefreshStatus statusEmitter) {
        mAdapter.clear();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.add(generateList());
                statusEmitter.onSuccess();
            }
        }, 1000);
    }
}
