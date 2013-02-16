package ro.ratoi.virgiliu.ruby.challenge.subtitle

import groovy.swing.SwingBuilder

import javax.swing.JFileChooser
import javax.swing.WindowConstants as WC
import javax.swing.filechooser.FileFilter

swing = new SwingBuilder()

frame = swing.frame(defaultCloseOperation: WC.EXIT_ON_CLOSE,
		location: [400, 400],
		size: [600, 300],
		title: 'Shift Subtitles') {
			def fc = fileChooser(dialogTitle: 'Choose a subtitle',
					id: 'fc',
					currentDirectory: 'H:/Downloads' as File,
					fileSelectionMode: JFileChooser.FILES_ONLY,
					fileFilter: [getDescription: {-> '*.srt'},
						accept: {file-> file ==~ /.*?\.srt/ || file.isDirectory() }] as FileFilter) {
					}
			def spn = swing.spinner(id: 'spn',
					model: swing.spinnerNumberModel(value: 500))
			def pne = panel {
				button (text: 'Open subtitle...', actionPerformed: { event ->
					if (fc.showOpenDialog() != JFileChooser.APPROVE_OPTION) {
						return
					}
					print fc.selectedFile
					ShiftSubtitles.shiftSubtitles(fc.selectedFile.absolutePath, (long) spn.value)
				})
				label('Shift by (millis)')
				hstrut()
			}
			pne.add(spn)
		}

frame.pack()
frame.setVisible(true)

