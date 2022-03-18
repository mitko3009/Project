package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.LaptopEnity;
import com.example.finalproject.model.entity.PeripheryEntity;
import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.PeripheryTypeEnum;
import com.example.finalproject.model.enums.Roles;
import com.example.finalproject.model.service.AddPeripheryServiceModel;
import com.example.finalproject.model.service.PeripheryUpdateServiceModel;
import com.example.finalproject.model.view.PeripheryViewModel;
import com.example.finalproject.repository.PeripheryRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.PeripheryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PeripheryServiceImpl implements PeripheryService {

    private PeripheryRepository peripheryRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public PeripheryServiceImpl(PeripheryRepository peripheryRepository, ModelMapper modelMapper,
                                UserRepository userRepository){

        this.peripheryRepository = peripheryRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void addPeriphery(AddPeripheryServiceModel serviceModel,String username) {
        UserEntity user = userRepository.findByUsername(username).get();
        PeripheryEntity peripheryEntity = modelMapper.map(serviceModel, PeripheryEntity.class);
        peripheryEntity.setSeller(user);
        peripheryEntity.setEmail(user.getEmail());
        peripheryEntity.setSellerNum(user.getTelNumber());

        peripheryRepository.save(peripheryEntity);
    }

    @Override
    public PeripheryTypeEnum[] getAllTypes() {
        return PeripheryTypeEnum.values();
    }

    @Override
    public List<PeripheryViewModel> getAll() {
        List<PeripheryEntity> all = peripheryRepository.findAll();

        List<PeripheryViewModel> views = new ArrayList<>();

        for (PeripheryEntity model : all) {
            views.add(modelMapper.map(model,PeripheryViewModel.class));
        }

        return views;
    }

    @Override
    public PeripheryViewModel findById(Long id) {
        PeripheryEntity byId = peripheryRepository.findById(id).get();
        return modelMapper.map(byId,PeripheryViewModel.class);
    }

    @Override
    public void update(PeripheryUpdateServiceModel serviceModel) {

        PeripheryEntity entity = peripheryRepository.findById(serviceModel.getId()).get();

        entity.setMake(serviceModel.getMake());
        entity.setModel(serviceModel.getModel());
        entity.setPrice(serviceModel.getPrice());

        peripheryRepository.save(entity);
    }

    @Override
    public void deleteByUserId(Long id) {

        List<PeripheryEntity> all = peripheryRepository.findAll();
        List<PeripheryEntity> updated = new ArrayList<>();

        for (PeripheryEntity entity : all) {
            if (!Objects.equals(entity.getSeller().getId(), id)){
                updated.add(entity);
            }
        }

        peripheryRepository.deleteAll();
        peripheryRepository.saveAll(updated);


    }

    @Override
    public boolean isOwner(String userName, Long id) {
        Optional<PeripheryEntity> offerOpt = peripheryRepository.
                findById(id);
        Optional<UserEntity> caller = userRepository.
                findByUsername(userName);

        if (offerOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            PeripheryEntity offerEntity = offerOpt.get();

            return isAdmin(caller.get()) ||
                    offerEntity.getSeller().getUsername().equals(userName);
        }
    }

    private boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(RoleEntity::getRole).
                anyMatch(r -> r == Roles.ADMIN);
    }

    @Override
    public void delete(Long id) {
        peripheryRepository.deleteById(id);
    }
}
