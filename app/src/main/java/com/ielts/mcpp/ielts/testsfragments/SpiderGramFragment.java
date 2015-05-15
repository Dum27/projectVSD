package com.ielts.mcpp.ielts.testsfragments;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpiderGramFragment extends Fragment {


    public SpiderGramFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spider_gram, container, false);
        ((MainActivity) this.getActivity()).setPageTitle("Spidergram");
        ((MainActivity) this.getActivity()).setPageColor(Color.parseColor("#7A0C66"),
                Color.parseColor("#7A0C66"));
        ButtonRectangle backBtn = (ButtonRectangle) view.findViewById(R.id.btn_spidergram_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });
        new LoadAds(view, R.id.adViewSpiderGram);
        return view;
    }



}
