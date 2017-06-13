package in.com.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import in.com.util.NumberUtils;

public class StockReport {
	
	private LocalDate settlementDate;
	private BigDecimal tradeAmount;
	
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}
	
	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	@Override
	public String toString() {
		return "StockReport [Settlement Date = " + settlementDate + ", Trade Amount = " + NumberUtils.format(tradeAmount) + "]";
	}
	
	

}
