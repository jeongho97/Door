package com.example.hotel;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정(서버랑 연결)
    final static private String URL = "http://180.189.90.25:8000/Register1.php"; // 심어놓은 php 파일 연동
    private Map<String, String> map;


    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID); // 키값
        map.put("userPassword", userPassword);
        map.put("userName",userName);
        map.put("userAge", userAge+ ""); // 인트형이라 스트링 형으로 편법법

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

