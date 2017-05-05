/**
 * HotelEstimate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zzc.webservice.provinceHotel;

public class HotelEstimate  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String countryCode;

    private java.lang.Integer year;

    private java.lang.Integer month;

    private java.lang.Integer type;

    private java.lang.Integer domestic;

    private java.lang.Integer foreigner;

    private java.lang.Integer hongkong;

    private java.lang.Integer macao;

    private java.lang.Integer taiwan;

    private java.lang.Integer abroad;

    private java.lang.Integer total;

    private java.lang.String createUser;

    private java.util.Calendar createTime;

    private java.lang.String updateUser;

    private java.util.Calendar updateTime;

    public HotelEstimate() {
    }

    public HotelEstimate(
           java.lang.String id,
           java.lang.String countryCode,
           java.lang.Integer year,
           java.lang.Integer month,
           java.lang.Integer type,
           java.lang.Integer domestic,
           java.lang.Integer foreigner,
           java.lang.Integer hongkong,
           java.lang.Integer macao,
           java.lang.Integer taiwan,
           java.lang.Integer abroad,
           java.lang.Integer total,
           java.lang.String createUser,
           java.util.Calendar createTime,
           java.lang.String updateUser,
           java.util.Calendar updateTime) {
           this.id = id;
           this.countryCode = countryCode;
           this.year = year;
           this.month = month;
           this.type = type;
           this.domestic = domestic;
           this.foreigner = foreigner;
           this.hongkong = hongkong;
           this.macao = macao;
           this.taiwan = taiwan;
           this.abroad = abroad;
           this.total = total;
           this.createUser = createUser;
           this.createTime = createTime;
           this.updateUser = updateUser;
           this.updateTime = updateTime;
    }


    /**
     * Gets the id value for this HotelEstimate.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this HotelEstimate.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the countryCode value for this HotelEstimate.
     * 
     * @return countryCode
     */
    public java.lang.String getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this HotelEstimate.
     * 
     * @param countryCode
     */
    public void setCountryCode(java.lang.String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the year value for this HotelEstimate.
     * 
     * @return year
     */
    public java.lang.Integer getYear() {
        return year;
    }


    /**
     * Sets the year value for this HotelEstimate.
     * 
     * @param year
     */
    public void setYear(java.lang.Integer year) {
        this.year = year;
    }


    /**
     * Gets the month value for this HotelEstimate.
     * 
     * @return month
     */
    public java.lang.Integer getMonth() {
        return month;
    }


    /**
     * Sets the month value for this HotelEstimate.
     * 
     * @param month
     */
    public void setMonth(java.lang.Integer month) {
        this.month = month;
    }


    /**
     * Gets the type value for this HotelEstimate.
     * 
     * @return type
     */
    public java.lang.Integer getType() {
        return type;
    }


    /**
     * Sets the type value for this HotelEstimate.
     * 
     * @param type
     */
    public void setType(java.lang.Integer type) {
        this.type = type;
    }


    /**
     * Gets the domestic value for this HotelEstimate.
     * 
     * @return domestic
     */
    public java.lang.Integer getDomestic() {
        return domestic;
    }


    /**
     * Sets the domestic value for this HotelEstimate.
     * 
     * @param domestic
     */
    public void setDomestic(java.lang.Integer domestic) {
        this.domestic = domestic;
    }


    /**
     * Gets the foreigner value for this HotelEstimate.
     * 
     * @return foreigner
     */
    public java.lang.Integer getForeigner() {
        return foreigner;
    }


    /**
     * Sets the foreigner value for this HotelEstimate.
     * 
     * @param foreigner
     */
    public void setForeigner(java.lang.Integer foreigner) {
        this.foreigner = foreigner;
    }


    /**
     * Gets the hongkong value for this HotelEstimate.
     * 
     * @return hongkong
     */
    public java.lang.Integer getHongkong() {
        return hongkong;
    }


    /**
     * Sets the hongkong value for this HotelEstimate.
     * 
     * @param hongkong
     */
    public void setHongkong(java.lang.Integer hongkong) {
        this.hongkong = hongkong;
    }


    /**
     * Gets the macao value for this HotelEstimate.
     * 
     * @return macao
     */
    public java.lang.Integer getMacao() {
        return macao;
    }


    /**
     * Sets the macao value for this HotelEstimate.
     * 
     * @param macao
     */
    public void setMacao(java.lang.Integer macao) {
        this.macao = macao;
    }


    /**
     * Gets the taiwan value for this HotelEstimate.
     * 
     * @return taiwan
     */
    public java.lang.Integer getTaiwan() {
        return taiwan;
    }


    /**
     * Sets the taiwan value for this HotelEstimate.
     * 
     * @param taiwan
     */
    public void setTaiwan(java.lang.Integer taiwan) {
        this.taiwan = taiwan;
    }


    /**
     * Gets the abroad value for this HotelEstimate.
     * 
     * @return abroad
     */
    public java.lang.Integer getAbroad() {
        return abroad;
    }


    /**
     * Sets the abroad value for this HotelEstimate.
     * 
     * @param abroad
     */
    public void setAbroad(java.lang.Integer abroad) {
        this.abroad = abroad;
    }


    /**
     * Gets the total value for this HotelEstimate.
     * 
     * @return total
     */
    public java.lang.Integer getTotal() {
        return total;
    }


    /**
     * Sets the total value for this HotelEstimate.
     * 
     * @param total
     */
    public void setTotal(java.lang.Integer total) {
        this.total = total;
    }


    /**
     * Gets the createUser value for this HotelEstimate.
     * 
     * @return createUser
     */
    public java.lang.String getCreateUser() {
        return createUser;
    }


    /**
     * Sets the createUser value for this HotelEstimate.
     * 
     * @param createUser
     */
    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }


    /**
     * Gets the createTime value for this HotelEstimate.
     * 
     * @return createTime
     */
    public java.util.Calendar getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this HotelEstimate.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Calendar createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the updateUser value for this HotelEstimate.
     * 
     * @return updateUser
     */
    public java.lang.String getUpdateUser() {
        return updateUser;
    }


    /**
     * Sets the updateUser value for this HotelEstimate.
     * 
     * @param updateUser
     */
    public void setUpdateUser(java.lang.String updateUser) {
        this.updateUser = updateUser;
    }


    /**
     * Gets the updateTime value for this HotelEstimate.
     * 
     * @return updateTime
     */
    public java.util.Calendar getUpdateTime() {
        return updateTime;
    }


    /**
     * Sets the updateTime value for this HotelEstimate.
     * 
     * @param updateTime
     */
    public void setUpdateTime(java.util.Calendar updateTime) {
        this.updateTime = updateTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HotelEstimate)) return false;
        HotelEstimate other = (HotelEstimate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.countryCode==null && other.getCountryCode()==null) || 
             (this.countryCode!=null &&
              this.countryCode.equals(other.getCountryCode()))) &&
            ((this.year==null && other.getYear()==null) || 
             (this.year!=null &&
              this.year.equals(other.getYear()))) &&
            ((this.month==null && other.getMonth()==null) || 
             (this.month!=null &&
              this.month.equals(other.getMonth()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.domestic==null && other.getDomestic()==null) || 
             (this.domestic!=null &&
              this.domestic.equals(other.getDomestic()))) &&
            ((this.foreigner==null && other.getForeigner()==null) || 
             (this.foreigner!=null &&
              this.foreigner.equals(other.getForeigner()))) &&
            ((this.hongkong==null && other.getHongkong()==null) || 
             (this.hongkong!=null &&
              this.hongkong.equals(other.getHongkong()))) &&
            ((this.macao==null && other.getMacao()==null) || 
             (this.macao!=null &&
              this.macao.equals(other.getMacao()))) &&
            ((this.taiwan==null && other.getTaiwan()==null) || 
             (this.taiwan!=null &&
              this.taiwan.equals(other.getTaiwan()))) &&
            ((this.abroad==null && other.getAbroad()==null) || 
             (this.abroad!=null &&
              this.abroad.equals(other.getAbroad()))) &&
            ((this.total==null && other.getTotal()==null) || 
             (this.total!=null &&
              this.total.equals(other.getTotal()))) &&
            ((this.createUser==null && other.getCreateUser()==null) || 
             (this.createUser!=null &&
              this.createUser.equals(other.getCreateUser()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.updateUser==null && other.getUpdateUser()==null) || 
             (this.updateUser!=null &&
              this.updateUser.equals(other.getUpdateUser()))) &&
            ((this.updateTime==null && other.getUpdateTime()==null) || 
             (this.updateTime!=null &&
              this.updateTime.equals(other.getUpdateTime())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getCountryCode() != null) {
            _hashCode += getCountryCode().hashCode();
        }
        if (getYear() != null) {
            _hashCode += getYear().hashCode();
        }
        if (getMonth() != null) {
            _hashCode += getMonth().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getDomestic() != null) {
            _hashCode += getDomestic().hashCode();
        }
        if (getForeigner() != null) {
            _hashCode += getForeigner().hashCode();
        }
        if (getHongkong() != null) {
            _hashCode += getHongkong().hashCode();
        }
        if (getMacao() != null) {
            _hashCode += getMacao().hashCode();
        }
        if (getTaiwan() != null) {
            _hashCode += getTaiwan().hashCode();
        }
        if (getAbroad() != null) {
            _hashCode += getAbroad().hashCode();
        }
        if (getTotal() != null) {
            _hashCode += getTotal().hashCode();
        }
        if (getCreateUser() != null) {
            _hashCode += getCreateUser().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getUpdateUser() != null) {
            _hashCode += getUpdateUser().hashCode();
        }
        if (getUpdateTime() != null) {
            _hashCode += getUpdateTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HotelEstimate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "HotelEstimate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("year");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("month");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Month"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domestic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Domestic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreigner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Foreigner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hongkong");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Hongkong"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("macao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Macao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taiwan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Taiwan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abroad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Abroad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UpdateUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UpdateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
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
