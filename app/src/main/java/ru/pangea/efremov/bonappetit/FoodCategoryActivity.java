package ru.pangea.efremov.bonappetit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;
import android.widget.Toast;

import ru.pangea.efremov.bonappetit.adapters.FoodCategoriesRecyclerViewAdapter;
import ru.pangea.efremov.bonappetit.datasource.BonAppetitDAO;
import ru.pangea.efremov.bonappetit.datasource.DefaultBonAppetitDAO;

public class FoodCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private boolean isLandscapeOrientation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DefaultBonAppetitDAO.initializeForContext(this);
        BonAppetitDAO dao = DefaultBonAppetitDAO.getInstance();
        setContentView(R.layout.activity_food_category);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if(recyclerView == null) {
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view_land);
            isLandscapeOrientation = true;
        }
        FoodCategoriesRecyclerViewAdapter adapter = new FoodCategoriesRecyclerViewAdapter(this,
                dao.getCategoriesNames(), onNameClick);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        if(isLandscapeOrientation) {
            GridLayoutManager layout = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(layout);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    FoodCategoriesRecyclerViewAdapter.OnNameClickListener onNameClick = new FoodCategoriesRecyclerViewAdapter.OnNameClickListener() {
        @Override
        public void onNameClick(int position, String name) {
            Toast.makeText(FoodCategoryActivity.this, name + " " + position, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(FoodCategoryActivity.this, DishesActivity.class);
            i.putExtra(Intent.EXTRA_TEXT, position);
            FoodCategoryActivity.this.startActivity(i);
        }
    };
}
