package com.example.simpleinstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.simpleinstagram.databinding.ActivityPostDetailBinding;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PostDetailActivity extends AppCompatActivity {

    Post post;

    TextView tvUsername;
    ImageView ivImage;
    TextView tvDescription;
    TextView tvCreatedAt;
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
        tvCreatedAt = binding.tvCreatedAt;

        post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        tvUsername.setText(post.getKeyUser().getUsername());
        tvDescription.setText(post.getKeyDescription());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String s = df.format(post.getKeyCreatedAt());
        tvCreatedAt.setText(s);

        Context context = getApplicationContext();
        Glide.with(context).load(post.getKeyImage().getUrl()).into(ivImage);
    }
}