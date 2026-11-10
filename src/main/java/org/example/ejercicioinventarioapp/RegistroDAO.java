package org.example.ejercicioinventarioapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {

    public List<Registro> findAll() throws SQLException {
        String sql = "SELECT id, nombre, descripcion, fecha, estado FROM registros ORDER BY id";
        List<Registro> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Registro(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("estado")));
            }
        }
        return lista;
    }

    public void insert(Registro r) throws SQLException {
        String sql = "INSERT INTO registros (nombre, descripcion, fecha, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getDescripcion());
            ps.setDate(3, Date.valueOf(r.getFecha()));
            ps.setString(4, r.getEstado());
            ps.executeUpdate();
        }
    }
}