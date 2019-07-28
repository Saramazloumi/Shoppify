package com.midterm.lasalle.shoppify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.midterm.lasalle.shoppify.fragment.FragmentItemsForCustomer;
import com.midterm.lasalle.shoppify.fragment.FragmentShowAllCustomers;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
    }

    public void getAllCustomers(View view){
        FragmentShowAllCustomers fragmentShowAllCustomers = new FragmentShowAllCustomers();
        addFragment(R.id.fragment_container, fragmentShowAllCustomers);
    }

    public void getItemsForCustomers(View view){
        FragmentItemsForCustomer fragmentItemsForCustomer = new FragmentItemsForCustomer();
        addFragment(R.id.fragment_container, fragmentItemsForCustomer);
    }

    public void addFragment(int container, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(container, fragment);
        fragmentTransaction.commit();
    }
}
