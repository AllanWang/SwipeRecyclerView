package ca.allanwang.swiperecyclerview.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;

import ca.allanwang.capsule.library.activities.CapsuleActivityFrame;
import ca.allanwang.capsule.library.interfaces.CDrawerItem;
import ca.allanwang.capsule.library.item.DrawerItem;
import ca.allanwang.swiperecyclerview.sample.fragments.HomeFragment;
import ca.allanwang.swiperecyclerview.sample.fragments.PairFragment;

/**
 * Created by Allan Wang on 2017-02-06.
 */

public class MainActivity extends CapsuleActivityFrame {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cFab.hide(); //no fab for now
    }


    @Nullable
    @Override
    protected AccountHeaderBuilder getAccountHeaderBuilder() {
        return null;
    }

    @Override
    protected CDrawerItem[] getDrawerItems() {
        return new CDrawerItem[]{
                new DrawerItem(R.string.home, GoogleMaterial.Icon.gmd_dashboard, true, HomeFragment::new),
                new DrawerItem(R.string.pair, GoogleMaterial.Icon.gmd_format_paint, true, PairFragment::new)
        };
    }
}
