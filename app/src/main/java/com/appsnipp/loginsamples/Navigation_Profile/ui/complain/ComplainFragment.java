package com.appsnipp.loginsamples.Navigation_Profile.ui.complain;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.BuildConfig;
import com.appsnipp.loginsamples.Navigation_Profile.Navigation_Activity;
import com.appsnipp.loginsamples.Navigation_Profile.ui.account.AccountFragment;
import com.appsnipp.loginsamples.Navigation_Profile.ui.profile.ProfileFragment;
import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.accout_fragment.bill_details;
import com.appsnipp.loginsamples.accout_fragment.maintence_details;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.CommanResponse;
import com.appsnipp.loginsamples.apiinterface.responce.event_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.appsnipp.loginsamples.camera.FileCompressor;
import com.appsnipp.loginsamples.complain_fragment.complain_enteryFragment;
import com.appsnipp.loginsamples.complain_fragment.complain_showFragment;
import com.appsnipp.loginsamples.storage.sareprefrencelogin;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ComplainFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentManager manager;
    Fragment fragment;

    private ComplainViewModel complainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        complainViewModel =
                ViewModelProviders.of(this).get(ComplainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_complain, container, false);

        tabLayout = root.findViewById(R.id.complain_tablayout);

        viewPager = root.findViewById(R.id.tablayout_complain_viewpager);
        manager = getFragmentManager();
        viewPager.setAdapter(new ComplainFragment.adapter(manager));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.addOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
      //  initDialog();
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
            fragment = null;
            if (position == 0) {

                fragment = new complain_enteryFragment();


            }
            if (position == 1) {

                fragment = new complain_showFragment();

            }


            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}