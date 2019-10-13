package com.appsnipp.loginsamples.Navigation_Profile.ui.profile;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView t;
    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        tabLayout = root.findViewById(R.id.tablayout_tl);
        t = root.findViewById(R.id.ka1);
        viewPager = root.findViewById(R.id.tablayout_viewpager);
        manager = getActivity().getSupportFragmentManager();

      viewPager.setAdapter(new ProfileFragment.adapter(manager));
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


    public class adapter extends FragmentPagerAdapter {

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