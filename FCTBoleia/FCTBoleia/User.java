package FCTBoleia;
import java.io.Serializable;

public interface User extends Serializable{

	String getPassword();

	int getNumberOfVisits();

	String getName();

	void addVisit();

	boolean hasDesloc(String data);

	void addDesloc(Deslocacao newDesloc, String data);

	int getNumberOfDesloc();

	String getEmail();

	void removeDesloc(String data);

	boolean hasDeslocOuBoleia(String data);

	Deslocacao getDesloc(String data);

	void addBoleia(Deslocacao desloc, String data);

	int addBoleiaToDesloc(String user, String data);

	boolean deslocHasBoleias(String data);

	boolean hasBoleia(String data);

	void removeBoleia(String data);

	String consultadesloc(String data);

	boolean hasDeslocs();

	String listDesloc();

	boolean hasBoleias();

	String listBoleias();

	String listDeslocFromOther();

	String consultadeslocFromOther(String data);

}
