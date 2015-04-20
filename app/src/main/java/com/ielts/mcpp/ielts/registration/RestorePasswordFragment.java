package com.ielts.mcpp.ielts.registration;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.connect.RegistrationAuthorization;

/**
 * Created by Jack on 4/20/2015.
 */
public class RestorePasswordFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_restore_password, container, false);
        ButtonRectangle btnRestore = (ButtonRectangle) view.findViewById(R.id.button_restore_password);
        btnRestore.setOnClickListener(restorePasswordButtonListener);
        return view;
    }
    View.OnClickListener restorePasswordButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText mail = (EditText) view.findViewById(R.id.edit_restore_password);
            new RegistrationAuthorization().restorePasswrod(mail.getText().toString(), getActivity());
        }};
}
