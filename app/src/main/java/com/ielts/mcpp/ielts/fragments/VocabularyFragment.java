package com.ielts.mcpp.ielts.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ielts.mcpp.ielts.R;


public class VocabularyFragment extends Fragment {

    private ListView listView;
    ArrayAdapter<String> adapter;
    EditText inputSearch;
    private RecyclerView.LayoutManager mLayoutManager;
    String products[] = {"relationship", "outsource", "congress", "problem", "stand", "code",
            "restrict", "highbrow", "formula", "community", "fashion", "action", "statement"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vocabulary, null);
        listView = (ListView) v.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, products);
        listView.setAdapter(adapter);
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
                String item = (String) adapter.getItem(pos);
                Toast.makeText(getActivity(), item + " выбран", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}