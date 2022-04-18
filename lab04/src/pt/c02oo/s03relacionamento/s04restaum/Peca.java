package pt.c02oo.s03relacionamento.s04restaum;

public class Peca {
	/* cria atributos para guardar a posição da peça e seu estado
	 * se está dentro do tabuleiro e se é peça
	 */
	int linha;
	int coluna;
	boolean ehTabuleiro;
	boolean temPeca;
	
	// construtor do objeto peça
	Peca(int linha, int coluna, boolean ehTabuleiro, boolean temPeca) {
		this.linha = linha;
		this.coluna = coluna;
		this.ehTabuleiro = ehTabuleiro;
		this.temPeca = temPeca;
	}
	
	
	/* método que analisa se a jogada solicitada para a peça source fazer é valida
	 * altera a matriz de peças que fizeram parte da jogada
	 * retorna true ou false
	 */
	boolean analisaJogada(Peca target, Peca jumped) {
		if ((this.ehTabuleiro && this.temPeca) &&
			(jumped.ehTabuleiro && jumped.temPeca) &&
			(target.ehTabuleiro && !(target.temPeca))) 
		{
			// atualiza estado da peça (source)
			this.temPeca = false;
			return true;
		} else return false;
	}
}
