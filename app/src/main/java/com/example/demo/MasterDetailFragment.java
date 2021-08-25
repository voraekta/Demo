package com.example.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.demo.adapter.MainDataListAdapter;
import com.example.demo.model.MasterModel;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MasterDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvMain;
    private MainDataListAdapter mainDataListAdapter;
    ArrayList<MasterModel> dataListModelArrayList;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String myResponse;

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
        String url = "https://api.imgflip.com/get_memes";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    myResponse = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MasterModel masterModel = gson.fromJson(myResponse, MasterModel.class);
                            Log.d("dataa", String.valueOf(masterModel.getData().getMemes().size()));
                            for (int i = 0; i < masterModel.getData().getMemes().size(); i++) {
                                dataListModelArrayList.add(masterModel);
                            }
                            mainDataListAdapter = new MainDataListAdapter(getContext(), dataListModelArrayList);
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            rvMain.setLayoutManager(mLayoutManager);
                            rvMain.setAdapter(mainDataListAdapter);
                        }
                    });
                }
            }
        });
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(getContext(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(getContext(), "on Swiped ", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getAdapterPosition();
                dataListModelArrayList.remove(position);
                mainDataListAdapter.notifyItemRemoved(position);
                mainDataListAdapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvMain);
    }

    @Override
    public void onRefresh() {

    }
}