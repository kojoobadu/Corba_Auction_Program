import distributedAuction.AuctionClientPOA;

public class AuctionClientObject extends AuctionClientPOA {

	
	public void update(String userID, String text) {
		System.out.println("<" + userID + "> " + text);
	}

}
