import java.util.Scanner;

public class Main {
	public static void main(String []args) {
		Product product = new Product();
		Taxes taxes = new Taxes();
		Double answerShippingCost = null;
		Double answerInsurance = null;
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Inform the product name: ");
			String productName = scanner.nextLine();
			
			System.out.println("Inform the product value: ");
			Double productValue = scanner.nextDouble();
			scanner.nextLine();
			
			product.setProduct(productName, productValue);
			
			Boolean answerTaxExemption = taxes.federalTaxExempt(scanner);
			
			if(answerTaxExemption == true) {
				answerShippingCost = product.shippingCost(scanner);		
				answerInsurance = product.productInsurance(scanner);
				taxes.setIPI(product, answerTaxExemption, answerShippingCost, answerInsurance);
			}else if(answerTaxExemption == false){
				answerShippingCost = product.shippingCost(scanner);		
				answerInsurance = product.productInsurance(scanner);
				taxes.setIPI(product, answerTaxExemption, answerShippingCost, answerInsurance);
			}
			
			taxes.setICMS(product, scanner);
			
			taxes.setISS(product, scanner);
			
			product.setContributionMargin(scanner);
			
			product.report(product, taxes, answerInsurance, answerShippingCost);
		}
	}
}
