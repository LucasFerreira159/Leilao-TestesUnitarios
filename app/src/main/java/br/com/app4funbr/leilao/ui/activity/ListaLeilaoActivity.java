package br.com.app4funbr.leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import br.com.app4funbr.leilao.AtualizadorDeLeiloes;
import br.com.app4funbr.leilao.R;
import br.com.app4funbr.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.app4funbr.leilao.api.retrofit.client.RespostaListener;
import br.com.app4funbr.leilao.model.Leilao;
import br.com.app4funbr.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static br.com.app4funbr.leilao.ui.activity.LeilaoConstantes.CHAVE_LEILAO;


public class ListaLeilaoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Leilões";
    private final LeilaoWebClient client = new LeilaoWebClient();
    private final AtualizadorDeLeiloes atualizadorDeLeiloes = new AtualizadorDeLeiloes();
    private ListaLeilaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        getSupportActionBar().setTitle(TITULO_APPBAR);
        configuraListaLeiloes();
    }

    private void configuraListaLeiloes() {

        configuraAdapter();
        configuraRecyclerView();
    }

    private void configuraRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
    }

    private void configuraAdapter() {
        adapter = new ListaLeilaoAdapter(this);
        adapter.setOnItemClickListener(new ListaLeilaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Leilao leilao) {
                vaiParaTelaDeLances(leilao);
            }
        });
    }

    private void vaiParaTelaDeLances(Leilao leilao) {
        Intent vaiParaLancesLeilao = new Intent(
                ListaLeilaoActivity.this,
                LancesLeilaoActivity.class);
        vaiParaLancesLeilao.putExtra(CHAVE_LEILAO, leilao);
        startActivity(vaiParaLancesLeilao);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizadorDeLeiloes.buscaLeiloes(adapter, client, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lista_leilao_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.lista_leilao_menu_usuarios) {
            Intent vaiParaListaDeUsuarios = new Intent(this, ListaUsuarioActivity.class);
            startActivity(vaiParaListaDeUsuarios);
        }
        return super.onOptionsItemSelected(item);
    }
}
