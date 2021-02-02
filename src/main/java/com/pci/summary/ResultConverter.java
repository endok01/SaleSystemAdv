package com.pci.summary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * 集計結果を各オブジェクトに変換するクラス
 * @author endo_k01
 *
 */
@Component
public class ResultConverter {

	/**
	 * 
	 * 集計結果リストオブジェクトとして格納する
	 * @param result
	 * @return
	 */
	public List<SalesSummary> salesSummaryResultConverter(List<Object[]> result) {
		List<SalesSummary> salesList = new ArrayList<>();
		for(Object[] objects : result) {
			salesList.add(new SalesSummary(objects));
		}
		return salesList;
	}
	
	public List<SalesSummaryDate> salesSummaryResultConverterForDate(List<Object[]> result) {
		List<SalesSummaryDate> salesList = new ArrayList<>();
		for(Object[] objects : result) {
			salesList.add(new SalesSummaryDate(objects));
		}
		return salesList;
	}

}
