package Configuracao;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class clsRedimensionarImagem {
	
	public Icon redimensionar(JLabel jLabel, int xLargura, int yAltura){
	       
        ImageIcon img = new ImageIcon (jLabel.getIcon().toString());  
        img.setImage(img.getImage().getScaledInstance(xLargura, yAltura,Image.SCALE_DEFAULT));
       
        return img;
    }

}
