package by.bsuir.feed_the_cat;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.title);

        feedButton = (Button) findViewById(R.id.feed_button);
        homeButton = (Button) findViewById(R.id.home_button);
        textValueOfSatiety = (TextView) findViewById(R.id.value_of_satiety);
        catImg = (ImageView) findViewById(R.id.cat_img);

        satiety = parseInt(textValueOfSatiety.getText().toString());

        Intent stActivity = new Intent(this, StartActivity.class);

        final Animation animationRotateCenter = AnimationUtils.loadAnimation(
                this, R.anim.cat_rotation);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello");

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
                startActivity(stActivity);
            }
        };
        feedButton.setOnClickListener(oclFeedButton);
        homeButton.setOnClickListener(oclHomeButton);

    }

}