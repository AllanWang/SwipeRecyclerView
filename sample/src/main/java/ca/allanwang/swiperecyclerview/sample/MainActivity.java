package ca.allanwang.swiperecyclerview.sample;

import android.support.annotation.Nullable;

import com.mikepenz.materialdrawer.AccountHeaderBuilder;

import ca.allanwang.capsule.library.activities.CapsuleActivityFrame;
import ca.allanwang.capsule.library.interfaces.CDrawerItem;

/**
 * Created by Allan Wang on 2017-02-06.
 */

public class MainActivity extends CapsuleActivityFrame {
    @Nullable
    @Override
    protected AccountHeaderBuilder getAccountHeaderBuilder() {
        return null;
    }

    @Override
    protected CDrawerItem[] getDrawerItems() {
        return new CDrawerItem[0];
    }
}
