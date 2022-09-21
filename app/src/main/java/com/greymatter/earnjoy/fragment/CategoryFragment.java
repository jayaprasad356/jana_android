package com.greymatter.earnjoy.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.greymatter.earnjoy.R;
import com.greymatter.earnjoy.adapter.CategoryAdapter;
import com.greymatter.earnjoy.helper.ApiConfig;
import com.greymatter.earnjoy.helper.Constant;
import com.greymatter.earnjoy.model.Category;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CategoryFragment extends Fragment {


    RecyclerView recyclerView;
    Activity activity;




    public CategoryFragment() {
        // Required empty public constructor
    }

    CategoryAdapter categoryAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root = inflater.inflate(R.layout.fragment_category, container, false);


        recyclerView = root.findViewById(R.id.recyclerView);


        activity = getActivity();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
        recyclerView.setLayoutManager(gridLayoutManager);


        categoryList();


       return root;
    }

    private void categoryList() {

        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("CAT_RES",response);

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Category> categories = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Category group = g.fromJson(jsonObject1.toString(), Category.class);
                                categories.add(group);
                            } else {
                                break;
                            }
                        }

                        //important
                        categoryAdapter = new CategoryAdapter(activity, categories);
                        recyclerView.setAdapter(categoryAdapter);




                    }
                    else {
                        Toast.makeText(getActivity(), ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.CATEGORY_LIST, params, true);




    }
}