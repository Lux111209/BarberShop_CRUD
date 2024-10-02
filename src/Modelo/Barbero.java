/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.frmBarbero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luzma
 */
public class Barbero {
//1- Parametros
    String idBarbero;
    String nombreBarbero;
    int edadBarbero;
    int pesoBarbero;
    String correoBarbero;

    public String getidBarbero() {
        return idBarbero;
    }

    public void setidBarbero(String idBarbero) {
        this.idBarbero = idBarbero;
    }

    public String getnombreBarbero() {
        return nombreBarbero;
    }

    public void setnombreBarbero(String nombreBarbero) {
        this.nombreBarbero = nombreBarbero;
    }

    public int getedadBarbero() {
        return edadBarbero;
    }

    public void setedadBarbero(int edadBarbero) {
        this.edadBarbero = edadBarbero;
    }

    public int getpesoBarbero() {
        return pesoBarbero;
    }

    public void setpesoBarbero(int pesoBarbero) {
        this.pesoBarbero = pesoBarbero;
    }

    public String getcorreoBarbero() {
        return correoBarbero;
    }

    public void setcorreoBarbero(String correoBarbero) {
        this.correoBarbero = correoBarbero;
    }
       
    public void Guardar() {
        Connection conexion = ClaseConexion.getConexion();
        try {
            PreparedStatement addBarbero = conexion.prepareStatement("INSERT INTO tbBarbero(idBarbero, nombreBarbero, edadBarbero, pesoBarbero, correoBarbero) VALUES (?, ?, ?, ?, ?)");
            addBarbero.setString(1, UUID.randomUUID().toString());
            addBarbero.setString(2, getnombreBarbero());
            addBarbero.setInt(3, getedadBarbero());
            addBarbero.setInt(4, getpesoBarbero());
            addBarbero.setString(5, getcorreoBarbero());
 
            addBarbero.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("Este es el error en el modelo: Método guardar " + ex);
        }
    }
    
    
        public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "DELETE FROM tbBarbero where idBarbero = ?";
            PreparedStatement deleteBarbero = conexion.prepareStatement(sql);
            deleteBarbero.setString(1, miId);
            deleteBarbero.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error método eliminar" + e);
        }
    }

    public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            String idBarbero = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "UPDATE tbBarbero set nombreBarbero= ?, edadBarbero= ?, pesoBarbero= ?, correoBarbero= ? where idBarbero = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getnombreBarbero());
                updateUser.setInt(2, getedadBarbero());
                updateUser.setInt(3, getpesoBarbero());
                updateUser.setString(4, getcorreoBarbero());
                updateUser.setString(5, idBarbero);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("Este es el error en el método actualizar" + e);
            }
        } else {
            System.out.println("NO");
        }
    }
    
    public void Mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelobarberos = new DefaultTableModel();
        modelobarberos.setColumnIdentifiers(new Object[]{"idBarbero", "nombreBarbero", "edadBarbero", "pesoBarbero", "correoBarbero"});
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tbBarbero");
            while (rs.next()) {
                modelobarberos.addRow(new Object[]{rs.getString("idBarbero"), 
                    rs.getString("nombreBarbero"), 
                    rs.getInt("edadBarbero"), 
                    rs.getDouble("pesoBarbero"),
                    rs.getString("correoBarbero")
                });
            }
            tabla.setModel(modelobarberos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, método mostrar " + e);
        }
    }
    
    public void limpiar(frmBarbero vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
 
    public void cargarDatosTabla(frmBarbero vista) {
        int filaSeleccionada = vista.jtbBarbero.getSelectedRow();

        if (filaSeleccionada != -1) {
            String ID = vista.jtbBarbero.getValueAt(filaSeleccionada, 0).toString();
            String Nombre = vista.jtbBarbero.getValueAt(filaSeleccionada, 1).toString();
            String Edad = vista.jtbBarbero.getValueAt(filaSeleccionada, 2).toString();
            String Peso= vista.jtbBarbero.getValueAt(filaSeleccionada, 3).toString();
            String Correo= vista.jtbBarbero.getValueAt(filaSeleccionada, 4).toString();

            vista.txtNombre.setText(Nombre);
            vista.txtEdad.setText(Edad);
            vista.txtPeso.setText(Peso);
            vista.txtCorreo.setText(Correo);
        }
    }
}


