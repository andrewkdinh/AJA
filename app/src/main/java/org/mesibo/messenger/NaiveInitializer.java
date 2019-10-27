package org.mesibo.messenger;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.mesibo.messenger.Label.HAM;


/**
 * This is the main method that will load the application.
 * 
 * DO NOT MODIFY
 */

public class NaiveInitializer {
	public static BufferedReader reader;
	static Instance[] trainingData;
	public static boolean isInstanceGenerated;
	private static String[] ls;
	/**
	 * Creates a fresh instance of the classifier.
	 * 
	 * @return	a classifier
	 */
	private static NaiveBayesClassifier getNewClassifier() {
		NaiveBayesClassifier nbc = new NaiveClassifier();
		return nbc;
	}

	public static boolean parse(Context mContext, String test) throws IOException {
		// Output classifications on test data
//		File trainingFile = new File("assets/SMSSpamCollection.txt");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(mContext.getAssets().open("SMSSpamCollection.txt")));
		if(!isInstanceGenerated) ls = lines(reader);
		System.out.println("__parse__");
//		for(String a : ls){
//			System.out.println(a);
//		}
		System.out.println("instance " + isInstanceGenerated);
		if(!isInstanceGenerated) trainingData = createInstances(ls, false);
		isInstanceGenerated = true;
		System.out.println("instance finished");
		Instance[] testData= createInstancesLine(test, true);
		System.out.println("testdata finished");

		NaiveBayesClassifier nbc = getNewClassifier();
		nbc.train(trainingData, vocabularySize(trainingData, testData));

        System.out.println("Probability of HAM: " + nbc.p_l(HAM));
        System.out.println("Probability of SPAM: " + nbc.p_l(Label.SPAM));
        
        System.out.println("Probability of 'great' given HAM: " + nbc.p_w_given_l("great", HAM));
        System.out.println("Probability of 'friday' given SPAM: " + nbc.p_w_given_l("friday", Label.SPAM));
        
		double correct = 0.0;
		System.out.println("\nPrediction in the test data set");
		Label prediction = null;
		for (Instance inst : testData) {
			prediction = nbc.classify(inst.words);
			System.out.println(String.format("Pred based on classification: %s    Real: %s", prediction, inst.label));
			if(prediction == inst.label) ++correct;
		}
		System.out.println(String.format("Test accuracy: %.2f", (correct / testData.length)));
		
//		nbc.showImpact();
		Toast.makeText(mContext,"The following message is " + prediction.toString(), Toast.LENGTH_SHORT).show();
		return prediction != HAM;
	}

	public static List<String> readLine(Context mContext, String path) {
		List<String> mLines = new ArrayList<>();

		AssetManager am = mContext.getAssets();

		try {
			InputStream is = am.open(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;

			while ((line = reader.readLine()) != null)
				mLines.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mLines;
	}
	
	private static int vocabularySize(Instance[]... data) {
		Set<String> all = new HashSet<String>();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				for (int k = 0; k < data[i][j].words.length; k++) {
					all.add(data[i][j].words[k]);
				}
			}
		}
		return all.size();
	}


	/**
	 * Reads the lines of the inputted file, treats the first token as the label
	 * and cleanses the remainder, returning an array of instances.
	 *
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private static Instance[] createInstances(String[] ls, boolean isTest) throws IOException {
//		String[] ls = lines(reader);
		Instance[] is = new Instance[ls.length];
		for (int i = 0; i < ls.length; i++) {
			String[] ws = cleanse(ls[i]).split("\\s");
			is[i] = new Instance();
			is[i].words = drop(ws, 1);
			is[i].label = isTest ? Label.valueOf("UNKNOWN") : Label.valueOf(ws[0].toUpperCase());
		}
		return is;
	}

	private static Instance[] createInstancesLine(String line, boolean isTest) throws IOException {
		Instance[] is = new Instance[1];
		String[] ws = cleanse(line).split("\\s");
		is[0] = new Instance();
		is[0].words = drop(ws, 1);
		is[0].label = isTest ? Label.valueOf("UNKNOWN") : Label.valueOf(ws[0].toUpperCase());
		return is;
	}

	/**
	 * Strip, increases density of model
	 * 
	 * @param s
	 * @return	the string with punctuation removed and uniform case
	 */
	private static String cleanse(String s) {
		s = s.replace("?", " ");
		s = s.replace(".", " ");
		s = s.replace(",", " ");
		s = s.replace("/", " ");
		s = s.replace("!", " ");
		return s.toLowerCase();
	}

	private static String[] lines(File f) throws IOException {
		FileReader fr = new FileReader(f);
		String[] l = lines(fr);
		fr.close();
		return l;
	}

	private static String[] lines(Reader r) throws IOException {
		BufferedReader br = new BufferedReader(r);
		String s;
		List<String> data = new ArrayList<String>();
		while ((s = br.readLine()) != null && !s.isEmpty()) {
			data.add(s);
		}
		br.close();
		return data.toArray(new String[data.size()]);
	}

	private static String[] lines(BufferedReader br) throws IOException {
		String s;
		List<String> data = new ArrayList<String>();
		while ((s = br.readLine()) != null && !s.isEmpty()) {
			data.add(s);
		}
		br.close();
		return data.toArray(new String[data.size()]);
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T[] drop(T[] xs, int i) {
		T[] ys = (T[]) Array.newInstance(xs[0].getClass(), xs.length - i);
		System.arraycopy(xs, i, ys, 0, xs.length - 1);
		return ys;		
	}
}
