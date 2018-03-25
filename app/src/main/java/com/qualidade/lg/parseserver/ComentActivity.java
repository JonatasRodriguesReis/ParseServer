package com.qualidade.lg.parseserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseObject;

public class ComentActivity extends AppCompatActivity {
    String idPost;
    EditText edtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coment);
        idPost = getIntent().getStringExtra("idPost");
        edtContent = findViewById(R.id.edtContent);
    }

    public void coment(View view){
        ParseObject coment = new ParseObject("Coment");
        coment.put("content",edtContent.getText().toString());
        coment.put("post",ParseObject.createWithoutData("Post",idPost));

        coment.saveInBackground();
    }
}
