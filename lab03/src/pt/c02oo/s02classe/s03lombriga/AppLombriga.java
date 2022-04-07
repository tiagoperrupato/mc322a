package pt.c02oo.s02classe.s03lombriga;

public class AppLombriga {

   public static void main(String[] args) {
      
	  Toolkit tk = Toolkit.start();
      
      String lombrigas[] = tk.recuperaLombrigas();
      Animacao animacao;
      String passo;
      
      // para cada aquário, executa uma animação
      for (int l = 0; l < lombrigas.length; l++) {
    	  
    	  // registra a situação inicial do aquário no csv
    	  animacao = new Animacao(lombrigas[l]);
    	  tk.gravaPasso("====="); // identifica o início da animação
    	  passo = animacao.apresenta(); // retorna a situação do aquário
    	  tk.gravaPasso(passo); // registra no csv
    	  
    	  // executa uma acão e registra ela no csv
    	  for (int i = 6; i < lombrigas[l].length(); i++) {
    		  animacao.acao = lombrigas[l].charAt(i); // retorna a ação a ser executada
    		  animacao.passo(); // executa o passo
        	  passo = animacao.apresenta(); // retorna a situação atual do aquário
        	  tk.gravaPasso(passo); // registra no csv
    	  }
      }
      
      tk.stop();
   }
}
