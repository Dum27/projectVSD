package com.ielts.mcpp.ielts.connect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.gc.materialdesign.widgets.Dialog;
import com.google.common.io.Files;
import com.ielts.mcpp.ielts.dao.Dao;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.util.List;

/**
 * Created by Jack on 06.05.2015.
 */
public class DownloadAudio {
    ProgressDialog progressDialog;
    int sizeOfList;
    Context context;
    List<ParseObject> listOfParseFiles;

    public DownloadAudio(Context context) {
        this.context = context;
    }

    public void downloadAudio(final Context context) {
        this.context = context;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionsAudio");
        query.setLimit(1000);
//        query.whereEqualTo("objectId","U8mCwTHOaC");
        final ProgressDialog waitP = new ProgressDialog(context);
        waitP.setTitle("Files download");
        waitP.show();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                waitP.dismiss();
                int i = 0;
                if (e == null) {
                    sizeOfList = objects.size();
                    listOfParseFiles = objects;
                    new DownloadOperation().execute("");
//                    for (ParseObject parseObject : objects) {
//                        i++;
//                        saveDownloadedFiles((ParseFile) parseObject.get("audio"), i);
//                    }
                } else {
                    final Dialog dialog = new Dialog(context, "Download failed", "Try to reload?\n\n");
                    dialog.addCancelButton("Exit");
                    dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            downloadAudio(context);
                        }
                    });
                    dialog.setOnCancelButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Activity activity = (Activity) DownloadAudio.this.context;
                            activity.onBackPressed();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    private class DownloadOperation extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            int i = 0;
            for (ParseObject object : listOfParseFiles) {
                saveDownloadedFiles((ParseFile) object.get("audio"), i);
                i++;
                publishProgress(i);
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMax(listOfParseFiles.size());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Files downloading, please wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);

        }

    }

    public void saveDownloadedFiles(final ParseFile parseFile, final int counter) {
        File folder = new File(Environment.getExternalStorageDirectory() + "/questions");
        final File fileToWriteTo = new File(Environment.getExternalStorageDirectory() + "/questions/"
                + parseFile.getName().substring(42));
        if (!folder.exists())
            folder.mkdir();
        try {
            Log.d("Jack", parseFile.getName());
            Files.write(parseFile.getData(), fileToWriteTo);
        } catch (Exception e) {
            Log.d("Jack", e.getMessage());
        }

        if (counter == sizeOfList) {
//            progressDialog.dismiss();
            new Dao(context).setChecker();

        }
    }
//    public void saveDownloadedFiles(final ParseFile parseFile, final int counter) {
//        parseFile.getDataInBackground(new GetDataCallback() {
//            @Override
//            public void done(final byte[] data, ParseException e) {
//                try {
//                    if (e == null) {
//                        File folder = new File(Environment.getExternalStorageDirectory() + "/questions");
//                        final File fileToWriteTo = new File(Environment.getExternalStorageDirectory() + "/questions/"
//                                + parseFile.getName().substring(42));
//                        if (!folder.exists())
//                            folder.mkdir();
//                        Files.write(data, fileToWriteTo);
//
//                        if (counter == sizeOfList) {
//                            progressDialog.dismiss();
//                            new Dao(context).setChecker();
//                        }
//                    } else {
//                        Log.d("Jack", e.getMessage() + "!!!!!!!!!!!!!");
//                        downloadAudio(context);
//                    }
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
//    }
}
