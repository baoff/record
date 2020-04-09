package com.foot.record.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.foot.record.base.BaseController;
import com.foot.record.entity.CountForm;
import com.foot.record.entity.Record;
import com.foot.record.entity.RecordInfoQueryForm;
import com.foot.record.entity.RecordLogger;
import com.foot.record.entity.User;
import com.foot.record.page.PageBean;
import com.foot.record.service.RecordInfoService;
import com.foot.record.service.RecordLoggerService;
import com.foot.record.service.UserInfoService;
import com.foot.record.utils.CheckMobile;
import com.foot.record.utils.ExportExcelUtil;
import com.foot.record.utils.FrameworkBeansName;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import sun.misc.BASE64Decoder;

@Controller
@RequestMapping(FrameworkBeansName.CONTROLLER_RECORDINFO)
public class RecordInfoController extends BaseController {

	private RecordInfoService recordInfoService;
	private RecordLoggerService loggerSerive;
	private UserInfoService userInfoService;

	public RecordInfoService getRecordInfoService() {
		return recordInfoService;
	}

	@Autowired
	public void setOrderInfoService(
			@Qualifier(FrameworkBeansName.RECORD_INFO_SERVICE_BEAN) RecordInfoService recordInfoService) {
		this.recordInfoService = recordInfoService;
	}

	public RecordLoggerService getLoggerSerive() {
		return loggerSerive;
	}

