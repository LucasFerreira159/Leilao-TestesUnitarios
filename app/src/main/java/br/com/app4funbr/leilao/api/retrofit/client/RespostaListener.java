package br.com.app4funbr.leilao.api.retrofit.client;

public interface RespostaListener<T> {
    void sucesso(T resposta);

    void falha(String mensagem);
}