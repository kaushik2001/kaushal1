package com.appsnipp.loginsamples.Navigation_Profile.ui.profile;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.FileProvider;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.BuildConfig;
import com.appsnipp.loginsamples.LoginActivity;
import com.appsnipp.loginsamples.Navigation_Profile.Navigation_Activity;
import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.CommanResponse;
import com.appsnipp.loginsamples.apiinterface.responce.loginresponce;
import com.appsnipp.loginsamples.apiinterface.responce.prof_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.appsnipp.loginsamples.camera.FileCompressor;
import com.appsnipp.loginsamples.profile.forgetpassword;
import com.appsnipp.loginsamples.profile.personaldetails;
import com.appsnipp.loginsamples.profile.professionaldetails;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentManager manager;
    Fragment fragment;
    AlertDialog.Builder builder;

    TextView name,mob;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_GALLERY_PHOTO = 2;
    File mPhotoFile;
    FileCompressor mCompressor;

    ImageView img;


    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        //  tabLayout = root.findViewById(R.id.tablayout_tl);
        mCompressor = new FileCompressor(getContext());
        //  viewPager = root.findViewById(R.id.tablayout_viewpager);
        manager = getActivity().getSupportFragmentManager();
        name=(TextView) root.findViewById(R.id.user_name);
        mob=(TextView) root.findViewById(R.id.user_mob);


        root.findViewById(R.id.exit_pro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sareprefrencelogin.getInstance(getContext()).clear();
                Intent i=new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        root.findViewById(R.id.changepass_pro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder= new AlertDialog.Builder(getContext());
                LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v=inflater.inflate(R.layout.fragment_forgetpassword,null);
                builder.setView(v);
                builder.setCancelable(true);
                AlertDialog alert=builder.create();

                User user= sareprefrencelogin.getInstance(getContext()).getuser();
//                String fname=user.getFname();
//                String lname=user.getLname();
//                String email=user.getEmail()+" ";
//                String mob=user.getMobno();

                EditText ecpass,enewpass,ecnewpass;
                Button save;
                ecpass=v.findViewById(R.id.chngopass);
                enewpass=v.findViewById(R.id.chngnpass);
                ecnewpass=v.findViewById(R.id.chngchecknpass);

                save=v.findViewById(R.id.savebtn_change);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (enewpass.getText().toString().equals(ecnewpass.getText().toString())) {
                            Api api = ApiClient.getClient().create(Api.class);

                            Call<prof_responce> call = api.profpass("profilepersonal", user.getMobno() + "",
                                    ecpass.getText()+"",
                                    enewpass.getText()+"");
                            call.enqueue(new Callback<prof_responce>() {
                                @Override
                                public void onResponse(Call<prof_responce> call, Response<prof_responce> response) {
                                    if (response.body().getSuccess()==200) {
                                        Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                        alert.dismiss();
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<prof_responce> call, Throwable t) {
                                    Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            ecnewpass.setError("Password Doesn`t Match");
                        }
                    }
                });

                //alert.dismiss();
                alert.show();

            }
        });




