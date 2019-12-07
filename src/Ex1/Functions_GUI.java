package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import com.google.gson.Gson;

public class Functions_GUI implements functions {

	public static Color[] Colors = { Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN,
			Color.PINK };

	ArrayList<function> functions;

	public Functions_GUI() {
		functions = new ArrayList<function>();
	}

	@Override
	public int size() {
		return functions.size();
	}

	@Override
	public boolean isEmpty() {
		return functions.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return functions.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return functions.iterator();
	}

	@Override
	public Object[] toArray() {
		return functions.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return functions.toArray(a);
	}

	@Override
	public boolean add(function f) {
		return functions.add(f);
	}

	@Override
	public boolean remove(Object o) {
		return functions.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return functions.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return functions.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return functions.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return functions.retainAll(c);
	}

	@Override
	public void clear() {
		functions.clear();
	}

	public function get(int i) {
		return functions.get(i);
	}

	public String toString() {
		return functions.toString();
	}

	public String prepareStringToFile() {
		StringBuilder sb = new StringBuilder();
		Iterator<function> it = this.iterator();
		while (it.hasNext()) {
			function f = it.next();
			sb.append(f);
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				String[] functions = line.split("\n");
				for (int i = 0; i < functions.length; i++) {
					this.functions.add(new ComplexFunction().initFromString(functions[i]));
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("could not read file");
		}

	}

	@Override
	public void saveToFile(String file) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(new File(file));
			pw.write(prepareStringToFile());
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("could not write file");
		}

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		prepareDrawingCanvas(width, height, rx, ry);

		double maxX = rx.get_max(), minX = rx.get_min();
		double steps = (Math.abs((minX - maxX)) / resolution);
		Iterator<function> it = this.iterator();
		while (it.hasNext()) {
			StdDraw.setPenColor(Colors[new Random().nextInt(Colors.length)]);
			function f = it.next();
			// draw the functions by values
			for (double i = minX; i < maxX; i = i + steps) {
				StdDraw.line(i, f.f(i), i + steps, f.f(i + steps));
			}
		}
	}

	@Override
	public void drawFunctions(String json_file) {
		JSONDrawingConfig config = deserialJSONConfig(json_file);
		if (!config.validateObject())
			throw new ArithmeticException("Some data in JSONDrawingConfig object is missing");
		
		Range rx = new Range(config.getRange_X()[0],config.getRange_X()[1]);
		Range ry = new Range(config.getRange_Y()[0],config.getRange_Y()[1]);
		this.drawFunctions(config.getWidth(), config.getHeight(), rx, ry, config.getResolution());
	}

	private void prepareDrawingCanvas(int width, int height, Range rx, Range ry) {
		StdDraw.setCanvasSize(width, height);
		double maxY = ry.get_max(), minY = ry.get_min();
		double maxX = rx.get_max(), minX = rx.get_min();

		// rescale the coordinate system
		StdDraw.setXscale(minX - 0.3, maxX + 0.3);
		StdDraw.setYscale(minY - 0.3, maxY + 0.3);
		//////// vertical lines
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (double i = minX; i <= maxX; i = i + 1) {
			StdDraw.line(i, minY, i, maxY);
		}
		////// horizontal lines
		for (double i = minY; i <= maxY; i = i + 1) {
			StdDraw.line(minX, i, maxX, i);
		}
		//////// x axis
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.003);
		StdDraw.line(minX, 0, maxX, 0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (double i = minX; i <= maxX; i = i + 1) {
			StdDraw.text(i - 0.07, -0.3, Double.toString(i));
		}
//		//////// y axis
		StdDraw.line(0, minY, 0, maxY);
		for (double i = minY; i <= maxY; i = i + 1) {
			if (i != 0)
				StdDraw.text(-0.3, i - 0.07, Double.toString(i));
		}
	}

	private JSONDrawingConfig deserialJSONConfig(String file) {
		Gson gson = new Gson();
		JSONDrawingConfig config = new JSONDrawingConfig();
		try {
			FileReader reader = new FileReader(file);
			config = gson.fromJson(reader, JSONDrawingConfig.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}

}
