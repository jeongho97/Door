package com.example.hotel;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class take_room_Info extends StringRequest {
    // 서버 URL 설정(서버랑 연결)
    final static private String URL = "http://165.229.187.90/take_room_Info.php"; // 심어놓은 php 파일 연동
    private Map<String, String> map;


    public take_room_Info(String userID,String userPW, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID); // 키값
        map.put("userPassword", userPW);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}