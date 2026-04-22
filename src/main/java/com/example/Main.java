package com.example;

import com.example.entity.Client;
import com.example.entity.Produit;
import com.example.entity.Commande;
import com.example.entity.LigneCommande;
import com.example.service.ClientService;
import com.example.service.CommandeService;
import com.example.service.ProduitService;
import com.example.util.JpaUtil;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

    ClientService cs = new ClientService();
    ProduitService ps = new ProduitService();
    CommandeService cmdService = new CommandeService();

    // ✅ 1. Add clients
    Client c1 = new Client();
    c1.setNom("Ali");
    c1.setEmail("ali@test.com");

    Client c2 = new Client();
    c2.setNom("Sara");
    c2.setEmail("sara@test.com");

    cs.addClient(c1);
    cs.addClient(c2);

    // ✅ 2. Add produits
    Produit p1 = new Produit(); p1.setNom("PC"); p1.setPrix(2000); p1.setStock(10);
    Produit p2 = new Produit(); p2.setNom("Mouse"); p2.setPrix(50); p2.setStock(20);
    Produit p3 = new Produit(); p3.setNom("Keyboard"); p3.setPrix(100); p3.setStock(15);

    ps.addProduit(p1);
    ps.addProduit(p2);
    ps.addProduit(p3);

    // ⚠️ reload from DB (IMPORTANT)
    p1 = ps.getAllProduits().get(0);
    p2 = ps.getAllProduits().get(1);
    p3 = ps.getAllProduits().get(2);

    c1 = cs.getAllClients().get(0);

    // ✅ 3. Create commande
    Commande cmd = new Commande();
    cmd.setClient(c1);
    cmd.setDateCommande(LocalDate.now());

    LigneCommande l1 = new LigneCommande();
    l1.setProduit(p1);
    l1.setQuantite(2);

    LigneCommande l2 = new LigneCommande();
    l2.setProduit(p2);
    l2.setQuantite(1);

    LigneCommande l3 = new LigneCommande();
    l3.setProduit(p3);
    l3.setQuantite(3);

    cmd.setLignes(List.of(l1, l2, l3));

    cmdService.createCommande(cmd);

    // ✅ 4. Check stock
    ps.getAllProduits().forEach(p ->
            System.out.println(p.getNom() + " stock = " + p.getStock())
    );

    // ✅ 5. Most sold products
    cmdService.produitsPlusVendus();
}   
}