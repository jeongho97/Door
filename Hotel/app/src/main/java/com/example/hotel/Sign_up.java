package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Sign_up extends AppCompatActivity {

    private Button btn_register;
    private EditText et_id,et_pw,et_name,et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_id = findViewById(R.id.id);
        et_pw = findViewById(R.id.pw);
        et_name = findViewById(R.id.name);
        et_age = findViewById(R.id.age);
        btn_register = findViewById(R.id.register); // 값 찾아주기

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 입력된 사용자 정보 받아오기
                String userID = et_id.getText().toString();
                String userPW = et_pw.getText().toString();
                String userName = et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString()); // int 형으로 형변환

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { // 서버 보낼때 그냥 string으로 못하므로 json 오브젝트로 만들어서 서버로 보내야 함(포장)
                        try {
                            JSONObject jsonObject = new JSONObject(response); // 회원가입 요청을 한다음 결과값을 json 오브젝트로 받음, 성공 여부 알기 위해 함
                            boolean success = jsonObject.getBoolean("success"); // php에 success가 가는데 그걸 받아와서 판단함
                            if(success)
                            {
                                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Sign_up.this, MainActivity.class);
                                startActivity(intent); // 가입 하고 메인화면 보내주기
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "회원가입이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(userID, userPW, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Sign_up.this);
                queue.add(registerRequest); // Volley를 이용해서 서버로 요청함
            }
        });
    }
}