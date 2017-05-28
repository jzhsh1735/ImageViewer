package com.myexample.imageviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_POSITION;
import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private static final int GRID_COLUMNS = 2;
    private static final List<String> URL_LISI = Arrays.asList(
            "http://farm1.static.flickr.com/229/482030938_2fb6198ab1.jpg",
            "http://farm1.static.flickr.com/34/116236383_2054a5a709.jpg",
            "http://farm1.static.flickr.com/111/269824306_6efd34d3cb.jpg",
            "http://farm3.static.flickr.com/2015/2066310589_127313f9df.jpg",
            "http://farm1.static.flickr.com/149/347763683_06ceb41158.jpg",
            "http://farm1.static.flickr.com/18/22681660_de78a5c17d.jpg",
            "http://farm2.static.flickr.com/1238/969348173_b0cc05ddda.jpg",
            "http://farm2.static.flickr.com/1046/1384635194_2bdfa62893.jpg",
            "http://farm1.static.flickr.com/180/392497682_a04247e176.jpg",
            "http://farm1.static.flickr.com/105/288106053_7539c6de2b.jpg",
            "http://farm2.static.flickr.com/1104/1133697429_1545da51ed.jpg",
            "http://farm2.static.flickr.com/1368/940407538_470b6a572d.jpg",
            "http://farm2.static.flickr.com/1051/1333214704_75f4f4267d.jpg",
            "http://farm2.static.flickr.com/1301/939558363_5baae57dbd.jpg",
            "http://farm2.static.flickr.com/1160/1276808847_4128af3e7f.jpg",
            "http://farm1.static.flickr.com/122/270853702_1708b70033.jpg",
            "http://farm1.static.flickr.com/54/118183180_e5b9917e04.jpg");
    private static final List<Image> IMAGE_LIST = new ArrayList<>();
    static {
        for (int i = 0; i < URL_LISI.size(); i++) {
            IMAGE_LIST.add(new Image(String.valueOf(i), URL_LISI.get(i)));
        }
    }

    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private ImageAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private boolean isLoadingMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(GRID_COLUMNS, VERTICAL);
        recyclerViewAdapter = new ImageAdapter(this, IMAGE_LIST);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int[] lastPositions = staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(null);
                    boolean toLoadMore = false;
                    for (int i = 0; i < lastPositions.length; i++) {
                        if (lastPositions[i] == NO_POSITION || lastPositions[i] == staggeredGridLayoutManager.getItemCount() - 1) {
                            toLoadMore = true;
                        }
                    }
                    if (!isLoadingMore && toLoadMore) {
                        isLoadingMore = true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        recyclerViewAdapter.add(IMAGE_LIST);
        isLoadingMore = false;
    }
}
