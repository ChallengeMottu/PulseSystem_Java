package pulse.com.br.pulse.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pulse.com.br.pulse.application.services.interfaces.BeaconAssociationService;

@Controller
@RequestMapping("/beacons")
public class AssociationController {

    @Autowired
    private BeaconAssociationService beaconAssociationService;


    @PreAuthorize("hasRole('ROLE_OPERADOR')")
    @PostMapping("/associate")
    public String associateBeacon(@RequestParam("plate") String plate,
                                  @RequestParam("beaconId") Long beaconId,
                                  Model model) {
        try {
            beaconAssociationService.associarBeacon(plate, beaconId);
            model.addAttribute("successAssociation", "Beacon associado com sucesso!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/motorcycles/list";
    }


    @PreAuthorize("hasRole('ROLE_OPERADOR')")
    @PostMapping("/dissociate")
    public String dissociateBeacon(@RequestParam("plate") String plate, Model model) {
        try {
            beaconAssociationService.desassociarBeacon(plate);
            model.addAttribute("success", "Beacon desassociado com sucesso!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/motorcycles/list";
    }
}
