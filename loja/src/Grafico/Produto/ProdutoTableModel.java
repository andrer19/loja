package Grafico.Produto;

import java.awt.Color;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import beans.CadproBean;

import entidades.Cadpro;

/**
 *
 * @author Samuelson
 */
public class ProdutoTableModel extends AbstractTableModel{
    
	CadproBean lista = new CadproBean();
    public List<Cadpro> dados = (List<Cadpro>) lista.getCodpros();
   
    
    private String[] colunas = {"IDPRODUTO","CODPRO","DESCRIÇÃO","UN","VRCOMPRA","ECONOMICO"};

    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getRowCount() {
    return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
       
        case 0:
            return dados.get(linha).getIdcadpro();
        case 1:
            return dados.get(linha).getCODPRO();
        case 2: 
            return dados.get(linha).getDESCPRO();
        case 3:
            return dados.get(linha).getUN();
        case 4:
            return dados.get(linha).getVRCOMPRA();
        case 5: 
            return dados.get(linha).getECONOMICO();
        }        
        return null;
        
    }
    
    private Color getCor(int linha) {  
        // linhas pares são amarelas e impares são verdes  
        if (linha % 2 == 0) {  
            return Color.WHITE;  
        }  
        return Color.LIGHT_GRAY;  
    }  
    
   
    
    public void addRow(Cadpro p){
    	 this.dados.add(p);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

}
