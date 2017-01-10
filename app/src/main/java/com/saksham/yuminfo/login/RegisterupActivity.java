package com.saksham.yuminfo.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saksham.yuminfo.MainActivity;
import com.saksham.yuminfo.R;
import com.saksham.yuminfo.domain.Config;
import com.saksham.yuminfo.domain.RequestHandler;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private int _User_Id=0;

    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.input_phone)
    EditText _phoneText;

    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerup);
        ButterKnife.bind(this);

        _User_Id =0;
        Intent intent = getIntent();
        _User_Id =intent.getIntExtra("user_Id", 0);
        UserRepo repo = new UserRepo(this);
        User user = new User();
        user = repo.getStudentById(_User_Id);
        _nameText.setText(user.name);
        _phoneText.setText(user.phone);


        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent myIntent = new Intent(RegisterupActivity.this,LoginActivity.class);
                //RegisterupActivity.this.startActivity(myIntent);
            }
        });
    }


    //Adding an employee
    private void addEmployee(){

        final String name = _nameText.getText().toString().trim();
        final String phone = _phoneText.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterupActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RegisterupActivity.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_PHONE,phone);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }




    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String phone = _phoneText.getText().toString();

            UserRepo repo = new UserRepo(this);
            User user = new User();
            user.status= 1;
            user.phone=phone;
            user.name=name;
            user.user_ID=_User_Id;

            if (_User_Id==0){
                _User_Id = repo.insert(user);

                //Toast.makeText(this,"New User Insert"+_User_Id,Toast.LENGTH_SHORT).show();
            }else{

                repo.update(user);
                //Toast.makeText(this,"User Record updated",Toast.LENGTH_SHORT).show();
            }


        // TODO: Implement your own signup logic here.
        addEmployee();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent myIntent = new Intent(RegisterupActivity.this,MainActivity.class);
        RegisterupActivity.this.startActivity(myIntent);
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Loading failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String phone = _phoneText.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (phone.isEmpty() || phone.length() < 10) {
            _phoneText.setError("enter a valid mobile no.");
            valid = false;
        } else {
            _phoneText.setError(null);
        }



        return valid;
    }
}