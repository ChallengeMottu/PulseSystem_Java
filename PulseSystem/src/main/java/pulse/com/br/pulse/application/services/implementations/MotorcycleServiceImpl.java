package pulse.com.br.pulse.application.services.implementations;


import org.springframework.stereotype.Service;
import pulse.com.br.pulse.application.exceptions.ObjectAlreadyExistsException;
import pulse.com.br.pulse.application.exceptions.ResourceNotFoundException;
import pulse.com.br.pulse.application.services.interfaces.MotorcycleService;
import pulse.com.br.pulse.domainmodel.entities.Motorcycle;
import pulse.com.br.pulse.domainmodel.entities.Parking;
import pulse.com.br.pulse.domainmodel.enums.OperationStatus;
import pulse.com.br.pulse.infraestructure.repositories.MotorcycleRepository;
import pulse.com.br.pulse.infraestructure.repositories.ParkingRepository;

import java.util.List;

@Service
public class MotorcycleServiceImpl implements MotorcycleService {

    private final MotorcycleRepository repository;
    private final ParkingRepository parkingRepository;

    public MotorcycleServiceImpl(MotorcycleRepository repository, ParkingRepository parkingRepository) {
        this.repository = repository;
        this.parkingRepository = parkingRepository;
    }



    @Override
    public Motorcycle save(Motorcycle motorcycle) {
        motorcycle.validate();


        if (repository.findByLicensePlate(motorcycle.getLicensePlate()) != null) {
            throw new ObjectAlreadyExistsException(
                    "Moto com placa: " + motorcycle.getLicensePlate() + " já possui cadastro"
            );
        }


        Parking parking = parkingRepository.findById(motorcycle.getParking().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Pátio não encontrado."));


        long totalMotos = repository.countByParking_Id(motorcycle.getParking().getId());
        if (totalMotos >= parking.getCapacity()) {
            throw new IllegalStateException("Capacidade máxima do pátio atingida.");
        }


        return repository.save(motorcycle);
    }



    @Override
    public Motorcycle findById(Long motorcycleId) {
        return repository.findById(motorcycleId).orElseThrow(()-> new ResourceNotFoundException("Moto não encontrada"));
    }

    @Override
    public Motorcycle findByLicensePlate(String licensePlate) {
        Motorcycle motorcycle = repository.findByLicensePlate(licensePlate);
        if (motorcycle == null){
            throw new ResourceNotFoundException("Moto de placa " + licensePlate + " não encontrada");
        }
        return motorcycle;
    }



    @Override
    public List<Motorcycle> findAllByParking(Long parkingId) {
        List<Motorcycle> listMotos = repository.findByParking_Id(parkingId);
        if (listMotos == null){
            throw new ResourceNotFoundException("Pátio não encontrado.");
        }
        return listMotos;
    }

    @Override
    public void delete(Long motorcycleId) {
        Motorcycle motorcycle = repository.findById(motorcycleId).orElseThrow(()->new ResourceNotFoundException("Moto não encontrada") );
        repository.delete(motorcycle);

    }

    @Override
    public List<Motorcycle> listAllByStatusAndParking(OperationStatus status, Long parkingId) {
        return repository.findByOperationalStatusAndParking_Id(status, parkingId);
    }



    @Override
    public void update(Long motorcycleId, Motorcycle moto) {
        Motorcycle motorcycle = repository.findById(motorcycleId)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));


        Motorcycle existing = repository.findByLicensePlate(moto.getLicensePlate());
        if (existing != null && !existing.getId().equals(motorcycleId)) {
            throw new ObjectAlreadyExistsException(
                    "Moto com placa: " + moto.getLicensePlate() + " já possui cadastro"
            );
        }

        motorcycle.setLicensePlate(moto.getLicensePlate());
        motorcycle.setModel(moto.getModel());
        motorcycle.setChassisNumber(moto.getChassisNumber());
        motorcycle.setMechanicalCondition(moto.getMechanicalCondition());
        motorcycle.setOperationalStatus(moto.getOperationalStatus());

        motorcycle.validate();

        if (!parkingRepository.existsById(motorcycle.getParking().getId())) {
            throw new ResourceNotFoundException("Pátio não encontrado.");
        }

        repository.save(motorcycle);
    }


    public boolean belongsToEmployeeParking(Long motorcycleId, Long parkingId) {
        Motorcycle moto = findById(motorcycleId);
        return moto != null && moto.getParking().getId().equals(parkingId);
    }

    public void updateStatus(Long id, OperationStatus newStatus) {
        Motorcycle moto = findById(id);
        if (moto == null) {
            throw new ResourceNotFoundException("Moto não encontrada com id " + id);
        }
        moto.setOperationalStatus(newStatus);
        update(moto.getId(), moto);
    }




}
