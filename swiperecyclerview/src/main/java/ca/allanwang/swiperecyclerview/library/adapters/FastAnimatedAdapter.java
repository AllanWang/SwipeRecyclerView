package ca.allanwang.swiperecyclerview.library.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.List;

import ca.allanwang.swiperecyclerview.library.interfaces.IItemAnimation;

/**
 * Created by Allan Wang on 2017-02-24.
 */

public class FastAnimatedAdapter<Item extends IItem> extends FastItemAdapter<Item> {

    private int lastAnimatedPosition = -1;
    private IItemAnimation mItemAnimator;
    private boolean animateFirstOnly = true;

    public FastAnimatedAdapter(@NonNull IItemAnimation animator) {
        mItemAnimator = animator;
    }

    public FastAnimatedAdapter animateFirstOnly(boolean b) {
        animateFirstOnly = b;
        return this;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        animate(holder, position);
    }

    public void animate(RecyclerView.ViewHolder viewHolder, int position) {
        if (animateFirstOnly && position <= lastAnimatedPosition)
            return; //previously bound; do not reanimate
        mItemAnimator.runAnimation(viewHolder.itemView, position, position > lastAnimatedPosition);
        if (viewHolder.getAdapterPosition() > lastAnimatedPosition)
            lastAnimatedPosition = viewHolder.getAdapterPosition();
    }

    public void setItemAnimator(@NonNull IItemAnimation animator) {
        mItemAnimator = animator;
    }

    /**
     * removes an item at the given position within the existing icons
     *
     * @param position the global position
     */
    @Override
    public FastItemAdapter<Item> remove(int position) {
        super.remove(position);
        lastAnimatedPosition = getAdapterItemCount();
        return this;
    }

    /**
     * removes a range of items starting with the given position within the existing icons
     *
     * @param position  the global position
     * @param itemCount the count of items removed
     */
    @Override
    public FastItemAdapter<Item> removeItemRange(int position, int itemCount) {
        super.removeItemRange(position, itemCount);
        lastAnimatedPosition = getAdapterItemCount();
        return this;
    }

    /**
     * removes all items of this adapter
     */
    @Override
    public FastItemAdapter<Item> clear() {
        super.clear();
        lastAnimatedPosition = getAdapterItemCount();
        return this;
    }
}
