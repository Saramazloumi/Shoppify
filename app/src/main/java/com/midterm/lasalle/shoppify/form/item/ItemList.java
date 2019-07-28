package com.midterm.lasalle.shoppify.form.item;

import com.midterm.lasalle.shoppify.form.api.ApiJsonGet;
import java.lang.reflect.Type;
import java.util.HashSet;

public class ItemList extends HashSet<MerchandiseForm> implements ApiJsonGet {

    private String id;
    private Type type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String getURL() {
        return "http://10.0.2.2:9090/items";
    }
}
