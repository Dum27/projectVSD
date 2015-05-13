package com.ielts.mcpp.ielts.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.adapters.MyTestsRecyclerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTestsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> finishTests;

    public MyTestsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_my_tests, container, false);
        finishTests = new ArrayList<>();
        finishTests.add("Test 1");
        finishTests.add("Test 2");
        finishTests.add("Test 3");
        traverse(new File(Environment.getExternalStorageDirectory() + "/" + "ielts_tests"));
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
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

    public void traverse(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; ++i) {
                File file = files[i];
                if (file.isDirectory()) {
                    traverse(file);
                } else {
//                    Log.d("Jack", file.getName());
                    finishTests.add(file.getName());
                }
            }
        }
    }

//    new String[]{
//        "10-04-15 12:55",
//                "18-04-15 11:01",
//                "23-04-15 16:20",
//                "30-04-15 18:45",
//                " 2-05-15 12:35",
//                "11-05-15  9:55",
//                "11-05-15 14:05",
//                "10-04-15 12:55",
//                "18-04-15 11:01",
//                "23-04-15 16:20",
//                "30-04-15 18:45",
//                " 2-05-15 12:35",
//                "11-05-15  9:55",
//                "11-05-15 14:05"}
}
