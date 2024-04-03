package repositorios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Local;
import filter.EntityManagerUtil;

public class LocalRepository {

	Locale local1 = new Locale("pt", "BR");
	DateFormat datestring = new SimpleDateFormat("dd/MM/yyyy", local1);

	public LocalRepository(EntityManager manager) {
		
	}

	public void grava(Local local) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (local.getIdlocal() == null) {
			EntityManagerUtil.manager.persist(local);
			EntityManagerUtil.commit();
		} else {
			EntityManagerUtil.manager.merge(local);
			EntityManagerUtil.commit();
		}
		EntityManagerUtil.close();
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Local local = procura(id);
		EntityManagerUtil.manager.remove(local);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void atualiza(Local local) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(local);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Local procura(Long id) {
		Local local = new Local();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		local = EntityManagerUtil.manager.find(Local.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		return local;
	}

	public List<Local> getLista() {
		List<Local> lista = new ArrayList<Local>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Local x");
		lista = query.getResultList();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		
		return lista;
	}

	public Date dataatual() {
		Date data = null;
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
			Date f = (Date) EntityManagerUtil.manager.createNativeQuery("select NOW()").getSingleResult();

			data = f;
			EntityManagerUtil.commit();
			EntityManagerUtil.close();
			
		return data;
	}

}
