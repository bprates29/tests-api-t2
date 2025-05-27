package org.example.crud_exemplo;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdutoCrud {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        AtomicInteger idGenerator = new AtomicInteger(1);

        Javalin app = Javalin.create( javalinConfig ->
                javalinConfig.http.defaultContentType = "application/json; charset=UTF-8")
                .start(7000);

        //GET - listar todos os produtos
        app.get("/produtos", ctx -> ctx.json(produtos));

        //GET - buscar por id
        app.get("produtos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            var produto = produtos
                    .stream()
                    .filter(p -> p.id == id)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("Produto não encontrado"));
            ctx.json(produto);
        });

        // POST - criar novo produto
        app.post("/produtos", ctx -> {
            String nome = ctx.body();
            Produto novo = new Produto(idGenerator.getAndIncrement(), nome);
            produtos.add(novo);
            ctx.status(201).json(novo);
        });
    }
}
