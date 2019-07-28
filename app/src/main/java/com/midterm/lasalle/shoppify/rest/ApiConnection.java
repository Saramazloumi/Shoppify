package com.midterm.lasalle.shoppify.rest;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import com.midterm.lasalle.shoppify.form.api.ApiJsonForm;
import com.midterm.lasalle.shoppify.form.api.ApiJsonGet;
import com.midterm.lasalle.shoppify.form.customer.AssignCustomerForm;
import com.midterm.lasalle.shoppify.form.customer.CustomerForm;
import com.midterm.lasalle.shoppify.form.customer.CustomerList;
import com.midterm.lasalle.shoppify.form.item.ItemList;
import com.midterm.lasalle.shoppify.form.item.ItemListForCustomer;
import com.midterm.lasalle.shoppify.form.item.MerchandiseForm;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

public class ApiConnection {

    private ApiCallback apiCallback;

    public ApiConnection(ApiCallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    public void createCustomer(CustomerForm customerForm){
        new PostRest<>(CustomerForm.class).execute(customerForm);
    }

    public void getAllCustomers(CustomerList customerList){
        new GetRest<>(CustomerList.class).execute(customerList);
    }

    public void assignCustomerToItems(AssignCustomerForm assignCustomerForm){
        new PostRest<>(AssignCustomerForm.class).execute(assignCustomerForm);
    }

    public void getItemsForCustomer(ItemListForCustomer itemListForCustomer){
        new GetRest<>(ItemListForCustomer.class).execute(itemListForCustomer);
    }

    public void createItems(MerchandiseForm merchandiseForm){
        new PostRest<>(MerchandiseForm.class).execute(merchandiseForm);
    }

    public void getAllItems(ItemList itemList){
        new GetRest<>(ItemList.class).execute(itemList);
    }



    @SuppressLint("StaticFieldLeak")
    public class PostRest<T extends ApiJsonForm> extends AsyncTask<T, Void, ResponseEntity<T>> {

        private JSONObject jsonObject;
        private Class<T> tClass;

        public PostRest(Class<T> tClass) {
            this.tClass = tClass;
        }

        @SafeVarargs
        @Override
        protected final ResponseEntity<T> doInBackground(T... ts) {
            this.jsonObject = ts[0].getJsonData();
            final String url = ts[0].getURL();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>(this.jsonObject.toString(), httpHeaders);
            RestTemplate restTemplate = new RestTemplate(true);
            try{
                return restTemplate.exchange(url, HttpMethod.POST, httpEntity, tClass);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ResponseEntity<T> responseEntity) {
            apiCallback.postResult(responseEntity.getStatusCode(), responseEntity.getBody().getDescriptions());
        }
    }

    @SuppressWarnings("StaticFieldLeak")
    public class GetRest<T extends ApiJsonGet> extends AsyncTask<T,Void, ResponseEntity<T>> {

        private Class<T> tClass;

        public GetRest(Class<T> tClass) {
            this.tClass = tClass;
        }

        @SafeVarargs
        @Override
        protected final ResponseEntity<T> doInBackground(T... ts) {

            final String url = ts[0].getURL();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", "application/json");

            HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
            RestTemplate restTemplate = new RestTemplate(true);

            try{
                return restTemplate.exchange(url, HttpMethod.GET, httpEntity, tClass);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ResponseEntity<T> responseEntity) {
            apiCallback.getResult(responseEntity.getStatusCode(), (Set<Object>)responseEntity.getBody());
        }
    }
}
