package br.edu.ifpr.irati.ads.controlehoras.modelo;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "items")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-items")
    @SequenceGenerator(name = "seq-items", sequenceName = "ITEMS_SEQ", allocationSize = 1, initialValue = 1)
    private int id;

    @Column(name = "descricao")
    private String descricao;

    public Item () {
        id = 0;
        descricao = "";
    }

    public Item(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
