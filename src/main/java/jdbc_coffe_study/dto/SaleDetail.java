package jdbc_coffe_study.dto;

public class SaleDetail {
	private int salePrice;
	private int addTax;
	private int supplyValue;
	private int marginPrice;
	private int rank;
	
	public SaleDetail() {
		// TODO Auto-generated constructor stub
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getAddTax() {
		return addTax;
	}

	public void setAddTax(int addTax) {
		this.addTax = addTax;
	}

	public int getSupplyValue() {
		return supplyValue;
	}

	public void setSupplyValue(int supplyValue) {
		this.supplyValue = supplyValue;
	}

	public int getMarginPrice() {
		return marginPrice;
	}

	public void setMarginPrice(int marginPrice) {
		this.marginPrice = marginPrice;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	
	
	public SaleDetail(int salePrice, int addTax, int supplyValue, int marginPrice, int rank) {
		this.salePrice = salePrice;
		this.addTax = addTax;
		this.supplyValue = supplyValue;
		this.marginPrice = marginPrice;
		this.rank = rank;
	}

	
	
	@Override
	public String toString() {
		return String.format("SaleDetail [salePrice=%s, addTax=%s, supplyValue=%s, marginPrice=%s, rank=%s]", salePrice,
				addTax, supplyValue, marginPrice, rank);
	}
	
	
}
