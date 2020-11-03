package com.neusoft.udolink.common.util;


public class CharConvert
{
  private String _sTargetCharset = "";
  private String _sSourceCharset = "ISO8859_1";
  public CharConvert(String strTargetCharset)
  {
    this._sTargetCharset = strTargetCharset;
  }
  
  public CharConvert(String strSourceCharset, String strTargetCharset)
  {
    this._sTargetCharset = strTargetCharset;
    this._sSourceCharset = strSourceCharset;
  }
  
  public String encode(String s)
  {
    try
    {
      return new String(s.getBytes(this._sSourceCharset), this._sTargetCharset);
    }
    catch (Exception e) {}
    return s;
  }
  
  public String decode(String s)
  {
    try
    {
      return new String(s.getBytes(this._sTargetCharset), this._sSourceCharset);
    }
    catch (Exception e) {}
    return s;
  }
  
  public String getTargetCharset()
  {
    return this._sTargetCharset;
  }
  
  public void setTargetCharset(String strTargetCharset)
  {
    this._sTargetCharset = strTargetCharset;
  }
  
  public String getSourceCharset()
  {
    return this._sSourceCharset;
  }
  
  public void setSourceCharset(String strSourceCharset)
  {
    this._sSourceCharset = strSourceCharset;
  }
  
  public boolean isEqual()
  {
    return this._sTargetCharset.trim().equalsIgnoreCase(this._sSourceCharset);
  }
  
}
