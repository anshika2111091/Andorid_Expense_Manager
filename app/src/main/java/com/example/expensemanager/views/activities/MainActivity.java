package com.example.expensemanager.views.activities;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensemanager.views.fragments.AddTransactionFragment;
import com.example.expensemanager.R;
import com.example.expensemanager.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.materialToolbar);
        getSupportActionBar().setTitle("Transactions");

  calendar=Calendar.getInstance();
  updateDate();
  binding.nextDate.setOnClickListener(c->{
      calendar.add(Calendar.DATE,1);
       updateDate();
  });
        binding.previousDate .setOnClickListener(c->{
            calendar.add(Calendar.DATE,- 1);
            updateDate();
        });

        binding.floatingActionButton.setOnClickListener(c->{
             new AddTransactionFragment().show(getSupportFragmentManager(),null);
        });

    }
public void updateDate(){
    SimpleDateFormat dateformat=new SimpleDateFormat("dd MMMM, yyyy");
    binding.currentDate.setText(dateformat.format(calendar.getTime()));
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}