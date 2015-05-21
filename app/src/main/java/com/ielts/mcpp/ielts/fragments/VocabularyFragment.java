package com.ielts.mcpp.ielts.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.adapters.VocabAdapter;
import com.ielts.mcpp.ielts.constants.Vocabulary;
import com.ielts.mcpp.ielts.utils.LoadAds;


public class VocabularyFragment extends Fragment {

    private ListView listView;
    private ListView listViewWords;
    View popupView;
    ArrayAdapter<String> adapter;
    VocabAdapter mVocabAdapter;
    EditText inputSearch;

    String [] topicNames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_vocabulary, null);
        topicNames = Vocabulary.topic_names;
        listView = (ListView) v.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, topicNames);
        listView.setAdapter(adapter);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.vocabulary_popup_window, null);
        listViewWords = (ListView) popupView.findViewById(R.id.listViewWords);

        final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        inputSearch = (EditText) v.findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
                popupWindow.dismiss();
                String topic = adapter.getItem(pos);

                TextView title = (TextView) popupView.findViewById(R.id.title);
                title.setText(topic);

                Log.d("taras","topic :"+topic);
                mVocabAdapter = new VocabAdapter(getActivity(),Vocabulary.getEngWords(topic),
                        Vocabulary.getChiWords(topic));
                listViewWords.setAdapter(mVocabAdapter);

                Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(listView, Gravity.CENTER, 0, 0);
            }
        });
        new LoadAds(v, R.id.adViewVocabulary);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}