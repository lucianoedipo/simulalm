import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Luciano
 * 
 */
public class Simulacao {
	private double TU, RM;
	/**
	 * Tamanho médio do pacote retirado da média dos tamahos de pacote
	 * encontrados no exercicio 2 no site da disciplina MO608 / MC962 - PROJETO
	 * DE REDES MULTIMÍDIA Disponível em:
	 * http://www.ic.unicamp.br/~nfonseca/MO648/doc/AMP1116IPZ.htm
	 */
	public static final int TAM_MED_PACOTE = 668;

	/**
	 * Quantidade de pacote utilizado para realização da simulação
	 */
	public static final int QTDE_PCT = 1000000;

	/**
	 * tamanho máximo em bytes de um pacote
	 */
	public static final int TAM_MAX_PCT = 1500;

	/**
	 * Taxa de velocidade 1500000 1.5Mbps 2000000 2.0Mbps 10000000 10Mbps
	 * 
	 */
	public static final double VEL_PROC = 1000000;

	/**
	 * Taxa de conversão de Byte para Bit
	 */
	public static final int TAX_BIT = 8;

	/**
	 * fila de pacotes da simulação
	 */
	public ArrayList<Pacote> filaPacote;

	private double intervaloMedio;
	private double tem_geral = 0;
	private double vel;

	public Simulacao(double intervaloMedio, double vel) {
		this.intervaloMedio = intervaloMedio;
		this.vel = vel;
		gerarFila();
		processarFila();
		calculaTuRM();
	}

	/**
	 * percorre a fila de pacotes e processoa os valores de: tempo de inicio de
	 * processamento, tempo de processamento e tempo em que ficará comleto o
	 * finalizado o processamento de cara pacote
	 */
	public void processarFila() {
		int i = 0;
		filaPacote.get(i).setTempo_proc(
				(filaPacote.get(i).getTamanho() * TAX_BIT)
						/ (this.vel * VEL_PROC));
		filaPacote.get(i).setT_inicio(filaPacote.get(i).getInt_chegada());
		filaPacote.get(i).setT_completo(
				filaPacote.get(i).getT_inicio()
						+ filaPacote.get(i).getTempo_proc());
		i++;
		for (; i < filaPacote.size(); i++) {
			filaPacote.get(i).setTempo_proc(
					(filaPacote.get(i).getTamanho() * TAX_BIT)
							/ (this.vel * VEL_PROC));
			/**
			 * se houver alguem na fila sendo processado, o tempo de inicio do
			 * pacote será igual ao tempo em q o pacote anterior terminar.
			 */
			if (filaPacote.get(i).getInt_chegada() < filaPacote.get(i - 1)
					.getT_completo()) {

				filaPacote.get(i).setT_inicio(
						(filaPacote.get(i - 1).getT_completo()));

				filaPacote.get(i).setT_completo(
						filaPacote.get(i - 1).getT_completo()
								+ filaPacote.get(i).getTempo_proc());
			}
			/**
			 * senão o pacote inicia seu processamento assim que chega.
			 */
			else {
				filaPacote.get(i).setT_inicio(
						filaPacote.get(i).getInt_chegada());

				filaPacote.get(i).setT_completo(
						filaPacote.get(i).getT_inicio()
								+ filaPacote.get(i).getTempo_proc());
			}
		}
	}

	/**
	 * método que preenche a fila com pacotes gerados
	 */
	public void gerarFila() {
		filaPacote = new ArrayList<Pacote>();
		double intervalo;
		for (int i = 0; i < QTDE_PCT; i++) {
			intervalo = gerIntCheg();
			filaPacote.add(new Pacote(gerTamPct(), getTempChegada(intervalo),
					intervalo));
		}
	}

	/**
	 * função de distribuição exponencial que gera o intervalo de chegada do
	 * pacote
	 * 
	 * @return intervalo de chegada
	 */
	public double gerIntCheg() {
		return -(intervaloMedio * Math.log(Math.random()));

	}

	/**
	 * Gera o instante de chegada
	 * 
	 * @param intervalo
	 *            intervalo gerado pela distribuição exponensial
	 * @return intante do pacote anterior + intervalo gerado.
	 */
	public double getTempChegada(double intervalo) {
		return tem_geral = tem_geral + intervalo;
	}

	/**
	 * Gerar tamanho do pacote
	 * 
	 * @return tamamho do pacote
	 */
	public int gerTamPct() {
		return (int) ((-((TAM_MED_PACOTE) * Math.log(Math.random()))) % TAM_MAX_PCT);
	}

	/**
	 * Método que realiza o cálculo de tempo de utilização e o retardo médio
	 */
	public void calculaTuRM() {
		Pacote pct;
		double pct_wait = 0, srv_busy = 0, avrWaitCust = 0;
		// DecimalFormat df = new DecimalFormat("#.#######");
		for (int i = 0; i < filaPacote.size(); i++) {
			pct = filaPacote.get(i);
			if (pct.getInt_chegada() < pct.getT_inicio()) {
				pct_wait++;
				// System.out.print(i + " >>FILA\t");
			}// else {
				// System.out.print(i + ">>OK\t");
			// }

			avrWaitCust += (pct.getT_inicio() - pct.getInt_chegada());

			srv_busy += pct.getTempo_proc();

			// System.out.println(" " + df.format(pct.getIntervalo()) + " \t"
			// + df.format(pct.getInt_chegada()) + " \t"
			// + df.format(pct.getT_inicio()) + " \t"
			// + df.format(pct.getTempo_proc()) + " \t"
			// + df.format(pct.getT_completo()));
		}
		this.RM = (avrWaitCust / QTDE_PCT);
		this.TU = srv_busy
				/ filaPacote.get(filaPacote.size() - 1).getT_completo();

		// System.out.println("Average waiting time for a customer: "
		// + df.format((avrWaitCust / QTDE_PCT)));
		// System.out
		// .println("Server Busy: "
		// + df.format(srv_busy
		// / filaPacote.get(filaPacote.size() - 1)
		// .getT_completo()));
		// double wait = pct_wait / QTDE_PCT;
		// System.out.println("Costumer has to wait: " + df.format(wait));
	}

	/**
	 * @param rM
	 *            the rM to set
	 */
	public void setRM(double rM) {
		RM = rM;
	}

	/**
	 * @return the rM
	 */
	public double getRM() {
		return RM;
	}

	/**
	 * @param tU
	 *            the tU to set
	 */
	public void setTU(double tU) {
		TU = tU;
	}

	/**
	 * @return the tU
	 */
	public double getTU() {
		return TU;
	}

}
