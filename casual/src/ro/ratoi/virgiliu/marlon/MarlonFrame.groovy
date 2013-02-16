package ro.ratoi.virgiliu.marlon

import groovy.swing.SwingBuilder

import java.awt.Desktop
import java.util.prefs.Preferences

import javax.swing.JFileChooser
import javax.swing.JOptionPane
import javax.swing.WindowConstants as WC
import javax.swing.filechooser.FileFilter

/**
 * 
 * @author virgiliu
 *
 */
final class MarlonFrame {

	private static final LAST_CHOOSER_DIR  = 'LAST_CHOOSER_DIR'

	private Preferences prefs = Preferences.userNodeForPackage(this.getClass())

	final void showFrame() {
		def swing = new SwingBuilder()
		swing.lookAndFeel('system')

		def frame = swing.frame(defaultCloseOperation: WC.EXIT_ON_CLOSE,
				location: [400, 400],
				size: [600, 300],
				title: 'Abbreviate excel files') {
					def lastFolder = prefs.get(LAST_CHOOSER_DIR, System.getProperty("user.home"))
					def fc = fileChooser(dialogTitle: 'Choose the excel file',
							id: 'fc',
							currentDirectory: lastFolder as File,
							fileSelectionMode: JFileChooser.FILES_ONLY,
							fileFilter: [getDescription: {-> '*.xls'},
								accept: {file-> file ==~ /.*?\.xls/ || file.isDirectory() }] as FileFilter) {
							}
					def pne = panel {
						button (text: 'Open excel file...', actionPerformed: { event ->
							if (fc.showOpenDialog() != JFileChooser.APPROVE_OPTION) {
								return
							}
						})
						hstrut()
						label('Prefix')
						textField(columns: 12, id: 'txt', text: 'P_RHST')
						label('Max chars')
						swing.spinner(id: 'spn',
								model: swing.spinnerNumberModel(value: 16))
						button (text: 'Go', actionPerformed: {
							abbreviate(fc, txt, spn, swing)
						})
						button (text: 'Validate', actionPerformed: {
							validateExcelFile(fc, swing, txt, spn)
						})
					}
				}

		frame.pack()
		frame.setVisible(true)
	}

	private validateExcelFile(JFileChooser fc, SwingBuilder swing, txt, spn) {
		try {
			validateInput(fc, swing, txt, spn)
		} catch (IllegalArgumentException e) {
			return
		}
		ExcelAbbreviator abv = new ExcelAbbreviator(spn.value, txt.text, fc.selectedFile.getAbsolutePath())
		showInfo(abv.validate(), 'Validation status', swing)
	}

	private abbreviate(final JFileChooser fc, final txt, final spn, final SwingBuilder swing) {
		try {
			validateInput(fc, swing, txt, spn)
		} catch (IllegalArgumentException e) {
			return
		}
		storeLastLocation(fc)

		ExcelAbbreviator abv = new ExcelAbbreviator(spn.value, txt.text, fc.selectedFile.getAbsolutePath())
		openGeneratedFile(abv)
	}

	private validateInput(final JFileChooser fc, final SwingBuilder swing, final txt, final spn) {
		if (!fc.selectedFile) {
			showError('Please select a file.', 'Select a file', swing)
			throw new IllegalArgumentException()
		}
		if (txt.text.size() >= spn.value) {
			showError("The prefix size: '${txt.text.size()}' must be smaller than the maximum number of characters: '${spn.value}'.",
					'Prefix too large', swing)
			throw new IllegalArgumentException()
		}
	}

	private storeLastLocation(final JFileChooser fc) {
		prefs.put(LAST_CHOOSER_DIR, fc.selectedFile.getParent())
	}

	private openGeneratedFile(final ExcelAbbreviator abv) {
		def generatedFile = abv.abbreviate()
		Desktop dt = Desktop.getDesktop()
		dt.open(generatedFile as File)
	}

	private void showError(def message, def title, def swingBuilder) {
		showAlert(message, title, swingBuilder, JOptionPane.ERROR_MESSAGE)
	}

	private void showInfo(def message, def title, def swingBuilder) {
		showAlert(message, title, swingBuilder, JOptionPane.INFORMATION_MESSAGE)
	}

	private void showAlert(def message, def title, def swingBuilder, int type) {
		swingBuilder.optionPane().showMessageDialog(null,
				message, title, type)
	}
}
