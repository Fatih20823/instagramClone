package com.example.instagramclonejava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclonejava.databinding.RecylerRowBinding;
import com.example.instagramclonejava.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private ArrayList<Post> postArrayList;

    public PostAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    class PostHolder extends RecyclerView.ViewHolder{
        RecylerRowBinding recylerRowBinding;

        public PostHolder(@NonNull RecylerRowBinding recylerRowBinding) {
            super(recylerRowBinding.getRoot());
            this.recylerRowBinding = recylerRowBinding;
        }
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecylerRowBinding recylerRowBinding = RecylerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(recylerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.recylerRowBinding.recyclerViewUserEmailText.setText(postArrayList.get(position).getEmail());
        holder.recylerRowBinding.recyclerViewCommentText.setText(postArrayList.get(position).getComment());
        Picasso.get().load(postArrayList.get(position).getDowloadUrl()).into(holder.recylerRowBinding.reclerViewImageView);
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

}
