/**
 * WebService_SanYaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class WebService_SanYaLocator extends org.apache.axis.client.Service implements com.zzc.webservice.provinceHotel.WebService_SanYa {

    public WebService_SanYaLocator() {
    }


    public WebService_SanYaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebService_SanYaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WebService_SanYaSoap
    private java.lang.String WebService_SanYaSoap_address = "http://218.77.183.129:9001/WebServices/WebService_SanYa.asmx";

    public java.lang.String getWebService_SanYaSoapAddress() {
        return WebService_SanYaSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebService_SanYaSoapWSDDServiceName = "WebService_SanYaSoap";

    public java.lang.String getWebService_SanYaSoapWSDDServiceName() {
        return WebService_SanYaSoapWSDDServiceName;
    }

    public void setWebService_SanYaSoapWSDDServiceName(java.lang.String name) {
        WebService_SanYaSoapWSDDServiceName = name;
    }

    public com.zzc.webservice.provinceHotel.WebService_SanYaSoap getWebService_SanYaSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebService_SanYaSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebService_SanYaSoap(endpoint);
    }

    public com.zzc.webservice.provinceHotel.WebService_SanYaSoap getWebService_SanYaSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zzc.webservice.provinceHotel.WebService_SanYaSoapStub _stub = new com.zzc.webservice.provinceHotel.WebService_SanYaSoapStub(portAddress, this);
            _stub.setPortName(getWebService_SanYaSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebService_SanYaSoapEndpointAddress(java.lang.String address) {
        WebService_SanYaSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.zzc.webservice.provinceHotel.WebService_SanYaSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zzc.webservice.provinceHotel.WebService_SanYaSoapStub _stub = new com.zzc.webservice.provinceHotel.WebService_SanYaSoapStub(new java.net.URL(WebService_SanYaSoap_address), this);
                _stub.setPortName(getWebService_SanYaSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WebService_SanYaSoap".equals(inputPortName)) {
            return getWebService_SanYaSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "WebService_SanYa");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "WebService_SanYaSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WebService_SanYaSoap".equals(portName)) {
            setWebService_SanYaSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
