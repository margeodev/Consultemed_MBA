/**
 * 
 */
package br.com.consultemed.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class JPAUtils {

	
	private static EntityManagerFactory factory;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}

	public static void shutdown() {
		if (factory != null) {
			factory.close();
		}
	}
}
