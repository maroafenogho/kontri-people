package com.maro.kontripeople.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maro.kontripeople.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private onPersonListener mOnPersonListener;
    private static List<People> peopleList;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        onPersonListener onPersonListener;
        TextView name, gender, occupation, age;
        public ViewHolder(@NonNull View itemView, onPersonListener onPersonListener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.gender);
            occupation = itemView.findViewById(R.id.occupation);
            age = itemView.findViewById(R.id.age);
            this.onPersonListener = onPersonListener;
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = getAdapterPosition();
                   onPersonListener.onNewsClick(peopleList.get(position));
               }
           });
        }


        public void setOnPersonListener(onPersonListener listener){
            this.onPersonListener = listener;
        }

//        @Override
//        public void onClick(View v) {
//            onPersonListener.onNewsClick(this.getLayoutPosition());
//        }
    }
    public void setResultsList (List<People> peopleList){
        RecyclerAdapter.peopleList = peopleList;
    }

    public RecyclerAdapter(List<People> peopleList, Context context) {
        RecyclerAdapter.peopleList = peopleList;
        this.context = context;
//        this.mOnPersonListener = mOnPersonListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.people_list_item, parent, false);
        return new ViewHolder(itemView, mOnPersonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        People people = peopleList.get(position);
        holder.name.setText(people.getName());
        holder.gender.setText(people.getGender());
        holder.occupation.setText(people.getOccupation());
        holder.age.setText(String.valueOf(people.getAge()));
    }

    public interface onPersonListener {
        void onNewsClick(People person);
    }

    public void setmOnPersonListener(onPersonListener listener){
        mOnPersonListener = listener;
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

}
