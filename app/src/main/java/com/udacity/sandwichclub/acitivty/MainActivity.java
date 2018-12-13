package com.udacity.sandwichclub.acitivty;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.databinding.ActivityMainBinding;
import com.udacity.sandwichclub.databinding.CardSandwichBinding;
import com.udacity.sandwichclub.utils.CONST;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        String[] sandwichesNames = getResources().getStringArray(R.array.sandwich_names);
        mRecyclerView = mBinding.sandwichesRecyclerView;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SandwichesAdapter(sandwichesNames));

    }

    public void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

    private class SandwichesAdapter extends RecyclerView.Adapter<SandwichesAdapter.ViewHolder> {

        private LayoutInflater mInflater;
        private String[] mSandwichesNames;

        public SandwichesAdapter(String[] sandwichesNames) {
            this.mSandwichesNames = sandwichesNames;
            this.mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @NonNull
        @Override
        public SandwichesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {
            CardSandwichBinding cardBinding = CardSandwichBinding.inflate(mInflater, viewGroup, false);
            return new ViewHolder(cardBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull SandwichesAdapter.ViewHolder viewHolder, int i) {
            viewHolder.bindData(mSandwichesNames[i]);
        }

        @Override
        public int getItemCount() {
            return mSandwichesNames.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CardSandwichBinding mBinding;

            public ViewHolder(CardSandwichBinding itemView) {
                super(itemView.getRoot());
                this.mBinding = itemView;
                mBinding.getRoot().setOnClickListener(view -> launchDetailActivity(getAdapterPosition()));
            }

            private void bindData(String sandwichName) {
                mBinding.sandwichNameTextView.setText(sandwichName);
                Glide.with(MainActivity.this).load(CONST.getSandwichIamgeUrl(getAdapterPosition())).into(mBinding.backgroundImageVIew);


            }
        }
    }

}
