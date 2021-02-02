package com.pci.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pci.entity.MtItem;

@Repository
public interface ItemRepository extends JpaRepository<MtItem, String> {
	public List<MtItem> findAllByOrderByItemCode();
}
