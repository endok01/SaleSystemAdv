package com.pci.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pci.entity.MtCustomer;

@Repository
public interface CustomerRepository extends JpaRepository<MtCustomer, String> {
	/**
	 * 顧客コードで昇順ソートされた一覧を返却する
	 * @return
	 */
	public List<MtCustomer> findAllByOrderByCustomerCode();

}
