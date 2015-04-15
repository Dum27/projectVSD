package com.ielts.mcpp.ielts.fragments;


import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.google.common.io.ByteStreams;
import com.ielts.mcpp.ielts.R;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {
    View mainView;

    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_test, container, false);
        ButtonRectangle button = (ButtonRectangle) mainView.findViewById(R.id.btn_test_me);
//        button.setOnClickListener(buttonListener);
        button.setOnClickListener(testMeListener);

        return mainView;
    }

    View.OnClickListener testMeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new TestTaskFragment());
            fragmentTransaction.commit();

        }
    };

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            // IMAGE!!!
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] data = stream.toByteArray();



            // Audio

//            byte[] data = new byte[8192];
            byte[] data = new byte[Byte.MAX_VALUE];
            try {
                data = ByteStreams.toByteArray(new FileInputStream("/sdcard/Download/12.mp3"));
            } catch (IOException e) {
                Log.d("Jack", e.getMessage());
                e.printStackTrace();
            }
            ParseFile file = new ParseFile("OVK.mp3", data);
            file.saveInBackground();


            ParseObject jobApplication = new ParseObject("Tickles");
            jobApplication.put("mediatype", "sound");
//            jobApplication.put("ticklecreatedid","Mayura");
            jobApplication.put("mediaurl", file);
            jobApplication.saveInBackground();

//Retrieve
            ParseFile applicantResume = (ParseFile)jobApplication.get("mediaurl");
            applicantResume.getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        // data has the bytes for the resume
//                        ImageView image = (ImageView) mainView.findViewById(R.id.testImageViewParse);
//                        Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                        image.setImageBitmap(bMap);

                    } else {
                        Log.d("Jack", e.getMessage());
                    }
                }
            });
        }
    };


}
