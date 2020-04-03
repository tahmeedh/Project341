package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class sign_up extends AppCompatActivity {


    public static final String TAG = "TAG";
    EditText name,email,password;
    Button submit;
    TextView login;
    FirebaseAuth fAuth;
    RadioGroup gender;
    String userID;
    FirebaseFirestore fstore;
    Switch s;
    String userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        login = findViewById(R.id.login);
        gender = findViewById(R.id.gender);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        s = findViewById(R.id.userType);





        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int selectedRadioButtonID = gender.getCheckedRadioButtonId();
                RadioButton selectedRadioButton =  findViewById(selectedRadioButtonID);

                final String vemail = email.getText().toString().trim();
                final String vpassword = password.getText().toString().trim();
                final String vname = name.getText().toString();
                final String vgender = selectedRadioButton.getText().toString().trim();
                final Boolean trainer = s.isChecked();
                FirebaseFirestore.setLoggingEnabled(true);

                if(TextUtils.isEmpty(vemail)){
                    email.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(vpassword)){
                    password.setError("Password is Required");
                    return;
                }

                if (password.length()< 6){
                    password.setError("Password Must be at Least 6 Characters");
                    return;
                }
                if (trainer == true){
                    userType = "Trainer";
                }else{
                    userType = "Client";
                }

                fAuth.createUserWithEmailAndPassword(vemail,vpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("name",vname);
                            user.put("email",vemail);
                            user.put("gender",vgender);
                            user.put("UserType",userType);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: user profile is created for "+ userID);
                                }
                            });
                            if(userType == "trainer"){
                                startActivity(new Intent(getApplicationContext(), TrainerActivity.class));
                            }
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

    }

}
