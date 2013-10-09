package edu.sjsu.cmpe.kidsontrack.config;

import java.net.UnknownHostException;


import java.util.Date;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class DBConfig {
	
//	public static DBCollection teacherTable;
//	public static DBCollection studentTable;
//	public static DBCollection documentTable;
	
	private MongoOperations mongoOps;
	
	public DBConfig() {
		try {
			
			// Since 2.10.0, uses MongoClient
			Mongo mongo = new Mongo("localhost", 27017);
			//MongoOperations mongoOps = new MongoTemplate(mongo, "kidsontrack");

			mongoOps = new MongoTemplate(mongo, "kidsontrack");
			
			// if database doesn't exists, MongoDB will create it for you
			//DB db = mongo.getDB("kidsontrack");
			//return mongo;
			//DBCollection teacherTable = db.getCollection("teacherTable");
			//DBCollection studentTable = db.getCollection("studentTable");
			//DBCollection documentTable = db.getCollection("documentTable");
		
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public DBConfig(String name) {
		try {
			
			// Since 2.10.0, uses MongoClient
			Mongo mongo = new Mongo("localhost", 27017);
			//MongoOperations mongoOps = new MongoTemplate(mongo, "kidsontrack");

			mongoOps = new MongoTemplate(mongo, name);
			
			// if database doesn't exists, MongoDB will create it for you
			//DB db = mongo.getDB("kidsontrack");
			//return mongo;
			//DBCollection teacherTable = db.getCollection("teacherTable");
			//DBCollection studentTable = db.getCollection("studentTable");
			//DBCollection documentTable = db.getCollection("documentTable");
		
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public MongoOperations getDB()
	{
		return mongoOps;
		
	}

/*
	public static MongoOperations getDB(String dbname) throws UnknownHostException
	{
		
		
			
			// Since 2.10.0, uses MongoClient
			Mongo mongo = new Mongo("localhost", 27017);
			MongoOperations mongoOps = new MongoTemplate(mongo, dbname);

			
			// if database doesn't exists, MongoDB will create it for you
			//DB db = mongo.getDB("kidsontrack");
			//return mongo;
			//DBCollection teacherTable = db.getCollection("teacherTable");
			//DBCollection studentTable = db.getCollection("studentTable");
			//DBCollection documentTable = db.getCollection("documentTable");
			
			return mongoOps;
	}
	

*/

}
