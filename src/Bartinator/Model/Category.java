package Bartinator.Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 19-10-2016.
 */

@Entity
public class Category {
	@Id
	private	String name;
	@ElementCollection
	private List<String> columns = new ArrayList<>();

	public Category(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public void addColumn(String name){
		columns.add(name);
	}

	public void removeColumn(String name){
		columns.remove(name);
	}
}
