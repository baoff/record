package com.foot.record.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.foot.record.entity.Record;
public class ExportExcelUtil {
	public File getExcelFile(String fileDir) throws Exception {
		String classDir = null;
		String fileBaseDir = null;
		File file = null;
		classDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		fileBaseDir = classDir.substring(0, classDir.lastIndexOf("classes"));
		file = new File(fileBaseDir + fileDir);
		if (!file.exists()) {
			throw new Exception("ģ���ļ������ڣ�");
		}
		return file;
	}
	
	public Workbook writeNewExcel(File file, String sheetName, List<Record> lis,String roleName) throws Exception {
		Workbook wb = null;
		Row row = null;
		Cell cell = null;
		FileInputStream fis = new FileInputStream(file);
		if (isExcel2003(file.getName())) {
			wb = new HSSFWorkbook(fis);
		} else {
			wb = new XSSFWorkbook(fis);
		}
		Sheet sheet = wb.getSheet(sheetName);
		// ѭ����������
		int lastRow = sheet.getLastRowNum() + 1; // �������ݵ�����ROW
		CellStyle cs = setSimpleCellStyle(wb); // Excel��Ԫ����ʽ
		for (int i = 0; i < lis.size(); i++) {
			row = sheet.createRow(lastRow + i); // �����µ�ROW���������ݲ���
			// ����Ŀʵ�������ڸô����������ݲ��뵽Excel��
			Record record = lis.get(i);
			if (record == null) {
				break;
			}
			// Cell��ֵ��ʼ
			cell = row.createCell(0);
			cell.setCellValue(record.getDepartment());
			cell.setCellStyle(cs);

			cell = row.createCell(1);
			cell.setCellValue(record.getRegion());
			cell.setCellStyle(cs);

			cell = row.createCell(2);
			cell.setCellValue(record.getRegistAddress());
			cell.setCellStyle(cs);

			cell = row.createCell(3);
			cell.setCellValue(record.getPostalAddress());
			cell.setCellStyle(cs);

			cell = row.createCell(4);
			cell.setCellValue(record.getContacts());
			cell.setCellStyle(cs);

			cell = row.createCell(5);
			cell.setCellValue(record.getStreet());
			cell.setCellStyle(cs);

			cell = row.createCell(6);
			cell.setCellValue(record.getAddedTax());
			cell.setCellStyle(cs);

			cell = row.createCell(7);
			cell.setCellValue(record.getIncomeTax());
			cell.setCellStyle(cs);

			cell = row.createCell(8);
			cell.setCellValue(record.getManageTax());
			cell.setCellStyle(cs);

			cell = row.createCell(9);
			cell.setCellValue(record.getReturnTax());
			cell.setCellStyle(cs);

			cell = row.createCell(10);
			cell.setCellValue(record.getPhone());
			cell.setCellStyle(cs);

			cell = row.createCell(11);
			if(record.getCustomerType()==1){
				cell.setCellValue("�ɽ��ͻ�");
			}else if(record.getCustomerType()==2){
				cell.setCellValue("���ֿͻ�");
			}
			cell.setCellStyle(cs);
			
			cell = row.createCell(12);
			cell.setCellValue(record.getWxNumber());
			cell.setCellStyle(cs);
			
			cell = row.createCell(13);
			cell.setCellValue(record.getOrderNumber());
			cell.setCellStyle(cs);
			
			cell = row.createCell(14);
			cell.setCellValue(record.getStar()+"��");
			cell.setCellStyle(cs);
			
			cell = row.createCell(15);
			String status = "ע����";
			if (record.getStatus() == 1) {
				status = "�����";
			} else if (record.getStatus() == 2) {
				status = "ע����";
			} 
			cell.setCellValue(status);
			cell.setCellStyle(cs);
			
			cell = row.createCell(16);
			if (record.getInitDate()!= null) {
				cell.setCellValue(record.getInitDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);

			} else {
				cell.setCellStyle(cs);
			}
		}
		return wb;
	}
	
