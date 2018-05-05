package com.tfg.ui;

import com.tfg.ui.actions.BuyTokensAction;
import com.tfg.ui.actions.CreateNewAccountAction;
import com.tfg.ui.actions.SendTransactionAction;
import com.tfg.utils.menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Create new account", CreateNewAccountAction.class },
				{ "Buy tokens", BuyTokensAction.class },
				{ "Send transaction", SendTransactionAction.class }, };
	}

	


}
