package com.cg.service;


import java.math.BigDecimal;
import java.util.List;

import com.cg.dto.Customer;
import com.cg.dto.Transactions;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.InvalidInputException;



public interface WalletService {
	public Customer createAccount(String name ,String mobileno, BigDecimal amount) throws InvalidInputException;
	public Customer showBalance (String mobileno) throws InvalidInputException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	public Customer depositAmount (String mobileNo,BigDecimal amount ) throws InvalidInputException;
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	public List<Transactions> getAllTransactions(String mobileNumber);
	
public static boolean validateMobileNo(String mobileNo)throws InvalidInputException {
	return false;
}
	
	
}
