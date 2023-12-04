package br.edu.ifpr.irati.ads.controlehoras.modelo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "item_alunos")
public class ItemAluno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-item_alunos")
    @SequenceGenerator(name = "seq-item_alunos", sequenceName = "ITEM_ALUNOS_SEQ", allocationSize = 1, initialValue = 1)
    private int id;

    @Column(name = "pagina", nullable = false)
    private int pagina;

    @Column(name = "horas", nullable = false)
    private double horas;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    private Item item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemAluno(int pagina, double horas, Item item) {
        this.pagina = pagina;
        this.horas = horas;
        this.item = item;
    }

    public ItemAluno() {
        pagina = 0;
        horas = 0.0;
        item = new Item();
    }

}
