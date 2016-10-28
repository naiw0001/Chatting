package com.example.idc06.layout.chatting;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ListAdapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by idc06 on 2016-10-18.
 */

public class ChatClient {

    public static final String SERVER_IP = "1.224.44.55";
    Context context;
    Handler handler;

    String msg;
    String rcvMsg;
    String name;
    String name2;

    String rcvName;


    ChatClient(Context context) {
        this.context = context;
    }

    public void startClient() {
        handler = new Handler();
        ConnectThread thread = new ConnectThread();
        thread.start();
    }

    public void sendName(String name) {
        this.name = name;
    }

    public void pname(){
         ((User_list)context).typeName(name);
    }

    class ConnectThread extends Thread {
        @Override
        public void run() {
            try {
                //1.Socket을 만들고 연결
                Socket socket = new Socket(SERVER_IP, 9999);
                //2.input/output에 해당하는 thread를 각각 만들고 start()함
                Thread sender = new Thread(new ClientSender(socket, name));
                Thread receiver = new Thread(new ClientReceiver(socket));

                sender.start();
                receiver.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ClientSender extends Thread {
        Socket socket;
        DataOutputStream out;
        String name;

        ClientSender(Socket socket, String name) {
            this.socket = socket;
            this.name = name;

            try {
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                //name 을 알려줌(server에서 hashmap에 이름 - output정보를 미리 저장함
                if (out != null) {
                   out.writeUTF(name);
                }

                while (out != null) {
                    if (msg != null) {
                        out.writeUTF("[" + name + "] : " + msg);
                        ((MainActivity)context).typeName(name);
                        msg = null;
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    class ClientReceiver extends Thread {
        Socket socket;
        DataInputStream in;

        ClientReceiver(Socket socket) {
            try {
                this.socket = socket;
                in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (in != null) {
                try {
                    //outputStream으로 온 데이터를 read함
                    rcvMsg = in.readUTF();
//                    rcvName = in.readUTF();
                } catch (IOException e) {
                }
                //작업 thread 안에서 inputText에 값을 직접 찍어주는건 불가능
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)context).typeMsg(rcvMsg);
                    }
                });
            }
        }

    }

    public void sendMsg(String msg) {
        this.msg = msg;
    }

}
