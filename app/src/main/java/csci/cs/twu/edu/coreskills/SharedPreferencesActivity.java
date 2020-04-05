package csci.cs.twu.edu.coreskills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**

 Assignment Notes: For this activity, the user should be able to
 save and load the username and computing ID from SharedPreferences,
 the key/value store built into Android.

 Reference:
 https://gitlab.com/csci3313sp20/storageexample-master

 */

public class SharedPreferencesActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "CoreSkillsPrefsFile";

    public static final String NAME = "name";
    public static final String ID = "id";

    SharedPreferences sharedPreferences;
    EditText spnameEditText;
    EditText spcompIDEditText;

    TextView spnameTextView;
    TextView spcompIDTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        spnameEditText = (EditText) findViewById(R.id.spnameEditText);
        spcompIDEditText = (EditText) findViewById(R.id.spcompIDEditText);

        spnameTextView = (TextView) findViewById(R.id.spnameTextView);
        spcompIDTextView = (TextView) findViewById(R.id.spcompIDTextView);
        sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
    }

    public void saveToSharedPreferences(View view) {

        // Add your code here to save
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(NAME,spnameEditText.getText().toString());
        edit.putString(ID,spcompIDEditText.getText().toString());
        edit.commit();

    }

    public void loadFromSharedPreferences(View view) {
        // Add your code here to load
        String name = sharedPreferences.getString(NAME,"");
        String id = sharedPreferences.getString(ID,"");
        spnameTextView.setText("name "+ name);
        spcompIDTextView.setText("id" + id);
    }
}
