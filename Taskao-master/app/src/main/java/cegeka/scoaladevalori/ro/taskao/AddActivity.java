package cegeka.scoaladevalori.ro.taskao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    public EditText titleActivity, descriptionActivity, dateActivity;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;
    private Button btnAddActivityToList;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    public DatabaseReference mDatabase;
    private String mUserId;
    private String mActivityId;



    String title, desc, date;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference ref = database.getReference("https://taskao-a1300.firebaseio.com/DOqCf1UevISBxr3f3llLw0jF4QG2/tasks");
    //ArrayList<UserActivities> userActivities = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        titleActivity = findViewById(R.id.etTitleActivity);
        descriptionActivity = findViewById(R.id.etDescriptionActivity);
        dateActivity = findViewById(R.id.etDueDate);
        btnAddActivityToList = findViewById(R.id.btnAddActivityMain);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mUserId = firebaseUser.getUid();

        btnAddActivityToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String mGroupId = mDatabase.push().getKey();
                    mDatabase.child(mUserId).child("tasks").child(mGroupId).child("task name").setValue(titleActivity.getText().toString());
                    mDatabase.child(mUserId).child("tasks").child(mGroupId).child("task desc").setValue(descriptionActivity.getText().toString());
                    titleActivity.setText("");
                    descriptionActivity.setText("");


                    mDatabase.child("users");
                    mDatabase.child(mUserId);
                    mDatabase.child("items");


                    final ListView listView = (ListView) findViewById(R.id.listView);
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(AddActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1);
                    listView.setAdapter(adapter);

                    mDatabase.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            adapter.add((String) dataSnapshot.child("title").getValue());
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            adapter.remove((String) dataSnapshot.child("title").getValue());
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



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
            Toast.makeText(AddActivity.this, "Please enter all the details!", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }



    private void loadLogInView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}
