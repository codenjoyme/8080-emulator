package spec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends JFrame implements KeyListener {

    private Application app;

    public static void main(String[] args) {
        String base = (args.length == 1) ? args[0] : null;
        new Main(base);
    }

    public Main(String base) {
        super("i8080 emulator");
        setMinimumSize(Graphic.getMinimumSize(15, 40));
        setVisible(true);
        setFocusable(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowFocusListener(focusListener());
        setFocusTraversalKeysEnabled(true);
        addKeyListener(this);

        app = new Application(this, getBaseUrl(base));
        scenario();
        app.gotFocus();
        app.start();
    }

    private void scenario() {
        app.record()
                .after(55).up(0x13)
                .after(90).down(0x23)
                .after(76).up(0x23)
                .after(189).down(0x0A)
                .after(61).up(0x0A)
                .after(399).down(0x4A)
                .after(57).up(0x4A)
                .after(189).down(0x0A)
                .after(68).up(0x0A)
                .after(4065).down(0x27)
                .after(541).up(0x27)
                .after(198).down(0x27)
                .after(57).up(0x27)
                .after(141).down(0x26)
                .after(256).up(0x26)
                .after(1377).down(0x27)
                .after(368).up(0x27)
                .after(197).down(0x27)
                .after(28).up(0x27)
                .after(56).down(0x26)
                .after(283).up(0x26)
                .after(56).down(0x27)
                .after(340).up(0x27)
                .after(56).down(0x26)
                .after(368).up(0x26)
                .after(56).down(0x27)
                .after(158).up(0x27)
                .after(17).down(0x25)
                .after(539).up(0x25)
                .after(1).down(0x26)
                .after(198).up(0x26)
                .after(35).down(0x27)
                .after(926).up(0x27)
                .after(272).down(0x25)
                .after(193).up(0x25)
                .after(38).down(0x27)
                .after(1011).up(0x27)
                .after(42).down(0x25)
                .after(226).up(0x25)
                .after(197).down(0x25)
                .after(56).up(0x25)
                .after(84).down(0x26)
                .after(197).up(0x26)
                .after(84).down(0x26)
                .after(28).up(0x26)
                .after(1).down(0x25)
                .after(28).up(0x25)
                .after(28).down(0x26)
                .after(198).up(0x26)
                .after(56).down(0x27)
                .after(166).up(0x27)
                .after(31).down(0x26)
                .after(141).up(0x26)
                .after(52).down(0x27)
                .after(92).up(0x27)
                .after(86).down(0x25)
                .after(56).up(0x25)
                .after(70).down(0x26)
                .after(180).up(0x26)
                .after(59).down(0x25)
                .after(28).up(0x25)
                .after(28).down(0x26)
                .after(67).up(0x26)
                .after(29).down(0x26)
                .after(158).up(0x26)
                .after(84).down(0x27)
                .after(28).up(0x27)
                .after(28).down(0x26)
                .after(255).up(0x26)
                .after(56).down(0x25)
                .after(56).up(0x25)
                .after(254).down(0x27)
                .after(85).up(0x27)
                .after(28).down(0x26)
                .after(169).up(0x26)
                .after(84).down(0x25)
                .after(92).up(0x25)
                .after(21).down(0x26)
                .after(144).up(0x26)
                .after(56).down(0x25)
                .after(24).up(0x25)
                .after(1).down(0x26)
                .after(198).up(0x26)
                .after(56).down(0x27)
                .after(56).up(0x27)
                .after(28).down(0x26)
                .after(283).up(0x26)
                .after(56).down(0x25)
                .after(113).up(0x25)
                .after(254).down(0x27)
                .after(56).up(0x27)
                .after(1).down(0x26)
                .after(312).up(0x26)
                .after(84).down(0x25)
                .after(283).down(0x26)
                .after(1).up(0x25)
                .after(113).up(0x26)
                .after(28).down(0x25)
                .after(166).up(0x25)
                .after(1).down(0x26)
                .after(173).up(0x26)
                .after(56).down(0x27)
                .after(480).down(0x26)
                .after(3).up(0x27)
                .after(394).up(0x26)
                .after(360).down(0x27)
                .after(282).down(0x26)
                .after(1).up(0x27)
                .after(262).up(0x26)
                .after(1).down(0x25)
                .after(140).up(0x25)
                .after(28).down(0x27)
                .after(312).down(0x26)
                .after(1).up(0x27)
                .after(312).up(0x26)
                .after(1).down(0x27)
                .after(248).down(0x26)
                .after(6).up(0x27)
                .after(295).up(0x26)
                .after(1).down(0x27)
                .after(76).up(0x27)
                .after(28).down(0x25)
                .after(56).up(0x25)
                .after(112).down(0x28)
                .after(170).up(0x28)
                .after(1).down(0x27)
                .after(226).up(0x27)
                .after(56).down(0x27)
                .after(356).down(0x26)
                .after(17).up(0x27)
                .after(340).up(0x26)
                .after(84).down(0x27)
                .after(748).up(0x27)
                .after(976).down(0x25)
                .after(85).up(0x25)
                .after(84).down(0x27)
                .after(63).up(0x27)
                .after(49).down(0x26)
                .after(169).up(0x26)
                .after(21).down(0x27)
                .after(81).down(0x26)
                .after(10).up(0x27)
                .after(226).up(0x26);
    }

    private URL getBaseUrl(String base) {
        try {
            return base == null
                    ? contentRoot().toURI().toURL()
                    : new URL(base);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File contentRoot() {
        File result = new File("src/main/resources");
        if (!result.exists()) {
            result = new File(".");
        }
        return result;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (app != null) {
            app.handleKey(new Key(event, true));
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (app != null) {
            app.handleKey(new Key(event, false));
        }
    }

    private WindowFocusListener focusListener() {
        return new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent event) {
                if (app != null) {
                    app.gotFocus();
                }
            }

            @Override
            public void windowLostFocus(WindowEvent event) {
                if (app != null) {
                    app.lostFocus();
                }
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        if (app != null) {
            app.repaint();
        }
    }
}