package br.com.projeto.ed.avl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArvoreAvl {

	private No raiz;
	//MUDAR DIRETORIO AQUI, COLOCAR DIRETORIO DO ENTRADA.TXT!!!!
	private String diretorioEntrada ="demo\\src\\main\\resources\\entrada.txt";
	//MUDAR DIRETORIO AQUI, COLOCAR DIRETORIO DO SAIDA.TXT!!!
	private String diretorioSaida ="demo\\src\\main\\resources\\saida.txt";
	public No getRaiz() {
		return raiz;
	}

	public void inserir(Aluno k) {
		No n = new No(k);
		inserirRecursivo(this.raiz, n);
	}

	public void inserirRecursivo(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.raiz = aInserir;

		} else {

			if (aInserir.getChave().getMatricula() < aComparar.getChave().getMatricula()) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirRecursivo(aComparar.getEsquerda(), aInserir);
				}

			} else if (aInserir.getChave().getMatricula() > aComparar.getChave().getMatricula()) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					inserirRecursivo(aComparar.getDireita(), aInserir);
				}

			} else {
				// O nó já existe
			}
		}
	}

	public void verificarBalanceamento(No atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	public void remover(int k) {
		removerRecursivo(this.raiz, k);
	}

	public void removerRecursivo(No atual, int k) {
		if (atual == null) {
			return;

		} else {

			if (atual.getChave().getMatricula() > k) {
				removerRecursivo(atual.getEsquerda(), k);

			} else if (atual.getChave().getMatricula() < k) {
				removerRecursivo(atual.getDireita(), k);

			} else if (atual.getChave().getMatricula() == k) {
				removerNoEncontrado(atual);
			}
		}
	}

	public void removerNoEncontrado(No aRemover) {
		No r;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

			if (aRemover.getPai() == null) {
				this.raiz = null;
				aRemover = null;
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}

		No p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			this.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}

	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);

			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);

			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No q) {
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	private int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;

		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());

		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());

		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	final public ArrayList<No> inorder() {
		ArrayList<No> ret = new ArrayList<No>();
		inorderRecursivo(raiz, ret);
		return ret;
	}

	final protected void inorderRecursivo(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		inorderRecursivo(no.getEsquerda(), lista);
		lista.add(no);
		inorderRecursivo(no.getDireita(), lista);
	}

	public Aluno buscarPorMatricula(int matricula) {
		return buscarPorMatriculaRecursivo(this.raiz, matricula);
	}
	//precisa para recursao 
	private Aluno buscarPorMatriculaRecursivo(No atual, int matricula) {
		if (atual == null) {
			return null; // Aluno não encontrado na árvore
		}

		int matriculaAtual = atual.getChave().getMatricula();

		if (matricula == matriculaAtual) {
			return atual.getChave(); // Aluno encontrado
		} else if (matricula < matriculaAtual) {
			return buscarPorMatriculaRecursivo(atual.getEsquerda(), matricula);
		} else {
			return buscarPorMatriculaRecursivo(atual.getDireita(), matricula);
		}
	}

	public void escreverEmArquivoEntrada() {

        try {
			//abre os arquivos
            BufferedReader leitorSaida = new BufferedReader(new FileReader(diretorioSaida));
            BufferedWriter escritorEntrada = new BufferedWriter(new FileWriter(diretorioEntrada));
            String linha;
            while ((linha = leitorSaida.readLine()) != null) {
                escritorEntrada.write(linha);
				//pula linha 
                escritorEntrada.newLine(); 
            }

            leitorSaida.close();
            escritorEntrada.close();

            System.out.println("Substituição concluída com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao ler/escrever os arquivos: " + e.getMessage());
        }
	}

	public void escreverEmArquivoSaida() {
		ArrayList<No> listaOrdenada = inorder();

		try (BufferedWriter leitorSaida = new BufferedWriter(new FileWriter(diretorioSaida))) { 
			for (No no : listaOrdenada) {
				Aluno aluno = no.getChave();
				leitorSaida.write(aluno.getMatricula() + ","+ aluno.getNome() + "," +
						aluno.getFaltas() + ", " + aluno.getN1() + "," + aluno.getN2() +
						"," + aluno.getN3() +"\n");
			}
			System.out.println("Dados gravados em 'saida.txt'");
			leitorSaida.close();
		} catch (IOException e) {
			System.err.println("Erro ao escrever no arquivo 'saida.txt': " + e.getMessage());
		}
	}
}