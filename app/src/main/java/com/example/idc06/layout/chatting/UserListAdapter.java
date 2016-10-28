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
 * Created by idc06 on 2016-10-27.
 */

public class UserListAdapter extends ArrayAdapter {
    List users = new ArrayList();

    public UserListAdapter(Context context, int textView){
        super(context, textView);
    }

    public void add(UserList object){
        users.add(object);
        super.add(object);
    }

    @Override
    public int getCount(){
        return users.size();
    }
    @Override
    public UserList getItem(int index){
        return (UserList)users.get(index);
    }
    @Override
    public View getView(int position, View convert, ViewGroup parent) {
        View row = convert;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chatting_message, parent, false);
        }

        UserList user = (UserList) users.get(position);

        TextView msgText = (TextView) row.findViewById(R.id.text_u);
        msgText.setText(user.getUser());
        msgText.setTextColor(Color.parseColor("#000000"));


        return row;
    }

}
