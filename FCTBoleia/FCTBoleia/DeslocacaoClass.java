package FCTBoleia;

import dataStructures.*;

public class DeslocacaoClass implements Deslocacao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String owner;
	public String origem;
	public String destino;
	public String data;
	public String hora;
	public int duration;
	public int lugares;
	public Queue<String> filaDeEspera;
	public List<String> ocupantes;
	
	public DeslocacaoClass(String user, String origem, String destino, String data, String hora, int duration, int lugares) {
		this.owner = user;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
		this.duration = duration;
		this.lugares = lugares;
		this.filaDeEspera = new QueueInList<String>();
		this.ocupantes = new DoublyLinkedList<String>();
	}
	
	@Override
	public String getOwner() {
		return this.owner;
	}

	@Override
	public int addBoleia(String user) {
		if(ocupantes.size() == lugares) {
			filaDeEspera.enqueue(user);
			return filaDeEspera.size();
		} else {
			ocupantes.addLast(user);
			return -1;
		}
	}

	@Override
	public boolean hasBoleias() {
		return ocupantes.size()!=0;
	}

	@Override
	public void removeBoleia(String email) {
		if(this.ocupantes.find(email) == -1) {
			this.filaDeEspera.remove(email);
		} else {
			this.ocupantes.remove(this.ocupantes.find(email));
			if(this.filaDeEspera.size()!=0) this.ocupantes.addLast(this.filaDeEspera.dequeue());
		}
	}

	@Override
	public String consulta() {
		String result =
				this.owner + "\n" +
				this.origem + "-" + this.destino +"\n" +
				this.data + " " + this.hora + " " + this.duration + "\n" +
				"Lugares vagos: " + (this.lugares-this.ocupantes.size()) + "\n";
				if(this.ocupantes.size() != 0) {
					result = result + "Boleias: ";
						for(Iterator<String> it = this.ocupantes.iterator(); it.hasNext(); ) {
							String d = it.next();
							result = result + d + "\n";
						}
				}
				else result = result + "Sem boleias registadas.\n";
				result = result + "Em espera: " + this.filaDeEspera.size();
		return result;
	}

	@Override
	public String consultaComoLista() {
		String result =
				this.owner + "\n" +
				this.origem + "-" + this.destino +"\n" +
				this.data + " " + this.hora + " " + this.duration;
		return result;
	}

	@Override
	public String consultaComoOutro() {
		String result =
				this.owner + "\n" +
				this.origem + "-" + this.destino +"\n" +
				this.data + " " + this.hora + " " + this.duration + " " + this.lugares;
		return result;
	}

	@Override
	public boolean isFull() {
		return this.ocupantes.size() == this.lugares;
	}

	@Override
	public String getData() {
		return this.data;
	}
}
