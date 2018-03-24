package com.tfg.ui.actions;

import org.json.JSONObject;
import org.springframework.messaging.simp.stomp.StompSession;

import com.tfg.utils.InitializeConnection;
import com.tfg.utils.alb.console.Console;
import com.tfg.utils.alb.menu.Action;

public class CreateNewAccountAction implements Action{

	@Override
	public void execute() throws Exception {
		
		StompSession session = InitializeConnection.initialize();
		String nickname;
		double amount;
		
		do {
			Thread.sleep(500);			
			nickname = Console.readString("Nickname: ");
			amount = Console.readDouble("Amount: ");
		
		} while(!check(nickname, amount));

		JSONObject json = new JSONObject();
		json.put("nickname", nickname);
		json.put("amount", amount);
		
		session.send("/app/chain.newAccount", json.toMap());	
	}
	
	private boolean check(String nickname, double amount) {
		if(nickname.equals("") || amount < 0) {
			Console.println("Invalid data. Please try again...");
			return false;
		}
		return true;	
	}


}
