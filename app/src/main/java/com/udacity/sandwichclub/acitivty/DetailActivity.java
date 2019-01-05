package com.udacity.sandwichclub.acitivty;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

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

        populateUI(sandwich);
        setTitle(sandwich.getMainName());
    }


    private void populateUI(Sandwich sandwich) {

        Glide.with(this).load(sandwich.getImage()).into(mBinding.sandwichImageView);

        if (sandwich.getAlsoKnownAs() != null) {
            mBinding.aKaTextView.setText("⚬ a.k.a : " + sandwich.getAlsoKnownAs());
        } else {
            mBinding.aKaTextView.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            mBinding.originTextView.setLayoutParams(params);
        }

        if (!sandwich.getPlaceOfOrigin().equals("")) {
            mBinding.originTextView.setText(sandwich.getPlaceOfOrigin());
        } else {
            if (sandwich.getAlsoKnownAs()!= null) {
                mBinding.originTextView.setVisibility(View.GONE);
            } else {
                mBinding.infoCard.setVisibility(View.INVISIBLE);
            }
        }
        mBinding.descriptionTextView.setText(sandwich.getDescription());
        mBinding.ingredientsTextView.setText(sandwich.getIngredients());


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

}
