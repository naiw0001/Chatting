package com.example.idc06.layout.chatting;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    LinearLayout linear;
    EditText inputText;
    ChatClient client;
    String name, msg;
   final String myName = name;
    ChatList chatList;
    UserListAdapter userlist;
    Button bt_u;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("익명 인원이 TALK방");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);


        inputText = (EditText) findViewById(R.id.input);
        //textView = (TextView) findViewById(R.id.textView);
        linear = (LinearLayout) findViewById(R.id.linear);
        linear.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftInputWindow(inputText);
                return true;
            }
        });

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        client = new ChatClient(this);
        client.sendName(name);
        client.startClient();

        bt_u = (Button)findViewById(R.id.btn_user);

        bt_u.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this,User_list.class);
                startActivity(intent);
                return true;
            }
        });

    }


    public void onSendBtnClicked(View v) {
        String msg = inputText.getText().toString();
        inputText.setText("");
        //server로 보내는 msg
        client.sendMsg(msg);
    }


    public void hideSoftInputWindow(View editView) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(
                editView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    // client에서 msg를 받아 뿌리는 메소드
    public void typeMsg(String msg) {
//          textView.setText(name);
        //textView.append(msg + "\n");
        chatList.add(new ChatMessage(msg));
        this.msg = msg;

    }
    public void typeName(String name){
        this.name=name;
        Log.d("asdasd",name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_user,menu);
        if(name != null) {
            menu.add(name);
        }
        name = null;
        chatList = new ChatList(this.getApplicationContext(),R.layout.chatting_message);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(chatList);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        chatList.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatList.getCount()-1);
            }
        });
        return true;
    }
//한빛미디어
//    인피니트북스
//        성능출판사
//  남궁성 자바의정석
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

//    @Override
//    protected void onStop() {
//        super.onStop();
//        //노티피케이션 알림
//         NotificationCompat.Builder builder =
//                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.app)
//                        .setContentTitle("인원이TALK")
//                        .setContentText(msg)
//                        .setDefaults(Notification.DEFAULT_ALL);
//        Intent MainIntent = new Intent(this ,MainActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(MainIntent);
//        PendingIntent MainPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(MainPendingIntent);
//        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(0, builder.build());
//    }
//}
