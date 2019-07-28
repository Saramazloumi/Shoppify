package com.midterm.lasalle.shoppify.fragment;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.midterm.lasalle.shoppify.R;
import com.midterm.lasalle.shoppify.form.customer.CustomerForm;
import com.midterm.lasalle.shoppify.form.customer.CustomerList;
import com.midterm.lasalle.shoppify.rest.ApiConnection;
import com.midterm.lasalle.shoppify.rest.ApiCustomerCallback;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Set;

public class FragmentShowAllCustomers extends ListFragment implements ApiCustomerCallback {

    private ApiConnection apiConnection;

    private HttpStatus httpStatus;
    private Set<Object> dataSet;
    ArrayList<CustomerForm> list;

    public FragmentShowAllCustomers() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiConnection = new ApiConnection(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_show_all_customers, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CustomerList customerList = new CustomerList();
        customerList.setType(CustomerForm.class);
        apiConnection.getAllCustomers(customerList);
    }

    @Override
    public void postResult(HttpStatus response, String description) {
        //nothing for now
    }

    @Override
    public void getResult(HttpStatus response, Set<Object> data) {

        this.httpStatus = response;
        this.dataSet = data;

        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list = new ArrayList<>();
                if (httpStatus == HttpStatus.OK){
                    for (Object data : dataSet){
                        CustomerForm customerForm = new CustomerForm();
                        customerForm.setName(((CustomerForm)data).getName());
                        customerForm.setEmail(((CustomerForm)data).getEmail());
                        customerForm.setPassword(((CustomerForm)data).getPassword());
                        list.add(customerForm);
                    }
                    ArrayAdapter<CustomerForm> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
                    setListAdapter(adapter);
                }
            }
        });
    }
}
