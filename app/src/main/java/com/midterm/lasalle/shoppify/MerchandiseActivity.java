package com.midterm.lasalle.shoppify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.midterm.lasalle.shoppify.fragment.FragmentAssign;
import com.midterm.lasalle.shoppify.fragment.FragmentShowAllItems;

public class MerchandiseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchandise);
    }

    public void showAllItems(View view){
        FragmentShowAllItems fragmentShowAllItems = new FragmentShowAllItems();
        addFragment(R.id.fragment_container, fragmentShowAllItems);
    }

    public void assignItemsToCustomer(View view){
        FragmentAssign fragmentAssign = new FragmentAssign();
        addFragment(R.id.fragment_container, fragmentAssign);
    }

    public void addFragment(int container, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(container, fragment);
        fragmentTransaction.commit();
    }
}
