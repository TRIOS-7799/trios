package com.example.login;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText eUser,ePass;
    Button bLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        eUser = (EditText)findViewById(R.id.username);
        ePass = (EditText)findViewById(R.id.password);
        bLogin = (Button)findViewById(R.id.btnlogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eUser.getText().toString();
                String password = ePass.getText().toString();

                Boolean Chkemailpass = db.emailpassword(email,password);
                if(Chkemailpass==true)
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();

            }
        });
    }
}