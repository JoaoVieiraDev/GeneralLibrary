package FCTBoleia;

import java.io.Serializable;

public class DateAndName implements Comparable<DateAndName> , Serializable{

	private static final long serialVersionUID = 1L;

	//Stores the number of points.
	private String date;
	
	//Stores the Id of the property.
	private String name;
	
	public DateAndName(Deslocacao desloc) {
		date = desloc.getData();
		name = desloc.getOwner();
	}
	
    @Override
    public int compareTo(DateAndName d2) {
    	String aux[] = date.split("-");
		int day1 = Integer.parseInt(aux[0]);
		int month1 = Integer.parseInt(aux[1]);
		int year1 = Integer.parseInt(aux[2]);
		String aux2[] = d2.getDate().split("-");
		int day2 = Integer.parseInt(aux2[0]);
		int month2 = Integer.parseInt(aux2[1]);
		int year2 = Integer.parseInt(aux2[2]);
    	if(year1 > year2) return 1;
    	else if(year1 < year2) return -1; 
    	else if(month1 > month2) return 1;
    	else if(month1 < month2) return -1;
    	else if(day1 > day2) return 1;
    	else if(day1 < day2) return -1;
    	else return name.compareTo(d2.getName());
    }
    
    private String getDate() {  return date; }
    
    private String getName() {  return name; }
    
}

