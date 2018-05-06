package com.tfg.ui.actions;

import org.json.JSONObject;
import org.springframework.messaging.simp.stomp.StompSession;

import com.tfg.utils.InitializeConnection;
import com.tfg.utils.console.Console;
import com.tfg.utils.menu.Action;

public class SendTransactionAction implements Action {
	@Override
	public void execute() throws Exception {

		StompSession session = InitializeConnection.initialize();
		String sender, receiver;
		Double amount, fee;
		do {
			Thread.sleep(500);
			sender = Console.readString("From");
			receiver = Console.readString("To");
			amount = Console.readDouble("Amount");
			fee = Console.readDouble("Fee");
		} while (!check(sender, receiver, amount, fee));
		JSONObject json = new JSONObject();
		json.put("sender", sender);
		json.put("receiver", receiver);
		json.put("amount", amount);
		json.put("fee", fee);

		session.send("/app/chain.sendTransaction", json.toMap());
	}

	private boolean check(String sender, String receiver, Double amount, Double fee) {

		if (sender.equals("")) {
			Console.println("Invalid sender. Please try again...");
			return false;
		}
		if (receiver.equals("")) {
			Console.println("Invalid receiver. Please try again...");
			return false;
		}

		if (amount < 0) {
			Console.println("Amount can not be negative. Please try again...");
			return false;
		}

		if (fee < 0) {
			Console.println("Fee can not be negative.Please try again...");
			return false;
		}
		return true;
	}

}
