package com.midterm.lasalle.shoppify.fragment;


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
import com.midterm.lasalle.shoppify.form.customer.AssignCustomerForm;
import com.midterm.lasalle.shoppify.rest.ApiConnection;
import com.midterm.lasalle.shoppify.rest.ApiMerchandiseCallback;

import org.springframework.http.HttpStatus;

import java.util.Set;

public class FragmentAssign extends Fragment implements ApiMerchandiseCallback {

    private ApiConnection apiConnection;

    public FragmentAssign() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiConnection = new ApiConnection(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_assign, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextCustomerEmail = view.findViewById(R.id.editTextCustomerEmail);
        final EditText editTextItemCode = view.findViewById(R.id.editTextItemCode);

        Button assignBtn = view.findViewById(R.id.assignBtn);
        assignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssignCustomerForm assignCustomerForm = new AssignCustomerForm();
                assignCustomerForm.setEmail(editTextCustomerEmail.getText().toString());
                assignCustomerForm.setCode(editTextItemCode.getText().toString());
                apiConnection.assignCustomerToItems(assignCustomerForm);
            }
        });
    }

    @Override
    public void postResult(HttpStatus response, String description) {
        if (response != null){
            if (response == HttpStatus.OK){
                Toast.makeText(getContext(), "Assigned successfully!", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getContext(), "Status code: " + description, Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(), "Unexpected Error!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void getResult(HttpStatus response, Set<Object> data) {
        //nothing for now
    }
}
