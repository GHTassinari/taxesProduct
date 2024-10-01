import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Taxes {
	private Double IPI;
	private Double ICMS;
	private Double ISS;
	
	public Double getIPI() {
		return IPI;
	}
	public void setIPI(Product product, Boolean taxExemption, Double shippingCost, Double insuranceCost) {
		if(taxExemption == true) {
			System.out.println("---------------------------------");
			System.out.println("The product is exempt from IPI, so it doesn't have any value");
			IPI = 0.0;
		}else {
			IPI = (product.getProductValue()+shippingCost+insuranceCost)*0.15;
			System.out.println("---------------------------------");
			System.out.printf("O valor do IPI é: %.2f%n", IPI);
		}
	}
	public Double getICMS() {
		return ICMS;
	}
	public void setICMS(Product product, Scanner scanner) {
		final Map<String, Integer> states = new HashMap<>();
		
		final double[][] ICMSTable = {
		        // AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PE, PI, PR, RJ, RN, RS, RO, RR, SC, SE, SP, TO
		        {17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // AC
		        {12, 18, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 4}, // AL
		        {12, 18, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 4}, // AM
		        {12, 18, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 4}, // AP
		        {12, 18, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 4}, // BA
		        {12, 18, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 4}, // CE
		        {12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 4}, // DF
		        {12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // ES
		        {12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // GO
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // MA
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // MT
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // MS
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // MG
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // PA
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // PB
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // PE
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}, // PI
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 12, 17.5, 12, 12, 12, 12, 4}, // PR
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 12, 17.5, 12, 12, 12, 12, 4}, // RJ
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 12, 17, 12, 12, 12, 12, 4}, // RN
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 12, 17, 12, 12, 12, 12, 4}, // RS
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 17, 12, 12, 12, 12, 4}, // RO
		        {17, 18, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 18, 18, 12, 12, 12, 4}, // RR
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 4}, // SC
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 18, 12, 4}, // SE
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 18, 4}, // SP
		        {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 17, 12, 12, 12, 12, 4}  // TO
		    };
		
	    String[] statesAcronyms = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RS", "RO", "RR", "SC", "SE", "SP", "TO"};
	    
	    for (int i = 0; i < statesAcronyms.length; i++) {
	        states.put(statesAcronyms[i], i);
	    }

		
			System.out.print("Inform the state of origin (Acronym): ");
			String origin = scanner.nextLine().toUpperCase();

			System.out.print("Inform the state of destination (Acronym): ");
			String destination = scanner.nextLine().toUpperCase();
			
	        if (!states.containsKey(origin) || !states.containsKey(destination)) {
	            System.out.println("State of origin, or destination, is invalid!");
	            setICMS(product, scanner);
	        }
	        
	        int originIndex = states.get(origin);
	        int destinationIndex = states.get(destination);
	        
	        Double taxRate = ICMSTable[originIndex][destinationIndex];
	        
	        ICMS = product.getProductValue()*(taxRate/100);
	        
	        System.out.println("---------------------------------");
	        System.out.printf("The ICMS value is: %.2f%n", ICMS);
	}
	
	public Double getISS() {
		return ISS;
	}
	
	public void setISS(Product product, Scanner scanner) {
		System.out.println("Inform the tax rate, from 0 to 100, of your ISS. Based on your product(or service) and municipality");
		Double ISSValue = scanner.nextDouble();
		scanner.nextLine();
		if(ISSValue <= 100 && ISSValue >= 0) {
			ISS = product.getProductValue()*(ISSValue/100);
			System.out.println("---------------------------------");
			System.out.printf("The ISS value is: %.2f%n", ISS);
		} else {
			System.out.println("---------------------------------");
			System.out.println("Please inform a value between 0 and 100");
			setISS(product, scanner);
		}
	}
	
	public Boolean federalTaxExempt(Scanner scanner){
			System.out.println("Is the product exempt from federal taxes? (Y/N)");
			String federalTaxAnswer = scanner.nextLine();
			if(federalTaxAnswer.equalsIgnoreCase("Y")) {
				return true;
			} else if (federalTaxAnswer.equalsIgnoreCase("N")) {
				return false;
			} else {
				System.out.println("---------------------------------");
				System.out.println("Please, inform Y to yes, and N to no, no other characters will be allowed. ");
				System.out.println("---------------------------------");
				return federalTaxExempt(scanner);
			}
	}
}
