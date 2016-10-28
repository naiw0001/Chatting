package com.example.idc06.layout.chatting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by idc06 on 2016-10-20.
 */

public class StartActivity extends AppCompatActivity {

    EditText inputName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_start);
        startActivity(new Intent(this,Splash.class));
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("인원이 TALK");
        toolbar.setLogo(R.drawable.aaaa);
        setSupportActionBar(toolbar);
        inputName = (EditText) findViewById(R.id.edit_name);

    }

    public void onSendNameStartChat(View v) {
        String name = inputName.getText().toString();
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        onStop();
    }
}
