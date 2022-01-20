package spec;

/**
 * @(#)Main.java V1.0 alpha, © 21/05/2011 Sam_Computers LTD
 * Special thanks to Adam Davidson & Andrew Pollard for Jasper sources.
 */


/**
 * java.applet.Applet содержит классы, необходимые для создания аплетов, то есть
 * разновидности приложений Java, встраиваемых в документы HTML и работающих под
 * управлением браузера Internet.
 *
 * java.awt. С ее помощью аплет может выполнять в своем окне рисование различных
 * изображений или текста.
 */
import java.applet.Applet;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>The <a href="http://www.odie.demon.co.uk/spectrum">Main</a> class wraps up
 * the Spechard class into an Applet which emulates a ZX Spechard in a Web Page.</p>
 *
 * <p><center>
 * <applet code=Main.class width=296 height=232>
 * <param name=sleepHack value=5>
 * </applet>
 * </center></p>
 *
 * <p>This applet can be supplied the following parameters:
 * <ul>
 *   <li><b>snapshot   </b> - name of SNA or Z80 snapshot to load
 *   <li><b>rom        </b> - filename of ROM (default=ramfos.rom)
 *   <li><b>borderWidth</b> - width of Spechard border (default=20)
 *   <li><b>refreshRate</b> - refresh screen every 'X' interrupts (default=1)
 *   <li><b>sleepHack  </b> - sleep per interrupt in ms, for old VMs (default=0)
 *   <li><b>showStats  </b> - show progress bar (default=Yes)
 * </ul>
 * <p>
 * Pollard</A><br>
 *
 * @see Spechard
 * @see Z80
 */

/**
 * Класс 'Main' с помощью ключевого слова {extends} наследуется от класса Applet.
 * При этом методам класса 'Main' становятся доступными все методы и данные класса,
 * за исключением определенных как private. Класс 'Applet' определен в библиотеке
 * классов java.applet.Applet, которую мы подключили оператором 'import'.
 */
public class Main extends Applet implements Runnable {

    private Hardware hard = null;
    private Thread thread = null;

    /**
     * Version and author information.
     *     мы переопределили метод getAppletInfo из базового класса так, что getAppletInfo
     *     возвращает текстовую информацию об аплете в виде объекта класса String.
     */
    @Override
    public String getAppletInfo() {
        return "Specialist V1.0 alpha, © 2011 Sam_Computers LTD";
    }

    /**
     * Applet parameters and their descriptions.
     */
    @Override
    public String[][] getParameterInfo() {
        String[][] info = {
                {"snapshot", "filename", "name of SNA or Z80 snapshot to load"},
                {"rom", "filename", "filename of ROM (default=ramfos.rom)"},
                {"borderWidth", "integer", "width of Spechard border (default=20)"},
                {"refreshRate", "integer", "refresh screen every 'X' interrupts (default=1)"},
                {"sleepHack", "integer", "sleep per interrupt in ms, for old VMs (default=0)"},
                {"showStats", "Yes/No", "show progress bar (default=Yes)"},
        };
        return info;
    }

    /**
     * #1 Метод init вызывается первым. В нем вы должны инициализировать свои переменные,
     *     Метод init вызывается только однажды — при загрузке апплета.
     *     Initailize the applet.
     *     Метод init вызывается тогда, когда браузер загружает в свое окно документ HTML с
     *     оператором <APPLET>, ссылающимся на данный аплет. В этот момент аплет может выполнять
     *     инициализацию, или, например, создавать потоки, если он работает в многопоточном режиме.
     */
    @Override
    public void init() {
        // вызов метода использован для выключения механизма режима размещения
        setLayout(null);
    }

    /**
     * #2 Start the applet creating a new thread which invokes run().
     *     Метод start вызывается после метода init в момент, когда пользователь начинает
     *     просматривать документ HTML с встроенным в него аплетом.
     *     Он также используется в качестве стартовой точки для возобновления работы после того,
     *     как апплет был остановлен.
     */
    @Override
    public void start() {
        if (thread == null) {
            thread = new Thread(this, "Main");
            thread.start();
        }
    }


