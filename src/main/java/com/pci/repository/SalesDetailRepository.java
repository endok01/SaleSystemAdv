package com.pci.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pci.entity.TrSalesDetail;

public interface SalesDetailRepository extends JpaRepository<TrSalesDetail, Long> {
	

	/**
	 * 商品別集計
	 * @return
	 */
	@Query("SELECT d.mtItem.itemCode as code, d.mtItem.itemName as name, sum(d.quantity*d.salesPrice) as total "
			+ "FROM TrSalesDetail d "
			+ "GROUP BY d.mtItem.itemCode, d.mtItem.itemName "
			+ "ORDER BY d.mtItem.itemCode")
	public List<Object[]> findBySalesSummaryByItem();
	/**
	 * 商品別集計
	 * @param userCode
	 * @return
	 */
	@Query("SELECT d.mtItem.itemCode as code, d.mtItem.itemName as name, sum(d.quantity*d.salesPrice) as total "
			+ "FROM TrSalesDetail d "
			+ "WHERE d.trSalesOutline.mtUser.userCode = :userCode "
			+ "GROUP BY d.mtItem.itemCode, d.mtItem.itemName "
			+ "ORDER BY d.mtItem.itemCode")
	public List<Object[]> findBySalesSummaryByItem(@Param("userCode") String userCode);

	/**
	 * 顧客別集計
	 * @return
	 */
	@Query("SELECT d.trSalesOutline.mtCustomer.customerCode as code, d.trSalesOutline.mtCustomer.customerName as name, sum(d.quantity*d.salesPrice) as total "
			+ "FROM TrSalesDetail d "
			+ "GROUP BY d.trSalesOutline.mtCustomer.customerCode, d.trSalesOutline.mtCustomer.customerName "
			+ "ORDER BY d.trSalesOutline.mtCustomer.customerCode")
	public List<Object[]> findBySalesSummaryByCustomer();
	/**
	 * 顧客別集計
	 * @param userCode
	 * @return
	 */
	@Query("SELECT d.trSalesOutline.mtCustomer.customerCode as code, d.trSalesOutline.mtCustomer.customerName as name, sum(d.quantity*d.salesPrice) as total "
			+ "FROM TrSalesDetail d "
			+ "WHERE d.trSalesOutline.mtUser.userCode = :userCode "
			+ "GROUP BY d.trSalesOutline.mtCustomer.customerCode, d.trSalesOutline.mtCustomer.customerName "
			+ "ORDER BY d.trSalesOutline.mtCustomer.customerCode")
	public List<Object[]> findBySalesSummaryByCustomer(@Param("userCode") String userCode);

	/**
	 * 商品区分別集計
	 * @return
	 */
	@Query("SELECT d.mtItem.mtItemGenre.itemGenreCode as code, d.mtItem.mtItemGenre.itemGenreName as name, sum(d.quantity*d.salesPrice) as total "
			+ "FROM TrSalesDetail d "
			+ "GROUP BY d.mtItem.mtItemGenre.itemGenreCode, d.mtItem.mtItemGenre.itemGenreName "
			+ "ORDER BY d.mtItem.mtItemGenre.itemGenreCode")
	public List<Object[]> findBySalesSummaryByItemGenre();

	/**
	 * 課員別集計
	 * @return
	 */
	@Query("SELECT d.trSalesOutline.mtUser.userCode as code, d.trSalesOutline.mtUser.userName as name, sum(d.quantity*d.salesPrice) as total "
			+ "FROM TrSalesDetail d "
			+ "GROUP BY d.trSalesOutline.mtUser.userCode, d.trSalesOutline.mtUser.userName "
			+ "ORDER BY d.trSalesOutline.mtUser.userCode")
	public List<Object[]> findBySalesSummaryByStaff();	

	/**
	 * 日付別集計
	 * @return
	 */
	@Query("SELECT d.trSalesOutline.saleDate as date, sum(d.quantity*d.salesPrice) as total "
			+ "FROM TrSalesDetail d "
			+ "GROUP BY d.trSalesOutline.saleDate "
			+ "ORDER BY d.trSalesOutline.saleDate DESC")
	public List<Object[]> findBySalesSummaryByDate();

	/**
	 * 日付別集計
	 * @param userCode
	 * @return
	 */
	@Query("SELECT d.trSalesOutline.saleDate as date, sum(d.quantity*d.salesPrice) as total "
			+ "FROM TrSalesDetail d "
			+ "WHERE d.trSalesOutline.mtUser.userCode = :userCode "
			+ "GROUP BY d.trSalesOutline.saleDate "
			+ "ORDER BY d.trSalesOutline.saleDate DESC")
	public List<Object[]> findBySalesSummaryByDate(@Param("userCode") String userCode);

}
