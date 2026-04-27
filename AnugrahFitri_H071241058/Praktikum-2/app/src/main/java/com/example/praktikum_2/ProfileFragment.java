package com.example.praktikum_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProfileFragment extends Fragment {

    private TextView tvFullName, tvBio, tvUsernameTop, tvPostsCount, tvFollowersCount, tvFollowingCount;
    private ImageView imgProfile;
    private View layoutButtons, scrollHighlights;
    private Button btnEditProfile;

    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String newName = data.getStringExtra("NAME");
                    String newUsername = data.getStringExtra("USERNAME");
                    String newBio = data.getStringExtra("BIO");
                    String newImageUri = data.getStringExtra("NEW_IMAGE");

                    if (newName != null) tvFullName.setText(newName);
                    if (newUsername != null) tvUsernameTop.setText(newUsername);
                    if (newBio != null) tvBio.setText(newBio);
                    if (newImageUri != null && !newImageUri.isEmpty()) {
                        imgProfile.setImageURI(Uri.parse(newImageUri));
                    }
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvFullName = view.findViewById(R.id.tvFullName);
        tvBio = view.findViewById(R.id.tvBio);
        tvUsernameTop = view.findViewById(R.id.tvUsernameTop);
        imgProfile = view.findViewById(R.id.imgProfile);
        tvPostsCount = view.findViewById(R.id.tvPostsCount);
        tvFollowersCount = view.findViewById(R.id.tvFollowersCount);
        tvFollowingCount = view.findViewById(R.id.tvFollowingCount);
        layoutButtons = view.findViewById(R.id.layoutButtons);
        scrollHighlights = view.findViewById(R.id.scrollHighlights);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);

        Bundle bundle = getArguments();
        List<Post> gridList = new ArrayList<>();

        String currentUsername = (bundle != null) ? bundle.getString("p_username") : "ira.fitri4343";

        if (currentUsername == null || currentUsername.equals("ira.fitri4343")) {
            tvUsernameTop.setText("ira.fitri4343");
            tvFullName.setText("Anugrah Fitri Novanda");
            tvBio.setText("Universitas Hasanuddin\nSistem Informasi 2024");
            imgProfile.setImageResource(R.drawable.profile_pic);
            tvPostsCount.setText(String.valueOf(DataRepository.userPosts.size()));
            tvFollowersCount.setText("1.255");
            tvFollowingCount.setText("1.926");

            layoutButtons.setVisibility(View.VISIBLE);
            scrollHighlights.setVisibility(View.VISIBLE);
            gridList = DataRepository.userPosts;
            setupHighlights(view);

            btnEditProfile.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra("OLD_NAME", tvFullName.getText().toString());
                intent.putExtra("OLD_USERNAME", tvUsernameTop.getText().toString());
                intent.putExtra("OLD_BIO", tvBio.getText().toString());
                editProfileLauncher.launch(intent);
            });
        } else {
            int imageRes = bundle.getInt("p_image");
            int postImage = bundle.getInt("p_post_image");
            String caption = bundle.getString("p_caption");

            tvUsernameTop.setText(currentUsername);
            tvFullName.setText(currentUsername);
            imgProfile.setImageResource(imageRes);
            tvBio.setText("Student @ Unhas\nExploring Informatics");

            tvPostsCount.setText("1");
            tvFollowersCount.setText(new Random().nextInt(900) + 100 + "");
            tvFollowingCount.setText(new Random().nextInt(900) + 100 + "");

            layoutButtons.setVisibility(View.GONE);
            scrollHighlights.setVisibility(View.GONE);
            gridList.add(new Post(currentUsername, imageRes, postImage, caption));
        }

        RecyclerView rvGrid = view.findViewById(R.id.gridPosts);
        rvGrid.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvGrid.setNestedScrollingEnabled(false);

        ProfileAdapter adapter = new ProfileAdapter(gridList, post -> {
            Intent intent = new Intent(getActivity(), DetailFeedActivity.class);
            // Mencari posisi post di DataRepository untuk DetailFeedActivity
            int pos = DataRepository.userPosts.indexOf(post);
            intent.putExtra("POSITION", pos != -1 ? pos : 0);
            startActivity(intent);
        });
        rvGrid.setAdapter(adapter);

        return view;
    }

    private void setupHighlights(View view) {
        int[] ids = {R.id.highlight1, R.id.highlight2, R.id.highlight3, R.id.highlight4, R.id.highlight5, R.id.highlight6, R.id.highlight7};
        String[] titles = {"anime", "coding", "game", "travel", "food", "movie", "music"};
        int[] imgs = {R.drawable.anime, R.drawable.coding, R.drawable.game, R.drawable.travel, R.drawable.food, R.drawable.movie, R.drawable.music};

        for (int i = 0; i < ids.length; i++) {
            View hl = view.findViewById(ids[i]);
            if (hl != null) {
                ((ImageView) hl.findViewById(R.id.imgHighlight)).setImageResource(imgs[i]);
                ((TextView) hl.findViewById(R.id.tvHighlightName)).setText(titles[i]);

                int finalI = i;
                hl.setOnClickListener(v -> {
                    Intent it = new Intent(getActivity(), DetailStoryActivity.class);
                    it.putExtra("STORY_TITLE", titles[finalI]);
                    it.putExtra("STORY_IMAGE", imgs[finalI]);
                    startActivity(it);
                });
            }
        }
    }
}
