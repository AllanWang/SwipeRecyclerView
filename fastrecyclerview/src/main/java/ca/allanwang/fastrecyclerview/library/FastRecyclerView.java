package ca.allanwang.fastrecyclerview.library;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

/**
 * Created by Allan Wang on 2017-02-06.
 */

public class FastRecyclerView<Item extends IItem> extends RecyclerView {

    private FastItemAdapter<Item> mAdapter;

    public static FastRecyclerView inflate(View view, @IdRes int id) {
        return (FastRecyclerView) view.findViewById(id);
    }

    public static <It extends IItem> FastRecyclerView inflate(View view, @IdRes int id, Class<It> clazz) {
        return (FastRecyclerView<It>) view.findViewById(id);
    }

    public FastRecyclerView(Context context) {
        super(context);
        init();
    }

    public FastRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FastRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mAdapter = new FastItemAdapter<>();
        setAdapter(mAdapter);
    }

    public FastItemAdapter<Item> getAdapter() {
        return mAdapter;
    }
}
