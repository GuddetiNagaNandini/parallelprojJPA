package com.cg.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	private static final long serialVersionUID = 1L;
	private String CustomerName;
	
	@Id
	private String mobileNo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="wallet_id")
	private Wallet wallet;

	@ManyToOne
	@JoinColumn(name="transaction_id")
	private Transactions transactions;
	
	public Customer() {
		super();
	}

	public Customer(String CustomerName, Wallet wallet) {
		super();
		this.CustomerName = CustomerName;
		this.wallet = wallet;
	}

	public Customer(String name, String mobileNo2, Wallet wallet2) {
		this.CustomerName=name;
		mobileNo=mobileNo2;
		wallet=wallet2;
	}
	
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String CustomerName) {
		this.CustomerName= CustomerName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Customer name=" + CustomerName + ", mobileNo=" + mobileNo
				 + wallet;
	}
	
	
}


