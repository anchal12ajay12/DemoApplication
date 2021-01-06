package com.AnchalAjay.demoapp.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.AnchalAjay.demoapp.Pojo.User;
import com.AnchalAjay.demoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static java.lang.String.*;

public class EnrollFragment extends Fragment {

    TextView first_name,last_name, dob, gender, country,state, home_town, phone_number;
    Button add_user;
    ImageView profile_pic;
    LinearLayout ll_progress_bar;
    FirebaseDatabase db;
    DatabaseReference dbRef;
    // Uri indicates, where the image will be picked from
    private Uri filePath;
    private int mYear, mMonth, mDay, mHour, mMinute;
    FirebaseStorage storage;
    Bitmap bitmap;
    StorageReference storageReference;
    String profile_url_string="";
    // request code
    private final int PICK_IMAGE_REQUEST = 71;

    public EnrollFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();



        first_name = view.findViewById(R.id.tv_first_name);
        last_name = view.findViewById(R.id.tv_last_name);
        dob = view.findViewById(R.id.tv_dob);
        gender = view.findViewById(R.id.tv_gender);
        country = view.findViewById(R.id.tv_country);
        state = view.findViewById(R.id.tv_state);
        home_town = view.findViewById(R.id.tv_home_town);
        phone_number = view.findViewById(R.id.tv_phone_number);
        add_user = view.findViewById(R.id.btn_add_user);
        profile_pic = view.findViewById(R.id.iv_profile_pic);
        ll_progress_bar = view.findViewById(R.id.ll_progress_bar);

        dob.setOnClickListener(v -> {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        (view1, year, monthOfYear, dayOfMonth) -> {
                    int month  = monthOfYear+1;
                            String date = dayOfMonth + "-" + month + "-" + year;
                            dob.setText(date);
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            });

        profile_pic.setOnClickListener(v -> chooseImage());



        add_user.setOnClickListener(v -> {
            ll_progress_bar.setVisibility(View.VISIBLE);
            add_user.setEnabled(false);
            String s_f_name = first_name.getText().toString();
            String s_l_name = last_name.getText().toString();
            String s_dob = dob.getText().toString();
            String s_gender = gender.getText().toString();
            String s_country = country.getText().toString();
            String s_state = state.getText().toString();
            String s_home_town = home_town.getText().toString();
            String s_phone_number = phone_number.getText().toString();

            if(!s_gender.trim().equalsIgnoreCase("male") && !s_gender.trim().equalsIgnoreCase("female")
                    && !s_gender.trim().equalsIgnoreCase("M") && !s_gender.trim().equalsIgnoreCase("F")){
                Toast.makeText(getContext(), "Enter Valid Gender! Male or Female", Toast.LENGTH_SHORT).show();
                ll_progress_bar.setVisibility(View.GONE);
                add_user.setEnabled(true);
                return;
            }

            if(s_phone_number.length()!=10){
                Toast.makeText(getContext(), "Enter Valid Phone Number!\nLength Should be 10.", Toast.LENGTH_SHORT).show();
                ll_progress_bar.setVisibility(View.GONE);
                add_user.setEnabled(true);
                return;
            }

            if(!s_country.isEmpty() && !s_dob.isEmpty() && !s_f_name.isEmpty() && !s_gender.isEmpty() && !s_home_town.isEmpty()
                && !s_l_name.isEmpty() && !s_phone_number.isEmpty() && !s_state.isEmpty()
            ){
                long tsLong = System.currentTimeMillis()/1000;
                User user = new User(s_f_name,s_l_name,
                        s_dob,s_gender,s_country,s_state, s_home_town,s_phone_number, tsLong);


                dbRef.child("Users").child(s_phone_number).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            // user exists in the database
                            ll_progress_bar.setVisibility(View.GONE);
                            add_user.setEnabled(true);
                            Toast.makeText(getContext(), "User Already Exist!", Toast.LENGTH_SHORT).show();

                        }else{
                            uploadImage(s_phone_number,user);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        ll_progress_bar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Error:" + " " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        add_user.setEnabled(true);
                    }
                });
            }
            else {
                Toast.makeText(getContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enroll, container, false);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                profile_pic.setImageBitmap(bitmap);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(String s_phone, User user) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] data = baos.toByteArray();

        if(bitmap != null)
        {
            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());




            ref.putBytes(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String profile_url = task.getResult().toString();
                                    user.setProfile_image(profile_url);
                                    dbRef.child("Users").child(s_phone).setValue(user);
                                    ll_progress_bar.setVisibility(View.GONE);
                                    add_user.setEnabled(true);
                                    Toast.makeText(getContext(), "User Successfully created.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                        }
                    });
        }
        else {
            ll_progress_bar.setVisibility(View.GONE);
            add_user.setEnabled(true);
            Toast.makeText(getContext(), "Please select an image!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}