package com.example.idc06.layout.chatting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by idc06 on 2016-10-21.
 */

public class Popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        // 화면이 잠겨있을 때 보여줌
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        // 키잠금 해제
        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
        // 화면 켜기
        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        TextView message = (TextView)findViewById(R.id.pop);
        message.setText(getIntent().getStringExtra("답변이 도착했습니다."));
        ImageButton ib = (ImageButton)findViewById(R.id.ok_img);
        ib.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지를 클릭하면 액티비티를 실행
                Intent intent = new Intent(Popup.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
