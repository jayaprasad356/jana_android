package com.greymatter.earnjoy.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greymatter.earnjoy.R;
import com.greymatter.earnjoy.Users.MoredetailsActivity;
import com.greymatter.earnjoy.model.Job_list;

import java.util.ArrayList;

public class NewsjobAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Job_list> job_lists;


    public  NewsjobAdapter (Activity activity,ArrayList<Job_list> job_list){

        this.activity = activity;
        this.job_lists = job_list ;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.job_list, parent, false);
        return new JobItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position)
    {
        final JobItemHolder holder = (JobItemHolder) holderParent;
        final Job_list job_list = job_lists.get(position);
     // Toast.makeText(activity, ""+ job_list.getEmail(), Toast.LENGTH_SHORT).show();

        holder.salary.setText(job_list.getSalary());
        holder.tvJobtitle.setText(job_list.getJob_title());
        holder.tvInterviewDate.setText(job_list.getInterview_date());
        holder.tvVenue.setText(job_list.getVenue());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MoredetailsActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return job_lists.size();
    }

    static class JobItemHolder extends RecyclerView.ViewHolder {

        final TextView salary,tvJobtitle,tvInterviewDate,tvVenue;


        public JobItemHolder(@NonNull View itemView) {
            super(itemView);

             salary = itemView.findViewById(R.id.salary_tv);
             tvJobtitle = itemView.findViewById(R.id.tvJobtitle);
             tvInterviewDate = itemView.findViewById(R.id.tvInterviewDate);
             tvVenue = itemView.findViewById(R.id.tvVenue);
//            questions = itemView.findViewById(R.id.question);
//            quesno = itemView.findViewById(R.id.question_no);
//            edit = itemView.findViewById(R.id.edit_ques);
        }
    }

}
