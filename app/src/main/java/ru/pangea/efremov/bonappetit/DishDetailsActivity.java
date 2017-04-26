package ru.pangea.efremov.bonappetit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DishDetailsActivity extends AppCompatActivity {

    private TextView details;
    private ImageView image;
    private String dishName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_details);
        dishName = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        initViews();
        fillDetailsForDish(dishName);
    }

    private void initViews() {
        details = (TextView) findViewById(R.id.dish_details);
        image = (ImageView) findViewById(R.id.dish_image);
    }

    private void fillDetailsForDish(String dishName) {
//        image.setImageResource(R.mipmap.ic_launcher_round);
        Picasso.with(this)
                .load("http://assets.bonappetit.com/photos/58e2aa9cc1779b17cf869bc9/16:9/w_1028,c_limit/0417-stir-fry-pork-bok-choy%20edit.jpg")
                .into(image);
        details.setText(dishName);
    }
}
