package com.vigi.util

/**
 * 
 * @author Virgiliu
 * Oct 10, 2011
 *
 */
final class Benchmark {

	static def benchmark(final Closure cl) {
		def start = System.nanoTime()
		cl.call()
		def end = System.nanoTime()
		return (end - start) * 1e-6
	}
}
