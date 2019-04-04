package com.hanhan.mobile07.fragment;

/**
 * @author Hanhan
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanhan.mobile07.R;
import com.hanhan.mobile07.adapter.CityAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
 
public class MainFragment extends Fragment{

    @BindView(R.id.rv_data)
    RecyclerView rvData;
    private CityAdapter cityAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.bind(this,view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), manager.getOrientation());
        rvData.addItemDecoration(decoration);
        rvData.setLayoutManager(manager);
        rvData.setAdapter(getCityAdapter());
        return view;
    }

    public CityAdapter getCityAdapter(){
        if(cityAdapter == null){
            cityAdapter = new CityAdapter();
//            cityAdapter.setListener(this);
        }
        return cityAdapter;
    }
}
