package br.com.app4funbr.leilao;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.com.app4funbr.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.app4funbr.leilao.api.retrofit.client.RespostaListener;
import br.com.app4funbr.leilao.model.Leilao;
import br.com.app4funbr.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class AtualizadorDeLeiloes {

    private static final String MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES = "Não foi possível carregar os leilões";

    public void buscaLeiloes(final ListaLeilaoAdapter adapter, LeilaoWebClient client, final Context context) {
        client.todos(new RespostaListener<List<Leilao>>() {
            @Override
            public void sucesso(List<Leilao> leiloes) {
                adapter.atualiza(leiloes);
            }

            @Override
            public void falha(String mensagem) {
                Toast.makeText(context,
                        MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
