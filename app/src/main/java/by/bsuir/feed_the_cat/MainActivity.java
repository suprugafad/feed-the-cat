package by.bsuir.feed_the_cat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button feedButton;
    Button homeButton;
    TextView textValueOfSatiety;
    ImageView catImg;

    int satiety;

    static User user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.title);

        feedButton = (Button) findViewById(R.id.feed_button);
        homeButton = (Button) findViewById(R.id.home_button);
        textValueOfSatiety = (TextView) findViewById(R.id.value_of_satiety);
        catImg = (ImageView) findViewById(R.id.cat_img);

        if (RegistrationActivity.flag) {
            user = RegistrationActivity.getUser();
            RegistrationActivity.flag = false;
        } else {
            user = AuthActivity.getUser();
        }

        satiety = user.highScore;
        textValueOfSatiety.setText(Integer.toString(satiety));

        Intent stActivity = new Intent(this, StartActivity.class);

        final Animation animationRotateCenter = AnimationUtils.loadAnimation(this, R.anim.cat_rotation);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("Users");

        View.OnClickListener oclFeedButton = new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                satiety += 1;
                textValueOfSatiety.setText(Integer.toString(satiety));

                if (satiety % 15 == 0)
                    catImg.startAnimation(animationRotateCenter);
            }
        };

        View.OnClickListener oclHomeButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.highScore = satiety;
                users.child(user.getName()).setValue(user);
                startActivity(stActivity);
            }
        };

        user.highScore = satiety;

        feedButton.setOnClickListener(oclFeedButton);
        homeButton.setOnClickListener(oclHomeButton);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        // first parameter is the file for icon and second one is menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // We are using switch case because multiple icons can be kept
        switch (item.getItemId()) {
            case R.id.shareButton:

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                // type of the content to be shared
                sharingIntent.setType("text/plain");

                // Body of the content
                String shareBody = "My result: " + satiety;

                // subject of the content. you can share anything
                String shareSubject = "Result in feed the cat";

                // passing body of the content
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                // passing subject of the content
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static User getUser(){
        return user;
    }

}