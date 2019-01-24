package com.ipartek.formacion.dgt.wssoap;

public class WSDatosVehiculoProxy implements com.ipartek.formacion.dgt.wssoap.WSDatosVehiculo {
  private String _endpoint = null;
  private com.ipartek.formacion.dgt.wssoap.WSDatosVehiculo wSDatosVehiculo = null;
  
  public WSDatosVehiculoProxy() {
    _initWSDatosVehiculoProxy();
  }
  
  public WSDatosVehiculoProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSDatosVehiculoProxy();
  }
  
  private void _initWSDatosVehiculoProxy() {
    try {
      wSDatosVehiculo = (new com.ipartek.formacion.dgt.wssoap.WSDatosVehiculoServiceLocator()).getWSDatosVehiculo();
      if (wSDatosVehiculo != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSDatosVehiculo)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSDatosVehiculo)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSDatosVehiculo != null)
      ((javax.xml.rpc.Stub)wSDatosVehiculo)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ipartek.formacion.dgt.wssoap.WSDatosVehiculo getWSDatosVehiculo() {
    if (wSDatosVehiculo == null)
      _initWSDatosVehiculoProxy();
    return wSDatosVehiculo;
  }
  
  public com.ipartek.formacion.modelo.pojo.Coche obtenerDatos(java.lang.String matricula) throws java.rmi.RemoteException{
    if (wSDatosVehiculo == null)
      _initWSDatosVehiculoProxy();
    return wSDatosVehiculo.obtenerDatos(matricula);
  }
  
  
}