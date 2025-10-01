package pulse.com.br.pulse.application.services.interfaces;


import org.springframework.stereotype.Service;
import pulse.com.br.pulse.domainmodel.entities.Beacon;
import pulse.com.br.pulse.domainmodel.enums.StatusBeacon;

import java.util.List;

@Service
public interface BeaconService {
    Beacon findById(Long id);
    List<Beacon> findAll();
    Beacon save(Beacon beacon);
    void delete(Long id);
    Beacon update(Long id, Beacon beacon);
    List<Beacon> findByStatus(StatusBeacon status);
    Beacon updateStatus(Long id, StatusBeacon novoStatus);



}
