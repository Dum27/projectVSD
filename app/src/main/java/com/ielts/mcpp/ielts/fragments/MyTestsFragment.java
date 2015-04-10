package com.ielts.mcpp.ielts.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.adapters.MyTestsRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTestsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public MyTestsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_my_tests, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

// use this setting to improve performance if you know that changes
// in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

// use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

// specify an adapter (see also next example)
        mAdapter = new MyTestsRecyclerAdapter(new String[]{
                "10-04-15 12:55",
                "18-04-15 11:01",
                "23-04-15 16:20",
                "30-04-15 18:45",
                " 2-05-15 12:35",
                "11-05-15  9:55",
                "11-05-15 14:05",
                "10-04-15 12:55",
                "18-04-15 11:01",
                "23-04-15 16:20",
                "30-04-15 18:45",
                " 2-05-15 12:35",
                "11-05-15  9:55",
                "11-05-15 14:05"}, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }


}
