package ual.hmis.sesion06;

public class Ejercicio3 {
    public String enmascarar(String password)
    {
        String enmascarar = "";
        if (password.length() < 5) {
             enmascarar += "password demasiado corto";
        } else if (password.length() <= 8) {
             enmascarar += "********";
        } else if(password.length() >= 12 && password.length() <= 40) {
             enmascarar += "************";
        } else if(password.length() > 40) {
             enmascarar += "password demasiado largo";
        } else {
             enmascarar += "*".repeat(password.length());
        }
        return enmascarar;
    }
}
