public class Driver {
	public static void main(String [] args) throws IOException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		
		double [] c1 = {6,5};
		int [] e1 = {2, 1};
		Polynomial p1 = new Polynomial(c1, e1);
		
		double [] c2 = {-2,1,-9};
		int [] e2 = {0, 4, 1};
		Polynomial p2 = new Polynomial(c2, e2);
		
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		System.out.println(Arrays.toString(s.coefficients));
		System.out.println(Arrays.toString(s.exponents));
		System.out.println("-------------------------------------");
		
		Polynomial m = p1.multiply(p2);
		System.out.println("m(1) = " + m.evaluate(1));
		if(m.hasRoot(0))
			System.out.println("0 is a root of m");
		else
			System.out.println("0 is not a root of m");
		System.out.println(Arrays.toString(m.coefficients));
		System.out.println(Arrays.toString(m.exponents));
		System.out.println("-------------------------------------");
	}
}