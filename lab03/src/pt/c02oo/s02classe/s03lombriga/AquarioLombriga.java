package pt.c02oo.s02classe.s03lombriga;

public class AquarioLombriga {
	
	int tamAquario, tamLombriga, pos;
	String lado;
	
	// cria um objeto que representa o aquário
	AquarioLombriga(int tamAquario, int tamLombriga, int pos) {
		if (pos > tamAquario || pos < 0)	// solução para valores inválidos de posição
			pos = 1;
		
		// solução para lombrigas maiores que aquário ou com parte do corpo para fora
		while ((tamLombriga + pos-1) > tamAquario) {
			if (pos != 1)
				pos = 1;
			else 
				tamLombriga = tamAquario;
		}
		
		// atribuições
		this.tamAquario = tamAquario;
		this.tamLombriga = tamLombriga;
		this.lado = "esquerdo";
		this.pos = pos;
	}
	
	// método que faz a lombriga crescer
	void crescer() {
		if (this.lado.equals("esquerdo")) {
			if ((this.tamLombriga + this.pos-1) < this.tamAquario)
				this.tamLombriga += 1;
		}
		else {
			if ((this.tamLombriga + this.tamAquario-this.pos) < this.tamAquario)
				this.tamLombriga += 1;
		}
	}
	
	// método que faz a lombriga virar de lado
	void virar() {
		if (this.lado.equals("esquerdo")) {
			this.lado = "direito";
			this.pos = this.pos + this.tamLombriga -1;
		}
		else {
			this.lado = "esquerdo";
			this.pos = this.pos - this.tamLombriga + 1;
		}
	}
	
	// move a lombriga uma posição na direção da sua cabeça, se possível
	void mover() {
		if (this.lado.equals("esquerdo")) {
			if (this.pos > 1)
				this.pos -= 1;
			else
				this.virar();
		}
		else {
			if (this.pos < this.tamAquario)
				this.pos += 1;
			else
				this.virar();
		}
	}
	
	// método que apresenta o estado atual do aquário
	String apresenta() {
		String aquario = ""; // string que representa o aquário
		int acc; // variável acumulativa que guarda a posição a ser representada na string
		
		// passos para caso a lombriga esteja virada para o lado esquerdo
		if (this.lado.equals("esquerdo")) {
			acc = 1;
			
			// enquanto não chega na cabeça
			while(acc < this.pos) {
				aquario = aquario + "#";
				acc++;
			}
			
			// registra a cabeça
			aquario = aquario + "O";
			acc++;
			
			//registra o corpo
			for (int i = 0; i < (this.tamLombriga-1); i++) {
				aquario = aquario + "@";
				acc++;
			}
			
			// registra o resto do aquário caso tenha
			while (acc <= this.tamAquario) {
				aquario = aquario + "#";
				acc++;
			}
			return aquario;
		}
		
		// passos para caso a lombriga esteja virada para direita
		else {
			acc = this.tamAquario; // acumulativa começa no fim e vai decrescendo
			
			// adiciona a situação no início da string
			
			// enquanto n hega na cabeça
			while(acc > this.pos) {
				aquario = "#" + aquario;
				acc--;
			}
			
			// adiciona a cabeça
			aquario = "O" + aquario;
			acc--;
			
			// adiciona o corpo
			for (int i = 0; i < (this.tamLombriga-1); i++) {
				aquario = "@" + aquario;
				acc--;
			}
			
			// adiciona o resto do aquário caso tenha
			while (acc > 0) {
				aquario = "#" + aquario;
				acc--;
			}
			return aquario;
		}
	}
}
