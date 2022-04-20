package by.bsuir.feed_the_cat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class TableActivity extends AppCompatActivity {

    TextView [] name = new TextView[5];
    TextView [] highScore = new TextView[5];

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table);

        i = 0;

        name[4] = (TextView) findViewById(R.id.table_view_11);
        name[3] = (TextView) findViewById(R.id.table_view_21);
        name[2] = (TextView) findViewById(R.id.table_view_31);
        name[1] = (TextView) findViewById(R.id.table_view_41);
        name[0] = (TextView) findViewById(R.id.table_view_51);

        highScore[4] = (TextView) findViewById(R.id.table_view_12);
        highScore[3] = (TextView) findViewById(R.id.table_view_22);
        highScore[2] = (TextView) findViewById(R.id.table_view_32);
        highScore[1] = (TextView) findViewById(R.id.table_view_42);
        highScore[0] = (TextView) findViewById(R.id.table_view_52);

        readUsersFromBd();
    }

    public void readUsersFromBd(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("Users");

        users.orderByChild("highScore").limitToLast(5).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String, User> currUser = (Map<String, User>) snapshot.getValue();
                System.out.println(currUser);
                name[i].setText(String.valueOf(currUser.get("name")));
                highScore[i].setText(String.valueOf(currUser.get("highScore")));
                i++;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}

        });
    }
}