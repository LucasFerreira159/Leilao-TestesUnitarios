package br.com.app4funbr.leilao;

import android.content.Context;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.app4funbr.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.app4funbr.leilao.api.retrofit.client.RespostaListener;
import br.com.app4funbr.leilao.model.Leilao;
import br.com.app4funbr.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeLeiloesTest {
    @Mock
    private LeilaoWebClient client;
    @Mock
    private ListaLeilaoAdapter adapter;
    @Mock
    private Context context;

    @Test
    public void deve_AtualizaListaDeLeiloes_QuandoBuscarLeiloesDaApi() throws InterruptedException {
        final List<Leilao> leilaos = Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro")
        );

        AtualizadorDeLeiloes atualizador = new AtualizadorDeLeiloes();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<Leilao>(leilaos));
                return null;
            }
        }).when(client).todos(ArgumentMatchers.any(RespostaListener.class));

        atualizador.buscaLeiloes(adapter, client, context);
        verify(client).todos(ArgumentMatchers.any(RespostaListener.class));
        verify(adapter).atualiza(leilaos);
    }
}