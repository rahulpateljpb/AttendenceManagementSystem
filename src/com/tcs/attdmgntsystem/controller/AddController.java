package com.tcs.attdmgntsystem.controller;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.service.AssociateService;

@Controller
public class AddController {
	@Autowired
	AssociateService service;
	//for setting values on basis of associate present in database or not.
	int checking,getnumberFromDatabase=2;
	//for updated list of associate
	@RequestMapping(value="/view")
	public String viewAssociate(Model model){
		ModelAssociate ma=new ModelAssociate();
		//return associate update list
		ArrayList<ModelAssociate> allAssociateList= returnAssociateList();
		checking=2;
		if(allAssociateList.size()>0){
			checking=getnumberFromDatabase;
			System.out.println(checking);
			if(checking==1){
				model.addAttribute("success", "Associate added successfully.");
				getnumberFromDatabase=2;
			}
			else if (checking==0) {
				model.addAttribute("success", "Associate Id already exists.");
				getnumberFromDatabase=2;
			}
			model.addAttribute("allAssociates", allAssociateList);
		}
		else
			model.addAttribute("allAssociates", "No Associate present in database.");
		model.addAttribute("ModelAssociate",ma);
		return "addAssociate";
	}
	//for adding a associate into database
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addAssociates(@Valid@ModelAttribute("ModelAssociate") ModelAssociate modelassociate,BindingResult result,Model model){
		if(result.hasErrors()){
			ArrayList<ModelAssociate> allAssociateList= returnAssociateList();
			if(allAssociateList.size()>0)
				//return associate list
				model.addAttribute("allAssociates", allAssociateList);
			else
				//if no associate in database
				model.addAttribute("allAssociates", "No Associate present in database.");
			return "addAssociate";
		}
		//adding associate into database
		getnumberFromDatabase=service.addAssociateService(modelassociate);
		return "redirect:view";
	}
	//for getting list of all associate present in database
	public ArrayList<ModelAssociate> returnAssociateList(){
		ArrayList<ModelAssociate> allAssociateList= service.getAllAssociate();
		return allAssociateList;
	}
}

// In case using js validation replace addAssociates method with this one
/*@RequestMapping(value="/add",method=RequestMethod.POST)
public String addAssociates(@ModelAttribute("ModelAssociate") ModelAssociate modelassociate){
	if(result.hasErrors()){
		ArrayList<ModelAssociate> allAssociateList= returnAssociateList();
		//return associate list
		model.addAttribute("allAssociates", allAssociateList);
		System.out.println("form contain error");
		return "addAssociate";
	}
	//adding associate into database
	getnumberFromDatabase=service.addAssociateService(modelassociate);
	System.out.println("success in addcontroller and value of getnumber is "+getnumberFromDatabase);
	return "redirect:view";
}*/ 