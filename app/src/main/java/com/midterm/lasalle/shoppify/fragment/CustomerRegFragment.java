package com.midterm.lasalle.shoppify.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midterm.lasalle.shoppify.R;
import com.midterm.lasalle.shoppify.form.customer.CustomerForm;
import com.midterm.lasalle.shoppify.rest.ApiConnection;
import com.midterm.lasalle.shoppify.rest.ApiCustomerCallback;

import org.springframework.http.HttpStatus;

import java.util.Set;

public class CustomerRegFragment extends Fragment implements ApiCustomerCallback {

    private ApiConnection apiConnection;


    public CustomerRegFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiConnection = new ApiConnection(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer_reg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextName = view.findViewById(R.id.editTextName);
        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);

        Button customerRegBtn = view.findViewById(R.id.customerRegBtn);
        customerRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerForm customerForm = new CustomerForm();
                customerForm.setName(editTextName.getText().toString());
                customerForm.setEmail(editTextEmail.getText().toString());
                customerForm.setPassword(editTextPassword.getText().toString());
                apiConnection.createCustomer(customerForm);

                editTextName.setText("");
                editTextEmail.setText("");
                editTextPassword.setText("");
            }
        });
    }

    @Override
    public void postResult(HttpStatus response, String description) {
        if (response != null) {
            if (response == HttpStatus.OK){
                Toast.makeText(getContext(), "Customer Added!", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getContext(), "Status code: " + description, Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(), "Unexpected Error!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void getResult(HttpStatus response, Set<Object> data) {
          //nothing for now
    }

}
