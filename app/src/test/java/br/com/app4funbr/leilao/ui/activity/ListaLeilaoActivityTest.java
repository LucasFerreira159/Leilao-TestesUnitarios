package br.com.app4funbr.leilao.ui.activity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListaLeilaoActivityTest{

    @Test
    public void deve_AtualizaListaDeLeiloes_QuandoBuscarLeiloesDaApi() throws InterruptedException {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();

        activity.configuraAdapter();
        activity.buscaLeiloes();
        Thread.sleep(2000);
        int quantidadeLeiloesDevolvida = activity.getAdapter().getItemCount();

        assertThat(quantidadeLeiloesDevolvida, is(2));
    }
}