package distributedAuction;

/**
* distributedAuction/AuctionServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* Wednesday, March 8, 2017 4:04:33 PM CST
*/

public final class AuctionServerHolder implements org.omg.CORBA.portable.Streamable
{
  public distributedAuction.AuctionServer value = null;

  public AuctionServerHolder ()
  {
  }

  public AuctionServerHolder (distributedAuction.AuctionServer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = distributedAuction.AuctionServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    distributedAuction.AuctionServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return distributedAuction.AuctionServerHelper.type ();
  }

}
