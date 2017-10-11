package mapsql.sql.statement;

import java.util.Map;

import mapsql.sql.condition.Equals;
import mapsql.sql.core.Condition;
import mapsql.sql.core.Row;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.SQLResult;
import mapsql.sql.core.SQLStatement;
import mapsql.sql.core.Table;
import mapsql.sql.core.TableDescription;
import mapsql.util.List;

public class DropTable implements SQLStatement 
{
	//declaring a private string called name
	private String name;
	
	//constructor
	public DropTable(String name) 
	{
		this.name = name;//initialising the string name to be that of a table
	}
	
	@Override
	public SQLResult execute(Map<String, Table> tables) throws SQLException 
	{
		
		if (name.equals("mapsql.tables")) //checks if the table name is mapsql.tables
		{
			throw new SQLException("Table 'mapsql.tables' cannot be modified"); //throws exception if it is
		}

		//final Table table = tables.get(name);
		
		if (tables.containsKey(name) == false) //if tables does not contain an instance of referenced table
		{
			throw new SQLException("Unknown table: " + name); //throw exception for unknown table
		}
		
		Equals equals = new Equals("table", name); //sets table equal to the table name and stores them in equals
		Table table = tables.get("mapsql.tables");	//creates table of type table and gets the value to which the specified key of mapsql.tables is mapped
		
		table.delete(equals); //deletes the table from the tables map
		tables.remove(name); //removes the table from the system tables
		
		return new SQLResult() //after the action has beein performed
		{
			public String toString() 
			{
				return "OK"; //return ok
			}

			@Override
			public TableDescription description() 
			{
				return null; //return the table description as null
			}

			@Override
			public List<Row> rows() 
			{
				return null; //return the rows for said table as null
			}
		};
	}

}
