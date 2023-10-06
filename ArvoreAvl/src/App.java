import java.io.BufferedReader;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception {

       String arquivo = "ArvoreAvl/src/dados/entrada.txt";
        ArvoreAvl avl = new ArvoreAvl();
        
        String linha;
    
       try(BufferedReader reader = new BufferedReader(new FileReader(arquivo))){
        //puxa todos os dados do arquivo de entrada e coloca na arvore
        while((linha = reader.readLine()) != null ){
            String dados[] = linha.split(",");
            Aluno aluno = new Aluno(Integer.parseInt(dados[0]), dados[1],Integer.parseInt(dados[2]),Double.parseDouble(dados[3]),Double.parseDouble(dados[4]),Double.parseDouble(dados[5]));
            avl.inserir(aluno); }
        
        }catch (NumberFormatException e) {
            System.err.println("Erro ao converter número na linha: ");
            
        }catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        //Para inserir !!TA PRONTO SO DESCOMENTAR!!
        // Aluno aluno =new Aluno(/*matricula*/Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a Matricula", "Matricula", 1)),
        //                /*nome*/JOptionPane.showInputDialog(null, "Digite o nome", "Nome", 1),
        //                 /*faltas*/Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de faltas", "Faltas", 1)),
        //                /*nota 1*/ Double.parseDouble( JOptionPane.showInputDialog(null, "Digite a primeira nota", "Nota 01", 1)),
        //                 /*nota 2*/Double.parseDouble( JOptionPane.showInputDialog(null, "Digite a segunda nota", "Nota 02", 1)),
        //                 /*nota 3*/Double.parseDouble( JOptionPane.showInputDialog(null, "Digite a terceira nota", "Nota 03", 1))
        //                 );
        // avl.inserir(aluno);((
        
        //Para buscar um unico aulono por matricula !!MELHORAR O PRINT!!
        System.out.println(avl.buscarPorMatricula(15).toString());

        //Remover por matricula
        avl.remover(15);

         // Imprime toda os dados da arvore
        for (No no : avl.inorder()) {
            System.out.println("Matrícula: " + no.getChave().getMatricula() + ", Nome: " + no.getChave().getNome()+", Faltas: "+no.getChave().getFaltas()+ ", Nota 01: "+no.getChave().getN1()+
             ", Nota 02: "+no.getChave().getN2()+ ", Nota 03: "+no.getChave().getN3()+ ", Media das notas: "+no.getChave().getMedia());
        }
        
        //FALTA PASSAR TUDO PAR AO ARQUIVO TXT, MELHORAR OS CATH, PREPARAR O MENU DE MANEIRA CORRETA.
       
    
    }
}
