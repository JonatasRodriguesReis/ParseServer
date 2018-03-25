package com.qualidade.lg.parseserver;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    EditText nomeUsuario;
    EditText senhaUsuario;
    EditText confSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this);

        nomeUsuario = findViewById(R.id.edtUsuario);
        senhaUsuario = findViewById(R.id.edtSenha);
        confSenha = findViewById(R.id.edtSenhaConfirma);



    }

    public void singUp(View view){
        if(senhaUsuario.getText().toString().equals(confSenha.getText().toString())){
            ParseUser user = new ParseUser();
            user.setUsername(nomeUsuario.getText().toString());
            user.setPassword(senhaUsuario.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        alertDisplayer("Sucessful Sign Up!","Welcome" + nomeUsuario.getText().toString() + "!");
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });


        }
    }

    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // don't forget to change the line below with the names of your Activities
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void logIn(View view){
        ParseUser.logInInBackground(nomeUsuario.getText().toString(),senhaUsuario.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    alertDisplayer("Sucessful Login","Welcome back " + nomeUsuario.getText().toString() + "!");

                } else {
                    ParseUser.logOut();
                    //Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        /*ParseObject post = new ParseObject("Post");
        post.put("title","Aqui");
        post.put("content","Aqui Por favor!");
        ParseObject user = ParseUser.getCurrentUser();

        post.put("user",ParseObject.createWithoutData("User","PgtVkoobTN"));

        post.saveInBackground();*/
    }

    public void logOut(View view){
        ParseUser.logOut();
    }
}
