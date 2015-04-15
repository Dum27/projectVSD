package com.ielts.mcpp.ielts.fragments.registration;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.fragments.TestFragment;

/**
 * Created by Jack on 4/15/2015.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btnLogin = (Button) view.findViewById(R.id.button_login);
        btnLogin.setOnClickListener(finishTestListener);
        return view;
    }

    View.OnClickListener finishTestListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new TestFragment());
            fragmentTransaction.commit();

        }
    };
}
