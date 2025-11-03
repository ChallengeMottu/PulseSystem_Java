package pulse.com.br.pulse.api;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pulse.com.br.pulse.application.dtos.MotorcycleRequest;
import pulse.com.br.pulse.application.exceptions.ResourceNotFoundException;
import pulse.com.br.pulse.application.services.interfaces.EmployeeService;
import pulse.com.br.pulse.application.services.interfaces.MotorcycleService;
import pulse.com.br.pulse.domainmodel.entities.Employee;
import pulse.com.br.pulse.domainmodel.entities.Motorcycle;
import pulse.com.br.pulse.domainmodel.entities.Parking;
import pulse.com.br.pulse.domainmodel.enums.OperationStatus;
import pulse.com.br.pulse.infraestructure.configurations.Mapper;

import java.util.List;

@Controller
@RequestMapping("/motorcycles")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @Autowired
    private EmployeeService employeeService;


    private Employee getLoggedEmployee(Authentication authentication) {
        String email = authentication.getName();
        return employeeService.findByEmail(email);
    }



    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("motorcycle", new MotorcycleRequest());
        return "registerMotorcycle";
    }


    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @PostMapping("/register")
    public String saveMotorcycle(@Valid @ModelAttribute("motorcycle") MotorcycleRequest dto,
                                 BindingResult bindingResult,
                                 Model model,
                                 Authentication authentication) {

        Employee employee = getLoggedEmployee(authentication);
        dto.setParkingId(employee.getParkingId());

        if (bindingResult.hasErrors()) {
            return "registerMotorcycle";
        }

        try {
            Motorcycle motorcycle = new Motorcycle();
            motorcycle.setLicensePlate(dto.getLicensePlate());
            motorcycle.setChassisNumber(dto.getChassisNumber());
            motorcycle.setMechanicalCondition(dto.getMechanicalCondition());
            motorcycle.setModel(dto.getModel());
            motorcycle.setOperationalStatus(dto.getOperationalStatus());

            Parking parking = new Parking();
            parking.setId(dto.getParkingId());
            motorcycle.setParking(parking);

            motorcycleService.save(motorcycle);

            model.addAttribute("success", "Moto cadastrada com sucesso!");
            return "redirect:/motorcycles/list";

        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("motorcycle", dto);
            return "registerMotorcycle";
        }
    }



    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @GetMapping("/list")
    public String listAllByParking(Model model, Authentication authentication) {
        Employee employee = getLoggedEmployee(authentication);

        List<Motorcycle> motorcycles = motorcycleService.findAllByParking(employee.getParkingId());
        model.addAttribute("motorcycles", motorcycles);

        return "listMotorcycles";
    }


    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @GetMapping("/plate")
    public String findByPlate(Model model,
                              @RequestParam("plate") String plate,
                              Authentication authentication) {
        Employee employee = getLoggedEmployee(authentication);

        try {
            Motorcycle motorcycle = motorcycleService.findByLicensePlate(plate);

            if (motorcycleService.belongsToEmployeeParking(motorcycle.getId(), employee.getParkingId())) {
                model.addAttribute("motorcycles", List.of(motorcycle));
            } else {
                model.addAttribute("motorcycles", List.of());
                model.addAttribute("error", "Nenhuma moto encontrada com essa placa.");
            }

        } catch (Exception e) {
            model.addAttribute("motorcycles", List.of());
            model.addAttribute("error", "Erro ao buscar moto: " + e.getMessage());
        }

        return "listMotorcycles";
    }


    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long motorcycleId,
                         Authentication authentication,
                         Model model) {
        Employee employee = getLoggedEmployee(authentication);

        if (motorcycleService.belongsToEmployeeParking(motorcycleId, employee.getParkingId())) {
            motorcycleService.delete(motorcycleId);
            model.addAttribute("success", "Moto deletada com sucesso!");
        } else {
            model.addAttribute("error", "Você não pode deletar essa moto.");
        }

        return "redirect:/motorcycles/list";
    }


    @PreAuthorize("hasAnyRole('ROLE_MECANICO')")
    @GetMapping("/status/{status}")
    public String findAllByOperationalStatus(@PathVariable("status") OperationStatus status,
                                             Authentication authentication,
                                             Model model) {
        Employee employee = getLoggedEmployee(authentication);

        List<Motorcycle> motorcycles = motorcycleService.listAllByStatusAndParking(status, employee.getParkingId());

        if (motorcycles.isEmpty()) {
            model.addAttribute("errorMessage", "Não há motos cadastradas com esse status");
        }

        model.addAttribute("motorcycles", motorcycles);
        model.addAttribute("status", status.name());
        return "listMotorcyclesByStatus";
    }

    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,
                                 Authentication authentication,
                                 Model model) {
        Employee employee = getLoggedEmployee(authentication);
        Motorcycle moto = motorcycleService.findById(id);

        if (!motorcycleService.belongsToEmployeeParking(moto.getId(), employee.getParkingId())) {
            model.addAttribute("error", "Você não pode atualizar essa moto.");
            return "redirect:/motorcycles/list";
        }

        MotorcycleRequest dto = Mapper.toEntity(moto, MotorcycleRequest.class);
        model.addAttribute("motorcycle", dto);

        return "formUpdateMotorcycle";
    }


    @PreAuthorize("hasAnyRole('ROLE_GESTOR', 'ROLE_OPERADOR')")
    @PostMapping("/update/{id}")
    public String updateMotorcycle(@PathVariable("id") Long id,
                                   @ModelAttribute("motorcycle") MotorcycleRequest dto,
                                   Authentication authentication,
                                   Model model) {
        Employee employee = getLoggedEmployee(authentication);

        if (!motorcycleService.belongsToEmployeeParking(id, employee.getParkingId())) {
            model.addAttribute("error", "Você não pode atualizar essa moto.");
            return "redirect:/motorcycles/list";
        }

        Motorcycle moto = Mapper.toEntity(dto, Motorcycle.class);
        moto.setId(id);
        motorcycleService.update(id, moto);

        model.addAttribute("success", "Moto atualizada com sucesso!");
        return "redirect:/motorcycles/list";
    }


    @PreAuthorize("hasAnyRole('ROLE_MECANICO')")
    @GetMapping("/listStatus")
    public String showListStatus(Model model) {
        model.addAttribute("motorcycle", new MotorcycleRequest());
        return "listMotorcyclesByStatus";
    }



    @PostMapping("/updateStatus")
    @PreAuthorize("hasRole('ROLE_MECANICO')")
    public String updateStatus(@RequestParam Long id,
                               @RequestParam OperationStatus operationalStatus,
                               Authentication authentication,
                               Model model) {
        Employee employee = getLoggedEmployee(authentication);

        if (!motorcycleService.belongsToEmployeeParking(id, employee.getParkingId())) {
            model.addAttribute("error", "Você não pode atualizar essa moto.");
            return "redirect:/motorcycles/list";
        }

        motorcycleService.updateStatus(id, operationalStatus);
        return "redirect:/motorcycles/status/" + operationalStatus.name();
    }


}