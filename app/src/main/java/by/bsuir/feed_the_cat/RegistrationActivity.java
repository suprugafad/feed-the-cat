package by.bsuir.feed_the_cat;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegistrationActivity extends AppCompatActivity {

    Button registrationButton;
    EditText editName;

    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout root;

    public static User user;

    public static boolean flag = false;

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
                    Snackbar.make(root, "Enter your name", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference findUsers = users.child(name);

                ValueEventListener eventListener = new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            Snackbar.make(root, "User with this name is already exist", Snackbar.LENGTH_SHORT).show();
                        } else {
                            user = new User(name);
                            users.child(user.getName()).setValue(user);
                            flag = true;
                            startActivity(startGame);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(TAG, databaseError.getMessage());
                    }
                };
                findUsers.addListenerForSingleValueEvent(eventListener);
            }
        };

        registrationButton.setOnClickListener(oclStartGameButton);
    }

    public static User getUser(){
        return user;
    }

}
