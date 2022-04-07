package pt.c02oo.s02classe.s03lombriga;

public class Animacao {
	char acao;
	AquarioLombriga aquario;
	
	// monta um objeto que representa a animação de um aquário
	Animacao(String lombrigas) {
		int tamAquario = Integer.parseInt(lombrigas.substring(0, 2));
		int tamLombriga = Integer.parseInt(lombrigas.substring(2, 4));
		int posIni = Integer.parseInt(lombrigas.substring(4, 6));
		
		// monta o aquário
		aquario = new AquarioLombriga(tamAquario, tamLombriga, posIni);
		acao = lombrigas.charAt(6); // registra a próxima ação para a animação
	}
	
	// retorna a situação atual do aquário
	String apresenta() {
		return this.aquario.apresenta();
	}
	
	// executa um passo da animação
	void passo() {
		
		// executa método para crescer
		if (this.acao == 'C')
			this.aquario.crescer();
		
		// executa método para mover
		else if (this.acao == 'M')
			this.aquario.mover();
		
		// executa método para virar (único restante possível)
		else
			this.aquario.virar();
	}
}
