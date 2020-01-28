package FCTBoleia;

import dataStructures.*;

public class UserClass implements User {
	
	private static final long serialVersionUID = 7940006560895024722L;
	public String email;
	public String nome;
	public String password;
	public int numberOfVisits;
	public SortedMap<String, Deslocacao> deslocacoes;
	public SortedMap<String, Deslocacao> boleias;
	
	
	public UserClass(String email, String nome, String password) {
		this.email = email;
		this.nome = nome;
		this.password = password;
		this.numberOfVisits = 0;
		this.deslocacoes = new OrderedDoubleList<String, Deslocacao>();
		this.boleias = new OrderedDoubleList<String, Deslocacao>();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public int getNumberOfVisits() {
		return this.numberOfVisits;
	}

	@Override
	public String getName() {
		return this.nome;
	}

	@Override
	public void addVisit() {
		this.numberOfVisits++;
	}

	@Override
	public boolean hasDesloc(String data) {
		return this.deslocacoes.find(data) != null;
	}

	@Override
	public void addDesloc(Deslocacao newDesloc, String data) {
		this.deslocacoes.insert(data, newDesloc);
	}

	@Override
	public int getNumberOfDesloc() {
		return this.deslocacoes.size();
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void removeDesloc(String data) {
		this.deslocacoes.remove(data);
	}

	@Override
	public boolean hasDeslocOuBoleia(String data) {
		return this.deslocacoes.find(data) != null || this.boleias.find(data) != null;
	}

	@Override
	public Deslocacao getDesloc(String data) {
		return this.deslocacoes.find(data);
	}

	@Override
	public void addBoleia(Deslocacao desloc, String data) {
		this.boleias.insert(data, desloc);
	}

	@Override
	public int addBoleiaToDesloc(String user, String data) {
		return this.deslocacoes.find(data).addBoleia(user);
	}

	@Override
	public boolean deslocHasBoleias(String data) {
		return this.deslocacoes.find(data).hasBoleias();
	}

	@Override
	public boolean hasBoleia(String data) {
		return this.boleias.find(data) != null;
	}

	@Override
	public void removeBoleia(String data) {
		this.boleias.find(data).removeBoleia(this.email);
		this.boleias.remove(data);
	}

	@Override
	public String consultadesloc(String data) {
		return this.deslocacoes.find(data).consulta();
	}

	@Override
	public boolean hasDeslocs() {
		return this.deslocacoes.size()!=0;
	}

	@Override
	public String listDesloc() {
		String result = "";
		
		for(Iterator<Entry<String, Deslocacao>> it = this.deslocacoes.iterator(); it.hasNext(); ) {
			Deslocacao d = it.next().getValue();
			result = result + d.consulta() + "\n\n";
		}
		/*
		for(Deslocacao d : this.deslocacoes.values()) {
			result = result + d.consulta() + "\n\n";
		}
		*/
		return result;
	}

	@Override
	public boolean hasBoleias() {
		return this.boleias.size()!=0;
	}

	@Override
	public String listBoleias() {
		String result = "";
		
		for(Iterator<Entry<String, Deslocacao>> it = this.boleias.iterator(); it.hasNext(); ) {
			Deslocacao d = it.next().getValue();
			result = result + d.consultaComoLista() + "\n\n";
		}
		/*
		for(Deslocacao d : this.boleias.values()) {
			result = result + d.consultaComoLista() + "\n\n";
		}
		*/
		return result;
	}

	@Override
	public String listDeslocFromOther() {
		String result = "";
		
		for(Iterator<Entry<String, Deslocacao>> it = this.deslocacoes.iterator(); it.hasNext(); ) {
			Deslocacao d = it.next().getValue();
			result = result + d.consultaComoLista() + "\n";
		}
		/*
		for(Deslocacao d : this.deslocacoes.values()) {
			result = result + d.consultaComoLista() + "\n";
		}
		*/
		return result;
	}

	@Override
	public String consultadeslocFromOther(String data) {
		return this.deslocacoes.find(data).consultaComoOutro();
	}

}
