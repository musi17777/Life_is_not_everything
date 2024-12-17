package com.example.recyclevapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {

    private ArrayList<DataModel> filteredList; // הרשימה המוצגת בפועל
    private Context context;

    public CustomeAdapter(Context context, ArrayList<DataModel> filteredList) {
        this.context = context;
        this.filteredList = filteredList; // מתייחסים לרשימה המסוננת
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_name);
            textViewVersion = itemView.findViewById(R.id.textView_about_me);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position) {
        DataModel item = filteredList.get(position); // מתייחס לרשימה הנוכחית
        holder.textViewName.setText(item.getName());
        holder.textViewVersion.setText(item.getDescription());
        holder.imageView.setImageResource(item.getImage());

        // מאזין ללחיצה על הרשומה
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "נבחרה הדמות: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size(); // מחזיר את מספר הפריטים ברשימה
    }

    // עדכון הרשימה המסוננת
    public void filterList(ArrayList<DataModel> newList) {
        filteredList = newList;
        notifyDataSetChanged(); // עדכון הרשימה
    }
}
