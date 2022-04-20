package by.bsuir.feed_the_cat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class StartActivity extends AppCompatActivity {

    Button newGameButton;
    Button continueButton;
    Button resultsButton;
    Button helpButton;
    Button aboutAuthorButton;

    SignInButton signInGoogleButton;
    GoogleSignInClient mGoogleSignInClient;

    TextView mainResultsValue;

    TextView userName;

    User user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setTitle(R.string.title);

        newGameButton = (Button) findViewById(R.id.new_game_button);
        continueButton = (Button) findViewById(R.id.continue_button);
        resultsButton = (Button) findViewById(R.id.results_button);
        helpButton = (Button) findViewById(R.id.help_button);
        aboutAuthorButton = (Button) findViewById(R.id.about_author_button);

        signInGoogleButton = findViewById(R.id.bt_google_sign_in);

        mainResultsValue = (TextView) findViewById(R.id.main_result_value);

        userName = (TextView) findViewById(R.id.user_name);

        Intent newGameIntent = new Intent(this, RegistrationActivity.class);
        Intent helpIntent = new Intent(this, HelpActivity.class);
        Intent continueIntent = new Intent(this, AuthActivity.class);
        Intent resultsIntent = new Intent(this, TableActivity.class);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        user = MainActivity.getUser();

        if (user != null) {
            int score = user.highScore;
            mainResultsValue.setText(Integer.toString(score));
        }

        View.OnClickListener oclButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case (R.id.new_game_button):
                        startActivity(newGameIntent);
                        break;
                    case (R.id.continue_button):
                        startActivity(continueIntent);
                        break;
                    case (R.id.results_button):
                        startActivity(resultsIntent);
                        break;
                    case (R.id.help_button):
                        startActivity(helpIntent);
                        break;
                    case (R.id.about_author_button):
                        showAuthorWindow();
                        break;
                }
            }
        };

        signInGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 100);

            }
        });

        newGameButton.setOnClickListener(oclButton);
        continueButton.setOnClickListener(oclButton);
        resultsButton.setOnClickListener(oclButton);
        helpButton.setOnClickListener(oclButton);
        aboutAuthorButton.setOnClickListener(oclButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) { //google authorisation
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    public void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
        } catch (Exception e) {
            googleSignInOk(null);
        }
    }

    public void googleSignInOk(GoogleSignInAccount account) {
        if (account == null) { //authorization error

        } else {
            userName.setText(account.getDisplayName());
        }
    }

    private void showAuthorWindow() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Task №1");
        dialog.setContentView(R.layout.dialog_view);
        TextView text = (TextView) dialog.findViewById(R.id.about_author_text);
        dialog.show();
    }
}