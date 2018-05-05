package com.tfg.entities;

import java.io.Serializable;
import java.util.Comparator;

public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public String sender;
	public String receiver;
	public double amount;
	public double fee;
	
	/**
	 * Constructor
	 * @param sender
	 * @param receiver
	 * @param amount
	 * @param fee
	 */
	public Transaction(String sender, String receiver, double amount, double fee) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = (double)Math.round(amount * 100d) / 100d;
		this.fee = (double)Math.round(fee * 100d) / 100d;
	}
	
	/**
	 * @return El nik del emisor de la transacción
	 */
	public String getSender() {
		 return sender;
	}
	 
	/**
	 * @return El nik del receptor de la transacción
	 */
	public String getReceiver() {
		 return receiver;
	 }
	 
	/**
	 * @return El importe de la transacción
	 */
	public double getAmount() {
		 return amount;
	 }
	 
	/**
	 * @return La comisión asociada a la transacción
	 */
	public double getFee() {
		 return fee;
	 }
	
	public Integer getHash() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (Double.doubleToLongBits(fee) != Double.doubleToLongBits(other.fee))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}




	/**
	 *  Comparador que utilizaremos a la hora de seleccionar las transacciones a añadir en un bloque
	 *  Recibe dos trasacciones y las compara por fee
	 */
	public static Comparator<Transaction> transactionsComparator = new Comparator<Transaction>() {

		@Override
		public int compare(Transaction o1, Transaction o2) {
			double amount1 = o1.getFee();
			double amount2 = o2.getFee();
			Double result = amount1 - amount2; 
			return result.intValue();
		}
		
	};
	
}
