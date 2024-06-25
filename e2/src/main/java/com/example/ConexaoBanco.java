package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;

public class ConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/gatosdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado com o Banco de Dados");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void cadastrarGato(Gato gato) {
        String SQL = "INSERT INTO gato(nome, raca, cor, sexo) VALUES(?,?,?,?)";
        try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setString(1, gato.nome);
            pstmt.setString(2, gato.raca);
            pstmt.setString(3, gato.cor);
            pstmt.setString(4, gato.sexo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ObservableList<Gato> pegarGatos() {
        String SQL = "SELECT * FROM gato";
        ObservableList<Gato> gatos = javafx.collections.FXCollections.observableArrayList();
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Gato gato = new Gato(
                    rs.getString("nome"),
                    rs.getString("raca"),
                    rs.getString("cor"),
                    rs.getString("sexo")
                );
                gatos.add(gato);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gatos;
    }

    public static void deletarGato(Gato gato) {
        String SQL = "DELETE FROM gato WHERE nome = ?";
        try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setString(1, gato.nome);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editarGato (Gato gato, String gatoNome) {
        String SQL = "UPDATE gato SET nome = ?, raca = ?, cor = ?, sexo = ? WHERE nome = ?";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, gato.nome);
            pstmt.setString(2, gato.raca);
            pstmt.setString(3, gato.cor);
            pstmt.setString(4, gato.sexo);
            pstmt.setString(5, gatoNome);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

