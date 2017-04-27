package ru.pangea.efremov.bonappetit.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.pangea.efremov.bonappetit.R;
import ru.pangea.efremov.bonappetit.datasource.BonAppetitDAO;
import ru.pangea.efremov.bonappetit.datasource.DefaultBonAppetitDAO;

/**
 * Created by efremov on 26/04/17.
 */

public class DishesListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int categoryNumber;
    private String[] dishes;
    private BonAppetitDAO dao;
    private Context context;

    public DishesListAdapter(Context context, int categoryNumber) {
        this.inflater = LayoutInflater.from(context);
        this.categoryNumber = categoryNumber;
        dao = DefaultBonAppetitDAO.getInstance();
        dishes = dao.getCategoryContent(categoryNumber);
        this.context = context;
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
        ImageView dishIcon = (ImageView) convertView.findViewById(R.id.food_category_img);
//        int imageRes = position % 2 == 0 ? R.mipmap.ic_launcher : R.mipmap.chinglish_dish2;
        Uri iconUri = dao.getImageUriByName(categoryNumber, dishes[position]);
        if(iconUri != null) {
            Picasso.with(context)
                    .load(iconUri)
                    .into(dishIcon);
        } else {
            dishIcon.setImageResource(dao.getImageResourceId(categoryNumber, dishes[position]));
        }

        TextView name = (TextView) convertView.findViewById(R.id.category_description);
        name.setText(dishes[position]);
        return convertView;
    }

}
