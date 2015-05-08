package com.ielts.mcpp.ielts.connect;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.common.io.ByteStreams;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.registration.WelcomeActivity;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jack on 4/16/2015.
 */
public class AudioSend {
    ParseFile file;
    int id;

    public AudioSend() {
    }

    public void sendAudio(final Context context, String filePath1,
                          String filePath2, String filePath3) {

        String[] audioFields     = {"audio", "audio_file1", "audio_file2"};
        String[] audioFilesPath  = {filePath1, filePath2, filePath3};
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter= new SimpleDateFormat("dd_MM_yyyy_hh_mm_a"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        Log.d("Jack", "!!!!!" + dateNow);
        // Audio
        ParseObject jobApplication = new ParseObject("audio");
        byte[] data = new byte[Byte.MAX_VALUE];
        for (int i = 0; i < 3; i++) {
            try {
                data = ByteStreams.toByteArray(new FileInputStream(
//                        Environment.getExternalStorageDirectory() + "/" + audioFilesPath[i]));     on Release
                          audioFilesPath[i]));
                file = new ParseFile(dateNow + "_part" + i + ".mp4", data);
                file.saveInBackground();
                jobApplication.put(audioFields[i], file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        NotificationManager notifyManager;
        NotificationCompat.Builder builder;
        id = 1;
        notifyManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("Sending...")
                .setContentText("Now app send your test")
                .setSmallIcon(R.drawable.nigers);
        builder.setProgress(0, 0, true);
        notifyManager.notify(id, builder.build());
        jobApplication.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.d("Jack", "Success");
                    setPostExecuteNotification(context);
                } else {
                    Log.d("Jack", "noooo!  " + e.getMessage());
                }
            }
        });



//Retrieve
//        ParseFile applicantResume = (ParseFile) jobApplication.get("mediaurl");
//        applicantResume.getDataInBackground(new GetDataCallback() {
//            public void done(byte[] data, ParseException e) {
//                if (e == null) {
//                    // data has the bytes for the resume
////                        ImageView image = (ImageView) mainView.findViewById(R.id.testImageViewParse);
////                        Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
////                        image.setImageBitmap(bMap);
//
//                } else {
//                    Log.d("Jack", e.getMessage());
//                }
//            }
//        });
    }
    private void setPostExecuteNotification(Context context){
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent notificationIntent = new Intent(context, WelcomeActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(context)
                .setContentTitle("Your test successfully send")
                .setSmallIcon(R.drawable.nigers)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setContentIntent(intent);

        Notification notificationn = notification.getNotification();
        notificationn.defaults |= Notification.DEFAULT_VIBRATE;
        notificationn.ledARGB = Color.BLUE;
        notificationn.ledOnMS = 500;
        notificationn.ledOffMS = 2000;
        notificationn.flags |= Notification.FLAG_SHOW_LIGHTS;
        notificationManager.notify(1, notificationn);
    }
}
