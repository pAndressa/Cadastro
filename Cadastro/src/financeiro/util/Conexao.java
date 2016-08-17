package financeiro.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexao {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory(){
		try{
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
		}catch(Throwable e){
			System.out.println("Cria��o inicial do objeto SessionFactory falhou.Erro: " + e);
			throw new ExceptionInInitializerError();
		}
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
