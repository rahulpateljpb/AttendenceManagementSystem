package com.tcs.attdmgntsystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tcs.attdmgntsystem.model.DemoDetailModel;
import com.tcs.attdmgntsystem.model.SendingDemoModel;
import com.tcs.attdmgntsystem.service.AssociateService;

@Controller
public class DemoController {
	@Autowired
	AssociateService service;
	int demo=0;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	@RequestMapping(value="/addDemo")
	public String addDemo(Model model){
		DemoDetailModel ddm=new DemoDetailModel();
		ArrayList<SendingDemoModel> demolist=service.getDemo();
		ArrayList<String> timeList=service.getTimeList();
		if(demo==1){
			model.addAttribute("success", "Demo Added successfully.");
			demo=0;
		}
		if(demolist.size()>0)
			model.addAttribute("demoList", demolist);
		else 
			model.addAttribute("demoList", "No demo in database");
		model.addAttribute("timeList", timeList);
		model.addAttribute("DemoDetailModel",ddm);
		return "addDemo";		
	}

	@RequestMapping(value="/addDetail")
	public String addDetail(@Valid@ModelAttribute("DemoDetailModel") DemoDetailModel demodetail,BindingResult result,Model model)
	{
		demo=service.addDemoDService(demodetail);
		//	model.addAttribute("message","Successfully added.");
		System.out.println("returning");
		return "redirect:addDemo";
	}
}
