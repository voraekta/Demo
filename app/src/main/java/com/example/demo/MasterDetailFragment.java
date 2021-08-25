package com.example.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.demo.R;
import com.example.demo.adapter.MainDataListAdapter;
import com.example.demo.model.MasterModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MasterDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvMain;
    private MainDataListAdapter mainDataListAdapter;
    String url = "https://api.imgflip.com/get_memes";
    ArrayList<MasterModel> dataListModelArrayList;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public MasterDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMain = view.findViewById(R.id.rv_main);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            }
        });
        dataListModelArrayList = new ArrayList<>();

        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        // GET
        Request get = new Request.Builder()
                .url(url)
                .build();

        client.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    Log.d("responses", responseBody.toString());
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    MasterModel.DataListModel user = gson.fromJson(response.body().charStream(), MasterModel.DataListModel.class);
                    Log.d("data", String.valueOf(user.getMemes()));
                    mainDataListAdapter = new MainDataListAdapter(getContext(), dataListModelArrayList);
                    rvMain.setAdapter(mainDataListAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRefresh() {

    }
}