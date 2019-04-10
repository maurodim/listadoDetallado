/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public interface Componable {
   public DefaultListModel LlenarList(Integer id);
   public DefaultTableModel LlenarTabla(Integer id);
   public ComboBoxModel LlenarCombo(Integer id);
   public DefaultListModel LlenarListConArray(ArrayList listado);
   public DefaultTableModel LlenarTablaConArray(ArrayList listado);
   public DefaultComboBoxModel LlenarComboConArray(ArrayList listado);
   
}