	@Autowired
	public void setUserService(@Qualifier(FrameworkBeansName.USER_INFO_SERVICE_BEAN) UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	@Autowired
	public void setLoggerSerive(
			@Qualifier(FrameworkBeansName.LOGGER_INFO＿SERVICE_BEAN) RecordLoggerService loggerSerive) {
		this.loggerSerive = loggerSerive;
	}

	@InitBinder
	public void InitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		// 不要删除下行注释!!! 将来"yyyy-MM-dd"将配置到properties文件中
		// SimpleDateFormat dateFormat = new
		// SimpleDateFormat(getText("date.format", request.getLocale()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 验证用户,打开添加页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping(params = "method=add_record_form")
	public String addRecordInfoForm(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
		Record record = new Record();
		record.setInitDate(new Date());
		record.setStatus(0);
		model.addAttribute("recordInfoBean", record);
		return "recordInfoAdd.jsp";
	}
	/**
	 * 资产列表
	 * 
	 * @param recordForm
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(params = "method=search_recordInfo")
	public String listRecordInfoAction(@ModelAttribute("orderInfoForm") RecordInfoQueryForm recordForm, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
		String roleName = currentuser.getRole().getName();
		recordForm.setOperatorId(currentuser.getId());
		int isexport = recordForm.getIsexport();
		if (isexport == 1) {
			// 导出
			OutputStream os = null;
			Workbook wb = null;
			// 数据库取值
			try {
				List<Record> records = recordInfoService.getRecordList(recordForm, null);
				ExportExcelUtil exportUtil = new ExportExcelUtil();
				File file = exportUtil.getExcelFile("excelFile/导出模板.xlsx");
				String sheetName = "sheet1";
				wb = exportUtil.writeNewExcel(file, sheetName, records, roleName);
				String fileName = "资产清单.xlsx";
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-disposition",
						"attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
				os = response.getOutputStream();
				wb.write(os);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				os.flush();
				os.close();
			}
			return null;
		}
		PageBean page = new PageBean();
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}

		List<Record> records = recordInfoService.getRecordList(recordForm,page);
		for (int i = 0; records != null && i < records.size(); i++) {
			Record record = records.get(i);
			long userId = record.getOperatorId();
			User user = userInfoService.findUser(userId);
			if (user != null) {
				record.setNickname(user.getNickname());
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("recordLists", records);
		model.addAttribute("recordInfoForm", recordForm);
		return "infolist.jsp";
	}
	
	/**
	 * 资产添加
	 * 
	 * @param recordInfo
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=add_recordinfo_action")
	public String addOrderInfoAction(@ModelAttribute("recordInfoBean") Record recordInfo, ModelMap model,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
		//部门唯一性检查
		String department = recordInfo.getDepartment();
		if(department == null || department.trim().equals("")){
			model.addAttribute("addMessage", "no department");
			return "recordInfoAdd.jsp";
		}
		Record record = recordInfoService.getRecordByProperty("department", department);
		if(record != null){
			model.addAttribute("addMessage", "has department");
			return "recordInfoAdd.jsp";
		}
		recordInfo.setOperatorId(currentuser.getId());
		recordInfo.setModifyDate(new Date());
		recordInfo.setInitDate(new Date());
		recordInfo.setDepartment(department.trim());
		recordInfoService.addRecord(recordInfo);
		//添加日志
		try {
			RecordLogger log = new RecordLogger();
			log.setRecordId(recordInfo.getId());
			log.setOrderNumber(recordInfo.getOrderNumber());
			log.setOrigin(recordInfo.getStatus());
			log.setGoal(recordInfo.getStatus());
			log.setType(1);
			log.setOperatorId(currentuser.getId());
			log.setDescription("添加资产");
			log.setCreateDate(new Date());
			loggerSerive.addLogger(log);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("addMessage", "success");
		return "recordInfoAdd.jsp";
	}

	/**
	 * 资产删除
	 * 
	 * @param orderId
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=delete_recordinfo_action")
	public void deleteRecordInfo(@RequestParam("recordId") long recordId, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		model.addAttribute("messages", "");
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			out.print("no user");
			return;
		}
		String message = "success";
		Record record = recordInfoService.getRecordById(recordId);
		if (record.getStatus() == 1) {
			message = "inFactory";
		} else {
			recordInfoService.deleteRecord(recordId);
			try{
				RecordLogger log = new RecordLogger();
				log.setOrderNumber(record.getOrderNumber());
				log.setRecordId(recordId);
				log.setOperatorId(currentuser.getId());
				log.setCreateDate(new Date());
				log.setDescription("删除资产");
				log.setGoal(record.getStatus());
				log.setOrigin(record.getStatus());
				log.setType(2);
				loggerSerive.addLogger(log);
			}catch(Exception e){
				
			}
		}
		out.print(message);
		return;
	}
	
	/**
	 * 资产修改页面
	 * 
	 * @param recordId
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=edit_recordinfo_form")
	public String EditRecordInfoForm(@RequestParam("recordId") long recordId, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String message = "";
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
		Record record = recordInfoService.getRecordById(recordId);
		if (record.getStatus() == 1) {
			model.addAttribute("messages", "inFactory");
			return listRecordInfoAction(new RecordInfoQueryForm(), model, request, response);
		}
		model.addAttribute("messages", message);
		model.addAttribute("editeRecord", record);
		return "recordInfoEdit.jsp";
	}
	
	/**
	 * 修改保存
	 * 
	 * @param record
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=edit_recordinfo_action")
	public String saveOrderInfo(@ModelAttribute("editeRecord") Record record, ModelMap model, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
	
		record.setOperatorId(currentuser.getId());
		record.setModifyDate(new Date());
		recordInfoService.updateRecord(record);
		try{
			RecordLogger log = new RecordLogger();
			log.setOrderNumber(record.getOrderNumber());
			log.setRecordId(record.getId());
			log.setOperatorId(currentuser.getId());
			log.setCreateDate(new Date());
			log.setDescription("修改资产");
			log.setGoal(record.getBackType());
			log.setOrigin(record.getStatus());
			log.setType(3);
			loggerSerive.addLogger(log);
		}catch(Exception e){
			
		}
		model.addAttribute("editMessage", "success");
		return "recordInfoEdit.jsp";
	}
	
	/**
	 * 移动统计
	 * 
	 * @param form
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=count_recordInfo_action")
	public String recordCount(@ModelAttribute("countForm") CountForm form, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
		String department = form.getDepartment();
		String region = form.getRegion();
		int customerType = form.getCustomerType();
		String startDate = form.getStartDate();
		String endDate = form.getEndDate();
		String wxNumber = form.getWxNumber();
		int returnTaxPercentage = form.getReturnTaxPercentage();
		int orderPercentage = form.getOrderPercentage();
//		int status = form.getStatus();
		// 查询数量
		StringBuffer sb = new StringBuffer();
		// if (status > -1) {
		// sb.append(" and status = " + status);
		// }
	
		if (department != null && department.trim().length() > 0) {
			sb.append(" and department like '%").append(department).append("%'");
		}
		if (region != null && region.trim().length() > 0) {
			sb.append(" and region like '%").append(region).append("%'");
		}
		if (wxNumber != null && wxNumber.trim().length() > 0) {
			sb.append(" and wxNumber like '%").append(wxNumber).append("%'");
		}
		
		if (customerType > 0 && customerType < 20) {
			sb.append(" and customerType= " + customerType);
		}
		if (customerType == 20) {
			sb.append(" and customerType >0");
		}
		
		if (returnTaxPercentage > 0 && returnTaxPercentage < 20) {
			sb.append(" and returnTaxPercentage= " + returnTaxPercentage);
		}
		if (returnTaxPercentage == 20) {
			sb.append(" and returnTaxPercentage >0");
		}
		if (orderPercentage > 0 && orderPercentage < 20) {
			sb.append(" and orderPercentage= " + orderPercentage);
		}
		if (orderPercentage == 20) {
			sb.append(" and orderPercentage >0");
		}
		if (startDate != null && startDate.trim().length() > 0) {
			sb.append(" and initDate >=str_to_date('" + startDate + "','%Y-%m-%d %H:%I:%S')");
		}
		if (endDate != null && endDate.trim().length() > 0) {
			sb.append(" and initDate <=str_to_date('" + endDate + "','%Y-%m-%d %H:%I:%S')");
		}

		// 总资产数
		String sql_asset_zong = "select count(*) from to_record where 1= 1" + sb.toString();
		//已完成的
		String sql_asset_1 = "select count(*) from to_record where 1= 1" + sb.toString() + " and status = 1";
		//注册中		
		String sql_asset_2 = "select count(*) from to_record where 1= 1" + sb.toString() + " and status = 2";
		int countAsset_zong = recordInfoService.getCount(sql_asset_zong);
		int countAsset_1 = recordInfoService.getCount(sql_asset_1);
		int countAsset_2 = recordInfoService.getCount(sql_asset_2);
		
		// 时间段
		String sqlDate_zong = "select modifyDate from to_record where 1=1 " + sb.toString()
				+ " order by initDate desc";
		
		String sqlDate_1 = "select initDate from to_record where 1=1 " + sb.toString() + " and status = 1"
				+ " order by initDate desc";
		
		String sqlDate_2 = "select initDate from to_record where 1=1 " + sb.toString() + " and status = 2"
				+ " order by initDate desc";
		
		List dateList_zong = recordInfoService.getProperty(sqlDate_zong);
		List dateList_1 = recordInfoService.getProperty(sqlDate_1);
		List dateList_2 = recordInfoService.getProperty(sqlDate_2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 总时间
		String start_zong = "";
		String end_zong = "";
		if (dateList_zong != null && dateList_zong.size() > 0) {
			Date eDate = (Date) dateList_zong.get(0);
			Date sDate = null;
			if (dateList_zong.size() > 1) {
				sDate = (Date) dateList_zong.get(dateList_zong.size() - 1);
			} else {
				sDate = eDate;
			}
			start_zong = sdf.format(sDate);
			end_zong = sdf.format(eDate);
		}

		if (startDate != null && startDate.trim().length() > 0) {
			start_zong = startDate;
		}
		if (endDate != null && endDate.trim().length() > 0) {
			end_zong = endDate;
		}
		// 0时间
		String start_1 = "";
		String end_1 = "";
		if (dateList_1 != null && dateList_1.size() > 0) {
			Date eDate = (Date) dateList_1.get(0);
			Date sDate = null;
			if (dateList_1.size() > 1) {
				sDate = (Date) dateList_1.get(dateList_1.size() - 1);
			} else {
				sDate = eDate;
			}
			start_1 = sdf.format(sDate);
			end_1 = sdf.format(eDate);
		}

		if (startDate != null && startDate.trim().length() > 0) {
			start_1 = startDate;
		}
		if (endDate != null && endDate.trim().length() > 0) {
			end_1 = endDate;
		}
		// 3时间
		String start_2 = "";
		String end_2 = "";
		if (dateList_2 != null && dateList_2.size() > 0) {
			Date eDate = (Date) dateList_2.get(0);
			Date sDate = null;
			if (dateList_2.size() > 1) {
				sDate = (Date) dateList_2.get(dateList_2.size() - 1);
			} else {
				sDate = eDate;
			}
			start_2 = sdf.format(sDate);
			end_2 = sdf.format(eDate);
		}

		if (startDate != null && startDate.trim().length() > 0) {
			start_2 = startDate;
		}
		if (endDate != null && endDate.trim().length() > 0) {
			end_2 = endDate;
		}
		model.addAttribute("countAsset_zong", countAsset_zong);
		model.addAttribute("countAsset_1", countAsset_1);
		model.addAttribute("countAsset_2", countAsset_2);
		model.addAttribute("start_zong", start_zong);
		model.addAttribute("start_1", start_1);
		model.addAttribute("start_2", start_2);
		model.addAttribute("end_zong", end_zong);
		model.addAttribute("end_1", end_1);
		model.addAttribute("end_2", end_2);
		return "recordCount.jsp";
	}
	/**
	 * 清单导入
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=upload_recordinfo_action")
	public String uploadOrderInfo(@RequestParam(value = "importExcel", required = false) MultipartFile file,
			ModelMap model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User currentuser = (User) session.getAttribute("currentuser");
		if (currentuser == null) {
			return "index.jsp";
		}
		// 处理excel
		String result = recordInfoService.readExcelFile(file, currentuser.getId());
		model.addAttribute("result", result);
		return "recordInfoUpload.jsp";
	}

}
