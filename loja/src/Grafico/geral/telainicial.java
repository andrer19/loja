package Grafico.geral;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPanel;

import beans.AcessoBean;
import javax.swing.JLabel;

public class telainicial extends JDialog {

     private JPanel contentPane;
     AcessoBean aces1 = new AcessoBean();
	
	public telainicial() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setModal(false);
		setBounds(10, 5, 285, 80);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		aces1.fundotela(contentPane);		
		
		aces1.demologger.info("ABERTO TELA DE LOGIN COM SUCESSO");
		JLabel lblmensagem = new JLabel("VERIFICANDO O AMBIENTE AGUARDE !!!!!!!!");
		lblmensagem.setForeground(Color.BLUE);
		lblmensagem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblmensagem.setBounds(10, 11, 249, 19);
		contentPane.add(lblmensagem);
	}
}
