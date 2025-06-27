package com.example.expensemanager.views.activities;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expensemanager.adapters.TransactionsAdapter;
import com.example.expensemanager.models.Transaction;
import com.example.expensemanager.utils.Constants;
import com.example.expensemanager.utils.Helper;
import com.example.expensemanager.views.fragments.AddTransactionFragment;
import com.example.expensemanager.R;
import com.example.expensemanager.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        Constants.setCategories();
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

        ArrayList<Transaction> transactions=new ArrayList<>();
        transactions.add(new Transaction( "Cash",Constants.EXPENSE,"Business","Some note here",new Date(),-543,3));
        transactions.add(new Transaction( "Other",Constants.INCOME,"Rent","Some note here",new Date(),663,5 ));
        transactions.add(new Transaction( "Card",Constants.EXPENSE,"Investment","Some note here",new Date(),-434,7));
        transactions.add(new Transaction( "Bank",Constants.INCOME,"Salary","Some note here",new Date(),235 ,9));
        TransactionsAdapter adapter=new TransactionsAdapter(this,transactions);
        binding.transactionsList.setLayoutManager(new LinearLayoutManager(this));
        binding.transactionsList.setAdapter(adapter);
    }
public void updateDate(){

    binding.currentDate.setText(Helper.formatDate(  calendar.getTime()));
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}