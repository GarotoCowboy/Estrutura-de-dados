import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception {
       String arquivo = "ArvoreAvl/src/dados/entrada.txt";

       try(BufferedReader reader = new BufferedReader(new FileReader(arquivo))){
        String linha;
        ArvoreAvl avl = new ArvoreAvl();
        

        while((linha = reader.readLine()) != null ){
            String dados[] = linha.split(",");
            System.out.println(Integer.parseInt(dados[0])+Integer.parseInt(dados[0]) );
            int matricula = Integer.parseInt(dados[0]);

            Aluno aluno = new Aluno( matricula, dados[1] );
            avl.inserir(aluno);
        }


        //System.out.println(avl.getClass().toString());
        
       }
       
       
       
       
     
    }
}
