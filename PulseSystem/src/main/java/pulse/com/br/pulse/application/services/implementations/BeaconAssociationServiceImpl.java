package pulse.com.br.pulse.application.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pulse.com.br.pulse.application.exceptions.InvalidArgumentException;
import pulse.com.br.pulse.application.exceptions.ObjectAlreadyExistsException;
import pulse.com.br.pulse.application.services.interfaces.BeaconAssociationService;
import pulse.com.br.pulse.application.services.interfaces.MotorcycleService;
import pulse.com.br.pulse.domainmodel.entities.Beacon;
import pulse.com.br.pulse.domainmodel.entities.Motorcycle;
import pulse.com.br.pulse.application.services.interfaces.BeaconService;
import pulse.com.br.pulse.domainmodel.enums.StatusBeacon;

@Service
public class BeaconAssociationServiceImpl implements BeaconAssociationService {

    @Autowired
    private MotorcycleService motorcycleService;

    @Autowired
    private BeaconService beaconService;

    @Override
    @Transactional
    public void associarBeacon(String plate, Long beaconId) {
        Beacon beacon = beaconService.findById(beaconId);

        if (beacon.getMotorcycle() != null) {
            throw new ObjectAlreadyExistsException("Beacon já está associado a outra moto");
        }


        Motorcycle moto = motorcycleService.findByLicensePlate(plate);
        beacon.setMotorcycle(moto);


        beacon.setBeaconStatus(StatusBeacon.INATIVO);

        beaconService.update(beacon.getId(), beacon);
    }


    @Override
    @Transactional
    public void desassociarBeacon(String plate) {
        Motorcycle moto = motorcycleService.findByLicensePlate(plate);
        if (moto == null) {
            throw new InvalidArgumentException("Moto com placa " + plate + " não encontrada.");
        }

        Beacon beacon = moto.getBeacon();
        if (beacon != null) {
            moto.setBeacon(null);
            beacon.setMotorcycle(null);

            beacon.setBeaconStatus(StatusBeacon.ATIVO);
            motorcycleService.update(moto.getId(), moto);
            beaconService.update(beacon.getId(), beacon);
        }
    }
}
