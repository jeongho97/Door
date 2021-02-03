package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button btn_login,btn_signup,btn_developer;
    private EditText et_id,et_pw;
    static String userID,userPW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login=findViewById(R.id.login);
        btn_signup=findViewById(R.id.signup);

        btn_developer=findViewById(R.id.developer);

        et_id=findViewById(R.id.IDinput);
        et_pw=findViewById(R.id.PWinput);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userID = et_id.getText().toString();
                userPW = et_pw.getText().toString();
                Log.d("test", "test login");

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response); // 로그인 요청을 한다음 결과값을 json 오브젝트로 받음, 성공 여부 알기 위해 함
                            boolean success = jsonObject.getBoolean("success"); // php에 success가 가는데 그걸 받아와서 판단함
                            if(success)
                            {
                                String userID = jsonObject.getString("userID"); // ID랑 PW 검사
                                String userPW = jsonObject.getString("userPassword");


                                Toast.makeText(getApplicationContext(), "로그인 성공",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity.this,host_or_guest.class);

                                intent.putExtra("userID", userID);
                                intent.putExtra("userPW", userPW);

                                startActivity(intent); // 가입 하고 메인화면 보내주기
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest=  new LoginRequest(userID, userPW, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() { // 회원가입 버튼 눌렀을 때
            @Override
            public void onClick(View view) {
                Intent register_intent = new Intent(MainActivity.this, Sign_up.class);
                startActivity(register_intent);
            }
        }); // 회원가입 페이지 이동
        btn_developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent2 = new Intent(MainActivity.this, host_or_guest.class);
                startActivity(register_intent2);
            }
        });
    }
}