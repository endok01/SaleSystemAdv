package com.pci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pci.entity.MtUser;
import com.pci.entity.TrSalesOutline;

@Repository
public interface SalesOutlineRepository extends JpaRepository<TrSalesOutline, Long> {
	
	/**
	 * ユーザ情報から、そのユーザの売上情報を取得する
	 * 下記SQL文のクエリを生成するメソッド名を付与
	 * SQL文：SELECT * FROM TR_SALE WHERE USER_CODE = MtUser.userCode
	 * @param mtUser
	 * @return
	 */
	List<TrSalesOutline> findByMtUser(MtUser mtUser);
}
