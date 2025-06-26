package com.example.expensemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanager.R;
import com.example.expensemanager.databinding.RowAccountsBinding;
import com.example.expensemanager.models.Account;

import java.util.ArrayList;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder> {
    Context context;
    ArrayList<Account> accountArrayList;

    public interface AccountClickListener{
        void onAccountSelected (Account account);
    }
 AccountClickListener accountClickListener;
    public AccountsAdapter(Context context, ArrayList< Account> accountArrayList,AccountClickListener  accountClickListener){
        this.context=context;
        this.accountArrayList=accountArrayList;
        this.accountClickListener=accountClickListener;

    }
    @NonNull
    @Override
    public AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_accounts, parent, false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull AccountsViewHolder holder, int position) {
        Account account=accountArrayList.get(position);
        holder.binding.accountName.setText(account.getAccountName());
        holder.itemView.setOnClickListener(c->{
            accountClickListener.onAccountSelected(account);
        });

    }

    @Override
    public int getItemCount() {
        return accountArrayList.size( );
    }

    public class AccountsViewHolder extends RecyclerView.ViewHolder{
        RowAccountsBinding binding;
        public AccountsViewHolder(@NonNull View itemView){
            super(itemView);
            binding=RowAccountsBinding.bind(itemView);
        }
    }
}
