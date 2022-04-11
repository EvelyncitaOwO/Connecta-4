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
    private String[] nombres;
    private String[] simbolos;
    private int turno;

    public void initConnecta4(){
        simbolos= new String[]{"["+"\033[31m"+"x"+"\u001B[0m"+"]","["+"\033[33m"+"O"+"\u001B[0m"+"]"};
        nombres= new String[]{"Jugador 1","Jugador 2"};

        CrearTauler();
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

    public void ComprovarGuanyador(String caracter,String jugador){

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols -3; j++) {
                if (tauler[i][j]==caracter && tauler[i][j+1]==caracter && tauler[i][j+2]==caracter && tauler[i][j+3]==caracter){
                    guanyador=jugador;
                    acabat=true;
                }
            }
        }

        for (int i = 0; i < rows -3; i++) {
            for (int j = 0; j < cols; j++) {
                if (tauler[i][j]==caracter && tauler[i+1][j]==caracter && tauler[i+2][j]==caracter && tauler[i+3][j]==caracter){
                    guanyador=jugador;
                    acabat=true;
                }
            }
        }

        for (int i = 0; i < rows -3; i++) {
            for (int j = 0; j < cols -3; j++) {
                if (tauler[i][j]==caracter && tauler[i+1][j+1]==caracter && tauler[i+2][j+2]==caracter && tauler[i+3][j+3]==caracter){
                    guanyador=jugador;
                    acabat=true;
                }
            }
        }

        for (int i = rows-1; i > 3; i--) {
            for (int j = 0; j < cols -3; j++) {
                if (tauler[i][j]==caracter && tauler[i-1][j+1]==caracter && tauler[i-2][j+2]==caracter && tauler[i-3][j+3]==caracter){
                    guanyador=jugador;
                    acabat=true;
                }
            }
        }
    }

    public void Jugador1(String caracter, String jugador){

        boolean check=true;
        int x;

        DibuixarTauler();
        while (!jugador2){

            Scanner leer = new Scanner(System.in);

            System.out.println("Turno de "+jugador);
            System.out.println("Indique la columna:");

            x = leer.nextInt();
            x--;
            if (x>=0 && x<cols){


                for (int i=rows-1;i>0;i--){

                    if (!jugador2) {

                        if (tauler[i][x] != simbolos[turno%2] && tauler[i][x] != simbolos[1-(turno%2)]) {
                            tauler[i][x] = caracter;
                            turno++;
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
        }
        jugador2=false;
        ComprovarGuanyador(caracter,jugador);
    }

    public void Bot(String caracter, String jugador){

        int x=0;
        boolean random=false;

        while (!jugador2){

            for (int i = 0; i < rows ; i++) {
                for (int j = 0; j < cols -3; j++) {
                    if (tauler[i][j]==simbolos[turno%2] && tauler[i][j+1]==simbolos[turno%2] && tauler[i][j+2]==simbolos[turno%2] && tauler[i][j+3]!=simbolos[turno%2] && tauler[i][j+3]!=simbolos[1-(turno%2)]){
                        x=j+3;
                        break;
                    }else{
                        x = (int) (Math.random() * cols);
                        random=true;
                    }
                }
            }
            for (int i = 0; i < rows ; i++) {
                for (int j = 3; j < cols; j++) {
                    if (tauler[i][j]==simbolos[turno%2] && tauler[i][j-1]==simbolos[turno%2] && tauler[i][j-2]==simbolos[turno%2] && tauler[i][j-3]!=simbolos[turno%2] && tauler[i][j-3]!=simbolos[1-(turno%2)]){
                        x=j-3;
                        break;
                    }else{
                        x = (int) (Math.random() * cols);
                        random=true;
                    }
                }
            }
            if (random){
                System.out.println(x);
                for (int i=tauler.length-1;i>0;i--){

                    if (!jugador2){
                        if (tauler[i][x]!=simbolos[turno%2] && tauler[i][x]!=simbolos[1-(turno%2)]){
                            tauler[i][x]=caracter;
                            turno++;
                            jugador2=true;
                        }
                    }
                }
            }

        }jugador2=false;
        ComprovarGuanyador(caracter,jugador);
    }

    public void Moviment(){

        while (!acabat){

            Jugador1(simbolos[turno%2],nombres[turno%2]);
            Bot(simbolos[turno%2],nombres[turno%2]);
        }
        DibuixarTauler();
        System.out.println("El "+guanyador+" ha ganado");
    }
}



