package com.appsnipp.loginsamples.Navigation_Profile.ui.event;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.responce.event_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.event_get_set;
import com.appsnipp.loginsamples.event_recycle_view.event_adapter;
import com.appsnipp.loginsamples.event_recycle_view.event_data;
import com.appsnipp.loginsamples.visitior_recy.visitior_adapter;
import com.appsnipp.loginsamples.visitior_recy.visitior_data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton f;
    event_adapter ev;
    SwipeRefreshLayout swipe;
    AlertDialog.Builder builder;
    List<event_get_set> li;
    EditText etf_name,etf_place,etf_member,etf_date,etf_time;
    Button Sv;
    String etfs_name,etfs_place,etfs_member,etfs_date,etfs_time;
    private EventViewModel eventViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventViewModel =
                ViewModelProviders.of(this).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.fragment_event, container, false);
        recyclerView=(RecyclerView) root.findViewById(R.id.event_recycle);
        swipe=(SwipeRefreshLayout) root.findViewById(R.id.swipe_event);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.lite_blue));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        loadevent();
                        swipe.setRefreshing(false);

                    }
                }, 2000);
               // swipe.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadevent();
//        li=new ArrayList<>();
//        event_data data[] ={new event_data("diwali","party plot","5 member","23/04/2019","2:45 pm")
//        ,new event_data("holi","play ground","6 member","23/06/2019","7:40 am")
//        ,new event_data("new year","club house","6 member","28/04/2019","7:90 am")
//        ,new event_data("navratri","play ground","All Family","22/09/2019","4:40 am")
//                ,new event_data("new year","club house","6 member","28/04/2019","7:90 am")
//                ,new event_data("holi","play ground","6 member","23/06/2019","7:40 am")
//        };
//        for(int i=0;i< data.length;i++){
//            li.add(data[i]);
//        }



        return root;
    }
    public void loadevent()
    {
        Api api= ApiClient.getClient().create(Api.class);
        Call<event_responce> call= api.eventdetail("eventdetail");
        call.enqueue(new Callback<event_responce>() {
            @Override
            public void onResponse(Call<event_responce> call, Response<event_responce> response) {
                if (response.body().getSuccess()==200) {
                    li=response.body().getDe();
                    Collections.reverse(li);
                    ev=new event_adapter(getContext(),li);

                    recyclerView.setAdapter(ev);
                    LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_from_right);
                    recyclerView.setLayoutAnimation(layoutAnimationController);

                }
                else {
                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<event_responce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });



    }
    public void anit()
    {
        Context context=recyclerView.getContext();
        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_from_right);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}