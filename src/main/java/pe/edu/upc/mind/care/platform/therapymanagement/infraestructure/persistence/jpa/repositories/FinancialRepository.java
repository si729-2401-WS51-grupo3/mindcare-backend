package pe.edu.upc.mind.care.platform.therapymanagement.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.care.platform.therapymanagement.domain.model.entities.Transaction;

import java.util.List;
import java.util.Map;


@Repository
public interface FinancialRepository extends JpaRepository<Financial, Long> {
    List<Financial> findByPatientId(Long patientId);
    @Query("SELECT t FROM Transaction t WHERE t.financial.patientId = :patientId")
    List<Transaction> findAllTransactionsByPatientId(@Param("patientId") Long patientId);
//cambie el financial.id por financial.financialId
    @Query("SELECT t FROM Transaction t WHERE t.financial.financial_id = :financialId")
    List<Transaction> findAllTransactionsByFinancialId(@Param("financialId") Long financialId);
}