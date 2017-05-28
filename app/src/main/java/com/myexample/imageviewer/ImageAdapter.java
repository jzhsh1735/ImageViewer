package com.myexample.imageviewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private final Context context;
    private final List<Image> imageList;

    public ImageAdapter(Context context, List<Image> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = imageList.get(position);
        holder.titleView().setText(image.title());
        Picasso.with(context).load(image.url()).into(holder.imageView());
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void add(List<Image> imageList) {
        int position = getItemCount();
        int count = imageList.size();
        this.imageList.addAll(imageList);
        notifyItemRangeChanged(position, count);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

        public TextView titleView() {
            return titleView;
        }

        public ImageView imageView() {
            return imageView;
        }
    }
}
