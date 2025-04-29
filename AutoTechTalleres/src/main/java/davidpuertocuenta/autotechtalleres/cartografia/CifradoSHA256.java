/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidpuertocuenta.autotechtalleres.cartografia;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author David Puerto Cuenca
 */
public class CifradoSHA256 {
    
    public static String cifrarContraseña(String password, String randomizador) {
        try {
            MessageDigest cifrado = MessageDigest.getInstance("SHA-256");
                byte[] hash = cifrado.digest((password + randomizador).getBytes(StandardCharsets.UTF_8));
                    return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    
    public static boolean verificarContraseña(String password, String randomizador, String hashUsuario) {
        String hashComprobacion = cifrarContraseña(password, randomizador);
            return hashComprobacion.equals(hashUsuario);
    }
    
    public static String generarRandomizador() {
        byte[] randomizador = new byte[16];
            new SecureRandom().nextBytes(randomizador);
                return Base64.getEncoder().encodeToString(randomizador);
    }
    
}
