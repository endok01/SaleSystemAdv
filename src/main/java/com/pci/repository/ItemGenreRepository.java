package com.pci.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pci.entity.MtItemGenre;

@Repository
public interface ItemGenreRepository extends JpaRepository<MtItemGenre, String> {
	public List<MtItemGenre> findAllByOrderByItemGenreCode();
}
