package com.ielts.mcpp.ielts.utils;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.ielts.mcpp.ielts.R;

/**
 * Created by Jack on 12.05.2015.
 */
public class LoadInterstitialAds {

    InterstitialAd mInterstitialAd;

    public LoadInterstitialAds(Context context) {
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.banner_interstitial_ad));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();
    }
    public void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void show() {
        if (mInterstitialAd.isLoaded())
            mInterstitialAd.show();
    }
}
