/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public interface Adeudable {
    public ArrayList ListarAPagar();
    public ArrayList ListarACobrar();
    public Object ActualizarComprobante(Object objeto);
    public Object PagarComprobante(Object objeto);
    public ArrayList ListarPorLocalidad(Integer idLocalidad);
    public Object PagarConOtrosMedios(Object objeto);
    
}
