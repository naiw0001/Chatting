//package com.example.idc06.layout.chatting;
//
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Iterator;
//
//
//public class JavaMultiChatServer {
//    HashMap clients;
//
//    JavaMultiChatServer() {
//        clients = new HashMap();
//        Collections.synchronizedMap(clients);
//    }
//
//    public void start() {
//        ServerSocket serverSocket = null;
//        Socket socket = null;
//        try {
//            serverSocket = new ServerSocket(9999);
//            System.out.println("서버가 시작되었습니다.");
//
//            //이 무한루프는 사용자를 계속 받기위함
//            while (true) {
//                socket = serverSocket.accept();
//                System.out.println("[" + socket.getInetAddress() + "," + socket.getPort() + "]" + "에서 접속하였습니다.");
//
//                //새로운 사용자가 접속을 시도할 때마다 계속 thread생성
//                ServerReceiver thread = new ServerReceiver(socket);
//                thread.start();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        new JavaMultiChatServer().start();
//    }
//
//    /**
//     * 모든 사용자에게 msg를 뿌려주는 메소드
//     *
//     * @param msg
//     */
//
//
//    public void sendToAll(String msg) {
//        Iterator it = clients.keySet().iterator();
//        while (it.hasNext()) {
//            try {
//                //iterator로 하나씩 조회하면서, client.get()을 통해 해당 value(저장된 OutputStream 값)을 빼온다
//                DataOutputStream out = (DataOutputStream)clients.get(it.next());
//                out.writeUTF(msg);//해당 outputStream으로 전달받은 msg를 뿌려줌
//                out.flush();
//            } catch (IOException e) {}
//        }
//    }
//
//    //새로운 사용자가 접속을 시도할 때마다 불리는  thread
//    class ServerReceiver extends Thread {
//        Socket socket;
//        DataInputStream in;
//        DataOutputStream out;
//
//        //contsructor에서 받아온 socket정보를 이용하여 새로운 사용자와의 연걸을 세팅함
//        ServerReceiver(Socket socket) {
//            this.socket = socket;
//
//            try {
//                in = new DataInputStream(socket.getInputStream());
//                out = new DataOutputStream(socket.getOutputStream());
//            } catch (IOException e) {}
//        }
//
//        @Override
//        public void run() {
//            String name = "";
//            //1.사용자의 이름과 outputStream정보를 hashmap에 추가함
//            try {
//                name = in.readUTF();//hashmap에 먼저 등록하기 위해 이름을 먼저 받아옴
//                System.out.println("#" + name + "님 접속하셨군요");//테스트용
//                sendToAll("#" + name + "님이 접속하셨습니다.");
//                //hashmap에 name-OutputStream정보를 추가
//                clients.put(name, out);
//                System.out.println("현재 접속자 수 : " + clients.size() + "명");
//
//                while (in != null) {
//                    sendToAll(in.readUTF());
//                }
//            } catch (IOException e) {}
//            finally {
//                sendToAll("#" + name + "님이 나가셨습니다.");
//
//                clients.remove(name);
//                System.out.println("[" + socket.getInetAddress() + "," + socket.getPort() + "]" +
//                        "에서 접속을 종료하였습니다.");
//                System.out.println("현재 접속자 수 : " + clients.size() + "명");
//            }
//        }
//
//    }
//}
