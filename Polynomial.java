import java.util.*;
import java.io.*;

public class Polynomial {
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0;
		exponents = new int[1];
		exponents[0] = 0;
	}

	public Polynomial(double[] c,int[] e) {
		coefficients = new double[c.length];
		exponents = new int[e.length];
		for (int i = 0; i < c.length; i++)
		{
			coefficients[i] = c[i];
			exponents[i] = e[i];
		}
	}

	public Polynomial(HashMap<Integer, Double> A) {
		coefficients = new double[A.size()];
		exponents = new int[A.size()];
		int i = 0;
		for (int k : A.keySet()) {
			coefficients[i] = A.get(k);
			exponents[i] = k;
			i++;
		}
	}
	
	public Polynomial(File A) throws IOException {
		Scanner scanner = new Scanner(A);
		String input = scanner.nextLine();
		// make splitting easier 
		input.replaceAll("-", "+-");
		String[] terms = input.split("\\+");  // special character in regex
		
		// for each term in the array, store it into HashMap
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		for (int i = 0; i < terms.length; i++)
		{
			String[] term = terms[i].split("x");
			double coef = Double.parseDouble(term[0]);
			int exp; 
			if (term.length == 1) {		// if there is no 'x', exp = 0 
				exp = 0;
			}
			else if (term[1] == "") {	// if there is nothing after x, exp = 1
				exp = 1;
			}
			else {
				exp = Integer.parseInt(term[1]);
			}
			// create a new pair for exp/coeff 
			map.put(exp, coef);
		}
		
		// turn them into arrays 
		coefficients = new double[map.size()];
		exponents = new int[map.size()];
		int i = 0;
		for (int k : map.keySet()) {
			coefficients[i] = map.get(k);
			exponents[i] = k;
			i++;
		}
		
		scanner.close();
	}

	public Polynomial add(Polynomial p) {
		// create a HashMap to store the exponent/coefficient pair 
		HashMap<Integer, Double> terms = new HashMap<Integer, Double>();
		
		// store each term in p in the HashMap
		for (int i = 0; i < p.coefficients.length; i++){
			terms.put(p.exponents[i], p.coefficients[i]);
		}
		// for each term in this (calling) add it to HashMap
		for (int j = 0; j < this.coefficients.length; j++) {
			double coef = this.coefficients[j];
			int exp = this.exponents[j];
			
			// if exponent exists, increment its value by coeff 
			if (terms.containsKey(exp)){
				terms.put(exp, terms.get(exp) + coef);
			}
			// otherwise, create a new pair for exp/coeff
			else {
				terms.put(exp, coef);
			}
		}
					
		// create & return new polynomial that has data from HashMap terms
		Polynomial newPol;
		newPol = new Polynomial(terms);
		return newPol;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < coefficients.length; i++)
		{
			result += (coefficients[i] * Math.pow(x, exponents[i]));
		}
		return result;
	}
	
	public boolean hasRoot(double n) {
		return evaluate(n) == 0;
	}

	public Polynomial multiply(Polynomial p) {
		// create a HashMap to store the exponent/coefficient pair 
		HashMap<Integer, Double> terms = new HashMap<Integer, Double>();
		
		// for each term in p, loop through terms in this (calling) 
		for (int i = 0; i < p.coefficients.length; i++){
			for (int j = 0; j < this.coefficients.length; j++){
				double coef = p.coefficients[i] * this.coefficients[j];
				int exp = p.exponents[i] + this.exponents[j];
				
				// if exponent exists, increment its value by coeff 
				if (terms.containsKey(exp)){
					terms.put(exp, terms.get(exp) + coef);
				}
				// otherwise, create a new pair for exp/coeff
				else {
					terms.put(exp, coef);
				}
			}
		}
		
		// create & return new polynomial that has data from HashMap terms
		Polynomial newPol;
		newPol = new Polynomial(terms);
		return newPol;
	}
	
	public void saveToFile(String filename) throws IOException {
		FileWriter myWriter = new FileWriter(filename);
		String terms = "";
		// loop through all the exponents 
		for (int i = 0; i < exponents.length; i++) {
			terms = terms.concat(String.valueOf(coefficients[i]));
			if (exponents[i] != 0) // if exp = 0, we don't write the x
			{
				terms = terms.concat("x");
				if (exponents[i] != 1) // if exp = 1, we don't write it
				{
					terms = terms.concat(String.valueOf(exponents[i]));
				}
			}
			if (i+1 < exponents.length) // + in between terms 
				terms = terms.concat("+");
		}
		// remove + when coef is negative 
		terms = terms.replaceAll("\\+-", "-");		// special character in regex
		
		myWriter.write(terms);
		
		myWriter.close();
	}
}