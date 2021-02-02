package com.pci.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MT_ROLE database table.
 * 
 */
@Entity
@Table(name="MT_ROLE")
@NamedQuery(name="MtRole.findAll", query="SELECT m FROM MtRole m")
public class MtRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_CODE")
	private String roleCode;

	@Column(name="EXPLAIN")
	private String explain;

	@Column(name="ROLE_NAME")
	private String roleName;

	//bi-directional many-to-one association to MtUser
	@OneToMany(mappedBy="mtRole")
	private List<MtUser> mtUsers;

	public MtRole() {
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getExplain() {
		return this.explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<MtUser> getMtUsers() {
		return this.mtUsers;
	}

	public void setMtUsers(List<MtUser> mtUsers) {
		this.mtUsers = mtUsers;
	}

	public MtUser addMtUser(MtUser mtUser) {
		getMtUsers().add(mtUser);
		mtUser.setMtRole(this);

		return mtUser;
	}

	public MtUser removeMtUser(MtUser mtUser) {
		getMtUsers().remove(mtUser);
		mtUser.setMtRole(null);

		return mtUser;
	}

}