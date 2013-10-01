import java.text.DecimalFormat;

/**
 * 
 */

/**
 * @author Luciano �dipo
 * @author Marcelo Benites
 */
public class Simulador {

	/**
	 * Quantidades de simula��es realizadas para decidir um ponto do grafico
	 */
	public static final int REPET = 30;
	/**
	 * z =1- alpha/2 alpha para o intervalo de confian�a de 95% � 0.05
	 */
	public static final double Z = 1.96;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length == 0) {
			Simulador.help();
			return;
		}

		Simulacao simulacao;
		double rm, tu;
		double intervalo = 1;
		double vt_rm[] = new double[REPET];
		double vt_tu[] = new double[REPET];
		DecimalFormat df = new DecimalFormat("#.#######");
		/**
		 * Iniciamos nossa simula��o com intervalo m�dio de 1 e decrementamos
		 * esse
		 */
		do {

			for (int j = 0; j < REPET; j++) {
				simulacao = new Simulacao(intervalo,
						Double.parseDouble(args[0]));
				vt_rm[j] = simulacao.getRM();
				vt_tu[j] = simulacao.getTU();
			}
			rm = iC(vt_rm);
			tu = iC(vt_tu);
			System.out.println(df.format(tu) + "\t" + df.format(rm));

			intervalo = intervalo / 1.1;
		} while ((intervalo > 0.0005) && tu < 0.95);

	}

	private static void help() {
		System.out.println("Simulador de Eventos Discretos v. 1.0");
		System.out.println("--------------------------------------");
		System.out.println("Uso: java -jar SimulaLM.jar velocidade");

	}

	/**
	 * Calcula m�dia entre os valores do vetor
	 * 
	 * @param vt
	 *            vetor com conjunto de simula��es realizadas para defini��o de
	 *            um ponto do grafico
	 * @return media
	 */
	public static double media(double vt[]) {
		double soma = 0, media = 0;
		for (int i = 0; i < vt.length; i++) {
			soma += vt[i];
		}
		media = soma / vt.length;
		return media;
	}

	/**
	 * M�todo que calcula a vari�ncia
	 * 
	 * @param vt
	 *            vetor de valores
	 * @return a variancia desse conjunto
	 */
	public static double variancia(double vt[]) {
		double soma = 0, variancia = 0, media = media(vt);
		for (int i = 0; i < vt.length; i++) {
			soma += Math.pow((vt[i] - media), 2);
		}
		variancia = soma / vt.length;
		return variancia;
	}

	/**
	 * M�todo de c�lculo do desvio padr�o
	 * 
	 * @param vt
	 *            vetor de valores
	 * @return desvio padr�o
	 */
	public static double desvioP(double vt[]) {
		return Math.sqrt(variancia(vt));
	}

	/**
	 * realiza o calculo do intervalo de confian�a para cada ponto do conjunto
	 * no vetor
	 * 
	 * @param vt
	 *            vetor de valores
	 * @return m�dia dos pontos dentro do intervalo de confian�a
	 */
	public static double iC(double vt[]) {
		double soma = 0, ponto;
		int qntd_valido = 0;
		double intervaloMenor = media(vt) - Z
				* (desvioP(vt) / Math.sqrt(REPET));
		double intervaloMaior = media(vt) + Z
				* (desvioP(vt) / Math.sqrt(REPET));
		for (int i = 0; i < vt.length; i++) {
			if ((vt[i] >= intervaloMenor) && (vt[i] <= intervaloMaior)) {
				soma += vt[i];
				qntd_valido++;
			}
		}
		ponto = soma / qntd_valido;
		return ponto;
	}
}
