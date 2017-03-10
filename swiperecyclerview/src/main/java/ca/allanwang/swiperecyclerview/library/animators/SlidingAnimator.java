package ca.allanwang.swiperecyclerview.library.animators;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import jp.wasabeef.recyclerview.animators.BaseItemAnimator;

/**
 * Created by Allan Wang on 2017-03-09.
 * Sliding Animator; enter up, exit right, with fading for both
 */

public class SlidingAnimator extends BaseItemAnimator {

    public SlidingAnimator() {
        mInterpolator = new AccelerateDecelerateInterpolator();
        setTimings();
    }

    public SlidingAnimator(Interpolator interpolator) {
        mInterpolator = interpolator;
        setTimings();
    }

    private void setTimings() {
        setAddDuration(500);
        setRemoveDuration(200);
    }

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
//        Animation slideUp = AnimationUtils.loadAnimation();
//        slideUp.setInterpolator(mInterpolator);

//        holder.itemView.startAnimation(slideUp);

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

    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        long displacement = (long) Math.max(holder.itemView.getHeight(), ((View) holder.itemView.getParent()).getHeight() - holder.itemView.getY());
        ViewCompat.setTranslationY(holder.itemView, displacement);
        ViewCompat.setAlpha(holder.itemView, 0);
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