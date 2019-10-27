package org.mesibo.messenger;

/**
 * a class that keeps track of occurrences of particular words throughout a
 *  set of training data for a naive bayes classifier that identifies "ham" and
 *  "spam" messages
 */
public class Word {
	public String s;
	public int hamCount;
	public int spamCount;

	@Override
	public boolean equals(Object o) {
		return o.toString().equals(s);
	}
	@Override
	public String toString() {
		return s;
	}
	
}
