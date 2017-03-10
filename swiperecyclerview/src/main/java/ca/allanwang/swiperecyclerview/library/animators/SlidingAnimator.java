package ca.allanwang.swiperecyclerview.library.animators;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import jp.wasabeef.recyclerview.animators.BaseItemAnimator;

/**
 * Created by Allan Wang on 2017-03-09.
 * Sliding Animator; enter up, exit right, with fading for both
 */

public class SlidingAnimator extends BaseItemAnimator {

    //animate from base; set true if holders are relatively big
    private boolean fromBase = false;

    public SlidingAnimator() {
        mInterpolator = new DecelerateInterpolator();
        setTimings();
    }

    public SlidingAnimator(Interpolator interpolator) {
        mInterpolator = interpolator;
        setTimings();
    }

    private void setTimings() {
        setAddDuration(200);
        setRemoveDuration(200);
    }

    public SlidingAnimator setFromBase(boolean fromBase) {
        if (this.fromBase != fromBase) {
            this.fromBase = fromBase;
            setAddDuration(fromBase ? 500 : 200);
        }
        return this;
    }

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView)
                .translationX(holder.itemView.getRootView().getWidth())
                .alpha(0)
                .setDuration(getRemoveDuration())
                .setInterpolator(mInterpolator)
                .setListener(new DefaultRemoveVpaListener(holder))
                .setStartDelay(getRemoveDelay(holder))
                .start();
    }

    @Override
    protected long getRemoveDelay(final RecyclerView.ViewHolder holder) {
        return Math.abs(holder.getOldPosition() * getRemoveDuration() * 4);
    }

    @Override
    protected long getAddDelay(final RecyclerView.ViewHolder holder) {
        return Math.abs(holder.getAdapterPosition() * getAddDuration() / 10);
    }

    /**
     * Set sliding base
     * If there are many holders that fit in the screen, do not slide from the bottom
     *
     * @param holder to animate
     */
    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        if (fromBase) slideInFromBase(holder);
        else fadeSlideIn(holder);
    }

    private void fadeSlideIn(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationY(holder.itemView, holder.itemView.getHeight() * 2);
        ViewCompat.setAlpha(holder.itemView, 0);
    }

    private void slideInFromBase(RecyclerView.ViewHolder holder) {
        long displacement = (long) Math.max(holder.itemView.getHeight(),
                ((View) holder.itemView.getParent()).getHeight() - holder.itemView.getY());
        ViewCompat.setTranslationY(holder.itemView, displacement);
    }

    @Override
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView)
                .translationY(0)
                .alpha(1)
                .setDuration(getAddDuration())
                .setInterpolator(mInterpolator)
                .setListener(new DefaultAddVpaListener(holder))
                .setStartDelay(getAddDelay(holder))
                .start();
    }
}