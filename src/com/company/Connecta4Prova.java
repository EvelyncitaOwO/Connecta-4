package com.company;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Random;
import java.util.Scanner;

public class Connecta4Prova {

    private int cols=9;
    private int rows=9;
    private String[][] tauler=new String[cols][rows];
    private int caselles=10;
    private boolean jugador1=true;
    private boolean jugador2=false;
    private boolean acabat=false;

    public void initConnecta4(){
        CrearTauler();
        DibuixarTauler();
        Moviment();
    }

    public void CrearTauler(){

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                tauler[i][j] = "[ ]";
            }
        }
    }

    public void DibuixarTauler(){

        for (int i=1;i<caselles;i++){

            System.out.print("|"+i+"| ");

        }
        System.out.println();

        for (int i = 1; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                System.out.print(tauler[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void Jugador1(){

        int x;
        while (!jugador2){
            Scanner leer = new Scanner(System.in);

            System.out.println("Turno del jugador 1");
            System.out.println("Indique la columna:");

            x = leer.nextInt();

            if (x>0 && x<9){
                x--;

                for (int i=tauler.length-1;i>0;i--){

                    if (!jugador2){

                        if (tauler[i][x]!="[x]" && tauler[i][x]!="[O]"){
                            tauler[i][x]="[x]";
                            jugador2=true;
                        }
                    }
                }
            }else{
                System.out.println("Error, seleccione una columna válida");
            }
        }jugador2=false;
    }

    public void Bot(){

        while (!jugador2){

            int x = (int) (Math.random() * cols);

            for (int i=tauler.length-1;i>0;i--){

                if (!jugador2){
                    if (tauler[i][x]!="[x]" && tauler[i][x]!="[O]"){
                        tauler[i][x]="[O]";
                        jugador2=true;
                    }
                }
            }
        }jugador2=false;
    }

    public void Moviment(){

        while (!acabat){

            Jugador1();
            Bot();
            DibuixarTauler();
        }
    }
}
