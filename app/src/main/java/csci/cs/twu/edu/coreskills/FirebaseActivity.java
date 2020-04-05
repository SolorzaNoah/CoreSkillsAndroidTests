package csci.cs.twu.edu.coreskills;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
/**

 Assignment Notes: This activity should pull data from Firebase Firestore based upon
 the course requested in the EditText.  There are only 4 entries in the database, for example,
 CSCI 3313. Your app should be able to pull other 3 entries, for example, CSCI 1403.
 Your code should populate the three placeholder variables.

 Reference this material for guidance:
 Initialize an instance of Cloud Firestore
 https://firebase.google.com/docs/firestore/quickstart

 Get a Document
 https://firebase.google.com/docs/firestore/query-data/get-data

 Hint: DocumentSnapShot has a method you can use to return the value of a field as a String
 https://firebase.google.com/docs/reference/android/com/google/firebase/firestore/DocumentSnapshot

 */

public class FirebaseActivity extends AppCompatActivity {

    EditText courseEditText;
    TextView courseNameTextView;
    TextView instructorTextView;
    TextView locationTextView;
    private static final String TAG = FirebaseActivity.class.getSimpleName();
    private static final String COURSE_NAME = "Course Name";
    private static final String INSTRUCTOR = "Instructor";
    private static final String LOCATION = "Location";

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        courseEditText = (EditText) findViewById(R.id.fbcourseEditText);
        courseNameTextView = (TextView) findViewById(R.id.fbcourseNameTextView);
        instructorTextView = (TextView) findViewById(R.id.fbinstructorTextView);
        locationTextView = (TextView) findViewById(R.id.fblocationTextView);

    }

    public void downloadFirebaseData(View view) {

        // This will split the input from the user into an array.
        String[] courseToSearch = courseEditText.getText().toString().split(" ");

        // Task: initialize an instance of Cloud Firestore
        db = FirebaseFirestore.getInstance();
        db.collection(courseToSearch[0]).document(courseToSearch[1]).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String courseName = documentSnapshot.getString(COURSE_NAME);
                    String instructor = documentSnapshot.getString(INSTRUCTOR);
                    String location = documentSnapshot.getString(LOCATION);
                    courseNameTextView.setText("Course Name:" + courseName);
                    locationTextView.setText("Location:" + location);
                    instructorTextView.setText("Instructor:" + instructor);
                }else{
                    System.out.println("No existance");
                }
            }
        });




        // Tasks: Retrieve the contents of a single document using the department and course information
        // provided by the user, then use the fields information in the document to populate the
        // three placeholders


    }
}
