package com.example.droiddaemon.lcaldev.listeners;

import com.example.droiddaemon.lcaldev.model.AllServiceModel;
import com.example.droiddaemon.lcaldev.model.CategoryByProductIdModel;

import java.util.ArrayList;
import java.util.List;

public interface CategoryByProductIdListener {
    void onFetchCategoryByProductIdeSuccess(List<CategoryByProductIdModel> categoryByProductIdModels);
    void onFetchCategoryByProductIdFailure(String msg);
}
