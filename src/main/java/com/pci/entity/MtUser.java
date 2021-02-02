package com.pci.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MT_USER database table.
 * 
 */
@Entity
@Table(name="MT_USER")
@NamedQuery(name="MtUser.findAll", query="SELECT m FROM MtUser m")
public class MtUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_CODE")
	private String userCode;

	private Boolean enabled;

	private String password;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to MtRole
	@ManyToOne
	@JoinColumn(name="ROLE_CODE")
	private MtRole mtRole;

	//bi-directional many-to-one association to TrSalesOutline
	@OneToMany(mappedBy="mtUser")
	private List<TrSalesOutline> trSalesOutlines;

	public MtUser() {
	}

	public MtUser(String userCode, String userName, String password, MtRole mtRole, Boolean enabled) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.password = password;
		this.mtRole = mtRole;
		this.enabled = enabled;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MtRole getMtRole() {
		return this.mtRole;
	}

	public void setMtRole(MtRole mtRole) {
		this.mtRole = mtRole;
	}

	public List<TrSalesOutline> getTrSalesOutlines() {
		return this.trSalesOutlines;
	}

	public void setTrSalesOutlines(List<TrSalesOutline> trSalesOutlines) {
		this.trSalesOutlines = trSalesOutlines;
	}

	public TrSalesOutline addTrSalesOutline(TrSalesOutline trSalesOutline) {
		getTrSalesOutlines().add(trSalesOutline);
		trSalesOutline.setMtUser(this);

		return trSalesOutline;
	}

	public TrSalesOutline removeTrSalesOutline(TrSalesOutline trSalesOutline) {
		getTrSalesOutlines().remove(trSalesOutline);
		trSalesOutline.setMtUser(null);

		return trSalesOutline;
	}

}