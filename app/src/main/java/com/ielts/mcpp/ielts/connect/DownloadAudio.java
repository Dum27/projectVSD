package com.ielts.mcpp.ielts.connect;

import android.util.Log;

import com.google.common.io.Files;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jack on 06.05.2015.
 */
public class DownloadAudio {

    public void downloadAudio() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionsAudio");
//        query.whereEqualTo("objectId","U8mCwTHOaC");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject parseObject : objects)
                        saveDownloadedFiles((ParseFile) parseObject.get("audio"));
                } else {
                }
            }
        });
    }

    public void saveDownloadedFiles(final ParseFile parseFile) {
        parseFile.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    Log.d("Jack", parseFile.getName() + "\n" + parseFile.getUrl() );
                    File fileToWriteTo = new File("sdcard/Download/ielts/" + parseFile.getName() + ".mp4");

                    try {
                        Files.write(data, fileToWriteTo);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } else {
                    // something went wrong
                }
            }
        });
    }
}
