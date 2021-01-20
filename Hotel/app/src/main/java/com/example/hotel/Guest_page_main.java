package com.example.hotel;


import android.os.Bundle;

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
                        HotelData hotelData = new HotelData("호텔이름: "+HotelName[i],"호텔주소: "+HotelAddress[i],"호텔번호: "+HotelNumber[i],
                                "인원: "+HotelCapacity[i], "체크인: "+HotelCheckIn[i],"체크아웃: "+HotelCheckOut[i],
                                "가격: "+HotelPrice[i],"작성자: "+HotelUserID[i]);
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

    }
}