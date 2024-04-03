package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Usuario;
import filter.EntityManagerUtil;

public class UsuarioRepository {

	public UsuarioRepository(EntityManager manager) {

	}

	public void grava(Usuario usuario) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		if (usuario.getId() == null) {
			EntityManagerUtil.manager.persist(usuario);
			EntityManagerUtil.commit();
		} else {
			EntityManagerUtil.manager.merge(usuario);
			EntityManagerUtil.commit();
		}
		EntityManagerUtil.close();
	}

	public void remove(Long id) {
		Usuario usuario = procura(id);
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		usuario.setSql_deleted("T");
		EntityManagerUtil.manager.merge(usuario);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}
	
	public void desativa(Long id) {
		Usuario usuario = procura(id);
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		usuario.setAtivo(true);
		EntityManagerUtil.manager.merge(usuario);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Usuario procura(Long id) {
		Usuario usuario = new Usuario();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		usuario = EntityManagerUtil.manager.find(Usuario.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return usuario;
	}

	
	public List<Usuario> getLista() {

		Usuario usuariolista = new Usuario();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createNativeQuery("SELECT id,login,nome,ativo FROM usuario WHERE sql_deleted = 'F' and login <> 'ADMSUPER'");

		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			usuariolista = new Usuario();
			usuariolista.setId(Long.parseLong(row[0].toString()));
			usuariolista.setLogin((String) row[1]);
			usuariolista.setNome((String) row[2]);
			usuariolista.setAtivo((Boolean) row[3]);
			usuarios.add(usuariolista);

		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return usuarios;
	}
	
	public List<Usuario> getListaclone() {

		List<Usuario> usuariosclone = new ArrayList<Usuario>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery("select x from Usuario x WHERE x.sql_deleted = 'F' and x.login <> 'ADMSUPER'");
		if (!query.getResultList().isEmpty()) {
			usuariosclone = query.getResultList();
		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return usuariosclone;
	}

	public Usuario procurausuario(String login,String senha) {
		Usuario user1 = new Usuario();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager.createQuery(
				"select x from Usuario x where x.login = '" + login + "' and  x.senha = MD5('" + senha + "') and x.sql_deleted = 'F' and x.ativo = false");

		if (!query.getResultList().isEmpty()) {
			user1 = (Usuario) query.getSingleResult();
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return user1;
	}
	public Usuario procurausuarioid(Long id) {
		Usuario user1 = new Usuario();

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("select x from Usuario x where x.id = '" + id + "' and x.sql_deleted = 'F'");

		if (!query.getResultList().isEmpty()) {
			user1 = (Usuario) query.getSingleResult();
		}

		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return user1;
	}

}
