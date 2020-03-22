package com.example.CelisParcial1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private Button login;
    private TextView faillogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        user = (EditText)findViewById(R.id.etUser);
        pass = (EditText)findViewById(R.id.etPassword);

        faillogin = (TextView)findViewById(R.id.tvFailLogin);

        login = (Button)findViewById(R.id.btnLogin);

        //google login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(user.getText().toString(), pass.getText().toString());
            }
        });

    }

    private void validate(String enterUsr, String UsrPass){
        if(enterUsr.equals("mcelis") && UsrPass.equals("pass")){
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
            finish();
        }else{
            faillogin.setText(R.string.wrongPass);
        }
    }
}
