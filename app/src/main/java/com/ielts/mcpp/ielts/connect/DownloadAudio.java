package com.ielts.mcpp.ielts.connect;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
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
    ProgressDialog progressDialog;
    int sizeOfList;

    public void downloadAudio(Context context) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionsAudio");
        query.setLimit(1000);
//        query.whereEqualTo("objectId","U8mCwTHOaC");
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Files download");
        progressDialog.show();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                sizeOfList = objects.size();
                int i = 0;
                if (e == null) {
                    for (ParseObject parseObject : objects) {
                        i++;
                        saveDownloadedFiles((ParseFile) parseObject.get("audio"), i);
                    }
                } else {
                }
            }
        });
    }

    public void saveDownloadedFiles(final ParseFile parseFile, final int counter) {
        parseFile.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, ParseException e) {
                try {
                    if (e == null) {
                        File folder        = new File(Environment.getExternalStorageDirectory() + "/questions");
                        File fileToWriteTo = new File(Environment.getExternalStorageDirectory() + "/questions/"
                                + parseFile.getName().substring(42));
                        if (!folder.exists())
                            folder.mkdir();
                        Files.write(data, fileToWriteTo);

                        if (counter == sizeOfList)
                            progressDialog.dismiss();
                    } else {
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
