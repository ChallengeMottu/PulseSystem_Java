package pulse.com.br.pulse.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pulse.com.br.pulse.application.services.interfaces.EmployeeService;
import pulse.com.br.pulse.application.services.interfaces.ParkingService;
import pulse.com.br.pulse.domainmodel.entities.Employee;

@Controller
public class AuthController {

    private final EmployeeService employeeService;
    private final ParkingService parkingService;

    public AuthController(EmployeeService employeeService, ParkingService parkingService) {
        this.employeeService = employeeService;
        this.parkingService = parkingService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        String email = authentication.getName();

        Employee employee = employeeService.findByEmail(email);

        String nome = employee.getName();
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("");

        model.addAttribute("nomeUsuario", nome);
        model.addAttribute("role", role);

        if (employee.getParkingId() != null) {
            try {
                String floorPlan = parkingService.findFloorPlanById(employee.getParkingId());
                model.addAttribute("floorPlan", floorPlan);
                System.out.println("SVG retornado: " + floorPlan);

            } catch (Exception e) {
                model.addAttribute("error", "Não foi possível carregar a estrutura do pátio: " + e.getMessage());
            }
        } else {
            model.addAttribute("error", "Usuário não possui pátio vinculado.");
        }

        return "home";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logoutSuccess";
    }
}
