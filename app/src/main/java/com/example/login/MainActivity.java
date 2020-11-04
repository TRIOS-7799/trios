package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText eUsername,ePassword,eConfirmpass,eAddress,eName,eStatus;
    RadioGroup gender;
    Button register,blogin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        eUsername=(EditText)findViewById(R.id.email);
        ePassword=(EditText)findViewById(R.id.pass);
        eConfirmpass=(EditText)findViewById(R.id.cpass);
        eName=(EditText)findViewById(R.id.name);
        eAddress=(EditText)findViewById(R.id.address);
        eStatus=(EditText)findViewById(R.id.status);
        gender=(RadioGroup)findViewById(R.id.gender);
        register=(Button)findViewById(R.id.register);
        blogin2=(Button)findViewById(R.id.login);
        blogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUsername = eUsername.getText().toString();
                String sPassword = ePassword.getText().toString();
                String sConfirmpass = eConfirmpass.getText().toString();
                String sName = eName.getText().toString();
                String sAddress = eAddress.getText().toString();
                String sStatus = eStatus.getText().toString();
                RadioButton s7 = findViewById(gender.getCheckedRadioButtonId());
                String sGender = s7.getText().toString();
                if(sUsername.equals("") ||sPassword.equals("") ||sConfirmpass.equals("") ||sName.equals("") ||sAddress.equals("") ||sStatus.equals("") ||sGender.equals("")) {
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(sPassword.equals(sConfirmpass)){
                        Boolean chkemail = db.chkemail(sUsername);
                        if (chkemail==true){
                            Boolean insert = db.insert(sUsername,sPassword,sConfirmpass,sName,sAddress,sStatus,sGender);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                                eUsername.setText("");
                                ePassword.setText("");
                                eConfirmpass.setText("");
                                eName.setText("");
                                eAddress.setText("");
                                eStatus.setText("");

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Username Already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}