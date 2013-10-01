/**
 * Classe que representa um pacote
 * 
 * @author Marcelo Benites
 * @author Luciano Édipo
 */
public class Pacote {
	private double t_chegada, t_proc, intervalo, t_inicio, t_completo;
	private int tamanho;
	private boolean toNaFila;

	/**
	 * Construtordo pacote
	 * 
	 * @param tam
	 *            tamanho do pacote
	 * @param inst_chegada
	 *            instante de chegada do pacote
	 * @param intervalo
	 *            intervalo, intante de chegada menos intante de chegada do
	 *            pacote anterior
	 */
	public Pacote(int tam, double inst_chegada, double intervalo) {
		this.setInt_chegada(inst_chegada);
		this.setTamanho(tam);
		this.intervalo = intervalo;

	}

	/**
	 * @param int_chegada
	 *            the int_chegada to set
	 */
	public void setInt_chegada(double int_chegada) {
		this.t_chegada = int_chegada;
	}

	/**
	 * @return the int_chegada
	 */
	public double getInt_chegada() {
		return t_chegada;
	}

	/**
	 * @param tamanho
	 *            the tamanho to set
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * @return the tamanho
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * @param tempo_proc
	 *            the tempo_proc to set
	 */
	public void setTempo_proc(double tempo_proc) {
		this.t_proc = tempo_proc;
	}

	/**
	 * @return the tempo_proc
	 */
	public double getTempo_proc() {
		return t_proc;
	}

	/**
	 * @param toNaFila
	 *            the toNaFila to set
	 */
	public void setToNaFila(boolean toNaFila) {
		this.toNaFila = toNaFila;
	}

	/**
	 * @return the toNaFila
	 */
	public boolean isToNaFila() {
		return toNaFila;
	}

	/**
	 * @param intervalo
	 *            the intervalo to set
	 */
	public void setIntervalo(double intervalo) {
		this.intervalo = intervalo;
	}

	/**
	 * @return the intervalo
	 */
	public double getIntervalo() {
		return intervalo;
	}

	/**
	 * @param t_inicio
	 *            the t_inicio to set
	 */
	public void setT_inicio(double t_inicio) {
		this.t_inicio = t_inicio;
	}

	/**
	 * @return the t_inicio
	 */
	public double getT_inicio() {
		return t_inicio;
	}

	/**
	 * @param t_completo
	 *            the t_completo to set
	 */
	public void setT_completo(double t_completo) {
		this.t_completo = t_completo;
	}

	/**
	 * @return the t_completo
	 */
	public double getT_completo() {
		return t_completo;
	}
}
