package com.tfg.ui.actions;

import org.json.JSONObject;
import org.springframework.messaging.simp.stomp.StompSession;

import com.tfg.utils.InitializeConnection;
import com.tfg.utils.alb.console.Console;
import com.tfg.utils.alb.menu.Action;

public class BuyTokensAction implements Action{

	@Override
	public void execute() throws Exception {
		
		StompSession session = InitializeConnection.initialize();
		String nickname, seller;
		double amount;
		do {
			 Thread.sleep(500);
			 nickname = Console.readString("Nickname: ");
			 seller = Console.readString("Seller: ");
			 amount = Console.readDouble("Amount: ");
		}while(!check(nickname, seller, amount));

		JSONObject json = new JSONObject();
		json.put("nickname", nickname);
		json.put("seller", seller);
		json.put("amount", amount);
		
		session.send("/app/chain.buyAccount", json.toMap());	
	}
	
	private boolean check(String nickname, String seller, double amount) {
		if(nickname.equals("") || seller.equals("") || amount < 0) {
			Console.println("Invalid data. Please try again...");
			return false;
		}
		return true;	
	}

}
