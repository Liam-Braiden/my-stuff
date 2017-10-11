package mapsql.sql.condition;

import java.util.Map;
import mapsql.sql.core.Field;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.TableDescription;

public class Like extends AbstractCondition 
{
	private String column;
	private String value;
	
	public Like(String column, String value) 
	{
		this.column = column;
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean evaluate(TableDescription description, Map<String, String> data) throws SQLException 
	{
		Field field = description.findField(column);
		
		String likeValue = data.get(column); //setting the passed data and value to which its mapped as a variable likeValue
		
		String  match1 = (String) field.toValue(likeValue); //the field value from the column casted to a string match 1
		String match2 = (String) field.toValue(value); //the field value of value casted to a string match2
		
		String start = match2.substring(1).toLowerCase(); //match2 begins with %
		String end = match2.substring(0, value.length()-1).toLowerCase(); //match2 ends with %
		String both = match2.substring(1, value.length()-1).toLowerCase(); //match2 has % at beginning and end
		
		
		if(value.startsWith("%") && !value.endsWith("%")) //if the value starts with % and does not end with %
		{
			if(match1.toLowerCase().startsWith(start)) //if the value in the column starts with % return true
			{
				//System.out.println("start");
				return true;
			}
			else //otherwise return false
			{
				//System.out.println("false for start");
				return false;
			}
		}
		
		else if(value.endsWith("%") && !value.startsWith("%")) // if the value ends with % and does not start with
		{
			if(match1.toLowerCase().endsWith(end)) //if the value in the column ends with % return true
			{
				//System.out.println("end");
				return true;
			}
			else //otherwise return false
			{
				//System.out.println("false for end");
				return false;
			}
		}
		
		else if(value.startsWith("%") && value.endsWith("%")) // if the value starts with and ends with %
		{
//			if(likeValue.startsWith(value) && likeValue.endsWith(value)) //if the value in the column starts with and ends with the value
//			{
//				return true;
//			}
			if(match1.toLowerCase().contains(both)) //if the value in the column starts with and ends with % return true
			{
				//System.out.println("both");
				return true;
			}
			else //otherwise return false
			{
				//System.out.println("false for both");
				return false;
			}
		}
		
		else
		{
			if(likeValue.equalsIgnoreCase(value)) //if the value equals to the value of that column without % contained
			{
				return true;
			}
			else //otherwise return false
			{
				return false;
			}
		}
	}
}
