package com.ielts.mcpp.ielts.fragments;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;

public class FinishFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish, container, false);
        ButtonRectangle btnFinish = (ButtonRectangle) view.findViewById(R.id.btn_finish_me);
        btnFinish.setOnClickListener(finishTestListener);
        return view;
    }

    View.OnClickListener finishTestListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new TestFragment());
            fragmentTransaction.commit();

        }
    };


}
