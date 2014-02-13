package A1Q2;

import java.util.Arrays;

/**
 * Represents an integer integral image, which allows the user to query the mean
 * value of an arbitrary rectangular subimage in O(1) time. Uses O(n) memory,
 * where n is the number of pixels in the image.
 * 
 * @author jameselder
 */
public class IntegralImage {

	private final int[][] integralImage;
	private final int imageHeight; // height of image (first index)
	private final int imageWidth; // width of image (second index)

	/**
	 * Constructs an integral image from the given input image.
	 * 
	 * @author jameselder
	 * @param image
	 *            The image represented
	 * @throws InvalidImageException
	 *             Thrown if input array is not rectangular
	 */
	public IntegralImage(int[][] image) throws InvalidImageException {
		// implement this method.

		// Use loops to add elements and tabulate the Summed Area Table.
		for (int x = 0; x < image.length; x++) {
			int check = 1;

			// Check if image is a square.
			if (image[check++].length != image[check - 1].length) {
				throw new InvalidImageException();
			}
			for (int y = 0; y < image[0].length; y++) {

				if (x == 0 && y == 0) {
					// If you're at the first px in the image.
					image[x][y] = image[x][y];
				} else {
					// Any other px.
					if (x == 0 && y > 0) {
						// Tabulate first row.
						image[x][y] = image[x][y] + image[x][y - 1];
					} else if (y == 0 && x > 0) {
						// Tabulate second row.
						image[x][y] = image[x][y] + image[x - 1][y];
					} else {
						// Anywhere in the middle.
						image[x][y] = image[x][y] + image[x][y - 1]
								+ image[x - 1][y] - image[x - 1][y - 1];
					}
				}

			}

		}

		// Set the values of the height and width.
		this.imageHeight = image.length;
		this.imageWidth = image[0].length;
		this.integralImage = image;

	}

	/**
	 * Returns the mean value of the rectangular sub-image specified by the top,
	 * bottom, left and right parameters. The sub-image should include pixels in
	 * rows top and bottom and columns left and right. For example, top = 1,
	 * bottom = 2, left = 1, right = 2 specifies a 2 x 2 sub-image starting at
	 * (top, left) coordinate (1, 1).
	 * 
	 * @author jameselder
	 * @param top
	 *            top row of sub-image
	 * @param bottom
	 *            bottom row of sub-image
	 * @param left
	 *            left column of sub-image
	 * @param right
	 *            right column of sub-image
	 * @return
	 * @throws BoundaryViolationException
	 *             if image indices are out of range
	 * @throws NullSubImageException
	 *             if top > bottom or left > right
	 */
	public double meanSubImage(int top, int bottom, int left, int right)
			throws BoundaryViolationException, NullSubImageException {
		// implement this method

		// bigger than top or smaller than bottom means it's outside the frame.
		if (top > integralImage.length || bottom < 0) {
			throw new BoundaryViolationException();
		}

		// Bottom > top.
		if (bottom < top) {
			throw new NullSubImageException();
		}

		// Left > right, sub image means nothing.
		if (left > right) {
			throw new NullSubImageException();
		}

		// Creating a mean sum to tally.
		double mean = 0;

		// Determine the number of elements summed.
		double divisor = ((right - left) + 1) * ((bottom - top) + 1);

		// If you're at the first element in the sum.

		// Any other px.
		if (top == 0 && left == 0) {
			mean = integralImage[bottom][right];
		} else if (top == 0) {
			// if in the first row.
			mean = integralImage[bottom][right]
					- integralImage[bottom][left - 1];
		} else if (left == 0) {
			// if in the first column.
			mean = integralImage[bottom][right]
					- integralImage[top-1][right];
		} else {
			// Anywhere in the middle.
			mean = integralImage[bottom][right]
					+ integralImage[top - 1][left - 1]
					- integralImage[top - 1][right]
					- integralImage[bottom][left - 1];
		}

		// Calculate the average.
		mean = mean / divisor;

		return mean;
	}
}
