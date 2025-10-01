package pulse.com.br.pulse.api;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pulse.com.br.pulse.application.dtos.BeaconRequestDto;
import pulse.com.br.pulse.application.exceptions.InvalidArgumentException;
import pulse.com.br.pulse.application.exceptions.ResourceNotFoundException;
import pulse.com.br.pulse.application.services.interfaces.BeaconService;
import pulse.com.br.pulse.domainmodel.entities.Beacon;
import pulse.com.br.pulse.domainmodel.enums.StatusBeacon;
import pulse.com.br.pulse.infraestructure.configurations.Mapper;

import java.util.List;

@Controller
@RequestMapping("/beacons")
public class BeaconController {

    @Autowired
    private BeaconService beaconService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("beacon", new BeaconRequestDto());
        return "registerBeacon";
    }


    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @PostMapping("/register")
    public String save(@Valid BeaconRequestDto dto,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("beacon", dto);
            return "registerBeacon";
        }

        try {
            Beacon beacon = Mapper.toEntity(dto, Beacon.class);
            beaconService.save(beacon);

            model.addAttribute("success", "Beacon cadastrado com sucesso!");
            model.addAttribute("beacon", new BeaconRequestDto());
            return "redirect:/beacons/list";

        } catch (InvalidArgumentException e) {
            model.addAttribute("beacon", dto);
            model.addAttribute("errorMessage", e.getMessage());
            return "registerBeacon";
        }
    }




    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @GetMapping("/list")
    public String listAll(Model model) {
        try{
            model.addAttribute("beacons", beaconService.findAll());

        } catch (ResourceNotFoundException e) {
            model.addAttribute("errorList", "Não há beacons cadastrados");

        }
        return "listBeacons";

    }


    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long beaconId, Model model) {
        try {
            beaconService.delete(beaconId);
            model.addAttribute("success", "Beacon deletado com sucesso");
            return "redirect:/beacons/list";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao excluir o beacon");
            return "redirect:/beacons/list";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("id") Long id,
                               @RequestParam("beaconStatus") StatusBeacon beaconStatus,
                               Model model) {
        try {
            Beacon beacon = beaconService.findById(id);
            beacon.setBeaconStatus(beaconStatus);
            beaconService.update(id, beacon);

            model.addAttribute("success", "Status do beacon atualizado com sucesso!");
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", "Beacon não encontrado");
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao atualizar o status do beacon");
        }
        return "redirect:/beacons/list";
    }


    @PreAuthorize("hasRole('ROLE_OPERADOR')")
    @GetMapping("/active/list")
    public String listActiveBeaconsForAssociation(Model model,
                                                  @RequestParam("plate") String plate) {
        List<Beacon> activeBeacons;
        try {
            activeBeacons = beaconService.findByStatus(StatusBeacon.ATIVO);
        } catch (ResourceNotFoundException e) {
            activeBeacons = List.of();
            model.addAttribute("errorList", "Nenhum beacon ativo disponível");
        }

        model.addAttribute("activeBeacons", activeBeacons);
        model.addAttribute("plate", plate);
        return "listBeaconsAtivos";
    }







}
