package org.mesibo.messenger;

import java.util.ArrayList;

/**
 * Your implementation of a naive bayes classifier. Please implement all four methods.
 */

public class NaiveClassifier implements NaiveBayesClassifier {
	
	final static double DELTA = 0.00001;
	
	// variables that reset during each training
	private ArrayList<Word> trainVocab;
	private double totalVocabSize;
	private double p_HAM;
	private double hamTokenCount;
	private double spamTokenCount;
	
	/**
	 * Trains the classifier with the provided training data and vocabulary size
	 */
	@Override
	public void train(Instance[] trainingData, int v) {
		// initialize variables
		double hamCount = 0.0;				// for priors
		hamTokenCount = 0.0;					// for conditionals
		spamTokenCount = 0.0;					// for conditionals
		trainVocab = new ArrayList<Word>();	// for conditionals
		totalVocabSize = v;					// for conditionals (smoothing)
		
		// iterate through the training data
		for (Instance i: trainingData) {
			// count - for prior probabilities, conditional probabilities
			if (i.label == Label.HAM) {
				++hamCount;
				hamTokenCount += i.words.length;
			} else {
				spamTokenCount += i.words.length;
			}
			
			// create counts for individual words
			for (String s: i.words) {
				Word temp = new Word();
				temp.s = s;
				int pos = trainVocab.indexOf(temp);
				// no matching word in vocab, generate one
				if (pos == -1) {
					if (i.label == Label.HAM)
						++temp.hamCount;
					else
						++temp.spamCount;
					trainVocab.add(temp);
				// update existing word
				} else {
					if (i.label == Label.HAM)
						trainVocab.get(pos).hamCount++;
					else
						trainVocab.get(pos).spamCount++;
				}
			}
		}
		
		//process results
		p_HAM = hamCount / (double) trainingData.length;
	}

	/**
	 * Returns the prior probability of the label parameter, i.e. P(SPAM) or P(HAM)
	 */
	@Override
	public double p_l(Label label) {
		return (label == Label.HAM)? p_HAM : 1.0 - p_HAM;
	}

	/**
	 * Returns the smoothed conditional probability of the word given the label,
	 * i.e. P(word|SPAM) or P(word|HAM)
	 */
	@Override
	public double p_w_given_l(String word, Label label) {
		Word temp = new Word();
		temp.s = word;
		int pos = trainVocab.indexOf(temp);
		
		// (C_label(word) + DELTA) / (Sum(v in V): C_label(v) + |V| * DELTA) 
		if (pos == -1) {
			return (label == Label.HAM)?
				DELTA / (hamTokenCount + DELTA * totalVocabSize):
				DELTA / (spamTokenCount + DELTA * totalVocabSize);
		} else {			
			return (label == Label.HAM)?
				(trainVocab.get(pos).hamCount + DELTA) 
					/ (hamTokenCount + DELTA * totalVocabSize):
				(trainVocab.get(pos).spamCount + DELTA)
					/ (spamTokenCount + DELTA * totalVocabSize);
		}
	}
	
	/**
	 * Classifies an array of words as either SPAM or HAM. 
	 */
	@Override
	public Label classify(String[] words) {
		double hamConditional = 0.0;
		double spamConditional = 0.0;
		
		for (String s: words) {
			hamConditional += Math.log(p_w_given_l(s, Label.HAM));
			spamConditional += Math.log(p_w_given_l(s, Label.SPAM));
		}
		
		return
			(Math.log(p_l(Label.HAM)) + hamConditional >
			Math.log(p_l(Label.SPAM)) + spamConditional)?
					Label.HAM : Label.SPAM;
	}
	
	/**
	 * Print out 5 most informative words.
	 */
	public void showImpact() {
		String[] mostInform = new String[5];
		double[] mostInformAmount = new double[5];
		
		for (Word w: trainVocab) {
			double information = Math.max(
					p_w_given_l(w.s, Label.HAM)/p_w_given_l(w.s, Label.SPAM),
					p_w_given_l(w.s, Label.SPAM)/p_w_given_l(w.s, Label.HAM));
			
			if (information > mostInformAmount[0]) {
				mostInform[0] = w.s;
				mostInformAmount[0] = information;
				
				for (int i = 1; i < 5; i++) {
					if (information > mostInformAmount[i]) {
						mostInform[i-1] = mostInform[i];
						mostInform[i] = w.s;
						mostInformAmount[i-1] = mostInformAmount[i];
						mostInformAmount[i] = information;
					}
				}
			}
			
		}
		
		for (String s: mostInform) System.out.println(s);
	}
}
