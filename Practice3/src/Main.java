import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int p = 0, p2 = 0;
    static int q = 0, q2 = 0;
    static int r = 0, r2 = 0;
    static int k = 0, k2 = 0;
    static int n = 0, n2 = 0;
    static int b = 0, b2 = 0;
    static int P = 0, P2 = 0;
    static int Q = 0, Q2 = 0;
    static int R = 0, R2 = 0;
    static int K = 0, K2 = 0;
    static int N = 0, N2 = 0;
    static int B = 0, B2 = 0;

    public static void verificarSintaxis(String fen) {
        if (fen.length() > 8) {
            System.out.println("La longitud de la FEN no puede ser mayor a 8 caracteres");
        }
        for(char c : fen.toCharArray()){
            if (Character.isDigit(c)) {
                if (c > '8' || c < '1') {
                    System.out.println("el número " + c + " no es valido, solo se admiten números del 1 al 8");
                }
            }
            if(Character.isLetter(c)){
                if(!((c == 'r') || (c == 'n') || (c == 'b') || (c == 'q') || (c == 'k') || (c == 'p') || (c == 'R') || (c == 'N') || (c == 'B') || (c == 'Q') || (c == 'K') || (c == 'P'))){
                    String Error = "Ingresaste la letra " + c + ", recuerde que solo se aceptan las letras rR,nN,bB,qQ,Kk,Pp";
                    System.out.println(Error);
                }
            }
        }
    }
    public static int verificarFilaFEN(String fila) {
        int casillas = 0;
        for (char c : fila.toCharArray()) {
            if (Character.isDigit(c)) {
                casillas += Character.getNumericValue(c);
            } else {
                casillas++;
            }
        }
        return casillas;
    }

    public static boolean cantidadDeFichas(String fen) {
        //itere toda la cadena fen y que vaya contando cuanto hay de cada letra para luego comparar con el contador, si es mayor, que devuelva a la cantidad a la que está el contador y la fila se anula
        for(char c : fen.toCharArray()) {
            if (Character.isLetter(c)) {
                switch (c) {
                    case 'r': {
                        r++;
                        break;
                    }
                    case 'p': {
                        p++;
                        break;
                    }
                    case 'k': {
                        k++;
                        break;
                    }
                    case 'n': {
                        n++;
                        break;
                    }
                    case 'q': {
                        q++;
                        break;
                    }
                    case 'b': {
                        b++;
                        break;
                    }
                    case 'R': {
                        R++;
                        break;
                    }
                    case 'P': {
                        P++;
                        break;
                    }
                    case 'K': {
                        K++;
                        break;
                    }
                    case 'N': {
                        N++;
                        break;
                    }
                    case 'Q': {
                        Q++;
                        break;
                    }
                    case 'B': {
                        B++;
                        break;
                    }
                }
            }
        }
        boolean correcto = true;
        int i = 0;
        while(correcto && i<1) {
            if ((r + r2) > 2) {
                System.out.println("No pueden haber más de 2 torres negras");
                correcto = false;
                break;
            } else {
                r2 = r + r2;
            }
            if ((p + p2) > 8) {
                System.out.println("No hay más de 8 peones negros");
                correcto = false;
                break;
            } else {
                p2 = p + p2;
            }
            if ((k + k2) > 1) {
                System.out.println("No hay más de 1 rey negro");
                correcto = false;
                break;
            } else {
                k2 = k + k2;
            }
            if ((n + n2) > 2) {
                System.out.println("No hay más de 2 caballos negros");
                correcto = false;
                break;
            } else {
                n2 = n + n2;
            }
            if ((q + q2) > 1) {
                System.out.println("No hay más de 1 reina negra");
                correcto = false;
                break;
            } else {
                q2 = q + q2;
            }
            if ((b + b2) > 2) {
                System.out.println("No hay más de 2 alfiles negros");
                correcto = false;
                break;
            } else {
                b2 = b + b2;
            }
            if ((R + R2) > 2) {
                System.out.println("no hay más de 2 torres blancas");
                correcto = false;
                break;
            } else {
                R2 = R + R2;
            }
            if ((P + P2) > 8) {
                System.out.println("No hay más de 8 peones blancos");
                correcto = false;
                break;
            } else {
                P2 = P + P2;
            }
            if ((K + K2) > 1) {
                System.out.println("No hay más de 1 rey blanco");
                correcto = false;
                break;
            } else {
                K2 = K + K2;
            }
            if ((N + N2) > 2) {
                System.out.println("No hay más de 2 caballos blancos");
                correcto = false;
                break;
            } else {
                N2 = N + N2;
            }
            if ((Q + Q2) > 1) {
                System.out.println("No hay más de 1 reina blanca");
                correcto = false;
                break;
            } else {
                Q2 = Q + Q2;
            }
            if ((B + B2) > 2) {
                System.out.println("No hay más de 2 alfiles blancos");
                correcto = false;
                break;
            } else {
                B2 = B + B2;
            }
            i++;
        }
        r=0;
        p=0;
        q=0;
        b=0;
        n=0;
        k=0;
        R=0;
        P=0;
        Q=0;
        B=0;
        N=0;
        K=0;
        return correcto;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tablero = new ArrayList<>();
        int centinela = 1;
        String pattern = "^[rnbqkpRNBQKP1-8]{1,8}$";
        System.out.println("Bienvenido a chess");
        while (centinela != 9) {
            System.out.println("Ingrese FEN de la fila " + centinela);
            String fen = sc.nextLine();
            boolean ok = fen.matches(pattern);
            if (ok) {
                if (verificarFilaFEN(fen)<=8 && cantidadDeFichas(fen)){
                    tablero.add(fen);
                    centinela++;
                }else{
                    int max = verificarFilaFEN(fen)-8;
                    if(max !=0 ) {
                        System.out.println("La fila " + centinela + " tiene " + max + " casillas de más");
                    }
                }
            }else {
                verificarSintaxis(fen);
            }
        }
        System.out.println("tablero completado");
        String cadenaFen = String.join("/", tablero);
        Visualizador.mostrarTablero(cadenaFen);
    }
}