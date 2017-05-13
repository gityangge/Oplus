package cn.ac.yangge.pojo;

public class ItemLoveDetail {
	private int loveDetailID;
	private String uniteID="";
	private int itemID=0;
	private long loveTime;
	public int getLoveDetailID() {
		return loveDetailID;
	}
	public void setLoveDetailID(int loveDetailID) {
		this.loveDetailID = loveDetailID;
	}
	public String getUniteID() {
		return uniteID;
	}
	public void setUniteID(String uniteID) {
		this.uniteID = uniteID;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public long getLoveTime() {
		return loveTime;
	}
	public void setLoveTime(long loveTime) {
		this.loveTime = loveTime;
	}
}
