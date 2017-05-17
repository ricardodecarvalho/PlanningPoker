package com.ricardodecarvalho.planningpoker;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListCardFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {
    RecyclerViewAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_card, container, false);

        String coffee = getString(R.string.text_coffee);
        String[] data = {"?", "0.5", "0", "1", "2", "3", "5", "8", "13", "21", "...", coffee};

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvNumbers);
        int numberOfColumns = calculateNumberOfColumns(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new RecyclerViewAdapter(getActivity(), data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        //item: adapter.getItem(position)
        //position: position

        Bundle bundle = new Bundle();
        bundle.putString("item", adapter.getItem(position));
        CardFragment cardFragment = new CardFragment();
        cardFragment.setArguments(bundle);

        FragmentTransaction transaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(R.id.main_activity, cardFragment)
                .addToBackStack(null)
                .commit();
    }

    public static int calculateNumberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numberOfColumns = (int) (dpWidth / 180);
        return numberOfColumns;
    }
}
