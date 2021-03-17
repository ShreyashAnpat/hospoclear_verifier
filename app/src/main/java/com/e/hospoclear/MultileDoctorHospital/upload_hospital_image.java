package com.e.hospoclear.MultileDoctorHospital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.e.hospoclear.Adapters.UploadImgAdapter;
import com.e.hospoclear.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class upload_hospital_image extends Fragment {
    private static final int PICK_IMG = 100;
    CircleImageView Hospital_profile ;
    ImageButton changeProfile ;
    Button btnAddHospitalImages , mBtnUpload;
    RecyclerView recyclerView ;
    private List<String> ImageList;
    UploadImgAdapter uploadImgAdapter ;
    ProgressDialog progressDialog;
    StorageReference storageReference ;
    FirebaseFirestore firebaseFirestore;
    String UserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload_hospital_image, container, false);
        Hospital_profile = view.findViewById(R.id.hospital_profile);
        changeProfile = view.findViewById(R.id.btnEditProfile);
        recyclerView = view.findViewById(R.id.recycleView2);
        btnAddHospitalImages = view.findViewById(R.id.btnAddHospitalImages);
        mBtnUpload = view.findViewById(R.id.btnUpload);
        firebaseFirestore = FirebaseFirestore.getInstance();
        ImageList = new ArrayList<>();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Fetching data..");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAddHospitalImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                startActivityForResult(intent,PICK_IMG);

            }
        });

        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImg();
                progressDialog.setMessage("Uploading...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        });

        uploadImgAdapter = new UploadImgAdapter(ImageList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(uploadImgAdapter);
        return view ;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMG && resultCode == RESULT_OK) {
            if (data.getClipData() != null) {
                int imgCount = data.getClipData().getItemCount();

                for (int i = 0; i < imgCount; i++) {
                    Uri ImgUri = data.getClipData().getItemAt(i).getUri();
                    String ImgUrl = String.valueOf(ImgUri);
                    ImageList.add(ImgUrl);
                    uploadImgAdapter.notifyDataSetChanged();
                }
            }else {
                Snackbar.make(getView(),"Select minimum 2 images..",Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private void uploadImg() {
        for (int i = 0 ; i < ImageList.size() ; i++) {
            String uri = ImageList.get(i);
            StorageReference file = storageReference.child("HospitalImages").child(System.currentTimeMillis()+".jpg");
            file.putFile(Uri.parse(uri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    file.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()){
                                String Url = task.getResult().toString();
                                HashMap<String,Object> map = new HashMap<>();
                                map.put("ImgUrl",Url);
                                firebaseFirestore.collection("Hospitals").document(UserId)
                                        .collection("Images").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful()){
                                            progressDialog.dismiss();
                                            try {
                                                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
                                                Snackbar.make(getView(),"Upload Successfully !!",Snackbar.LENGTH_LONG).show();
                                            }catch (Exception ignored){

                                            }
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar.make(getView(),"Time out please try again !!",Snackbar.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });
        }
    }

}