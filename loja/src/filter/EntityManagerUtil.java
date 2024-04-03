package filter;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.swing.JOptionPane;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@WebFilter(servletNames = { "Faces Servlet" })
public class EntityManagerUtil {
	private static EntityManagerFactory factory;

	public static EntityManager manager;
	public static String caminhobanco;
	static String raiz = System.getProperty("user.dir");
	
	static String diretorio = raiz;

	public static void criaconexao() {
		caminhobanco = diretorio + "\\banco.txt";
		
		//JOptionPane.showMessageDialog(null, "DIRETORIO: " + raiz);

		File file = new File(diretorio);
		if (!file.exists()) {
			file.mkdirs();
		}

		PrintStream ps = null;

		File filebanco = new File(caminhobanco);
		if (!filebanco.exists()) {
			try {
				ps = new PrintStream(caminhobanco);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ps.print("javax.persistence.jdbc.url = jdbc:mysql://localhost:3306/loja?useSSL=false&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
			ps.close();
		}

		try (InputStream jpaFileInput = new FileInputStream(diretorio + "\\banco.txt")) {
			Properties properties = new Properties();
			properties.load(jpaFileInput);
			factory = Persistence.createEntityManagerFactory("play", properties);
		} catch (Exception ex) {
			// Tratamento de exceção
		}

	}

	public void destroy() {
		this.factory.close();
	}

	public static EntityManager getManager() {
		
		//JOptionPane.showMessageDialog(null, "Conexao " + manager);
		if (manager == null) {
			criaconexao();
			
		}
		
		manager = factory.createEntityManager();
		

		return manager;

	}

	public static EntityManager conexao() {
		if (!manager.isOpen()) {
			EntityManagerUtil.getManager();
		}

		return manager;
	}

	public static void begin() {
		EntityTransaction tx = manager.getTransaction();
		if (!tx.isActive()) {
			manager.getTransaction().begin();
		}
	}

	public static void commit() {
		manager.getTransaction().commit();
	}

	public static void rollback() {
		manager.getTransaction().rollback();
	}

	public static void close() {
		if (manager.isOpen()) {
			// manager.flush();
			manager.close();
			//factory.close();
		}
	}

}