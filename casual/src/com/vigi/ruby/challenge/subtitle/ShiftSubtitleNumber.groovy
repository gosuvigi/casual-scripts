package com.vigi.ruby.challenge.subtitle
/**
 *
 * Shift subtitle indexes for merging two CD subtitles into one
 *
 * Created by vigi on 4/22/2017.
 */
class ShiftSubtitleNumber {

    static void main(String[] args) {
        def path = args[0]
        File f = new File(path)
        def lines = new ArrayList()
        def newLines = new ArrayList()

        int numberToShift = Integer.parseInt(args[1])
        def line
        f.withReader("ISO_8859_1") { reader ->
            while ((line = reader.readLine()) != null) {
                lines.add(line)
            }
        }
        lines.each { l ->
            try {
                Integer i = Integer.parseInt(l) + numberToShift
                newLines.add(i.toString())
            } catch (NumberFormatException e) {
                newLines.add(l)
            }
        }

        File newFile = new File(path + ".new")
        newFile.delete()
        newLines.each { newLine ->
            newFile << newLine + "\r\n"
        }
    }

}
