package com.ielts.mcpp.ielts.fragments;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.adapters.MyTestsRecyclerAdapter;
import com.ielts.mcpp.ielts.adapters.RecyclerItemClickListener;
import com.ielts.mcpp.ielts.connect.AudioSend;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTestsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> finishTests;
    private OnFragmentInteractionListener mListener;

    public MyTestsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_my_tests, container, false);
        finishTests = new ArrayList<>();
        traverse(new File(Environment.getExternalStorageDirectory() + "/" + "ielts_tests"));
        TextView emptyView = (TextView) v.findViewById(R.id.empty_view_my_test);
        if (finishTests.size() == 0)
            emptyView.setVisibility(View.VISIBLE);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView textView = (TextView) view.findViewById(R.id.text_test);
                if (MainActivity.readyToPurchase) {
                    if (checkPayment()) {
                        String filePathToSend = Environment.getExternalStorageDirectory() + "/ielts_tests/merge_T1_" +
                                textView.getText() + ".mp4";
                        String filePathToSend2 = Environment.getExternalStorageDirectory() + "/ielts_tests/merge_T2_" +
                                textView.getText() + ".mp4";
                        String filePathToSend3 = Environment.getExternalStorageDirectory() + "/ielts_tests/merge_T3_" +
                                textView.getText() + ".mp4";
                        new AudioSend().sendAudio(getActivity(), filePathToSend, filePathToSend2,
                                filePathToSend3);
                    }
                }
            }
        }));
//        mRecyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        TextView textView = (TextView) view.findViewById(R.id.text_test);
//                        String filePathToSend = Environment.getExternalStorageDirectory() + "/ielts_tests/" +
//                                textView.getText();
//                        String filePathToSend2 = Environment.getExternalStorageDirectory() + "/ielts_tests/" +
//                                textView.getText().toString().substring(0, 7 ) + "2" + textView.getText().toString().substring(8);
//                        String filePathToSend3 = Environment.getExternalStorageDirectory() + "/ielts_tests/" +
//                                textView.getText().toString().substring(0, 7 ) + "3" + textView.getText().toString().substring(8);
////                        /storage/emulated/0/intro-f2ame-answ1.mp4
////                                            merge_T1
//                        new AudioSend().sendAudio(getActivity(),filePathToSend, filePathToSend2,
//                                filePathToSend3);
//
//                    }
//                })
//        );
        mRecyclerView.setOnScrollListener
                (new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        Log.d("taras", "  dx :" + dx);
                        Log.d("taras", "  dy :" + dy);
                    }
                });
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyTestsRecyclerAdapter(finishTests, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    public void findThreeTests(File dir) {

    }

    public void traverse(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; ++i) {
                File file = files[i];
                if (file.isDirectory()) {
                    traverse(file);
                } else {
                    if (file.getName().contains("merge_T1"))
                        finishTests.add(file.getName().substring(9, file.getName().length() - 4));
                }
            }
        }
    }

    public boolean checkPayment() {
        if (mListener != null) {
            return mListener.onFragmentInteraction();
        }
        return false;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public boolean onFragmentInteraction();
    }
}
