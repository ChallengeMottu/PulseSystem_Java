package pulse.com.br.pulse.application.services.implementations;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pulse.com.br.pulse.application.exceptions.ObjectAlreadyExistsException;
import pulse.com.br.pulse.application.exceptions.ResourceNotFoundException;
import pulse.com.br.pulse.application.services.interfaces.BeaconService;
import pulse.com.br.pulse.domainmodel.entities.Beacon;
import pulse.com.br.pulse.domainmodel.enums.StatusBeacon;
import pulse.com.br.pulse.infraestructure.repositories.BeaconRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BeaconServiceImpl implements BeaconService {

    private final BeaconRepository repository;

    public BeaconServiceImpl(BeaconRepository repository) {
        this.repository = repository;
    }


    @Override
    public Beacon findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Beacon inexistente"));
    }

    @Override
    public List<Beacon> findAll() {
        List<Beacon> beacons = repository.findAll();
        if (beacons.isEmpty()){
            throw new ResourceNotFoundException("Nenhum beacon encontrado");
        }
        return beacons;
    }

    @Override
    public List<Beacon> findByStatus(StatusBeacon status) {
        List<Beacon> beacons = repository.findByBeaconStatus(status);
        if (beacons.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum beacon com status " + status + " encontrado");
        }
        return beacons;
    }


    @Override
    public Beacon save(Beacon beacon) {
        beacon.validated();
        if (repository.findByBeaconCode(beacon.getBeaconCode()) != null){
            throw new ObjectAlreadyExistsException("Beacon com código UUID " + beacon.getBeaconCode() + " já existe");
        }

        return repository.save(beacon);
    }

    @Override
    public void delete(Long id) {
        Beacon beacon = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Beacon inexistente"));
        repository.delete(beacon);

    }

    @Override
    public Beacon update(Long id, Beacon beaconAtualizado) {
        Beacon beaconExistente = findById(id);


        beaconExistente.setBeaconCode(beaconAtualizado.getBeaconCode());
        beaconExistente.setBeaconStatus(beaconAtualizado.getBeaconStatus());
        beaconExistente.setMotorcycle(beaconAtualizado.getMotorcycle());

        return repository.save(beaconExistente);
    }

    @Override
    public Beacon updateStatus(Long id, StatusBeacon novoStatus) {
        Beacon beacon = findById(id);
        beacon.setBeaconStatus(novoStatus);
        return repository.save(beacon);
    }

}