    /**
     * #5 Stop the applet.
     *     метод stop. Он получает управление, когда пользователь покидает страницу с аплетом
     *     и загружает в окно браузера другую страницу, stop вызывается перед методом destroy.
     */
    @Override
    public void stop() {
        if (thread != null) {
            // thread.stop();
            thread = null;
        }
    }

    /**
     * #6 Destroy the applet.
     * Cleans up whatever resources are being held.
     * If the applet is active it is stopped.
     */
    @Override
    public void destroy() {
        // do nothing
    }

    /**
     * Метод run не вызывается напрямую никакими другими методами. Он получает управление
     *  при запуске потока методом start.
     *  Read applet parameters and start the Spechard.
     */
    public void run() {
        showStatus(getAppletInfo());

        //--- ещё не создан экземпляр Spechard.class
        if (hard == null) {
            try {
                // Конструктору класса  Spechard()передается ссылка на компонент,
                // для которого необходимо отслеживать загрузку изображений (или что-то?).
                // В данном случае это наш аплет,
                hard = new Hardware(this);
                readParameters();
            } catch (Exception e) {
                showStatus("Caught IO Error: " + e);
            }
        }
        if (hard != null) {
            hard.execute();
        }
    }


    /**
     * Parse available applet parameters.
     * Вызывается из метода  RUN
     * @throws Exception Problem loading ROM or snaphot.
     */
    public void readParameters() throws Exception {
        String rom = getParameter("rom");

        hard.setBorderWidth(getIntParameter("borderWidth",
                hard.borderWidth * Hardware.pixelScale, 0, 100));

        hard.refreshRate = getIntParameter("refreshRate",
                hard.refreshRate, 1, 100);

        hard.sleepHack = getIntParameter("sleepHack",
                hard.sleepHack, 0, 100);

        // once borderWidth is set up
        resize(preferredSize());

        String showStats = getParameter("showStats");
        if (showStats != null) {
            if (showStats.equals("Yes")) {
                hard.showStats = true;
                hard.pbaron = true;
            } else if (showStats.equals("No")) {
                hard.showStats = false;
                hard.pbaron = false;
            }
        }

        URL baseURL = getDocumentBase();
        hard.urlField.setText(baseURL.toString());

        boolean lik = true;
        if (lik) {
            // ЛИК
            URL likRom1URL = new URL(baseURL, "lik/roms/01_zagr.bin");
            hard.loadROM(likRom1URL.toString(), likRom1URL.openStream(), 0xC000);

            URL likRom2URL = new URL(baseURL, "lik/roms/02_mon-1m.bin");
            hard.loadROM(likRom2URL.toString(), likRom2URL.openStream(), 0xC800);

            URL likRom3URL = new URL(baseURL, "lik/roms/03_mon-1m_basicLik.bin");
            hard.loadROM(likRom3URL.toString(), likRom3URL.openStream(), 0xD000);

            URL likRom4URL = new URL(baseURL, "lik/roms/04_basicLik.bin");
            hard.loadROM(likRom4URL.toString(), likRom4URL.openStream(), 0xD800);

            URL likRom5URL = new URL(baseURL, "lik/roms/05_basicLik.bin");
            hard.loadROM(likRom5URL.toString(), likRom5URL.openStream(), 0xE000);

            URL likRom6URL = new URL(baseURL, "lik/roms/06_basicLik.bin");
            hard.loadROM(likRom6URL.toString(), likRom6URL.openStream(), 0xE800);

            URL kladURL = new URL(baseURL, "lik/apps/klad.rks");
            hard.loadRKS(kladURL.toString(), kladURL.openStream());
        } else {
            // Специалист
            URL specRom0URL = new URL(baseURL, "specialist/roms/monitor0.rom");
            hard.loadROM(specRom0URL.toString(), specRom0URL.openStream(), 0xC000);

            URL specRom1URL = new URL(baseURL, "specialist/roms/monitor1.rom");
            hard.loadROM(specRom1URL.toString(), specRom1URL.openStream(), 0xC800);
        }

        String snapshot = getParameter("snapshot");
        snapshot = ((snapshot == null) ? getParameter("sna") : snapshot);
        snapshot = ((snapshot == null) ? getParameter("z80") : snapshot);

        if (snapshot != null) {
            URL url = new URL(baseURL, snapshot);
            URLConnection snap = url.openConnection();

            InputStream input = snap.getInputStream();
            hard.loadSnapshot(url.toString(), input, snap.getContentLength());
            input.close();
        } else {
            hard.reset();
            hard.refreshWholeScreen();
        }
    }

