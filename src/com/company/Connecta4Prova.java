package com.company;

import java.util.Scanner;

public class Connecta4Prova {

    private int rows=9;
    private int cols=9;
    private String[][] tauler=new String[rows][cols];
    private boolean jugador2=false;
    private boolean acabat=false;
    private String guanyador;
    private String[] nombres;
    private String[] simbolos;
    private int turno;
    boolean guanyar=false;
    Scanner leer = new Scanner(System.in);

    public void initConnecta4(){
        simbolos= new String[]{"["+"\033[31m"+"x"+"\u001B[0m"+"]","["+"\033[33m"+"O"+"\u001B[0m"+"]"};
        nombres= new String[]{"Jugador 1","Jugador 2"};

        System.out.println("\033[35m"+"Modo de juego"+"\u001B[0m");
        System.out.println("\033[34m"+"1.PVP"+"\u001B[0m");
        System.out.println("\033[31m"+"2.PVC"+"\u001B[0m");

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
                if (tauler[i][j] == caracter && tauler[i][j + 1] == caracter && tauler[i][j + 2] == caracter && tauler[i][j + 3] == caracter) {
                    guanyar=true;
                    guanyador = jugador;
                    acabat = true;
                    break;
                }
            }
        }

        for (int i = 0; i < rows -3; i++) {
            for (int j = 0; j < cols; j++) {
                if (tauler[i][j] == caracter && tauler[i + 1][j] == caracter && tauler[i + 2][j] == caracter && tauler[i + 3][j] == caracter) {
                    guanyar=true;
                    guanyador = jugador;
                    acabat = true;
                    break;
                }
            }
        }

        for (int i = 0; i < rows -3; i++) {
            for (int j = 0; j < cols -3; j++) {
                if (tauler[i][j] == caracter && tauler[i + 1][j + 1] == caracter && tauler[i + 2][j + 2] == caracter && tauler[i + 3][j + 3] == caracter) {
                   guanyar=true;
                    guanyador = jugador;
                    acabat = true;
                    break;
                }
            }
        }

        for (int i = rows-1; i > 3; i--) {
            for (int j = 0; j < cols -3; j++) {
                if (tauler[i][j] == caracter && tauler[i - 1][j + 1] == caracter && tauler[i - 2][j + 2] == caracter && tauler[i - 3][j + 3] == caracter) {
                    guanyar=true;
                    guanyador = jugador;
                    acabat = true;
                    break;
                }
            }
        }
    }

    public void Jugador1(String caracter, String jugador){

        int x;

        DibuixarTauler();
        while (!jugador2){

            System.out.println("Turno de "+jugador);
            System.out.println("Indique la columna:");

            x = leer.nextInt();
            x--;
            if (x>=0 && x<cols){


                for (int i=rows-1;i>=0;i--){

                    if (!jugador2){

                        if (tauler[i][x] != simbolos[turno%2] && tauler[i][x] != simbolos[1-(turno%2)]){

                            if (i>0){
                                tauler[i][x] = caracter;
                                turno++;
                                jugador2 = true;
                            }else {
                                System.out.println("\033[31m"+"Error, esta columna ya esta ocupada"+"\u001B[0m");
                                DibuixarTauler();
                            }
                        }
                    }
                }
            }else{
                System.out.println("\033[31m"+"Error, seleccione una columna válida"+"\u001B[0m");
            }
        }
        jugador2=false;
        if (!guanyar){
            ComprovarGuanyador(caracter,jugador);
        }

    }

    public void Bot(String caracter, String jugador){

        int x;

        while (!jugador2){

            x = (int) (Math.random() * cols);
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

        }jugador2=false;
        if (!guanyar){
            ComprovarGuanyador(caracter,jugador);
        }

    }

    public void Moviment(){

        int x;

        x = leer.nextInt();

        if (x==1){
            while (!acabat){

                Jugador1(simbolos[turno%2],nombres[turno%2]);
                Jugador1(simbolos[turno%2],nombres[turno%2]);
            }
            DibuixarTauler();
            System.out.println("\033[32m"+"El "+guanyador+" ha ganado"+"\u001B[0m");
        }else if(x==2){

            while (!acabat){

                Jugador1(simbolos[turno%2],nombres[turno%2]);
                Bot(simbolos[turno%2],nombres[turno%2]);
            }
            DibuixarTauler();
            System.out.println("\033[32m"+"El "+guanyador+" ha ganado"+"\u001B[0m");
        }else {
            System.out.println("\033[31m"+"Error, seleccione una opción válida"+"\u001B[0m");
            initConnecta4();
        }
    }
}



