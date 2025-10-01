package pulse.com.br.pulse.domainmodel.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import pulse.com.br.pulse.application.exceptions.InvalidArgumentException;
import pulse.com.br.pulse.domainmodel.enums.UserRole;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String password;


    @Column(name = "parking_id", nullable = false)
    private Long parkingId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }


    public void validate() {
        validateCpf();
        validatePassword();
        validateAge();
    }

    private void validateCpf() {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new InvalidArgumentException("CPF deve ter 11 dígitos.");
        }
    }

    private void validatePassword() {
        if (password == null || password.length() < 6) {
            throw new InvalidArgumentException("Senha inválida, deve ter pelo menos 6 caracteres.");
        }
    }

    private void validateAge() {
        if (birthDate == null) {
            throw new InvalidArgumentException("Data de nascimento é obrigatória.");
        }

        long idade = ChronoUnit.YEARS.between(birthDate, LocalDate.now());

        if (idade < 18) {
            throw new InvalidArgumentException("Usuário deve ser maior de idade.");
        }
    }

}
