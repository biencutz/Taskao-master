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



        // .child("users").child(mUserId).child("items").push().child("title").setValue(text.getText().toString());


        //firebaseDatabase = FirebaseDatabase.getInstance();


        //DatabaseReference actRef = ref.child("tasks");

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

                    //UserActivities userActivities = new UserActivities(title, desc, date);
                    //DatabaseReference actRef = ref.child("users");


                }
            }
        });
    }
/*
    private void addActivity(){
        String activity_title = titleActivity.getText().toString().trim();
        String activity_description = descriptionActivity.getText().toString();
        String activity_date = dateActivity.getText().toString().trim();

        String id = ref.push().getKey();
    }

*/




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
























                /*
                 btnAddActivityToList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if (validate()) {
                String activity_title = titleActivity.getText().toString().trim();
                String activity_description = descriptionActivity.getText().toString();
                String activity_date = dateActivity.getText().toString().trim();

                UserActivities userActivities = new UserActivities(title, desc, date);
                //DatabaseReference actRef = ref.child("users");
                DatabaseReference.child()
                firebaseDatabase.(activity_title, activity_description, activity_date) {


                if (task.isSuccessful()) {
                Map<String, AddActivity> users = new HashMap<>();
                Toast.makeText(AddActivity.this, "Activity successfully added!", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(AddActivity.this, MainActivity.class));
                } else {
                Toast.makeText(AddActivity.this, "Activity not successfully added!", Toast.LENGTH_SHORT).show();
                }





                /*
                ArrayList<UserActivities> userActivities=new ArrayList<>();
                Boolean saved=null;
                public Boolean save(UserActivities userTask)
                {
                if(userTask==null)
                {
                saved=false;
                }else
                {
                try
                {
                ref.child("UserActivities").push().setValue(userTask);
                saved=true;

                }catch (DatabaseException e)
                {
                e.printStackTrace();
                saved=false;
                }
                }

                private void fetchData(DataSnapshot dataSnapshot)
                {
                userActivities.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                UserActivities userActivities = ds.getValue(UserActivities.class);
                userActivities.add(userActivities);
                }
                }

                public ArrayList<UserActivities> retrieve() {
                ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
                });

                return userActivities;
                }


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
                 */
/*
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