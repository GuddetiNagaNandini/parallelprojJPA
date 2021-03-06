package com.cg.ui;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.dto.Customer;
import com.cg.dto.Transactions;
import com.cg.dto.Wallet;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.InvalidInputException;
import com.cg.service.WalletService;
import com.cg.service.WalletServiceImpl;


public class Client 
{
	WalletService service;
	
	Client() {
		service = new WalletServiceImpl();
	}
	
	
	public void menu() {
		System.out.println("1) Create Account");
		System.out.println("2) Display Balance");
		System.out.println("3) Display Deposit Amount");
		System.out.println("4) Withdraw Amount");
		System.out.println("5) Funds Transfer");
		System.out.println("6) Display Transactions");
		System.out.println("0) Exit from Application");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your choice");
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1:
				Customer customer = new Customer();
				Wallet wallet = new Wallet();
				
				System.out.print("please Enter your name: ");
				String name = sc.next();
				
				System.out.print("please Enter your mobileNumber: ");
				String mobileNumber = sc.next();
				
				System.out.print("please Enter Amount: ");
				BigDecimal amount = sc.nextBigDecimal();
				
				try 
				{
					customer = service.createAccount(name, mobileNumber, amount);
					System.out.println("Your account has successfully registered");
				} 
				catch (InvalidInputException e) {
					e.printStackTrace();
				}
				break;
				
			case 2:
				System.out.println("Enter mobile number");
				mobileNumber = sc.next();
				
			try {
				customer = service.showBalance(mobileNumber);
				System.out.print("The balance in account " + customer.getCustomerName());
				System.out.println(" is " + customer.getWallet().getBalance());
			} catch (InvalidInputException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
				
				break;
			
			case 3:
				System.out.println("Enter mobile number");
				mobileNumber = sc.next();
				
				System.out.println("Enter amount to be deposited");
				amount = sc.nextBigDecimal();
				
				try 
				{
					customer = service.depositAmount(mobileNumber, amount);
					System.out.println("Successfully deposited");
					System.out.println("Account balance is: " + customer.getWallet().getBalance());
				} 
				catch (InvalidInputException e2) 
				{
					e2.printStackTrace();
				}
				
				
				
				break;
			
			case 4:
				System.out.println("Enter mobile number");
				mobileNumber = sc.next();
				
				System.out.println("Enter amount to be withdrawn");
				amount = sc.nextBigDecimal();
				
				try 
				{
					customer = service.withdrawAmount(mobileNumber, amount);
					System.out.println("Successfully withdrawn");
					System.out.println("Account balance is: " + customer.getWallet().getBalance());
				}
				catch (InvalidInputException e1) 
				{
					e1.printStackTrace();
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case 5:
				System.out.print("Enter your mobile number: ");
				String sourceMobile = sc.next();
				
				System.out.print("Enter the mobile number to perform funds transfer: ");
				String targetMobile = sc.next();
				
				System.out.println("Enter amount to be transferred");
				amount = sc.nextBigDecimal();
				
				try 
				{
					customer = service.fundTransfer(sourceMobile, targetMobile, amount);
					System.out.println("Amount has successfully transferred from account " + customer.getCustomerName());
					System.out.println("And now your balance is " + customer.getWallet().getBalance());

				} 
				catch (InvalidInputException e) {
					e.printStackTrace();
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("Enter your number");
				mobileNumber = sc.next();
				List<Transactions> transactions = null;
				
				transactions = service.getAllTransactions(mobileNumber);
				
				Iterator<Transactions> it = transactions.iterator();
				
				System.out.println("Type \t\tAmount \t\tDate");
				
				while(it.hasNext()) 
				{
					Transactions transaction = it.next();
					System.out.println(transaction.getTransactionType() + "\t" + 
					transaction.getAmount() + "\t" + 
							transaction.getDate() + "\t");
				}
				break;
			case 0:
				System.out.println("Thank you for using Electronic wallet");
				
				System.exit(0);
			
			default:
				System.out.println("Please enter valid choice");
				break;
		}
		
		
	}
	
	public static void main(String[] args) 
	{
		Client client = new Client();
		while(true) {
			client.menu();
		}
	}
	
	
}
