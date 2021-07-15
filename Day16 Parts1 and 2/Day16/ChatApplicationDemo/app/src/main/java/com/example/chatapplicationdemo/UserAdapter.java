package com.example.chatapplicationdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    List<String> userList;
    String userName;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    public UserAdapter(Context context, List<String> userList, String userName) {
        this.context = context;
        this.userList = userList;
        this.userName = userName;
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    @NonNull
    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_users,
                parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.UserViewHolder holder, int position) {

        databaseReference.child("Users").child(userList.get(position)).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        String otherName = snapshot.child("userName").getValue().toString();
                        String imageURL = snapshot.child("image").getValue().toString();
                        holder.userNameTextView.setText(otherName);
                        if (imageURL.equals("null")) {
                            holder.userImage.setImageResource(R.drawable.baseline_account_circle_black_24dp);
                        } else{
                            Picasso.get().load(imageURL).into(holder.userImage);
                        }
                        holder.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(context, ChatActivity.class);
                                intent.putExtra("userName", userName);
                                intent.putExtra("otherName", otherName);
                                context.startActivity(intent);

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        ImageView userImage;
        CardView cardView;

        public UserViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.imv_user);
            cardView = itemView.findViewById(R.id.root_card_view);
            userNameTextView = itemView.findViewById(R.id.txv_user_name);
        }
    }
}
