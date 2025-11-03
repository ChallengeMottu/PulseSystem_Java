package pulse.com.br.pulse.domainmodel.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "parkings")
public class Parking {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CAPACITY")
    private Integer capacity;


    @Column(name = "FLOOR_PLAN")
    private String floorPlan;


    @Column(name = "STRUCTURE_PLAN")
    private String structurePlan;

    @Lob
    @Column(name = "MAP_PLAN")
    private String mapPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMapPlan() {
        return mapPlan;
    }

    public void setMapPlan(String mapPlan) {
        this.mapPlan = mapPlan;
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }

    public String getStructurePlan() {
        return structurePlan;
    }

    public void setStructurePlan(String structurePlan) {
        this.structurePlan = structurePlan;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
