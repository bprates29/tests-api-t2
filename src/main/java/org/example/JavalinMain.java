package org.example;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.util.Map;

public class JavalinMain {
    public static void main(String[] args) {
        Javalin app = Javalin.create(
                javalinConfig -> javalinConfig.http.defaultContentType = "text/plain; charset=UTF-8"
        ).start(7000);

        //GET simples - resposta direta
        app.get("/hello", ctx -> {
            ctx.result("Olá, Javalin!");
        });

        //POST - lê o corpo da requisicão (raw body)
        app.post("/echo", ctx -> {
            String msg = ctx.body();
            ctx.status(HttpStatus.CREATED); //Definir o status HTTP
            ctx.result("Você disse: " + msg);
        });

        //GET com query param: /saudacao?nome=Bernardo
        app.get("/saudacao", ctx -> {
            String nome = ctx.queryParam("nome");
            ctx.result("Olá " + nome + "!");
                });

        //GET com path param: /usuarios/42
        app.get("/usuarios/{id}", ctx -> {
            String id = ctx.pathParam("id");
            ctx.result("UsuárioID = " + id);
                }
                );

        //GET que retorna um JSON: /produto
        app.get("/produto", ctx -> {
            var produto = Map.of("nome", "camiseta", "preco", 59.90);
            ctx.json(produto);
        });

        app.get("/produto/{id}/comentario/{id_comentario}", ctx -> {
            String id = ctx.pathParam("id");
            String idComentario = ctx.pathParam("id_comentario");
            String filtro = ctx.queryParam("ordem");
            String pagina = ctx.queryParam("pagina");
            ctx.result("Comentário do produto " + id + " - Ordem: " + filtro +
                    " - PAGINA: " + pagina +
                    " ID COMENTARIO: " + idComentario);
        });
    }
}
