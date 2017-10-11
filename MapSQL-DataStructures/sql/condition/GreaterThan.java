package mapsql.sql.condition;

import java.util.Map;

import mapsql.sql.core.Field;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.TableDescription;

public class GreaterThan extends AbstractCondition 
{
	//instance variables for column and value
	private String column;
	private String value;
	
	//constructor
	public GreaterThan(String column, String value) 
	{
		this.column = column;//initialising
		this.value = value;//initialising
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean evaluate(TableDescription description, Map<String, String> data) throws SQLException 
	{
		
		Field field = description.findField(column);
		
		//created two booleans to check the value when compared 
		boolean greater = comparator.compare(field.toValue(data.get(column)), field.toValue(value)) == 1;
		boolean equal = comparator.compare(field.toValue(data.get(column)), field.toValue(value)) == 0;
		
		if(greater && !equal) // if it is greater than and not equal return true
		{
			return true;
		}
		else if(greater && equal) // if it is greater than and equal return false
		{
			return false;
		}
		else // otherwise return true
		{
			return false;
		}
		
		
	}

}
