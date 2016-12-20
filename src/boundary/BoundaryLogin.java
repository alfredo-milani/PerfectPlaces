package boundary;

import control.ControlloreLogin;
import exception.DeserializzazioneException;

/**
 * Created by alfredo on 18/12/16.
 */
public class BoundaryLogin {

    private ControlloreLogin cL;

    public BoundaryLogin() {
        cL = new ControlloreLogin();
    }

    public void login(String username, String password)
    throws DeserializzazioneException{
        cL.login(username, password);
    }

    public boolean controlloAccesso() {
        return cL.getLogged();
    }

    public void logout() {
        cL.logout();
    }

    public String ritornaUsername() {
        return cL.getUser();
    }

    public String ritornaPassword() {
        return cL.getPsw();
    }
}