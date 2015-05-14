package com.ielts.mcpp.ielts.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ielts.mcpp.ielts.R;

import java.util.List;

/**
 * Created by taras on 10.04.2015.
 */
public class MyTestsRecyclerAdapter extends RecyclerView.Adapter<MyTestsRecyclerAdapter.ViewHolder> {
    private List<String> mDataset;
    Context context;
    //    private final View.OnClickListener mOnClickListener = new MyOnClickListener();
    RecyclerView mRecyclerView;

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text_test);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyTestsRecyclerAdapter(List<String> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyTestsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_list_item_my_tests, parent, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
// - get element from your dataset at this position
// - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //    View.OnClickListener MyOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            int itemPosition = mRecyclerView.getChildPosition(v);
//            String item = mDataset.get(itemPosition);
//            Toast.makeText(context, item, Toast.LENGTH_LONG).show();
//        }
//    };

}