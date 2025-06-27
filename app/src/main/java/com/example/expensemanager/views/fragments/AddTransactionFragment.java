package com.example.expensemanager.views.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expensemanager.R;
import com.example.expensemanager.adapters.AccountsAdapter;
import com.example.expensemanager.adapters.CategoryAdapter;
import com.example.expensemanager.databinding.FragmentAddTransactionBinding;
import com.example.expensemanager.databinding.ListDialogBinding;
import com.example.expensemanager.models.Account;
import com.example.expensemanager.models.Category;
import com.example.expensemanager.models.Transaction;
import com.example.expensemanager.utils.Constants;
import com.example.expensemanager.views.activities.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AddTransactionFragment extends BottomSheetDialogFragment {



    public AddTransactionFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
FragmentAddTransactionBinding binding;
    Transaction transaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
binding=FragmentAddTransactionBinding.inflate(inflater);


transaction=new Transaction();




binding.incomeBtn.setOnClickListener(c->{
    binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
    binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
    binding.expenseBtn.setTextColor(getContext().getColor(R.color.textColor));
    binding.incomeBtn.setTextColor(getContext().getColor(R.color.greenColor));
    transaction.setType(Constants.INCOME);
});
        binding.expenseBtn.setOnClickListener(c->{
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expense_selector));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.redColor));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.textColor));
            transaction.setType(Constants.EXPENSE);
        });

        binding.date.setOnClickListener(c->{
            DatePickerDialog datepickerdialog=new DatePickerDialog(getContext());
            datepickerdialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH,view.getDayOfMonth());
                    calendar.set(Calendar.MONTH,view.getMonth());
                    calendar.set(Calendar.YEAR ,view.getYear());

                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMM, yyyy ");
                    String dateToShow=dateFormat.format(calendar.getTime());
                    binding.date.setText(dateToShow);
                    transaction.setDate(calendar.getTime());
                    transaction.setId(calendar.getTime().getTime()  );
                }
            });
            datepickerdialog.show();
        });

        binding.category.setOnClickListener(c->{
            ListDialogBinding dialogBinding= ListDialogBinding.inflate(inflater);
            AlertDialog categoryDialog=new AlertDialog.Builder(getContext()).create();
            categoryDialog.setView(dialogBinding.getRoot());

            CategoryAdapter categoryAdapter=new CategoryAdapter(getContext(), Constants.categories, new CategoryAdapter.CategoryClickListener() {
                @Override
                public void onCategoryClick(Category category) {
                    binding.category.setText(category.getCategoryName());
                    transaction.setCategory(category.getCategoryName() );
                    categoryDialog.dismiss();
                }
            });
            dialogBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3 ));
            dialogBinding.recyclerView.setAdapter(categoryAdapter);
            categoryDialog.show();
        });


        binding.account.setOnClickListener(c->{
            ListDialogBinding dialogBinding= ListDialogBinding.inflate(inflater);
            AlertDialog accountsDialog=new AlertDialog.Builder(getContext()).create();
            accountsDialog.setView(dialogBinding.getRoot());
ArrayList<Account> accounts=new ArrayList<>();
accounts.add(new Account(0,"Cash"));
            accounts.add(new Account(0,"Bank"));
            accounts.add(new Account(0,"PayTM "));
            accounts.add(new Account(0,"EasyPaise"));
            accounts.add(new Account(0 ,"Other "));
            AccountsAdapter adapter=new AccountsAdapter(getContext(),accounts, new AccountsAdapter.AccountClickListener() {
                @Override
                public void onAccountSelected(Account account) {
                    binding.account.setText(account.getAccountName());
                    transaction.setAccount(account.getAccountName());
                    accountsDialog.dismiss();
                }
            });
            dialogBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
          dialogBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            dialogBinding.recyclerView.setAdapter(adapter);
            accountsDialog.show();
        });

        binding.saveTransactionBtn.setOnClickListener(c->{
            double amount=Double.parseDouble(binding.amount.getText().toString());
             String note= binding.note.getText().toString();
             if(transaction.getType().equals(Constants.EXPENSE)){
                 transaction.setAmount(amount*-1);
             }
else{
                 transaction.setAmount(amount);
             }
              transaction.setNote(note);

            MainActivity mainActivity = (MainActivity) getActivity();
           mainActivity.viewModel.addTransactions(transaction);
           mainActivity.getTransactions();
            dismiss();
        });
        return binding.getRoot();

    }
}