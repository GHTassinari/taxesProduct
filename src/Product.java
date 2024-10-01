import java.util.Scanner;

public class Product {
	private String productName;
	private Double productValue;
	private Double contributionMarginValue;
	private Double finalProductValue;


	public void setProduct(String productName, Double productValue) {
		this.productName = productName;
		this.productValue = productValue;
	}


	public String getProductName() {
		return productName;
	}


	public Double getProductValue() {
		return productValue;
	}
	
	public Double shippingCost(Scanner scanner){
			System.out.println("Does this product have a shipping cost? Answer with (Y/N)");
			String shippingCostAnswer = scanner.nextLine();
			
			if(shippingCostAnswer.equalsIgnoreCase("Y")) {
				System.out.println("---------------------------------");
				System.out.println("Now inform the product shipping cost:");
				Double productShippingCost = scanner.nextDouble();
				scanner.nextLine();
				return productShippingCost;
				
			} else if (shippingCostAnswer.equalsIgnoreCase("N")) {
				return 0.0;
			} else {
				System.out.println("---------------------------------");
				System.out.println("Please, inform Y to yes, and N to no, no other characters will be allowed. ");
				System.out.println("---------------------------------");
				return shippingCost(scanner);
			}
	}
	
	public Double productInsurance(Scanner scanner){
			System.out.println("Does this product have insurance? Answer with (Y/N)");
			String insuranceAnswer = scanner.nextLine();
			
			if(insuranceAnswer.equalsIgnoreCase("Y")) {
				System.out.println("---------------------------------");
				System.out.println("Now inform the product insurance cost:");
				Double productInsuranceCost = scanner.nextDouble();
				scanner.nextLine();
				return productInsuranceCost;
			} else if (insuranceAnswer.equalsIgnoreCase("N")) {
				return 0.0;
			} else {
				System.out.println("---------------------------------");
				System.out.println("Please, inform Y to yes, and N to no, no other characters will be allowed. ");
				System.out.println("---------------------------------");
				return productInsurance(scanner);
			}
	}
	
	public void setContributionMargin(Scanner scanner) {
		System.out.println("---------------------------------");
		System.out.println("Now, you will inform what amount of contribution margin you would want in this product, for example, the product price is 8R$");
		System.out.println("But the contribution margin value is R$2, so the final price of the product will be R$10");
		System.out.println("---------------------------------");
		System.out.println("Inform the contribution margin value:");
		contributionMarginValue = scanner.nextDouble();
		scanner.nextLine();
		if(contributionMarginValue < 0) {
			System.out.println("Please, inform a positive contribution margin.");
			contributionMarginValue = null;
			setContributionMargin(scanner);
		} 
		System.out.println("---------------------------------");
		System.out.printf("The contribution margin value is: %.2f%n", contributionMarginValue);
		
	}
	
	public Double getContributionMargin() {
		return contributionMarginValue;
	}
	
	public void report(Product product, Taxes taxes, Double insurance, Double shipping) {
		finalProductValue = (product.getProductValue()+insurance+shipping)+product.getContributionMargin()+taxes.getICMS()+taxes.getISS()+taxes.getIPI();
		System.out.println("---------------------------------");
		System.out.println("The product name is: " + product.getProductName());
		System.out.printf("The product value is: %.2f%n", product.getProductValue());
		System.out.printf("The product shipping cost is: %.2f%n", shipping);
		System.out.printf("The product insurance is: %.2f%n", insurance);
		System.out.printf("The product contribution margin is: %.2f%n", product.getContributionMargin());
		System.out.printf("The product IPI tax is: %.2f%n", taxes.getIPI());
		System.out.printf("The product ICMS tax is: %.2f%n", taxes.getICMS());
		System.out.printf("The product ISS tax is: %.2f%n", taxes.getISS());
		System.out.println("---------------------------------");
		System.out.printf("The product final value is: %.2f%n", finalProductValue);
	}
}
