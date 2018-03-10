package business.impl;

import business.TransactionService;
import javax.ejb.Remote;

@Remote
public interface RemoteTransactionService extends TransactionService { }
