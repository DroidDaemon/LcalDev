package com.example.droiddaemon.lcaldev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryByIdModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("parentId")
    @Expose
    private Integer parentId;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("children")
    @Expose
    private List<Child> children = null;
    @SerializedName("productId")
    @Expose
    private Object productId;
    @SerializedName("settings")
    @Expose
    private Object settings;
    @SerializedName("basePrice")
    @Expose
    private Integer basePrice;
    @SerializedName("isDisplayed")
    @Expose
    private Boolean isDisplayed;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Object getProductId() {
        return productId;
    }

    public void setProductId(Object productId) {
        this.productId = productId;
    }

    public Object getSettings() {
        return settings;
    }

    public void setSettings(Object settings) {
        this.settings = settings;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Boolean getDisplayed() {
        return isDisplayed;
    }

    public void setDisplayed(Boolean displayed) {
        isDisplayed = displayed;
    }

    public class Child {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("parentId")
        @Expose
        private Integer parentId;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("children")
        @Expose
        private List<Object> children = null;
        @SerializedName("productId")
        @Expose
        private Object productId;
        @SerializedName("settings")
        @Expose
        private Object settings;
        @SerializedName("basePrice")
        @Expose
        private Integer basePrice;
        @SerializedName("isDisplayed")
        @Expose
        private Boolean isDisplayed;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<Object> getChildren() {
            return children;
        }

        public void setChildren(List<Object> children) {
            this.children = children;
        }

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }

        public Object getSettings() {
            return settings;
        }

        public void setSettings(Object settings) {
            this.settings = settings;
        }

        public Integer getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(Integer basePrice) {
            this.basePrice = basePrice;
        }

        public Boolean getIsDisplayed() {
            return isDisplayed;
        }

        public void setIsDisplayed(Boolean isDisplayed) {
            this.isDisplayed = isDisplayed;
        }

    }
}
