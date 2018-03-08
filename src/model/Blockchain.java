package model;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	private static Blockchain instance;
	private static Owner owner;
	private static Block current;
	private List<User> clients = new ArrayList<User>();
	
	private Blockchain () {	
		this.owner = Owner.getInstance();
		this.current = new Block(System.currentTimeMillis(), "", new ArrayList<Transaction>(), "Genesis Block", 0, null, owner);
	}
	
	public static Blockchain getInstance() {
		if (instance == null) {instance = new Blockchain(); }
		return instance;
	}
	


}
