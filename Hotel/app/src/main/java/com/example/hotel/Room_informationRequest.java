package com.example.hotel;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Room_informationRequest extends StringRequest {
    // 서버 URL 설정(서버랑 연결)
    final static private String URL = "http://180.189.90.25:8000/room_information.php"; // 심어놓은 php 파일 연동
    private Map<String, String> map;


    public Room_informationRequest(String hotelName, String hotelAddress, String hotelNumber, int hotelCapacity,String hotelCheckIn,String hotelCheckOut
            ,String hotelPrice,String hotelUserID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("hotelName", hotelName); // 키값
        map.put("hotelAddress", hotelAddress);
        map.put("hotelNumber",hotelNumber);
        map.put("hotelCapacity", hotelCapacity+""); // 인트형이라 스트링 형으로 편법법
        map.put("hotelCheckIn", hotelCheckIn);
        map.put("hotelCheckOut", hotelCheckOut);
        map.put("hotelPrice", hotelPrice);
        map.put("hotelUserID", hotelUserID);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
