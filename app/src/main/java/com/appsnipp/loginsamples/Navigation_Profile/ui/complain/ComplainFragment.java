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
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
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
import com.appsnipp.loginsamples.Navigation_Profile.ui.profile.ProfileFragment;
import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.apiinterface.Api;
import com.appsnipp.loginsamples.apiinterface.ApiClient;
import com.appsnipp.loginsamples.apiinterface.CommanResponse;
import com.appsnipp.loginsamples.apiinterface.responce.event_responce;
import com.appsnipp.loginsamples.apiinterface.responce_get_set.User;
import com.appsnipp.loginsamples.camera.FileCompressor;
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

public class ComplainFragment extends Fragment {
    Button btn_sv;
    EditText ttl,desc;
    AlertDialog.Builder builder;
    private ComplainViewModel complainViewModel;
    ImageView img;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_GALLERY_PHOTO = 2;
    File mPhotoFile;
    FileCompressor mCompressor;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        complainViewModel =
                ViewModelProviders.of(this).get(ComplainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_complain, container, false);
        img=root.findViewById(R.id.cmpphoto);
        btn_sv=root.findViewById(R.id.cmp_save_btn);
        ttl=root.findViewById(R.id.cmp_ttl_ed);
        desc=root.findViewById(R.id.cmp_desc_ed);

        mCompressor = new FileCompressor(getContext());

        btn_sv.setAlpha(0.5f);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        root.findViewById(R.id.cmp_save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_sv.getAlpha()==0.5f){
                    Toast.makeText(getContext(), "Please Select Image", Toast.LENGTH_SHORT).show();
                }else
                    uploadFile();
            }
        });

      //  initDialog();
        return root;
    }

    private void uploadFile() {
//        Map<String, RequestBody> map = new HashMap<>();
//        File file = new File(mPhotoFile.getPath());
//
//
//        RequestBody comp = RequestBody.create(MediaType.parse("text/plain"), "complainentry");
//        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), "title");
//        RequestBody discrip= RequestBody.create(MediaType.parse("text/plain"), "des");
//
//
//        // Parsing any Media type file
//        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
//        map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

        User user= sareprefrencelogin.getInstance(getContext()).getuser();
        String flno= user.getHouseno();





        File imadata = new File(mPhotoFile.getPath());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imadata);
        MultipartBody.Part document_file = MultipartBody.Part.createFormData("document_file", imadata.getName(), requestBody);
//allothers
        RequestBody comp = RequestBody.create(MediaType.parse("text/plain"), "complainentry");
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), ttl.getText().toString());
        RequestBody discrip= RequestBody.create(MediaType.parse("text/plain"), desc.getText().toString());
        RequestBody flatn = RequestBody.create(MediaType.parse("text/plain"), flno);
        RequestBody status = RequestBody.create(MediaType.parse("text/plain"), "Pending");
        if(ttl.getText().length()>0&&desc.getText().length()>0) {
            Api api = ApiClient.getClient().create(Api.class);
            //Call<event_responce> call= api.eventdetail("eventdetail");
            Call<CommanResponse> call = api.complainentry(comp, title, discrip, document_file, flatn, status);
            //Toast.makeText(getContext(), map+"", Toast.LENGTH_SHORT).show();
            call.enqueue(new Callback<CommanResponse>() {
                @Override
                public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                    if (response.body().getSuccess() == 200) {
                        Toast.makeText(getContext(), response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CommanResponse> call, Throwable t) {
                    Toast.makeText(getContext(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(getContext(), "Please Enter Credentials", Toast.LENGTH_SHORT).show();
        }

    }


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
                Glide.with(ComplainFragment.this)
                        .load(mPhotoFile)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.profile_pic_place_holder))
                        .into(img);
        //        btn_sv.setEnabled(true);
                btn_sv.setAlpha(1f);
            } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                Uri selectedImage = data.getData();
                try {
                    mPhotoFile = mCompressor.compressToFile(new File(getRealPathFromUri(selectedImage)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(ComplainFragment.this)
                        .load(mPhotoFile)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.profile_pic_place_holder))
                        .into(img);
          //      btn_sv.setEnabled(true);
                btn_sv.setAlpha(1f);
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
        File storageDir =getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        return mFile;
    }

    /**
     * Get real file path from URI
     */
    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor =getActivity().getContentResolver().query(contentUri, proj, null, null, null);
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