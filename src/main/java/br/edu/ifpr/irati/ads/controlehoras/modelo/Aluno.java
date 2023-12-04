package br.edu.ifpr.irati.ads.controlehoras.modelo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "alunos")
public class Aluno implements Comparable<Aluno>, Serializable {

    private static final long serialVersionUID = 350087217396827505L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-alunos")
    @SequenceGenerator(name = "seq-alunos", sequenceName = "ALUNOS_SEQ", allocationSize = 1, initialValue = 1)
    private int id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id", nullable = false)
    private List<ItemAluno> listaItemAluno;

    public Aluno(int id, String nome, List<ItemAluno> listaItemAluno) {
        this.id = id;
        this.nome = nome;
        this.listaItemAluno = listaItemAluno;
    }

    public Aluno() {
        id = 0;
        nome = "";
        listaItemAluno = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemAluno> getListaItemAluno() {
        return listaItemAluno;
    }

    public void setListaItemAluno(List<ItemAluno> listaItemAluno) {
        this.listaItemAluno = listaItemAluno;
    }

    @Override
    public int compareTo(Aluno o) {
        return -this.nome.compareTo(o.getNome());
    }

    public void adicionarListaItemAluno(ItemAluno itemAluno) {
        this.listaItemAluno.add(itemAluno);
    }
}
