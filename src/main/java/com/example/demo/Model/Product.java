package com.example.demo.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String pdImage;
	private String pdManufacturer;
	private Date pdMfg; // แสดงฟิลด์วันที่และเวลา pd_mfg
	private Date pdExp; // แสดงฟิลด์วันที่และเวลา pd_exp
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product() {
		super();
	}
	public Product(Integer pdId,String pdName,Integer pdPrice, String pdDescription,String pdImage ,String pdManufacturer
			,Date pdMfg, Date pdExp ) {
		super();
		this.pdId =pdId;
		this.pdPrice = pdPrice;
		this.pdName = pdName;
		   this.pdDescription = pdDescription;
	        this.pdImage = pdImage;
	        this.pdManufacturer = pdManufacturer;
	        this.pdMfg = pdMfg;
	        this.pdExp = pdExp;
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
	public String getPdImage() {
		return pdImage;
	}
	public void setPdImage(String pdImage) {
		this.pdImage = pdImage;
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
	

}
