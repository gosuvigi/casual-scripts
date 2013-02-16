package ro.ratoi.virgiliu.marlon

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CreationHelper
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

/**
 * 
 * @author virgiliu
 *
 */
final class ExcelAbbreviator {

	private int maxNoOfCharacters
	private String prefix
	private String fileName

	public ExcelAbbreviator(final int maxNoOfCharacters, final String prefix, final String fileName) {
		super()
		this.maxNoOfCharacters = maxNoOfCharacters
		this.prefix = prefix
		this.fileName = fileName
	}

	/**
	 * Abbreviate the provided file and return the name of the generated file.
	 * 
	 * @return The name of the generated file.
	 */
	public final String abbreviate() {
		int maxLength = maxNoOfCharacters - prefix.size()
		Abbreviator abv = new Abbreviator()

		Workbook wb = new HSSFWorkbook()
		//Workbook wb = new XSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper()
		Sheet sheet = wb.createSheet("new sheet")

		int idx = 0
		new ExcelBuilder(fileName).eachLine {
			def cell = cell(0)
			println "${cell} : " + prefix + abv.abbreviate(cell, maxLength)
			Row row = sheet.createRow(idx++)
			Cell cell1 = row.createCell(0)
			cell1.setCellValue(cell)
			Cell cell2 = row.createCell(1)
			final String value = prefix + abv.abbreviate(cell, maxLength)
			cell2.setCellValue(value)
		}

		def generatedFile = 'workbook.xls'
		FileOutputStream fileOut = new FileOutputStream(generatedFile)
		wb.write(fileOut)
		fileOut.close()
		return generatedFile
	}

	public final String validate() {
		StringBuilder buff = new StringBuilder()
		int i = 0
		try {
			new ExcelBuilder(fileName).eachLine {
				def cell = cell(1)
				if (cell.size() > maxNoOfCharacters) {
					buff << 'Line ' << i << ' ' << cell << ' has size ' << cell.size() << ' larger than: ' << maxNoOfCharacters << '.\n'
				}
				i++
			}
		} catch (NullPointerException e) {
			return "Couldn't read the second column from the file: '${fileName}'. Make sure it has at least two columns."
		} catch (Exception e) {
			return e.getMessage()
		}
		if (buff.size() == 0) {
			return 'No errors were found.'
		}
		return buff.toString()
	}
}
