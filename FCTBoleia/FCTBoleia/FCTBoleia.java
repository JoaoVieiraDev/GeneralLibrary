package FCTBoleia;
import java.io.Serializable;

public interface FCTBoleia extends Serializable{

	void registerUser(String email, String nome, String password);

	boolean initialMode();

	boolean hasUser(String email);

	int getNumberOfRegistos();

	String getUserPassword(String email);

	void login(String email);

	int getNumberOfUserVisits();

	String getCurrentUser();

	void logout();

	boolean userHasDesloc(String data);

	void addDesloc(String origem, String destino, String data, String hora, int duration, int lugares);

	int getNumberOfUserDesloc();

	String getCurrentUserName();

	void removeDesloc(String data);

	boolean otherUserHasDesloc(String email, String data);

	boolean userHasDeslocOuBoleia(String data);

	int addBoleia(String email, String data);

	boolean deslocHasBoleias(String data);

	boolean userHasBoleia(String data);

	void removeBoleia(String data);

	String consultaDesloc(String email, String data);

	boolean hasDesloc();

	String listMinhasDesloc();

	boolean hasBoleia();

	String listaMinhasBoleias();

	String listaUserDesloc(String email);

	String listaDeslocFromData(String modo);

	boolean hasAnyDeslocInData(String data);

	boolean hasAnyDesloc();

	String listaAllDesloc();

}
