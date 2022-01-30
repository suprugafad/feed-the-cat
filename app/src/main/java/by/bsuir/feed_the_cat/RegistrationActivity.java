package by.bsuir.feed_the_cat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationActivity extends AppCompatActivity {

    Button registrationButton;
    EditText editName;

//    FirebaseAuth auth;
//    FirebaseDatabase db;
//    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setTitle(R.string.title);

        registrationButton = (Button) findViewById(R.id.registration_button);
        editName = (EditText) findViewById(R.id.editName);

        Intent startGame = new Intent(this, MainActivity.class);

//        auth = FirebaseAuth.getInstance();
//        db = FirebaseDatabase.getInstance();
//        users = db.getReference("Users");

        View.OnClickListener oclStartGameButton = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();

                if (name.length() == 0) {
                    registrationButton.setEnabled(true);
                } else {
                    startActivity(startGame);
                }
            }
        };
        registrationButton.setOnClickListener(oclStartGameButton);
    }

}