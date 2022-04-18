package pt.c02oo.s03relacionamento.s04restaum;

public class Tabuleiro {
	// cria uma matriz de 2 dimensões para representar o tabuleiro
	char board[][] = {
	         {' ', ' ', 'P', 'P', 'P', ' ', ' '},
	         {' ', ' ', 'P', 'P', 'P', ' ', ' '},
	         {'P', 'P', 'P', 'P', 'P', 'P', 'P'},
	         {'P', 'P', 'P', '-', 'P', 'P', 'P'},
	         {'P', 'P', 'P', 'P', 'P', 'P', 'P'},
	         {' ', ' ', 'P', 'P', 'P', ' ', ' '},
	         {' ', ' ', 'P', 'P', 'P', ' ', ' '}
	      };
	
	// cria uma matriz em que cada item aponta para um objeto Peça
	Peca[][] pecas;
	
	// Construtor do objeto Tabuleiro
	Tabuleiro() {
		pecas = new Peca[7][7];
		for (int i=0; i < board.length; i++) {
			for (int j=0; j < board[0].length; j++) {
				if (board[i][j] == ' ')
					pecas[i][j] = new Peca(i, j, false, false);
				else if (board[i][j] == 'P')
					pecas[i][j] = new Peca(i, j, true, true);
				else if (board[i][j] =='-')
					pecas[i][j] = new Peca(i, j, true, false);
			}
		}
	}
	
	// método que converte uma coluna em letra para representação em número
	int converteColuna(char colunaChar) {
		if (colunaChar == 'a')
			return 1;
		else if (colunaChar == 'b')
			return 2;
		else if (colunaChar == 'c')
			return 3;
		else if (colunaChar == 'd')
			return 4;
		else if (colunaChar == 'e')
			return 5;
		else if (colunaChar == 'f')
			return 6;
		else if (colunaChar == 'g') {
			return 7;
		}
		else return 0;
	}
	
	/* método que converte uma String que representa uma posição de peça para um vetor de int
	 * primeiro item representa coluna e segundo a linha
	 * retorna esse vetor
	 */
	int[] convertePos(String posString) {
		int[] posInt = new int[2];
		
		posInt[0] = converteColuna(posString.charAt(0))-1;
		posInt[1] = Integer.parseInt(posString.substring(1))-1;
		
		return posInt;
	}
	
	/* método que monta o trajeto feito pela peça source e encontra a peça pulada (jumped)
	 * retorna um vetor de posição da peça pulada
	 */
	int[] encontraTrajeto(int[] source, int[] target) {
		int[] jumped = new int[2];
		
		if(source[0] == target[0]) {
			jumped[0] = source[0];
			if((source[1] - target[1]) == 2) {
				jumped[1] = source[1]-1;
				return jumped;
			} else if((target[1] - source[1]) == 2) {
				jumped[1] = target[1]-1;
				return jumped;
			} else return null;
		} else if(source[1] == target[1]) {
			jumped[1] = source[1];
			if((source[0] - target[0]) == 2) {
				jumped[0] = source[0]-1;
				return jumped;
			} else if ((target[0] - source[0]) == 2) {
				jumped[0] = target[0]-1;
				return jumped;
			} else return null;
		} else return null;
	}
	
	// método que atualiza a matriz do tabuleiro caso tenha sido feita alguma movimentação de peça
	void atualizaBoard(int[] posPeca) {
		// caso a posição esteja dentro do tabuleiro e tenha peça
		if(this.pecas[posPeca[1]][posPeca[0]].ehTabuleiro && 
		   this.pecas[posPeca[1]][posPeca[0]].temPeca)
				board[posPeca[1]][posPeca[0]] = 'P';
		
		// caso a posição esteja no tabuleiro mas não tenha peça
		else if(this.pecas[posPeca[1]][posPeca[0]].ehTabuleiro && 
		   !(this.pecas[posPeca[1]][posPeca[0]].temPeca))
				board[posPeca[1]][posPeca[0]] = '-';
		
		// caso a posição não esteja no tabuleiro
		else if(!(this.pecas[posPeca[1]][posPeca[0]].ehTabuleiro) && 
				   !(this.pecas[posPeca[1]][posPeca[0]].temPeca))
						board[posPeca[1]][posPeca[0]] = ' ';
	}
	
	
	// método que executa uma jogada solicitada
	void executaJodada(String jogada) {
		int[] source = new int[2];
		int[] target = new int[2];
		int[] jumped = new int[2];
		
		// cria vetores de posições de cada peça que participa da jogada
		source = convertePos(jogada.substring(0, 2));
		target = convertePos(jogada.substring(3, 5));
		jumped = encontraTrajeto(source, target);
		
		// caso tenha encontrado uma peça pulada pela source
		if (jumped != null) {
			// se a jogada for válida (analisada pela peça source)
			if(this.pecas[source[1]][source[0]].analisaJogada(this.pecas[target[1]][target[0]],
															  this.pecas[jumped[1]][jumped[0]])) 
			{
			
				// atualiza estado do target e da peça pulada (jumped)
				this.pecas[target[1]][target[0]].temPeca = true;
				this.pecas[jumped[1]][jumped[0]].temPeca = false;
				
				// atualiza o tabuleiro
				this.atualizaBoard(source);
				this.atualizaBoard(jumped);
				this.atualizaBoard(target);
			}
		}
	}
}