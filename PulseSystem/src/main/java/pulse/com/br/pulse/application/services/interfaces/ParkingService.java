package pulse.com.br.pulse.application.services.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface ParkingService {
    String findFloorPlanById(Long id);
    String findStructurePlanById(Long id);
    String findMapPlanById(Long id);
}
