package com.example.simpleinstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.simpleinstagram.databinding.ActivityPostDetailBinding;

import org.parceler.Parcels;

public class PostDetailActivity extends AppCompatActivity {

    Post post;

    TextView tvUsername;
    ImageView ivImage;
    TextView tvDescription;
    ActivityPostDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPostDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tvUsername = binding.tvUsername;
        ivImage = binding.ivImage;
        tvDescription = binding.tvDescription;

        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        tvUsername.setText(post.getKeyUser().getUsername());
        tvDescription.setText(post.getKeyDescription());

        Context context = getApplicationContext();
        Glide.with(context).load(post.getKeyImage().getUrl()).into(ivImage);
    }
}