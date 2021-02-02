package com.pci.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pci.entity.MtUser;

@Repository
public interface UserRepository extends JpaRepository<MtUser, String> {
	public MtUser findByUserCode(String userCode);
	
	public MtUser findByUserName(String username);
	
	@Query("select m from MtUser m where m.mtRole.roleName = 'manager'")
	public List<MtUser> getManagerList();
	
}
