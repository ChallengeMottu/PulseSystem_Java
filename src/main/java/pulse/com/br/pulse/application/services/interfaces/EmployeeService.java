package pulse.com.br.pulse.application.services.interfaces;

import org.springframework.stereotype.Service;
import pulse.com.br.pulse.domainmodel.entities.Employee;

import java.util.List;


@Service
public interface EmployeeService {
    Employee save(Employee user);
    Employee findByEmail(String email);
    Employee findByCpf(String cpf);
    List<Employee> findByParkingId(Long parkingId);
    void deleteUserByCpf(String cpf);
    void updateUser(Employee employee);
    String getLoggedUserName(String email);


}
