package com.appsnipp.loginsamples.Navigation_Profile.ui.dashboard;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appsnipp.loginsamples.Navigation_Profile.ui.account.AccountFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.complain.ComplainFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.document.DocumentFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.electionandpoll.ElectionFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.event.EventFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.members.MembersFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.resource.ResourceFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.visitor.VisitorFragment;
import com.appsnipp.loginsamples.R;

public class DashBoardFragment extends Fragment {
    CardView c1,c2,c3,c4,c5,c6,c7,c8;
    FragmentManager manager;
    FragmentTransaction transaction;
    Fragment fragment;
    NavigationView navigationView;
    private DashBoardViewModel dashBoardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashBoardViewModel =
                ViewModelProviders.of(this).get(DashBoardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        dashBoardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        navigationView =root.findViewById(R.id.nav_view);
        manager=getActivity().getSupportFragmentManager();
        c1=(CardView) root.findViewById(R.id.card_account);
        c2=(CardView) root.findViewById(R.id.card_resource);
        c3=(CardView) root.findViewById(R.id.card_document);
        c4=(CardView) root.findViewById(R.id.card_member);
        c5=(CardView) root.findViewById(R.id.card_event);
        c6=(CardView) root.findViewById(R.id.card_election);
        c7=(CardView) root.findViewById(R.id.card_complain);
        c8=(CardView) root.findViewById(R.id.card_visitor);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new AccountFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ResourceFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new DocumentFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new MembersFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new EventFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ElectionFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ComplainFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new VisitorFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return root;
    }



   // @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.card_account:
//                Toast.makeText(getContext(), "kaushal", Toast.LENGTH_SHORT).show();

//                break;
//            case R.id.card_resource:

//                break;
//            case R.id.card_visitor:
//                fragment = new VisitorFragment();
//                transaction = manager.beginTransaction();
//                transaction.add(R.id.nav_host_fragment, fragment, "A");
//                transaction.addToBackStack("addA");
//                transaction.commit();
//                break;
//            case R.id.card_event:
//                fragment = new EventFragment();
//                transaction = manager.beginTransaction();
//                transaction.add(R.id.nav_host_fragment, fragment, "A");
//                transaction.addToBackStack("addA");
//                transaction.commit();
//                break;
//            case R.id.card_election:
//                fragment = new ElectionFragment();
//                transaction = manager.beginTransaction();
//                transaction.add(R.id.nav_host_fragment, fragment, "A");
//                transaction.addToBackStack("addA");
//                transaction.commit();
//                break;
//            case R.id.card_document:
//                fragment = new DocumentFragment();
//                transaction = manager.beginTransaction();
//                transaction.add(R.id.nav_host_fragment, fragment, "A");
//                transaction.addToBackStack("addA");
//                transaction.commit();
//                break;
//            case R.id.card_complain:
//                fragment = new ComplainFragment();
//                transaction = manager.beginTransaction();
//                transaction.add(R.id.nav_host_fragment, fragment, "A");
//                transaction.addToBackStack("addA");
//                transaction.commit();
//                break;
//            case R.id.card_member:
//                fragment = new MembersFragment();
//                transaction = manager.beginTransaction();
//                transaction.add(R.id.nav_host_fragment, fragment, "A");
//                transaction.addToBackStack("addA");
//                transaction.commit();
//                break;
//        }
//    }
}