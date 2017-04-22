package com.vigi.ruby.challenge.subtitle

import groovy.swing.SwingBuilder

import javax.swing.*
import static java.awt.GridBagConstraints.*
import javax.swing.WindowConstants as WC
import javax.swing.filechooser.FileFilter
import java.util.prefs.Preferences

/**
 *
 * @author vigi Feb 17, 2013 4:01:33 PM
 *
 */
public class TweakSubtitleFrame {

    private static final LAST_CHOOSER_DIR = 'LAST_CHOOSER_DIR'

    private static final Preferences prefs = Preferences.userNodeForPackage(this.getClass())

    public static void main(String[] args) {
        SwingBuilder swing = new SwingBuilder()

        JFrame frame = swing.frame(defaultCloseOperation: WC.EXIT_ON_CLOSE,
                location: [400, 400],
                size: [900, 300],
                title: 'Tweak Subtitles') {
            def lastFolder = prefs.get(LAST_CHOOSER_DIR, System.getProperty("user.home"))
            def fc = fileChooser(dialogTitle: 'Choose a subtitle',
                    id: 'fc',
                    currentDirectory: lastFolder as File,
                    fileSelectionMode: JFileChooser.FILES_ONLY,
                    fileFilter: [getDescription: { -> '*.srt' },
                                 accept        : { file -> file ==~ /.*?\.srt/ || file.isDirectory() }] as FileFilter) {
            }
            def pne = panel {
                def padding = [5, 5, 5, 5]
                gridBagLayout()
                label(text: "Shift by (millis)",
                        constraints: gbc(fill: BOTH, gridx: 0, gridwidth: 1, gridy: 0, weightx: 0.1,
                                weighty: 0.2, insets: padding))
                spinner(id: 'spn', model: swing.spinnerNumberModel(value: 500),
                        constraints: gbc(fill: BOTH, gridx: 1, gridwidth: 2, gridy: 0, weightx: 0.6,
                                weighty: 0.2, insets: padding))
                button(text: 'Shift by (millis)',
                        constraints: gbc(fill: BOTH, gridx: 3, gridwidth: 1, gridy: 0, weightx: 0.1,
                                weighty: 0.2, insets: padding),
                        actionPerformed: {
                            execute(fc) {
                                TweakSubtitles.shiftSubtitles(fc.selectedFile.absolutePath, (long) spn.value)
                            }
                        })
                button(text: '23 fps to 25 fps',
                        constraints: gbc(fill: BOTH, gridx: 4, gridwidth: 1, gridy: 0, weightx: 0.1,
                                weighty: 0.2, insets: padding),
                        actionPerformed: {
                            execute(fc) {
                                TweakSubtitles.convert23FpsTo25Fps(fc.selectedFile.absolutePath)
                            }
                        })
                button(text: '25 fps to 23 fps',
                        constraints: gbc(fill: BOTH, gridx: 5, gridwidth: 1, gridy: 0, weightx: 0.1,
                                weighty: 0.2, insets: padding),
                        actionPerformed: {
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


