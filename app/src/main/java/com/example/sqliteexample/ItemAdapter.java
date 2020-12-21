package com.example.sqliteexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    Context context;
    ArrayList item_id, item_name, item_date;

    public ItemAdapter(Context context, ArrayList item_id, ArrayList item_name, ArrayList item_date) {
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_date = item_date;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(item_id.get(position) + " ");
        holder.date.setText(item_date.get(position) + " ");
        holder.name.setText(item_name.get(position) + " ");
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

    TextView date, id, name;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date_added);
        id = itemView.findViewById(R.id.item_id);
        name = itemView.findViewById(R.id.name);
    }
}
}
