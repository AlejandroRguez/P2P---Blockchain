package com.tfg.entities;

import java.io.Serializable;

/**
 * Interfaz que implementarán las clases User y Owner
 *
 */
public interface Account extends Serializable {
	
	/**
	 * @return El importe asociado a una cuenta
	 */
	public double getAmount();
	/**
	 * Modifica el importe asociado a una cuenta
	 * @param amount El nuevo importe
	 */
	public void setAmount(double amount);
	/**
	 * @return La dirección asociada a una cuenta
	 */
	public String getAddress();
	/**
	 * @return El nik del propietario de la cuenta
	 */
	public String getNickname();

}
