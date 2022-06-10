package com.infotech.worker;

import com.infotech.model.Account;
import java.util.*;
public class AccountHolder implements Runnable {
	private Account account;

	public AccountHolder(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of withdrawls");
		int noOfWithdrawls = sc.nextInt();
		for (int i = 1; i <= noOfWithdrawls; i++) {
			makeWithdrawal(2000);
			if (account.getBalance() < 0) {
				System.out.println("account is overdrawn!");
			}
		}
	}

	private synchronized void makeWithdrawal(int withdrawAmount) {
		if (account.getBalance() >= withdrawAmount) {
			System.out.println(Thread.currentThread().getName()	+ " is going to withdraw $"+withdrawAmount);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
			}
			account.withdraw(withdrawAmount);
			System.out.println(Thread.currentThread().getName()	+ " completes the withdrawal of $"+withdrawAmount);
		} else {
			System.out.println("Not enough in account for "	+ Thread.currentThread().getName() + " to withdraw "
					+ account.getBalance());
		}
	}
}