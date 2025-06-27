package com.example.expensemanager.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanager.R;
import com.example.expensemanager.databinding.RowTransactionBinding;
import com.example.expensemanager.models.Category;
import com.example.expensemanager.models.Transaction;
import com.example.expensemanager.utils.Constants;
import com.example.expensemanager.utils.Helper;

import java.util.ArrayList;

import io.realm.RealmResults;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder> {
   private final RealmResults<Transaction> transactions;
   Context context;
   public TransactionsAdapter(Context context,RealmResults<Transaction> transactions){
       this.context=context;
       this.transactions=transactions;
   }

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionsViewHolder(LayoutInflater.from(context).inflate(R.layout.row_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        holder.binding.transactionAmount.setText(String.valueOf(transaction.getAmount()));
        holder.binding.accountLbl.setText(String.valueOf(transaction.getAccount()));
        holder.binding.transactionDate.setText(Helper.formatDate(transaction.getDate()));
        holder.binding.transactionCategory.setText(transaction.getCategory());

        Category transactionCategory = Constants.getCategoryDetails(transaction.getCategory());
        if (transactionCategory != null) {


            holder.binding.categoryIcon.setImageResource(transactionCategory.getCategoryImage());
            try {
                holder.binding.categoryIcon.setBackgroundTintList(
                        context.getColorStateList(transactionCategory.getCategoryColor()));
            } catch (Resources.NotFoundException e) {
                holder.binding.categoryIcon.setBackgroundTintList(
                        context.getColorStateList(R.color.black));
            }
        } else {
            holder.binding.categoryIcon.setImageResource(R.drawable.ic_launcher_background);
            holder.binding.categoryIcon.setBackgroundTintList(
                    context.getColorStateList(R.color.black));
        }
holder.binding.accountLbl.setBackgroundTintList(context.getColorStateList(Constants.getAccountsColor(transaction.getAccount())));

        if (Constants.INCOME.equals(transaction.getType())) {
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.greenColor));
        } else if (Constants.EXPENSE.equals(transaction.getType())) {
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.redColor));
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class TransactionsViewHolder extends RecyclerView.ViewHolder{
       RowTransactionBinding binding;
       public TransactionsViewHolder(View itemView){
           super(itemView);
           binding=RowTransactionBinding.bind(itemView);

       }
   }
}
