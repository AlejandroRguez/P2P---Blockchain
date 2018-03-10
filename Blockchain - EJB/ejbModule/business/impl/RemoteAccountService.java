package business.impl;

import business.AccountService;
import javax.ejb.Remote;

@Remote
public interface RemoteAccountService extends AccountService{ }
