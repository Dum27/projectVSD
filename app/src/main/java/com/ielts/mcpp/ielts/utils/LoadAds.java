package com.ielts.mcpp.ielts.utils;

import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ielts.mcpp.ielts.R;

/**
 * Created by Jack on 11.05.2015.
 */
public class LoadAds {

    public LoadAds(View view, int idResourceView) {
        AdView mAdView = (AdView) view.findViewById(idResourceView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
