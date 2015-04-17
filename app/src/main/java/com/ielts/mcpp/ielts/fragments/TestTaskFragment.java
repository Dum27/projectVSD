package com.ielts.mcpp.ielts.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.rengwuxian.materialedittext.Colors;

import java.util.LinkedHashMap;


public class TestTaskFragment extends Fragment {
    int numberTest = 1;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test_task, container, false);
        ButtonRectangle btnPlay = (ButtonRectangle) view.findViewById(R.id.btn_play_test);
        ButtonRectangle btnRecord = (ButtonRectangle) view.findViewById(R.id.btn_record_test);
        ButtonRectangle btnNext = (ButtonRectangle) view.findViewById(R.id.btn_next_test);
        btnPlay.setOnClickListener(playListener);
        btnRecord.setOnClickListener(recordListener);
        btnNext.setOnClickListener(nextTestListener);
        return view;
    }

    View.OnClickListener recordListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new TaskRecordExample().execute();
        }
    };
    View.OnClickListener playListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new TaskPlayExample().execute();

        }
    };
    View.OnClickListener nextTestListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            numberTest++;
            if (numberTest > 3){
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new FinishFragment());
                fragmentTransaction.commit();
            } else {
                MainActivity.testSection.setSectionColor(Color.parseColor("#03a9f4"));
                TextView textView = (TextView) view.findViewById(R.id.text_test_my);
                textView.setText("\n It`s speaking test # " + numberTest + " \n \n - Answer the questions ");
            }
        }
    };

    class TaskPlayExample extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity.pd = ProgressDialog.show(getActivity(), "Question", "Here must be question", true, false);
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            MainActivity.pd.dismiss();
        }
    }
    class TaskRecordExample extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity.pd = ProgressDialog.show(getActivity(), "Answer", "Here you must record the answer", true, false);
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            MainActivity.pd.dismiss();
        }
    }

}
