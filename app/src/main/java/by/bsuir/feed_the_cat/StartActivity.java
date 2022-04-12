package by.bsuir.feed_the_cat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    Button newGameButton;
    Button resultsButton;
    Button helpButton;
    Button aboutAuthorButton;

    TextView mainResultsValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setTitle(R.string.title);

        newGameButton = (Button) findViewById(R.id.new_game_button);
        resultsButton = (Button) findViewById(R.id.results_button);
        helpButton = (Button) findViewById(R.id.help_button);
        aboutAuthorButton = (Button) findViewById(R.id.about_author_button);

        mainResultsValue = (TextView) findViewById(R.id.main_result_value);

        Intent newGameIntent = new Intent(this, RegistrationActivity.class);
        Intent helpIntent = new Intent(this, HelpActivity.class);


        View.OnClickListener oclButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case (R.id.new_game_button):
                        startActivity(newGameIntent);
                        break;
//                    case (R.id.results_button):
//
//                        break;
                    case (R.id.help_button):
                        startActivity(helpIntent);
                        break;
//                    case (R.id.about_author_button):
//                        showAuthorWindow();
//                        break;
                }
            }
        };

        newGameButton.setOnClickListener(oclButton);
        resultsButton.setOnClickListener(oclButton);
        helpButton.setOnClickListener(oclButton);
        aboutAuthorButton.setOnClickListener(oclButton);
    }

//    private void showAuthorWindow() {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle("ЛР №1");
//        dialog.setMessage("Пойда Александрина 951007");
//
//        dialog.setNegativeButton("Ok.", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//
//    }
}