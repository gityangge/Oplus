package cn.ac.yangge.pojo;

public class Item {
	private int itemID;
	private String itemName="";
	private int itemType=0;
	private String itemContent="";//简介
	private int itemContentID=0;
	private String itemLeader="";
	private int itemTeam=-1;
	private int itemState=0;
	private String itemStateOther="";
	private int itemNeedMember=-1;
	private int itemCareNum=0;
	private int itemCareListID;
	private int itemLoveNum=0;
	private int itemLoveListID;
	private ItemMemberState itemMemberState;
	private long itemTime;
	public void initItem(){
		itemMemberState=new ItemMemberState();
	}
	public String getItemContent() {
		return itemContent;
	}
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
	public void changeItem(Item cItem){
		
	}
	public int getItemState() {
		return itemState;
	}
	public void setItemState(int itemState) {
		this.itemState = itemState;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public String getItemLeader() {
		return itemLeader;
	}
	public void setItemLeader(String itemLeader) {
		this.itemLeader = itemLeader;
	}
	public int getItemTeam() {
		return itemTeam;
	}
	public void setItemTeam(int itemTeam) {
		this.itemTeam = itemTeam;
	}
	public int getItemNeedMember() {
		return itemNeedMember;
	}
	public void setItemNeedMember(int itemNeedMember) {
		this.itemNeedMember = itemNeedMember;
	}
	public int getItemCareNum() {
		return itemCareNum;
	}
	public void setItemCareNum(int itemCareNum) {
		this.itemCareNum = itemCareNum;
	}
	public int getItemCareListID() {
		return itemCareListID;
	}
	public void setItemCareListID(int itemCareListID) {
		this.itemCareListID = itemCareListID;
	}
	public int getItemLoveNum() {
		return itemLoveNum;
	}
	public void setItemLoveNum(int itemLoveNum) {
		this.itemLoveNum = itemLoveNum;
	}
	public int getItemLoveListID() {
		return itemLoveListID;
	}
	public void setItemLoveListID(int itemLoveListID) {
		this.itemLoveListID = itemLoveListID;
	}
	public long getItemTime() {
		return itemTime;
	}
	public void setItemTime(long itemTime) {
		this.itemTime = itemTime;
	}
	public String getItemStateOther() {
		return itemStateOther;
	}
	public void setItemStateOther(String itemStateOther) {
		this.itemStateOther = itemStateOther;
	}
	public ItemMemberState getItemMemberState() {
		return itemMemberState;
	}
	public void setItemMemberState(ItemMemberState itemMemberState) {
		this.itemMemberState = itemMemberState;
	}
	public int getItemContentID() {
		return itemContentID;
	}
	public void setItemContentID(int itemContentID) {
		this.itemContentID = itemContentID;
	}
	
}
