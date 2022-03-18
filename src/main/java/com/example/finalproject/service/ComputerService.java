package com.example.finalproject.service;

import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.service.ComputerServiceModel;
import com.example.finalproject.model.service.ComputerUpdateServiceModel;
import com.example.finalproject.model.view.ComputerDetailsViewModel;
import com.example.finalproject.model.view.ComputerOfferView;

import java.util.List;

public interface ComputerService {

   void addComputer(ComputerServiceModel serviceModel,String username);
   List<ComputerOfferView> getAllComputers();
   ComputerDetailsViewModel findComputerById(Long id);

   void update(ComputerUpdateServiceModel serviceModel);
   boolean isOwner(String userName, Long id);
   void deleteByUserId(Long id);
   boolean isAdmin(UserEntity user);

   void delete(Long id);
}
