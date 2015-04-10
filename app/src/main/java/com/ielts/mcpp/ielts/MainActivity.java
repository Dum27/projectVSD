package com.ielts.mcpp.ielts;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ielts.mcpp.ielts.fragments.AboutFragment;
import com.ielts.mcpp.ielts.fragments.MainFragment;
import com.ielts.mcpp.ielts.fragments.MyTestsFragment;
import com.ielts.mcpp.ielts.fragments.SettingsFragment;
import com.ielts.mcpp.ielts.fragments.TestFragment;
import com.ielts.mcpp.ielts.fragments.UsefulStuffFragment;
import com.ielts.mcpp.ielts.fragments.VocabularyFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;


public class MainActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {

        // set the header image
        this.setDrawerHeaderImage(R.drawable.mat2);

        // create sections
        this.addSection(newSection(getString(R.string.nav_drawer_test), new TestFragment()));
        this.addSection(newSection(getString(R.string.nav_drawer_tests), new MyTestsFragment()));
        this.addSection(newSection(getString(R.string.nav_drawer_vocabulary), new VocabularyFragment()));
        this.addSection(newSection(getString(R.string.nav_drawer_useful_staff), new UsefulStuffFragment()));
        this.addSection(newSection(getString(R.string.nav_drawer_settings), new SettingsFragment()));
        this.addBottomSection(newSection(getString(R.string.nav_drawer_about), new AboutFragment()));

//        this.addBottomSection(newSection(getResources().getString(R.string.about), new AboutFragment()));
//        this.addSection(newSection("Section 3", R.drawable.ic_mic_white_24dp, new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
//        this.addSection(newSection("Section", R.drawable.ic_hotel_grey600_24dp, new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));
//
        //
//        create bottom section
//        this.addBottomSection(newSection("Bottom Section", R.drawable.ic_settings_black_24dp, new Intent(this, Settings.class)));
    }
}
