package com.tfg;

import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tfg.entities.Blockchain;
import com.tfg.services.AccountService;
import com.tfg.services.BlockService;
import com.tfg.services.BlockchainService;
import com.tfg.services.Services;
import com.tfg.services.TransactionService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockchainServerApplicationTests {
	
	BlockchainService bcs = Services.getBlockchainService();
	AccountService as = Services.getAccountService();
	BlockService bs = Services.getBlockService();
	TransactionService ts = Services.getTransactionService();
	
	@Test
	public void generalTest() {
		
		Blockchain.getInstance();
		
		as.newAccount("Cuenta-A", 11.00);
		as.newAccount("Cuenta-B", 11.00);
		as.newAccount("Cuenta-C", 11.00);
		as.newAccount("Cuenta-D", 11.00);
		as.newAccount("Cuenta-E", 11.00);
		
		//5 transacciones, aún no se genera nuevo bloque
		Assert.assertTrue(bcs.getInstance().getOwner().getAmount() == 1000.0);
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().stream().mapToDouble(m -> m.getAmount()).sum() == 0.0);
		
		as.newAccount("Cuenta-F", 11.00);
		//Se genera nuevo bloque y se ejecutan las 4 primeras transacciones
		

		Assert.assertTrue(bcs.getInstance().getOwner().getAmount() == 956.0);
		
		//Las 4 transacciones más los 10 tokens de la coinbase adjudicados al minero
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().stream().parallel().mapToDouble(m -> m.getAmount()).sum() == 54.0);
		Assert.assertTrue(bcs.getInstance().getNumberOfBlocks() == 2);
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().size() == 6);
		Assert.assertTrue(bcs.getInstance().getNumberOfAddedTransactions() == 4);
		Assert.assertTrue(bcs.getInstance().getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(bcs.getInstance().getNumberOfTotalTransactions() == 6);
		
		//No se crean cuentas repetidas
		as.newAccount("Cuenta-A", 11.00);
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().size() == 6);
		Assert.assertTrue(bcs.getInstance().getNumberOfAddedTransactions() == 4);
		Assert.assertTrue(bcs.getInstance().getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(bcs.getInstance().getNumberOfTotalTransactions() == 6);
		
		//FindByName funciona
		Assert.assertNotNull(as.getByNickname("Cuenta-B"));
		Assert.assertNull(as.getByNickname("Z"));
		
		//A partir de aquí se crearán transacciones suficientes para alcanzar los 10 bloques y poder comprobar
		//que los datos de la cadena se actualizan. No se pueden comprobar amounts porque el minero es aleatorio
		//por lo que los test pasarían unas veces y otras no
		ts.newTransaction("Cuenta-A", "Cuenta-B", 1, 0.90);
		ts.newTransaction("Cuenta-A", "Cuenta-B", 1, 0.90);
		ts.newTransaction("Cuenta-A", "Cuenta-B", 1, 0.80);
		ts.newTransaction("Cuenta-A", "Cuenta-B", 1, 0.80);
		
		
		//Creado Bloque 3
		//Validar que solo se han añadido aquellas con mayor fee
		Assert.assertTrue(bcs.getInstance().getCurrentTransactions().stream().parallel().filter(t -> t.getFee() > 0.8).collect(Collectors.toList()).size() == 0);
		
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, 0.80);
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, 0.80);
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, 0.70);
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, 0.70);
		//Creado Bloque 4
		
		ts.newTransaction("Cuenta-C", "Cuenta-D", 1, 0.70);
		ts.newTransaction("Cuenta-C", "Cuenta-D", 1, 0.70);
		ts.newTransaction("Cuenta-C", "Cuenta-D", 1, 0.60);
		ts.newTransaction("Cuenta-C", "Cuenta-D", 1, 0.60);
		//Creado Bloque 5
		
		//Comprobación periódica de bloques y transacciones para ver que se están creando correctamente
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().size() == 6);
		Assert.assertTrue(bcs.getInstance().getNumberOfAddedTransactions() == 16);
		Assert.assertTrue(bcs.getInstance().getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(bcs.getInstance().getNumberOfTotalTransactions() == 18);
		Assert.assertTrue(bcs.getInstance().getNumberOfBlocks() == 5);
		
		ts.newTransaction("Cuenta-D", "Cuenta-E", 1, 0.60);
		ts.newTransaction("Cuenta-D", "Cuenta-E", 1, 0.60);
		ts.newTransaction("Cuenta-D", "Cuenta-E", 1, 0.50);
		ts.newTransaction("Cuenta-D", "Cuenta-E", 1, 0.50);
		//Creado Bloque 6
		
		ts.newTransaction("Cuenta-E", "Cuenta-F", 1, 0.50);
		ts.newTransaction("Cuenta-E", "Cuenta-F", 1, 0.50);
		ts.newTransaction("Cuenta-E", "Cuenta-F", 1, 0.40);
		ts.newTransaction("Cuenta-E", "Cuenta-F", 1, 0.40);
		//Creado Bloque 7
		
		ts.newTransaction("Cuenta-F", "Cuenta-A", 1, 0.40);
		ts.newTransaction("Cuenta-F", "Cuenta-A", 1, 0.40);
		ts.newTransaction("Cuenta-F", "Cuenta-A", 1, 0.30);
		ts.newTransaction("Cuenta-F", "Cuenta-A", 1, 0.30);
		//Creado Bloque 8
		
		ts.newTransaction("Cuenta-A", "Cuenta-B", 1, 0.30);
		ts.newTransaction("Cuenta-A", "Cuenta-B", 1, 0.30);
		ts.newTransaction("Cuenta-A", "Cuenta-B", 1, 0.20);
		ts.newTransaction("Cuenta-A", "Cuenta-B", 1, 0.20);
		//Creado Bloque 9
		
		double previousPrice = bcs.getInstance().getCurrentPrice();
		double previousTokens = bcs.getInstance().getTokens();
		Assert.assertTrue(bcs.getInstance().getProofOfWork().equals("09"));
		
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, 0.20);
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, 0.20);
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, 0.10);
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, 0.10);
		//Creado Bloque 10
		
		Assert.assertTrue(bcs.getInstance().getNumberOfAddedTransactions() == 36);
		Assert.assertTrue(bcs.getInstance().getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(bcs.getInstance().getNumberOfTotalTransactions() == 38);
		Assert.assertTrue(bcs.getInstance().getNumberOfBlocks() == 10);
		Assert.assertTrue(bcs.getInstance().getProofOfWork().equals("99"));
		Assert.assertTrue(bcs.getInstance().getTokens() == previousTokens + 1000);
		Assert.assertTrue(bcs.getInstance().getCurrentPrice() != previousPrice);
		
		//No se puede chequear el amount con assertEquals ya que al generarse aleatoriamente el minero no lo conocemos
		//Pero podemos comprobar que el total de operaciones sea correcto
		
		
		double tokensByAccount = 11.0;
		double accounts = 6.0;
		double blocks = 9.0;
		double coinbase = 10.0;
		
		double expectedTotalAmount = (tokensByAccount * accounts) + (blocks * coinbase);
		double expectedAutogeneratedTokens = 2000.0 - (tokensByAccount*accounts);
		
		Assert.assertTrue(bcs.getInstance().getTokens() - expectedAutogeneratedTokens == 0.0);
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().stream().parallel().mapToDouble(m -> m.getAmount()).sum() - expectedTotalAmount < 1);
		
		//Invalid sender
		ts.newTransaction("Cuenta-J", "Cuenta-C", 1, 0.20);
		//Invalid recipient
		ts.newTransaction("Cuenta-B", "Cuenta-L", 1, 0.20);
		//Invalid amount
		ts.newTransaction("Cuenta-B", "Cuenta-C", -3, 0.10);
		//Invalid fee
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, -1);
		//Too much fee and/or amount
		ts.newTransaction("Cuenta-B", "Cuenta-C", 1, as.getByNickname("Cuenta-B").getAmount());
		ts.newTransaction("Cuenta-B", "Cuenta-C", as.getByNickname("Cuenta-B").getAmount(), 1);
		
		Assert.assertTrue(bcs.getInstance().getNumberOfAddedTransactions() == 36);
		Assert.assertTrue(bcs.getInstance().getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(bcs.getInstance().getNumberOfTotalTransactions() == 38);
		Assert.assertTrue(bcs.getInstance().getNumberOfBlocks() == 10);
		
		as.createBuyAccount("Cuenta-B", 5.0, "Cuenta-G");
		as.createBuyAccount("Cuenta-C", 5.0, "Cuenta-H");
		as.createBuyAccount("Cuenta-D", 5.0, "Cuenta-I");
		as.createBuyAccount("Cuenta-E", 5.0, "Cuenta-J");
		
		Assert.assertTrue(bcs.getInstance().getNumberOfAddedTransactions() == 40);
		Assert.assertTrue(bcs.getInstance().getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(bcs.getInstance().getNumberOfTotalTransactions() == 42);
		Assert.assertTrue(bcs.getInstance().getNumberOfBlocks() == 11);
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().size() == 10);
		
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().stream().parallel().mapToDouble(m -> m.getAmount()).sum() - (expectedTotalAmount + 10) < 1);
		
		as.createBuyAccount("", 5.0, "G");
		as.createBuyAccount("Cuenta-C", 5.0, "");
		as.createBuyAccount("Cuenta-D", -5.0, "M");
		as.createBuyAccount("Cuenta-E", as.getByNickname("Cuenta-E").getAmount() + 1, "P");
		
		Assert.assertTrue(bcs.getInstance().getNumberOfAddedTransactions() == 40);
		Assert.assertTrue(bcs.getInstance().getNumberOfPendingTransactions() == 2);
		Assert.assertTrue(bcs.getInstance().getNumberOfTotalTransactions() == 42);
		Assert.assertTrue(bcs.getInstance().getNumberOfBlocks() == 11);
		Assert.assertTrue(bcs.getInstance().getCurrentUsers().size() == 10);
		
		bcs.getInstance().getCurrentUsers().stream().parallel().forEach((m -> System.out.println(m.getNickname() + ":" + m.getAmount())));
		
		
	}

}
