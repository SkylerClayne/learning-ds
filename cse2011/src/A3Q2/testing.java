package A3Q2;

public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Integer[] a1 = { 1, 3, 4, 5 };
		Integer[] a2 = { 2, 6, 7, 8 };
		Integer kth;
		Integer k = 10;

		try {
			SortedArrayPair<Integer> myPair = new SortedArrayPair<Integer>(a1,
					a2);
			kth = myPair.kthSmallestOfUnion(k);
			System.out.println(kth.toString());
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnmatchedArrayPairException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RankOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
