/**
 * PublicInterfaceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class PublicInterfaceLocator extends org.apache.axis.client.Service implements com.zzc.webservice.travelagency.PublicInterface {

    public PublicInterfaceLocator() {
    }


    public PublicInterfaceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PublicInterfaceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PublicInterfaceSoap
    private java.lang.String PublicInterfaceSoap_address = "http://lxscs.100chengxin.com/PublicInterface.asmx";

    public java.lang.String getPublicInterfaceSoapAddress() {
        return PublicInterfaceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PublicInterfaceSoapWSDDServiceName = "PublicInterfaceSoap";

    public java.lang.String getPublicInterfaceSoapWSDDServiceName() {
        return PublicInterfaceSoapWSDDServiceName;
    }

    public void setPublicInterfaceSoapWSDDServiceName(java.lang.String name) {
        PublicInterfaceSoapWSDDServiceName = name;
    }

    public com.zzc.webservice.travelagency.PublicInterfaceSoap getPublicInterfaceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PublicInterfaceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPublicInterfaceSoap(endpoint);
    }

    public com.zzc.webservice.travelagency.PublicInterfaceSoap getPublicInterfaceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zzc.webservice.travelagency.PublicInterfaceSoapStub _stub = new com.zzc.webservice.travelagency.PublicInterfaceSoapStub(portAddress, this);
            _stub.setPortName(getPublicInterfaceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPublicInterfaceSoapEndpointAddress(java.lang.String address) {
        PublicInterfaceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.zzc.webservice.travelagency.PublicInterfaceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zzc.webservice.travelagency.PublicInterfaceSoapStub _stub = new com.zzc.webservice.travelagency.PublicInterfaceSoapStub(new java.net.URL(PublicInterfaceSoap_address), this);
                _stub.setPortName(getPublicInterfaceSoapWSDDServiceName());
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
        if ("PublicInterfaceSoap".equals(inputPortName)) {
            return getPublicInterfaceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "PublicInterface");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "PublicInterfaceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PublicInterfaceSoap".equals(portName)) {
            setPublicInterfaceSoapEndpointAddress(address);
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
