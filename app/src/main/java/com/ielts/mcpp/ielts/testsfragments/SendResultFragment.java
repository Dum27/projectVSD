package com.ielts.mcpp.ielts.testsfragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.connect.AudioSend;
import com.ielts.mcpp.ielts.fragments.LayerTestTaskFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendResultFragment extends Fragment {


    public SendResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_result, container, false);
        ((MainActivity) this.getActivity()).setPageColor(Color.parseColor("#7A0C66"), Color.BLACK);
        ((MainActivity) this.getActivity()).setPageTitle("Finished!");
        final MainActivity mainActivity = ((MainActivity) this.getActivity());
        Log.d("Jack", MainActivity.sTestFileDate);

        ButtonRectangle buttonSend = (ButtonRectangle) view.findViewById(R.id.btn_fragment_send);
        ButtonRectangle buttonSave = (ButtonRectangle) view.findViewById(R.id.btn_fragment_send_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
        return view;
    }

    private void send() {
        String filePathToSend = Environment.getExternalStorageDirectory() + "/ielts_tests/merge_T1_" +
                MainActivity.sTestFileDate + ".mp4";
        String filePathToSend2 = Environment.getExternalStorageDirectory() + "/ielts_tests/merge_T2_" +
                MainActivity.sTestFileDate + ".mp4";
        String filePathToSend3 = Environment.getExternalStorageDirectory() + "/ielts_tests/merge_T3_" +
                MainActivity.sTestFileDate + ".mp4";
        new AudioSend().sendAudio(getActivity(), filePathToSend, filePathToSend2,
                filePathToSend3);
    }


}
