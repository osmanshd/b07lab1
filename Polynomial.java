public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0;
	}

	public Polynomial(double[] A) {
		coefficients = new double[A.length];
		for (int i = 0; i < A.length; i++)
		{
			coefficients[i] = A[i];
		}
	}

	public Polynomial add(Polynomial p) {
		Polynomial newPol;
		
		if (p.coefficients.length > this.coefficients.length)
		{
			newPol = new Polynomial(p.coefficients);
			for (int i = 0; i < this.coefficients.length; i++)
			{
				newPol.coefficients[i] += this.coefficients[i];
			}
		}
		else 
		{
			newPol = new Polynomial(this.coefficients);
			for (int i = 0; i < p.coefficients.length; i++)
			{
				newPol.coefficients[i] += p.coefficients[i];
			}
		}
		
		return newPol;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < coefficients.length; i++)
		{
			result += (coefficients[i] * Math.pow(x, i));
		}
		return result;
	}
	
	public boolean hasRoot(double n) {
		return evaluate(n) == 0;
	}
}