//        viewPager.setAdapter(new ProfileFragment.adapter(manager));
//        viewPager.setOffscreenPageLimit(3);
//        tabLayout.addOnTabSelectedListener(this);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        img = root.findViewById(R.id.pre_dp);

        img.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                LayoutInflater inflater1 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.fragement_profile_dp, null);
                ImageView dp;
                TextView t;
                dp = v.findViewById(R.id.profile_dp);
                t = v.findViewById(R.id.edit_dp);
                Drawable d = img.getDrawable();

                dp.setImageDrawable(d);

                builder = new AlertDialog.Builder(getActivity());

                builder.setView(v);

                builder.setCancelable(true);
                AlertDialog alert = builder.create();
                alert.show();

                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        selectImage();
                        alert.dismiss();
                    }

                });


                //.setLayoutAnimation(layoutAnimationController);
                alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            }


        });



        root.findViewById(R.id.personal_pro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder= new AlertDialog.Builder(getContext());
                LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v=inflater.inflate(R.layout.fragment_personaldetails,null);
                builder.setView(v);
                builder.setCancelable(true);
                AlertDialog alert=builder.create();
                User user= sareprefrencelogin.getInstance(getContext()).getuser();
                String fname=user.getFname();
                String lname=user.getLname();
                String email=user.getEmail()+" ";
                String mob=user.getMobno();

                EditText efname,elname,eemail,emobno;
                Button save;
                efname=v.findViewById(R.id.personal_fname);
                elname=v.findViewById(R.id.personal_lname);
                eemail=v.findViewById(R.id.personal_email);
                emobno=v.findViewById(R.id.personal_mobno);
                save=v.findViewById(R.id.personal_save);

                efname.setText(fname);
                elname.setText(lname);
                eemail.setText(email);
                emobno.setText(mob);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Api api = ApiClient.getClient().create(Api.class);
                        Call<loginresponce> call=api.personalupdate("profilepersonal",
                                efname.getText()+"",
                                elname.getText()+"",
                                emobno.getText()+"",
                                eemail.getText()+"",
                                user.getHouseno()+"");
                        call.enqueue(new Callback<loginresponce>() {
                            @Override
                            public void onResponse(Call<loginresponce> call, Response<loginresponce> response) {
                                if (response.body().getSuccess()==200) {
                                    loginresponce loginresponce=response.body();
                                    sareprefrencelogin.getInstance(getContext()).saveuser(loginresponce.getUser());
                                    Intent i = new Intent(getContext(), Navigation_Activity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<loginresponce> call, Throwable t) {
                                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                            }
                        });


                        alert.dismiss();
                    }
                });

                alert.show();
            }
        });


        root.findViewById(R.id.professional_pro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder= new AlertDialog.Builder(getContext());
                LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v=inflater.inflate(R.layout.fragment_professionaldetails,null);
                builder.setView(v);
                builder.setCancelable(true);
                AlertDialog alert=builder.create();
                User user= sareprefrencelogin.getInstance(getContext()).getuser();
//                String fname=user.getFname();
//                String lname=user.getLname();
//                String email=user.getEmail()+" ";
//                String mob=user.getMobno();

                EditText eempt,eproft,ecmpname,edesig,ecno;
                Button save;
                eempt=v.findViewById(R.id.prof_emptype);
                eproft=v.findViewById(R.id.prof_proftype);
                ecmpname=v.findViewById(R.id.prof_cmpname);
                edesig=v.findViewById(R.id.prof_desig);
                ecno=v.findViewById(R.id.prof_cno);
                save=v.findViewById(R.id.prof_save);


                Api api = ApiClient.getClient().create(Api.class);
                Call<prof_responce> call=api.profget("profilepersonal", user.getHouseno()+"");
                call.enqueue(new Callback<prof_responce>() {
                    @Override
                    public void onResponse(Call<prof_responce> call, Response<prof_responce> response) {
                        if(response.body().getSuccess()==200){
                            eempt.setText(response.body().getDe().get(0).getEmptype()+"");
                            eproft.setText(response.body().getDe().get(0).getProftype()+"");
                            ecmpname.setText(response.body().getDe().get(0).getCmpname()+"");
                            edesig.setText(response.body().getDe().get(0).getDesi()+"");
                            ecno.setText(response.body().getDe().get(0).getConno()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<prof_responce> call, Throwable t) {

                    }
                });



                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Api api = ApiClient.getClient().create(Api.class);
                        Call<CommanResponse> call=api.profupdate("profilepersonal",
                                eempt.getText()+"",
                                eproft.getText()+"",
                                ecmpname.getText()+"",
                                edesig.getText()+"",
                                ecno.getText()+"",
                                user.getHouseno()+"");
                        call.enqueue(new Callback<CommanResponse>() {
                            @Override
                            public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                                if (response.body().getSuccess()==200){
                                    Toast.makeText(getContext(), "Updated Sucessfully", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(getContext(), "Some error occured", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<CommanResponse> call, Throwable t) {
                                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        alert.dismiss();
                    }
                });


                //alert.dismiss();
                alert.show();
            }
        });


        return root;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user= sareprefrencelogin.getInstance(getContext()).getuser();
        String s=user.getFname()+" "+user.getLname();
        name.setText(s);
        mob.setText(user.getMobno());
    }

//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        viewPager.setCurrentItem(tab.getPosition());
//
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }
//
//
//    public class adapter extends FragmentStatePagerAdapter {
//
//        public adapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            fragment = null;
//            if (position == 0) {
//
//                fragment = new personaldetails();
//
//            }
//            if (position == 1) {
//
//                fragment = new professionaldetails();
//
//            }
//            if (position == 2) {
//
//                fragment = new forgetpassword();
//
//            }
//
//
//            return fragment;
//
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }

    private void selectImage() {
        final CharSequence[] items = {
                "Take Photo", "Choose from Library",
                "Cancel"
        };
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Take Photo")) {
                requestStoragePermission(true);
            } else if (items[item].equals("Choose from Library")) {
                requestStoragePermission(false);
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * Capture image from camera
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);

                mPhotoFile = photoFile;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    /**
     * Select image fro gallery
     */
    private void dispatchGalleryIntent() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                try {
                    mPhotoFile = mCompressor.compressToFile(mPhotoFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(ProfileFragment.this)
                        .load(mPhotoFile)
                        .apply(new RequestOptions().centerCrop()

                                .placeholder(R.drawable.profile_pic_place_holder))
                        .into(img);
            } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                Uri selectedImage = data.getData();
                try {
                    mPhotoFile = mCompressor.compressToFile(new File(getRealPathFromUri(selectedImage)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(ProfileFragment.this)
                        .load(mPhotoFile)
                        .apply(new RequestOptions().centerCrop()

                                .placeholder(R.drawable.profile_pic_place_holder))
                        .into(img);
            }
        }
    }

    /**
     * Requesting multiple permissions (storage and camera) at once
     * This uses multiple permission model from dexter
     * On permanent denial opens settings dialog
     */
    private void requestStoragePermission(final boolean isCamera) {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            if (isCamera) {
                                dispatchTakePictureIntent();
                            } else {
                                dispatchGalleryIntent();
                            }
                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                   PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(
                        error -> Toast.makeText(getContext(), "Error occurred! ", Toast.LENGTH_SHORT)
                                .show())
                .onSameThread()
                .check();
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Need Permissions");
        builder.setMessage(
                "This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    /**
     * Create file with current timestamp name
     *
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        return mFile;
    }

    /**
     * Get real file path from URI
     */
    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            assert cursor != null;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}