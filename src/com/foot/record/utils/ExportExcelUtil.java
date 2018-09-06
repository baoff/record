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
			throw new Exception("模板文件不存在！");
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
		// 循环插入数据
		int lastRow = sheet.getLastRowNum() + 1; // 插入数据的数据ROW
		CellStyle cs = setSimpleCellStyle(wb); // Excel单元格样式
		for (int i = 0; i < lis.size(); i++) {
			row = sheet.createRow(lastRow + i); // 创建新的ROW，用于数据插入
			// 按项目实际需求，在该处将对象数据插入到Excel中
			Record record = lis.get(i);
			if (record == null) {
				break;
			}
			// Cell赋值开始
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
				cell.setCellValue("成交客户");
			}else if(record.getCustomerType()==2){
				cell.setCellValue("商讨客户");
			}
			cell.setCellStyle(cs);
			
			cell = row.createCell(12);
			cell.setCellValue(record.getWxNumber());
			cell.setCellStyle(cs);
			
			cell = row.createCell(13);
			cell.setCellValue(record.getOrderNumber());
			cell.setCellStyle(cs);
			
			cell = row.createCell(14);
			cell.setCellValue(record.getStar()+"星");
			cell.setCellStyle(cs);
			
			cell = row.createCell(15);
			String status = "注册中";
			if (record.getStatus() == 1) {
				status = "已完成";
			} else if (record.getStatus() == 2) {
				status = "注册中";
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
		// 循环插入数据
		int lastRow = sheet.getLastRowNum() + 1; // 插入数据的数据ROW
		CellStyle cs = setSimpleCellStyle(wb); // Excel单元格样式
		for (int i = 0; i < lis.size(); i++) {
			row = sheet.createRow(lastRow + i); // 创建新的ROW，用于数据插入
			// 按项目实际需求，在该处将对象数据插入到Excel中
			NBOrder order = lis.get(i);
			if (order == null) {
				break;
			}
			// Cell赋值开始
			//出库单号
			cell = row.createCell(0);
			cell.setCellValue(order.getMobileOutFactoryNo());
			cell.setCellStyle(cs);
			//出库日期
			cell = row.createCell(1);
			if (order.getOutFactoryDate() != null) {
				cell.setCellValue(order.getOutFactoryDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);
			} else {
				cell.setCellStyle(cs);
			}
			//出库属性
			cell = row.createCell(2);
			if (order.getOutFactoryProp() == 1) {
				cell.setCellValue("报废");
			} else if (order.getOutFactoryProp() == 2) {
				cell.setCellValue("利旧");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cs);
			//出库交接
			cell = row.createCell(3);
			cell.setCellValue(order.getOutFactoryName());
			cell.setCellStyle(cs);
			//腔体
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
			//库位
			cell = row.createCell(7);
			cell.setCellValue(order.getLibrary());
			cell.setCellStyle(cs);
			//载频数
			cell = row.createCell(8);
			cell.setCellValue(order.getCarryNum());
			cell.setCellStyle(cs);
			//天线数
			cell = row.createCell(9);
			cell.setCellValue(order.getFeederNum());
			cell.setCellStyle(cs);
			//资产标签号
			cell = row.createCell(10);
			cell.setCellValue(order.getAssetTagNum());
			cell.setCellStyle(cs);
			//资产编号
			cell = row.createCell(11);
			cell.setCellValue(order.getAssetNum());
			cell.setCellStyle(cs);
			//资产名称
			cell = row.createCell(12);
			cell.setCellValue(order.getAssetName());
			cell.setCellStyle(cs);
			//规格型号
			cell = row.createCell(13);
			cell.setCellValue(order.getAssetType());
			cell.setCellStyle(cs);
			//现存量
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
			//单位
			cell = row.createCell(15);
			cell.setCellValue(order.getNumUnit());
			cell.setCellStyle(cs);
			//出库数量
			cell = row.createCell(16);
			if(order.getIsSee()==0){
				cell.setCellValue(0);
			}else {
				cell.setCellValue(order.getNum());
			}
			cell.setCellStyle(cs);
			//物理站名
			cell = row.createCell(17);
			cell.setCellValue(order.getPhysicalSite());
			cell.setCellStyle(cs);
			//状态
			cell = row.createCell(18);
			if (order.getIsSee()==0) {
				cell.setCellValue("现存");
			} else if (order.getIsSee() == 2) {
				cell.setCellValue("已出库");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cs);
			//开单部门
			cell = row.createCell(19);
			cell.setCellValue(order.getDepartment());
			cell.setCellStyle(cs);
			//区域
			cell = row.createCell(20);
			cell.setCellValue(order.getRegion());
			cell.setCellStyle(cs);
			//站点地址
			cell = row.createCell(21);
			cell.setCellValue(order.getSiteAddress());
			cell.setCellStyle(cs);
			//入库单号
			cell = row.createCell(22);
			cell.setCellValue(order.getMobileFactoryNo());
			cell.setCellStyle(cs);
			//入库日期
			cell = row.createCell(23);
			if (order.getIntoFactoryDate() != null) {
				cell.setCellValue(order.getIntoFactoryDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);

			} else {
				cell.setCellStyle(cs);
			}
			//逻辑站名
			cell = row.createCell(24);
			cell.setCellValue(order.getLogicSite());
			cell.setCellStyle(cs);
			//替换厂商
			cell = row.createCell(25);
			cell.setCellValue(order.getManufacturer());
			cell.setCellStyle(cs);
			//施工队地址
			cell = row.createCell(26);
			cell.setCellValue(order.getBuildAddress());
			cell.setCellStyle(cs);
			//施工队联系人
			cell = row.createCell(27);
			cell.setCellValue(order.getBuildPeople());
			cell.setCellStyle(cs);
			//施工队联系电话
			cell = row.createCell(28);
			cell.setCellValue(order.getBuildPhone());
			cell.setCellStyle(cs);
			//厂商区域负责人
			cell = row.createCell(29);
			cell.setCellValue(order.getRegionManage());
			cell.setCellStyle(cs);
			//仓库备注
			cell = row.createCell(30);
			cell.setCellValue(order.getFactoryMark());
			cell.setCellStyle(cs);
			//车队备注
			cell = row.createCell(31);
			cell.setCellValue(order.getFleetMark());
			cell.setCellStyle(cs);
			//回运失败
			cell = row.createCell(32);
			String fail = "";
			if (order.getTransportFail() == 1) {
				fail = "设备未拆除";
			} else if (order.getTransportFail() == 2) {
				fail = "物业方原因";
			} else if (order.getTransportFail() == 3) {
				fail = "现场无三联单";
			} else if (order.getTransportFail() == 4) {
				fail = "机房门打不开";
			} else if (order.getTransportFail() == 5) {
				fail = "施工队未申报";
			} else if (order.getTransportFail() == 6) {
				fail = "设备原先已拉走";
			} else if (order.getTransportFail() == 7) {
				fail = "设备上无资产标签";
			} else if (order.getTransportFail() == 8) {
				fail = "施工队申报时间太晚";
			} else if (order.getTransportFail() == 9) {
				fail = "数据库查询不到资产标签";
			} else if (order.getTransportFail() == 10) {
				fail = "现场设备标签与数据库资产标签不行";
			} else if (order.getTransportFail() == 11) {
				fail = "其它";
			} else if(order.getTransportFail() == 12){
				fail = "材料未拆";
			}else if(order.getTransportFail() == 13){
				fail = "施工队无人";
			}else if(order.getTransportFail() == 14){
				fail ="施工队无料可退";
			}else if(order.getTransportFail() == 15){
				fail = "督导退库单未带";
			}
			cell.setCellValue(fail);
			cell.setCellStyle(cs);
			//预约计划
			cell = row.createCell(33);
			if (order.getAppointPlan() == 1) {
				cell.setCellValue("新建");
			} else if (order.getAppointPlan() == 2) {
				cell.setCellValue("集中");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cs);
			//预约时间
			cell = row.createCell(34);
			if (order.getAppointDate() != null) {
				cell.setCellValue(order.getAppointDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);

			} else {
				cell.setCellStyle(cs);
			}
			//申报时间
			cell = row.createCell(35);
			if (order.getDeclareDate() != null) {
				cell.setCellValue(order.getDeclareDate());
				CellStyle csa = wb.createCellStyle();
				csa.setDataFormat(wb.createDataFormat().getFormat("yyyy/mm/dd"));
				cell.setCellStyle(csa);

			} else {
				cell.setCellStyle(cs);
			}
			//在库时间
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
	 * 描述：设置简单的Cell样式
	 * 
	 * @return
	 */
	public CellStyle setSimpleCellStyle(Workbook wb) {
		CellStyle cs = wb.createCellStyle();
		cs.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
		cs.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		cs.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		cs.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		cs.setAlignment(CellStyle.ALIGN_CENTER); // 居中
		return cs;
	}

	// @描述：是否是2003的excel，返回true是2003
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	// @描述：是否是2007的excel，返回true是2007
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
