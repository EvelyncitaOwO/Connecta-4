package com.company;

import java.util.Scanner;

public class Connecta4Prova {

    private int rows=9;
    private int cols=9;
    private String[][] tauler=new String[rows][cols];
    private boolean jugador1=true;
    private boolean jugador2=false;
    private boolean acabat=false;
    private String guanyador;

    public void initConnecta4(){
        CrearTauler();
        DibuixarTauler();
        Moviment();
    }

    public void CrearTauler(){

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tauler[i][j] = "[ ]";
            }
        }
    }

    public void DibuixarTauler(){

        for (int i=1;i<cols+1;i++){

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

    public void ComprovarGuanyador(String caracter){

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols -3; j++) {
                if (tauler[i][j]==caracter && tauler[i][j+1]==caracter && tauler[i][j+2]==caracter && tauler[i][j+3]==caracter){
                    if (caracter == "[x]"){
                        guanyador="Jugador 1";
                    }else{
                        guanyador="Bot";
                    }acabat=true;
                }
            }
        }

        for (int i = 0; i < rows -3; i++) {
            for (int j = 0; j < cols; j++) {
                if (tauler[i][j]==caracter && tauler[i+1][j]==caracter && tauler[i+2][j]==caracter && tauler[i+3][j]==caracter){
                    if (caracter == "[x]"){
                        guanyador="Jugador 1";
                    }else{
                        guanyador="Bot";
                    }acabat=true;
                }
            }
        }
    }

    public void Jugador1(){

        boolean check=true;
        int x;
        while (!jugador2){

            Scanner leer = new Scanner(System.in);

            System.out.println("Turno del jugador 1");
            System.out.println("Indique la columna:");

            x = leer.nextInt();
            x--;
            if (x>=0 && x<cols){


                for (int i=rows-1;i>0;i--){

                    if (!jugador2) {

                        if (tauler[i][x] != "[x]" && tauler[i][x] != "[O]") {
                            tauler[i][x] = "[x]";
                            jugador2 = true;
                        }else {
                            if (check){
                                check=false;
                                System.out.println("Error, esta columna ya esta ocupada");
                            }
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
            ComprovarGuanyador("[x]");
            ComprovarGuanyador("[O]");
        }
        System.out.println("El "+guanyador+" ha ganado");
    }
}



