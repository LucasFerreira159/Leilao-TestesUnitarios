package br.com.app4funbr.leilao.ui.recyclerview.adapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.app4funbr.leilao.model.Leilao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListaLeilaoAdapterTest {

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoReceberListaDeLeiloes() {
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(null);
        adapter.atualiza(new ArrayList<Leilao>(Arrays.asList(
                new Leilao("Console"),
                new Leilao("Computador")
        )));
        int quantidadeLeiloesDevolvida = adapter.getItemCount();
        assertThat(quantidadeLeiloesDevolvida, is(2));
    }
}