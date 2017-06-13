package in.com;

import java.util.List;

import in.com.dao.StockReportDAO;
import in.com.dto.Operation;
import in.com.dto.RankingReport;

public class Report {
	
	private static final StockReportDAO stockReportDAO = new StockReportDAO();
	
	public static void main(String[] args) {
		incomingAmountReport();
		outgoingAmountReport();
		rankingReport();
	}
	
	public static void incomingAmountReport() {
		System.out.println("Printing incoming amount report");
		stockReportDAO.amountReport(Operation.SELL).forEach(System.out::println);
		System.out.println("\n");
	}
	
	public static void outgoingAmountReport() {
		System.out.println("Printing outgoing amount report");
		stockReportDAO.amountReport(Operation.BUY).forEach(System.out::println);
		System.out.println("\n");
	}
	
	public static void rankingReport() {
		
		List<RankingReport> rakingOutgoing = stockReportDAO.getRankingReport(Operation.BUY);
		
		List<RankingReport> rankingIncoming = stockReportDAO.getRankingReport(Operation.SELL);
		
		System.out.println("Ranking Report for Outgoing");
		rakingOutgoing.forEach(System.out::println);
		
		System.out.println("\n");
		
		System.out.println("Ranking Report for Incoming");
		rankingIncoming.forEach(System.out::println);
	}
}