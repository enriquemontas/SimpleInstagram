package com.example.simpleinstagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simpleinstagram.EndlessRecyclerViewScrollListener;
import com.example.simpleinstagram.Post;
import com.example.simpleinstagram.PostAdapter;
import com.example.simpleinstagram.databinding.FragmentHomeBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "HomeFragment";
    public static final int POST_LIMIT = 20;
    RecyclerView rvPosts;
    protected PostAdapter postAdapter;
    protected List<Post> allPosts;
    FragmentHomeBinding binding;
    SwipeRefreshLayout swipeLayout;
    EndlessRecyclerViewScrollListener scrollListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPosts = binding.rvPosts;
        allPosts = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), allPosts);
        rvPosts.setAdapter(postAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(layoutManager);
        swipeLayout = binding.swipeContainer;
        swipeLayout.setOnRefreshListener(this);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i(TAG, "need more");
                loadMoreData(page);
            }
        };
        rvPosts.addOnScrollListener(scrollListener);

        queryPosts();
    }

    private void loadMoreData(int page) {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setSkip((page)*POST_LIMIT);
        query.setLimit(POST_LIMIT);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "error in retrieving posts", e);
                    return;
                }
                for (Post p : posts){
                    Log.i(TAG, p.getKeyDescription());
                }
                postAdapter.addAll(posts);
                postAdapter.notifyDataSetChanged();
            }
        });
    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(POST_LIMIT);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "error in retrieving posts", e);
                    return;
                }
                for (Post p : posts){
                    Log.i(TAG, p.getKeyDescription());
                }
                allPosts.addAll(posts);
                postAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh() {
        queryPosts();
        swipeLayout.setRefreshing(false);
    }
}