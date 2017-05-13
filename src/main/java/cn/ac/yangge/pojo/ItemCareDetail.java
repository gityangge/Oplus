package cn.ac.yangge.pojo;

public class ItemCareDetail {
	private int careDetailID;
	private String uniteID;
	private int itemID;
	private long careTime;
	public int getCareDetailID() {
		return careDetailID;
	}
	public void setCareDetailID(int careDetailID) {
		this.careDetailID = careDetailID;
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
	public long getCareTime() {
		return careTime;
	}
	public void setCareTime(long careTime) {
		this.careTime = careTime;
	}
}
