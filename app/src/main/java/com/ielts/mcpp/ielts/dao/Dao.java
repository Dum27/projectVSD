package com.ielts.mcpp.ielts.dao;

import android.app.FragmentManager;
import android.content.Context;

import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.connect.DownloadAudio;

/**
 * Created by Jack on 08.05.2015.
 */
public class Dao {
    Context context;
    SecurePreferences preferences;
    String prefDownloadAudioChecker;

    public Dao(Context context) {
        this.context = context;
        prefDownloadAudioChecker = context.getResources().getString(R.string.check_download_audio);
        preferences = new SecurePreferences(context, "preferences", "CFC9A7AE4EE8BC9A325197C93AC56", true);
    }

    public String checkAudioExist(){
        return preferences.getString(prefDownloadAudioChecker);
    }

    public void saveAudio(){
        new DownloadAudio().downloadAudio(context);
    }

    public void setChecker(){
        preferences.put(prefDownloadAudioChecker, "saved");
    }

}
