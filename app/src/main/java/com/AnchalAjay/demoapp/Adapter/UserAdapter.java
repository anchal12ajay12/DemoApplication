package com.AnchalAjay.demoapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.AnchalAjay.demoapp.Pojo.User;
import com.AnchalAjay.demoapp.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements ChildEventListener {
    private ArrayList<User> users;
    private DatabaseReference reference;
    private Context mContext;

    public UserAdapter(ArrayList<User> users,DatabaseReference reference,Context context){
        this.users = users;
        this.mContext = context;
        this.reference = reference;
        if(reference!=null){
            reference.addChildEventListener(this);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.card_user, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.populate(users.get(position));
        String name = "User";
        if (user.getFirst_name() != null && user.getLast_name() != null)
        {
            name = user.getFirst_name() + " " + user.getLast_name();
            name = capitalize(name);
        }
        holder.tv_username.setText(name);
        if(user.getProfile_image()!=null) {
            Glide.with(mContext).load(user.getProfile_image()).into(holder.imageView);
            Log.e("UserImage", user.getProfile_image());
        }
        String age = "0";
        if(user.getDob()!=null){
            String[] stringAge = user.getDob().split("-");
            age = getAge(Integer.parseInt(stringAge[2]), Integer.parseInt(stringAge[1]),Integer.parseInt(stringAge[0]));
        }
        String gender = "Other";
        if(user.getGender()!=null &&  (user.getGender().trim().equalsIgnoreCase("male") || user.getGender().trim().equalsIgnoreCase("female")
                || user.getGender().trim().equalsIgnoreCase("M") || user.getGender().trim().equalsIgnoreCase("F"))){
            gender = user.getGender();
            gender = capitalize(gender);
        }
        String state = "";
        if(user.getState()!=null){
            state = user.getState();
            state = capitalize(state);
        }

        String detail = gender + " | " + age + " | " + state;
        holder.tv_detail.setText(detail);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        users.add(dataSnapshot.getValue(User.class));
        notifyDataSetChanged();
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        users.remove(dataSnapshot.getValue(User.class));
        notifyDataSetChanged();
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_username,tv_detail;
        public ImageView imageView,deleteIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_user_name);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            imageView = itemView.findViewById(R.id.iv_user_image);
            deleteIcon = itemView.findViewById(R.id.iv_delete);

        }
        public void populate(User user){
            deleteIcon.setOnClickListener(v->{
                    if(reference!=null)
                        reference.child(user.getPhone_number()).setValue(null);
                    users.remove(user);
                Toast.makeText(mContext, "User Deleted!", Toast.LENGTH_SHORT).show();
            });
        }

    }

    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = Integer.valueOf(age);

        return ageInt.toString();
    }

    public static String capitalize(String str)
    {
        if(str == null) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
