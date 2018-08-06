package com.tcs.attdmgntsystem.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.tcs.attdmgntsystem.model.DemoReport;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.tcs.attdmgntsystem.entity.Associate;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelAttendance;
import com.tcs.attdmgntsystem.model.ModelDemo;
import com.tcs.attdmgntsystem.model.Report;
import com.tcs.attdmgntsystem.model.SendingDemoModel;
import com.tcs.attdmgntsystem.model.DemoDetailModel;
import com.tcs.attdmgntsystem.model.DemoModel;
@Service
public interface AssociateService {
	//declaration for getting all associate
	public ArrayList<ModelAssociate> getAllAssociate();
	//declaration for fetching all Demos in list
	public List<ModelDemo> getAllDemo();
	//declaration for adding associate
	public int addAssociateService(ModelAssociate modelassociate);
	//declaration for report
	public ArrayList<Report> getReport(DemoReport demoReport);
	//add attendance function
	public String addAttendanceService(ModelAttendance modAtten);
	//view associates in add attendance
	public ModelAssociate viewAssociateService(ModelAttendance modAtten);
	public ArrayList<ModelAssociate> getAllAssociateList(String associateId);
	//add demo function
	public Integer addDemoService(ModelDemo modDem);
	//declaration for getting demo list
	public ArrayList<DemoModel> getDemo(String s);
	//adding demo
	public int addDemoDService(DemoDetailModel demodetail);
	//getting time list
	public ArrayList<String> getTimeList();
	public ArrayList<SendingDemoModel> getDemo(); 
}
