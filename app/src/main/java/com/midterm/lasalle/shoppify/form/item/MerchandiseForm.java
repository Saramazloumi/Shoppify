package com.midterm.lasalle.shoppify.form.item;

import com.midterm.lasalle.shoppify.form.api.ApiJsonForm;
import org.json.JSONObject;

import java.security.spec.ECField;

public class MerchandiseForm implements ApiJsonForm {

    private String code;
    private String name;
    private String description;
    private double price;

    public MerchandiseForm() {
    }

    public MerchandiseForm(String code, String name, String description, double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public JSONObject getJsonData() {

        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("code", this.code);
            jsonObject.put("name", this.name);
            jsonObject.put("description", this.description);
            jsonObject.put("price", this.price);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return jsonObject;
    }

    @Override
    public String getURL() {
        return "http://10.0.2.2:9090/item/create";
    }

    @Override
    public String getDescriptions() {
        return "Customer Created!";
    }



    @Override
    public String toString() {
        return "MerchandiseForm{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
