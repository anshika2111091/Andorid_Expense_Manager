package com.example.expensemanager.views.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expensemanager.adapters.TransactionsAdapter;
import com.example.expensemanager.models.Transaction;
import com.example.expensemanager.utils.Constants;
import com.example.expensemanager.utils.Helper;
import com.example.expensemanager.viewmodels.MainViewModel;
import com.example.expensemanager.views.fragments.AddTransactionFragment;
import com.example.expensemanager.R;
import com.example.expensemanager.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;


import java.util.Calendar;
import java.util.Date;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
Calendar calendar;

/*
0=Daily
1=Monthly
2=Calendar
3=Summary
4=Notes
 */
    int selectedTab=0;

public MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(MainViewModel.class);


        setSupportActionBar(binding.materialToolbar);
        getSupportActionBar().setTitle("Transactions");
        Constants.setCategories();
  calendar=Calendar.getInstance();
  updateDate();
  binding.nextDate.setOnClickListener(c->{
      if(Constants.SELECTED_TAB==Constants.DAILY) {
          calendar.add(Calendar.DATE, 1);
      }
      else if(Constants.SELECTED_TAB==Constants.MONTHLY){
          calendar.add(Calendar.MONTH, 1);
      }
      updateDate();
  });
        binding.previousDate .setOnClickListener(c->{
            if(Constants.SELECTED_TAB==Constants.DAILY) {
                calendar.add(Calendar.DATE, -1);
            }
            else if(Constants.SELECTED_TAB==Constants.MONTHLY){
                calendar.add(Calendar.MONTH, -1);
            }
            updateDate();
        });

        binding.floatingActionButton.setOnClickListener(c->{
             new AddTransactionFragment().show(getSupportFragmentManager(),null);
        });



    binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
           if(tab.getText().equals("Monthly")){
               Constants.SELECTED_TAB=1;
               updateDate();

           }
            else if(tab.getText().equals("Daily")){
                Constants.SELECTED_TAB=0;
                updateDate();

            }
            else if(tab.getText().equals("Calender")){
                Constants.SELECTED_TAB=2;

            }
           else if(tab.getText().equals("Summary")){
                Constants.SELECTED_TAB=3;

            }
           else if(tab.getText().equals("Notes")){
                Constants.SELECTED_TAB=4;
            }


        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
updateDate();
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
updateDate();
        }
    });


        binding.transactionsList.setLayoutManager(new LinearLayoutManager(this));     //Select * from transactions
         //Select * from transactions where id=5
viewModel.transactions.observe(this, new Observer<RealmResults<Transaction>>() {
    @Override
    public void onChanged(RealmResults<Transaction> transactions) {
        TransactionsAdapter adapter=new TransactionsAdapter(MainActivity.this,transactions);
        binding.transactionsList.setAdapter(adapter);
        if(transactions.size()>0){
            binding.emptyState.setVisibility(View.GONE );
        }
        else{
            binding.emptyState.setVisibility(View.VISIBLE);
        }


    }
});

viewModel.totalExpense.observe(this, new Observer<Double>() {
    @Override
    public void onChanged(Double aDouble) {
        binding.expenseValue.setText(String.valueOf(aDouble));
    }
});
        viewModel.totalIncome.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.incomeValue.setText(String.valueOf(aDouble));
            }
        });
        viewModel.totalAmount.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.totalAmount.setText(String.valueOf(aDouble));
            }
        });
viewModel.getTransactions(calendar);
    }

public void getTransactions(){viewModel.getTransactions(calendar);
}
public void updateDate(){
        if(Constants.SELECTED_TAB==Constants.DAILY ) {
            binding.currentDate.setText(Helper.formatDate(calendar.getTime()));
        }
        else if(Constants.SELECTED_TAB==Constants.MONTHLY) {
            binding.currentDate.setText(Helper.formatDateByMonth( calendar.getTime()));
        }
    viewModel.getTransactions(calendar);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}