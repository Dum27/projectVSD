package com.ielts.mcpp.ielts.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;


public class AboutFragment extends Fragment {

   // ButtonRectangle mBtnRateUs;
    Button mBtnAboutUs;
    Button mBtnFeatures;
    Button mBtnFeedback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, null);
//        mBtnRateUs = (ButtonRectangle) v.findViewById(R.id.btn_rate_us);
        mBtnAboutUs  = (Button) v.findViewById(R.id.btn_about_us);
        mBtnFeatures = (Button) v.findViewById(R.id.btn_features);
        mBtnFeedback = (Button) v.findViewById(R.id.btn_feedback);
        mBtnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.test-me.co";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        mBtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "feedback");
                i.putExtra(Intent.EXTRA_TEXT   , "your feedback");
                try {
                    startActivity(Intent.createChooser(i, "Send mail"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtnFeatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction =
                        getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.about_layer_cont,new FeaturesFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        new LoadAds(v, R.id.adViewAbout);
        return v;
    }


}
