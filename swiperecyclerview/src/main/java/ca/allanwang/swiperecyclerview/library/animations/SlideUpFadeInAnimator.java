package ca.allanwang.swiperecyclerview.library.animations;

import android.content.res.Resources;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import ca.allanwang.swiperecyclerview.library.interfaces.IItemAnimation;

/**
 * Created by Allan Wang on 2017-02-24.
 */

public class SlideUpFadeInAnimator implements IItemAnimation {

    private static final int DELAY = 20, Y_OFFSET = dpToPx(200);

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void runAnimation(View view, int position, boolean belowPrevLast) {
        if (!belowPrevLast) return; //only animate new items
        view.setTranslationY(Y_OFFSET);
        view.setAlpha(0.f);
        view.animate()
                .translationY(0).alpha(1.f)
                .setStartDelay(DELAY * position)
                .setInterpolator(new DecelerateInterpolator(2.f))
                .setDuration(300)
                .start();
    }
}
