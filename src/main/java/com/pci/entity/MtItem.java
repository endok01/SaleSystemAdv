package com.pci.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the MT_ITEM database table.
 * 
 */
@Entity
@Table(name="MT_ITEM")
@NamedQuery(name="MtItem.findAll", query="SELECT m FROM MtItem m")
public class MtItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ITEM_CODE")
	@Size(min=8, max=8)
	private String itemCode;

	@Column(name="ITEM_NAME")
	@Size(min=1, max=20)
	private String itemName;

	@Min(1)
	@Max(9999999)
	@NotNull
	private Integer price;

	@Size(max=10)
	private String spec;

	//bi-directional many-to-one association to MtItemGenre
	@ManyToOne
	@JoinColumn(name="ITEM_GENRE_CODE")
	private MtItemGenre mtItemGenre;

	//bi-directional many-to-one association to TrSalesDetail
	@OneToMany(mappedBy="mtItem")
	private List<TrSalesDetail> trSalesDetails;

	/**
	 * コンストラクタ
	 */
	public MtItem() {
		super();
	}
	public MtItem(String itemCode, String itemName, Integer price, String spec, MtItemGenre mtItemGenre) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
		this.spec = spec;
		this.mtItemGenre = mtItemGenre;
	}


	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public MtItemGenre getMtItemGenre() {
		return this.mtItemGenre;
	}

	public void setMtItemGenre(MtItemGenre mtItemGenre) {
		this.mtItemGenre = mtItemGenre;
	}

	public List<TrSalesDetail> getTrSalesDetails() {
		return this.trSalesDetails;
	}

	public void setTrSalesDetails(List<TrSalesDetail> trSalesDetails) {
		this.trSalesDetails = trSalesDetails;
	}

	public TrSalesDetail addTrSalesDetail(TrSalesDetail trSalesDetail) {
		getTrSalesDetails().add(trSalesDetail);
		trSalesDetail.setMtItem(this);

		return trSalesDetail;
	}

	public TrSalesDetail removeTrSalesDetail(TrSalesDetail trSalesDetail) {
		getTrSalesDetails().remove(trSalesDetail);
		trSalesDetail.setMtItem(null);

		return trSalesDetail;
	}

}