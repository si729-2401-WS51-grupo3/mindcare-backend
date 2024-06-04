package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.exceptions;

public class FinancialNotFoundException extends RuntimeException {
    public FinancialNotFoundException(Long aLong) {
        super("Financial transaction with id " + aLong + " not found");
    }
}