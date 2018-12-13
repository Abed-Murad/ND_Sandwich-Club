package com.udacity.sandwichclub.acitivty;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.databinding.ActivityMainBinding;
import com.udacity.sandwichclub.databinding.CardSandwichBinding;

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

    private class SandwichesAdapter extends RecyclerView.Adapter {

        private String[] mSandwichesNames;

        public SandwichesAdapter(String[] sandwichesNames) {
            this.mSandwichesNames = sandwichesNames;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return mSandwichesNames.length;
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            CardSandwichBinding mBinding;

            public ViewHolder(CardSandwichBinding binding) {
                super(binding.getRoot());
                this.mBinding = binding;
                mBinding.getRoot().setOnClickListener(view -> launchDetailActivity(getAdapterPosition()));

            }
        }
    }

}
