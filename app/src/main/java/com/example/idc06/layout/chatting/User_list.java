package com.example.idc06.layout.chatting;


import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by idc06 on 2016-10-21.
 */

public class User_list extends AppCompatActivity {
    String name;
    UserListAdapter userlist;
    ChatClient client;
    Button btt;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle("익명 인원이 TALK방");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        //client.pname();



        btt = (Button)findViewById(R.id.btn_talk);

        btt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(User_list.this,MainActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }

    public void typeName(String name) {
        userlist.add(new UserList(name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        userlist = new UserListAdapter(this.getApplicationContext(), R.layout.user);
        final ListView listView = (ListView) findViewById(R.id.list_name);
        listView.setAdapter(userlist);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        userlist.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(userlist.getCount() - 1);
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings2) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//    public void btn_u2(View v){
//
//            Intent intent = new Intent(User_list.this,User_list.class);
//            startActivity(intent);
//    }
//    public void btn_t2(View v){
//        Intent intent = new Intent(User_list.this,MainActivity.class);
//       // intent.putExtra("name",name);
//        startActivity(intent);
//        onStop();
//    }
}
//        Intent intent = getIntent();
//        name = intent.getStringExtra("name");

//        }






