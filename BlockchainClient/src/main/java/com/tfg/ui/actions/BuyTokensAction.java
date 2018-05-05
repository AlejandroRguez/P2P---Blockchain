package com.tfg.ui.actions;

import org.json.JSONObject;
import org.springframework.messaging.simp.stomp.StompSession;

import com.tfg.utils.InitializeConnection;
import com.tfg.utils.console.Console;
import com.tfg.utils.menu.Action;

public class BuyTokensAction implements Action{

	@Override
	public void execute() throws Exception {
		
		StompSession session = InitializeConnection.initialize();
		String nickname, seller;
		double amount;
		do {
			 Thread.sleep(500);
			 nickname = Console.readString("Nickname");
			 seller = Console.readString("Seller");
			 amount = Console.readDouble("Amount");
		}while(!check(nickname, seller, amount));
		long t1 = System.currentTimeMillis();
		System.out.print(t1);
		JSONObject json = new JSONObject();
		json.put("nickname", nickname);
		json.put("seller", seller);
		json.put("amount", amount);
		
		session.send("/app/chain.buyAccount", json.toMap());	
	}
	
	private boolean check(String nickname, String seller, double amount) {
		
		if(nickname.equals("")) {
			Console.println("Invalid nikname. Please try again...");
			return false;
		}
		if (seller.equals("")) {
			Console.println("Invalid seller. Please try again...");
			return false;
		}
		
		if(amount < 2) {
			Console.println("Amount can not be negative. 2 tokens are the fee for this operation. Please try again...");
			return false;
		}
		
		return true;	
	}

}
