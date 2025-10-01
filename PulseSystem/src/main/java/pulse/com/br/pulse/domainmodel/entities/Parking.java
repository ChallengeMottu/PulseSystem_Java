package pulse.com.br.pulse.domainmodel.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "parkings")
public class Parking {

    @Id
    private Long id;

    private Integer capacity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
