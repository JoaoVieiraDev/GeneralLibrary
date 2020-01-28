package FCTBoleia;

import dataStructures.*;

public class FCTBoleiaClass implements FCTBoleia {
	
	private static final long serialVersionUID = -4618772066255118451L;
	public boolean initialMode;
	public User currentUser;
	public Map<String, User> users;
	public Map<String, SortedMap<String, Deslocacao>> deslocacoesPorData;
	public SortedMap<DateAndName, Deslocacao> allDeslocacoes;
	
	public FCTBoleiaClass() {
		this.initialMode = true;
		this.currentUser = null;
		users = new SepChainHashTable<String, User>(200);
		deslocacoesPorData = new SepChainHashTable<String, SortedMap<String, Deslocacao>>(200);
		allDeslocacoes = new OrderedDoubleList<DateAndName, Deslocacao>();
	}

	@Override
	public void registerUser(String email, String nome, String password) {
		User newUser = new UserClass(email, nome, password);
		users.insert(email, newUser);
	}

	@Override
	public boolean initialMode() {
		return this.initialMode;
	}

	@Override
	public boolean hasUser(String email) {
		return this.users.find(email) != null;
	}

	@Override
	public int getNumberOfRegistos() {
		return this.users.size();
	}

	@Override
	public String getUserPassword(String email) {
		return this.users.find(email).getPassword();
	}

	@Override
	public void login(String email) {
		this.initialMode = false;
		this.currentUser = users.find(email);
		this.currentUser.addVisit();
	}

	@Override
	public int getNumberOfUserVisits() {
		return this.currentUser.getNumberOfVisits();
	}

	@Override
	public String getCurrentUser() {
		return this.currentUser.getEmail();
	}

	@Override
	public void logout() {
		this.initialMode = true;
		this.currentUser = null;
	}

	@Override
	public boolean userHasDesloc(String data) {
		return this.currentUser.hasDesloc(data);
	}

	@Override
	public void addDesloc(String origem, String destino, String data, String hora, int duration, int lugares) {
		Deslocacao newDesloc = new DeslocacaoClass(this.getCurrentUser(), origem, destino, data, hora, duration, lugares);
		this.currentUser.addDesloc(newDesloc, data);
		if(!(this.deslocacoesPorData.find((data)) != null)) {
			SortedMap<String, Deslocacao> tree = new OrderedDoubleList<String, Deslocacao>();
			this.deslocacoesPorData.insert(data, tree);
			this.deslocacoesPorData.find(data).insert(newDesloc.getOwner(), newDesloc);
		} else this.deslocacoesPorData.find(data).insert(newDesloc.getOwner(), newDesloc);
		DateAndName dateAndName = new DateAndName(newDesloc);
		this.allDeslocacoes.insert(dateAndName, newDesloc);
		
	}

	@Override
	public int getNumberOfUserDesloc() {
		return this.currentUser.getNumberOfDesloc();
	}

	@Override
	public String getCurrentUserName() {
		return this.currentUser.getName();
	}

	@Override
	public void removeDesloc(String data) {
		Deslocacao d = this.currentUser.getDesloc(data);
		this.allDeslocacoes.removeWithCustomComparator(new DateAndName(d));
		this.deslocacoesPorData.find(data).remove(d.getOwner());
		this.currentUser.removeDesloc(data);
		
	}

	@Override
	public boolean otherUserHasDesloc(String email, String data) {
		return this.users.find(email).hasDesloc(data);
	}

	@Override
	public boolean userHasDeslocOuBoleia(String data) {
		return this.currentUser.hasDeslocOuBoleia(data);
	}

	@Override
	public int addBoleia(String email, String data) {
		int positionInWaitinglist = this.users.find(email).addBoleiaToDesloc(this.currentUser.getEmail(), data);
		this.currentUser.addBoleia(this.users.find(email).getDesloc(data), data);
		return positionInWaitinglist;
	}

	@Override
	public boolean deslocHasBoleias(String data) {
		return this.currentUser.deslocHasBoleias(data);
	}

	@Override
	public boolean userHasBoleia(String data) {
		return this.currentUser.hasBoleia(data);
	}

	@Override
	public void removeBoleia(String data) {
		this.currentUser.removeBoleia(data);
	}

	@Override
	public String consultaDesloc(String email, String data) {
		//if(this.currentUser.getEmail().equals(email)) return this.currentUser.consultadesloc(data);
		return this.users.find(email).consultadeslocFromOther(data);
	}

	@Override
	public boolean hasDesloc() {
		return this.currentUser.hasDeslocs();
	}

	@Override
	public String listMinhasDesloc() {
		return this.currentUser.listDesloc();
	}

	@Override
	public boolean hasBoleia() {
		return this.currentUser.hasBoleias();
	}

	@Override
	public String listaMinhasBoleias() {
		return this.currentUser.listBoleias();
	}

	@Override
	public String listaUserDesloc(String email) {
		return this.users.find(email).listDeslocFromOther();
	}

	@Override
	public String listaDeslocFromData(String data) {
		SortedMap<String, Deslocacao> tree = this.deslocacoesPorData.find(data);
		String result = "";
		for(Iterator<Entry<String, Deslocacao>> it = tree.iterator(); it.hasNext(); ) {
			Deslocacao d = it.next().getValue();
			if(!d.isFull()) {
				result = result + d.getOwner() + "\n\n";
			}
		}
		
		/*
		for(Deslocacao d : tree) {
			if(!d.isFull()) {
				result = result + d.getOwner() + "\n\n";
			}
		}
		*/
		return result;
	}

	@Override
	public boolean hasAnyDeslocInData(String data) {
		return this.deslocacoesPorData.find(data) != null;
	}

	@Override
	public boolean hasAnyDesloc() {
		return this.allDeslocacoes.size() != 0;
	}

	@Override
	public String listaAllDesloc() {
		String result = "";
		for(Iterator<Entry<DateAndName, Deslocacao>> it = this.allDeslocacoes.iterator(); it.hasNext(); ) {
			Deslocacao d = it.next().getValue();
			result = result + d.getData() + " " + d.getOwner() +"\n\n";
		}
		
		/*
		for(Deslocacao d : this.allDeslocacoes) {
			result = result + d.getData() + " " + d.getOwner() +"\n\n";
		}
		*/
		
		return result;
	}

}
