package practica.practicalaberint;

/**
 * Created by Ramon Mas on 10/3/16.
 * Classe activitat general per si fa falta guardar elements comuns a totes les activitats
 */

import android.app.Activity;
import android.graphics.Typeface;


public class GeneralActivity extends Activity {
    protected Typeface fontJoc;

    public void init()
    {
        fontJoc = Typeface.createFromAsset(this.getAssets(), "fonts/arkitechbold.ttf");
    }
}
