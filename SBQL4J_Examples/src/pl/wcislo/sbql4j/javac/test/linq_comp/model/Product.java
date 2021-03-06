package pl.wcislo.sbql4j.javac.test.linq_comp.model;

public class Product {
//	public Product(int unitsInStock, String productName, double unitPrice) {
//		this.unitsInStock = unitsInStock;
//		this.productName = productName;
//		this.unitPrice = unitPrice;
//	}
	
	

	public Product(int productID, String productName, String category,
			double unitPrice, int unitsInStock) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.category = category;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
	}



	public int productID;
	public String productName;
	public String category;
	public double unitPrice;
	public int unitsInStock;
	
	
	@Override
	public String toString() {
		return "Product[productID="+productID+", productName="+productName+", category="+category+", unitPrice="+unitPrice+", unitsInStock="+unitsInStock+"]";
	}
}