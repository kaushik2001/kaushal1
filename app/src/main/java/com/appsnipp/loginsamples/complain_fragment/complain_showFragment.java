package com.appsnipp.loginsamples.complain_fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.responce.cmp_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.cmp_get_set;
import com.appsnipp.loginsamples.cmp_recycle.cmp_adapter;
import com.appsnipp.loginsamples.storage.sareprefrencelogin;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class complain_showFragment extends Fragment {


    RecyclerView recyclerView;

    cmp_adapter ev;
    SwipeRefreshLayout swipe;
    List<cmp_get_set> li;

    public complain_showFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_complain_show, container, false);
        recyclerView=(RecyclerView) root.findViewById(R.id.complainrecycle);
        swipe=(SwipeRefreshLayout) root.findViewById(R.id.swipe_complain);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.lite_blue));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        loadcmp();
                        swipe.setRefreshing(false);

                    }
                }, 2000);
                // swipe.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadcmp();


        return root;
    }

    public void loadcmp()
    {
        User user= sareprefrencelogin.getInstance(getContext()).getuser();
        String flatnn=user.getHouseno();

        Api api= ApiClient.getClient().create(Api.class);
        Call<cmp_responce> call= api.cmp_user_details("complainuserdetail",flatnn);
        call.enqueue(new Callback<cmp_responce>() {
            @Override
            public void onResponse(Call<cmp_responce> call, Response<cmp_responce> response) {
                if (response.body().getSuccess()==200) {
                    li=response.body().getDe();
                    Collections.reverse(li);
                    ev=new cmp_adapter(getContext(),li);

                    recyclerView.setAdapter(ev);
                    LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_from_right);
                    recyclerView.setLayoutAnimation(layoutAnimationController);
                }
                else {
                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<cmp_responce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });



    }


}
