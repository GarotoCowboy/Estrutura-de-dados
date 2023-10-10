package br.com.projeto.ed.avl;
public class Aluno {
   private int matricula;
   private int faltas;
   private double n1,n2,n3;
   private double media;
   private String nome;
   
   

@Override
public String toString() {
    return "Aluno [matricula=" + matricula + ", faltas=" + faltas + ", n1=" + n1 + ", n2=" + n2 + ", n3=" + n3
            + ", media=" + media + ", nome=" + nome + "]";
}

public Aluno(int matricula, String nome,int faltas, double n1, double n2, double n3) {
    this.matricula = matricula;
    this.faltas = faltas;
    this.n1 = n1;
    this.n2 = n2;
    this.n3 = n3;
    this.nome = nome;
    this.media = (n1*0.2)+(n2*0.35)+ (n3*0.45); //calculo da media
}

public String getNome() {
    return nome;
}
public void setNome(String nome) {
    this.nome = nome;
}
public double getMedia() {
    return media;
}
public void setMedia(double media) {
    this.media = media;
}
public int getMatricula() {
    return matricula;
}
public void setMatricula(int matricula) {
    this.matricula = matricula;
}
public int getFaltas() {
    return faltas;
}
public void setFaltas(int faltas) {
    this.faltas = faltas;
}
public double getN1() {
    return n1;
}
public void setN1(double n1) {
    this.n1 = n1;
}
public double getN2() {
    return n2;
}
public void setN2(double n2) {
    this.n2 = n2;
}
public double getN3() {
    return n3;
}
public void setN3(double n3) {
    this.n3 = n3;
}


}
