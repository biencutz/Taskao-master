package cegeka.scoaladevalori.ro.taskao;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    public EditText titleActivity, descriptionActivity, dateActivity;

    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;
    private Button btnAddActivityToList;
    private DataSnapshot myFirebaseRef;
    String title, desc, date;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");
    ArrayList<UserActivities> userActivities=new ArrayList<>();

    public AddActivity(DatabaseReference ref) {
        this.ref = ref;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titleActivity = findViewById(R.id.etTitleActivity);
        descriptionActivity = findViewById(R.id.etDescriptionActivity);
        dateActivity = findViewById(R.id.etDueDate);
        btnAddActivityToList = findViewById(R.id.btnAddActivityMain);

        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference taskRef = ref.child("tasks");

        btnAddActivityToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String activity_title = titleActivity.getText().toString().trim();
                    String activity_description = descriptionActivity.getText().toString();
                    String activity_date = dateActivity.getText().toString().trim();

                    UserActivities userActivities = new UserActivities(title, desc, date);
                    DatabaseReference taskRef = ref.child("users");



                    firebaseDatabase.(activity_title, activity_description, activity_date) {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Map<String, AddActivity> users = new HashMap<>();
                                Toast.makeText(AddActivity.this, "Activity successfully added!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(AddActivity.this, "Activity not successfully added!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }
        });
    }

        private Boolean validate() {
            Boolean result = false;

            title = titleActivity.getText().toString();
            desc = descriptionActivity.getText().toString();
            date = dateActivity.getText().toString();

            if (title.isEmpty() || desc.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please enter all the details!", Toast.LENGTH_SHORT).show();
            } else {
                result = true;
            }

            return result;
        }
/**
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserActivities userActivities = dataSnapshot.getValue(UserActivities.class);
                titleActivity.setText("Title: " + userActivities.getUserActivityTitile());
                descriptionActivity.setText("Description: " + userActivities.getUserActivityDescription());
                dateActivity.setText("Date: " + userActivities.getUserActivityDate());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(title, desc, date);
        myRef.setValue(userProfile);
    }


    }
 */
}



