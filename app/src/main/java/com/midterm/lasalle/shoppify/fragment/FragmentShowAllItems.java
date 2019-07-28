package com.midterm.lasalle.shoppify.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.midterm.lasalle.shoppify.R;
import com.midterm.lasalle.shoppify.form.item.ItemList;
import com.midterm.lasalle.shoppify.form.item.MerchandiseForm;
import com.midterm.lasalle.shoppify.rest.ApiConnection;
import com.midterm.lasalle.shoppify.rest.ApiMerchandiseCallback;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Set;

public class FragmentShowAllItems extends ListFragment implements ApiMerchandiseCallback {

    private HttpStatus httpStatus;
    private Set<Object> dataSet;
    private ApiConnection apiConnection;

    ArrayList<MerchandiseForm> list;


    public FragmentShowAllItems() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiConnection = new ApiConnection(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_show_all_items, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ItemList itemList = new ItemList();
        itemList.setType(MerchandiseForm.class);
        apiConnection.getAllItems(itemList);
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
                    for (Object data:dataSet){
                        MerchandiseForm merchandiseForm = new MerchandiseForm();
                        merchandiseForm.setName(((MerchandiseForm)data).getName());
                        merchandiseForm.setCode(((MerchandiseForm)data).getCode());
                        merchandiseForm.setDescription(((MerchandiseForm)data).getDescription());
                        merchandiseForm.setPrice(((MerchandiseForm)data).getPrice());
                        list.add(merchandiseForm);
                    }
                    ArrayAdapter<MerchandiseForm> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,list);
                    setListAdapter(adapter);
                }
            }
        });
    }
}
