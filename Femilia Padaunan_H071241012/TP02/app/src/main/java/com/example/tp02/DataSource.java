package com.example.tp02;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Post> generateHomePosts() {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Femiiiii", R.drawable.foto1, R.drawable.foto11, "hiiiii"));
        posts.add(new Post("Lia___", R.drawable.foto2, R.drawable.foto3, "hiiii"));
        posts.add(new Post("ani", R.drawable.foto3, R.drawable.foto4, "hiiii"));
        posts.add(new Post("Anaaa", R.drawable.foto4, R.drawable.foto4, "hiii"));
        posts.add(new Post("Jeniiii", R.drawable.foto5, R.drawable.foto9, "hi"));
        posts.add(new Post("Liaaa", R.drawable.foto6, R.drawable.foto10, "hi"));
        posts.add(new Post("Jelin..", R.drawable.foto7, R.drawable.foto3, "hi"));
        posts.add(new Post("ANDRAA", R.drawable.foto8, R.drawable.foto8, "ihiy"));
        posts.add(new Post("Gebiii", R.drawable.foto9, R.drawable.foto4, "uhuy"));
        posts.add(new Post("Bintangg", R.drawable.foto10, R.drawable.foto7, "hiii"));
        return posts;
    }

    public static ArrayList<Post> profilePosts = new ArrayList<Post>() {{
        add(new Post("jelin...", R.drawable.foto1, R.drawable.foto11, "indah"));
        add(new Post("ANDRA", R.drawable.foto10, R.drawable.foto2, "hi"));
        add(new Post("Bintangg", R.drawable.foto8, R.drawable.foto3, "keren"));
        add(new Post("ani", R.drawable.foto, R.drawable.foto4, "haiii"));
        add(new Post("Femiiiii", R.drawable.foto5, R.drawable.foto1, "semoga"));
    }};

    public static ArrayList<Story> generateStories() {
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(new Story("langit", R.drawable.foto1));
        stories.add(new Story("indahhhh", R.drawable.foto2));
        stories.add(new Story("24dian", R.drawable.foto3));
        stories.add(new Story("scifive", R.drawable.foto4));
        stories.add(new Story("kangen", R.drawable.foto5));
        stories.add(new Story("syggg", R.drawable.foto6));
        stories.add(new Story("kerenbgt", R.drawable.foto7));
        return stories;
    }
}
