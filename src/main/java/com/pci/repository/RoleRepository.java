package com.pci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pci.entity.MtRole;

@Repository
public interface RoleRepository extends JpaRepository<MtRole, String> {
	public MtRole findByRoleCode(String roleCode);

}
