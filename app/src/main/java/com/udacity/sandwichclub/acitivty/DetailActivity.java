package com.udacity.sandwichclub.acitivty;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);


        ImageView ingredientsIv = findViewById(R.id.sandwichImageView);
        TextView originPlaceTv = findViewById(R.id.originTextView);
        TextView alsoknownTv = findViewById(R.id.aKaTextView);
        TextView ingredientsTv = findViewById(R.id.ingredientsTextView);
        TextView descriptionTv = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Glide.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());


        originPlaceTv.setText(sandwich.getPlaceOfOrigin());


        alsoknownTv.setText(sandwich.getAlsoKnownAs() != null ? "⚬ A.K.A : " + sandwich.getAlsoKnownAs() : "");
        originPlaceTv.setText(sandwich.getPlaceOfOrigin() != null ? "⚬ Originally from  " + sandwich.getPlaceOfOrigin() : "");
        ingredientsTv.setText(sandwich.getAlsoKnownAs() != null ? sandwich.getIngredients() : "");

        descriptionTv.setText(sandwich.getDescription());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
