package repositorios;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Logusu;
import entidades.Usuario;
import filter.EntityManagerUtil;

public class LogusuRepository {

	public LogusuRepository(EntityManager manager) {

	}

	public void adiciona(Logusu logusu) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.persist(logusu);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void adicionaLog(String texto, String alias, Long id1, Usuario u) {

		
		Logusu logusu = new Logusu();
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "ERRO NO IP " + e.getMessage());
		}
		
		//JOptionPane.showMessageDialog(null, "entrou");
		Date hoje = new Date();
		//JOptionPane.showMessageDialog(null, "pasou data");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("aaaa:mm:dd");
		Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
		//JOptionPane.showMessageDialog(null, "passou hora");
		String horaFormatada = sdf.format(hora);
		//JOptionPane.showMessageDialog(null, "formatou hora");
		
		//JOptionPane.showMessageDialog(null, "data " + hoje);
		
		//JOptionPane.showMessageDialog(null, "hora " + horaFormatada);
		
		//JOptionPane.showMessageDialog(null, "ip " + ip);
		
		//JOptionPane.showMessageDialog(null, "texto " + texto);
		
		//JOptionPane.showMessageDialog(null, "usuario " + u.getNome());
		
		//JOptionPane.showMessageDialog(null, "alias " + alias);
		
		//JOptionPane.showMessageDialog(null, "dataa " + hoje + " horaa " + horaFormatada + " ipa " + ip + " textoa "
				//+ texto + " usuarioa " + u.getNome() + " aliasa " + alias + " id1a " + id1);

		logusu.setData(hoje);
		logusu.setHora(horaFormatada);
		logusu.setIp(ip.toString());
		logusu.setTexto(texto);
		logusu.setUsuario(u.getNome());
		logusu.setAlias(alias);
		logusu.setId1(id1);
		
		//JOptionPane.showMessageDialog(null, "data " + logusu.getData() + " hora " + logusu.getHora() + " ip " + logusu.getIp() + " texto "
				//+ logusu.getTexto() + " usuario " + logusu.getUsuario() + " alias " + logusu.getAlias() + " id1 " + logusu.getId1());

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.persist(logusu);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

	}

	public void remove(Long id) {
		Logusu logusu = this.procura(id);
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.remove(logusu);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public void atualiza(Logusu logusu) {
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		EntityManagerUtil.manager.merge(logusu);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
	}

	public Logusu procura(Long id) {
		Logusu logusu = new Logusu();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		logusu = EntityManagerUtil.manager.find(Logusu.class, id);
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return logusu;
	}

	public List<Logusu> getLista() {
		List<Logusu> lista = new ArrayList<Logusu>();
		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createQuery("select x from Logusu x  where x.sql_deleted = :sql_deleted");
		query.setParameter("sql_deleted", "F");
		lista = query.getResultList();
		EntityManagerUtil.commit();
		EntityManagerUtil.close();
		return lista;
	}

}
