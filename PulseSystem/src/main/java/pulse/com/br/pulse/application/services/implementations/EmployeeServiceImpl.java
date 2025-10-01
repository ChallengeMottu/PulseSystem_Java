package pulse.com.br.pulse.application.services.implementations;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pulse.com.br.pulse.application.exceptions.InvalidPasswordException;
import pulse.com.br.pulse.application.exceptions.ObjectAlreadyExistsException;
import pulse.com.br.pulse.application.exceptions.ResourceNotFoundException;
import pulse.com.br.pulse.application.services.interfaces.EmployeeService;
import pulse.com.br.pulse.domainmodel.entities.Employee;
import pulse.com.br.pulse.infraestructure.repositories.EmployeeRepository;
import pulse.com.br.pulse.infraestructure.repositories.ParkingRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ParkingRepository parkingRepository;

    public EmployeeServiceImpl(EmployeeRepository repository, PasswordEncoder passwordEncoder, ParkingRepository parkingRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public Employee save(Employee user) {
        user.validate();
        if (repository.findByCpf(user.getCpf()) != null) {
            throw new ObjectAlreadyExistsException("Usuário com CPF " + user.getCpf() + " já existe.");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new InvalidPasswordException("Senha inválida, não pode ser nula ou vazia.");
        }

        if (!parkingRepository.existsById(user.getParkingId())) {
            throw new ResourceNotFoundException("Pátio não encontrado.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public Employee findByEmail(String email) {
        Employee user = repository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário com email " + email + " não encontrado.");
        }
        return user;
    }

    @Override
    public Employee findByCpf(String cpf) {
        Employee user = repository.findByCpf(cpf);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário com CPF " + cpf + " não encontrado.");
        }
        return user;
    }

    @Override
    public List<Employee> findByParkingId(Long parkingId) {
        List<Employee> users = repository.findAllByParkingId(parkingId);
        if (users == null || users.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum usuário encontrado para o pátio");
        }
        return users;
    }

    @Override
    public void deleteUserByCpf(String cpf) {
        Employee user = repository.findByCpf(cpf);
        if (user == null) {
            throw new ResourceNotFoundException("Não é possível excluir. Usuário com CPF " + cpf + " não encontrado.");
        }
        repository.delete(user);
    }

    @Override
    public void updateUser(Employee employee) {
        Employee existing = repository.findByCpf(employee.getCpf()); // ou findById(employee.getId())

        if (existing == null) {
            throw new ResourceNotFoundException("Usuário não encontrado.");
        }


        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setRole(employee.getRole());
        existing.setParkingId(employee.getParkingId());
        existing.setBirthDate(employee.getBirthDate());



        repository.save(existing);
    }

    @Override
    public String getLoggedUserName(String email) {
        Employee user = repository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário com email " + email + " não encontrado.");
        }
        return user.getName();
    }




}
