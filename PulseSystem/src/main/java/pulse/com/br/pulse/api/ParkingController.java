package pulse.com.br.pulse.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pulse.com.br.pulse.application.services.interfaces.ParkingService;

@Controller
@RequestMapping("/parkings")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR', 'MECANICO')")
    @GetMapping("/structurePlan/{id}")
    public String showStructurePlan(Model model, @PathVariable long id) {
        try {
            String structurePlan = parkingService.findStructurePlanById(id);
            if (structurePlan == null || structurePlan.trim().isEmpty()) {
                model.addAttribute("error", "Structure Plan não encontrado para o ID: " + id);
            } else {
                model.addAttribute("structurePlan", structurePlan);
                model.addAttribute("planType", "structure");
            }
            return "home";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao buscar Structure Plan: " + e.getMessage());
            return "home";
        }
    }

    @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR', 'MECANICO')")
    @GetMapping("/floorPlan/{id}")
    public String showFloorPlan(Model model, @PathVariable long id) {
        try {
            String floorPlan = parkingService.findFloorPlanById(id);
            if (floorPlan == null || floorPlan.trim().isEmpty()) {
                model.addAttribute("error", "Floor Plan não encontrado para o ID: " + id);
            } else {
                model.addAttribute("floorPlan", floorPlan);
                model.addAttribute("planType", "floor");
            }
            return "home";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao buscar Floor Plan: " + e.getMessage());
            return "home";
        }
    }

    @PreAuthorize("hasAnyRole('OPERADOR', 'GESTOR', 'MECANICO')")
    @GetMapping("/mapPlan/{id}")
    public String showMapPlan(Model model, @PathVariable long id) {
        try {
            String mapPlan = parkingService.findMapPlanById(id);

            // Debug: Verifique o que está retornando
            System.out.println("Map Plan encontrado: " + (mapPlan != null));
            if (mapPlan != null) {
                System.out.println("Tamanho do Map Plan: " + mapPlan.length());
                System.out.println("Primeiros 100 caracteres: " + mapPlan.substring(0, Math.min(100, mapPlan.length())));
            }

            if (mapPlan == null || mapPlan.trim().isEmpty()) {
                model.addAttribute("error", "Map Plan não encontrado para o ID: " + id);
            } else {
                model.addAttribute("mapPlan", mapPlan);
                model.addAttribute("planType", "map");
            }
            return "home";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao buscar Map Plan: " + e.getMessage());
            return "home";
        }
    }
}