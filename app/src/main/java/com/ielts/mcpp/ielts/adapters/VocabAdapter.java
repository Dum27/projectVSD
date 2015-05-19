package com.ielts.mcpp.ielts.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ielts.mcpp.ielts.R;

/**
 * Created by taras on 19.05.2015.
 */
public class VocabAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String [] mEngWords;
    private String [] mChiWords;

    public VocabAdapter() {
    }

    public VocabAdapter(Context context, String[] mEngWords, String[] mChiWords) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mEngWords = mEngWords;
        this.mChiWords = mChiWords;
    }

    @Override
    public int getCount() {
        return mEngWords.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.vocab_list_item, parent, false);
        }
        TextView mWordEng = (TextView) view.findViewById(R.id.word_eng);
        TextView mWordChi = (TextView) view.findViewById(R.id.word_chi);

        Log.d("taras","length :"+mEngWords.length+" position :"+position);
        mWordEng.setText(mEngWords[position]);
        mWordChi.setText(mChiWords[position]);

        return view;
    }
}
