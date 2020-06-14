package com.parsermoex.repositories;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.parsermoex.entities.Emitent;
import com.parsermoex.entities.Trade;
import com.parsermoex.entities.TradeString;

@Repository
@Transactional
public class TradeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Trade obj) {
		entityManager.createNativeQuery("INSERT INTO Trade(sec_id, trade_date, num_trades, open_price, close_price) VALUES(?1,?2,?3,?4,?5)")
				.setParameter(1, obj.getSecId())
				.setParameter(2, obj.getTradeDate())
				.setParameter(3, obj.getNumTrades())
				.setParameter(4, obj.getOpen())
				.setParameter(5, obj.getClose())
				.executeUpdate();
	}
	
	public List<TradeString> getHistory() {
		String q = "SELECT e.sec_id, e.reg_number, e.sec_name, e.emitent_title, t.trade_id, t.trade_date, t.open_price, t.close_price, t.num_trades FROM Trade t, Emitent e WHERE e.sec_id = t.sec_id";
		Query query = entityManager.createNativeQuery(q, TradeString.class);
		return query.getResultList();
	}

	public void updateTrade(String tradeDate, Double openPrice, Double closePrice, Double numTrades, long id) {
		String q = "UPDATE Trade t SET t.trade_date = ?1, t.open_price = ?2, t.close_price = ?3, t.num_trades = ?4 WHERE t.trade_id = ?5";
		entityManager.createNativeQuery(q)
				.setParameter(1, tradeDate)
				.setParameter(2, openPrice)
				.setParameter(3, closePrice)
				.setParameter(4, numTrades)
				.setParameter(5, id)
				.executeUpdate();
	}
	
	public void updateEmitent(String secName, String emitentTitle, String regNumber, String secId) {
		String q = "UPDATE Emitent e SET e.sec_name = ?1, e.emitent_title = ?2, e.reg_number = ?3 WHERE e.sec_id = ?4";
		entityManager.createNativeQuery(q)
				.setParameter(1, secName)
				.setParameter(2, emitentTitle)
				.setParameter(3, regNumber)
				.setParameter(4, secId)
				.executeUpdate();
	}

	public void removeTrade(long id) {
		String q = "DELETE FROM Trade WHERE trade_id=?1";
		entityManager.createNativeQuery(q)
				.setParameter(1, id)
				.executeUpdate();		
	}

	public List<Emitent> getMissingEmitent() {
		String q = "SELECT em.* FROM Emitent em EXCEPT SELECT e.* FROM Emitent e, Trade t WHERE t.sec_id = e.sec_id";
		Query query = entityManager.createNativeQuery(q, Emitent.class);
		List<Emitent> diff = query.getResultList();
		return diff;
	}
}
