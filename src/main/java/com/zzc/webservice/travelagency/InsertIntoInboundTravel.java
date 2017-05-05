/**
 * InsertIntoInboundTravel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.travelagency;

public class InsertIntoInboundTravel  implements java.io.Serializable {
    private java.lang.String areaID;

    private java.lang.String code;

    private java.lang.String unitMaster;

    private java.lang.String filler;

    private java.lang.String fillerTel;

    private java.lang.String sortID;

    private java.lang.String seasonDay;

    private java.lang.String seasonAdmitDay;

    private java.lang.String season;

    private java.lang.String seasonAdmit;

    private java.lang.String verificationCode;

    public InsertIntoInboundTravel() {
    }

    public InsertIntoInboundTravel(
           java.lang.String areaID,
           java.lang.String code,
           java.lang.String unitMaster,
           java.lang.String filler,
           java.lang.String fillerTel,
           java.lang.String sortID,
           java.lang.String seasonDay,
           java.lang.String seasonAdmitDay,
           java.lang.String season,
           java.lang.String seasonAdmit,
           java.lang.String verificationCode) {
           this.areaID = areaID;
           this.code = code;
           this.unitMaster = unitMaster;
           this.filler = filler;
           this.fillerTel = fillerTel;
           this.sortID = sortID;
           this.seasonDay = seasonDay;
           this.seasonAdmitDay = seasonAdmitDay;
           this.season = season;
           this.seasonAdmit = seasonAdmit;
           this.verificationCode = verificationCode;
    }


    /**
     * Gets the areaID value for this InsertIntoInboundTravel.
     * 
     * @return areaID
     */
    public java.lang.String getAreaID() {
        return areaID;
    }


    /**
     * Sets the areaID value for this InsertIntoInboundTravel.
     * 
     * @param areaID
     */
    public void setAreaID(java.lang.String areaID) {
        this.areaID = areaID;
    }


    /**
     * Gets the code value for this InsertIntoInboundTravel.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this InsertIntoInboundTravel.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the unitMaster value for this InsertIntoInboundTravel.
     * 
     * @return unitMaster
     */
    public java.lang.String getUnitMaster() {
        return unitMaster;
    }


    /**
     * Sets the unitMaster value for this InsertIntoInboundTravel.
     * 
     * @param unitMaster
     */
    public void setUnitMaster(java.lang.String unitMaster) {
        this.unitMaster = unitMaster;
    }


    /**
     * Gets the filler value for this InsertIntoInboundTravel.
     * 
     * @return filler
     */
    public java.lang.String getFiller() {
        return filler;
    }


    /**
     * Sets the filler value for this InsertIntoInboundTravel.
     * 
     * @param filler
     */
    public void setFiller(java.lang.String filler) {
        this.filler = filler;
    }


    /**
     * Gets the fillerTel value for this InsertIntoInboundTravel.
     * 
     * @return fillerTel
     */
    public java.lang.String getFillerTel() {
        return fillerTel;
    }


    /**
     * Sets the fillerTel value for this InsertIntoInboundTravel.
     * 
     * @param fillerTel
     */
    public void setFillerTel(java.lang.String fillerTel) {
        this.fillerTel = fillerTel;
    }


    /**
     * Gets the sortID value for this InsertIntoInboundTravel.
     * 
     * @return sortID
     */
    public java.lang.String getSortID() {
        return sortID;
    }


    /**
     * Sets the sortID value for this InsertIntoInboundTravel.
     * 
     * @param sortID
     */
    public void setSortID(java.lang.String sortID) {
        this.sortID = sortID;
    }


    /**
     * Gets the seasonDay value for this InsertIntoInboundTravel.
     * 
     * @return seasonDay
     */
    public java.lang.String getSeasonDay() {
        return seasonDay;
    }


    /**
     * Sets the seasonDay value for this InsertIntoInboundTravel.
     * 
     * @param seasonDay
     */
    public void setSeasonDay(java.lang.String seasonDay) {
        this.seasonDay = seasonDay;
    }


    /**
     * Gets the seasonAdmitDay value for this InsertIntoInboundTravel.
     * 
     * @return seasonAdmitDay
     */
    public java.lang.String getSeasonAdmitDay() {
        return seasonAdmitDay;
    }


    /**
     * Sets the seasonAdmitDay value for this InsertIntoInboundTravel.
     * 
     * @param seasonAdmitDay
     */
    public void setSeasonAdmitDay(java.lang.String seasonAdmitDay) {
        this.seasonAdmitDay = seasonAdmitDay;
    }


    /**
     * Gets the season value for this InsertIntoInboundTravel.
     * 
     * @return season
     */
    public java.lang.String getSeason() {
        return season;
    }


    /**
     * Sets the season value for this InsertIntoInboundTravel.
     * 
     * @param season
     */
    public void setSeason(java.lang.String season) {
        this.season = season;
    }


    /**
     * Gets the seasonAdmit value for this InsertIntoInboundTravel.
     * 
     * @return seasonAdmit
     */
    public java.lang.String getSeasonAdmit() {
        return seasonAdmit;
    }


    /**
     * Sets the seasonAdmit value for this InsertIntoInboundTravel.
     * 
     * @param seasonAdmit
     */
    public void setSeasonAdmit(java.lang.String seasonAdmit) {
        this.seasonAdmit = seasonAdmit;
    }


    /**
     * Gets the verificationCode value for this InsertIntoInboundTravel.
     * 
     * @return verificationCode
     */
    public java.lang.String getVerificationCode() {
        return verificationCode;
    }


    /**
     * Sets the verificationCode value for this InsertIntoInboundTravel.
     * 
     * @param verificationCode
     */
    public void setVerificationCode(java.lang.String verificationCode) {
        this.verificationCode = verificationCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertIntoInboundTravel)) return false;
        InsertIntoInboundTravel other = (InsertIntoInboundTravel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.areaID==null && other.getAreaID()==null) || 
             (this.areaID!=null &&
              this.areaID.equals(other.getAreaID()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.unitMaster==null && other.getUnitMaster()==null) || 
             (this.unitMaster!=null &&
              this.unitMaster.equals(other.getUnitMaster()))) &&
            ((this.filler==null && other.getFiller()==null) || 
             (this.filler!=null &&
              this.filler.equals(other.getFiller()))) &&
            ((this.fillerTel==null && other.getFillerTel()==null) || 
             (this.fillerTel!=null &&
              this.fillerTel.equals(other.getFillerTel()))) &&
            ((this.sortID==null && other.getSortID()==null) || 
             (this.sortID!=null &&
              this.sortID.equals(other.getSortID()))) &&
            ((this.seasonDay==null && other.getSeasonDay()==null) || 
             (this.seasonDay!=null &&
              this.seasonDay.equals(other.getSeasonDay()))) &&
            ((this.seasonAdmitDay==null && other.getSeasonAdmitDay()==null) || 
             (this.seasonAdmitDay!=null &&
              this.seasonAdmitDay.equals(other.getSeasonAdmitDay()))) &&
            ((this.season==null && other.getSeason()==null) || 
             (this.season!=null &&
              this.season.equals(other.getSeason()))) &&
            ((this.seasonAdmit==null && other.getSeasonAdmit()==null) || 
             (this.seasonAdmit!=null &&
              this.seasonAdmit.equals(other.getSeasonAdmit()))) &&
            ((this.verificationCode==null && other.getVerificationCode()==null) || 
             (this.verificationCode!=null &&
              this.verificationCode.equals(other.getVerificationCode())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAreaID() != null) {
            _hashCode += getAreaID().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getUnitMaster() != null) {
            _hashCode += getUnitMaster().hashCode();
        }
        if (getFiller() != null) {
            _hashCode += getFiller().hashCode();
        }
        if (getFillerTel() != null) {
            _hashCode += getFillerTel().hashCode();
        }
        if (getSortID() != null) {
            _hashCode += getSortID().hashCode();
        }
        if (getSeasonDay() != null) {
            _hashCode += getSeasonDay().hashCode();
        }
        if (getSeasonAdmitDay() != null) {
            _hashCode += getSeasonAdmitDay().hashCode();
        }
        if (getSeason() != null) {
            _hashCode += getSeason().hashCode();
        }
        if (getSeasonAdmit() != null) {
            _hashCode += getSeasonAdmit().hashCode();
        }
        if (getVerificationCode() != null) {
            _hashCode += getVerificationCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertIntoInboundTravel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", ">InsertIntoInboundTravel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "AreaID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitMaster");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "UnitMaster"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filler");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "Filler"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fillerTel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "FillerTel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "SortID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seasonDay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "SeasonDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seasonAdmitDay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "SeasonAdmitDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("season");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "Season"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seasonAdmit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "SeasonAdmit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verificationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://lxstj.100chengxin.com/", "VerificationCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
