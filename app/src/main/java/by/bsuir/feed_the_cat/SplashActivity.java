package by.bsuir.feed_the_cat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    ImageView raw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        raw = (ImageView) findViewById(R.id.raw);

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        anim.setRepeatCount(-1);
        raw.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, StartActivity.class);
                startActivity(i);
                finish();
            }
        }, 3*1000);
    }
}