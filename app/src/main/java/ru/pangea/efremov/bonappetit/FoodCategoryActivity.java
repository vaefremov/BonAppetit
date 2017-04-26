package ru.pangea.efremov.bonappetit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import ru.pangea.efremov.bonappetit.adapters.FoodCategoriesRecyclerViewAdapter;

public class FoodCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        FoodCategoriesRecyclerViewAdapter adapter = new FoodCategoriesRecyclerViewAdapter(this,
                getResources().getStringArray(R.array.categories), onNameClick);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
