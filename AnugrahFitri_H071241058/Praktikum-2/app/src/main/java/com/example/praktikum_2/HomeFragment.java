package com.example.praktikum_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rvHome = view.findViewById(R.id.rvHome);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Post> postList = new ArrayList<>();
        postList.add(new Post("ira.fitri4343", R.drawable.profile_pic, R.drawable.post1, "Lagi lihat buku di gramed"));
        postList.add(new Post("user_unhas_1", R.drawable.user1_pic, R.drawable.user1_post, "Suasana kantin hari ini."));
        postList.add(new Post("user_unhas_2", R.drawable.user2_pic, R.drawable.user2_post, "Tugas Android menumpuk."));
        postList.add(new Post("user_unhas_3", R.drawable.user3_pic, R.drawable.user3_post, "Semangat mahasiswa SI!"));
        postList.add(new Post("user_unhas_4", R.drawable.user4_pic, R.drawable.user4_post, "Coding is fun."));
        postList.add(new Post("user_unhas_5", R.drawable.user5_pic, R.drawable.user5_post, "Pantai Losari malam hari."));
        postList.add(new Post("user_unhas_6", R.drawable.user6_pic, R.drawable.user6_post, "Mabar di selasar."));
        postList.add(new Post("user_unhas_7", R.drawable.user7_pic, R.drawable.user7_post, "Liburan singkat."));
        postList.add(new Post("user_unhas_8", R.drawable.user8_pic, R.drawable.user8_post, "Buku baru di perpus."));
        postList.add(new Post("user_unhas_9", R.drawable.user9_pic, R.drawable.user9_post, "Sunset di Gowa."));

        HomeAdapter adapter = new HomeAdapter(postList, post -> {
            ((MainActivity)getActivity()).navigateToProfile(post);
        });

        rvHome.setAdapter(adapter);

        return view;
    }
}