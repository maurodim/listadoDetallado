/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosR;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author Usuario
 */
public class jBackground extends JDesktopPane{
    protected void paintComponent(Graphics g){
        Image img=new ImageIcon(this.getClass().getResource("/imagen/nuevos/Manantial-logo-wide.png")).getImage();
        try{
            Graphics2D g2d=(Graphics2D)g;
            double x=img.getWidth(null);
            double y=img.getHeight(null);
            g2d.scale(getWidth() / x,getHeight() / y);
            g2d.drawImage(img, 0, 0, this);
        }catch(Exception e){
            System.out.println("error "+e);
        }
    }
}
