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
import com.midterm.lasalle.shoppify.form.item.MerchandiseForm;
import com.midterm.lasalle.shoppify.rest.ApiConnection;
import com.midterm.lasalle.shoppify.rest.ApiMerchandiseCallback;

import org.springframework.http.HttpStatus;

import java.util.Set;

public class ItemRegFragment extends Fragment implements ApiMerchandiseCallback {

    private ApiConnection apiConnection;

    public ItemRegFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiConnection = new ApiConnection(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_reg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextCode = view.findViewById(R.id.editTextCode);
        final EditText editTextName = view.findViewById(R.id.editTextName);
        final EditText editTextDescription = view.findViewById(R.id.editTextDescription);
        final EditText editTextPrice = view.findViewById(R.id.editTextPrice);

        Button itemRegBtn = view.findViewById(R.id.itemRegBtn);
        itemRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MerchandiseForm merchandiseForm = new MerchandiseForm();
                merchandiseForm.setCode(editTextCode.getText().toString());
                merchandiseForm.setName(editTextName.getText().toString());
                merchandiseForm.setDescription(editTextDescription.getText().toString());
                merchandiseForm.setPrice(Double.valueOf(editTextPrice.getText().toString()));
                apiConnection.createItems(merchandiseForm);

                editTextCode.setText("");
                editTextDescription.setText("");
                editTextName.setText("");
                //editTextPrice.setText("");
            }
        });
    }

    @Override
    public void postResult(HttpStatus response, String description) {
        if (response != null){

            if (response == HttpStatus.OK){

                Toast.makeText(getContext(), "Item Added!", Toast.LENGTH_LONG).show();

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
