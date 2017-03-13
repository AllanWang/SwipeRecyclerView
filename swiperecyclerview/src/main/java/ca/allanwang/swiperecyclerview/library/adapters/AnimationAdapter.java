package ca.allanwang.swiperecyclerview.library.adapters;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.List;

import ca.allanwang.swiperecyclerview.library.interfaces.IAdapterAnimator;
import ca.allanwang.swiperecyclerview.library.interfaces.IItemAnimatorExtension;
import ca.allanwang.swiperecyclerview.library.wasabeef.internal.ViewHelper;

/**
 * Created by Allan Wang on 2017-03-09.
 */

public class AnimationAdapter<Item extends IItem> extends FastItemAdapter<Item> {

    private int mDuration = 300;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mLastPosition = -1;
    private IAdapterAnimator mAnimator;
    private RecyclerView mRecyclerView;

    private boolean isFirstOnly;

    public AnimationAdapter() {
        super();
        init();
    }

    public AnimationAdapter(IAdapterAnimator animator) {
        super();
        mAnimator = animator;
        init();
    }


    private void init() {
        setAnimator(mAnimator);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        int adapterPosition = holder.getAdapterPosition();
        if (!isFirstOnly || adapterPosition > mLastPosition) {
            for (Animator anim : mAnimator.getAnimators(holder.itemView, getItemCount() == 0)) {
                anim.setDuration(mDuration).start();
                anim.setInterpolator(mInterpolator);
            }
            mLastPosition = adapterPosition;
        } else {
            ViewHelper.clear(holder.itemView);
        }
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setStartPosition(int start) {
        mLastPosition = start;
    }

    public void setFirstOnly(boolean firstOnly) {
        isFirstOnly = firstOnly;
    }

    public void setAnimator(@Nullable IAdapterAnimator animator) {
        mAnimator = animator;
        if (mAnimator == null) {
            //set default animations (none)
            mAnimator = new IAdapterAnimator() {
                @Override
                public boolean isFirstOnly() {
                    return false;
                }

                @Override
                public int getDuration() {
                    return 0;
                }

                @NonNull
                @Override
                public Interpolator getInterpolator() {
                    return new LinearInterpolator();
                }

                @NonNull
                @Override
                public Animator[] getAnimators(View view, boolean isEmpty) {
                    return new Animator[0];
                }
            };
        }
        isFirstOnly = mAnimator.isFirstOnly();
        mDuration = mAnimator.getDuration();
        mInterpolator = mAnimator.getInterpolator();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    /*
     * override add functions
     */

    private void trigger(int position) {
        if (mRecyclerView == null) return;
        RecyclerView.ItemAnimator itemAnimator = mRecyclerView.getItemAnimator();
        if (itemAnimator instanceof IItemAnimatorExtension)
            ((IItemAnimatorExtension) itemAnimator).triggerAdd(position == 0, position >= getItemCount(), getItemCount() == 0);
    }

    @Override
    public AnimationAdapter<Item> add(List<Item> items) {
        trigger(getItemCount());
        super.add(items);
        return this;
    }

    @Override
    public AnimationAdapter<Item> add(Item item) {
        trigger(getItemCount());
        super.add(item);
        return this;
    }

    @Override
    public FastItemAdapter<Item> add(int position, List<Item> items) {
        trigger(position);
        super.add(position, items);
        return this;
    }

    @Override
    public FastItemAdapter<Item> add(int position, Item item) {
        trigger(position);
        super.add(position, item);
        return this;
    }
}
