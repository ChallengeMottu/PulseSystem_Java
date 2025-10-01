package pulse.com.br.pulse.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.com.br.pulse.domainmodel.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
    Employee findByCpf(String cpf);
    List<Employee> findAllByParkingId(Long parkingId);
}
