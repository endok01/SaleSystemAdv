package com.pci.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the MT_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="MT_CUSTOMER")
@NamedQuery(name="MtCustomer.findAll", query="SELECT m FROM MtCustomer m")
public class MtCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_CODE")
	@Size(min=8,max=8)
	private String customerCode;

	@Column(name="CUSTOMER_NAME")
	@Size(min=1, max=20)
	private String customerName;

	//bi-directional many-to-one association to TrSalesOutline
	@OneToMany(mappedBy="mtCustomer")
	private List<TrSalesOutline> trSalesOutlines;

	/**
	 * コンストラクタ
	 * 
	 */
	public MtCustomer() {
		super();
	}
	public MtCustomer(String customerCode, String customerName) {
		super();
		this.customerCode = customerCode;
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<TrSalesOutline> getTrSalesOutlines() {
		return this.trSalesOutlines;
	}

	public void setTrSalesOutlines(List<TrSalesOutline> trSalesOutlines) {
		this.trSalesOutlines = trSalesOutlines;
	}

	public TrSalesOutline addTrSalesOutline(TrSalesOutline trSalesOutline) {
		getTrSalesOutlines().add(trSalesOutline);
		trSalesOutline.setMtCustomer(this);

		return trSalesOutline;
	}

	public TrSalesOutline removeTrSalesOutline(TrSalesOutline trSalesOutline) {
		getTrSalesOutlines().remove(trSalesOutline);
		trSalesOutline.setMtCustomer(null);

		return trSalesOutline;
	}

}