package ca.allanwang.swiperecyclerview.sample.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.allanwang.swiperecyclerview.library.SwipeRecyclerView;
import ca.allanwang.swiperecyclerview.library.animators.SlidingAnimator;
import ca.allanwang.swiperecyclerview.library.items.CheckBoxItem;
import ca.allanwang.swiperecyclerview.sample.R;

/**
 * Created by Allan Wang on 2017-02-24.
 */

public class HomeFragment extends BaseFragment<CheckBoxItem> {
    private static final String[] ALPHABET = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    @Override
    protected void configSRV(SwipeRecyclerView srv) {
        srv.setItemAnimator(new SlidingAnimator().setFromBase(true));
    }

    @Override
    protected List<CheckBoxItem> generateList() {
        int x = 0;
        List<CheckBoxItem> items = new ArrayList<>();
        for (String s : ALPHABET) {
            int count = new Random().nextInt(32);
            for (int i = 1; i <= count; i++, x++)
                items.add(new CheckBoxItem().withName(s + " Test " + x).withIdentifier(100 + x));
        }
        return items;
    }

    @Override
    public int getTitleId() {
        return R.string.home;
    }

}
