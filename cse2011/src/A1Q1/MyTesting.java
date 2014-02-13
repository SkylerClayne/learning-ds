package A1Q1;

public class MyTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        SparseNumericVector X = new SparseNumericVector();

        X.add(new SparseNumericElement(150000, 3.1415));
        X.add(new SparseNumericElement(15, 3));
        X.add(new SparseNumericElement(1500, 3.14));
        
        // comment
        System.out.println(X.toString());
        System.out.println(X.getSize());
        System.out.println(X.getFirst().toString());
	}

}
