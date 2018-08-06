package com.tcs.attdmgntsystem.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat; //new
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;	//new
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.tcs.attdmgntsystem.dao.AssociateDaoImpl;
import com.tcs.attdmgntsystem.entity.Associate_Demo;
import com.tcs.attdmgntsystem.model.DemoModel;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelAttendance;	
import com.tcs.attdmgntsystem.model.ModelDemo;
import com.tcs.attdmgntsystem.service.AssociateService;

@Controller
public class AttendanceController  extends HttpServlet {
	@Autowired
	AssociateService Service;
	ArrayList<ModelAssociate> assDetails= new ArrayList<ModelAssociate>();
	ArrayList<DemoModel> demoDetails= new ArrayList<DemoModel>();
	ArrayList<ModelAssociate> PresentAssociateList = new ArrayList<ModelAssociate>();
	ArrayList<Associate_Demo> PresentAssoicateDataArrayList = new ArrayList<Associate_Demo>();
	ArrayList<ModelAssociate> retainAssDetails= new ArrayList<ModelAssociate>(); 
	int newdata=0;
	//code for landing on single jsp please do not delete	
	@RequestMapping(value="/addAttendance")
	public String viewAttendance(Model model,HttpServletRequest request)
	{
		 HttpSession session = request.getSession();
		 session.setAttribute("flagAjax", "false");
		 session.setAttribute("demoIdname", "");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String cDate = dateFormat.format(date);
		model.addAttribute("currDate", cDate);
		ModelDemo modelDemo = new ModelDemo();
		assDetails.clear();
		retainAssDetails.clear(); 
		model.addAttribute("ModelDemo", modelDemo);
		return"addAttendance";
	}  
	//code for autopopulating demo name
	@RequestMapping(value="/addAttendance1")
	public void viewDemo(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("application/json");
		try {
			DemoModel modeldemo = new DemoModel();
			String term = request.getParameter("term");
			ArrayList<DemoModel> list = Service.getDemo(term);
			Gson json = new Gson();
			String searchList = new Gson().toJson(list);
			response.getWriter().write(searchList);
			demoDetails.addAll(list);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//adding a demo in database, ayush
	public String addDemo(Integer associateId, Integer demoInteger){
		ModelDemo modDem =new ModelDemo();
		ModelAssociate modAss =new ModelAssociate();
		ModelAttendance modAtten = new ModelAttendance();
		modAss.setAssociateId(associateId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//get current date time with Date()
		Date date = new Date();
		String cur_date = dateFormat.format(date);
		Date current_date = null;
		try {
			current_date = dateFormat.parse(cur_date);	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		modAtten.setAssociateId(associateId);
		modAtten.setDemoId(demoInteger);
		//			modAtten.setDemoDate(current_date);
		String controller_string = Service.addAttendanceService(modAtten);
		return controller_string;
	}
	//Viral method added for Auto Populate Associate Id
	@RequestMapping(value="/searched", method = RequestMethod.POST)
	public void getSearchedAssociateId(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		response.setContentType("application/json");
		String AssociateId = request.getParameter("term"); 
		ArrayList<ModelAssociate> autoPopulateAssociatelist = Service.getAllAssociateList(AssociateId);
		String searchList = new Gson().toJson(autoPopulateAssociatelist);
		response.getWriter().print(searchList);
		HttpSession session = request.getSession();
		session.setAttribute("AutoPopulate", autoPopulateAssociatelist); 
	}	
	//ayush function for viewing through ajax
	@RequestMapping(value ="/employee", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ModelAssociate> add(HttpServletRequest request, HttpServletResponse response)throws Exception{
		List<ModelAssociate> autoPopulateList=new ArrayList<ModelAssociate>();
		ArrayList<ModelAssociate> tempSaveAssociateDetails = new ArrayList<>(); 
		autoPopulateList=(List<ModelAssociate>) request.getSession().getAttribute("AutoPopulate");
		int noAddFlag=0;
		String s= request.getParameter("AssociateId");
		int parsedInteger = Integer.parseInt(s);
		for(int listIterator = 0;listIterator<autoPopulateList.size();listIterator++){
			if(autoPopulateList.get(listIterator).getAssociateId()==parsedInteger){
				ModelAssociate mod1 = new ModelAssociate();
				mod1 = autoPopulateList.get(listIterator);
				tempSaveAssociateDetails.add(mod1);
			}
		}
		assDetails.addAll(tempSaveAssociateDetails);
		retainAssDetails.addAll(tempSaveAssociateDetails);
		if(PresentAssociateList.size()==0 && tempSaveAssociateDetails.size()!=0 )
		{
			newdata = 1;
			PresentAssociateList.addAll(tempSaveAssociateDetails);
		}
		else if(newdata==1){
			for(ModelAssociate checkNewAssoc:PresentAssociateList){

				if(checkNewAssoc.getAssociateId().equals(tempSaveAssociateDetails.get(0).getAssociateId())){
					noAddFlag=1;
					break;
				}
			}
			if(noAddFlag!=1){
				PresentAssociateList.addAll(tempSaveAssociateDetails);
			}
		}
		else{
			for(ModelAssociate checkingAssociate:tempSaveAssociateDetails){
				for(ModelAssociate checkingAssociate2:PresentAssociateList){
					if(checkingAssociate.getAssociateId().equals(checkingAssociate2.getAssociateId())){
						assDetails.remove(checkingAssociate);
						noAddFlag=1;
						break;
					}
				}
				if(noAddFlag!=1){
					PresentAssociateList.addAll(tempSaveAssociateDetails);
				}
			}
		}
		return retainAssDetails;
	}
	//mark attendance function
	@RequestMapping(value="/addAssociate", method = RequestMethod.POST)
	public String add(HttpServletRequest request,HttpServletResponse response,Model model, @ModelAttribute("ModelDemo") ModelDemo modelDemo){
		Integer id=modelDemo.getDemoId();
		String addedDemo =null;
		if(assDetails.size()!=0){
			if(newdata==1){
				assDetails.removeAll(assDetails);
				assDetails.addAll(PresentAssociateList);
			}
			for (ModelAssociate modelAssociate : assDetails) {
				Integer associateId = modelAssociate.getAssociateId();
				addedDemo = addDemo(associateId, id);
				newdata=0;
			}
		}	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String cDate = dateFormat.format(date);
		model.addAttribute("currDate", cDate);
		modelDemo.setDemoName(null);
		assDetails.removeAll(assDetails);
		model.addAttribute("checkerString", 1);
		return "addAttendance";
	}
	
	
	
	// function for onload associate when demo name selected
	@RequestMapping(value ="/sendingDemoId", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ModelAssociate> sendingDemoIdToJs(@ModelAttribute("ModelDemo") ModelDemo modelDemoJsp,
			HttpServletRequest request, HttpServletResponse response,Model model)throws Exception{
		//viral code
		String Id = request.getParameter("demoId");
		int demoId = Integer.parseInt(Id);
		newdata=0;
		if(PresentAssoicateDataArrayList.size()!=0){
			PresentAssoicateDataArrayList.removeAll(PresentAssoicateDataArrayList);
		}
		AssociateDaoImpl dao1 = new AssociateDaoImpl();
		PresentAssoicateDataArrayList = dao1.getAllAvailableAssociate(demoId);
		PresentAssociateList =dao1.getAssociateFromDemoId(PresentAssoicateDataArrayList);
		//temp.addAll(PresentAssociateList);
		HttpSession session = request.getSession();
		session.setAttribute("flagAjax", "false" );
		session.setAttribute("demoIdname", "" );
		retainAssDetails.addAll(PresentAssociateList); 
		return PresentAssociateList;
	}
	
	
	// function for auto selected Dates when demo name selected
	@RequestMapping(value ="/sendingDates", method = RequestMethod.POST)
	public String sendingDatestoForm(@ModelAttribute("ModelDemo") ModelDemo modelDemoJsp,@RequestParam("FlagForajaxcall") String FlagForajaxcall,HttpServletRequest request, HttpServletResponse response,Model model)throws Exception{

		String Id = request.getParameter("demoIdname");
		String flagAjax = "true";
		int demoId = Integer.parseInt(Id);
		AssociateDaoImpl dao1 = new AssociateDaoImpl();
		ModelDemo demoObjectWithId = dao1.getDemoObjectwithId(demoId);
		String trimStartDate=demoObjectWithId.getStartDateTime();
		String trimEndDate = demoObjectWithId.getEndDateTime();
		String[] arrayOfStartDate = trimStartDate.split("\\s+");
		String[] arrayOfEndDate = trimEndDate.split("\\s+");
		demoObjectWithId.setStartDateTime(arrayOfStartDate[0]);
		demoObjectWithId.setEndDateTime(arrayOfEndDate[0]);
		HttpSession session = request.getSession();
		session.setAttribute("flagAjax", flagAjax);
		session.setAttribute("demoIdname", Id);
		model.addAttribute("ModelDemo",demoObjectWithId);
		return "addAttendance";
	} 
}