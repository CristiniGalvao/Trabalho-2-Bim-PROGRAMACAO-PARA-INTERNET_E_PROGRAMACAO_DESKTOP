/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.net.URL;
import jdk.jshell.execution.Util;
import modelo.Cliente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.OutputStream;

/**
 *
 * @author Gabriella
 */
public class ApiService {
private static final String BASE_URL = "http://localhost:8080/api";
    private static final int SUCESSO = 200;
    
    public List<Cliente> buscarClientes() throws Exception {
        String urlChamada = BASE_URL + "/clientes";
        
        try {
            URL url = new URL(urlChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            
            if (conexao.getResponseCode() != SUCESSO) {
                throw new RuntimeException("Erro ao conectar: " + conexao.getResponseCode());
            }

            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = Util.converteJsonString(resposta);

            Gson gson = new Gson();
            return gson.fromJson(json, new TypeToken<List<Cliente>>(){}.getType());
        } catch (Exception ex) {
            throw new Exception("Erro ao retornar clientes: " + ex);
        }
    }
public void salvarCliente(Cliente cliente) throws Exception {
        String urlChamada = BASE_URL + "/clientes";
        try {
            URL url = new URL(urlChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json; utf-8");
            conexao.setDoOutput(true);

            Gson gson = new Gson();
            String jsonInputString = gson.toJson(cliente);

            try (OutputStream os = conexao.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            if (conexao.getResponseCode() != SUCESSO) {
                throw new RuntimeException("Erro ao salvar cliente: " + conexao.getResponseCode());
            }
        } catch (Exception ex) {
            throw new Exception("Erro ao salvar cliente: " + ex);
        }
    }
    public static class Util {
        public static String converteJsonString(BufferedReader bufferedReader) throws Exception {
            StringBuilder json = new StringBuilder();
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                json.append(linha);
            }
            return json.toString();
        }
    }
}