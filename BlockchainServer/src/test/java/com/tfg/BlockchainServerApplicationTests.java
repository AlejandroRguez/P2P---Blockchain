package com.tfg;

import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tfg.bussiness.AccountService;
import com.tfg.bussiness.BlockService;
import com.tfg.bussiness.BlockchainService;
import com.tfg.bussiness.Services;
import com.tfg.bussiness.TransactionService;
import com.tfg.entities.Blockchain;
import com.tfg.entities.User;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockchainServerApplicationTests {
	
	BlockchainService bcs = Services.getBlockchainService();
	AccountService as = Services.getAccountService();
	BlockService bs = Services.getBlockService();
	TransactionService ts = Services.getTransactionService();
	Blockchain chain = bcs.getInstance();

	@Test
	public void generalTest() {
		User a = new User(11.00, "A");
		User b = new User(11.00, "B");
		User c = new User(11.00, "C");
		User d = new User(11.00, "D");
		User e = new User(11.00, "E");
		User f = new User(11.00, "F");
								
		
		as.newAccount(a);
		as.newAccount(b);
		as.newAccount(c);
		as.newAccount(d);
		as.newAccount(e);
		
		//5 transacciones, aún no se genera nuevo bloque
		Assert.assertTrue(chain.getOwner().getAmount() == 1000.0);
		Assert.assertTrue(chain.getCurrentUsers().stream().mapToDouble(m -> m.getAmount()).sum() == 0.0);
		
		as.newAccount(f);
		//Se genera nuevo bloque y se ejecutan las 4 primeras transacciones
		chain.getCurrentUsers().stream().forEach(m -> System.out.println("User: " + m.getNickname() + "Amount: " + m.getAmount()));

		Assert.assertTrue(chain.getOwner().getAmount() == 956.0);
		
		//Las 4 transacciones más los 10 tokens de la coinbase adjudicados al minero
		Assert.assertTrue(chain.getCurrentUsers().stream().mapToDouble(m -> m.getAmount()).sum() == 54.0);
		Assert.assertTrue(chain.getNumberOfBlocks() == 2);
		Assert.assertTrue(chain.getCurrentUsers().size() == 6);
		Assert.assertTrue(chain.getNumberOfAddedTransactions() == 4);
		Assert.assertTrue(chain.getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(chain.getNumberOfTotalTransactions() == 6);
		
		//No se crean cuentas repetidas
		as.newAccount(a);
		Assert.assertTrue(chain.getCurrentUsers().size() == 6);
		Assert.assertTrue(chain.getNumberOfAddedTransactions() == 4);
		Assert.assertTrue(chain.getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(chain.getNumberOfTotalTransactions() == 6);
		
		//FindByName funciona
		Assert.assertNotNull(as.getByNickname("B"));
		Assert.assertNull(as.getByNickname("Z"));
		
		//A partir de aquí se crearán transacciones suficientes para alcanzar los 10 bloques y poder comprobar
		//que los datos de la cadena se actualizan. No se pueden comprobar amounts porque el minero es aleatorio
		//por lo que los test pasarían unas veces y otras no
		ts.newTransaction("A", "B", 1, 0.90);
		ts.newTransaction("A", "B", 1, 0.90);
		ts.newTransaction("A", "B", 1, 0.80);
		ts.newTransaction("A", "B", 1, 0.80);
		
		//chain.getCurrentUsers().stream().forEach(m -> System.out.println("User: " + m.getNickname() + "Amount: " + m.getAmount()));
		
		//Creado Bloque 3
		//Validar que solo se han añadido aquellas con mayor fee
		Assert.assertTrue(chain.getCurrentTransactions().stream().filter(t -> t.getFee() > 0.8).collect(Collectors.toList()).size() == 0);
		
		ts.newTransaction("B", "C", 1, 0.80);
		ts.newTransaction("B", "C", 1, 0.80);
		ts.newTransaction("B", "C", 1, 0.70);
		ts.newTransaction("B", "C", 1, 0.70);
		//Creado Bloque 4
		//chain.getCurrentUsers().stream().forEach(m -> System.out.println("User: " + m.getNickname() + "Amount: " + m.getAmount()));
		
		ts.newTransaction("C", "D", 1, 0.70);
		ts.newTransaction("C", "D", 1, 0.70);
		ts.newTransaction("C", "D", 1, 0.60);
		ts.newTransaction("C", "D", 1, 0.60);
		//Creado Bloque 5
		
		//Comprobación periódica de bloques y transacciones para ver que se están creando correctamente
		Assert.assertTrue(chain.getCurrentUsers().size() == 6);
		Assert.assertTrue(chain.getNumberOfAddedTransactions() == 16);
		Assert.assertTrue(chain.getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(chain.getNumberOfTotalTransactions() == 18);
		Assert.assertTrue(chain.getNumberOfBlocks() == 5);
		
		ts.newTransaction("D", "E", 1, 0.60);
		ts.newTransaction("D", "E", 1, 0.60);
		ts.newTransaction("D", "E", 1, 0.50);
		ts.newTransaction("D", "E", 1, 0.50);
		//Creado Bloque 6
		
		ts.newTransaction("E", "F", 1, 0.50);
		ts.newTransaction("E", "F", 1, 0.50);
		ts.newTransaction("E", "F", 1, 0.40);
		ts.newTransaction("E", "F", 1, 0.40);
		//Creado Bloque 7
		
		ts.newTransaction("F", "A", 1, 0.40);
		ts.newTransaction("F", "A", 1, 0.40);
		ts.newTransaction("F", "A", 1, 0.30);
		ts.newTransaction("F", "A", 1, 0.30);
		//Creado Bloque 8
		
		ts.newTransaction("A", "B", 1, 0.30);
		ts.newTransaction("A", "B", 1, 0.30);
		ts.newTransaction("A", "B", 1, 0.20);
		ts.newTransaction("A", "B", 1, 0.20);
		//Creado Bloque 9
		
		double previousPrice = chain.getCurrentPrice();
		double previousTokens = chain.getTokens();
		Assert.assertTrue(chain.getProofOfWork().equals("09"));
		
		ts.newTransaction("B", "C", 1, 0.20);
		ts.newTransaction("B", "C", 1, 0.20);
		ts.newTransaction("B", "C", 1, 0.10);
		ts.newTransaction("B", "C", 1, 0.10);
		//Creado Bloque 10
		
		Assert.assertTrue(chain.getNumberOfAddedTransactions() == 36);
		Assert.assertTrue(chain.getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(chain.getNumberOfTotalTransactions() == 38);
		Assert.assertTrue(chain.getNumberOfBlocks() == 10);
		Assert.assertTrue(chain.getProofOfWork().equals("000"));
		Assert.assertTrue(chain.getTokens() == previousTokens + 1000);
		Assert.assertTrue(chain.getCurrentPrice() != previousPrice);
		
		//No se puede chequear el amount con assertEquals ya que al generarse aleatoriamente el minero no lo conocemos
		//Pero podemos comprobar que el total de operaciones sea correcto
		
		chain.getCurrentUsers().stream().forEach(m -> System.out.println("User: " + m.getNickname() + "Amount: " + m.getAmount()));
		
		int tokensByAccount = 11;
		int accounts = 6;
		int blocks = 9;
		int coinbase = 10;
		
		int expectedTotalAmount = (tokensByAccount * accounts) + (blocks * coinbase);
		int expectedAutogeneratedTokens = 2000 - (tokensByAccount*accounts);
		
		Assert.assertTrue(chain.getTokens() == expectedAutogeneratedTokens);
		Assert.assertTrue(chain.getCurrentUsers().stream().mapToDouble(m -> m.getAmount()).sum() == expectedTotalAmount);
		
		//Invalid sender
		ts.newTransaction("J", "C", 1, 0.20);
		//Invalid recipient
		ts.newTransaction("B", "L", 1, 0.20);
		//Invalid amount
		ts.newTransaction("B", "C", -3, 0.10);
		//Invalid fee
		ts.newTransaction("B", "C", 1, -1);
		//Too much fee and/or amount
		ts.newTransaction("B", "C", 1, as.getByNickname("B").getAmount());
		ts.newTransaction("B", "C", as.getByNickname("B").getAmount(), 1);
		
		Assert.assertTrue(chain.getNumberOfAddedTransactions() == 36);
		Assert.assertTrue(chain.getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(chain.getNumberOfTotalTransactions() == 38);
		Assert.assertTrue(chain.getNumberOfBlocks() == 10);
		
		as.createBuyAccount("B", 5.0, "G");
		as.createBuyAccount("C", 5.0, "H");
		as.createBuyAccount("D", 5.0, "I");
		as.createBuyAccount("E", 5.0, "J");
		
		Assert.assertTrue(chain.getNumberOfAddedTransactions() == 40);
		Assert.assertTrue(chain.getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(chain.getNumberOfTotalTransactions() == 42);
		Assert.assertTrue(chain.getNumberOfBlocks() == 11);
		Assert.assertTrue(chain.getCurrentUsers().size() == 10);
		
		Assert.assertTrue(chain.getCurrentUsers().stream().mapToDouble(m -> m.getAmount()).sum() == expectedTotalAmount + 10);
		
		as.createBuyAccount("", 5.0, "G");
		as.createBuyAccount("C", 5.0, "");
		as.createBuyAccount("D", -5.0, "M");
		as.createBuyAccount("E", as.getByNickname("E").getAmount() + 1, "P");
		
		Assert.assertTrue(chain.getNumberOfAddedTransactions() == 40);
		Assert.assertTrue(chain.getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(chain.getNumberOfTotalTransactions() == 42);
		Assert.assertTrue(chain.getNumberOfBlocks() == 11);
		Assert.assertTrue(chain.getCurrentUsers().size() == 10);
		
		
		
		
	}

}
