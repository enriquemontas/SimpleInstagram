package com.example.simpleinstagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simpleinstagram.databinding.ItemPostBinding;
import com.parse.ParseFile;

import org.parceler.Parcels;

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

    public void addAll(List<Post> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(binding.getRoot());
            tvUsername = binding.tvUsername;
            ivImage = binding.ivImage;
            tvDescription = binding.tvDescription;
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            tvUsername.setText(post.getKeyUser().getUsername());
            tvDescription.setText(post.getKeyDescription());
            ParseFile image = post.getKeyImage();
            if (image != null){
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                Post p = posts.get(position);
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(p));
                context.startActivity(intent);
            }
        }
    }
}
