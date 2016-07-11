package com.example.hicham.properties;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Hicham on 6/22/2016.
 */
public class MyItemListener implements AdapterView.OnItemLongClickListener {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        MyDialog dialog = new MyDialog(view.getContext(),view,(Appartment)parent.getAdapter().getItem(position));
        dialog.getBuilder().show();
        return true;
    }
}
