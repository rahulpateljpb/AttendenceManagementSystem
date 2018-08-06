package com.tcs.attdmgntsystem.manager;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;

import com.tcs.attdmgntsystem.model.DemoModel;
import com.tcs.attdmgntsystem.model.DemoReport;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelDemo;
public interface AssociateManager {
	public List<ModelAssociate> getAllAssociate();
	
	//function for getting associate list by Associate Id initials
	public ArrayList<ModelAssociate> getAssociateList(String AssociateId);
	//declaration for getting demo list in auto populate
	public ArrayList<DemoModel> getDemoList(String demoLst);
	//declaration for fetching demoList
	public ArrayList<String> getAllDemo();
	//declaration for fetching last demo
	public ModelDemo getLastDemoDetails();
	//declaration for fetching next demos
	public ArrayList<ModelDemo> getNextDemo();
}
