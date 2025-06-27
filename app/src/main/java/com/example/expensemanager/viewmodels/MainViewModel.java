package com.example.expensemanager.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.expensemanager.models.Transaction;
import com.example.expensemanager.utils.Constants;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainViewModel extends AndroidViewModel  {

   public MutableLiveData<RealmResults<Transaction>> transactions=new MutableLiveData<>();
   public MutableLiveData<Double> totalIncome=new MutableLiveData<>();
    public MutableLiveData<Double> totalExpense=new MutableLiveData<>();
    public MutableLiveData<Double> totalAmount =new MutableLiveData<>();
    static Realm realm;
    public MainViewModel(@NonNull Application application) {
        super(application);
        Realm.init(application);
        setupDatabase();
    }

    public void  getTransactions(Calendar calendar ){
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND ,0);
        RealmResults<Transaction> newtransactions=realm.where(Transaction.class)
                .greaterThanOrEqualTo("date",calendar.getTime())
                .lessThan("date",new Date(calendar.getTime().getTime() +(24*60*60*1000)))
                .findAll();
double income=realm.where(Transaction.class)
        .greaterThanOrEqualTo("date",calendar.getTime())
        .lessThan("date",new Date(calendar.getTime().getTime() +(24*60*60*1000)))
        .equalTo("type",Constants.INCOME).sum("amount").doubleValue();
        double expense=realm.where(Transaction.class)
                .greaterThanOrEqualTo("date",calendar.getTime())
                .lessThan("date",new Date(calendar.getTime().getTime() +(24*60*60*1000)))
                .equalTo("type",Constants.EXPENSE).sum("amount").doubleValue();
        double total=realm.where(Transaction.class)
                .greaterThanOrEqualTo("date",calendar.getTime())
                .lessThan("date",new Date(calendar.getTime().getTime() +(24*60*60*1000)))
                .sum("amount").doubleValue();
        totalIncome.setValue(income);
        totalExpense.setValue(expense);
        totalAmount.setValue(total);


//        RealmResults<Transaction> newtransactions=realm.where(Transaction.class)
//                .equalTo("date",calendar.getTime())
//                .findAll();
        transactions.setValue(newtransactions);
    }
    public static  void addTransactions(Transaction transaction ){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(transaction);
        realm.commitTransaction();
    }
    void setupDatabase(){
        realm = Realm.getDefaultInstance();
    }

}
