package mapsql.sql.condition;

import java.util.Map;

import mapsql.sql.core.Field;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.TableDescription;

public class LessThan extends AbstractCondition 
{
	//instance variables for column and value
	private String column;
	private String value;
	
	//constructor
	public LessThan(String column, String value) 
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
		boolean less = comparator.compare(field.toValue(data.get(column)), field.toValue(value)) == -1;
		boolean equal = comparator.compare(field.toValue(data.get(column)), field.toValue(value)) == 0;
		
		if(less && !equal) // if it is less than and not equal to return true
		{
			return true;
		}
		else if(less && equal) // if it is less than and equal to return false
		{
			return false;
		}
		else // otherwise return false
		{
			return false;
		}
	}

}
