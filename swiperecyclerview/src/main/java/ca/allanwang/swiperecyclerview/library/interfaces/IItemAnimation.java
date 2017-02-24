package ca.allanwang.swiperecyclerview.library.interfaces;

import android.view.View;

/**
 * Created by Allan Wang on 2017-02-24.
 */

public interface IItemAnimation {
    void runAnimation(View view, int position, boolean belowPrevLast);
}
