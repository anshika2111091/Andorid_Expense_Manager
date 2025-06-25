package com.example.expensemanager.views.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.expensemanager.R;
import com.example.expensemanager.databinding.FragmentAddTransactionBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
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
        return binding.getRoot();

    }
}