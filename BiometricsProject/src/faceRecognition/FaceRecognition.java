package faceRecognition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.face.EigenFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;

public class FaceRecognition {

	public static String csvFilePath = "/Users/luciadabezies/BiometricsProject/BiometricsProject/resources/"	
											+ "faceRecognition/TrainingData.txt";

	public void recongize() {

		ArrayList<Mat> images = new ArrayList<>();
		ArrayList<Integer> labels = new ArrayList<>();
		
		readCSV(images, labels);

		Mat testSample = images.get(images.size() - 1);
		Integer testLabel = labels.get(images.size() - 1);
		images.remove(images.size() - 1);
		labels.remove(labels.size() - 1);
		MatOfInt labelsMat = new MatOfInt();
		labelsMat.fromList(labels);
		EigenFaceRecognizer efr = EigenFaceRecognizer.create();
		efr.train(images, labelsMat);

		int[] outLabel = new int[1];
		double[] outConf = new double[1];
		efr.predict(testSample, outLabel, outConf);
		
		System.out.println("***Predicted label is "+outLabel[0]+".***");

		System.out.println("***Actual label is "+testLabel+".***");
		System.out.println("***Confidence value is "+outConf[0]+".***");
	}

	private static void readCSV(ArrayList<Mat> images, ArrayList<Integer> labels) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csvFilePath));

			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("\\;");
				Mat readImage = Imgcodecs.imread(tokens[0], 0);//aca no funca
				images.add(readImage);
				labels.add(Integer.parseInt(tokens[1]));
			}
		} catch (FileNotFoundException e) {
			System.out.println("fileNotFound");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("NumberFormat");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}

	}

}
