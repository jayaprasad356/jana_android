package com.greymatter.earnjoy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.greymatter.earnjoy.adapter.NewsjobAdapter;
import com.greymatter.earnjoy.helper.ApiConfig;
import com.greymatter.earnjoy.helper.Constant;
import com.greymatter.earnjoy.helper.Session;
import com.greymatter.earnjoy.model.Job_list;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoblistActivity extends AppCompatActivity {

    Session session;
    Activity activity;
    RecyclerView recyclerView;
    public static NewsjobAdapter newsjobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joblist);
        session = new Session(activity);
        activity = JoblistActivity.this;


        recyclerView = findViewById(R.id.recycler_joblist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        joblist();

    }

    private void joblist() {

        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response)->{
            Log.d("CAT_RES",response);

            if (result) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();

                        ArrayList<Job_list> answers = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Job_list job_list = g.fromJson(jsonObject1.toString(), Job_list.class);
                                answers.add(job_list);
                            } else {
                                break;
                            }
                        }
                        newsjobAdapter = new NewsjobAdapter(activity, answers);
                        recyclerView.setAdapter(newsjobAdapter);



                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }


        },activity, Constant.Listnews_jobs, params, true);


    }
}