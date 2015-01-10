package com.vigi.ruby.challenge.subtitle

import groovy.swing.SwingBuilder

import java.util.prefs.Preferences

import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.WindowConstants as WC
import javax.swing.filechooser.FileFilter

/**
 * 
 * @author vigi Feb 17, 2013 4:01:33 PM
 *
 */
public class TweakSubtitleFrame {

	private static final LAST_CHOOSER_DIR  = 'LAST_CHOOSER_DIR'

	private static final Preferences prefs = Preferences.userNodeForPackage(this.getClass())

	public static void main(String[] args) {
		SwingBuilder swing = new SwingBuilder()

		JFrame frame = swing.frame(defaultCloseOperation: WC.EXIT_ON_CLOSE,
		location: [400, 400],
		size: [600, 300],
		title: 'Tweak Subtitles') {
			def lastFolder = prefs.get(LAST_CHOOSER_DIR, System.getProperty("user.home"))
			def fc = fileChooser(dialogTitle: 'Choose a subtitle',
			id: 'fc',
			currentDirectory: lastFolder as File,
			fileSelectionMode: JFileChooser.FILES_ONLY,
			fileFilter: [getDescription: {-> '*.srt'},
				accept: {file-> file ==~ /.*?\.srt/ || file.isDirectory() }] as FileFilter) {
			}
			def pne = panel {
				label("Shift by (millis)")
				swing.spinner(id: 'spn', model: swing.spinnerNumberModel(value: 500))
				button (text: 'Shift by (millis)', actionPerformed: {
					execute(fc) {
						TweakSubtitles.shiftSubtitles(fc.selectedFile.absolutePath, (long) spn.value)
					}
				})
				hstrut()
				button (text: '23 fps to 25 fps', actionPerformed: {
					execute(fc) {
						TweakSubtitles.convert23FpsTo25Fps(fc.selectedFile.absolutePath)
					}
				})
				hstrut()
				button (text: '25 fps to 23 fps', actionPerformed: {
					execute(fc) {
						TweakSubtitles.convert25FpsTo23Fps(fc.selectedFile.absolutePath)
					}
				})
			}
		}

		frame.pack()
		frame.setVisible(true)
	}

	private static void execute(JFileChooser fc, Closure cl) {
		if (fc.showOpenDialog() != JFileChooser.APPROVE_OPTION) {
			return
		}
		println fc.selectedFile
		cl.call(fc.selectedFile.absolutePath)
		storeLastLocation(fc)
	}

	private static storeLastLocation(final JFileChooser fc) {
		prefs.put(LAST_CHOOSER_DIR, fc.selectedFile.getParent())
	}
}


