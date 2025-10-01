//package pulse.com.br.pulse.application.validators;
//
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import pulse.com.br.pulse.domainmodel.entities.Employee;
//
//@Component
//public class EmployeeDomainValidator implements Validator {
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Employee.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Employee employee = (Employee) target;
//
//        // Validação de idade
//        if (employee.getBirthDate() != null) {
//            long age = java.time.temporal.ChronoUnit.YEARS.between(
//                    employee.getBirthDate().toInstant()
//                            .atZone(java.time.ZoneId.systemDefault())
//                            .toLocalDate(),
//                    java.time.LocalDate.now());
//            if (age < 18) {
//                errors.rejectValue("birthDate", "employee.age.invalid", "Usuário deve ser maior de idade.");
//            }
//        }
//
//        // Validação de senha
//        if (employee.getPassword() == null || employee.getPassword().length() < 6) {
//            errors.rejectValue("password", "employee.password.invalid", "Senha deve ter pelo menos 6 caracteres.");
//        }
//
//        // Validação de CPF
//        if (employee.getCpf() == null || !employee.getCpf().matches("\\d{11}")) {
//            errors.rejectValue("cpf", "employee.cpf.invalid", "CPF deve ter 11 dígitos.");
//        }
//    }
//}
