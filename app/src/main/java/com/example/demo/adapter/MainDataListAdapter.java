package com.example.demo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.model.MasterModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainDataListAdapter extends RecyclerView.Adapter<MainDataListAdapter.ViewHolder> {
    Context context;
    ArrayList<MasterModel> dataListModelArrayList;

    public MainDataListAdapter(Context context, ArrayList<MasterModel> dataListModelArrayList) {
        this.context = context;
        this.dataListModelArrayList=dataListModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_main, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MasterModel masterModel=dataListModelArrayList.get(position);
        holder.textView.setText(masterModel.getData().getMemes().get(position).getId());
        Picasso.get().load(masterModel.getData().getMemes().get(position).getUrl()).into(holder.imageView);
        Log.d("adapter",masterModel.getData().getMemes().get(position).getId());

    }

    @Override
    public int getItemCount() {
        return dataListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.iv_main);

        }
    }
}
