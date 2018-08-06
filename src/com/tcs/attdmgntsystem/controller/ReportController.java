package com.tcs.attdmgntsystem.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tcs.attdmgntsystem.manager.AssociateManager;
import com.tcs.attdmgntsystem.model.DemoReport;
import com.tcs.attdmgntsystem.model.ModelDemo;
import com.tcs.attdmgntsystem.model.Report;
import com.tcs.attdmgntsystem.service.AssociateService;

@Controller
public class ReportController {
	@Autowired
	AssociateService service;
	@Autowired
	AssociateManager manager;
	//binds the date web request parameter to attribute in JavaBean
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	//fetching demo list from database
	@RequestMapping(value="/report" ,method=RequestMethod.GET)
	public String add(Model model)
	{
		DemoReport demoReport = new DemoReport();
		model.addAttribute("modelReport", demoReport);
		model.addAttribute("DemoList",manager.getAllDemo());
		return "report";
	}
	//report generation
	@RequestMapping(value="/displayReport" ,method=RequestMethod.POST)
	public String displayReport(@ModelAttribute("modelReport") DemoReport demoReport,Model model) throws ParseException{
		String string=null;
		String sFromDate= null;
		String sToDate= null;
		String dName=null;
		String reportHead=null;
		Date dFromDate=demoReport.getFromDate();
		Date dToDate=demoReport.getToDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(dFromDate!=null&&dToDate!=null){
			sFromDate= dateFormat.format(dFromDate);
			sToDate=dateFormat.format(dToDate);
		}
		ModelAndView mv=new ModelAndView();
		model.addAttribute("modelReport", demoReport);
		model.addAttribute("DemoList",manager.getAllDemo());
		ArrayList<Report> reportList=new ArrayList<Report>();
		reportList=service.getReport(demoReport);
		if(reportList.size()>0)
		{  
			dName=reportList.get(0).getDemoName();
			model.addAttribute("reportList",reportList);
		}
		else
		{
			model.addAttribute("reportList", "LISTEMPTY");
		}
		if(!demoReport.getDemoName().equalsIgnoreCase("Select")){
			if(dFromDate!=null&&dToDate!=null){
				reportHead="Report for :\r\r Demo: "+ dName+"\n"+" to the Dates between "+sFromDate+" to "+sToDate;
			}
			else reportHead="Report for Demo: "+dName;
			string="ReportOne";
		}
		else {
			reportHead="Report for the Dates between "+sFromDate+" to "+sToDate;
			string="ReportTwo";
		}
		model.addAttribute("reportHead", reportHead);
		return string;
	}
	@RequestMapping(value="/notice" ,method=RequestMethod.GET)
	public String notice(Model model){
		model.addAttribute("demo",manager.getLastDemoDetails());
		ArrayList<ModelDemo> md=manager.getNextDemo();
		if(md.size()>0){
			model.addAttribute("nextDemo",md);
		}
		else model.addAttribute("nextDemo","empty");
		return "home";
	}
}