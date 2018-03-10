package infraestructure;

import business.ServicesFactory;

public class Factories {
	private static String CONFIG_FILE = "/factories.properties";
	public static ServicesFactory services = (ServicesFactory)
			FactoriesHelper.createFactory(CONFIG_FILE, "SERVICES_FACTORY");

}
