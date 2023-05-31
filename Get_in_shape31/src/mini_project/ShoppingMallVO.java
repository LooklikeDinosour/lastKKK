package mini_project;

public class ShoppingMallVO {
	
	//멤버변수 정의
	private int itemNum;
	private String itemBrand;
	private String itemName;
	private int itemPoint;
	
	public ShoppingMallVO () {} // 생성자 두개 생성

	//생성자 정의
	public ShoppingMallVO(int itemNum, String itemBrand, String itemName, int itemPoint) {
		super();
		this.itemNum = itemNum;
		this.itemBrand = itemBrand;
		this.itemName = itemName;
		this.itemPoint = itemPoint;
	}

	//getter, setter
	
	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPoint() {
		return itemPoint;
	}

	public void setItemPoint(int itemPoint) {
		this.itemPoint = itemPoint;
	}
	
	
	
	

}
