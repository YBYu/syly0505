/**
 * WebService_SanYaSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class WebService_SanYaSoapStub extends org.apache.axis.client.Stub implements com.zzc.webservice.provinceHotel.WebService_SanYaSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[13];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("A1_Test");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Uuid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "A1_TestResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetHotel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"), com.zzc.webservice.provinceHotel.TraveAgenciesBasic.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetHotelResponse>GetHotelResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetHotelResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetHotelEstimate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimate"), com.zzc.webservice.provinceHotel.HotelEstimate.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetHotelEstimateResponse>GetHotelEstimateResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetHotelEstimateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetMonthly");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "A"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "Audit"), com.zzc.webservice.provinceHotel.Audit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "UserAccount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetMonthlyResponse>GetMonthlyResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetMonthlyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetDragonBoatFestival");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "DragonBoatFestivalInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "DragonBoatFestivalInfo"), com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetDragonBoatFestivalResponse>GetDragonBoatFestivalResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetDragonBoatFestivalResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetMoonFestival");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "MoonFestivalInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "MoonFestivalInfo"), com.zzc.webservice.provinceHotel.MoonFestivalInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetMoonFestivalResponse>GetMoonFestivalResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetMoonFestivalResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddHotel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"), com.zzc.webservice.provinceHotel.TraveAgenciesBasic.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>AddHotelResponse>AddHotelResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "AddHotelResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ModiflyHotel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "UserAccount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic"), com.zzc.webservice.provinceHotel.TraveAgenciesBasic.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>ModiflyHotelResponse>ModiflyHotelResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ModiflyHotelResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddHotelEstimate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimateInMonth"), com.zzc.webservice.provinceHotel.HotelEstimateInMonth.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>AddHotelEstimateResponse>AddHotelEstimateResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "AddHotelEstimateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddAuditAndMonthly");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "audit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "Audit"), com.zzc.webservice.provinceHotel.Audit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "listTheMonth"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfTheMonth"), com.zzc.webservice.provinceHotel.TheMonth[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "reportedEnterpriseUserAccount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>AddAuditAndMonthlyResponse>AddAuditAndMonthlyResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "AddAuditAndMonthlyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ModiflyAuditAndMonthly");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "audit"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "Audit"), com.zzc.webservice.provinceHotel.Audit.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "newlistTheMonth"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfTheMonth"), com.zzc.webservice.provinceHotel.TheMonth[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth"));
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "reportedEnterpriseUserAccount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>ModiflyAuditAndMonthlyResponse>ModiflyAuditAndMonthlyResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ModiflyAuditAndMonthlyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddDragonBoatFestival");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "dBFInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "DragonBoatFestivalInfo"), com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userAccount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>AddDragonBoatFestivalResponse>AddDragonBoatFestivalResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "AddDragonBoatFestivalResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddMoonFestival");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Key"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "moonFInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", "MoonFestivalInfo"), com.zzc.webservice.provinceHotel.MoonFestivalInfo.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userAccount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", ">>AddMoonFestivalResponse>AddMoonFestivalResult"));
        oper.setReturnClass(com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "AddMoonFestivalResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

    }

    public WebService_SanYaSoapStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WebService_SanYaSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WebService_SanYaSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>AddAuditAndMonthlyResponse>AddAuditAndMonthlyResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>AddDragonBoatFestivalResponse>AddDragonBoatFestivalResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>AddHotelEstimateResponse>AddHotelEstimateResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>AddHotelResponse>AddHotelResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>AddMoonFestivalResponse>AddMoonFestivalResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>GetDragonBoatFestivalResponse>GetDragonBoatFestivalResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>GetHotelEstimateResponse>GetHotelEstimateResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>GetHotelResponse>GetHotelResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>GetMonthlyResponse>GetMonthlyResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>GetMoonFestivalResponse>GetMoonFestivalResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>ModiflyAuditAndMonthlyResponse>ModiflyAuditAndMonthlyResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>ModiflyHotelResponse>ModiflyHotelResult");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddAuditAndMonthly");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddAuditAndMonthly.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddAuditAndMonthlyResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddDragonBoatFestival");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddDragonBoatFestival.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddDragonBoatFestivalResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddHotel");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddHotel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddHotelEstimate");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddHotelEstimate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddHotelEstimateResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddHotelEstimateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddHotelResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddHotelResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddMoonFestival");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddMoonFestival.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">AddMoonFestivalResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AddMoonFestivalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">GetDragonBoatFestival");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetDragonBoatFestival.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">GetDragonBoatFestivalResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">GetHotelEstimate");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetHotelEstimate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">GetHotelEstimateResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetHotelEstimateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">GetMonthly");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetMonthly.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">GetMonthlyResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetMonthlyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">GetMoonFestival");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetMoonFestival.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">GetMoonFestivalResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.GetMoonFestivalResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ModiflyAuditAndMonthly");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthly.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ModiflyAuditAndMonthlyResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ModiflyHotel");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.ModiflyHotel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ModiflyHotelResponse");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.ModiflyHotelResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "AbstractObject");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.AbstractObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfTheMonth");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.TheMonth[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth");
            qName2 = new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "Audit");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.Audit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "DragonBoatFestivalInfo");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimate");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.HotelEstimate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimateInMonth");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.HotelEstimateInMonth.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimateValues");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.HotelEstimateValues.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "MoonFestivalInfo");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.MoonFestivalInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "TheMonth");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.TheMonth.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", "TraveAgenciesBasic");
            cachedSerQNames.add(qName);
            cls = com.zzc.webservice.provinceHotel.TraveAgenciesBasic.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String a1_Test(java.lang.String uuid) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/A1_Test");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "A1_Test"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {uuid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult getHotel(java.lang.String key, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/GetHotel");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetHotel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, traveAgenciesBasic});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.GetHotelResponseGetHotelResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult getHotelEstimate(java.lang.String key, com.zzc.webservice.provinceHotel.HotelEstimate hotelEstimate) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/GetHotelEstimate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetHotelEstimate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, hotelEstimate});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.GetHotelEstimateResponseGetHotelEstimateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult getMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit a, java.lang.String userAccount) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/GetMonthly");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetMonthly"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, a, userAccount});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.GetMonthlyResponseGetMonthlyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult getDragonBoatFestival(java.lang.String key, com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dragonBoatFestivalInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/GetDragonBoatFestival");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetDragonBoatFestival"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, dragonBoatFestivalInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.GetDragonBoatFestivalResponseGetDragonBoatFestivalResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult getMoonFestival(java.lang.String key, com.zzc.webservice.provinceHotel.MoonFestivalInfo moonFestivalInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/GetMoonFestival");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetMoonFestival"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, moonFestivalInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.GetMoonFestivalResponseGetMoonFestivalResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult addHotel(java.lang.String key, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/AddHotel");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "AddHotel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, traveAgenciesBasic});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.AddHotelResponseAddHotelResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult modiflyHotel(java.lang.String key, java.lang.String userAccount, com.zzc.webservice.provinceHotel.TraveAgenciesBasic traveAgenciesBasic) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/ModiflyHotel");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ModiflyHotel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, userAccount, traveAgenciesBasic});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.ModiflyHotelResponseModiflyHotelResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult addHotelEstimate(java.lang.String key, com.zzc.webservice.provinceHotel.HotelEstimateInMonth hotelEstimate) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/AddHotelEstimate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "AddHotelEstimate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, hotelEstimate});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.AddHotelEstimateResponseAddHotelEstimateResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult addAuditAndMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit audit, com.zzc.webservice.provinceHotel.TheMonth[] listTheMonth, java.lang.String reportedEnterpriseUserAccount) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/AddAuditAndMonthly");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "AddAuditAndMonthly"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, audit, listTheMonth, reportedEnterpriseUserAccount});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.AddAuditAndMonthlyResponseAddAuditAndMonthlyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult modiflyAuditAndMonthly(java.lang.String key, com.zzc.webservice.provinceHotel.Audit audit, com.zzc.webservice.provinceHotel.TheMonth[] newlistTheMonth, java.lang.String reportedEnterpriseUserAccount) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/ModiflyAuditAndMonthly");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ModiflyAuditAndMonthly"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, audit, newlistTheMonth, reportedEnterpriseUserAccount});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.ModiflyAuditAndMonthlyResponseModiflyAuditAndMonthlyResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult addDragonBoatFestival(java.lang.String key, com.zzc.webservice.provinceHotel.DragonBoatFestivalInfo dBFInfo, java.lang.String userAccount) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/AddDragonBoatFestival");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "AddDragonBoatFestival"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, dBFInfo, userAccount});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.AddDragonBoatFestivalResponseAddDragonBoatFestivalResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult addMoonFestival(java.lang.String key, com.zzc.webservice.provinceHotel.MoonFestivalInfo moonFInfo, java.lang.String userAccount) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/AddMoonFestival");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "AddMoonFestival"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {key, moonFInfo, userAccount});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.zzc.webservice.provinceHotel.AddMoonFestivalResponseAddMoonFestivalResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
