package com.example.simpleinstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simpleinstagram.databinding.ItemPostBinding;
import com.parse.ParseFile;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;
    ItemPostBinding binding;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemPostBinding.inflate(LayoutInflater.from(context), parent, false);
        View view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = binding.tvUsername;
            ivImage = binding.ivImage;
            tvDescription = binding.tvDescription;
        }

        public void bind(Post post) {
            tvUsername.setText(post.getKeyUser().getUsername());
            tvDescription.setText(post.getKeyDescription());
            ParseFile image = post.getKeyImage();
            if (image != null){
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

        }
    }
}
