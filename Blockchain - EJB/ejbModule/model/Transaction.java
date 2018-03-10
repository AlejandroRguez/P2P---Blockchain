package model;

import java.util.Comparator;

public class Transaction {
	
	Account sender;
	Account receiver;
	double amount;
	double fee;
	
	public Transaction(Account sender, Account receiver, double amount, double fee) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.fee = fee;
	}
	
	public Account getSender() {
		 return sender;
	}
	 
	public Account getReceiver() {
		 return receiver;
	 }
	 
	public double getAmount() {
		 return amount;
	 }
	 
	public double getFee() {
		 return fee;
	 }
	
	public Integer getHash() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((receiver == null) ? 0 : receiver.getAddress().hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.getAddress().hashCode());
		return result;
	}


	public static Comparator<Transaction> transactionsComparator = new Comparator<Transaction>() {

		@Override
		public int compare(Transaction o1, Transaction o2) {
			double amount1 = o1.getAmount();
			double amount2 = o2.getAmount();
			Double result = amount1 - amount2; 
			return result.intValue();
		}
		
	};
	
}
