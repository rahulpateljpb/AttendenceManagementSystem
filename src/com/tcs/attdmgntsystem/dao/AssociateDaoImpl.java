package com.tcs.attdmgntsystem.dao;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.tcs.attdmgntsystem.entity.Associate;
import com.tcs.attdmgntsystem.entity.Associate_Demo;
import com.tcs.attdmgntsystem.entity.Associate;
import com.tcs.attdmgntsystem.entity.Demo;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelAttendance;
import com.tcs.attdmgntsystem.model.DemoModel;
import com.tcs.attdmgntsystem.model.DemoReport;
import com.tcs.attdmgntsystem.model.ModelAssociate;
import com.tcs.attdmgntsystem.model.ModelDemo;
import com.tcs.attdmgntsystem.model.Report;
import com.tcs.attdmgntsystem.model.SendingDemoModel;
import javassist.bytecode.stackmap.BasicBlock.Catch;

@Repository
public class AssociateDaoImpl implements AssociateDao {
	//fetch demos from database
	public List<Demo> getAllDemo() {
		ModelDemo md=new ModelDemo();
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").addAnnotatedClass(Demo.class).buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Demo.class);
		Date currentDate=new Date();
		criteria.add(Restrictions.le("startDateTime",currentDate));
		List demoList=criteria.list();
		return demoList;
	}
	//Retrieve report from database 
	@Override
	public ArrayList<Report> getReport(DemoReport demoReport) {
		ArrayList<Report> reportList = new ArrayList<Report>();
		Integer demoId=0;
		Integer associateId=0;
		String demoName=null;
		String associateName=null;
		Date demoStartDate=null;
		Date demoEndDate=null;
		String stringStartDate=null;
		String stringEndDate=null;
		String demoStartTime=null;
		String demoEndTime=null;
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		if(demoReport.getDemoName()!="Select"&&demoReport.getFromDate()==null){
			Criteria demoCriteria = session.createCriteria(Demo.class);
			demoCriteria.add(Restrictions.eq("demoName",demoReport.getDemoName()).ignoreCase());
			List<Demo> associateDemo=demoCriteria.list();
			for (Demo demo : associateDemo) {
				demoId=demo.getDemoId();
				Criteria associate_DemoCriteria = session.createCriteria(Associate_Demo.class);
				associate_DemoCriteria.add(Restrictions.eq("demo_Id",demoId));
				List<Associate_Demo> associate_Demo=associate_DemoCriteria.list();
				for(Associate_Demo ad:associate_Demo){
					demoId=ad.getDemo_Id();
					associateId=ad.getAssociate_Id();
					Criteria newDemocriteria = session.createCriteria(Demo.class);
					newDemocriteria.add(Restrictions.eq("demoId",demoId));
					List<Demo> demos=newDemocriteria.list();
					for(Demo d:demos){
						demoId=d.getDemoId();
						demoName=d.getDemoName();
						demoStartDate=demo.getStartDateTime();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						stringStartDate=format.format(demoStartDate);
						SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
						demoStartTime=formatter1.format(demoStartDate);
						demoEndDate=demo.getEndDateTime();
						SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
						stringEndDate=format3.format(demoEndDate);
						SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
						demoEndTime=formatter2.format(demoEndDate);
					}
					Criteria associateCriteria = session.createCriteria(Associate.class);
					associateCriteria.add(Restrictions.eq("associateId",associateId));
					List<Associate> associateList=associateCriteria.list();
					for (Associate associate: associateList) {
						associateName=associate.getAssociateName();
						Report report=new Report(associateId, associateName, demoId, demoName, stringStartDate, stringEndDate, demoStartTime, demoEndTime);
						reportList.add(report);
					}

				}
			}
		}
		else	if(demoReport.getDemoName().equalsIgnoreCase("Select") && demoReport.getFromDate()!=null){
			Criteria demoCriteria = session.createCriteria(Demo.class);
			demoCriteria.add(Restrictions.between("startDateTime",demoReport.getFromDate(),demoReport.getToDate()));
			List<Demo> demo=demoCriteria.list();
			for (Demo d : demo) {
				demoId=d.getDemoId();
				demoName=d.getDemoName();
				demoStartDate=d.getStartDateTime();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				stringStartDate=format.format(demoStartDate);
				SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
				demoStartTime=formatter1.format(demoStartDate);
				demoEndDate=d.getEndDateTime();
				SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
				stringEndDate=format3.format(demoEndDate);
				SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
				demoEndTime=formatter2.format(demoEndDate);
				Criteria associateDemoCriteria = session.createCriteria(Associate_Demo.class);
				associateDemoCriteria.add(Restrictions.eq("demo_Id",demoId));
				List<Associate_Demo> associate_Demo=associateDemoCriteria.list();
				for(Associate_Demo asDemo:associate_Demo){
					associateId=asDemo.getAssociate_Id();
					Criteria associateCriteria = session.createCriteria(Associate.class);
					associateCriteria.add(Restrictions.eq("associateId",associateId));
					List<Associate> associate=associateCriteria.list();
					for(Associate a:associate){
						associateId=a.getAssociateId();
						associateName=a.getAssociateName();
					}
					Report report=new Report(associateId, associateName, demoId, demoName, stringStartDate, stringEndDate, demoStartTime, demoEndTime);
					reportList.add(report);
				}
			}
		}
		else if(!demoReport.getDemoName().equalsIgnoreCase("Select")||demoReport.getFromDate()!=null){
			Criteria demoCriteria = session.createCriteria(Demo.class);
			demoCriteria.add(Restrictions.between("startDateTime",demoReport.getFromDate(),demoReport.getToDate()));
			demoCriteria.add(Restrictions.eq("demoName",demoReport.getDemoName()).ignoreCase());
			List<Demo> associateDemo=demoCriteria.list();
			for (Demo demo : associateDemo) {
				demoId=demo.getDemoId();
				Criteria associate_DemoCriteria = session.createCriteria(Associate_Demo.class);
				associate_DemoCriteria.add(Restrictions.eq("demo_Id",demoId));
				List<Associate_Demo> associate_Demo=associate_DemoCriteria.list();
				for(Associate_Demo ad:associate_Demo){
					demoId=ad.getDemo_Id();
					associateId=ad.getAssociate_Id();
					Criteria newDemocriteria = session.createCriteria(Demo.class);
					newDemocriteria.add(Restrictions.eq("demoId",demoId));
					List<Demo> demos=newDemocriteria.list();
					for(Demo d:demos){
						demoId=d.getDemoId();
						demoName=d.getDemoName();
						demoStartDate=demo.getStartDateTime();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						stringStartDate=format.format(demoStartDate);
						SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
						demoStartTime=formatter1.format(demoStartDate);
						demoEndDate=demo.getEndDateTime();
						SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
						stringEndDate=format3.format(demoEndDate);
						SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
						demoEndTime=formatter2.format(demoEndDate);
					}
					Criteria associateCriteria = session.createCriteria(Associate.class);
					associateCriteria.add(Restrictions.eq("associateId",associateId));
					List<Associate> associateList=associateCriteria.list();
					for (Associate associate: associateList) {
						associateName=associate.getAssociateName();
						Report report=new Report(associateId, associateName, demoId, demoName, stringStartDate, stringEndDate, demoStartTime, demoEndTime);
						reportList.add(report);
					}

				}
			}
		}
		return reportList;
	}
	//Adds associate to database
	@Override
	public int addAssociateDao(ModelAssociate modelassociate) {
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Integer idCheck=modelassociate.getAssociateId();
		String	hql="from Associate E where E.associateId="+idCheck;
		Query query=session.createQuery(hql);
		List s=query.list();
		if(s.size()==0){
			Associate associateEntity=new Associate();
			associateEntity.setAssociateId(modelassociate.getAssociateId());
			associateEntity.setAssociateName(modelassociate.getAssociateName());
			associateEntity.setAssociateContact(modelassociate.getContactNumber());
			associateEntity.setAssociateEmail(modelassociate.getEmailId());
			session.save(associateEntity);
			session.getTransaction().commit();
			session.close();
			return 1;
		}
		else
			return 0;
	}
	//Retrieve all associate list
	@Override
	public ArrayList<Associate> getAllAssociate() {
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").addAnnotatedClass(Demo.class).buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		Criteria criteria = session.createCriteria(Associate.class);
		ArrayList<Associate> associateList=(ArrayList<Associate>) criteria.list();
		return associateList;
	}
	//add attendance function implementation     ayush
	public String addAttendenceDao(ModelAttendance modAtten){
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Associate_Demo associateDemo = new Associate_Demo();
		associateDemo.setAssociate_Id(modAtten.getAssociateId());
		associateDemo.setDemo_Id(modAtten.getDemoId());
		session.save(associateDemo);
		session.getTransaction().commit();
		session.close();
		return"Attendance marked successfully";
	} 
	//view associates in add attendance implementation
	public ModelAssociate viewAssociateDao(ModelAttendance modAtten){
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Associate.class);
		Integer id = modAtten.getAssociateId();
		cr.add(Restrictions.eq("associateId",id));
		List<Associate> ad=cr.list();
		ModelAssociate modAttenView =new ModelAssociate();
		for (Associate associate : ad) {
			modAttenView.setAssociateId(associate.getAssociateId());
			modAttenView.setAssociateName(associate.getAssociateName());
			modAttenView.setContactNumber(associate.getAssociateContact());
			modAttenView.setEmailId(associate.getAssociateEmail());
		}
		session.getTransaction().commit();
		session.close();
		return modAttenView;
	}
	
	
	// function for getting list of Associate AutoPopulate
	@Override
	public ArrayList<ModelAssociate> getAutoPopulateAssociate(String associateIdInitial) {
		{
			SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			ArrayList<ModelAssociate> associateList = new ArrayList<ModelAssociate>();
			String data;
			try {
				int i = 1;
				String hql = "FROM Associate E WHERE str(E.associateId) LIKE :id";	
				Query query = session.createQuery(hql);
				query.setParameter("id",associateIdInitial+'%');
				List<Associate> results = query.list();
				for (Associate associate : results) {
					ModelAssociate associateObject =new ModelAssociate();
					associateObject.setAssociateId(associate.getAssociateId());
					associateObject.setAssociateName(associate.getAssociateName());
					associateObject.setContactNumber(associate.getAssociateContact());
					associateObject.setEmailId(associate.getAssociateEmail());
					associateList.add(associateObject);
				}
				session.getTransaction().commit();
				session.close(); 
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return associateList;
		}
	}
	
	
	
	// function for getting list of Demo AutoPopulate
	@Override
	public ArrayList<DemoModel> getDemoList(String demoName) {
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		ArrayList<DemoModel> demolist = new ArrayList<DemoModel>();
		try {
			String hql = "FROM Demo E fetch all properties WHERE lower(E.demoName) LIKE :demoName AND current_timestamp between E.startDateTime and E.endDateTime";			
			Query query = session.createQuery(hql);
			query.setParameter("demoName",demoName.toLowerCase()+'%');		
			List<Demo> results = query.list();
			for (Demo demoEntity1 : results) {
				DemoModel demomodel1 = new DemoModel();
				demomodel1.setDemoId(demoEntity1.getDemoId());
				demomodel1.setDemoName(demoEntity1.getDemoName());
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startTime1=df1.format(demoEntity1.getStartDateTime());
				String endTime1=df1.format(demoEntity1.getEndDateTime());
				demomodel1.setStartDateTime(startTime1);
				demomodel1.setEndDateTime(endTime1);
				demolist.add(demomodel1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return demolist;	
	}
	public Integer addDemoDao(ModelDemo modDem){
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Demo demo =new Demo();
		demo.setDemoName(modDem.getDemoName());
		session.save(demo);
		session.getTransaction().commit();
		session.close();
		Integer DemoId = returnDemoId(modDem);
		return DemoId;
	}  
	//Retrieve last demo
	@Override
	public ModelDemo getLastDemoDetails() {
		Date startDateTime=null;
		Date endDateTime=null;
		Date currentDate=new Date();
		String stringDemoDate = null;
		String demoStartTime=null;
		String demoDuration=null;
		Integer demoId=0;
		Integer id=0;
		String demoName=null;
		List<Demo> demoList = null;
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		Criteria demoCriteria = session.createCriteria(Demo.class);
		demoCriteria.addOrder(Order.desc("startDateTime"));
		demoCriteria.add(Restrictions.le("startDateTime",currentDate));
		List<Demo> dList=demoCriteria.list();
		ModelDemo md = null;
		for(Demo d:dList){
			demoId=d.getDemoId();
			demoName=d.getDemoName();
			startDateTime=d.getStartDateTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			stringDemoDate=format.format(startDateTime);
			endDateTime=d.getEndDateTime();
			Criteria associateDemoCriteria = session.createCriteria(Associate_Demo.class);
			associateDemoCriteria.add(Restrictions.eq("demo_Id",demoId));
			List<Associate_Demo> asd=associateDemoCriteria.list();
			demoId=0;
			for(Associate_Demo ad:asd){
				if(ad.getAssociate_Id()!=0){
					demoId=ad.getDemo_Id();
					id=1;
					if(demoId!=0)
						break;
				}
			}
			if(id!=0)
				break;
		}
		md=new ModelDemo(demoId, demoName, stringDemoDate, stringDemoDate, demoDuration);
		return md;
	}
	// ayush function
	public Integer returnDemoId(ModelDemo modDem){
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Demo.class);
		String demo_Name = modDem.getDemoName();
		cr.add(Restrictions.eq("demoName",demo_Name));
		List<Demo> demoList=cr.list();
		ModelDemo modelDemo =new ModelDemo();
		for (Demo demo : demoList) {
			modelDemo.setDemoId(demo.getDemoId());
		}
		session.getTransaction().commit();
		session.close();
		return modelDemo.getDemoId();
	} 
	@Override
	public List<Demo> getNextDemo() {
		Date currentDate=new Date();
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		Criteria demoCriteria = session.createCriteria(Demo.class);
		demoCriteria.add(Restrictions.ge("startDateTime",currentDate));
		List<Demo> dList=demoCriteria.list();
		return dList;
	}
	@Override
	public int addDemoDDao(SendingDemoModel demo)
	{
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Demo demoEntity=new Demo();
		demoEntity.setDemoName(demo.getDemoName());
		demoEntity.setStartDateTime(demo.getStartDateAndTime());
		demoEntity.setEndDateTime(demo.getEndDateAndTime());
		session.save(demoEntity);
		session.getTransaction().commit();
		session.close();
		return 1;
	}
	//get all demo list
	public ArrayList<Demo> getDemo() {
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		Criteria criteria = session.createCriteria(Demo.class);
		criteria.addOrder(Order.desc("demoId"));
		ArrayList<Demo> demoList=(ArrayList<Demo>) criteria.list();
		return demoList;
	} 
	//viral code fetching associates by demo id
	public ArrayList<Associate_Demo> getAllAvailableAssociate(Integer id) {
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").addAnnotatedClass(Demo.class).buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		String	hql="from Associate_Demo E where demo_Id="+id;
		Query query=session.createQuery(hql);
		ArrayList associatePresentList=(ArrayList) query.list();
		return associatePresentList;
	}
	
	

	public ArrayList<ModelAssociate> getAssociateFromDemoId(ArrayList<Associate_Demo> presentArrayList) {
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		List<Integer> AssocId = new ArrayList<Integer>();
		for (Associate_Demo id : presentArrayList) {
			AssocId.add(id.getAssociate_Id());
		}
		session.beginTransaction();
		ArrayList<ModelAssociate> associateList = new ArrayList<ModelAssociate>();
		String data;
		try {
			int i = 1;
			String hql = "FROM Associate E WHERE E.associateId IN (:id)";	
			Query query = session.createQuery(hql);
			query.setParameterList("id",AssocId);
			List<Associate> results = query.list();
			for (Associate associate : results) {
				ModelAssociate modAttenView =new ModelAssociate();
				modAttenView.setAssociateId(associate.getAssociateId());
				modAttenView.setAssociateName(associate.getAssociateName());
				modAttenView.setContactNumber(associate.getAssociateContact());
				modAttenView.setEmailId(associate.getAssociateEmail());
				associateList.add(modAttenView);
			}
			session.getTransaction().commit();
			session.close(); 
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return associateList;
	}
	
	// function for getting Demo object for given demo Id
	public ModelDemo getDemoObjectwithId(int demoId) {
		SessionFactory sessionFactory=new Configuration().configure("resources/hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM Demo E WHERE demoId ="+demoId;			
		Query query = session.createQuery(hql);
		List<Demo> results = query.list();
		ModelDemo demoObject =new ModelDemo();
		for (Demo demo : results) {
			demoObject.setDemoId(demo.getDemoId());
			demoObject.setDemoName(demo.getDemoName());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTime1=df1.format(demo.getStartDateTime());
			String endTime1=df1.format(demo.getEndDateTime());
			demoObject.setStartDateTime(startTime1);
			demoObject.setEndDateTime(endTime1);
			//Integer i=associate.getAssociateId();
		}
		session.getTransaction().commit();
		session.close();
		return demoObject;
	} 
}