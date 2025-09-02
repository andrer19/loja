package Grafico.cademp;

import entidades.Cademp;
import entidades.Cadmanuemp;
import entidades.Municip;
import entidades.Usuario;
import filter.EnviaEmail;
import Configuracao.Letramaiuscula;
import Municipio.ListaMunicipio;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;

import beans.AcessoBean;
import beans.CadempBean;
import beans.MunicipBean;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class cadastramanuemp extends JDialog {
	private JPanel contentPane;
	
	private JLabel lblquantidade,lblunitario,lblreqcompra,lblpedcompras,lblorcvendas,lblpedvendas,lblfornecedor,lbllote,lblestfinal,lblnormas,
	lblinspcorte,lblordemproducao,lblordemembalagem,lblinspprocesso,lblreqproducao,lblcliente,lbltransportadora;
	
	private JTextField razaosocial,orcvendas,pedvendas,pedcompras,fornecedor,estoquefinal,inspcorte,ordemembalagem,reqcompra,
	normas,ordemproducao,lote,reqproducao,inspprocesso,cliente,codtrans;
	
	JButton btncadastrar,btnlimpar;
	
	Configuracao.Letramaiuscula bloqueios = new Configuracao.Letramaiuscula();
	
	AcessoBean aces1 = new AcessoBean();

	CadempBean empbean = new CadempBean();
	private JTextField casasqtde;
	private JTextField casasunitario;

	public cadastramanuemp(final Cadmanuemp manemp, Usuario u) throws Exception {

		setTitle("MANUTEN\u00C7\u00C3O EMPRESA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(aces1.imagemicone()));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(10, 5, 477, 364);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		aces1.fundotela(contentPane);
		contentPane.setLayout(null);
		
		razaosocial = new JTextField();
		razaosocial.setBounds(5, 10, 447, 20);
		razaosocial.setDocument(new Configuracao.Letramaiuscula());
		aces1.bloqueado(razaosocial);
		contentPane.add(razaosocial);
		
		lblquantidade = new JLabel("DECIMAIS QUANTIDADE");
		aces1.padraojlabel(lblquantidade);
		lblquantidade.setBounds(5, 43, 160, 15);
		contentPane.add(lblquantidade);
		
		casasqtde = new JTextField("");
		aces1.liberado(casasqtde);
		aces1.numeros(casasqtde);
		casasqtde.setBounds(164, 40, 60, 20);
		contentPane.add(casasqtde);
		
		lblunitario = new JLabel("DECIMAIS UNITARIO");
		aces1.padraojlabel(lblunitario);
		lblunitario.setBounds(227, 43, 160, 15);
		contentPane.add(lblunitario);
		
		casasunitario = new JTextField();
		aces1.liberado(casasunitario);
		aces1.numeros(casasunitario);
		casasunitario.setBounds(389, 40, 60, 20);
		contentPane.add(casasunitario);
		
		lblreqcompra = new JLabel("REQUISIÇÃO DE COMPRA");
		lblreqcompra.setBounds(5, 67, 160, 15);
		aces1.padraojlabel(lblreqcompra);
		contentPane.add(lblreqcompra);

		reqcompra = new JTextField("");
		reqcompra.setBounds(164, 65, 60, 20);
		aces1.liberado(reqcompra);
		aces1.numeros(reqcompra);
		contentPane.add(reqcompra);
		
		lblpedcompras = new JLabel("PEDIDO DE COMPRAS");
		aces1.padraojlabel(lblpedcompras);
		lblpedcompras.setBounds(227, 67, 160, 15);
		contentPane.add(lblpedcompras);
		
		pedcompras = new JTextField();
		pedcompras.setBounds(389, 65, 60, 20);
		aces1.liberado(pedcompras);
		aces1.numeros(pedcompras);
		contentPane.add(pedcompras);
		
		lblorcvendas = new JLabel("ORÇAMENTO DE VENDAS");
		lblorcvendas.setBounds(5, 92, 160, 15);
		aces1.padraojlabel(lblorcvendas);
		contentPane.add(lblorcvendas);

		orcvendas = new JTextField();
		orcvendas.setBounds(164, 90, 60, 20);
		aces1.liberado(orcvendas);
		aces1.numeros(orcvendas);
		contentPane.add(orcvendas);
		
		lblpedvendas = new JLabel("PEDIDO DE VENDAS");
		lblpedvendas.setBounds(227, 92, 160, 15);
		aces1.padraojlabel(lblpedvendas);
		contentPane.add(lblpedvendas);

		pedvendas = new JTextField();
		pedvendas.setBounds(389, 90, 60, 20);
		aces1.liberado(pedvendas);
		aces1.numeros(pedvendas);
		contentPane.add(pedvendas);

		lblfornecedor = new JLabel("FORNECEDOR");
		lblfornecedor.setBounds(5, 117, 160, 15);
		aces1.padraojlabel(lblfornecedor);
		contentPane.add(lblfornecedor);

		fornecedor = new JTextField();
		fornecedor.setBounds(164, 115, 60, 20);
		aces1.liberado(fornecedor);
		aces1.numeros(fornecedor);
		contentPane.add(fornecedor);

		lbllote = new JLabel("LOTE");
		lbllote.setBounds(227, 117, 160, 15);
		aces1.padraojlabel(lbllote);
		contentPane.add(lbllote);

		lote = new JTextField();
		lote.setBounds(389, 115, 60, 20);
		aces1.liberado(lote);
		aces1.numeros(lote);
		contentPane.add(lote);

		lblestfinal = new JLabel("ESTOQUE FINAL");
		lblestfinal.setBounds(5, 142, 160, 15);
		aces1.padraojlabel(lblestfinal);
		contentPane.add(lblestfinal);

		estoquefinal = new JTextField();
		estoquefinal.setBounds(164, 140, 60, 20);
		aces1.liberado(estoquefinal);
		aces1.numeros(estoquefinal);
		contentPane.add(estoquefinal);
		
	    lblnormas = new JLabel("NORMAS");
		lblnormas.setBounds(227, 142, 160, 15);
		aces1.padraojlabel(lblnormas);
		contentPane.add(lblnormas);

		normas = new JTextField();
		normas.setBounds(389, 140, 60, 20);
		aces1.liberado(normas);
		contentPane.add(normas);
		
		lblinspcorte = new JLabel("INSPEÇÃO DE CORTE");
		lblinspcorte.setBounds(5, 167, 160, 14);
		aces1.padraojlabel(lblinspcorte);
		contentPane.add(lblinspcorte);

		inspcorte = new JTextField();
		inspcorte.setBounds(164, 165, 60, 20);
		aces1.liberado(inspcorte);
		aces1.numeros(inspcorte);
		contentPane.add(inspcorte);
		
		lblordemproducao = new JLabel("ORDEM DE PRODUÇÃO");
		lblordemproducao.setBounds(227, 167, 160, 15);
		aces1.padraojlabel(lblordemproducao);
		contentPane.add(lblordemproducao);

		ordemproducao = new JTextField();
		ordemproducao.setBounds(389, 165, 60, 20);
		aces1.liberado(ordemproducao);
		aces1.numeros(ordemproducao);
		contentPane.add(ordemproducao);

		lblordemembalagem = new JLabel("ORDEM DE EMBALAGEM");
		lblordemembalagem.setBounds(5, 192, 160, 15);
		aces1.padraojlabel(lblordemembalagem);
		contentPane.add(lblordemembalagem);

		ordemembalagem = new JTextField();
		ordemembalagem.setBounds(164, 190, 60, 20);
		aces1.liberado(ordemembalagem);
		aces1.numeros(ordemembalagem);
		contentPane.add(ordemembalagem);
		
		lblinspprocesso = new JLabel("INSPEÇÃO DE PROCESSO");
		aces1.padraojlabel(lblinspprocesso);
		lblinspprocesso.setBounds(227, 193, 160, 15);
		contentPane.add(lblinspprocesso);

		inspprocesso = new JTextField();
		aces1.liberado(inspprocesso);
		aces1.numeros(inspprocesso);
		inspprocesso.setBounds(389, 190, 60, 20);
		contentPane.add(inspprocesso);
		
		lblreqproducao = new JLabel("REQUISIÇÃO DE PRODUÇÃO");
		lblreqproducao.setForeground(new Color(0, 0, 0));
		lblreqproducao.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblreqproducao.setBounds(5, 219, 160, 15);
		contentPane.add(lblreqproducao);

		reqproducao = new JTextField();
		aces1.liberado(reqproducao);
		aces1.numeros(reqproducao);
		reqproducao.setBounds(164, 215, 60, 20);
		contentPane.add(reqproducao);

		lblcliente = new JLabel("CLIENTE");
		aces1.padraojlabel(lblcliente);
		lblcliente.setBounds(227, 218, 160, 14);
		contentPane.add(lblcliente);

		cliente = new JTextField();
		cliente.setBounds(389, 215, 60, 20);
		aces1.liberado(cliente);
		aces1.numeros(cliente);
		contentPane.add(cliente);
		
		lbltransportadora = new JLabel("TRANSPORTADORA");
		aces1.padraojlabel(lbltransportadora);
		lbltransportadora.setBounds(5, 244, 160, 15);
		contentPane.add(lbltransportadora);
		
		codtrans = new JTextField();
		aces1.liberado(codtrans);
		codtrans.setBounds(164, 240, 60, 20);
		aces1.numeros(codtrans);
		contentPane.add(codtrans);

		btncadastrar = new JButton();
		btncadastrar.setBounds(170, 270, 55, 46);
		btncadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					btnlimpar.requestFocus();
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					gravamanuempresa(manemp);
				}
			}
		});
		btncadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravamanuempresa(manemp);

			}
		});
		btncadastrar.setIcon(new ImageIcon(cadastramanuemp.class.getResource("/imagens/salvar.png")));
		aces1.butonfundo(btncadastrar);
		contentPane.add(btncadastrar);

		btnlimpar = new JButton();
		btnlimpar.setBounds(240, 270, 55, 46);
		btnlimpar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnlimpar.setIcon(new ImageIcon(cadastramanuemp.class.getResource("/imagens/fechar.png")));
		aces1.butonfundo(btnlimpar);
		contentPane.add(btnlimpar);

		if (manemp.getId() != null) {

			casasqtde.setText(aces1.converteintparastring(manemp.getDecqtde()));
			casasunitario.setText(aces1.converteintparastring(manemp.getDecunit()));
			orcvendas.setText(manemp.getOrcven().trim());
			razaosocial.setText(manemp.getDescemp().trim());
			pedvendas.setText(manemp.getNumpedv().trim());
			pedcompras.setText(manemp.getNumpedc().trim());
			fornecedor.setText(manemp.getCodfor().trim());
			estoquefinal.setText(manemp.getRecebi().trim());
			lote.setText(manemp.getNumlot().trim());
			inspcorte.setText(manemp.getInspgui().trim());
			ordemembalagem.setText(manemp.getOrdememb().trim());
			ordemproducao.setText(manemp.getOrdemprod().trim());
			normas.setText(manemp.getNormas().trim());
			reqcompra.setText(manemp.getReqcom().trim());
			reqproducao.setText(manemp.getEtiqpro().trim());
			inspprocesso.setText(manemp.getInspprocesso().trim());
			cliente.setText(manemp.getCodcli().trim());
			codtrans.setText(manemp.getCodtrans().trim());
			reqcompra.requestFocus();
		}else {
			razaosocial.setText(manemp.getDescemp().trim());
			reqcompra.requestFocus();
			
		}

		// fechar janela com esc
		JRootPane rootPane = this.getRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		ActionListener cancelAction = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		};
		rootPane.registerKeyboardAction(cancelAction, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public void gravamanuempresa(Cadmanuemp e2) {

		Cadmanuemp e = new Cadmanuemp();
		
		
		
		if(e2.getId() != null) {
		    e =	empbean.procuramanuemp(e2.getId());
		}

		e.setOrcven(orcvendas.getText().trim());
		e.setDescemp(razaosocial.getText().trim());
		e.setNumpedv(pedvendas.getText().trim());
		e.setNumpedc(pedcompras.getText().trim());
		e.setCodfor(fornecedor.getText().trim());
		e.setRecebi(estoquefinal.getText().trim());
		e.setNumlot(lote.getText().trim());
		e.setInspgui(inspcorte.getText().trim());
		e.setOrdememb(ordemembalagem.getText().trim());
		e.setOrdemprod(ordemproducao.getText().trim());
		e.setNormas(normas.getText().trim());
		e.setReqcom(reqcompra.getText().trim());
		e.setEtiqpro(reqproducao.getText().trim());
		e.setInspprocesso(inspprocesso.getText().trim());
		e.setCodcli(cliente.getText().trim());
		e.setCodtrans(codtrans.getText().trim());
		e.setDecqtde(Integer.parseInt(casasqtde.getText()));
		e.setDecunit(Integer.parseInt(casasunitario.getText()));

		try {
			empbean.adicionamanuemp(e);
			dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "ERRO AO GRAVAR MANUTENÇÃO EMPRESA " + e1.getMessage());
			aces1.demologger.error("ERRO AO GRAVAR MANUTENÇÃO EMPRESA " + e1.getMessage());
		}
	}
}
