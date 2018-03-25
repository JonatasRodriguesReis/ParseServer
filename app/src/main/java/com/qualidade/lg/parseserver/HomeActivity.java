package com.qualidade.lg.parseserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    EditText edtTitle;
    EditText edtContent;
    ParseObject postChoosed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);

        /*ParseUser user = new ParseUser();
        user.setUsername("JonatasTeste6");
        user.setPassword("123");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(HomeActivity.this,"OK6",Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser.logOut();
                    Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        ParseUser user2 = new ParseUser();
        user2.setUsername("JonatasTeste7");
        user2.setPassword("123");
        user2.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(HomeActivity.this,"OK7",Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser.logOut();
                    Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        ParseUser user3 = new ParseUser();
        user3.setUsername("JonatasTeste8");
        user3.setPassword("123");
        user3.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(HomeActivity.this,"OK8",Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser.logOut();
                    Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }

    public void postIn(View view){
        ParseObject post = new ParseObject("Post");
        post.put("title",edtTitle.getText().toString());
        post.put("content",edtContent.getText().toString());

        post.put("user",ParseUser.getCurrentUser());
        /*ParseObject coment = new ParseObject("Coment");
        coment.put("content","taaa");
        coment.put("outro",post);*/



        post.saveInBackground();
    }

    public void pesquisar(View view){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                   try {
                       edtTitle.setText(objects.get(0).getString("title"));
                       edtContent.setText(objects.get(0).getString("content"));
                       postChoosed = objects.get(0);
                   }catch (IndexOutOfBoundsException ex){
                       Toast.makeText(HomeActivity.this,"There is not any Post!",Toast.LENGTH_SHORT).show();
                   }


                }
                else {
                    Log.d("Post", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void coment (View view){
        Intent intent = new Intent(HomeActivity.this,ComentActivity.class);
        intent.putExtra("idPost",this.postChoosed.getObjectId());
        startActivity(intent);
    }
}
