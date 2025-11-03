package pulse.com.br.pulse.application.services.implementations;

import org.springframework.stereotype.Service;
import pulse.com.br.pulse.application.exceptions.ResourceNotFoundException;
import pulse.com.br.pulse.application.services.interfaces.ParkingService;
import pulse.com.br.pulse.infraestructure.repositories.ParkingRepository;


@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingServiceImpl(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }


    @Override
    public String findFloorPlanById(Long id) {
        String result = parkingRepository.findFloorPlanById(id);
        if (result == null) {
            throw new ResourceNotFoundException("Estrutura total da planta baixa do pátio não encontrada");
        }
        return result;
    }

    @Override
    public String findStructurePlanById(Long id) {
        String result = parkingRepository.findStructurePlanById(id);
        if (result == null) {
            throw new ResourceNotFoundException("Estrutura da planta baixa do pátio não encontrada");
        }
        return result;
    }

    @Override
    public String findMapPlanById(Long id) {
        String result = parkingRepository.findMapPlanById(id);
        if (result == null) {
            throw new ResourceNotFoundException("Mapa atualizado do pátio não encontrado");
        }
        return result;
    }
}
