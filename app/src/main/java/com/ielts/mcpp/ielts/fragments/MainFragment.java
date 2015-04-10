package com.ielts.mcpp.ielts.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.ielts.mcpp.ielts.R;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.json.JSONException;

public class MainFragment extends Fragment {

    /***
     * With {@link com.octo.android.robospice.UncachedSpiceService} there is no cache management. Remember to declare it in
     * AndroidManifest.xml
     */
//    private SpiceManager spiceManager = new SpiceManager(SampleInMemorySpiceService.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        spiceManager.start(getActivity());
////        if (wordField.getText() != null) {
////            spiceManager.addListenerIfPending(String.class, wordField.getText().toString(), new NetworkRequestListener());
////        }
//    }
//
//    @Override
//    public void onStop() {
//        if (spiceManager.isStarted()) {
//            spiceManager.shouldStop();
//        }
//        super.onStop();
//    }

//    private void performRequest() {
////        resultTextView.setText("");
//
//        MainFragment.this.getActivity().setProgressBarVisibility(true);
//
//        NetworkRequest request = new NetworkRequest();
//        spiceManager.execute(request, "", DurationInMillis.ALWAYS_RETURNED, new NetworkRequestListener());
//    }


//    private final class NetworkRequestListener implements RequestListener<String> {
//        @Override
//        public void onRequestFailure(SpiceException spiceException) {
//            MainFragment.this.getActivity().setProgressBarIndeterminateVisibility(false);
//        }
//
//        @Override
//        public void onRequestSuccess(String result) {
//            MainFragment.this.getActivity().setProgressBarIndeterminateVisibility(false);
//            try {
//                JsonAdapter jsonAdapter = new JsonAdapter(result);
//                CurrencyDAO currencyDAO = new CurrencyDAOImpl(getActivity());
//
//                currencyDAO.saveData(jsonAdapter.getInterBankCur(), jsonAdapter.getNationalBankCur());
//                for (InterBank item : currencyDAO.getInterBankCurrency()){
//                    Log.d("Jack", item.getCurrency() + " " + item.getAsk() + " " + item.getBid());
//                    Toast.makeText(getActivity(), "JJ " + item.getCurrency(), Toast.LENGTH_LONG ).show();
//                    break;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
