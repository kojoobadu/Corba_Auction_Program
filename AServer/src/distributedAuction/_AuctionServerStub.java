package distributedAuction;


/**
* distributedAuction/_AuctionServerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* Wednesday, March 8, 2017 4:04:33 PM CST
*/

public class _AuctionServerStub extends org.omg.CORBA.portable.ObjectImpl implements distributedAuction.AuctionServer
{

  public String subscribe (String userID, distributedAuction.AuctionClient c, int clientType) throws distributedAuction.NameAlreadyUsed
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("subscribe", true);
                $out.write_string (userID);
                distributedAuction.AuctionClientHelper.write ($out, c);
                $out.write_long (clientType);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:distributedAuction/NameAlreadyUsed:1.0"))
                    throw distributedAuction.NameAlreadyUsedHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return subscribe (userID, c, clientType        );
            } finally {
                _releaseReply ($in);
            }
  } // subscribe

  public void unsubscribe (String id, int clientType) throws distributedAuction.UnknownID
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("unsubscribe", true);
                $out.write_string (id);
                $out.write_long (clientType);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:distributedAuction/UnknownID:1.0"))
                    throw distributedAuction.UnknownIDHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                unsubscribe (id, clientType        );
            } finally {
                _releaseReply ($in);
            }
  } // unsubscribe

  public void comment (String id, String text) throws distributedAuction.UnknownID
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("comment", true);
                $out.write_string (id);
                $out.write_string (text);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:distributedAuction/UnknownID:1.0"))
                    throw distributedAuction.UnknownIDHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                comment (id, text        );
            } finally {
                _releaseReply ($in);
            }
  } // comment

  public boolean isAuctionEmpty ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("isAuctionEmpty", true);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return isAuctionEmpty (        );
            } finally {
                _releaseReply ($in);
            }
  } // isAuctionEmpty

  public void setID (String id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("setID", true);
                $out.write_string (id);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                setID (id        );
            } finally {
                _releaseReply ($in);
            }
  } // setID

  public void viewHighestBidder (String ID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("viewHighestBidder", true);
                $out.write_string (ID);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                viewHighestBidder (ID        );
            } finally {
                _releaseReply ($in);
            }
  } // viewHighestBidder

  public void offer (String ID, String name, String description, int price)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("offer", true);
                $out.write_string (ID);
                $out.write_string (name);
                $out.write_string (description);
                $out.write_long (price);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                offer (ID, name, description, price        );
            } finally {
                _releaseReply ($in);
            }
  } // offer

  public void bid (String ID, int amount)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("bid", true);
                $out.write_string (ID);
                $out.write_long (amount);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                bid (ID, amount        );
            } finally {
                _releaseReply ($in);
            }
  } // bid

  public void viewAuctionStatus (String id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("viewAuctionStatus", true);
                $out.write_string (id);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                viewAuctionStatus (id        );
            } finally {
                _releaseReply ($in);
            }
  } // viewAuctionStatus

  public void viewBidStatus (String id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("viewBidStatus", true);
                $out.write_string (id);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                viewBidStatus (id        );
            } finally {
                _releaseReply ($in);
            }
  } // viewBidStatus

  public String sell ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sell", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return sell (        );
            } finally {
                _releaseReply ($in);
            }
  } // sell

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:distributedAuction/AuctionServer:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _AuctionServerStub
