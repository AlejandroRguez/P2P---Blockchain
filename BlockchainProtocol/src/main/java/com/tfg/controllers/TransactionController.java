package com.tfg.controllers;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.tfg.entities.Blockchain;
import com.tfg.services.Services;

@Controller
public class TransactionController 
{
    /**
     * Recibe los datos de una transacción, la añade a la cadena
     * y devuelve una copia a todos los clientes conectados
     * @param transaction Los datos de la transacción
     * @return La copia del objeto blockchain serializado
     * @throws Exception
     */
    @MessageMapping("/chain.sendTransaction")
    @SendTo("/topic/transactions")
    public Blockchain addTransaction(Map<String, Object> transaction) throws Exception {
    	String sender = (String)transaction.get("sender");
    	String receiver = (String)transaction.get("receiver");
    	double amount = (Double)transaction.get("amount");
    	double fee = (Double)transaction.get("fee");
    	long t1 = System.currentTimeMillis();
		System.out.print(t1);
    	Services.getTransactionService().newTransaction(sender, receiver, amount, fee);
    	return Services.getBlockchainService().getInstance();
    }
    
    /**
     * Recibe los datos necesarios para crear una cuenta a partir de tokens autogenerados,
     * añade un nuevo usuario a la cadena y devuelve una copia actualizada a todos los clientes
     * @param account Los datos de la cuenta
     * @return Una copia actualizada de la cadena
     * @throws Exception
     */
    @MessageMapping("/chain.newAccount")
    @SendTo("/topic/transactions")
    public Blockchain createAccount(Map<String, Object> account) throws Exception {
    	String nickname = (String)account.get("nickname");
    	double amount = (Double)account.get("amount");
    	long t1 = System.currentTimeMillis();
		System.out.print(t1);
    	Services.getAccountService().newAccount(nickname, amount);
    	return Services.getBlockchainService().getInstance();
    }
    
    /**
     * Recibe los datos necesarios para crear una cuenta a partir de tokens de un usuario existente,
     * añade un nuevo usuario a la cadena y devuelve una copia actualizada a todos los clientes
     * @param account Los datos de la cuenta
     * @return Una copia actualizada de la cadena
     * @throws Exception
     */
    @MessageMapping("/chain.buyAccount")
    @SendTo("/topic/transactions")
    public Blockchain buyAccount(Map<String, Object> purchase) throws Exception {
    	String nik = (String)purchase.get("nickname");
    	String seller = (String)purchase.get("seller");
    	double amount = (Double)purchase.get("amount");
    	long t1 = System.currentTimeMillis();
		System.out.print(t1);
    	Services.getAccountService().createBuyAccount(seller, amount, nik);
    	return Services.getBlockchainService().getInstance();
    }
}
