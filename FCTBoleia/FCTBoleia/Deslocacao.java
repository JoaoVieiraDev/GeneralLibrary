package FCTBoleia;

import java.io.Serializable;

public interface Deslocacao extends Serializable{

	int addBoleia(String user);

	boolean hasBoleias();

	void removeBoleia(String email);

	String consulta();

	String consultaComoLista();

	String consultaComoOutro();

	String getOwner();

	boolean isFull();

	String getData();

}
