package com.vigi.marlon;

/**
 * 
 * @author virgiliu
 * 
 */
public interface IAbbreviatorStrategy {

	/**
	 * Abbreviates a given word to fit into as many as maxLength characters.
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	String abbreviate(final String str, final int maxLength);

}
