package net.mcmhsj.archersbattle.managers;

import java.sql.Connection;
import java.sql.Statement;

public class Database {
	private static Database db;
	Connection conn;
	Statement st;
	public Database(Connection conn)
	{
		this.conn=conn;
		db=this;
	}
	
	public boolean init()
	{
		boolean success=false;
		try
		{
			st=conn.createStatement();
			st.executeUpdate("CREATE TABLE `ab_stats` (`name`  varchar(100) NOT NULL ,`kill`  varchar(100) NOT NULL ,`death`  varchar(100) NOT NULL ,`time`  varchar(100) NOT NULL ,PRIMARY KEY (`name`));");
			success=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return success;
	}
	
	public static Database getDatabase()
	{
		return db;
	}
	
}
