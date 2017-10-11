package mapsql.sql.core;

import java.util.HashMap;
import java.util.Map;

import mapsql.sql.condition.Equals;
import mapsql.util.LinkedList;
import mapsql.util.List;
import mapsql.util.Position;

public class Table 
{
	private TableDescription description; //description of table
	private List<Row> rows = new LinkedList<Row>(); //linked list of the rows in the database
	
	public Table(TableDescription description) //constructor
	{
		this.description = description; //initialising the current table
	}
	
	public TableDescription description() //if the table description is called type table description is returned
	{
		return description;
	}

	public List<Row> select(Condition where) throws SQLException 
	{
		List<Row> list = new LinkedList<Row>(); //creating a new linked list for rows called list
		
		for (Row row : rows) //enhanced for loop to go through the rows
		{
			if (row.satisfies(where, description)) //calls the satisfies method to see if input is correct
			{
				list.insertLast(row);	//inserts t
			}
		}
		return list;	//returns list
	}

	public void insert(String[] columns, String[] values) throws SQLException 
	{
		Map<String, String> data = new HashMap<String, String>();//creating a new hash map
		
		for (int i=0; i < columns.length; i++) //goes through all the columns
		{
			Field field = description.findField(columns[i]); //sets the field as the description for each coulumn
			
			if (field.isUnique()) //if the field is unique
			{
				if (!select(new Equals(columns[i], values[i])).isEmpty()) //the name of the column is unique but the data is not
				{
					throw new SQLException("Column '" + columns[i] + "' is UNIQUE - a row with '" + values[i] + "' already exists"); //throws exception
				}
			}
			data.put(columns[i], field.validate(values[i])); //Associates the specified value with the specified column in this map
		}
		
		Field[] fields = description.fields(); //taking all the fields
		
		for (int i=0; i < fields.length; i++) //looping through the fields
		{
			if (!data.containsKey(fields[i].name())) //Returns true if this map contains a mapping for the specified key, so if it does not then condition is met
			{
				String val = fields[i].defaultValue();

				if (fields[i].isUnique()) //if field is unique with the default value of that field
				{
					if (!select(new Equals(fields[i].name(), val)).isEmpty()) //the name of the field is unique but the data is not
					{
						throw new SQLException("Column '" + fields[i].name() + "' is UNIQUE - a row with '" + val + "' already exists");
					}
				}
				data.put(fields[i].name(), val); //Associates the specified value with the specified key
			}
		}
		rows.insertLast(new Row(data)); //inserting the data into a new row after the rest of the rows
	}

	public void update(String[] columns, String[] values, Condition where) throws SQLException 
	{
		Map<String, String> data = new HashMap<String, String>(); //creating new hash map called data
		
		for(Row row: rows) //enhanced for loop to go through all the rows
		{
			if (row.satisfies(where, description)) //if a row satisfies the where condition
			{
				for(int i=0; i<columns.length; i++) //loop through all the columns to find the correct one to update
				{
					row.data.put(columns[i], values[i]); //put the value into the column that is being updated in the specified row
				}
			}
		 }
		
	}
	
	public void delete(Condition where) throws SQLException 
	{
		Map<String, String> data = new HashMap<String, String>();
		
		Position<Row> rowToDelete = rows.first(); //starts at first row to loop through the rest of them
		Position<Row> duplicateRow = rows.first();  //to store a duplicated row so that the final row can be deleted
		Position<Row> lastRow = rows.last();  //to set the duplicated row as the new last row after current last row
		
		for(Row row: rows) //enhanced for loop to go through all of the rows
		{
			if (row.satisfies(where, description)) //if a row satisfies the where condition
			{
				
				if(rowToDelete == rows.last()) //if the row to be deleted is the last row
				{
					duplicateRow =  rows.prev(lastRow); //duplicate the row before the last row so that there is no jumbling when the row is deleted as both occurrences are beside each other
					rows.insertAfter(lastRow, duplicateRow.element()); //insert the duplicated row after the last
					
					rows.remove(rowToDelete); //delete the specified row as it is no longer last row
					rows.remove(duplicateRow); //the duplicate row can be removed as the first occurring row is deleted rather than the actual last row
				}
				
				else //if the row to be deleted is not the last row
				{
					rows.remove(rowToDelete); //delete the row you want o delete
				}
			}
			else //if where condition is not met for a row increment to next row
			{
				rowToDelete = rows.next(rowToDelete); //incrementing
			}
		}
		
	}
}
