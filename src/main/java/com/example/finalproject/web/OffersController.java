package com.example.finalproject.web;

import com.example.finalproject.model.binding.AddComputerBindingModel;
import com.example.finalproject.model.view.ComputerOfferView;
import com.example.finalproject.model.view.LaptopOfferView;
import com.example.finalproject.model.view.PeripheryViewModel;
import com.example.finalproject.service.ComputerService;
import com.example.finalproject.service.LaptopService;
import com.example.finalproject.service.PeripheryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private ComputerService computerService;
    private LaptopService laptopService;
    private PeripheryService peripheryService;

    public OffersController(ComputerService computerService, LaptopService laptopService, PeripheryService peripheryService) {

        this.computerService = computerService;
        this.laptopService = laptopService;
        this.peripheryService = peripheryService;
    }

    @GetMapping("/all")
    public String allOffers(Model model){
        List<ComputerOfferView> allComputers = computerService.getAllComputers();
        List<LaptopOfferView> allLaptops = laptopService.getAllLaptops();
        List<PeripheryViewModel> allPeripheries = peripheryService.getAll();

        model.addAttribute("allComputers",allComputers);
        model.addAttribute("allLaptops",allLaptops);
        model.addAttribute("allPeripheries",allPeripheries);

        return "list-offers";
    }



}
