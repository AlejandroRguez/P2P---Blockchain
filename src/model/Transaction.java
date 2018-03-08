package model;


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
	
	Integer getHash() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((receiver == null) ? 0 : receiver.getAddress().hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.getAddress().hashCode());
		return result;
	}

}
