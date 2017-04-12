import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import distributedAuction.*;

import org.omg.CosNaming.*;

public class Server {
	public static void main(String args[]) {
		try{
			// initializing ORB
			ORB orb = ORB.init(args,null);

			// getting reference to POA
			org.omg.CORBA.Object obj = orb.resolve_initial_references("RootPOA");
			POA rootpoa = POAHelper.narrow(obj);
			// getting reference to POA manager
			POAManager manager = rootpoa.the_POAManager();
			// activating manager 
			manager.activate();

			// getting NameService
			obj = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = org.omg.CosNaming.NamingContextExtHelper.narrow(obj);

			// creating servant
			AuctionServerObject ao = new AuctionServerObject();
			// connecting servant to ORB 
			AuctionServer chatserver = ao._this(orb);
			// binding servant reference to NameService
			ncRef.rebind(ncRef.to_name("client"), chatserver);

			System.out.println("Object activated");
			// starting orb
			orb.run();

		} catch(Exception e) {
			e.printStackTrace(System.out);
		}
	}
}



