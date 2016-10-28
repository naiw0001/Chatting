package com.example.idc06.layout.chatting;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idc06 on 2016-10-25.
 */

public class ChatList  extends ArrayAdapter{
    List msgs = new ArrayList();

    public ChatList(Context context, int textView){
        super(context, textView);
    }

    public void add(ChatMessage object) {
        msgs.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return msgs.size();
    }
    @Override
    public ChatMessage getItem(int index){
        return (ChatMessage)msgs.get(index);
    }
    @Override
    public View getView(int position, View convert, ViewGroup parent){
        View row = convert;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    row = inflater.inflate(R.layout.chatting_message,parent, false);
        }

        ChatMessage msg = (ChatMessage)msgs.get(position);

        TextView msgText = (TextView)row.findViewById(R.id.chatmessage);
        msgText.setText(msg.getMessage());
        msgText.setTextColor(Color.parseColor("#000000"));
        msgText.setBackground(this.getContext()
                .getResources().getDrawable(R.drawable.c));


        return row;
    }


}
