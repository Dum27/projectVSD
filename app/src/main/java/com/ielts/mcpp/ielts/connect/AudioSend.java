package com.ielts.mcpp.ielts.connect;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.common.io.ByteStreams;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Jack on 4/16/2015.
 */
public class AudioSend {

    public AudioSend() {
    }

    public void sendAudio(Context context, Environment environment){
        // Audio
//      byte[] data = new byte[8192];
        byte[] data = new byte[Byte.MAX_VALUE];
        try {
            data = ByteStreams.toByteArray(new FileInputStream(environment.getExternalStorageDirectory()));
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

//        jobApplication.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//            }
//        });

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
}
