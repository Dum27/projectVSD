package com.ielts.mcpp.ielts;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.gc.materialdesign.widgets.Dialog;
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


public class MainActivity extends MaterialNavigationDrawer implements MyTestsFragment.OnFragmentInteractionListener {

    public static ProgressDialog pd;
    public static MaterialSection testSection;
    public static int sTextTask;
    public static String sTestFileDate;
    // PRODUCT & SUBSCRIPTION IDS
    public static final String PRODUCT_ID = "com.anjlab.test.iab.s2.p5";
    public static final String SUBSCRIPTION_ID = "com.anjlab.test.iab.subs1";
    public static final String LICENSE_KEY = null; // PUT YOUR MERCHANT KEY HERE;

    public static boolean readyToPurchase = false;
    public BillingProcessor bp;


    @Override
    public void init(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/blue.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
        Log.d("Jack", "!! " + getApplicationContext().getPackageName());
        // set the header image
        this.setDrawerBackgroundColor(Color.parseColor("#FF616261"));
        this.setDrawerHeaderImage(R.mipmap.logo_rectangle);
        // create sections
        this.addSection(newSection(getString(R.string.nav_drawer_test), getResources().getDrawable(R.drawable.icon_test1png),
                new LayerTestTaskFragment()).setSectionColor(Color.parseColor("#7A0C66")));
        this.addSection(newSection(getString(R.string.nav_drawer_tests), getResources().getDrawable(R.drawable.icon_test1png),
                new MyTestsFragment()).setSectionColor(Color.parseColor("#7A0C66")));
        this.addSection(newSection(getString(R.string.nav_drawer_vocabulary), getResources().getDrawable(R.drawable.icon_vocab1),
                new VocabularyFragment()).setSectionColor(Color.parseColor("#7A0C66")));
        this.addSection(newSection(getString(R.string.nav_drawer_useful_staff),
                getResources().getDrawable(R.drawable.icon_useful_stuff1),
                new LayerStaffFragment()).setSectionColor(Color.parseColor("#7A0C66")));
        this.addSection(newSection(getString(R.string.nav_drawer_settings), getResources().getDrawable(R.drawable.icon_settings1),
                new SettingsFragment()).setSectionColor(Color.parseColor("#7A0C66")));
        this.addBottomSection(newSection(getString(R.string.nav_drawer_about),
                new LayerAboutFrament()).setSectionColor(Color.parseColor("#7A0C66")));
        bp = new BillingProcessor(this, MainActivity.LICENSE_KEY, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(String productId, TransactionDetails details) {
//                showToast("onProductPurchased: " + productId);
//                updateTextViews();
            }

            @Override
            public void onBillingError(int errorCode, Throwable error) {
//                showToast("onBillingError: " + Integer.toString(errorCode));
            }

            @Override
            public void onBillingInitialized() {
//                showToast("onBillingInitialized");
                MainActivity.readyToPurchase = true;
//                updateTextViews();
            }

            @Override
            public void onPurchaseHistoryRestored() {
//                showToast("onPurchaseHistoryRestored");
//                for(String sku : bp.listOwnedProducts())
//                    Log.d(LOG_TAG, "Owned Managed Product: " + sku);
//                for(String sku : bp.listOwnedSubscriptions())
//                    Log.d(LOG_TAG, "Owned Subscription: " + sku);
//                updateTextViews();
            }
        });
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
    public void setPageColor(int primaryColor, int darkColor){
        this.changeToolbarColor(primaryColor, darkColor);
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            final Dialog dialog = new Dialog(this, "Confirm", "You really want to exit?\n\n");
            // Set accept click listenner
            dialog.addCancelButton("BACK");

            dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.super.onBackPressed();
                }
            });
            dialog.setOnCancelButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
//            Toast.makeText(this,"PressBack again",Toast.LENGTH_SHORT).show();
////            super.onBackPressed();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();
        super.onDestroy();
    }

    @Override
    public boolean onFragmentInteraction() {
        bp.purchase(this, PRODUCT_ID);
        return bp.isPurchased(PRODUCT_ID);
    }
}
