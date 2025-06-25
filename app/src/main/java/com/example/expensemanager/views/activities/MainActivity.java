package com.example.expensemanager.views.activities;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensemanager.views.fragments.AddTransactionFragment;
import com.example.expensemanager.R;
import com.example.expensemanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.materialToolbar);
        getSupportActionBar().setTitle("Transactions");

        binding.floatingActionButton.setOnClickListener(c->{
             new AddTransactionFragment().show(getSupportFragmentManager(),null);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}