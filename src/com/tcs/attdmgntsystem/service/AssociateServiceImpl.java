package com.tcs.attdmgntsystem.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tcs.attdmgntsystem.dao.AssociateDao;
import com.tcs.attdmgntsystem.entity.Associate;
import com.tcs.attdmgntsystem.entity.Demo;
import com.tcs.attdmgntsystem.manager.AssociateManager;
import com.tcs.attdmgntsystem.model.DemoReport;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelDemo;
import com.tcs.attdmgntsystem.model.Report;
import com.tcs.attdmgntsystem.model.SendingDemoModel;
import com.tcs.attdmgntsystem.model.ModelAttendance;
import com.tcs.attdmgntsystem.model.DemoDetailModel;
import com.tcs.attdmgntsystem.model.DemoModel;
@Service
public class AssociateServiceImpl implements AssociateService{
	@Autowired
	AssociateDao dao;
	@Autowired
	AssociateManager manager;
	//Retrieve all associate list from database
	public ArrayList<ModelAssociate> getAllAssociate() {
		ArrayList<Associate> associateEntityList= dao.getAllAssociate();
		ArrayList<ModelAssociate> associateModelList= convertEntityAssociateToModelAssociate(associateEntityList);
		return associateModelList;
	}
	//Retrieve all demo
	public List<ModelDemo> getAllDemo() {
		List<Demo> demoEntityList=dao.getAllDemo();
		List<ModelDemo> demoModelList=convertEntityToModelDemo(demoEntityList);
		return demoModelList;
	}

	//convert entity to model
	private List<ModelDemo> convertEntityToModelDemo(List<Demo> demoEntityList) {
		List<ModelDemo> dList=new ArrayList<ModelDemo>();
		for(Demo dm: demoEntityList){
			ModelDemo md=new ModelDemo();
			md.setDemoId(dm.getDemoId());
			md.setDemoName(dm.getDemoName());
			Date d=dm.getStartDateTime();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			md.setDemoDate(df.format(d));
			dList.add(md);
		}
		return dList;
	} 
	//It pass associate details  
	@Override
	public int addAssociateService(ModelAssociate modelassociate) {
		return dao.addAssociateDao(modelassociate);
	}
	//Retrieve report
	public ArrayList<Report> getReport(DemoReport demoReport) {
		return dao.getReport(demoReport);
	}
	//This method convert all entity associate to model associate
	public ArrayList<ModelAssociate> convertEntityAssociateToModelAssociate(ArrayList<Associate> assoEntityList){
		ArrayList<ModelAssociate> assList=new ArrayList<ModelAssociate>();
		for(Associate object: assoEntityList)
		{
			ModelAssociate modelAssociate=new ModelAssociate();
			modelAssociate.setAssociateId(object.getAssociateId());
			modelAssociate.setAssociateName(object.getAssociateName());
			modelAssociate.setContactNumber(object.getAssociateContact());
			modelAssociate.setEmailId(object.getAssociateEmail());
			assList.add(modelAssociate);
		}
		return assList;
	}

	//add attendance function implementation
	public String addAttendanceService(ModelAttendance modAtten){
		String service_string=dao.addAttendenceDao(modAtten);
		return service_string;
	}

	//view associates function implementation in add attendance
	public ModelAssociate viewAssociateService(ModelAttendance modAtten){
		ModelAssociate modAssociateView = dao.viewAssociateDao(modAtten);
		System.out.println(modAssociateView.getContactNumber());
		System.out.println(modAssociateView.getAssociateId());
		return modAssociateView;
	}

	public ArrayList<ModelAssociate> getAllAssociateList(String associateId) {
		ArrayList<ModelAssociate> associateList = manager.getAssociateList(associateId); 

		return associateList;
	}

	//add demo function implementation
	public Integer addDemoService(ModelDemo modDem){
		Integer demoServiceInteger= dao.addDemoDao(modDem);
		return demoServiceInteger;
	}
	// for getting demo list
	@Override
	public ArrayList<DemoModel> getDemo(String str) {
		ArrayList<DemoModel> demoList = manager.getDemoList(str); 

		return demoList;
	}
	//Adding demo
	@Override
	public int addDemoDService(DemoDetailModel demodetail){
		SendingDemoModel demo=new SendingDemoModel();
		demo.setDemoName(demodetail.getDemoName());
		//for start date
		Date startdate=demodetail.getStartDate();
		System.out.println(startdate+"start date");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
		String startdatef=df.format(startdate);
		String starttime=demodetail.getStartTime();
		String startDateAndTime=startdatef+" "+starttime;
		System.out.println("printing "+startDateAndTime);
		SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//for end date
		Date enddate=demodetail.getEndDate();
		SimpleDateFormat dff=new SimpleDateFormat("yyyy-MM-dd"); 
		String enddatef=dff.format(enddate);
		String endtime=demodetail.getEndTime();
		String endDateAndTime=enddatef+" "+endtime;
		System.out.println("printing "+endDateAndTime);
		SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date startDateFinal=df1.parse(startDateAndTime);
			System.out.println(startDateFinal);
			demo.setStartDateAndTime(startDateFinal);
			Date endDateFinal=df1.parse(endDateAndTime);
			System.out.println(endDateFinal);
			demo.setEndDateAndTime(endDateFinal);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao.addDemoDDao(demo);
	}
	//getting time list
	@Override
	public ArrayList<String> getTimeList() {
		ArrayList<String> timeList=new ArrayList<String>();
		timeList.add("Select");
		timeList.add("9:00");
		timeList.add("9:30");
		timeList.add("10:00");
		timeList.add("10:30");
		timeList.add("11:00");
		timeList.add("11:30");
		timeList.add("12:00");
		timeList.add("12:30");
		timeList.add("13:00");
		timeList.add("13:30");
		timeList.add("14:00");
		timeList.add("14:30");
		timeList.add("15:00");
		timeList.add("15:30");
		timeList.add("16:00");
		timeList.add("16:30");
		timeList.add("17:00");
		timeList.add("17:30");
		timeList.add("18:00");
		return timeList;
	}
	public ArrayList<SendingDemoModel> getDemo() {
		ArrayList<Demo> demoEntityList= dao.getDemo();
		ArrayList<SendingDemoModel> demoModelList= convertEntityDemoToSendingDemoModel(demoEntityList);
		return demoModelList;
	}
	public ArrayList<SendingDemoModel> convertEntityDemoToSendingDemoModel(ArrayList<Demo> demoEntityList){
		// TODO Auto-generated method stub
		ArrayList<SendingDemoModel> dList=new ArrayList<SendingDemoModel>();

		for(Demo object: demoEntityList)
		{
			SendingDemoModel dmodel=new SendingDemoModel();
			dmodel.setDemoId(object.getDemoId());
			dmodel.setDemoName(object.getDemoName());
			Date startDate=object.getStartDateTime();
			Date endDate=object.getEndDateTime();
			System.out.println(startDate);
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if(startDate!=null)
			dmodel.setStringStartDateAndTime(df.format(startDate));
			if(endDate!=null)
			dmodel.setStringEndDateAndTime(df.format(endDate));
			dList.add(dmodel);
		}
		return dList;
	}
}