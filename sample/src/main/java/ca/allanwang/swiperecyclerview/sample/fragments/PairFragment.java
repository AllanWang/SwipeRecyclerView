package ca.allanwang.swiperecyclerview.sample.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.allanwang.swiperecyclerview.library.SwipeRecyclerView;
import ca.allanwang.swiperecyclerview.library.animators.SlidingAnimator;
import ca.allanwang.swiperecyclerview.library.items.PairItem;
import ca.allanwang.swiperecyclerview.sample.R;

/**
 * Created by Allan Wang on 2017-02-24.
 */

public class PairFragment extends BaseFragment<PairItem> {

    @Override
    protected void configSRV(SwipeRecyclerView srv) {
        srv.setItemAnimator(new SlidingAnimator().setFromBase(false));
    }

    @Override
    protected List<PairItem> generateList() {
        List<PairItem> items = new ArrayList<>();
        for (int i = 0; i < 200; i++)
            items.add(new PairItem(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        return items;
    }

    @Override
    public int getTitleId() {
        return R.string.pair;
    }
}
