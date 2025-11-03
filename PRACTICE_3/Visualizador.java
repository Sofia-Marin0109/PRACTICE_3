import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Visualizador extends JPanel {
    private String fenPiezas;
    private static final int tamanoCasillas = 60;
    private Map<Character, Image> imagenesPiezas;

    public Visualizador (String fenPiezas) {
        this.fenPiezas = fenPiezas;
        this.imagenesPiezas = new HashMap<>();
        cargarImagenesPiezas();
        setPreferredSize(new Dimension(8 * tamanoCasillas + 50, 8 * tamanoCasillas + 50));
    }
    private void cargarImagenesPiezas() {
        try {
            //Fichas blancas
            imagenesPiezas.put('K', ImageIO.read(new File("resources/piezas/king.png")));
            imagenesPiezas.put('Q', ImageIO.read(new File("resources/piezas/queen.png")));
            imagenesPiezas.put('R', ImageIO.read(new File("resources/piezas/rook.png")));
            imagenesPiezas.put('B', ImageIO.read(new File("resources/piezas/bishop.png")));
            imagenesPiezas.put('N', ImageIO.read(new File("resources/piezas/knight.png")));
            imagenesPiezas.put('P', ImageIO.read(new File("resources/piezas/pawn.png")));

            //Fichas negras
            imagenesPiezas.put('k', ImageIO.read(new File("resources/piezas/king1.png")));
            imagenesPiezas.put('q', ImageIO.read(new File("resources/piezas/queen1.png")));
            imagenesPiezas.put('r', ImageIO.read(new File("resources/piezas/rook1.png")));
            imagenesPiezas.put('b', ImageIO.read(new File("resources/piezas/bishop1.png")));
            imagenesPiezas.put('n', ImageIO.read(new File("resources/piezas/knight1.png")));
            imagenesPiezas.put('p', ImageIO.read(new File("resources/piezas/pawn1.png")));

        } catch (IOException e) {
            System.out.println("Error cargando imágenes: " + e.getMessage());
            System.out.println("Usando símbolos Unicode como respaldo");
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarTablero(g);
        dibujarPiezas(g);
        dibujarCoordenadas(g);
    }
    private void dibujarTablero(Graphics g) {
        Color colorClaro = new Color(240, 217, 181);
        Color colorOscuro = new Color(181, 136, 99);
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                if((fila+columna)%2==0) {
                    g.setColor(colorClaro);
                } else {
                    g.setColor(colorOscuro);
                }
                g.fillRect(
                        columna * tamanoCasillas + 25,  // Posición X
                        fila * tamanoCasillas + 25,     // Posición Y
                        tamanoCasillas,                 // Ancho
                        tamanoCasillas                  // Alto
                );
            }
        }
    }
    private void dibujarPiezas(Graphics g) {
        String[] filas = fenPiezas.split("/");
        for (int fila = 0; fila < 8; fila++) {
            String filaFEN = filas[fila];
            int columna = 0;
            char[] caracteres = filaFEN.toCharArray();
            for (int i = 0; i < caracteres.length; i++) {
                char c = caracteres[i];
                if (Character.isDigit(c)) {
                    int espacios = Character.getNumericValue(c);
                    columna += espacios;
                }
                else {
                    Image imagenPieza = imagenesPiezas.get(c);
                    if (imagenPieza != null) {
                        int x = columna * tamanoCasillas + 25 + 5;
                        int y = fila * tamanoCasillas + 25 + 5;
                        int ancho = tamanoCasillas - 10;
                        int alto = tamanoCasillas - 10;
                        g.drawImage(imagenPieza, x, y, ancho, alto, this);
                    } else {
                        dibujarPiezaUnicode(g, c, fila, columna);
                    }
                    columna++;
                }
            }
        }
    }
    private void dibujarPiezaUnicode(Graphics g, char pieza, int fila, int columna) {
        char simbolo = obtenerSimboloPieza(pieza);
        if (simbolo != ' ') {
            g.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 40));
            int x = columna * tamanoCasillas + 25 + tamanoCasillas/2 - 15;
            int y = fila * tamanoCasillas + 25 + tamanoCasillas/2 + 15;
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(simbolo), x, y);
        }
    }
    private char obtenerSimboloPieza(char pieza) {
        switch (pieza) {
            // Resources.Piezas BLANCAS (mayúsculas)
            case 'K': return '♔'; // Rey blanco - U+2654
            case 'Q': return '♕'; // Reina blanca - U+2655
            case 'R': return '♖'; // Torre blanca - U+2656
            case 'B': return '♗'; // Alfil blanco - U+2657
            case 'N': return '♘'; // Caballo blanco - U+2658
            case 'P': return '♙'; // Peón blanco - U+2659

            // Resources.Piezas NEGRAS (minúsculas)
            case 'k': return '♚'; // Rey negro - U+265A
            case 'q': return '♛'; // Reina negra - U+265B
            case 'r': return '♜'; // Torre negra - U+265C
            case 'b': return '♝'; // Alfil negro - U+265D
            case 'n': return '♞'; // Caballo negro - U+265E
            case 'p': return '♟'; // Peón negro - U+265F
            default: return ' ';  // Espacio vacío
        }
    }

    private void dibujarCoordenadas(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            String letra = String.valueOf((char) ('a' + i));
            int x = i * tamanoCasillas + 25 + tamanoCasillas/2 - 5;
            g.drawString(letra, x, 20);
            g.drawString(letra, x, 8 * tamanoCasillas + 45);
        }
        for (int i = 0; i < 8; i++) {
            String numero = String.valueOf(8 - i);
            int y = i * tamanoCasillas + 25 + tamanoCasillas/2 + 5;
            g.drawString(numero, 10, y); //izq
            g.drawString(numero, 8 * tamanoCasillas + 35, y); // der
        }
    }
    public static void mostrarTablero(String fenPiezas) {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Visualizador tablero = new Visualizador(fenPiezas);
        frame.add(tablero);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
