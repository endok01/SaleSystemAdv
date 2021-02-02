package com.pci.summary;

import java.sql.Date;

/**
 * 
 * 集計結果を保持するクラス
 * 日付別集計はSaleSummaryクラスと保持するメンバ変数の数が異なるので別のクラスとして定義する
 * @author endo_k01
 *
 */
public class SalesSummaryDate {

	private Date date;	// 日付
	private Long total;	// 合計
	
	/**
	 * コンストラクタ
	 */
	public SalesSummaryDate() {
		super();
	}
	public SalesSummaryDate(Date date, Long total) {
		super();
		this.date = date;
		this.total = total;
	}
	
	public SalesSummaryDate(Object[] objects) {
		this((Date)objects[0],(Long)objects[1]);
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

}