	/*	
	public Workbook writeOutExcel(File file, String sheetName, List<NBOrder> lis,String roleName) throws Exception {
		Workbook wb = null;
		Row row = null;
		Cell cell = null;
		FileInputStream fis = new FileInputStream(file);
		if (isExcel2003(file.getName())) {
			wb = new HSSFWorkbook(fis);
		} else {
			wb = new XSSFWorkbook(fis);
		}
		Sheet sheet = wb.getSheet(sheetName);
		// ѭ����������
		int lastRow = sheet.getLastRowNum() + 1; // �������ݵ�����ROW
		CellStyle cs = setSimpleCellStyle(wb); // Excel��Ԫ����ʽ
		for (int i = 0; i < lis.size(); i++) {
			row = sheet.createRow(lastRow + i); // �����µ�ROW���������ݲ���
			// ����Ŀʵ�������ڸô����������ݲ��뵽Excel��
			NBOrder order = lis.get(i);
			if (order == null) {
				break;
			}
			// Cell��ֵ��ʼ
			//���ⵥ��
			cell = row.createCell(0);
			cell.setCellValue(order.getMobileOutFactoryNo());
			cell.setCellStyle(cs);
			//��������
			cell = row.createCell(1);
			if (order.getOutFactoryDate() != null) {
				cell.setCellValue(order.getOutFactoryDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);
			} else {
				cell.setCellStyle(cs);
			}
			//��������
			cell = row.createCell(2);
			if (order.getOutFactoryProp() == 1) {
				cell.setCellValue("����");
			} else if (order.getOutFactoryProp() == 2) {
				cell.setCellValue("����");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cs);
			//���⽻��
			cell = row.createCell(3);
			cell.setCellValue(order.getOutFactoryName());
			cell.setCellStyle(cs);
			//ǻ��
			cell = row.createCell(4);
			cell.setCellValue(order.getQt());
			cell.setCellStyle(cs);
			//BBU
			cell = row.createCell(5);
			cell.setCellValue(order.getBbu());
			cell.setCellStyle(cs);
			//RRU
			cell = row.createCell(6);
			cell.setCellValue(order.getRru());
			cell.setCellStyle(cs);
			//��λ
			cell = row.createCell(7);
			cell.setCellValue(order.getLibrary());
			cell.setCellStyle(cs);
			//��Ƶ��
			cell = row.createCell(8);
			cell.setCellValue(order.getCarryNum());
			cell.setCellStyle(cs);
			//������
			cell = row.createCell(9);
			cell.setCellValue(order.getFeederNum());
			cell.setCellStyle(cs);
			//�ʲ���ǩ��
			cell = row.createCell(10);
			cell.setCellValue(order.getAssetTagNum());
			cell.setCellStyle(cs);
			//�ʲ����
			cell = row.createCell(11);
			cell.setCellValue(order.getAssetNum());
			cell.setCellStyle(cs);
			//�ʲ�����
			cell = row.createCell(12);
			cell.setCellValue(order.getAssetName());
			cell.setCellStyle(cs);
			//����ͺ�
			cell = row.createCell(13);
			cell.setCellValue(order.getAssetType());
			cell.setCellStyle(cs);
			//�ִ���
			if(order.getStatus()==15){
				if(order.getIsSee()==0){
					cell = row.createCell(14);
					cell.setCellValue(order.getNum());
					cell.setCellStyle(cs);
				}else{
					cell = row.createCell(14);
					cell.setCellValue(0);
					cell.setCellStyle(cs);
				}
			}
			//��λ
			cell = row.createCell(15);
			cell.setCellValue(order.getNumUnit());
			cell.setCellStyle(cs);
			//��������
			cell = row.createCell(16);
			if(order.getIsSee()==0){
				cell.setCellValue(0);
			}else {
				cell.setCellValue(order.getNum());
			}
			cell.setCellStyle(cs);
			//����վ��
			cell = row.createCell(17);
			cell.setCellValue(order.getPhysicalSite());
			cell.setCellStyle(cs);
			//״̬
			cell = row.createCell(18);
			if (order.getIsSee()==0) {
				cell.setCellValue("�ִ�");
			} else if (order.getIsSee() == 2) {
				cell.setCellValue("�ѳ���");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cs);
			//��������
			cell = row.createCell(19);
			cell.setCellValue(order.getDepartment());
			cell.setCellStyle(cs);
			//����
			cell = row.createCell(20);
			cell.setCellValue(order.getRegion());
			cell.setCellStyle(cs);
			//վ���ַ
			cell = row.createCell(21);
			cell.setCellValue(order.getSiteAddress());
			cell.setCellStyle(cs);
			//��ⵥ��
			cell = row.createCell(22);
			cell.setCellValue(order.getMobileFactoryNo());
			cell.setCellStyle(cs);
			//�������
			cell = row.createCell(23);
			if (order.getIntoFactoryDate() != null) {
				cell.setCellValue(order.getIntoFactoryDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);

			} else {
				cell.setCellStyle(cs);
			}
			//�߼�վ��
			cell = row.createCell(24);
			cell.setCellValue(order.getLogicSite());
			cell.setCellStyle(cs);
			//�滻����
			cell = row.createCell(25);
			cell.setCellValue(order.getManufacturer());
			cell.setCellStyle(cs);
			//ʩ���ӵ�ַ
			cell = row.createCell(26);
			cell.setCellValue(order.getBuildAddress());
			cell.setCellStyle(cs);
			//ʩ������ϵ��
			cell = row.createCell(27);
			cell.setCellValue(order.getBuildPeople());
			cell.setCellStyle(cs);
			//ʩ������ϵ�绰
			cell = row.createCell(28);
			cell.setCellValue(order.getBuildPhone());
			cell.setCellStyle(cs);
			//������������
			cell = row.createCell(29);
			cell.setCellValue(order.getRegionManage());
			cell.setCellStyle(cs);
			//�ֿⱸע
			cell = row.createCell(30);
			cell.setCellValue(order.getFactoryMark());
			cell.setCellStyle(cs);
			//���ӱ�ע
			cell = row.createCell(31);
			cell.setCellValue(order.getFleetMark());
			cell.setCellStyle(cs);
			//����ʧ��
			cell = row.createCell(32);
			String fail = "";
			if (order.getTransportFail() == 1) {
				fail = "�豸δ���";
			} else if (order.getTransportFail() == 2) {
				fail = "��ҵ��ԭ��";
			} else if (order.getTransportFail() == 3) {
				fail = "�ֳ���������";
			} else if (order.getTransportFail() == 4) {
				fail = "�����Ŵ򲻿�";
			} else if (order.getTransportFail() == 5) {
				fail = "ʩ����δ�걨";
			} else if (order.getTransportFail() == 6) {
				fail = "�豸ԭ��������";
			} else if (order.getTransportFail() == 7) {
				fail = "�豸�����ʲ���ǩ";
			} else if (order.getTransportFail() == 8) {
				fail = "ʩ�����걨ʱ��̫��";
			} else if (order.getTransportFail() == 9) {
				fail = "���ݿ��ѯ�����ʲ���ǩ";
			} else if (order.getTransportFail() == 10) {
				fail = "�ֳ��豸��ǩ�����ݿ��ʲ���ǩ����";
			} else if (order.getTransportFail() == 11) {
				fail = "����";
			} else if(order.getTransportFail() == 12){
				fail = "����δ��";
			}else if(order.getTransportFail() == 13){
				fail = "ʩ��������";
			}else if(order.getTransportFail() == 14){
				fail ="ʩ�������Ͽ���";
			}else if(order.getTransportFail() == 15){
				fail = "�����˿ⵥδ��";
			}
			cell.setCellValue(fail);
			cell.setCellStyle(cs);
			//ԤԼ�ƻ�
			cell = row.createCell(33);
			if (order.getAppointPlan() == 1) {
				cell.setCellValue("�½�");
			} else if (order.getAppointPlan() == 2) {
				cell.setCellValue("����");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cs);
			//ԤԼʱ��
			cell = row.createCell(34);
			if (order.getAppointDate() != null) {
				cell.setCellValue(order.getAppointDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);

			} else {
				cell.setCellStyle(cs);
			}
			//�걨ʱ��
			cell = row.createCell(35);
			if (order.getDeclareDate() != null) {
				cell.setCellValue(order.getDeclareDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);

			} else {
				cell.setCellStyle(cs);
			}
			//�ڿ�ʱ��
			cell = row.createCell(36);
			if (order.getAtFactoryDate() != null) {
				cell.setCellValue(order.getAtFactoryDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);

			} else {
				cell.setCellStyle(cs);
			}
		}
		return wb;
	}
*/
	/**
	 * ���������ü򵥵�Cell��ʽ
	 * 
	 * @return
	 */
	public CellStyle setSimpleCellStyle(Workbook wb) {
		CellStyle cs = wb.createCellStyle();
		cs.setBorderBottom(CellStyle.BORDER_THIN); // �±߿�
		cs.setBorderLeft(CellStyle.BORDER_THIN);// ��߿�
		cs.setBorderTop(CellStyle.BORDER_THIN);// �ϱ߿�
		cs.setBorderRight(CellStyle.BORDER_THIN);// �ұ߿�
		cs.setAlignment(CellStyle.ALIGN_CENTER); // ����
		return cs;
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
