package com.untar.eaglevision;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class CategoryFragment extends Fragment {
	GridView categoryGrid;
	OnCategorySelectedListener _listener;
	
	public interface OnCategorySelectedListener {
        public void onCategorySelected(int position);
    }

	@Override
    public void onAttach(Activity activity) {
		//Verify and attach call back listener in parent activity
        super.onAttach(activity);
        try {
            _listener = (OnCategorySelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCategorySelectedListener");
        }
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.category_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		//Until this point, none of the views actually exist. This is the first place we can actually
		//access them with findViewById(). Therefore, it's the first place we can call setAdapter()
		super.onActivityCreated(savedInstanceState);
		categoryGrid = (GridView) getActivity().findViewById(R.id.category_grid);
		categoryGrid.setAdapter(new CategoryAdapter(getActivity(), R.array.CategoryTitles, R.array.CategoryIcons));
		
		categoryGrid.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	_listener.onCategorySelected(position);
	        }
	    });
	}

	public void setActiveItem(int position) {
		//This will activate/check one item in the grid and deactivate the rest
		//Probably should create a custom checkable grid view for this
	}
}
