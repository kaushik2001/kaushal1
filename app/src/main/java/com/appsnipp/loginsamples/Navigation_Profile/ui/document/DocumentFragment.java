package com.appsnipp.loginsamples.Navigation_Profile.ui.document;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.responce.document_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.document_get_set;
import com.appsnipp.loginsamples.document_recycle.document_adapter;
import com.appsnipp.loginsamples.document_recycle.documnet_data;
import com.appsnipp.loginsamples.notice_recycle.notice_adapter;
import com.appsnipp.loginsamples.notice_recycle.notice_data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentFragment extends Fragment {
    RecyclerView recyclerView;

    SwipeRefreshLayout swipe;
    document_adapter ev;
    List<document_get_set> li;
    private DocumentViewModel documentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        documentViewModel =
                ViewModelProviders.of(this).get(DocumentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_document, container, false);

        recyclerView=(RecyclerView) root.findViewById(R.id.document_recycle);
        swipe=(SwipeRefreshLayout) root.findViewById(R.id.swipe_doc);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.lite_blue));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        loaddocument();
                        swipe.setRefreshing(false);

                    }
                }, 2000);
                // swipe.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        loaddocument();
        return root;
    }

    public void loaddocument()
    {
        Api api= ApiClient.getClient().create(Api.class);
        Call<document_responce> call= api.documentdetailsl("documentdetail");
        call.enqueue(new Callback<document_responce>() {
            @Override
            public void onResponse(Call<document_responce> call, Response<document_responce> response) {
                if (response.body().getSuccess()==200) {
                    li=response.body().getDe();
                    Collections.reverse(li);
                    ev=new document_adapter(li,getContext());

                    recyclerView.setAdapter(ev);
                    LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_from_right);
                    recyclerView.setLayoutAnimation(layoutAnimationController);
                }
                else {
                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<document_responce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });



    }
}