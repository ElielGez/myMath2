package Ex1;

public class JSONDrawingConfig {
	private int Width;
	private int Height;
	private int Resolution;
	private double[] Range_X;
	private double[] Range_Y;

	public JSONDrawingConfig() {

	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public int getResolution() {
		return Resolution;
	}

	public void setResolution(int resolution) {
		Resolution = resolution;
	}

	public double[] getRange_X() {
		return Range_X;
	}

	public void setRange_X(double[] range_X) {
		Range_X = range_X;
	}

	public double[] getRange_Y() {
		return Range_Y;
	}

	public void setRange_Y(double[] range_Y) {
		Range_Y = range_Y;
	}

	/**
	 * Validate that the range is not null and his length is bigger than 1
	 * @param range
	 * @return
	 */
	private static boolean validateRange(double[] range) {
		return range != null && range.length >= 2;
	}

	
	/**
	 * Function to check that object of the config isn't missing any data
	 * @return
	 */
	public boolean validateObject() {
		return this.Width != 0 && this.Height != 0 && this.Resolution != 0 && validateRange(this.Range_X)
				&& validateRange(this.Range_Y);
	}
}
