package ru.pangea.efremov.bonappetit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ru.pangea.efremov.bonappetit.adapters.DishesListAdapter;

public class DishesActivity extends AppCompatActivity {

    private String[] dishes;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        final int categoryNumber = getIntent().getIntExtra(Intent.EXTRA_TEXT, 0);
        dishes = findDishesListByCategory(categoryNumber);
        BaseAdapter adapter = new DishesListAdapter(this, categoryNumber);
        listView = (ListView) findViewById(R.id.dishes_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DishesActivity.this, dishes[position], Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DishesActivity.this, DishDetailsActivity.class);
                i.putExtra(Intent.EXTRA_TEXT, dishes[position]);
                i.putExtra(Intent.EXTRA_INDEX, categoryNumber);
                startActivity(i);
            }
        });
    }

    private String[] findDishesListByCategory(int categoryNumber) {
        switch (categoryNumber) {
            case 0:
                return getResources().getStringArray(R.array.fishes);
            case 1:
                return getResources().getStringArray(R.array.meats);
            case 2:
                return getResources().getStringArray(R.array.others);
            default:
                return new String[0];
        }
    }

}
