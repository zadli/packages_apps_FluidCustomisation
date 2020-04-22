package com.fluid.customization.fragments.qs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.fluid.customization.BlankFragment;
import com.fluid.customization.utils.FrameModel;
import com.fluid.customization.MainFragment;
import com.fluid.customization.R;
import com.fluid.customization.fragments.qs.QSPFragment;

import java.util.ArrayList;

public class QSFragment extends MainFragment {

    private FrameLayout header;
    private RecyclerView recy;
    ArrayList<FrameModel> listOfFragments = new ArrayList<>();

    public QSFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        if(header != null && recy != null) {
            setupRecycler(getContext(), header, recy, listOfFragments);
        }
    }

    private void inflateList() {
        // Register all top level fragments
        listOfFragments.clear();
        listOfFragments.add(new FrameModel(R.drawable.ic_launcher_background, "QS panel", new QSPFragment()));
        listOfFragments.add(new FrameModel(R.drawable.ic_launcher_background, "Notifications", new BlankFragment()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ArrayList<FrameModel> listOfFragments = new ArrayList<>();


        header = view.findViewById(R.id.header_view);
        recy = view.findViewById(R.id.custom_list);

        TextView one = view.findViewById(R.id.text_fluid);
        TextView two = view.findViewById(R.id.text_customisation);

        setTitle("QS Settings", one, two);

        inflateList();
        setupRecycler(getContext(), header, recy, listOfFragments);

        return view;
    }
}
