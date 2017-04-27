package ru.pangea.efremov.bonappetit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.pangea.efremov.bonappetit.datasource.BonAppetitDAO;
import ru.pangea.efremov.bonappetit.datasource.DefaultBonAppetitDAO;

public class DishDetailsActivity extends AppCompatActivity {

    private TextView details;
    private ImageView image;
    private String dishName;
    private int categoryNumber;
    private BonAppetitDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_details);
        dishName = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        categoryNumber = getIntent().getIntExtra(Intent.EXTRA_INDEX, 0);
        dao = DefaultBonAppetitDAO.getInstance();
        initViews();
        fillDetailsForDish(dishName);
    }

    private void initViews() {
        details = (TextView) findViewById(R.id.dish_details);
        image = (ImageView) findViewById(R.id.dish_image);
    }

    private void fillDetailsForDish(String dishName) {
//        image.setImageResource(R.mipmap.ic_launcher_round);
        Uri dishImageUri = dao.getImageUriByName(categoryNumber, dishName);
        if(dishImageUri != null) {
            Picasso.with(this)
                    .load(dishImageUri)
                    .into(image);
        } else {
            image.setImageResource(dao.getImageResourceId(categoryNumber, dishName));
        }
        details.setText(dao.getDetailsByName(categoryNumber, dishName));
    }
}
