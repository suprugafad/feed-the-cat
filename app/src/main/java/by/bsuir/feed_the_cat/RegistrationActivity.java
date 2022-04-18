package by.bsuir.feed_the_cat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationActivity extends AppCompatActivity {

    Button registrationButton;
    EditText editName;

    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout root;

    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setTitle(R.string.title);

        root = findViewById(R.id.root_element);

        registrationButton = (Button) findViewById(R.id.registration_button);
        editName = (EditText) findViewById(R.id.editName);

        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        Intent startGame = new Intent(this, MainActivity.class);

        View.OnClickListener oclStartGameButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();

                if (name.length() == 0) {
                    registrationButton.setEnabled(true);
                    Snackbar.make(root, "Input your name", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                user = new User(name);

                users.child(user.getName()).setValue(user);
                startActivity(startGame);
            }
        };

        registrationButton.setOnClickListener(oclStartGameButton);
    }

    public static User getUser(){
        return user;
    }

}
