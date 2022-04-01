package com.company;

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

    public void Moviment(){

        while (!acabat){
            if (jugador1){

                Scanner leer = new Scanner(System.in);

                int x;

                System.out.println("Turno del jugador 1");
                System.out.println("Indique la columna:");

                x = leer.nextInt();

                for (int i=0;i<tauler.length;i++){

                    if (!jugador2){
                        if (tauler[i][x]=="[x]"| tauler[i][x]=="[O]"){
                            tauler[i-1][x]="[x]";
                            jugador2=true;
                        }else{
                            tauler[i][x]="[x]";
                        }
                    }else {
                        jugador1=false;
                    }
                }
                 DibuixarTauler();

            }
            else{
                Random r = new Random();
                int x =r.nextInt(9);
                tauler[8][x]="[O]";
                DibuixarTauler();
                jugador1=true;
                jugador2=false;
            }
        }
    }
}
