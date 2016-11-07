package com.untar.eaglevision;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

public class CategoryAdapter extends BaseAdapter {
    private Context _context;
	private String[] _titleStringIds;
	private TypedArray _iconIds;

    public CategoryAdapter(Context c, int titleResourceId, int iconResourceId) {
        _context = c;
        _titleStringIds = _context.getResources().getStringArray(titleResourceId);
        _iconIds = _context.getResources().obtainTypedArray(iconResourceId);
    }

    public int getCount() {
        return _iconIds.length();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItem;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            gridItem = ((LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
            		inflate(R.layout.category_grid_item, null);
        } else {
            gridItem = convertView;
        }

        ((ImageView) gridItem.findViewById(R.id.grid_item_start_image)).setImageDrawable(_iconIds.getDrawable(position));
        ((TextView) gridItem.findViewById(R.id.grid_item_start_title)).setText(_titleStringIds[position]);
        return gridItem;
    }
    
}
