package repositorios;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import entidades.Entidadegenerica;
import filter.EntityManagerUtil;

public class EntidadeRepository{

	ArrayList<Entidadegenerica> listas = new ArrayList<Entidadegenerica>();
	
	public EntidadeRepository(EntityManager manager) {

	}

	public List<Entidadegenerica> listaclientetotal(Long cliente, Date datai, Date dataf) throws Exception {
		listas = new ArrayList<Entidadegenerica>();
		Entidadegenerica ent1 = new Entidadegenerica();
		
		Locale local1 = new Locale("pt", "BR");
		DateFormat datestring = new SimpleDateFormat("yyyy-MM-dd", local1);
		Double valorf = 0.0;

		EntityManagerUtil.conexao();
		EntityManagerUtil.begin();
		Query query = EntityManagerUtil.manager
				.createNativeQuery("select c.codcli,c.desccli, SUM(p.vrtot) as total,MAX(p.emissao) as emissao,"
						+ " MAX(p.numdoc) as pedido from pdsaic p"
						+ " INNER JOIN cadcli c ON p.cliente = c.idcadcli"
						+ " where p.sql_deleted = 'F' and p.cliente =" + cliente + " and p.emissao >= '" + datestring.format(datai)
						+"' and p.emissao <= '"+ datestring.format(dataf) + "' group by c.codcli");
		List<Object[]> result = query.getResultList();
		for (Object[] row : result) {
			ent1 = new Entidadegenerica();
			
			ent1.setCodclifor((String) row[0]);
			ent1.setDescclifor((String) row[1]);
			ent1.setValortotal((Double) row[2]);
			ent1.setData((Date) row[3]);
			ent1.setNumdoc((String) row[4]);
			listas.add(ent1);

		}
		EntityManagerUtil.commit();
		EntityManagerUtil.close();

		return listas;
	}	
	
	public Double validanumero(String p) {
		Double valor = 0.0;
		
		if (p != null && !p.trim().isEmpty()) {
			valor = Double.parseDouble(p);
		}
		
		
		return valor;

	}

}
