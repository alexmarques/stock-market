package in.com.dto;

import java.math.BigDecimal;

import in.com.util.NumberUtils;

public class RankingReport {
	
	private Entity entity;
	private BigDecimal tradeAmount;
	private Integer rankingPosition;
	private Operation operation;
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public Integer getRankingPosition() {
		return rankingPosition;
	}
	public void setRankingPosition(Integer rankingPosition) {
		this.rankingPosition = rankingPosition;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "RankingReport [Entity = " + entity.getName() + ", Trade Amount = " + NumberUtils.format(tradeAmount) + ", Ranking Position = "
				+ rankingPosition + ", operation = " + operation.toString() + "]";
	}
	
	
	
	
}