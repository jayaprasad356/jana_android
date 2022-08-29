package com.example.jana.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jana.Admin.MainActivity;
import com.example.jana.R;
import com.example.jana.Users.HomeActivity;
import com.example.jana.Users.MoredetailsActivity;
import com.example.jana.adapter.NewsjobAdapter;
import com.example.jana.adapter.SliderAdapterExample;
import com.example.jana.helper.ApiConfig;
import com.example.jana.helper.Constant;
import com.example.jana.helper.Session;
import com.example.jana.model.Job_list;
import com.example.jana.model.Slide;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.*;


public class DashboardFragment extends Fragment {




   

    public DashboardFragment() {
        // Required empty public constructor
    }
    SliderView sliderView;
    private SliderAdapterExample adapter;
    View rootview;
    Session session;
    Activity activity;
    RecyclerView recyclerView;
    public static NewsjobAdapter newsjobAdapter;
    EditText edSearchjob;
    NestedScrollView nestedScrollView;
    LinearLayout lytSearchView;
    boolean searchVisible = false;








    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_dashboard, container, false);
        session = new Session(activity);
        activity = getActivity();



        recyclerView = rootview.findViewById(R.id.recycler_joblist);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
       // recyclerView.setLayoutManager(linearLayoutManager);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
//        recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));




        edSearchjob = rootview.findViewById(R.id.edSearchjob);
        lytSearchView = rootview.findViewById(R.id.lytSearchView);
        nestedScrollView = rootview.findViewById(R.id.nestedScrollView);



        sliderView = rootview.findViewById(R.id.image_slider);
        adapter = new SliderAdapterExample(getActivity());


        edSearchjob.setOnTouchListener((View v, MotionEvent event) -> {
            Fragment fragment = new CategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.FROM, "search");
            bundle.putString(Constant.NAME, activity.getString(R.string.search));
            bundle.putString(Constant.ID, "");
            fragment.setArguments(bundle);
            HomeActivity.fm.beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
            return false;
        });



        joblist();


        slideslist();
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });


        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                Rect scrollBounds = new Rect();
                nestedScrollView.getHitRect(scrollBounds);
                if (!lytSearchView.getLocalVisibleRect(scrollBounds) || scrollBounds.height() < lytSearchView.getHeight()) {
                    searchVisible = true;

                } else {
                    searchVisible = false;

                }
                activity.invalidateOptionsMenu();
            });
        }

        edSearchjob.setOnTouchListener((View v, MotionEvent event) -> {
//            Fragment fragment = new ProductListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString(Constant.FROM, "search");
//            bundle.putString(Constant.NAME, activity.getString(R.string.search));
//            bundle.putString(Constant.ID, "");
//            fragment.setArguments(bundle);
//            MainActivity.fm.beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(null).commit();
            return false;
        });

        lytSearchView.setOnClickListener(v -> {
//            Fragment fragment = new ProductListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString(Constant.FROM, "search");
//            bundle.putString(Constant.NAME, activity.getString(R.string.search));
//            bundle.putString(Constant.ID, "");
//            fragment.setArguments(bundle);
//            MainActivity.fm.beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(null).commit();
        });







        return rootview;
    }

    private void slideslist() {

        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Slide> slides = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Slide group = g.fromJson(jsonObject1.toString(), Slide.class);
                                slides.add(group);
                            } else {
                                break;
                            }
                        }
                        adapter.renewItems(slides);



                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.SLIDES_LIST, params, true);








    }


    private void joblist()
    {
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