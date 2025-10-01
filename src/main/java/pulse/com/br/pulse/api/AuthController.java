package pulse.com.br.pulse.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pulse.com.br.pulse.application.services.interfaces.EmployeeService;

@Controller
public class AuthController {

    private final EmployeeService employeeService;

    public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {

        String email = authentication.getName();


        String nome = employeeService.getLoggedUserName(email);


        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("");


        model.addAttribute("nomeUsuario", nome);
        model.addAttribute("role", role);

        return "home";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logoutSuccess";
    }
}
