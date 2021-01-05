package com.example.hotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class host_or_guest extends AppCompatActivity {

    Button btn_guest;
    Button btn_host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_or_guest);

        btn_guest=(Button)findViewById(R.id.guest);
        btn_host=(Button)findViewById(R.id.host);

        btn_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad =new AlertDialog.Builder(host_or_guest.this);
                ad.setMessage("사용하실 GUEST_ID를 입력해주세요");

                final EditText et = new EditText(host_or_guest.this);
                ad.setView(et);

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result = et.getText().toString();//guestID활용할수있는부분
                        dialog.dismiss();
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();;
                    }
                });
                ad.show();
            }
        });
        btn_host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad2 = new AlertDialog.Builder(host_or_guest.this);
                ad2.setMessage("사용하실 HOST_ID를 입력해주세요");

                final EditText et2=new EditText(host_or_guest.this);
                ad2.setView(et2);

                ad2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result2 = et2.getText().toString();//hostID활용할수있는부분
                        dialog.dismiss();
                    }
                });
                ad2.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad2.show();
            }
        });
    }
}