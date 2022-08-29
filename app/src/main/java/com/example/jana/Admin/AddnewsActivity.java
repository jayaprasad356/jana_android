package com.example.jana.Admin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jana.R;
import com.example.jana.helper.ApiConfig;
import com.example.jana.helper.Constant;
import com.example.jana.helper.Session;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddnewsActivity extends AppCompatActivity {

    TextInputEditText jobtitle,comp_name,comp_address,emp_qual,emp_exp,salary,interview_date,interview_time,venue,ph_no,mail,more_details,link;
    Spinner lanuguage;
    Button publish;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnews);
        activity = AddnewsActivity.this;

        jobtitle = findViewById(R.id.job_title);
        comp_name = findViewById(R.id.name_company);
        comp_address = findViewById(R.id.address);
        emp_qual = findViewById(R.id.emp_qualification);
        emp_exp = findViewById(R.id.experience);
        salary = findViewById(R.id.salary);
        interview_date = findViewById(R.id.interview_date);
        interview_time = findViewById(R.id.interview_time);
        venue = findViewById(R.id.venue);
        ph_no = findViewById(R.id.phone);
        mail= findViewById(R.id.mail);
        more_details= findViewById(R.id.more_details);
        link= findViewById(R.id.link);
        lanuguage = findViewById(R.id.spinner);
        publish = findViewById(R.id.publish_btn);


        publish.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                postjob();
            }
        });





    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void postjob() {

        Map<String, String> params = new HashMap<>();
        Map<String, String> fileParams = new HashMap<>();
        params.put(Constant.Job_Title, jobtitle.getText().toString().trim());
        params.put(Constant.Company_name, comp_name.getText().toString().trim());
        params.put(Constant.Company_Address, comp_address.getText().toString().trim());
        params.put(Constant.Emp_Experience, emp_exp.getText().toString().trim());
        params.put(Constant.Emp_Qualification, emp_qual.getText().toString().trim());
        params.put(Constant.Salary, salary.getText().toString().trim());
        params.put(Constant.Interview_Date, interview_date.getText().toString().trim());
        params.put(Constant.Interview_Time, interview_time.getText().toString().trim());
        params.put(Constant.Venue, venue.getText().toString().trim());
        params.put(Constant.Phone_no, ph_no.getText().toString().trim());
        params.put(Constant.Mail, mail.getText().toString().trim());
        params.put(Constant.More_details, more_details.getText().toString().trim());
        params.put(Constant.Link, link.getText().toString().trim());
        params.put(Constant.Language, lanuguage.getSelectedItem().toString());

        ApiConfig.RequestToVolley((result, response) ->  {

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {


                        Toast.makeText(this, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(this, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }




        },activity,Constant.Addnew_jobs,params,fileParams);


    }
}