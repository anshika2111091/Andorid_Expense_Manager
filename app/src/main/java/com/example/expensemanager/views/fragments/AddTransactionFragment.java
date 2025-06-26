package com.example.expensemanager.views.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.expensemanager.R;
import com.example.expensemanager.adapters.CategoryAdapter;
import com.example.expensemanager.databinding.FragmentAddTransactionBinding;
import com.example.expensemanager.databinding.ListDialogBinding;
import com.example.expensemanager.models.Category;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
binding=FragmentAddTransactionBinding.inflate(inflater);
binding.incomeBtn.setOnClickListener(c->{
    binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
    binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
    binding.expenseBtn.setTextColor(getContext().getColor(R.color.textColor));
    binding.incomeBtn.setTextColor(getContext().getColor(R.color.greenColor));
});
        binding.expenseBtn.setOnClickListener(c->{
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expense_selector));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.redColor));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.textColor));
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
                }
            });
            datepickerdialog.show();
        });

        binding.category.setOnClickListener(c->{
            ListDialogBinding dialogBinding= ListDialogBinding.inflate(inflater);
            AlertDialog categoryDialog=new AlertDialog.Builder(getContext()).create();
            categoryDialog.setView(dialogBinding.getRoot());
            ArrayList<Category> categories=new ArrayList<>();
            categories.add(new Category("Salary",R.drawable.ic_salary,R.color.category1));
            categories.add(new Category("Business",R.drawable.ic_business,R.color.category2));
            categories.add(new Category("Investment",R.drawable.ic_investment,R.color.category3));
            categories.add(new Category("Loan",R.drawable.ic_loan,R.color.category4));
            categories.add(new Category("Rent",R.drawable.ic_rent,R.color.category5));
            categories.add(new Category("Other ",R.drawable.ic_other,R.color.category6));
            CategoryAdapter categoryAdapter=new CategoryAdapter(getContext(),categories);
            dialogBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3 ));
            dialogBinding.recyclerView.setAdapter(categoryAdapter);
            categoryDialog.show();
        });

        return binding.getRoot();

    }
}