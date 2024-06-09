/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalho_2bim;

import java.io.IOException;
import service.ApiProduto;
import view.ViewPrincipal;

/**
 *
 * @author Gabriella
 */
public class Trabalho_2Bim {

    public static void main(String[] args) {
       new ViewPrincipal().setVisible(true);
       
               ApiProduto apiProdut = new ApiProduto();
        try {
            double valorProduto = apiProdut.obterValorProdutoDaAPI("Camisa");
            System.out.println("Valor do produto: " + valorProduto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
