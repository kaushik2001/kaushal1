package com.appsnipp.loginsamples.Navigation_Profile.ui.profile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.Navigation_Profile.Navigation_Activity;
import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.profile.forgetpassword;
import com.appsnipp.loginsamples.profile.personaldetails;
import com.appsnipp.loginsamples.profile.professionaldetails;

public class ProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentManager manager;
    Fragment fragment;
    AlertDialog.Builder builder;
ImageView img;



    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        tabLayout = root.findViewById(R.id.tablayout_tl);

        viewPager = root.findViewById(R.id.tablayout_viewpager);
        manager = getActivity().getSupportFragmentManager();

      viewPager.setAdapter(new ProfileFragment.adapter(manager));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.addOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        PagerAdapter pagerAdapter=new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
//            @NonNull
//            @Override
//            public Fragment getItem(int position) {
//                fragment = null;
//                if (position == 0) {
//                    fragment = new personaldetails();
//                }
//                if (position == 1) {
//                    fragment = new professionaldetails();
//                }
//                if (position == 2) {
//                    fragment = new forgetpassword();
//                }
//                return fragment;            }
//
//            @Override
//            public int getCount() {
//                return 3;
//            }
//        };
        //final TextView textView = root.findViewById(R.id.text_profile);
//        profileViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                //          textView.setText(s);
//            }
//        });
        img=root.findViewById(R.id.pre_dp);

        img.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                LayoutInflater inflater1=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v=inflater.inflate(R.layout.fragement_profile_dp,null);
                ImageView dp;
                TextView t;
                dp=v.findViewById(R.id.profile_dp);
                t=v.findViewById(R.id.edit_dp);
                Drawable d=img.getDrawable();

                dp.setImageDrawable(d);

                builder = new AlertDialog.Builder(getActivity());

                builder.setView(v);



                builder.setCancelable(true);
                AlertDialog alert = builder.create();
                alert.show();

                //.setLayoutAnimation(layoutAnimationController);
                alert.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            }


        });


        return root;

    }




    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public class adapter extends FragmentStatePagerAdapter {

        public adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            fragment=null;
            if (position == 0) {

                fragment = new personaldetails();

            }
            if (position == 1) {

                fragment = new professionaldetails();

            }
            if (position == 2) {

                fragment = new forgetpassword();

            }


            return fragment;
         //   switch (position) {
//                case 0:
//                    personaldetails personaldetails = new personaldetails();
//                    return personaldetails;
//                case 1:
//                    professionaldetails professionaldetails = new professionaldetails();
//                    return professionaldetails;
//                case 2:
//                    forgetpassword forgetpassword= new forgetpassword();
//                    return forgetpassword;
//                default:
//                    return null;
//            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}