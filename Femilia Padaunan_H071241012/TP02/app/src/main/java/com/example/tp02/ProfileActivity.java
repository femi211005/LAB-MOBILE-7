package com.example.tp02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileActivity extends AppCompatActivity {
    private ProfileFeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        de.hdodenhof.circleimageview.CircleImageView ivProfileHeader = findViewById(R.id.iv_profile_header);
        android.widget.TextView tvUsernameHeader = findViewById(R.id.tv_username_header);

        com.example.tp02.Post clickedPost = getIntent().getParcelableExtra("EXTRA_POST");

        if (clickedPost != null) {
            ivProfileHeader.setImageResource(clickedPost.getUserProfileImage());
            tvUsernameHeader.setText(clickedPost.getUsername());
        }

        RecyclerView rvStory = findViewById(R.id.rv_story);
        rvStory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        StoryAdapter storyAdapter = new StoryAdapter(com.example.tp02.DataSource.generateStories());
        rvStory.setAdapter(storyAdapter);

        storyAdapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(ProfileActivity.this, com.example.tp02.DetailStoryActivity.class);
            intent.putExtra("EXTRA_STORY", data);
            startActivity(intent);
        });

        RecyclerView rvProfileFeed = findViewById(R.id.rv_profile_feed);
        rvProfileFeed.setLayoutManager(new GridLayoutManager(this, 3));

        feedAdapter = new ProfileFeedAdapter(com.example.tp02.DataSource.profilePosts);

        rvProfileFeed.setAdapter(feedAdapter);

        feedAdapter.setOnItemClickCallback(data -> {
            com.example.tp02.Post currentProfile = getIntent().getParcelableExtra("EXTRA_POST");
            com.example.tp02.Post postToSend;

            if (currentProfile != null) {
                if (data.getImageUri() != null) {
                    postToSend = new com.example.tp02.Post(
                            currentProfile.getUsername(),
                            currentProfile.getUserProfileImage(),
                            data.getImageUri(),
                            data.getCaption()
                    );
                } else {
                    postToSend = new com.example.tp02.Post(
                            currentProfile.getUsername(),
                            currentProfile.getUserProfileImage(),
                            data.getPostImage(),
                            data.getCaption()
                    );
                }
            } else {
                postToSend = data;
            }

            Intent intent = new Intent(ProfileActivity.this, com.example.tp02.DetailFeedActivity.class);
            intent.putExtra("EXTRA_POST", postToSend);
            startActivity(intent);
        });

        android.widget.ImageView ivAddPostTop = findViewById(R.id.iv_add_post_top);
        ivAddPostTop.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, com.example.tp02.PostActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (feedAdapter != null) {
            feedAdapter.notifyDataSetChanged();

            android.widget.TextView tvPostCount = findViewById(R.id.tv_post_count);
            tvPostCount.setText(String.valueOf(com.example.tp02.DataSource.profilePosts.size()));
        }
    }
}