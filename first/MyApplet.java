import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class MyApplet extends Applet implements ActionListener {
    Image myImage;
    String name = "Johan Gonzalez";

    // Se insertan los sonidos
    AudioClip sound1;
    AudioClip sound2;

    // Variables para los botones
    Button btnOneTime1, btnOneTime2, btnLoop1, btnLoop2, btnStop;

    public void init() {
        // Layout principal
        setLayout(new BorderLayout());

        // Se crea la Imagen
        myImage = getImage(getDocumentBase(), "yo.png");

        // Cargamos los botones con la imagen
        sound1 = getAudioClip(getDocumentBase(), "sound1.wav");
        sound2 = getAudioClip(getDocumentBase(), "sound2.wav");

        // Se crean los Botones
        btnOneTime1 = new Button("Reproducir una vez el sonido # 1");
        btnOneTime2 = new Button("Reproducir una vez el sonido # 2");
        btnLoop1 = new Button("Reproducir indefinidamente el sonido # 1");
        btnLoop2 = new Button("Reproducir indefinidamente el sonido # 2");
        btnStop = new Button("Parar todos los sonidos");

        // Este es el panel para los botones con el layout en columna
        Panel panelButtons = new Panel(new GridLayout(5, 1, 5, 5));
        panelButtons.add(btnOneTime1);
        panelButtons.add(btnOneTime2);
        panelButtons.add(btnLoop1);
        panelButtons.add(btnLoop2);
        panelButtons.add(btnStop);

        // Se agrega el panel de los botones en la parta de abajo
        add(panelButtons, BorderLayout.SOUTH);

        // Y estos son los Listeners :D
        btnOneTime1.addActionListener(this);
        btnOneTime2.addActionListener(this);
        btnLoop1.addActionListener(this);
        btnLoop2.addActionListener(this);
        btnStop.addActionListener(this);
    }

    public void paint(Graphics g) {
        g.drawImage(myImage, 0, 0, 560, 400, this);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(name, getWidth() / 2 - 80, getHeight() / 2);
    }

    // Función para reproducir los sonidos
    public void actionPerformed(ActionEvent e) {
        Object fount = e.getSource();

        if (fount == btnOneTime1) {
            sound1.play();
        } else if (fount == btnOneTime2) {
            sound2.play();
        } else if (fount == btnLoop1) {
            sound1.loop();
        } else if (fount == btnLoop2) {
            sound2.loop();
        } else if (fount == btnStop) {
            sound1.stop();
            sound2.stop();
        }
    }

};