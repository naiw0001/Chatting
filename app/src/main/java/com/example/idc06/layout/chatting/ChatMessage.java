package com.example.idc06.layout.chatting;

import java.util.ArrayList;

import static android.R.id.message;

/**
 * Created by idc06 on 2016-10-27.
 */
public class ChatMessage {
    public String message;

    public ChatMessage(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}