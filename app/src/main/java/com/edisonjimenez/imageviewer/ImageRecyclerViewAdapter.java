package com.edisonjimenez.imageviewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final String TAG = ImageRecyclerViewAdapter.class.getSimpleName();
    private List<Photo> mPhotosList;
    private Context mContext;

    public ImageRecyclerViewAdapter(Context context, List<Photo> photosList) {
        this.mPhotosList = photosList;
        mContext = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse, null);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder imageViewHolder, int i) {

        Photo photoItem = mPhotosList.get(i);

        Log.d(TAG, " Processing: " + photoItem.getTitle() + " --> " + Integer.toString(i));

        Picasso.with(mContext).load(photoItem.getLink())
                .placeholder(R.drawable.placeholder)
                .into(imageViewHolder.thumbnail);

        imageViewHolder.title.setText(photoItem.getTitle());
    }


    @Override
    public int getItemCount() {
        return (mPhotosList != null ? mPhotosList.size() : 0);
    }

    public void loadNewData(List<Photo> newPhotos) {
        mPhotosList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return (mPhotosList != null) ? mPhotosList.get(position) : null;
    }
}

