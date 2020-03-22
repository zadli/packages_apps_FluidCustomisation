package com.fluid.customization;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fluid.customization.fragments.clock.ClockFragment;
import com.fluid.customization.fragments.gesture.GestureFragment;
import com.fluid.customization.fragments.nav.NavFragment;
import com.fluid.customization.fragments.qs.QSFragment;
import com.fluid.customization.utils.CustomRecyclerView;
import com.fluid.customization.utils.FrameModel;
import com.fluid.customization.utils.LinearLayoutManagerAccurateOffset;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private FrameLayout header;
    private RecyclerView recy;

    ArrayList<FrameModel> listOfFragments = new ArrayList<>();

    public MainFragment() {
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
        listOfFragments.add(new FrameModel(R.drawable.ic_qs_svg, "QS", new QSFragment()));
        listOfFragments.add(new FrameModel(R.drawable.ic_clock_svg, "Clock", new ClockFragment()));
        listOfFragments.add(new FrameModel(R.drawable.ic_gestures, "Gestures", new GestureFragment()));
        listOfFragments.add(new FrameModel(R.drawable.ic_navigation_svg, "Navigation", new NavFragment()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        header = view.findViewById(R.id.header_view);
        recy = view.findViewById(R.id.custom_list);

        TextView one = view.findViewById(R.id.text_fluid);
        TextView two = view.findViewById(R.id.text_customisation);

        setTitle("fluid Customisations", one, two);

        inflateList();
        setupRecycler(getContext(), header, recy, listOfFragments);

        return view;
    }

    public void setupRecycler(Context context, final View header, final RecyclerView recy, ArrayList<FrameModel> listOfFragments) {
        CustomRecyclerView listAdapter = new CustomRecyclerView(context, listOfFragments);
        recy.setAdapter(listAdapter);
        LinearLayoutManagerAccurateOffset llm = new LinearLayoutManagerAccurateOffset(context, LinearLayout.VERTICAL, false);
        llm.setOrientation(RecyclerView.VERTICAL);
        recy.setLayoutManager(llm);
        recy.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (header != null) {
                    translateHeader(header, recy.computeVerticalScrollOffset());
                }
            }
        });
    }

    private void translateHeader(View header, float of) {
        header.setTranslationY(-(of * 0.5f));
    }

    public void setTitle(String str, TextView one, TextView two) {
        String[] splits = str.split(" ");
        if (one != null && two != null) {
            if (splits.length > 1) {
                one.setText(String.format("%s ", splits[0]));
                two.setText(splits[1]);
            } else if (splits.length == 1) {
                one.setText(splits[0]);
                two.setText("");
            }
        }
    }
}
