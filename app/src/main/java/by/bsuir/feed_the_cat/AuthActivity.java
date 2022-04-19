package by.bsuir.feed_the_cat;

import static android.content.ContentValues.TAG;

import static java.lang.Thread.sleep;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AuthActivity extends AppCompatActivity {

    Button authButton;
    EditText editName;

    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout root;

    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        setTitle(R.string.title);

        root = findViewById(R.id.root_element);

        authButton = (Button) findViewById(R.id.registration_button);
        editName = (EditText) findViewById(R.id.editName);

        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        Intent startGame = new Intent(this, MainActivity.class);

        View.OnClickListener oclStartGameButton = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();

                if (name.length() == 0) {
                    authButton.setEnabled(true);
                    Snackbar.make(root, "Enter your name", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference findUsers = users.child(name);

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()) {
                            Snackbar.make(root, "User with this name isn't exist", Snackbar.LENGTH_SHORT).show();
                        } else {
                            user = new User();
                            user.name = findUsers.getKey();
                            //user.highScore = Integer.parseInt(findUsers.child("highscore").get().toString());
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

        authButton.setOnClickListener(oclStartGameButton);
    }

    public static User getUser(){
        return user;
    }

}
