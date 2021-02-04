package com.example.hotel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Guest_page_chatting extends AppCompatActivity {
    private Handler mHandler;
    Socket socket;
    PrintWriter sendWriter;
    private String ip = "180.189.90.25";
    private int port = 8888;

    TextView textView;
    TextView textView2;
    Button chatbutton;
    TextView chatView;
    EditText message;
    String sendmsg;
    String read;
    private String userID=MainActivity.userID;
    public String curHotelOwner=HotelAdapter.curUserID;
    public String curHotelName=HotelAdapter.curName;


    @Override
    protected void onStop() {
        super.onStop();
        try {
            sendWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page_chatting);
//
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();
//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getSupportActionBar().setTitle(curHotelOwner+"  "+curHotelName);

        mHandler = new Handler();

        chatView = (TextView) findViewById(R.id.chatView);
        message = (EditText) findViewById(R.id.message);
        chatbutton = (Button) findViewById(R.id.chatbutton);

        new Thread() {
            public void run() {
                try {
                    InetAddress serverAddr = InetAddress.getByName(ip);//소켓생성
                    socket = new Socket(serverAddr, port);
                    sendWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")), true);
                    chatView.setText("[##] 서버와 연결이 되었습니다......\r\n");
                    //sendWriter = new PrintWriter(socket.getOutputStream()); //데이터 전송
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));//데이터 수신
                    while(true){
                        read = input.readLine(); //데이터 수신

                        System.out.println("TTTTTTTT"+read);
                        if(read!=null){
                            mHandler.post(new msgUpdate(read));//받아온 데이터 화면에 출력 cf)handler:msgUpdate 객체를 message queue에 전달하는 함수
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } }}.start();

        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmsg = message.getText().toString(); //입력 메세지
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sendWriter.println(userID +">"+ sendmsg+"  ");
                            sendWriter.flush();
                            message.setText("");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    class msgUpdate implements Runnable{
        private String msg;
        public msgUpdate(String str) {this.msg=str;}

        @Override
        public void run() {
            chatView.setText(chatView.getText().toString()+msg+"\n");

        }
    }
}