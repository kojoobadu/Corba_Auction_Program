public class Item {
	private String itemName;
	private String itemDescription;
	private int itemPrice;
	private String highestBidder = "None";
	boolean sold;
	
	
	public Item(String itemName, String itemDescription, int itemInitialPrice) {
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemInitialPrice;
		this.sold = false;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(String ID) {
		this.highestBidder = ID;
	}
	
	public String sell(){
		this.sold = true;
		return "Sold to "+ getHighestBidder();
	}

	public boolean isSold() {
		return sold;
	}
	
	public boolean bid( int amount){
		if(amount > getItemPrice()){
			setItemPrice(amount);
			return true;
		}
		return false;
	}
	
}
	