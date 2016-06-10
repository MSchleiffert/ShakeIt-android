package com.example.menno.shakeit.Models;

import com.example.menno.shakeit.VariableChangeListener;

/**
 * Created by Menno on 9-6-2016.
 */
public class Globals {
    public static boolean loggedin = false;
    public static boolean shaked = false;
    public static int userId = 0;
    VariableChangeListener variableChangeListener;

    private static Globals global = new Globals( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private Globals(){ }

    /* Static 'instance' method */
    public static Globals getInstance( ) {
        return global;
    }

    public void setVariableChangeListener(VariableChangeListener variableChangeListener) {
        this.variableChangeListener = variableChangeListener;
    }

    public void notifyListener(){
        variableChangeListener.onVariableChanged(userId);
    }


}
