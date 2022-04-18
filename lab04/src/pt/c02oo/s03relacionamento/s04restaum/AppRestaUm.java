package pt.c02oo.s03relacionamento.s04restaum;

public class AppRestaUm {

   public static void main(String[] args) {
      AppRestaUm.executaJogo(null, null); // executa o jogo RestaUm
   }
   
   // método que executa o jogo RestaUm
   public static void executaJogo(String arquivoEntrada, String arquivoSaida) {
      Toolkit tk = Toolkit.start(arquivoEntrada, arquivoSaida);
      
      String commands[] = tk.retrieveCommands();
      
      // apresenta o tabuleiro inicial
      Tabuleiro tabuleiro = new Tabuleiro();
      tk.writeBoard("Tabuleiro inicial", tabuleiro.board);
      
      // executa cada jogada solicitada e apresenta o estado do tabuleiro
      for (int l = 0; l < commands.length; l++) {
    	  tabuleiro.executaJodada(commands[l]);
    	  tk.writeBoard("source: "+commands[l].substring(0, 2)+"; target: "+
    			  		commands[l].substring(3, 5), tabuleiro.board);
      }
      		
      tk.stop();
   }

}
