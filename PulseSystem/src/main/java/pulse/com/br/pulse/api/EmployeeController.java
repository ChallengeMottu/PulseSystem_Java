package pulse.com.br.pulse.api;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pulse.com.br.pulse.application.dtos.EmployeeRequestDto;
import pulse.com.br.pulse.application.services.interfaces.EmployeeService;
import pulse.com.br.pulse.domainmodel.entities.Employee;
import pulse.com.br.pulse.domainmodel.enums.UserRole;
import pulse.com.br.pulse.infraestructure.configurations.Mapper;

import java.util.List;

@Controller
@RequestMapping("/users")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }


    @PreAuthorize("hasRole('GESTOR')")
    @GetMapping("/register")
    public String showRegisterForm(Model model, Authentication authentication) {
        EmployeeRequestDto dto = new EmployeeRequestDto();
        String emailGestor = authentication.getName();
        dto.setParkingId(employeeService.findByEmail(emailGestor).getParkingId());
        model.addAttribute("user", dto);
        return "registerEmployees";
    }


    @PreAuthorize("hasRole('GESTOR')")
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") EmployeeRequestDto dto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "registerEmployees";
        }

        try {
            Employee employee = Mapper.toEntity(dto, Employee.class);
            employeeService.save(employee);

            model.addAttribute("success", "Usuário cadastrado com sucesso!");
            model.addAttribute("user", new EmployeeRequestDto());
            return "redirect:/users/list";

        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("user", dto);
            return "registerEmployees";
        }
    }



    @PreAuthorize("hasRole('GESTOR')")
    @GetMapping("/list")
    public String findAllByParking(Model model, Authentication authentication) {
        String email = authentication.getName();
        Employee employee = employeeService.findByEmail(email);

        List<Employee> employees = employeeService.findByParkingId(employee.getParkingId());


        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getRole() != UserRole.GESTOR)
                .toList();

        model.addAttribute("employees", filteredEmployees);
        return "listEmployees";
    }



    @PreAuthorize("hasRole('GESTOR')")
    @GetMapping("/cpf")
    public String findByCpf(Model model, String cpf) {
        Employee employee = employeeService.findByCpf(cpf);
        model.addAttribute("employee", employee);
        return "listEmployees";
    }


    @PreAuthorize("hasRole('GESTOR')")
    @GetMapping("/update/{cpf}")
    public String showUpdateForm(@PathVariable String cpf, Model model) {
        Employee employee = employeeService.findByCpf(cpf);
        EmployeeRequestDto dto = Mapper.toDTO(employee, EmployeeRequestDto.class);
        model.addAttribute("user", dto);
        return "formUpdateUser";
    }


    @PreAuthorize("hasRole('GESTOR')")
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") EmployeeRequestDto dto, Model model) {
        Employee user = Mapper.toEntity(dto, Employee.class);
        employeeService.updateUser(user);

        model.addAttribute("success", "Usuário atualizado com sucesso!");
        return "redirect:/users/list";
    }

    @PreAuthorize("hasRole('GESTOR')")
    @PostMapping("/delete")
    public String deleteUser(Model model, String cpf, Authentication authentication) {
        employeeService.deleteUserByCpf(cpf);

        String email = authentication.getName();
        Employee employee = employeeService.findByEmail(email);
        List<Employee> employees = employeeService.findByParkingId(employee.getParkingId());
        model.addAttribute("employees", employees);
        model.addAttribute("success", "Usuário excluído com sucesso!");
        return "listEmployees";
    }



}
