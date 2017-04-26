package ru.pangea.efremov.bonappetit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.pangea.efremov.bonappetit.R;

/**
 * Created by efremov on 26/04/17.
 */

public class DishesListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private String[] dishes;

    public DishesListAdapter(Context context, String[] strings) {
        this.inflater = LayoutInflater.from(context);
        this.dishes = strings;
    }

    @Override
    public int getCount() {
        return dishes == null ? 0 : dishes.length;
    }

    @Override
    public Object getItem(int position) {
        return dishes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recycler_components, parent, false);
        }
        ImageView avatar = (ImageView) convertView.findViewById(R.id.food_category_img);
        int imageRes = position % 2 == 0 ? R.mipmap.ic_launcher : R.mipmap.ic_launcher_round;
        avatar.setImageResource(imageRes);
        TextView name = (TextView) convertView.findViewById(R.id.category_description);
        name.setText(dishes[position]);
        return convertView;
    }

}
