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
import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.applet.Applet;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Класс 'Main' с помощью ключевого слова {extends} наследуется от класса Applet.
 * При этом методам класса 'Main' становятся доступными все методы и данные класса,
 * за исключением определенных как private. Класс 'Applet' определен в библиотеке
 * классов java.applet.Applet, которую мы подключили оператором 'import'.
 */
public class Main extends Applet implements Runnable {

    private Hardware hard = null;
    private Thread thread = null;

    @Override
    public String getAppletInfo() {
        return "Specialist V1.0 alpha, © 2011 Sam_Computers LTD";
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

        if (hard == null) {
            try {
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
        hard.setBorderWidth(hard.borderWidth);

        // once borderWidth is set up
        resize(preferredSize());

        URL base = getDocumentBase();
        boolean lik = true;
        if (lik) {
            Lik.loadRom(base, hard.roms());
            Lik.loadGame(base, hard.roms(), "klad");
        } else {
            Specialist.loadRom(base, hard.roms());
            Specialist.loadGame(base, hard.roms(), "blobcop");
        }

        String snapshot = null; // TODO научиться сохранять и загружать снепшоты
        if (snapshot != null) {
            URL url = new URL(base, snapshot);
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

    @Override
    public Dimension minimumSize() {
        int border = (hard == null) ? 20 : hard.borderWidth;
        return new Dimension(
                Video.width + border * 2,
                Video.height + border * 2);
    }

    @Override
    public Dimension preferredSize() {
        return minimumSize();
    }
}