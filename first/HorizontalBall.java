import javax.swing.*;
import java.awt.*;

public class HorizontalBall extends JPanel {

    private int x = 0; // Eje horizontal
    private int y = 100; // Eje vertical
    private final int diameter = 50; // Diametro
    private int dx = 5; // Velocidad horizontal

    private Timer timer;

    public HorizontalBall() {
        setPreferredSize(new Dimension(600, 300));
        setBackground(Color.WHITE);

        // Temporizador para animación
        timer = new Timer(20, e -> {
            x += dx;

            if (x < 0 || x + diameter > getWidth()) {
                dx *= -1;
            }

            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }

    // Métodos para controlar la animación
    public void start() {
        x = 0; // Reinicia posición
        dx = 25; // Dirección inicial
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void resume() {

        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pelota");
        HorizontalBall ballPanel = new HorizontalBall();

        // Panel de botones
        JPanel buttonsPanel = new JPanel();
        JButton btnStart = new JButton("Iniciar");
        JButton btnStop = new JButton("Parar");
        JButton btnResume = new JButton("Continuar");

        // Acciones
        btnStart.addActionListener(e -> ballPanel.start());
        btnStop.addActionListener(e -> ballPanel.stop());
        btnResume.addActionListener(e -> ballPanel.resume());

        buttonsPanel.add(btnStart);
        buttonsPanel.add(btnStop);
        buttonsPanel.add(btnResume);

        // Layout general
        frame.setLayout(new BorderLayout());
        frame.add(ballPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    };

}