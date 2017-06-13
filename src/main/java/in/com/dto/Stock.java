package in.com.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Stock implements Serializable {
	
	private static final long serialVersionUID = -2431388199634337318L;
	
	private Long id;
	private Entity entity;
	private Operation operation;
	private BigDecimal agreedExchange;
	private Currency currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private Long units;
	private BigDecimal pricePerUnit;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public BigDecimal getAgreedExchange() {
		return agreedExchange;
	}
	public void setAgreedExchange(BigDecimal agreedExchange) {
		this.agreedExchange = agreedExchange;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	public Long getUnits() {
		return units;
	}
	public void setUnits(Long units) {
		this.units = units;
	}
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	public BigDecimal tradeAmount() {
		return pricePerUnit.multiply(BigDecimal.valueOf(units)).multiply(agreedExchange);
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", entity=" + entity + ", operation=" + operation + ", agreedExchange="
				+ agreedExchange + ", currency=" + currency + ", instructionDate=" + instructionDate
				+ ", settlementDate=" + settlementDate + ", units=" + units + ", pricePerUnit=" + pricePerUnit + "]";
	}
	
	
}