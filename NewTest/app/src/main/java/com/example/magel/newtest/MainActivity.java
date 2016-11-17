package com.example.magel.newtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.support.design.widget.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText loginEditText;
    EditText passwordEditText;
    RelativeLayout mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = (RelativeLayout)findViewById(R.id.activity_main);

        loginEditText = (EditText) findViewById(R.id.et_Username);
        passwordEditText = (EditText) findViewById(R.id.et_Password);

        findViewById(R.id.bt_SignIn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login();
                    }
                });
    }

    private void login() {

        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isLoginNotEmpty = login!=null && !login.isEmpty();
        boolean isPasswordNotEmpty = password!=null && !password.isEmpty();

        if(isLoginNotEmpty && isPasswordNotEmpty) {


            boolean isLoginOk = isLoginOk(login);
            boolean isPasswordOk = password.length()>8;

            if(isLoginOk && isPasswordOk) {

                if (login.equals("fabio@fabio.cm") && password.equals("00000") ||
                        login.equals("thomas@thomas.com") && password.equals("00000") ||
                        login.equals("ibrahim@ibrahim.com") && password.equals("00000")) {
                    startActivity(new Intent(MainActivity.this, Menu_activity.class));
                }

            } else { showAlert(R.string.alert_dialog_login_ok_title,
                        R.string.alert_dialog_message_login_ok);
            }

        } else {
            showSnackbar(R.string.alert_dialog_error_message_login);
        }
    }

    private boolean isLoginOk(String login)  {
        int lastIndexOfDot = login.lastIndexOf(".");

        boolean loginContainAt = login.contains("@");
        boolean loginContainDot = login.contains(".");

        return loginContainAt && loginContainDot &&
                (login.length()-1-lastIndexOfDot>=2);
    }

    private void showAlert(int title, int message) {

        // this = current instance for MainActivity = Context

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();

        alertDialog.show();
    }

    private void showSnackbar(int message) {
        Snackbar.make(mainView, message, Snackbar.LENGTH_SHORT).show();
    }
}

