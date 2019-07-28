package com.midterm.lasalle.shoppify.form.customer;

import com.midterm.lasalle.shoppify.form.api.ApiJsonForm;
import org.json.JSONObject;

public class AssignCustomerForm implements ApiJsonForm {

    private String email;
    private String code;

    public AssignCustomerForm() {
    }

    public AssignCustomerForm(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public JSONObject getJsonData() {

        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("email", this.email);
            jsonObject.put("code", this.code);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return jsonObject;
    }

    @Override
    public String getURL() {
        return "http://10.0.2.2:9090/assign/items";
    }

    @Override
    public String getDescriptions() {
        return "Assigned Successfully!";
    }
}
