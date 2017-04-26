package ru.pangea.efremov.bonappetit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.pangea.efremov.bonappetit.R;

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
            categoryDescription.setText(categoryName);
//            categoryImage.setImageResource(R.mipmap.ic_launcher);
            String imageUrl;
            switch(position) {
                case 0:
                    imageUrl = "http://pngimg.com/uploads/fish/fish_PNG25151.png";
                    break;
                case 1:
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr9bacuBPTUsPmug6XFBaZz0k1rxUI7rkVdOfYYIeFmwgtXrU";
                    break;
                default:
                    imageUrl = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQbX9GWf3OmCUQF7xG3PXEEF_QRxww5USxaQSPRyEOAlmxgr1Ue";
                    break;
            }
            Picasso.with(context)
                    .load(imageUrl)
                    .into(categoryImage);
        }
    }

    public interface OnNameClickListener {
        void onNameClick(int position, String name);
    }

}
