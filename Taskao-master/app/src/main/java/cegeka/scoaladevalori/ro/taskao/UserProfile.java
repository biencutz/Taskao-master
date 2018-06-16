package cegeka.scoaladevalori.ro.taskao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserProfile {
    public String userEmail;
    public String userName;

    public UserProfile(String title, String desc, String date){
    }

    public UserProfile(String userName, String userEmail) {
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

