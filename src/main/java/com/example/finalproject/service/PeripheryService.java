package com.example.finalproject.service;

import com.example.finalproject.model.enums.PeripheryTypeEnum;
import com.example.finalproject.model.service.AddPeripheryServiceModel;
import com.example.finalproject.model.service.PeripheryUpdateServiceModel;
import com.example.finalproject.model.view.PeripheryViewModel;

import java.util.List;

public interface PeripheryService {

    void addPeriphery(AddPeripheryServiceModel serviceModel,String username);
    PeripheryTypeEnum[] getAllTypes();

    List<PeripheryViewModel> getAll();

    PeripheryViewModel findById(Long id);

    void deleteByUserId(Long id);

    void update(PeripheryUpdateServiceModel serviceModel);

    boolean isOwner(String userName, Long id);

    void delete(Long id);
}
