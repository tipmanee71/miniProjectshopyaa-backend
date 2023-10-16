package com.example.demo.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer pdId;
	private String pdName;
	private Integer pdPrice;
	private String pdDescription;
//	private String pdImage;
	private String pdManufacturer;
	private Date pdMfg; // แสดงฟิลด์วันที่และเวลา pd_mfg
	private Date pdExp; // แสดงฟิลด์วันที่และเวลา pd_exp
	
	@Lob
	@Column(length = 3048576)
	private byte[] pdImage;
	
	@OneToMany
	@JoinColumn(name = " cartsId")
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Product() {
		super();
	}
	public Product(Integer id, Integer pdId, String pdName, Integer pdPrice, String pdDescription,
			String pdManufacturer, Date pdMfg, Date pdExp, byte[] pdImage) {
		super();
		this.id = id;
		this.pdId = pdId;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.pdDescription = pdDescription;
		this.pdManufacturer = pdManufacturer;
		this.pdMfg = pdMfg;
		this.pdExp = pdExp;
		this.pdImage = pdImage;
	}
	public Integer getPdId() {
		return pdId;
	}
	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public Integer getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(Integer pdPrice) {
		this.pdPrice = pdPrice;
	}
	public String getPdDescription() {
		return pdDescription;
	}
	public void setPdDescription(String pdDescription) {
		this.pdDescription = pdDescription;
	}
	public String getPdManufacturer() {
		return pdManufacturer;
	}
	public void setPdManufacturer(String pdManufacturer) {
		this.pdManufacturer = pdManufacturer;
	}
	public Date getPdMfg() {
		return pdMfg;
	}
	public void setPdMfg(Date pdMfg) {
		this.pdMfg = pdMfg;
	}
	public Date getPdExp() {
		return pdExp;
	}
	public void setPdExp(Date pdExp) {
		this.pdExp = pdExp;
	}
	public byte[] getPdImage() {
		return pdImage;
	}
	public void setPdImage(byte[] pdImage) {
		this.pdImage = pdImage;
	}
	

}
