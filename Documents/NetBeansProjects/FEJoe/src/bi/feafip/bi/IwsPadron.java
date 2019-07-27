package feafip.bi  ;

import com4j.*;

@IID("{0CEB0878-6393-4701-8C86-2CA793CDCB0D}")
public interface IwsPadron extends Com4jObject {
  // Methods:
  /**
   * @param cuit Mandatory double parameter.
   * @param contribuyenteResult Mandatory feafip.bi.IContribuyente parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(7)
  boolean consultar(
    double cuit,
    feafip.bi.IContribuyente contribuyenteResult);


  /**
   * @param cuit Mandatory double parameter.
   * @param archivoDestino Mandatory java.lang.String parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(8)
  boolean descargarConstancia(
    double cuit,
    java.lang.String archivoDestino);


  /**
   * <p>
   * Getter method for the COM property "ErrorDesc"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(206) //= 0xce. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String errorDesc();


  // Properties:
}
