package by.bsuir.feed_the_cat;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button feedButton;
    TextView textValueOfSatiety;
    ImageView cat;

    int satiety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.title);

        feedButton = (Button) findViewById(R.id.feed_button);
        textValueOfSatiety = (TextView) findViewById(R.id.value_of_satiety);

        satiety = parseInt(textValueOfSatiety.getText().toString());

        View.OnClickListener oclFeedButton = new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                satiety += 1;
                textValueOfSatiety.setText(Integer.toString(satiety));

//                if (satiety % 15 == 0) {
//
//                }

            }
        };

        feedButton.setOnClickListener(oclFeedButton);

    }

}