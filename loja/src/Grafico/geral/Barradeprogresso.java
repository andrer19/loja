package Grafico.geral;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import beans.AcessoBean;
import entidades.CriaExcel;
import entidades.Entidadegenerica;

public class Barradeprogresso extends Thread {

public JDialog dialog = new JDialog();
JProgressBar bar = new JProgressBar();
	
	
	public void run() {
		super.run();
		bar = new JProgressBar();
		bar.setIndeterminate(true);
		dialog = new JDialog();
		dialog.setModal(true);
		dialog.setResizable(false);
		dialog.setSize(new Dimension(250, 40));
		dialog.setLocationRelativeTo(null);
		dialog.setUndecorated(true);
		dialog.add(bar);
		dialog.setVisible(true);		
	}

	public void iniciaBar() {
		start();
	}
	
	public void paraBar() {
		dialog.dispose();
	}	
	
	public void relavenda(Date data1, Date data2, String filterdesc) {	
		CriaExcel cexcel = new CriaExcel();
		
		try {
			cexcel.criarexcelvenda("pedidovenda", data1, data2, filterdesc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void relatcompra(Date data1, Date data2) {	
		CriaExcel cexcel = new CriaExcel();
		
		try {
			cexcel.criarexcelcompra("pedidocompra", data1, data2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void relatpadrao(String tabelar,String abar,Date data1, Date data2,Entidadegenerica genr, String titulor) throws Exception {	
		CriaExcel cexcel = new CriaExcel();
		try {
			cexcel.criarexcelpadrao(tabelar, abar, data1, data2, genr,titulor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
