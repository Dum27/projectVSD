package com.ielts.mcpp.ielts.registration;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timer(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void timer (final Context context){
        final int timeDelta = 4000;
        new CountDownTimer(timeDelta, 1000) {
            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                startActivity(new Intent(context, MainActivity.class));
            }
        }.start();
    }
}
