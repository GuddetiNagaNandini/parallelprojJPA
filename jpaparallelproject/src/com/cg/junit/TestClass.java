package com.cg.junit;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.dto.Customer;
import com.cg.dto.Wallet;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.InvalidInputException;
import com.cg.service.WalletService;
import com.cg.service.WalletServiceImpl;


public class TestClass {

	static WalletService walletService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		walletService = new WalletServiceImpl();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	boolean result;

	
	@Test
	public void test_ValidateMobileNo_v1()throws InvalidInputException{
		result = WalletService.validateMobileNo("0123456789");
		Assert.assertEquals(false, result);
		
	}
	
	
	@Test
	public void test_ValidateMobileNo_v2()throws InvalidInputException{
		result = WalletService.validateMobileNo("8912434354dsf");
		Assert.assertEquals(false, result);
		
	}
	
	@Test
	public void test_ValidateMobileNo_v3()throws InvalidInputException{
		result = WalletService.validateMobileNo("8978063079");
		Assert.assertEquals(true, result);
		
	}

	@Test
	public void testCreateAccount() throws InvalidInputException {
		Customer customer = new Customer();
		Wallet wallet = new Wallet();
		String name = "nandini";
		String number = "8247766753";
		BigDecimal amount = new BigDecimal(5000);
		
		customer.setCustomerName(name);
		customer.setMobileNo(number);
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		
		customer = walletService.createAccount(name, number, amount);
		
		assertNotEquals(null, customer);
	}
	
	private void assertNotEquals(Object object, Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testshowBalance() throws InvalidInputException {
		String number = "8247766753";
		
		Customer customer = walletService.showBalance(number);
		
		assertNotEquals(null,customer);
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testWithdrawAmount() throws InvalidInputException, InsufficientBalanceException {
		String name = "nandini";
		String mobileNumber = "8247766753";
		BigDecimal balance = new BigDecimal(2000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}
	
	@Test
	public void testDepositAmount() throws InvalidInputException {
		String name = "nandini";
		String mobileNumber = "8247766753";
		BigDecimal balance = new BigDecimal(3000);
		
		Customer customer = walletService.createAccount(name, mobileNumber, balance);
		System.out.println(customer.getWallet().getBalance());
		Customer customer1 = walletService.depositAmount(mobileNumber, new BigDecimal(3000));
		System.out.println(customer1.getWallet().getBalance());
		assertEquals(6000 , customer1.getWallet().getBalance());
	}
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "nandini";
		String mobileNumber = "8247766753";
		BigDecimal balance = new BigDecimal(3000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}


}
