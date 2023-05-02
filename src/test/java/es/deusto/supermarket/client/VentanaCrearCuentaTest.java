package es.deusto.supermarket.client;
import org.junit.Test;

import es.deusto.spq.supermarket.client.VentanaCrearCuenta;

import static org.mockito.Mockito.*;

import javax.swing.JTextField;

import static org.junit.Assert.*;

public class VentanaCrearCuentaTest {

    @Test
    public void testReturcodigo() {
        int expected = 12345;
        VentanaCrearCuenta.codigoverificacion = expected;
        int actual = VentanaCrearCuenta.returcodigo();
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnnombre() {
        String expected = "Juan";
        VentanaCrearCuenta.txtUsername = mock(JTextField.class);
        when(VentanaCrearCuenta.txtUsername.getText()).thenReturn(expected);
        String actual = VentanaCrearCuenta.returnnombre();
        assertEquals(expected, actual);
    }

    @Test
    public void testReturnmail() {
        String expected = "juan@example.com";
        VentanaCrearCuenta.txtEmail = mock(JTextField.class);
        when(VentanaCrearCuenta.txtEmail.getText()).thenReturn(expected);
        String actual = VentanaCrearCuenta.returnmail();
        assertEquals(expected, actual);
    }

    @Test
    public void testReturncontra() {
        String expected = "password123";
        VentanaCrearCuenta.txtContraseña = mock(JTextField.class);
        when(VentanaCrearCuenta.txtContraseña.getText()).thenReturn(expected);
        String actual = VentanaCrearCuenta.returncontra();
        assertEquals(expected, actual);
    }

    @Test
    public void testReturntrabajador() {
        int expected = 0;
        int actual = VentanaCrearCuenta.returntrabajador();
        assertEquals(expected, actual);
    }

    @Test
    public void testReturngerente() {
        int expected = 0;
        int actual = VentanaCrearCuenta.returngerente();
        assertEquals(expected, actual);
    }
}
