package ru.pangea.efremov.bonappetit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.pangea.efremov.bonappetit.R;

/**
 * Created by efremov on 26/04/17.
 */

public class FoodCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<FoodCategoriesRecyclerViewAdapter.FoodKindViewHolder> {

    String[] categories;
    private LayoutInflater inflater;
    private OnNameClickListener clickListener;

    public FoodCategoriesRecyclerViewAdapter(Context context, String[] strings, OnNameClickListener clickListener) {
        categories = strings;
        inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
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
        holder.bind(categories[position]);
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.length;
    }

    static class FoodKindViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryImage;
        private TextView categoryDescription;

        public FoodKindViewHolder(View itemView) {
            super(itemView);
            categoryImage = (ImageView) itemView.findViewById(R.id.food_category_img);
            categoryDescription = (TextView) itemView.findViewById(R.id.category_description);
        }

        public void bind(String categoryName) {
            categoryDescription.setText(categoryName);
            categoryImage.setImageResource(R.mipmap.ic_launcher);
        }
    }

    public interface OnNameClickListener {
        void onNameClick(int position, String name);
    }

}
