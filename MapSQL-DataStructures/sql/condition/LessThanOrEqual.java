package mapsql.sql.condition;

import java.util.Comparator;
import java.util.Map;

import mapsql.sql.core.Condition;
import mapsql.sql.core.Field;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.TableDescription;
import mapsql.util.IncomparableException;

public class LessThanOrEqual extends AbstractCondition 
{
	//instance variables for column and value
	private String column;
	private String value;
	
	//constructor
	public LessThanOrEqual(String column, String value) 
	{
		this.column = column; //initialising
		this.value = value; //initialising
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean evaluate(TableDescription description, Map<String, String> data) throws SQLException 
	{
		
		Field field = description.findField(column);
		
		//created two booleans to check the value when compared 
		boolean less = comparator.compare(field.toValue(data.get(column)), field.toValue(value)) == -1;
		boolean equal = comparator.compare(field.toValue(data.get(column)), field.toValue(value)) == 0;
		
		if(less && !equal) //if is less than but not equal to return true
		{
			return true;
		}
		else if(less || equal) //if it is less than or equal to return true
		{
			return true;
		}
		else //otherwise return false
		{
			return false;
		}
	}

}
