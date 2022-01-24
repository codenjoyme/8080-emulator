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
        // scenario();
        app.gotFocus();
        app.start();
    }

    private void scenario() {
        KeyRecord.Action action = app.record().after(51530).up(0x13);
        action = action.after(64000).down(0x23);
        action = action.after(53882).up(0x23);
        action = action.after(106043).down(0x0A);
        action = action.after(83609).up(0x0A);
        action = action.after(909375).down(0x4A);
        action = action.after(57340).up(0x4A);
        action = action.after(219376).down(0x0A);
        action = action.after(100936).up(0x0A);
        action = action.after(4030324).down(0x27);
        action = action.after(598484).up(0x27);
        action = action.after(298236).down(0x25);
        action = action.after(71244).up(0x25);
        action = action.after(56789).down(0x27);
        action = action.after(85655).up(0x27);
        action = action.after(287505).down(0x25);
        action = action.after(25137).up(0x25);
        action = action.after(340474).down(0x26);
        action = action.after(303061).up(0x26);
        action = action.after(1302998).down(0x27);
        action = action.after(453594).down(0x26);
        action = action.after(1).up(0x27);
        action = action.after(304947).up(0x26);
        action = action.after(3491).down(0x27);
        action = action.after(372428).down(0x26);
        action = action.after(7069).up(0x27);
        action = action.after(134976).up(0x26);
        action = action.after(281763).down(0x20);
        action = action.after(31657).down(0x27);
        action = action.after(467632).up(0x20);
        action = action.after(1).up(0x27);
        action = action.after(1).down(0x25);
        action = action.after(399513).down(0x26);
        action = action.after(35424).up(0x25);
        action = action.after(191376).down(0x25);
        action = action.after(21484).up(0x26);
        action = action.after(375524).down(0x26);
        action = action.after(28209).up(0x25);
        action = action.after(113608).up(0x26);
        action = action.after(10658).down(0x27);
        action = action.after(159352).up(0x27);
        action = action.after(170339).down(0x25);
        action = action.after(270194).up(0x25);
        action = action.after(28235).down(0x27);
        action = action.after(1).down(0x20);
        action = action.after(783190).up(0x20);
        action = action.after(30260).up(0x27);
        action = action.after(84667).down(0x27);
        action = action.after(141869).up(0x27);
        action = action.after(1).down(0x25);
        action = action.after(256388).up(0x25);
        action = action.after(920).down(0x27);
        action = action.after(287809).up(0x27);
        action = action.after(317478).down(0x27);
        action = action.after(1497558).down(0x26);
        action = action.after(28182).up(0x27);
        action = action.after(290778).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(407818).down(0x26);
        action = action.after(10673).up(0x27);
        action = action.after(226198).up(0x26);
        action = action.after(3491).down(0x25);
        action = action.after(53092).up(0x25);
        action = action.after(1).down(0x26);
        action = action.after(397007).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(147441).up(0x27);
        action = action.after(1).down(0x25);
        action = action.after(1257056).down(0x27);
        action = action.after(24692).up(0x25);
        action = action.after(453703).up(0x27);
        action = action.after(35289).down(0x26);
        action = action.after(276608).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(397162).down(0x26);
        action = action.after(28214).up(0x27);
        action = action.after(255478).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(198645).down(0x20);
        action = action.after(568468).up(0x20);
        action = action.after(881641).down(0x20);
        action = action.after(34601).down(0x25);
        action = action.after(1).up(0x27);
        action = action.after(255033).up(0x20);
        action = action.after(342438).up(0x25);
        action = action.after(1).down(0x26);
        action = action.after(312513).up(0x26);
        action = action.after(56517).down(0x27);
        action = action.after(312163).down(0x26);
        action = action.after(28334).up(0x27);
        action = action.after(227240).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(204165).down(0x25);
        action = action.after(1).up(0x27);
        action = action.after(113726).up(0x25);
        action = action.after(1).down(0x26);
        action = action.after(255479).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(56618).down(0x26);
        action = action.after(28240).up(0x27);
        action = action.after(348098).down(0x25);
        action = action.after(17748).up(0x26);
        action = action.after(287225).down(0x26);
        action = action.after(14149).up(0x25);
        action = action.after(81466).down(0x25);
        action = action.after(17827).up(0x26);
        action = action.after(141905).down(0x26);
        action = action.after(28220).up(0x25);
        action = action.after(113645).down(0x27);
        action = action.after(28220).up(0x26);
        action = action.after(508957).up(0x27);
        action = action.after(84738).down(0x27);
        action = action.after(84659).up(0x27);
        action = action.after(28299).down(0x25);
        action = action.after(227132).up(0x25);
        action = action.after(56440).down(0x27);
        action = action.after(205557).up(0x27);
        action = action.after(3278).down(0x25);
        action = action.after(17738).down(0x20);
        action = action.after(202601).down(0x27);
        action = action.after(17842).up(0x25);
        action = action.after(70673).up(0x20);
        action = action.after(24881).up(0x27);
        action = action.after(56519).down(0x28);
        action = action.after(113363).down(0x25);
        action = action.after(28298).up(0x28);
        action = action.after(28266).down(0x20);
        action = action.after(410623).up(0x20);
        action = action.after(503991).down(0x27);
        action = action.after(28334).up(0x25);
        action = action.after(312497).down(0x25);
        action = action.after(28399).up(0x27);
        action = action.after(285982).down(0x27);
        action = action.after(3644).up(0x25);
        action = action.after(736677).up(0x27);
        action = action.after(163222).down(0x27);
        action = action.after(1543309).down(0x26);
        action = action.after(28263).up(0x27);
        action = action.after(311849).down(0x27);
        action = action.after(3659).up(0x26);
        action = action.after(365149).down(0x26);
        action = action.after(53051).up(0x27);
        action = action.after(372305).down(0x27);
        action = action.after(28166).up(0x26);
        action = action.after(175673).up(0x27);
        action = action.after(1).down(0x25);
        action = action.after(515001).down(0x26);
        action = action.after(24569).up(0x25);
        action = action.after(220026).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(1270545).down(0x26);
        action = action.after(56457).up(0x26);
        action = action.after(42594).up(0x27);
        action = action.after(95105).down(0x26);
        action = action.after(432929).down(0x25);
        action = action.after(52868).up(0x26);
        action = action.after(102665).down(0x26);
        action = action.after(81140).up(0x25);
        action = action.after(98832).down(0x25);
        action = action.after(28274).up(0x26);
        action = action.after(1).down(0x26);
        action = action.after(28179).up(0x25);
        action = action.after(113000).up(0x26);
        action = action.after(1).down(0x25);
        action = action.after(63740).down(0x26);
        action = action.after(21152).up(0x25);
        action = action.after(220557).down(0x27);
        action = action.after(5992).up(0x26);
        action = action.after(398934).down(0x26);
        action = action.after(28179).up(0x27);
        action = action.after(141272).down(0x27);
        action = action.after(1).up(0x26);
        action = action.after(42597).down(0x26);
        action = action.after(14144).up(0x27);
        action = action.after(440588).up(0x26);
        action = action.after(205228).down(0x27);
        action = action.after(279262).down(0x26);
        action = action.after(28101).up(0x27);
        action = action.after(283399).down(0x25);
        action = action.after(28327).up(0x26);
        action = action.after(147471).up(0x25);
        action = action.after(1).down(0x27);
        action = action.after(280668).down(0x26);
        action = action.after(49359).up(0x27);
        action = action.after(322970).down(0x27);
        action = action.after(1).up(0x26);
        action = action.after(255563).down(0x26);
        action = action.after(84473).up(0x27);
        action = action.after(244613).down(0x27);
        action = action.after(38789).up(0x26);
        action = action.after(1254171).up(0x27);
        action = action.after(186777).down(0x27);
        action = action.after(38879).up(0x27);
        action = action.after(846459).down(0x25);
        action = action.after(85059).up(0x25);
        action = action.after(84718).down(0x27);
        action = action.after(85238).up(0x27);
        action = action.after(84569).down(0x26);
        action = action.after(560797).down(0x25);
        action = action.after(7070).up(0x26);
        action = action.after(161713).up(0x25);
        action = action.after(127087).down(0x26);
        action = action.after(424889).up(0x26);
        action = action.after(1).down(0x25);
        action = action.after(56532).up(0x25);
        action = action.after(229694).down(0x26);
        action = action.after(109772).up(0x26);
        action = action.after(207965).down(0x25);
        action = action.after(301583).up(0x25);
        action = action.after(281892).down(0x25);
        action = action.after(56929).up(0x25);
        action = action.after(367274).down(0x25);
        action = action.after(1509511).up(0x25);
        action = action.after(1).down(0x26);
        action = action.after(539585).down(0x25);
        action = action.after(28327).up(0x26);
        action = action.after(62088).up(0x25);
        action = action.after(24770).down(0x27);
        action = action.after(117190).up(0x27);
        action = action.after(141125).down(0x27);
        action = action.after(28123).down(0x26);
        action = action.after(1).up(0x27);
        action = action.after(237590).down(0x27);
        action = action.after(45772).up(0x27);
        action = action.after(28242).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(63861).up(0x27);
        action = action.after(77524).down(0x27);
        action = action.after(56882).up(0x27);
        action = action.after(451878).down(0x27);
        action = action.after(255480).up(0x27);
        action = action.after(225645).down(0x25);
        action = action.after(56538).up(0x25);
        action = action.after(395540).down(0x27);
        action = action.after(1093833).up(0x27);
        action = action.after(1).down(0x26);
        action = action.after(331166).up(0x26);
        action = action.after(140413).down(0x27);
        action = action.after(367603).down(0x26);
        action = action.after(1).up(0x27);
        action = action.after(170380).up(0x26);
        action = action.after(28213).down(0x26);
        action = action.after(14275).up(0x26);
        action = action.after(70503).down(0x20);
        action = action.after(56429).down(0x25);
        action = action.after(702294).up(0x25);
        action = action.after(25739).up(0x20);
        action = action.after(56433).down(0x28);
        action = action.after(198708).down(0x25);
        action = action.after(28185).up(0x28);
        action = action.after(341024).up(0x25);
        action = action.after(28025).down(0x27);
        action = action.after(60409).up(0x27);
        action = action.after(193837).down(0x25);
        action = action.after(85394).up(0x25);
        action = action.after(56456).down(0x27);
        action = action.after(227027).up(0x27);
        action = action.after(1298252).down(0x25);
        action = action.after(198739).up(0x25);
        action = action.after(141118).down(0x27);
        action = action.after(71093).up(0x27);
        action = action.after(4806713).down(0x27);
        action = action.after(275574).down(0x26);
        action = action.after(28959).up(0x27);
        action = action.after(319437).down(0x27);
        action = action.after(1).up(0x26);
        action = action.after(85016).down(0x26);
        action = action.after(42311).up(0x27);
        action = action.after(155537).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(56572).down(0x26);
        action = action.after(28180).up(0x27);
        action = action.after(226891).down(0x25);
        action = action.after(28259).up(0x26);
        action = action.after(141765).down(0x26);
        action = action.after(6981).up(0x25);
        action = action.after(276819).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(250074).up(0x27);
        action = action.after(28165).down(0x25);
        action = action.after(194845).up(0x25);
        action = action.after(220).down(0x26);
        action = action.after(212722).down(0x25);
        action = action.after(28259).up(0x26);
        action = action.after(198514).down(0x26);
        action = action.after(28094).up(0x25);
        action = action.after(368715).down(0x27);
        action = action.after(28181).up(0x26);
        action = action.after(510513).down(0x28);
        action = action.after(17621).up(0x27);
        action = action.after(141845).down(0x25);
        action = action.after(10666).up(0x28);
        action = action.after(155936).up(0x25);
        action = action.after(677931).down(0x27);
        action = action.after(226630).up(0x27);
        action = action.after(198327).down(0x27);
        action = action.after(339973).up(0x27);
        action = action.after(56665).down(0x25);
        action = action.after(542975).down(0x26);
        action = action.after(23875).up(0x25);
        action = action.after(283307).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(170161).up(0x27);
        action = action.after(207993).down(0x25);
        action = action.after(216267).up(0x25);
        action = action.after(1353411).down(0x27);
        action = action.after(776247).down(0x28);
        action = action.after(17691).up(0x27);
        action = action.after(396947).up(0x28);
        action = action.after(38652).down(0x25);
        action = action.after(51659).up(0x25);
        action = action.after(66941).down(0x26);
        action = action.after(251180).up(0x26);
        action = action.after(21881).down(0x27);
        action = action.after(539043).down(0x26);
        action = action.after(1).up(0x27);
        action = action.after(127088).up(0x26);
        action = action.after(14129).down(0x27);
        action = action.after(42612).down(0x26);
        action = action.after(14050).up(0x27);
        action = action.after(656344).down(0x25);
        action = action.after(28258).up(0x26);
        action = action.after(677233).up(0x25);
        action = action.after(91613).down(0x26);
        action = action.after(162902).down(0x27);
        action = action.after(28193).up(0x26);
        action = action.after(283676).down(0x26);
        action = action.after(28164).up(0x27);
        action = action.after(145462).down(0x27);
        action = action.after(24602).up(0x26);
        action = action.after(312160).down(0x26);
        action = action.after(28048).up(0x27);
        action = action.after(209197).up(0x26);
        action = action.after(17541).down(0x27);
        action = action.after(151767).down(0x20);
        action = action.after(203525).up(0x20);
        action = action.after(196485).down(0x25);
        action = action.after(28103).up(0x27);
        action = action.after(141635).down(0x28);
        action = action.after(28214).up(0x25);
        action = action.after(88346).down(0x25);
        action = action.after(24962).up(0x28);
        action = action.after(425091).down(0x20);
        action = action.after(127937).up(0x20);
        action = action.after(287499).down(0x26);
        action = action.after(28199).up(0x25);
        action = action.after(198417).up(0x26);
        action = action.after(1).down(0x27);
        action = action.after(482559).down(0x26);
        action = action.after(56320).up(0x27);
        action = action.after(198406).down(0x27);
        action = action.after(28292).up(0x26);
        action = action.after(250229).down(0x26);
        action = action.after(7027).up(0x27);
        action = action.after(359304).up(0x26);
        action = action.after(224705).down(0x27);
        action = action.after(130817).down(0x26);
        action = action.after(10559).up(0x27);
        action = action.after(254583).down(0x27);
        action = action.after(1).up(0x26);
        action = action.after(539343).up(0x27);
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