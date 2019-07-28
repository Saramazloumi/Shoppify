package com.midterm.lasalle.shoppify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.midterm.lasalle.shoppify.fragment.CustomerRegFragment;
import com.midterm.lasalle.shoppify.fragment.ItemRegFragment;
import com.midterm.lasalle.shoppify.rest.ApiMainActivity;

import org.springframework.http.HttpStatus;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCustomerActivity(View view){
        Intent intent = new Intent(this, CustomerActivity.class);
        startActivity(intent);
    }

    public void openItemActivity(View view){
        Intent intent = new Intent(this, MerchandiseActivity.class);
        startActivity(intent);
    }

    public void customerReg(View view){
        CustomerRegFragment customerRegFragment = new CustomerRegFragment();
        addFragment(R.id.fragment_container, customerRegFragment);
    }

    public void itemReg(View view){
        ItemRegFragment itemRegFragment = new ItemRegFragment();
        addFragment(R.id.fragment_container, itemRegFragment);
    }

    public void addFragment(int container, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(container, fragment);
        fragmentTransaction.commit();
    }
}
