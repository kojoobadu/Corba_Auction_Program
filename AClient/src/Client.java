import java.io.*;
import java.util.Scanner;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import distributedAuction.*;

import org.omg.CosNaming.*;

public class Client implements Runnable {
	static AuctionServer auctionserver;
	static Scanner c = null;
	static String name = "";
	static int clientType = 0;
	static String selectedOption = "";
	static boolean state = true;
	static String id = "";
	static Thread t1 = null;
	static Thread t2 = null;
	public void run() {
		try {
			// getting reference to POA
			org.omg.CORBA.Object obj =
			orb.resolve_initial_references("RootPOA");
			POA rootpoa = POAHelper.narrow(obj);
			// getting reference to POA manager
			POAManager manager = rootpoa.the_POAManager();
			// activating manager
			manager.activate();
			orb.run();
		} catch (Exception e) {}
	}

	protected static ORB orb;

	public static void main(String args[]) {
		try {
			// initializing ORB
			orb = ORB.init(args,null);
			// getting NameService
			org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef =
			org.omg.CosNaming.NamingContextExtHelper.narrow(obj);

			// resolving servant name
			obj = ncRef.resolve_str("client");
			auctionserver = AuctionServerHelper.narrow(obj);

			// creating servant
			AuctionClientObject ac = new AuctionClientObject();
			// connecting servant to ORB
			AuctionClient auctionclient = ac._this(orb);
			c = new Scanner(System.in);
			System.out.println("Enter:\n1 for Bidder\n2 for Seller\n");
			selectedOption = c.nextLine();
			while(!selectedOption.equals("0")){

				//Bidder
				if(selectedOption.equals("1")){
					clientType = 1;
					System.out.print("Enter your name\n");
					name = c.nextLine();
					id = auctionserver.subscribe(name, auctionclient, clientType);
					bidderInterface();
					System.out.println("Enter:\n1 for Bidder\n2 for Seller\n");
					selectedOption = c.nextLine();
				}

				//Seller
				else if( selectedOption.equals("2")){
					clientType = 2;
					if(!auctionserver.isAuctionEmpty()){
						System.out.println("Auction is currently busy please try again in a few minutes");
						break;
					}
					else if( auctionserver.isAuctionEmpty()){
						System.out.print("Enter your name\n");
						name = c.nextLine();
						id = auctionserver.subscribe(name, auctionclient, clientType);
						auctionserver.setID(name);
					}
					sellerInterface();
					System.out.println("Enter:\n1 for Bidder\n2 for Seller\n");
					selectedOption = c.nextLine();
				}

				System.out.println("Enter:\n1 for Bidder\n2 for Seller\n");
				selectedOption = c.nextLine();
			}
		}
			catch(Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static void sellerInterface(){
		System.out.println("Welcome to the Seller Interface...\n");
		System.out.println("Seller Menu: \n1. Check if auction is empty\n2. Offer item for sale\n3. View high bidder\n4. View auction status\n5. Exit Seller Menu\n");
		selectedOption = c.nextLine();
		while(!selectedOption.equals("5")){
					//Check if auction is empty
					if(selectedOption.equals("1")){
								state = auctionserver.isAuctionEmpty();
								if(state){
									System.out.println("You can offer an item for sale");
								}
								else{
									System.out.println("The auction is busy. Try again in a few minutes");
								}

								System.out.println("Seller Menu: \n1. Check if auction is empty\n2. Offer item for sale\n3. View high bidder\n4. View auction status\n5. Exit Seller Menu\n");
								selectedOption = c.nextLine();
					}
					//Offer item for sale
					else if(selectedOption.equals("2")){
								System.out.println("Enter the name of the product\n");
								String pname = c.nextLine();
								System.out.println("Enter the description of the product\n");
								String description = c.nextLine();
								System.out.println("Enter the price of the product\n");
								int price = c.nextInt();
								auctionserver.offer(name, pname, description, price);
								System.out.println("Seller Menu: \n1. Check if auction is empty\n2. Offer item for sale\n3. View high bidder\n4. View auction status\n5. Exit Seller Menu\n");
								System.out.println(selectedOption);
								selectedOption = c.nextLine();
								selectedOption = c.nextLine();

					}
					else if(selectedOption.equals("3")){
								t2 = new Thread(new Client());
								try {
									t2.start();
									auctionserver.viewHighestBidder(id);
								} finally {

								}
								System.out.println("Seller Menu: \n1. Check if auction is empty\n2. Offer item for sale\n3. View high bidder\n4. View auction status\n5. Exit Seller Menu\n");
								selectedOption = c.nextLine();
								selectedOption = c.nextLine();
					}
					else if(selectedOption.equals("4")){
								t2 = new Thread(new Client());
								try {
									t2.start();
									auctionserver.viewAuctionStatus(id);
								} finally {

								}
								System.out.println("Seller Menu: \n1. Check if auction is empty\n2. Offer item for sale\n3. View high bidder\n4. View auction status\n5. Exit Seller Menu\n");
								selectedOption = c.nextLine();
								selectedOption = c.nextLine();
					}
					else if(selectedOption.equals("6")){
						System.out.println(auctionserver.sell());
						System.out.println("Seller Menu: \n1. Check if auction is empty\n2. Offer item for sale\n3. View high bidder\n4. View auction status\n5. Exit Seller Menu\n");
						selectedOption = c.nextLine();
						selectedOption = c.nextLine();
					}
		}
		System.out.println("Exiting Seller Interface...\n");
	}

	public static void bidderInterface() throws UnknownID, InterruptedException{
		System.out.println("Welcome to the Bidder Interface...\n");
		System.out.println("Bidder Menu: \n1. Bid\n2. View auction status\n3. View bid status\n4. Exit Bidder Menu\n");
		selectedOption = c.nextLine();
		while(!selectedOption.equals("4")){
			//Place a bid
			if(selectedOption.equals("1")){
				t1 = new Thread(new Client());
				try {
					t1.start();
						System.out.println("Enter amount to place bid\n");
						int bidAmount = c.nextInt();
						auctionserver.bid(id, bidAmount);
				} finally {

				}
				System.out.println("Bidder Menu: \n1. Bid\n2. View auction status\n3. View bid status\n4. Exit Bidder Menu\n");
				selectedOption = c.nextLine();
				selectedOption = c.nextLine();
			}
			//View Auction Status
			else if(selectedOption.equals("2")){
				t1 = new Thread(new Client());
				try {
					t1.start();
						auctionserver.viewAuctionStatus(id);
				} finally {

				}
				System.out.println("Bidder Menu: \n1. Bid\n2. View auction status\n3. View bid status\n4. Exit Bidder Menu\n");
				selectedOption = c.nextLine();
				selectedOption = c.nextLine();
			}
			else if(selectedOption.equals("3")){
				t1 = new Thread(new Client());
				try {
					t1.start();
						auctionserver.viewBidStatus(id);
				} finally {

				}
				System.out.println("Bidder Menu: \n1. Bid\n2. View auction status\n3. View bid status\n4. Exit Bidder Menu\n");
				selectedOption = c.nextLine();
				selectedOption = c.nextLine();
			}
		}
		System.out.println("Exiting Bidder Interface...\n");
	}
}