    /**
     * Handle integer parameters in a range with defaults.
     *     Вызывается из метода readParameters()
     */
    public int getIntParameter(String name, int ifUndef, int min, int max) {
        String param = getParameter(name);
        if (param == null) {
            return ifUndef;
        }
        try {
            int n = Integer.parseInt(param);
            if (n < min) return min;
            if (n > max) return max;
            return n;
        } catch (Exception e) {
            return ifUndef;
        }
    }

    /**
     * #4 Refresh handling.
     *     метод update класса Applet сначала закрашивает апплет цветом фона по умолчанию, после
     *     чего вызывает метод paint. Если вы в методе paint заполняете фон другим цветом,
     *     пользователь будет видеть вспышку цвета по умолчанию при каждом вызове метода update —
     *     то есть, всякий раз, когда вы перерисовываете апплет. Чтобы избежать этого, нужно
     *     заместить метод update. В общем случае нужно выполнять операции рисования в методе
     *     update, а в методе paint, к которому будет обращаться AWT, просто вызвать update.
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * #3 Метод paint вызывается, когда необходимо перерисовать окно аплета.
     *     Методу paint в качестве параметра передается ссылка на объект Graphics: g
     *     По своему смыслу этот объект напоминает контекст отображения, с которым хорошо
     *     знакомы создатели приложений Windows. Контекст отображения - это как бы холст,
     *     на котором аплет может рисовать изображение или писать текст.
     *     Многочисленные методы класса Graphics позволяют задавать различные параметры холста,
     *     такие, например, как цвет или шрифт.
     *     Для окна аплета создается объект класса Graphics, ссылка на который передается методу
     *     "paint".
     *     Paint sets a flag on the Spechard to tell it to redraw the
     *     screen on the next Z80 interrupt.
     *     Метод paint вызывается каждый раз при повреждении апплета. В таких случаях, как,
     *     например, перекрытие окна апплета другим окном. В таких случаях, после того, как апплет
     *     снова оказывается видимым, для восстановления его изображения вызывается метод paint.
     */
    @Override
    public void paint(Graphics g) {
// showStatus(" Happenned PAINT !");
        if (hard != null) {
            hard.repaint();
        }
    }

    /**
     * Event handling.
     *     Когда возникает событие, управление получает метод handleEvent из класса Component.
     *     Класс Applet является дочерним по отношению к классу Component.
     *
     *     Вызываемый используемой по умолчанию реализацией метод handleEvents класса Component.
     *     Этот метод вызывается с объектом класса Event, описывающего все возможные типы событий.
     *     Наиболее часто используемые события, например, те, что связаны с мышью и клавиатурой,
     *     диспетчеризируются другим методам класса Component.
     *
     *     приложение может переопределить метод handleEvent и обрабатывать события самостоятельно
     */
    @Override
    public boolean handleEvent(Event e) {
        if (hard != null) {
            return hard.handleEvent(e);
        }
        return super.handleEvent(e);
    }


    /**
     * Applet size.
     */
    @Override
    public Dimension minimumSize() {
        int scale = Hardware.pixelScale;
        int border = (hard == null) ? 20 : hard.borderWidth;

        return new Dimension(
                Hardware.nPixelsWide * scale + border * 2,
                Hardware.nPixelsHigh * scale + border * 2);
    }

    @Override
    public Dimension preferredSize() {
        return minimumSize();
    }
}