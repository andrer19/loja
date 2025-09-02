package repositorios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Acesso;
import entidades.Cadbco;
import entidades.Cadnat;
import entidades.Cadpro;
import entidades.Modulos;
import entidades.Pdsaii;
import entidades.Rotinas;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class AcessoRepository {

	private List<Acesso> useracessos;
	private List<Rotinas> rotinas;

	public AcessoRepository(EntityManager manager) {

	}

	public void grava(Acesso acesso) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (acesso.getId() == null) {
			EntityManagerUtil.manager.persist(acesso);
		} else {
			EntityManagerUtil.manager.merge(acesso);
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void gravalista(List<Acesso> acessos) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		for (Acesso p : acessos) {
			if (p.getId() == null) {
				EntityManagerUtil.manager.persist(p);
			}
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("delete from Acesso where id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Acesso procura(Long id) {
		Acesso acesso = new Acesso();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		acesso = EntityManagerUtil.manager.find(Acesso.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return acesso;
	}

	public List<Acesso> getLista(Usuario user) {
		List<Acesso> lista = new ArrayList<Acesso>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Acesso x where usuario_id = :usuario");
		query.setParameter("usuario", user);
		lista = query.getResultList();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return lista;
	}

	public List<Acesso> verificaacessos(Usuario user) {

		List<Acesso> acessos1 = new ArrayList<Acesso>();
		Boolean verifica = false;

		this.useracessos = getLista(user);

		if (user.getId() != null) {
			for (Acesso p : useracessos) {
				if (p.getModulo() != null) {
					acessos1.add(p);
				}
			}

		}

		return acessos1;
	}

	public Acesso procuraacesso1(Usuario user, String m) {
		Acesso acesso = new Acesso();

		if (user.getLogin().equals("ADMSUPER")) {
			acesso.setAcesso(true);
			acesso.setInclusao(true);
			acesso.setAlteracao(true);
			acesso.setExclusao(true);
			acesso.setNivel4(true);
			acesso.setNivel5(true);
			acesso.setNivel6(true);
			acesso.setNivel7(true);
			acesso.setNivel8(true);
			acesso.setUsuario(user);;

		} else {
			EntityManagerUtil.conexao();
			EntityManagerUtil.begin();

			Query query = EntityManagerUtil.manager.createNativeQuery("SELECT id,modulo FROM acesso WHERE usuario_id = '"
					+ user.getId() + "' and modulo = '" + m + "' and sql_deleted = 'F'");
			List<Object[]> result = query.getResultList();
			for (Object[] row : result) {
				acesso = new Acesso();
				acesso = procura(Long.parseLong(row[0].toString()));
			}
		}

		return acesso;

	}

	public String data() {
		Date data = null;

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Date f = (Date) EntityManagerUtil.manager.createNativeQuery("select NOW()").getSingleResult();

		data = f;
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(data);
	}

	public String hora() {
		Date data = null;

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Date f = (Date) EntityManagerUtil.manager.createNativeQuery("select NOW()").getSingleResult();
		data = f;
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(data);
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

	public Acesso procuraacesso(Usuario user, String m) {
		Acesso acesso = new Acesso();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("SELECT x FROM Acesso x WHERE x.usuario = " + user.getId()
				+ " and x.modulo='" + m + "' and x.sql_deleted = 'F'");

		acesso = (Acesso) query.getSingleResult();

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return acesso;

	}

	public void deletaacessos(Usuario user) {

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("delete from Acesso x Where x.usuario = :usuario1");
		query.setParameter("usuario1", user);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public List<Acesso> listausuarioacessos(Usuario user) {

		List<Acesso> acesso1 = new ArrayList<Acesso>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Acesso x Where x.usuario = :usuario1");
		query.setParameter("usuario1", user);
		if (!query.getResultList().isEmpty()) {
			acesso1 = query.getResultList();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return acesso1;

	}

}
