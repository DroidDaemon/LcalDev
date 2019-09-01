package com.example.droiddaemon.lcaldev.listeners;

import com.example.droiddaemon.lcaldev.model.CategoryByIdModel;

import java.util.List;

public interface CategoryByIdListener {
    void onFetchCategoryByIdeSuccess(List<CategoryByIdModel> categoryByIdModels);
    void onFetchCategoryByIdFailure(String msg);
}
