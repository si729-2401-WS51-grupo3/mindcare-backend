package pe.edu.upc.mind.mind_care_platform.shared.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
//porque el Created y Updated lo tienen la
//mayoria de clases que vamos a crear
@MappedSuperclass
@Data
//clase generica para llamarla en cada clase que estamos
// creando para que de forma automatica Spring actualice esta info
public class AuditableModel {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}