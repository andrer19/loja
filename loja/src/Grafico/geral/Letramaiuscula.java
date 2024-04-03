package Grafico.geral;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Letramaiuscula extends PlainDocument {
	
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	        if (str == null) {
	            return;
	        }
	        super.insertString(offs, str.toUpperCase(), a);
	    }
	 
	

}
