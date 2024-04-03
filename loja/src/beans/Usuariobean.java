package beans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import repositorios.CadempRepository;
import repositorios.PdentiRepository;
import repositorios.UsuarioRepository;
import entidades.Cademp;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class Usuariobean {

	public Usuario user = new Usuario();
	public List<Usuario> usuarios, usuariosclone;
	AcessoBean aces1 = new AcessoBean();

	public boolean procurasenha(String login,String senha) {
		EntityManagerUtil.conexao();
		boolean valido = false;
		UsuarioRepository userrepo = new UsuarioRepository(EntityManagerUtil.manager);
		Usuario user = new Usuario();
		try {
			user = userrepo.procurausuario(login,senha);
			aces1.demologger.info("achou senha bean");
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura senha bean " + t.getMessage());
			aces1.demologger.error("Erro procura senha bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		if (user.getId() != null) {
			valido = true;
		}

		return valido;
	}

	public Usuario procurausuario(String login,String senha) {
		EntityManagerUtil.conexao();
		UsuarioRepository userrepo = new UsuarioRepository(EntityManagerUtil.manager);
		Usuario user = new Usuario();
		try {
			user = userrepo.procurausuario(login,senha);
			aces1.demologger.info("achou usuario bean");
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura usuario bean " + t.getMessage());
			aces1.demologger.error("Erro procura usuario bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return user;
	}

	public String procuranomeusuario(String login,String senha) {
		EntityManagerUtil.conexao();
		UsuarioRepository userrepo = new UsuarioRepository(EntityManagerUtil.manager);
		Usuario user = new Usuario();
		try {
			user = userrepo.procurausuario(login,senha);
			aces1.demologger.info("achou nome usuario bean");
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura nome usuario bean " + t.getMessage());
			aces1.demologger.error("Erro procura nome usuario bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return user.getNome();

	}

	public Usuario procurausuarioid(Long id) {
		EntityManagerUtil.conexao();
		Usuario user1 = new Usuario();
		UsuarioRepository userrepo = new UsuarioRepository(EntityManagerUtil.manager);
		try {
			user1 = userrepo.procurausuarioid(id);
			aces1.demologger.info("achou id usuario bean");
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura id usuario bean " + t.getMessage());
			aces1.demologger.error("Erro procura id usuario bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
		return user1;
	}

	public void adiciona(Usuario p) {
		EntityManagerUtil.conexao();
		UsuarioRepository repository = new UsuarioRepository(EntityManagerUtil.manager);
		try {
			repository.grava(p);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro adiciona usuario bean " + t.getMessage());
			aces1.demologger.error("Erro adiciona usuario bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public Usuario procura(Long id) {
		EntityManagerUtil.conexao();
		UsuarioRepository repository = new UsuarioRepository(EntityManagerUtil.manager);
		user = new Usuario();
		try {
			user = repository.procura(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro procura usuario bean " + t.getMessage());
			aces1.demologger.error("Erro procura usuario bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}

		return this.user = repository.procura(id);
	}

	public void remove(Long id) {
		EntityManagerUtil.conexao();
		UsuarioRepository repository = new UsuarioRepository(EntityManagerUtil.manager);

		try {
			repository.remove(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro exclui usuario bean " + t.getMessage());
			aces1.demologger.error("Erro exclui usuario bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}
	
	public void desativa(Long id) {
		EntityManagerUtil.conexao();
		UsuarioRepository repository = new UsuarioRepository(EntityManagerUtil.manager);

		try {
			repository.desativa(id);
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, "Erro desativa usuario bean " + t.getMessage());
			aces1.demologger.error("Erro desativa usuario bean " + t.getMessage());
			EntityManagerUtil.rollback();
		} finally {
			EntityManagerUtil.close();
		}
	}

	public List<Usuario> getUsuarios() {
		EntityManagerUtil.conexao();
		if (this.usuarios == null) {
			UsuarioRepository repository = new UsuarioRepository(EntityManagerUtil.manager);
			try {
				this.usuarios = repository.getLista();
				aces1.demologger.info("listou usuario bean");
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro lista usuario bean " + t.getMessage());
				aces1.demologger.error("Erro lista usuario bean " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}
		}
		return usuarios;
	}
	
	
	public List<Usuario> getUsuariosclone() {
		EntityManagerUtil.conexao();
		if (this.usuariosclone == null) {
			UsuarioRepository repository = new UsuarioRepository(EntityManagerUtil.manager);
			try {
				this.usuariosclone = repository.getListaclone();
				aces1.demologger.info("listou usuario clone bean");
			} catch (Throwable t) {
				JOptionPane.showMessageDialog(null, "Erro lista usuario clone bean " + t.getMessage());
				aces1.demologger.error("Erro lista usuario clone bean " + t.getMessage());
				EntityManagerUtil.rollback();
			} finally {
				EntityManagerUtil.close();
			}
		}
		return usuariosclone;
	}

	public String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			// Instanciamos o nosso HASH MD5, poderíamos usar outro como
			// SHA, por exemplo, mas optamos por MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior comparação se senhas
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// =======================================================================================

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void setUsuariosclone(List<Usuario> usuariosclone) {
		this.usuariosclone = usuariosclone;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	
	
	

}
