package com.greymatter.earnjoy.Users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.greymatter.earnjoy.R;
import com.greymatter.earnjoy.helper.Session;

import in.aabhasjindal.otptextview.OtpTextView;

public class OtpActivity extends AppCompatActivity {

    Button continue_btn;
    OtpTextView otp_view;
    String MobileNumber;
    TextView tvMobileno;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        session = new Session(OtpActivity.this);



      //  MobileNumber = getIntent().getStringExtra(Constant.MOBILE);



        continue_btn = findViewById(R.id.continue_btn);
        otp_view = findViewById(R.id.otp_view);
        tvMobileno = findViewById(R.id.tvMobileno);

        tvMobileno.setText("OTP sent to "+ MobileNumber);



        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(otp_view.getOTP().length()==6){
                    login();

                }

                else {

                    Toast.makeText(OtpActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void login() {

        Intent intent = new Intent(OtpActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();

//        Map<String, String> params = new HashMap<>();
//        //request
//
//        params.put(Constant.MOBILE,MobileNumber);
//
//
//        ApiConfig.RequestToVolley((result, response) -> {
//            if (result) {
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d("OTP_RES",response);
//
//
//                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
//                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
//                        session.setBoolean("is_logged_in", true);
//                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
//                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
//                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
//
//
//
//
//
//                        startActivity(new Intent(OtpActivity.this, MainActivity.class));
//                        finish();
//                    }
//                    else {
//                        Intent intent = new Intent(OtpActivity.this,SignupActivity.class);
//                        intent.putExtra(Constant.MOBILE,MobileNumber);
//                        startActivity(intent);
//                        finish();
//                    }
//                } catch (JSONException e){
//                    e.printStackTrace();
//                }
//
//
//
//            }
//            else {
//                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();
//
//            }
//            //pass url
//        }, OtpActivity.this, Constant.LOGIN_URL, params,true);


    }
}