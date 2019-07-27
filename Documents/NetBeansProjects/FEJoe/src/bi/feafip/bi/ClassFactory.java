package feafip.bi  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  public static feafip.bi.Iwsaa createwsaa() {
    return COM4J.createInstance( feafip.bi.Iwsaa.class, "{33B45EE7-0219-4BE1-A9CA-2B57CA4FD209}" );
  }

  public static feafip.bi.Iwsfexv1 createwsfexv1() {
    return COM4J.createInstance( feafip.bi.Iwsfexv1.class, "{CBC36AD9-1D16-4590-A82C-2ED017AAAB4C}" );
  }

  public static feafip.bi.Iwsfev1 createwsfev1() {
    return COM4J.createInstance( feafip.bi.Iwsfev1.class, "{6804CFD5-32DD-43AE-A463-CB64FCBE32D2}" );
  }

  public static feafip.bi.Iwsbfev1 createwsbfev1() {
    return COM4J.createInstance( feafip.bi.Iwsbfev1.class, "{2E472E22-AD8A-4071-8C62-D2D9B8CE47D3}" );
  }

  /**
   * wsmtxca Object
   */
  public static feafip.bi.Iwsmtxca createwsmtxca() {
    return COM4J.createInstance( feafip.bi.Iwsmtxca.class, "{C3DD12A3-EAA2-4F45-8F5D-4A25CBD19838}" );
  }

  /**
   * wsseg Object
   */
  public static feafip.bi.Iwsseg createwsseg() {
    return COM4J.createInstance( feafip.bi.Iwsseg.class, "{5B4092EF-B311-4CDD-A9F8-61A0AEC7E54C}" );
  }

  public static feafip.bi.IwsPadron createwsPadron() {
    return COM4J.createInstance( feafip.bi.IwsPadron.class, "{F57D2D12-E231-4AF7-BB54-3CDDFB52713B}" );
  }

  public static feafip.bi.IComprobante createComprobante() {
    return COM4J.createInstance( feafip.bi.IComprobante.class, "{A9B8A44F-99B4-4D18-8A54-A66CC5C39BEB}" );
  }

  public static feafip.bi.ICbteAsoc createCbteAsoc() {
    return COM4J.createInstance( feafip.bi.ICbteAsoc.class, "{259527D7-6AE5-411F-89EC-9C9A480A41F9}" );
  }

  public static feafip.bi.ITributo createTributo() {
    return COM4J.createInstance( feafip.bi.ITributo.class, "{FD8F306C-CE28-460C-810C-57CE15C35A37}" );
  }

  public static feafip.bi.IAlicIva createAlicIva() {
    return COM4J.createInstance( feafip.bi.IAlicIva.class, "{DA06EFB8-3B19-4061-A544-157036E2CB57}" );
  }

  public static feafip.bi.IOpcional createOpcional() {
    return COM4J.createInstance( feafip.bi.IOpcional.class, "{74473C21-FABC-49EB-B268-7D6B33D8C728}" );
  }

  public static feafip.bi.IObs createObs() {
    return COM4J.createInstance( feafip.bi.IObs.class, "{C778E764-6411-4086-A488-14FC22B1BA4A}" );
  }

  public static feafip.bi.IContribuyente createContribuyente() {
    return COM4J.createInstance( feafip.bi.IContribuyente.class, "{0214EC04-2B59-4CDA-BE4F-6212C9B65F02}" );
  }

  public static feafip.bi.IDomicilio createDomicilio() {
    return COM4J.createInstance( feafip.bi.IDomicilio.class, "{0370A743-18E0-424E-8124-9CA27A80EB16}" );
  }

  public static feafip.bi.IwsPadronARBA createwsPadronARBA() {
    return COM4J.createInstance( feafip.bi.IwsPadronARBA.class, "{6F042FCF-8D78-498B-8630-61346537279F}" );
  }

  public static feafip.bi.IConsultaAlicuotaRespuesta createConsultaAlicuotaRespuesta() {
    return COM4J.createInstance( feafip.bi.IConsultaAlicuotaRespuesta.class, "{E17927E3-019B-4B2E-BB5B-CD6DA8A61F59}" );
  }

  public static feafip.bi.ICertificado createCertificado() {
    return COM4J.createInstance( feafip.bi.ICertificado.class, "{189DA0FB-8B57-4C51-834E-666BE83E5878}" );
  }

  /**
   * wscdc Object
   */
  public static feafip.bi.Iwscdc createwscdc() {
    return COM4J.createInstance( feafip.bi.Iwscdc.class, "{FDFE6850-8AE7-4B58-8D03-7655A9F28402}" );
  }

  /**
   * Barcode Object
   */
  public static feafip.bi.IBarcode createBarcode() {
    return COM4J.createInstance( feafip.bi.IBarcode.class, "{C53215BA-E553-4742-8D17-193B041996F9}" );
  }

  public static feafip.bi.Iwsct createwsct() {
    return COM4J.createInstance( feafip.bi.Iwsct.class, "{0C0A8678-5679-4F36-A995-85DA19D90CF5}" );
  }
}
