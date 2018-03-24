package com.tfg;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.tfg.bussiness.Services;
import com.tfg.entities.Blockchain;
import com.tfg.entities.User;

@Controller
public class TransactionController 
{
    @MessageMapping("/chain.sendTransaction")
    @SendTo("/topic/transactions")
    public Blockchain addTransaction(Map<String, Object> transaction) throws Exception
    {
    	String sender = (String)transaction.get("sender");
    	String receiver = (String)transaction.get("receiver");
    	double amount = (Double)transaction.get("amount");
    	double fee = (Double)transaction.get("fee");
    	
    	return Services.getTransactionService().
    			newTransaction(sender, receiver, amount, fee);
    }
    
    @MessageMapping("/chain.newAccount")
    @SendTo("/topic/transactions")
    public Blockchain createAccount(Map<String, Object> account) throws Exception
    {
    	User user = new User((Double)account.get("amount"), (String)account.get("nickname"));
    	return Services.getAccountService().newAccount(user);
    }
    
//    @MessageMapping("/chain.newAccount")
//    @SendTo("/topic/transactions")
//    public JSONObject createAccount(Map<String, Object> account) throws Exception
//    {
//    	JSONObject json = new JSONObject();
//    	json.put("amount", account.get("amount"));
//    	json.put("nickname", account.get("nickname"));
//    	return json;
//    }
    
    @MessageMapping("/chain.buyAccount")
    @SendTo("/topic/transactions")
    public Blockchain buyAccount(Map<String, Object> purchase) throws Exception
    {
    	String nik = (String)purchase.get("nickname");
    	String seller = (String)purchase.get("seller");
    	double amount = (Double)purchase.get("amount");
    	
    	return Services.getAccountService().createBuyAccount(seller, amount, nik);
    }
}
