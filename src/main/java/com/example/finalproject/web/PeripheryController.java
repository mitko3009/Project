package com.example.finalproject.web;

import com.example.finalproject.model.binding.AddPeripheryBindingModel;
import com.example.finalproject.model.binding.LaptopUpdateBindingModel;
import com.example.finalproject.model.binding.PeripheryUpdateBindingModel;
import com.example.finalproject.model.service.AddPeripheryServiceModel;
import com.example.finalproject.model.service.LaptopUpdateServiceModel;
import com.example.finalproject.model.service.PeripheryUpdateServiceModel;
import com.example.finalproject.model.view.PeripheryViewModel;
import com.example.finalproject.service.PeripheryService;
import com.example.finalproject.service.impl.ProjectUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PeripheryController {

    private ModelMapper modelMapper;
    private PeripheryService peripheryService;

    public PeripheryController(ModelMapper modelMapper, PeripheryService peripheryService) {
        this.modelMapper = modelMapper;
        this.peripheryService = peripheryService;
    }

    @GetMapping("/addPeriphery")
    public String addPeripheryPage(Model model) {
        if (!model.containsAttribute("offerAddBindModel")) {
            model.addAttribute("peripheryAddBondingModel", new AddPeripheryBindingModel());
            model.addAttribute("types", peripheryService.getAllTypes());
        }

        return "add_periphery";
    }

    @PostMapping("/addPeriphery")
    public String addPeriphery(@Valid AddPeripheryBindingModel addPeripheryBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal ProjectUser user) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPeripheryBindingModel", addPeripheryBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPeripheryBindingModel", bindingResult);


            return "redirect:/addPeriphery";
        }

        peripheryService.addPeriphery(modelMapper.map(addPeripheryBindingModel, AddPeripheryServiceModel.class), user.getUserIdentifier());

        return "redirect:/periphery/all";


    }

    @GetMapping("/periphery/all")
    public String all(Model model){
        model.addAttribute("allPeripheries",peripheryService.getAll());

        return "list-periphery-all";
    }

    @GetMapping("/periphery/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {

        PeripheryViewModel byId = peripheryService.findById(id);
        model.addAttribute("peripheryModel", modelMapper.map(byId,PeripheryUpdateBindingModel.class));
        model.addAttribute("types", peripheryService.getAllTypes());

        return "periphery-update";
    }

    @PatchMapping("/periphery/{id}/edit")
    public String editOffer(
            @PathVariable Long id,
            @Valid PeripheryUpdateBindingModel peripheryUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors() || !peripheryService.isOwner(principal.getName(), id)) {

            redirectAttributes.addFlashAttribute("offerModel", peripheryUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/periphery/all";
        }

        PeripheryUpdateServiceModel serviceModel = modelMapper.map(peripheryUpdateBindingModel, PeripheryUpdateServiceModel.class);
        serviceModel.setId(id);

        peripheryService.update(serviceModel);

        return "redirect:/periphery/all";
    }

    @PreAuthorize("isOwner(#id)")
    //@PreAuthorize("@offerServiceImpl.isOwner(#principal.name, #id)")
    @GetMapping("/periphery/{id}/delete")
    public String delete(@PathVariable Long id, Principal principal){

        if (!peripheryService.isOwner(principal.getName(), id)) {
           return "redirect:/periphery/all";
        }
        peripheryService.delete(id);

        return "redirect:/periphery/all";

    }
}
