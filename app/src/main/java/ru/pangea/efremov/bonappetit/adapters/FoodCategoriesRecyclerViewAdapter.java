package ru.pangea.efremov.bonappetit.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.pangea.efremov.bonappetit.R;
import ru.pangea.efremov.bonappetit.datasource.BonAppetitDAO;
import ru.pangea.efremov.bonappetit.datasource.DefaultBonAppetitDAO;

/**
 * Created by efremov on 26/04/17.
 */

public class FoodCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<FoodCategoriesRecyclerViewAdapter.FoodKindViewHolder> {

    String[] categories;
    private LayoutInflater inflater;
    private OnNameClickListener clickListener;
    Context context;

    public FoodCategoriesRecyclerViewAdapter(Context context, String[] strings, OnNameClickListener clickListener) {
        categories = strings;
        inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        this.context = context;
    }

    @Override
    public FoodKindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_components, parent, false);
        final FoodKindViewHolder holder = new FoodKindViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position == RecyclerView.NO_POSITION) return;
                clickListener.onNameClick(position, categories[position]);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(FoodKindViewHolder holder, int position) {
        holder.bind(categories[position], position);
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.length;
    }

    class FoodKindViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryImage;
        private TextView categoryDescription;

        public FoodKindViewHolder(View itemView) {
            super(itemView);
            categoryImage = (ImageView) itemView.findViewById(R.id.food_category_img);
            categoryDescription = (TextView) itemView.findViewById(R.id.category_description);
        }

        public void bind(String categoryName, int position) {
            BonAppetitDAO dao = DefaultBonAppetitDAO.getInstance();
            categoryDescription.setText(categoryName);
            Uri imageUrl = dao.getCategoryIconUriByNum(position);
            if (imageUrl != null) {
                Picasso.with(context)
                        .load(imageUrl)
                        .into(categoryImage);
            } else {
                categoryImage.setImageResource(dao.getCategoryIconResByNum(position));
            }
        }
    }

    public interface OnNameClickListener {
        void onNameClick(int position, String name);
    }

}
