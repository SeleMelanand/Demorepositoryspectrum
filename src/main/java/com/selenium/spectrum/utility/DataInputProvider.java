package com.selenium.spectrum.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DataInputProvider {
	public static Object[][] getSheet(String workbokname,String worksheetname) {

		Object[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(new File("./testdata/" + workbokname + ".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(worksheetname);
			// get the number of rows
			int rowCount = sheet.getLastRowNum();
			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];
			// loop through the rows
			for (int i = 1; i < rowCount + 1; i++) {
				try {
					XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < columnCount; j++) { // loop through the columns
						Object cellValue = ""; 
						try {
							cellValue = row.getCell(j).getStringCellValue();
						} catch (Exception e) {
							System.out.println("No data in the respective cell");
						}
						data[i - 1][j] = cellValue; // add to the data array
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

}