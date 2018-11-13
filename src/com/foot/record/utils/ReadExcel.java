package com.foot.record.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.foot.record.entity.Record;

public class ReadExcel {
	// ������
	private int totalRows = 0;
	// ������
	private int totalCells = 0;
	// ������Ϣ������
	private String errorMsg;

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * ��EXCEL�ļ�����ȡ��Ϣ����
	 * 
	 * @param fielName
	 * @return
	 */
	public List<Record> getExcelInfo(MultipartFile mFile) {
		String fileName = mFile.getOriginalFilename();// ��ȡ�ļ���
		List<Record> recordList = null;
		try {
			if (!validateExcel(fileName)) {// ��֤�ļ����Ƿ�ϸ�
				return null;
			}
			boolean isExcel2003 = true;// �����ļ����ж��ļ���2003�汾����2007�汾
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			recordList = createExcel(mFile.getInputStream(), isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordList;
	}
	
	/**
	 * ����excel��������ݶ�ȡ�ͻ���Ϣ
	 * 
	 * @param is
	 *            ������
	 * @param isExcel2003
	 *            excel��2003����2007�汾
	 * @return
	 * @throws IOException
	 */
	public List<Record> createExcel(InputStream is, boolean isExcel2003) {
		List<Record> recordlist = null;
		try {
			Workbook wb = null;
			if (isExcel2003) {// ��excel��2003ʱ,����excel2003
				wb = new HSSFWorkbook(is);
			} else {// ��excel��2007ʱ,����excel2007
				wb = new XSSFWorkbook(is);
			}
			recordlist = readExcelValue(wb);// ��ȡExcel����ͻ�����Ϣ
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recordlist;
	}

	/**
	 * ��ȡExcel����ͻ�����Ϣ
	 * 
	 * @param wb
	 * @return
	 */
	private List<Record> readExcelValue(Workbook wb) {
		// �õ���һ��shell
		Sheet sheet = wb.getSheetAt(0);
		// �õ�Excel������
		this.totalRows = sheet.getPhysicalNumberOfRows();
		// �õ�Excel������(ǰ����������)
		if (totalRows > 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		
		List<Record> recordList = new ArrayList<Record>();
		// ѭ��Excel����
		for (int r = 1; r < totalRows; r++) {
			String department = null;
			String region = null;
			String registAddress = null;
			String postalAddress = null;
			String contacts = null;
			String street = null;
			String addedTax = null;
			String incomeTax = null;
			String manageTax = null;
			String returnTax = null;
			String phone = null;
			int customerType = -1;
			String wxNumber = null;
			String orderNumber = null;
			int status = -1;
			int star = 0;
			int returnTaxPercentage =-1;
			int orderPercentage =-1;
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			Record record = new Record();
			// ѭ��Excel����
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					// ����Ǵ�����,������д����25,cell.getNumericCellValue()�����25.0,ͨ����ȡ�ַ���ȥ��.0���25
					if(c==0){
						// ��������
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							department = cell.getNumericCellValue()+"";
							department = department.substring(0, department.indexOf(".0"));
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							department = cell.getStringCellValue();
							
						}
						
					}else if(c==1){
						// ����
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							region = cell.getNumericCellValue()+"";
							region = region.substring(0, region.indexOf(".0"));
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							region = cell.getStringCellValue();
						}
					}else if(c==2){
						//ע���ַ
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							registAddress = cell.getNumericCellValue()+"";
							registAddress = registAddress.substring(0, registAddress.indexOf(".0"));
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							registAddress = cell.getStringCellValue();
						}
					}else if(c==3){
						//ͨѶ��ַ
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							postalAddress = cell.getNumericCellValue()+"";
							postalAddress = postalAddress.substring(0, postalAddress.indexOf(".0"));
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							postalAddress = cell.getStringCellValue();
						}
					}else if(c==4){
						// ��ϵ��
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							contacts = cell.getNumericCellValue()+"";
							contacts = contacts.substring(0, contacts.indexOf(".0"));
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							contacts = cell.getStringCellValue();
						}
					}else if(c==5){
						//�ֵ�
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							street = cell.getNumericCellValue()+"";
							street = street.substring(0, street.indexOf(".0"));
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							street = cell.getStringCellValue();
						}
					}else if(c== 6){
						//��ֵ˰
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							addedTax = cell.getNumericCellValue()+"".trim();
							if(addedTax.endsWith(".0")){
								addedTax = addedTax.substring(0, addedTax.indexOf(".0"));
							}
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							addedTax = cell.getStringCellValue();
						}
					}else if(c==7){
						//����˰
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							incomeTax = cell.getNumericCellValue()+"".trim();
							if(incomeTax .endsWith(".0")){
								incomeTax = incomeTax.substring(0, incomeTax.indexOf(".0"));
							}
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							incomeTax = cell.getStringCellValue();
						}
					}else if(c==8){
						//�����
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							manageTax = cell.getNumericCellValue()+"".trim();
							if(manageTax.endsWith(".0")){
								manageTax = manageTax.substring(0, manageTax.indexOf(".0"));
							}
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							manageTax = cell.getStringCellValue();
							
						}
					}else if(c==9){
						//��˰
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							returnTax = cell.getNumericCellValue()+"".trim();
							if(returnTax.endsWith(".0")){
								returnTax = returnTax.substring(0, returnTax.indexOf(".0"));
							}
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							returnTax = cell.getStringCellValue();
							
						}
					}else if(c==10){
						//��˰���
						String type = null;
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							type = cell.getNumericCellValue()+"".trim();
							type = type.substring(0,type.indexOf(".0"));
						}else {
							type = cell.getStringCellValue();
						}
						if(type != null ){
							if(type.equals("��֧��")){
								returnTaxPercentage = 1;
							}else if(type.equals("δ֧��")){
								returnTaxPercentage = 2;
							}
						}
					}
					else if(c==11){
						//��˰���
						String type = null;
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							type = cell.getNumericCellValue()+"".trim();
							type = type.substring(0,type.indexOf(".0"));
						}else {
							type = cell.getStringCellValue();
						}
						if(type != null ){
							if(type.equals("��֧��")){
								orderPercentage = 1;
							}else if(type.equals("δ֧��")){
								orderPercentage = 2;
							}
						}
					}else if(c==12){
						//�绰
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							phone = cell.getNumericCellValue()+"".trim();
							if(phone.endsWith(".0")){
								phone = phone.substring(0, phone.indexOf(".0"));
							}
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							phone = cell.getStringCellValue();
						}
						if(phone != null){
							if (phone.indexOf("E")!=-1 || phone.indexOf("e")!=-1 || phone.indexOf("+")!=-1) {
	                            BigDecimal bd = new BigDecimal(phone);
	                            phone = bd.toString();
							}
						}
					}else if(c==13){
						//�ͻ�����
						String type = null;
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							type = cell.getNumericCellValue()+"".trim();
							type = type.substring(0,type.indexOf(".0"));
						}else {
							type = cell.getStringCellValue();
						}
						if(type != null ){
							if(type.equals("�ɽ��ͻ�")){
								customerType = 1;
							}else if(type.equals("���ֿͻ�")){
								customerType = 2;
							}
						}
					}else if(c==14){
						//΢�ź�
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							wxNumber = cell.getNumericCellValue()+"".trim();
							if(wxNumber.endsWith(".0")){
								wxNumber = wxNumber.substring(0, wxNumber.indexOf(".0"));
							}
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							wxNumber = cell.getStringCellValue();
						}
						if(wxNumber != null){
							if (wxNumber.indexOf("E")!=-1 || wxNumber.indexOf("e")!=-1 || wxNumber.indexOf("+")!=-1) {
	                            BigDecimal bd = new BigDecimal(wxNumber);
	                            wxNumber = bd.toString();
							}
						}
					}
					
					else if(c==15){
						//������
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							orderNumber = cell.getNumericCellValue()+"".trim();
							if(orderNumber.endsWith(".0")){
								orderNumber = orderNumber.substring(0, orderNumber.indexOf(".0"));
							}
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							orderNumber = cell.getStringCellValue();
						}
						if(orderNumber != null){
							if (orderNumber.indexOf("E")!=-1 || orderNumber.indexOf("e")!=-1 || orderNumber.indexOf("+")!=-1) {
	                            BigDecimal bd = new BigDecimal(orderNumber);
	                            orderNumber = bd.toString();
							}
						}
					}else if(c==16){
						//״̬
						String st="";
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							st = cell.getNumericCellValue()+"".trim();
							st = st.substring(0, st.indexOf(".0"));
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							st = cell.getStringCellValue();
						}
						if(st.trim().endsWith("��")){
							star = Integer.parseInt(st.trim().substring(0, st.trim().length()-1));
						}else{
							star = Integer.parseInt(st);
						}
						
					}else if(c==17){
						//״̬
						String st="";
						if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
							st = cell.getNumericCellValue()+"".trim();
							st = st.substring(0, st.indexOf(".0"));
							
						}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
							st = cell.getStringCellValue();
						}
						if(st.equals("�����")){
							status = 1;
						}else if(st.equals("ע����")){
							status =2;
						}
					}
				}
			}
			if(department == null){
				continue;
			}
			record.setDepartment(department);
			record.setRegion(region);
			record.setRegistAddress(registAddress);
			record.setPostalAddress(postalAddress);
			record.setContacts(contacts);
			record.setStreet(street);
			record.setAddedTax(addedTax);
			record.setIncomeTax(incomeTax);
			record.setManageTax(manageTax);
			record.setReturnTax(returnTax);
			record.setPhone(phone);
			record.setCustomerType(customerType);
			record.setWxNumber(wxNumber);
			record.setOrderNumber(orderNumber);
			record.setStar(star);
			record.setStatus(status);
			record.setReturnTaxPercentage(returnTaxPercentage);
			record.setOrderPercentage(orderPercentage);
			record.setInitDate(new Date());
			// ��ӵ�list
			recordList.add(record);
		}
		return recordList;
	}
	/**
	 * ��֤EXCEL�ļ�
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorMsg = "�ļ�������excel��ʽ";
			return false;
		}
		return true;
	}

	// @�������Ƿ���2003��excel������true��2003
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	// @�������Ƿ���2007��excel������true��2007
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
