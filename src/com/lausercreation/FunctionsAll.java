package com.lausercreation;

 import java.io.*;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


 public class FunctionsAll {
   
	 public static void createUser(String cellvalue, String emailID, int rownum) throws Exception {
	   
		File src=new File("C:\\Users\\nrouthu\\Documents\\eclipse\\CreateLAUsers\\CPUsers.xlsx");
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb = null;
		wb=new XSSFWorkbook(fis);
		XSSFSheet sht=wb.getSheetAt(0);
		
		
		
		Row row1 = sht.createRow((short) 0);
		row1.createCell(0).setCellValue("UserID");
		row1.createCell(1).setCellValue("EmailID");
		Row row2 = sht.createRow((short) rownum);
		row2.createCell(0).setCellValue(cellvalue);
		row2.createCell(1).setCellValue(emailID);
		
		FileOutputStream fo= new FileOutputStream(src);
		wb.write(fo);
		wb.close();
		fo.close();
   }
	 ///////////////////////////////////////////////////////
	 //Functions to read Data from Excel, waiting for Webelement and storing links into an excel
	 static int rownum=1;
	 
	 public static String readData(String Sheetname, int rownum) throws Exception {
		   
			File src=new File("C:\\Users\\nrouthu\\Documents\\eclipse\\CreateLAUsers\\CPUsers.xlsx");
			FileInputStream fis=new FileInputStream(src);
			XSSFWorkbook wb = null;
			wb=new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			
			//int rowCount = sheet1.getLastRowNum()-sheet1.getFirstRowNum();
			Row row1 = sheet1.getRow(rownum);
			String emailVal = row1.getCell(0).getStringCellValue();
			return emailVal;
	 }
	 
	 public static void waitForElement(WebDriver driver, String locatorType ,String locator) {
		
		 WebDriverWait wait=new WebDriverWait(driver, 10);
		 if(locatorType.equals("id"))
		 {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
		 }
		 else if (locatorType.equals("xpath"))
		 {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		 }
		 
	 }
	 
	 public static void storeLinks(String ID,String link) throws Exception {
		   
			File src=new File("C:\\Users\\nrouthu\\Documents\\eclipse\\CreateLAUsers\\CPUserRegLinks.xlsx");
			FileInputStream fis=new FileInputStream(src);
			
			XSSFWorkbook wb = null;
			wb=new XSSFWorkbook(fis);
			XSSFSheet sht=wb.getSheetAt(0);
			
			//Row row1 = sht.createRow((short) 0);
			//row1.createCell(0).setCellValue("ConfirmationLink");
			//Row row2 = sht.createRow((short) rownum);
			//row2.createCell(0).setCellValue(link);
			
			Row row1 = sht.createRow((short) 0);
			row1.createCell(0).setCellValue("UserID");
			row1.createCell(1).setCellValue("Link");
			Row row2 = sht.createRow((short) rownum);
			row2.createCell(0).setCellValue(ID);
			row2.createCell(1).setCellValue(link);
			FileOutputStream fo= new FileOutputStream(src);
			wb.write(fo);
			wb.close();
			fo.close();
			rownum++;
	   }
	 
	 ////////////////////////////////////////////////////////////////////////////
	 //Function for retrieving confirmation links from the Excel
	 public static String retrieveLink(String Sheetname, int rownum1, int rownum2) throws Exception {
		   
			File src=new File("C:\\Users\\nrouthu\\Documents\\eclipse\\CreateLAUsers\\CPUserRegLinks.xlsx");
			FileInputStream fis=new FileInputStream(src);
			XSSFWorkbook wb = null;
			wb=new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
		
			//int rowCount = sheet1.getLastRowNum()-sheet1.getFirstRowNum();
			Row row1 = sheet1.getRow(rownum1);
			String userID = row1.getCell(0).getStringCellValue();
			
			Row row2 = sheet1.getRow(rownum2);
			String linkVal = row2.getCell(1).getStringCellValue();
			return linkVal;
	 }
	 
	
 }