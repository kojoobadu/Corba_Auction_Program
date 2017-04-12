import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import distributedAuction.AuctionClient;
import distributedAuction.AuctionServerPOA;
import distributedAuction.NameAlreadyUsed;
import distributedAuction.UnknownID;

public class AuctionServerObject extends AuctionServerPOA{
	
	final static boolean busy = false;
	final static boolean free = true;
	private boolean auctionState = free;
	private Seller currentClient;
	private String sellID = "";
	private String ID;
	private Item clientItem = null;
	private Item currentItem = null;
	private boolean offer = false;
	private boolean bid = false;
	
	protected class Seller{
		public String userID;
		public int clientType;
		public distributedAuction.AuctionClient auctionclient;
		public Seller(String ID, distributedAuction.AuctionClient auctionclient){
			this.userID = ID;
			this.auctionclient = auctionclient;
		}
	}
	
	protected class Bidders {
		public distributedAuction.AuctionClient auctionclient;
		public String userID;
		public int clientType;
		public Bidders(String ID, distributedAuction.AuctionClient auctionclient) {
			this.auctionclient = auctionclient;
			this.userID = ID;
		}
	}
	
	protected Map<String, Bidders> clients = new HashMap<String, Bidders>();
	protected Map<String, Seller> sellers = new HashMap<String, Seller>();
	protected List<String> userIDs = new Vector<String>();

	@Override
	public String subscribe(String ID, distributedAuction.AuctionClient c, int clientType)
			throws distributedAuction.NameAlreadyUsed {
			if (userIDs.contains(ID)) throw new distributedAuction.NameAlreadyUsed();
			userIDs.add(ID);
			String id = UUID.randomUUID().toString();
			if(clientType == 1){
				auctionState = busy;
				setID(ID);
				System.out.println("Bidder subscribe: " + ID + " -> " + id);
				clients.put(id, new Bidders(ID, c));
			}
			else if(clientType == 2){
				setID(ID);
				System.out.println("Seller subscribe: " + ID + " -> " + id);
				currentClient = new Seller(ID, c);
				sellers.put(id, new Seller(ID, c));
			}
			return id;
		}

	@Override
	public void unsubscribe(String id, int clientType) throws distributedAuction.UnknownID {
		if(clientType == 1){
			Bidders c = clients.remove(id);
			if (c == null) throw new distributedAuction.UnknownID();
			userIDs.remove(c.userID);
		}
		else if(clientType == 2){
			Seller s = sellers.remove(id);
			if (s == null) throw new distributedAuction.UnknownID();
			userIDs.remove(s.userID);
		}
		System.out.println("unsubscribe: " + id);
		
	}

	@Override
	public void comment(String id, String text) throws distributedAuction.UnknownID {
		Bidders current = clients.get(id);
		current.auctionclient.update(id, text);
	}

	@Override
	public boolean isAuctionEmpty() {
		return auctionState;
	}

	@Override
	public void setID(String id) {
		this.ID = id;
	}

	@Override
	public void viewHighestBidder(String iden) {
		Seller current = sellers.get(iden);
		Bidders currentb = clients.get(iden);
		if( current != null){
			current.auctionclient.update(ID, currentItem.getHighestBidder());
		}
		else if ( currentb != null ){
			currentb.auctionclient.update(currentb.userID, currentItem.getHighestBidder());
		}	
	}

	@Override
	public void offer(String ID, String name, String description, int price) {
		if(isAuctionEmpty()){
			clientItem = new Item(name, description, price);
			currentItem = clientItem;
			offer = true;
			auctionState = busy;
			System.out.println("Offer Successfully place!");
			System.out.println(toString());
		}
		else{
			System.out.println("Error creating offer.");
		}
	}

	@Override
	public void bid(String ID, int amount) {
		Bidders current = clients.get(ID);
		if(offer){
			System.out.println("The current item for sale is " + toString() + "\n");
			if(currentItem.bid(amount)){
			   sellID = ID;
			   currentItem.setHighestBidder("<"+current.userID+"> ID : "+ ID);
			   System.out.println("Highest Bidder: "+ currentItem.getHighestBidder());
			   current.auctionclient.update(ID, "You are currently the highest bidder\n");
			   bid = true;
			}
			else if(!currentItem.bid(amount)){
				current.auctionclient.update(ID, "The highest bid on "+ currentItem.getItemName() + " is " + currentItem.getItemPrice());
				System.out.println("The highest bid on "+ currentItem.getItemName() + " is " + currentItem.getItemPrice());
			}
		}
		else{
			System.out.println("The highest bid on "+ currentItem.getItemName() + " is " + currentItem.getItemPrice());
		}
	}

	@Override
	public void viewAuctionStatus(String id){
		Seller current = sellers.get(id);
		Bidders currentb = clients.get(id);
		if( current != null){
			if( isAuctionEmpty()){
				current.auctionclient.update(current.userID, "Auction is currently Empty\n");
			}
			else{
				current.auctionclient.update(current.userID,"Current item for sale    : " + currentItem.getItemName());
				current.auctionclient.update(current.userID,"Current item descirption       : " + currentItem.getItemDescription());
				current.auctionclient.update(current.userID,"Price of current item for sale : " + currentItem.getItemPrice());
			}
		}
		else if ( currentb != null ){
			if( isAuctionEmpty()){
				currentb.auctionclient.update(currentb.userID, "Auction is currently Empty\n");
			}
			else{
				currentb.auctionclient.update(currentb.userID,"Current item for sale    : " + currentItem.getItemName());
				currentb.auctionclient.update(currentb.userID,"Current item descirption       : " + currentItem.getItemDescription());
				currentb.auctionclient.update(currentb.userID,"Price of current item for sale : " + currentItem.getItemPrice());
			}
		}
		
		
	}

	@Override
	public void viewBidStatus(String id) {
		Bidders current = clients.get(id);
		if (!isAuctionEmpty() && currentItem.isSold() == false){
			current.auctionclient.update(id,"The current highest bidder : "+ currentItem.getHighestBidder());
			System.out.println("The current highest bidder : "+ currentItem.getHighestBidder());
		}
		else{
			current.auctionclient.update(id,"Operation Failed");
		}
	}
	
	public String toString(){
		return currentItem.getItemName() + "|" + currentItem.getItemDescription() + "|" + currentItem.getItemPrice() + "|" + currentItem.isSold();
	}
	
	@Override
	public String sell(){
		if(bid){
			String id = sellID;
			System.out.println(id+"\n");
			Bidders highRoller = clients.get(id);
			String name = highRoller.userID;
			highRoller.auctionclient.update(id, "Congratulations, you just won the bidding war\nYou now own "+ currentItem.getItemName());
			currentItem.sold = true;
			highRoller.auctionclient.update(highRoller.userID, toString());
			clients.remove(id);
			for ( Bidders to : clients.values()){
				to.auctionclient.update(name, "Won the auction\nAuction is over!");
			}
			currentItem = null;
			auctionState = free;
			offer = false;
			bid = false;
			return "Sold";
		}
		return "Cannot Sell";
	}

}
