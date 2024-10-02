package Controlador;

import Modelo.Barbero;
import Vista.frmBarbero;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
/**
 *
 * @author Luzma
 */
public class ctrlBarbero {
   
    //1- Llamar a las otras capas(modelo, vista)
    private frmBarbero vista;
    private Barbero modelo;
    
      public ctrlBarbero(frmBarbero vista, Barbero modelo) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.jtbBarbero.addMouseListener(this);

        modelo.Mostrar(vista.jtbBarbero);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    modelo.setnombreBarbero(vista.txtNombre.getText());
                    modelo.setedadBarbero(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setpesoBarbero(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setcorreoBarbero(vista.txtCorreo.getText());

                    modelo.Guardar();
                    modelo.Mostrar(vista.jtbBarbero);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número entero", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para poder eliminarlo", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.jtbBarbero);
                modelo.Mostrar(vista.jtbBarbero);
                modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para poder actualizarlo", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                
                    modelo.setnombreBarbero(vista.txtNombre.getText());
                    modelo.setedadBarbero(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setpesoBarbero(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setcorreoBarbero(vista.txtCorreo.getText());

                    modelo.Actualizar(vista.jtbBarbero);
                    modelo.Mostrar(vista.jtbBarbero);
                    modelo.limpiar(vista);
                
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.jtbBarbero) {
            modelo.cargarDatosTabla(vista);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
   
