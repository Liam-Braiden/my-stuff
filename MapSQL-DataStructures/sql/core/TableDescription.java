package mapsql.sql.core;

public class TableDescription //main class
{
	//private variables
	private String name; 
	private Field[] fields;
	
	//constructor initialising these variables
	public TableDescription(String name, Field[] fields) 
	{
		this.name = name;
		this.fields = fields;
	}
	
	//function to return string name
	public String name() 
	{
		return name;
	}
	
	//returns the field for the corresponding string name
	public Field findField(String name) 
	{
		for (int i=0; i < fields.length; i++) //loops through all fields
		{
			if (fields[i].name().equals(name)) //if field name equals string name
			{
				return fields[i]; //return the field
			}
		}
		return null; // return null
	}

	/**
	 * This method resolves an array of columns into the actual column headings to
	 * be returned (i.e. * is resolved to all the column names). If an invalid 
	 * column name is given, then this method will throw an SQLException.
	 * 
	 * @param columns
	 * @return
	 * @throws SQLException
	 */
	public String[] resolveColumns(String[] columns) throws SQLException 
	{
		String[] cols; //columns string array
		
		if (columns.length == 1 && columns[0].equals("*")) 
		{
			cols = new String[fields.length];
			for (int i=0;i<fields.length;i++) 
			{
				cols[i] = fields[i].name();
			}
		} 
		else 
		{
			cols = new String[columns.length];
			
			for (int i=0; i<columns.length;i++) 
			{
				Field field = findField(columns[i]);
				if (field == null) throw new SQLException("Unknown field '" + columns[i] + "' in table: '" + name + "'");
				cols[i] = columns[i];
			}
		}
		return cols;
	}
	
	//returns all fields
	public Field[] fields() 
	{
		return fields;
	}

	/**
	 * Checks that no columns marked "not null" have been missed.
	 * @param cols
	 * @throws SQLException 
	 */
	public void checkForNotNulls(String[] cols) throws SQLException {
		
		boolean notNull;//boolean to check for not nulls
		
		for(Field field: fields)//enhanced for loop to go through all fields
		{
			if(field.isNotNull())//if each field of the iteration is not null check
			{
				for(String col: cols)//enhanced for loop to loop through all columns
				{
					if(col.equalsIgnoreCase(field.name()))//checking if the column contains the field name for each column
					{
						notNull = true; //if found the bool will be set to true meaning it is not null
					}
					else
					{
						notNull = false;//if not found the bool will be set to false meaning it is null
					}
				}
				if(notNull = false)//after the loop if field name is null an exception is thrown...otherwise the check complete with no exception thrown as it is not null
				{
					throw new SQLException("field name is null and not found");//exception thrown
				}
			}
		}
	}

}
