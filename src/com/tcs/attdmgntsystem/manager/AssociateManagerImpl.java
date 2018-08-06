package com.tcs.attdmgntsystem.manager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tcs.attdmgntsystem.dao.AssociateDao;
import com.tcs.attdmgntsystem.entity.Demo;
import com.tcs.attdmgntsystem.model.DemoModel;
import com.tcs.attdmgntsystem.model.DemoReport;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelDemo;
import com.tcs.attdmgntsystem.service.AssociateService;
@Service
public class AssociateManagerImpl implements AssociateManager{
	@Autowired
	AssociateDao dao;
	@Autowired
	AssociateService service;
	@Override
	public List<ModelAssociate> getAllAssociate() {
		return null;
	}

	public ArrayList<ModelAssociate> getAssociateList(String Id) {
		ArrayList<ModelAssociate> AssociateList = dao.getAutoPopulateAssociate(Id);
		return AssociateList;
	}
	//for getting demo list in auto populate
	@Override
	public ArrayList<DemoModel> getDemoList(String demoLst) {
		ArrayList<DemoModel> demoList = dao.getDemoList(demoLst);
		return demoList;
	}

	//Method to fetch list of all demo
	public ArrayList<String> getAllDemo() {
		List<ModelDemo> ml=service.getAllDemo();
		ArrayList<String> demoList=new ArrayList<String>();
		demoList.add("Select");
		for(ModelDemo md:ml){
			String demoName=md.getDemoName().toLowerCase();
			if(!demoList.contains(demoName)){
				demoList.add(demoName);
			}
		}
		return demoList;
	}

	@Override
	public ModelDemo getLastDemoDetails() {
		ModelDemo dm=dao.getLastDemoDetails();
		return dm;
	}
	//Method to fetch list of future demo 
	@Override
	public ArrayList<ModelDemo> getNextDemo() {
		List<Demo> demoList=dao.getNextDemo();
		ArrayList<ModelDemo> modelDemoList = new ArrayList<ModelDemo>();
			for(Demo d:demoList){
			ModelDemo md=new ModelDemo();
			md.setDemoId(d.getDemoId());
			md.setDemoName(d.getDemoName());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			md.setDemoDate(format.format(d.getStartDateTime()));
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			md.setDemoEndDate(format1.format(d.getEndDateTime()));
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
			md.setStartDateTime(formatTime.format(d.getStartDateTime()));
			SimpleDateFormat formatEndTime = new SimpleDateFormat("HH:mm");
			md.setEndDateTime(formatEndTime.format(d.getEndDateTime()));
			modelDemoList.add(md);
		}
		return modelDemoList;
	}
}
