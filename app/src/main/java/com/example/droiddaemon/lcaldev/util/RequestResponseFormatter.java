package com.example.droiddaemon.lcaldev.util;

import com.example.droiddaemon.lcaldev.model.AllServiceModel;
import com.example.droiddaemon.lcaldev.model.AllServiceRequestModel;

import java.util.ArrayList;
import java.util.List;

public class RequestResponseFormatter {


    public static ArrayList<AllServiceModel> convertAllserviceObject(ArrayList<AllServiceRequestModel> allServiceModels) {
        ArrayList<AllServiceModel> allServiceModelList = new ArrayList<>();

        for (AllServiceRequestModel allServiceRequestModel : allServiceModels) {
            AllServiceModel allServiceModel = new AllServiceModel();
            allServiceModel.setProductId(allServiceRequestModel.getProductId());
            allServiceModel.setCategory(allServiceRequestModel.getCategory());
            allServiceModel.setCategoryId(allServiceRequestModel.getCategoryId());
            allServiceModel.setName(allServiceRequestModel.getName());
            allServiceModel.setDescription(allServiceRequestModel.getDescription());
            allServiceModel.setImageUrl("https://www.localramu.com/"+allServiceRequestModel.getImageUrl());
            allServiceModel.setEstimatedPrice(allServiceRequestModel.getEstimatedPrice());
            allServiceModelList.add(allServiceModel);
        }
        return allServiceModelList;
    }
}
