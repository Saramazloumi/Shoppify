package com.midterm.lasalle.shoppify.form.customer;

import com.midterm.lasalle.shoppify.form.api.ApiJsonForm;
import org.json.JSONObject;

public class CustomerForm implements ApiJsonForm {

    private String name;
    private String email;
    private String password;

    public CustomerForm() {
    }

    public CustomerForm(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public JSONObject getJsonData() {
        JSONObject jsonData = new JSONObject();

        try{
            jsonData.put("name", this.name);
            jsonData.put("email", this.email);
            jsonData.put("password", this.password);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return jsonData;
    }

    @Override
    public String getURL() {
        return "http://10.0.2.2:9090/customer/create";
    }

    @Override
    public String getDescriptions() {
        return "Customer Created!";
    }


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
