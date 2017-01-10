package com.saksham.yuminfo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.saksham.yuminfo.MainActivity;
import com.saksham.yuminfo.R;

public class DecideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide);
        UserRepo repo = new UserRepo(this);
        User user = new User();
        user.status= 0;
        user.phone="";
        user.name="";
        user.user_ID=1;
        user=repo.getStudentById(user.user_ID);


        if(user.user_ID==1) {
            Toast.makeText(this,"Welcome "+user.name, Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(DecideActivity.this,MainActivity.class);
            DecideActivity.this.startActivity(myIntent);
        }
        else{
            Intent myIntent = new Intent(DecideActivity.this,RegisterupActivity.class);
            DecideActivity.this.startActivity(myIntent);
        }
    }
}
