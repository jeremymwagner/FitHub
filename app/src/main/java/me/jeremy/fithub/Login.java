package me.jeremy.fithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity{

    public Button register,login;
    public EditText email,password;

    /**
     * Method to handle all button events on Login page
     */
    void init(){
        register = (Button) findViewById(R.id.login_signup);
        register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toRegister = new Intent(Login.this,Register.class);
                    startActivity(toRegister);
                }
            }

        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }


}
