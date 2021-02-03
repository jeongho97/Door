package com.example.hotel;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Guest_page_main extends AppCompatActivity {


    private ArrayList<HotelData> arrayList;
    private HotelAdapter hotelAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private String[] HotelName;
    private String[] HotelAddress;
    private String[] HotelNumber;
    private String[] HotelCapacity;
    private String[] HotelCheckIn;
    private String[] HotelCheckOut;
    private String[] HotelPrice;
    private String[] HotelUserID;
    private String userID=MainActivity.userID;
    private String userPW=MainActivity.userPW;

    private Button btn_chat;



    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page_main);


        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();//HotelData를 담을 어레이 리스트 (어댑터쪽으로)
        hotelAdapter = new HotelAdapter(arrayList);
        recyclerView.setAdapter(hotelAdapter);


       Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    num=jsonObject.getInt("number");
                    HotelName=new String[num+1];
                    HotelAddress =new String[num+1];
                    HotelNumber =new String[num+1];
                    HotelCapacity = new String[num+1];
                    HotelCheckIn= new String[num+1];
                    HotelCheckOut = new String[num+1];
                    HotelPrice= new String[num+1];
                    HotelUserID = new String[num+1];

                    for (int i = 0; i < num+1; i++) {

                        HotelName[i] = jsonObject.getString("hotelName"+i);
                        HotelAddress[i] = jsonObject.getString("hotelAddress"+i);
                        HotelNumber[i] = jsonObject.getString("hotelNumber"+i);
                        HotelCapacity[i] = String.valueOf(jsonObject.getInt("hotelCapacity"+i));
                        HotelCheckIn[i] = jsonObject.getString("hotelCheckIn"+i);
                        HotelCheckOut[i] = jsonObject.getString("hotelCheckOut"+i);
                        HotelPrice[i] = jsonObject.getString("hotelPrice"+i);
                        HotelUserID[i] = jsonObject.getString("hotelUserID"+i);
                        System.out.println(HotelName);
                        HotelData hotelData = new HotelData(HotelName[i],HotelAddress[i],HotelNumber[i], HotelCapacity[i], HotelCheckIn[i]
                                ,HotelCheckOut[i], HotelPrice[i],HotelUserID[i]);
                        arrayList.add(hotelData);
                        hotelAdapter.notifyDataSetChanged();


                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }

        };


        take_room_Info Take_room_Info = new take_room_Info(userID,userPW, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Guest_page_main.this);
        queue.add(Take_room_Info); // Volley를 이용해서 서버로 요청함
        btn_chat=(Button)findViewById(R.id.chat);

        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guest_page_main.this, Guest_page_chatting.class);
                startActivity(intent);
            }
        });

    }
}