package com.ielts.mcpp.ielts.connect;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;
import com.google.common.io.Files;
import com.ielts.mcpp.ielts.dao.Dao;
import com.ielts.mcpp.ielts.registration.WelcomeActivity;
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
    Context context;

    public void downloadAudio(final Context context) {
        this.context = context;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionsAudio");
        query.setLimit(1000);
//        query.whereEqualTo("objectId","U8mCwTHOaC");
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Files download");
        progressDialog.show();
        progressDialog.setCancelable(false);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                int i = 0;
                if (e == null) {
                    sizeOfList = objects.size();
                    for (ParseObject parseObject : objects) {
                        i++;
                        saveDownloadedFiles((ParseFile) parseObject.get("audio"), i);
                    }
                } else {
                    progressDialog.dismiss();
//                    new DownloadDialogFragment().show(fragmentManager, null);
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

                        if (counter == sizeOfList) {
                            progressDialog.dismiss();
                            new Dao(context).setChecker();
                        }
                    } else {
                        downloadAudio(context);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
//    public class DownloadDialogFragment extends DialogFragment {
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle("Download failed")
//                    .setPositiveButton("Reload", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                        }
//                    })
//                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            Activity activity = (Activity) DownloadAudio.this.context;
//                            activity.onBackPressed();
//                        }
//                    });
//            return builder.create();
//        }
//    }
}
