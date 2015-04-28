package com.ielts.mcpp.ielts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.ielts.mcpp.ielts.fragments.LayerAboutFrament;
import com.ielts.mcpp.ielts.fragments.LayerStaffFragment;
import com.ielts.mcpp.ielts.fragments.LayerTestTaskFragment;
import com.ielts.mcpp.ielts.fragments.MyTestsFragment;
import com.ielts.mcpp.ielts.fragments.SettingsFragment;
import com.ielts.mcpp.ielts.fragments.VocabularyFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends MaterialNavigationDrawer {

    public static ProgressDialog pd;
    public static MaterialSection testSection;

    @Override
    public void init(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/blue.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
        // set the header image
        this.setDrawerHeaderImage(R.mipmap.logo_rectangle);

        // create sections
        testSection = newSection(getString(R.string.nav_drawer_test), new LayerTestTaskFragment());
        this.addSection(testSection);
        this.addSection(newSection(getString(R.string.nav_drawer_tests), new MyTestsFragment()));
        this.addSection(newSection(getString(R.string.nav_drawer_vocabulary), new VocabularyFragment()));
        this.addSection(newSection(getString(R.string.nav_drawer_useful_staff), new LayerStaffFragment()));
        this.addSection(newSection(getString(R.string.nav_drawer_settings), new SettingsFragment()));
        this.addBottomSection(newSection(getString(R.string.nav_drawer_about), new LayerAboutFrament()));

//        this.addBottomSection(newSection(getResources().getString(R.string.about), new AboutFragment()));
//        this.addSection(newSection("Section 3", R.drawable.ic_mic_white_24dp, new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
//        this.addSection(newSection("Section", R.drawable.ic_hotel_grey600_24dp, new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));
//
        //
//        create bottom section
//        this.addBottomSection(newSection("Bottom Section", R.drawable.ic_settings_black_24dp, new Intent(this, Settings.class)));
    }

    public void setPageTitle(String title){
        this.setTitle(title);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
