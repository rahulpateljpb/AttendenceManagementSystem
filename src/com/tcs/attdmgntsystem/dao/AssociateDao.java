package com.tcs.attdmgntsystem.dao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.DemoModel;
import com.tcs.attdmgntsystem.model.DemoReport;
import com.tcs.attdmgntsystem.entity.Associate;
import com.tcs.attdmgntsystem.entity.Associate_Demo;
import com.tcs.attdmgntsystem.entity.Demo;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelAttendance;
import com.tcs.attdmgntsystem.model.ModelDemo;
import com.tcs.attdmgntsystem.model.Report;
import com.tcs.attdmgntsystem.model.SendingDemoModel;
public interface AssociateDao {
	//declaration for fetching all demos 
	public List<Demo> getAllDemo();
	//declaration for retrieving report 
	public ArrayList<Report> getReport(DemoReport demoReport);
	//add attendance function
	public String addAttendenceDao(ModelAttendance modAtten);
	//view Associates function in add attendance
	public ModelAssociate viewAssociateDao(ModelAttendance modAtten);
	//Retrieve list of all associate from database
	public ArrayList<Associate> getAllAssociate();
	// public Connection getConnection();
	public ArrayList<ModelAssociate> getAutoPopulateAssociate(String AssociateId);
	//add demo function declaration
	public Integer addDemoDao(ModelDemo modDem);
	//Adds associate to database
	int addAssociateDao(ModelAssociate modelassociate);
	//daclaration for adding demo list in databvase to autocomplete
	public ArrayList<DemoModel> getDemoList(String demoName);
	//Retrieves last demo details
	public ModelDemo getLastDemoDetails();	
	//Retrieves future demo details
	public List<Demo> getNextDemo();
	//Adding demos
	public int addDemoDDao(SendingDemoModel demo);
	//Retrieves demos
	public ArrayList<Demo> getDemo(); 
	//viral code for fetching associates eith demo id
	public ArrayList<Associate_Demo> getAllAvailableAssociate(Integer id);
	//Retrieves AssociateFromDemoId
	public ArrayList<ModelAssociate> getAssociateFromDemoId(ArrayList<Associate_Demo> presentArrayList);
	

}
