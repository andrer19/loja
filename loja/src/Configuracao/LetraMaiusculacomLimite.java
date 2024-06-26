package Configuracao;

import javax.swing.text.PlainDocument;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Classe para controle da quantidade de caracteres a serem digitados em &lt;code&gt;TextArea&lt;/code&gt;
 * @author junior
 */
public class LetraMaiusculacomLimite extends PlainDocument{
  private int maxLength = 0;
  private boolean isUpperCase = false;

  /**
   * Define a quantidade m�xima de caracteres a serem digitados
   * @param length int
   */
  public LetraMaiusculacomLimite(int length) {
    super();
    maxLength = length;
  }

  /**
   * Define o documento para ser digitado apenas em uppercase
   * @param upper boolean
   */
  public void setUpperCase(boolean upper) {
    isUpperCase = upper;
  }

  /**
   * Controla a entrada e a quantidade de caracteres
   * @param offs int
   * @param str String
   * @param a AttributeSet
   * @throws BadLocationException
   */
  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
    if (str != null) {
      int qtdeFinal = getLength() + str.length();

      if (maxLength == 0 || qtdeFinal != maxLength)
        super.insertString(offs,str.toUpperCase(), a);
        
      else
      if (qtdeFinal > maxLength) {
        String nova = str.substring(0, maxLength - getLength());
        super.insertString(offs, nova.toUpperCase(), a);
      }
    }
  }
 
  
}
