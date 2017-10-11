package mapsql.sql.command;

import mapsql.sql.core.SQLCommand;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.SQLManager;

public class Sources implements SQLCommand 
{
	//creating private variable for the filename
	private String filename;
	
	//constructor
	public Sources(String filename) 
	{
		this.filename = filename; //sets filename as name passed in
	}
	
	@Override
	public String execute(SQLManager manager) throws SQLException 
	{
		return filename;//returning the filename as it is a string which can be returned
	}
}
