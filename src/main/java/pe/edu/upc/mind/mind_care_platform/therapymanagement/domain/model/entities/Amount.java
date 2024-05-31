package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;

@Setter
@Getter
@Entity
@Embeddable
//bounded context de financial
public class Amount {
    @Id
    private Long id;
    private String amount;
    //Se hace una relacion de ONEtoONE
    //con Financial

    @OneToOne
    @JoinColumn(name= "financial_id")
    @NotNull
    private Financial financial;

    public Amount(Financial financial){
        this.financial= financial;
    }

    public Amount() {}
}