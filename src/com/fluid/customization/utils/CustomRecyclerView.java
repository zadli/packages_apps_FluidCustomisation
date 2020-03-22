package com.fluid.customization.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.fluid.customization.R;

import java.util.ArrayList;

public class CustomRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int count;
    private final ArrayList<FrameModel> frameList;
    private Context context;

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 0;


    public CustomRecyclerView(Context context, ArrayList<FrameModel> frameList) {
        this.frameList = frameList;
        this.context = context;
        this.count = frameList.size() + 1;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
            return new HeaderViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
            return new ItemViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.title.setText(frameList.get(position - 1).getTitle());
            itemViewHolder.background.setImageResource(frameList.get(position - 1).getBackground());
            itemViewHolder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentActivity fragmentActivity = (FragmentActivity) context;

                    FragmentManager fm = fragmentActivity.getSupportFragmentManager();

                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment frag = frameList.get(position - 1).getFragment();
                    ft.replace(R.id.container, frag);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        final TextView title;
        final ImageView background;
        final FrameLayout container;

        ItemViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.card_title);
            background = view.findViewById(R.id.background_image);
            container = view.findViewById(R.id.frame_container);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        HeaderViewHolder(View view) {
            super(view);
        }
    }
}