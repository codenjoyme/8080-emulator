package spec;

/*
 * @(#)Spec1987.java V1.0 alpha, © 21/05/2011 Sam_Computers LTD
 * Special thanks to Adam Davidson & Andrew Pollard for Jasper sources.
 */

//--------------------------------------------------------------------------------------------
//     Оператор import должен располагаться в файле исходного текста перед другими операторами
//     В качестве параметра оператору import передается имя подключаемого класса из
//     библиотеки классов. Если же необходимо подключить все классы данной библиотеки,
//      вместо имени класса указывается символ "*".
import java.applet.*;
//     java.applet.Applet содержит классы, необходимые для создания аплетов, то есть
//     разновидности приложений Java, встраиваемых в документы HTML и работающих под
//     управлением браузера Internet.
import java.awt.*;
//     java.awt. С ее помощью аплет может выполнять в своем окне рисование различных
//     изображений или текста.
import java.net.*;
import java.io.*;
import java.awt.AWTEvent;
/**
 * <p>The <a href="http://www.odie.demon.co.uk/spectrum">Spec1987</a> class wraps up
 * the Spechard class into an Applet which emulates a ZX Spechard in a Web Page.</p>
 *
 * <p><center>
 *   <applet code=Spec1987.class width=296 height=232>
 *   <param name=sleepHack value=5>
 *   </applet>
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
 *
Pollard</A><br>
 *
 * @see Spechard
 * @see Z80
 */

//--------------------------------------------------------------------------------------------
//---  класс 'Spec1987' с помощью ключевого слова {extends} наследуется от класса Applet.
//---  При этом методам класса 'Spec1987' становятся доступными все методы и данные класса,
//---  за исключением определенных как private. Класс 'Applet' определен в библиотеке
//---  классов java.applet.Applet, которую мы подключили оператором 'import'.
public class Spec1987 extends Applet implements Runnable {
  Spechard spechard = null; //--- класс Spechard.class
    Thread thread   = null;

  // Version and author information.
  // мы переопределили метод getAppletInfo из базового класса так, что getAppletInfo
  // возвращает текстовую информацию об аплете в виде объекта класса String.
public String getAppletInfo() {
    return "Specialist V1.0 alpha, © 2011 Sam_Computers LTD";
  }

//--------------------------------------------------------------------------------------------
  // Applet parameters and their descriptions.
public String[][] getParameterInfo() {
    String [][] info = {
      { "snapshot",    "filename", "name of SNA or Z80 snapshot to load" },
      { "rom",         "filename", "filename of ROM (default=ramfos.rom)" },
      { "borderWidth", "integer",  "width of Spechard border (default=20)" },
      { "refreshRate", "integer",  "refresh screen every 'X' interrupts (default=1)" },
      { "sleepHack",   "integer",  "sleep per interrupt in ms, for old VMs (default=0)" },
      { "showStats",   "Yes/No",   "show progress bar (default=Yes)" },
    };
    return info;
  }

// 1 ========================================================================================
//  Метод init вызывается первым. В нем вы должны инициализировать свои переменные,
//  Метод init вызывается только однажды — при загрузке апплета.
  // Initailize the applet.
  // Метод init вызывается тогда, когда браузер загружает в свое окно документ HTML с
  // оператором <APPLET>, ссылающимся на данный аплет. В этот момент аплет может выполнять
  // инициализацию, или, например, создавать потоки, если он работает в многопоточном режиме.
public void init() {
// System.out.println(" Happenned INIT !");
    setLayout( null ); // вызов метода использован для выключения механизма режима размещения
  }

// 2 ========================================================================================
  // Start the applet creating a new thread which invokes run().
  // Метод start вызывается после метода init в момент, когда пользователь начинает
  // просматривать документ HTML с встроенным в него аплетом.
  // Он также используется в качестве стартовой точки для возобновления работы после того,
  //  как апплет был остановлен.
public void start() {
// System.out.println(" Happenned START !");
   if(thread == null ) {
      thread = new Thread( this, "Spec1987" );
      thread.start();
    }
  }

// 5 ========================================================================================
  // Stop the applet.
  // метод stop. Он получает управление, когда пользователь покидает страницу с аплетом
  // и загружает в окно браузера другую страницу, stop вызывается перед методом destroy.
public void stop() {
// System.out.println(" Happenned STOP !");
   if(thread != null ) {
      //--thread.stop();
      thread = null;
    }
  }

// 6 ========================================================================================
  // Destroy the applet.
  /**
  * Cleans up whatever resources are being held.
  * If the applet is active it is stopped.
  *
  */
public void destroy() {
  }

//--------------------------------------------------------------------------------------------
// метод run не вызывается напрямую никакими другими методами. Он получает управление
// при запуске потока методом start.
//
// Read applet parameters and start the Spechard.
public void run() {
//  showStatus(" Happenned RUN !");
    showStatus( getAppletInfo() );  //--- "Specialist V1.0 © 2011 Sam_Computers LTD"

   if( spechard == null ) //--- ещё не создан экземпляр Spechard.class
     {
      try {//- Конструктору класса  Spechard()передается ссылка на компонент,
           //- для которого необходимо отслеживать загрузку изображений (или что-то?).
           //- В данном случае это наш аплет, --> мы передаем конструктору значение (this)!!!
           spechard = new Spechard( this ); //--- создан экземпляр Spechard.class
           readParameters();
          }
           catch ( Exception e )
          {
           showStatus( "Caught IO Error: " + e.toString() );
          }
     }
     if ( spechard != null )
       {
//  showStatus(" Spechard.execute !");
        spechard.execute(); // ---> Z80.class > public final void execute()
        // class Spechard extends Z80 -> все методы Z80.class доступны Spechard.class-у
       }
  }

//--------------------------------------------------------------------------------------------
  // Parse available applet parameters.
  //  @exception Exception Problem loading ROM or snaphot.
  //  Вызывается из метода ------------------------------ RUN -------
public void readParameters() throws Exception {
  String rom = getParameter( "rom" );
    if ( rom == null ) {
         rom = "ramfos.rom";
    }
    spechard.setBorderWidth( getIntParameter( "borderWidth",
    spechard.borderWidth*spechard.pixelScale, 0, 100 ) );

    spechard.refreshRate   = getIntParameter( "refreshRate",
    spechard.refreshRate, 1, 100 );

    spechard.sleepHack   = getIntParameter( "sleepHack",
    spechard.sleepHack, 0, 100 );

    resize( preferredSize() ); // once borderWidth is set up

  String showStats = getParameter( "showStats" );
    if ( showStats != null ) {
      if ( showStats.equals( "Yes" ) ) {
           spechard.showStats = true;
           spechard.pbaron = true;
           }
       else
      if ( showStats.equals( "No" ) )
         {
           spechard.showStats = false;
           spechard.pbaron = false;
      }
    }

    URL baseURL = getDocumentBase();      //  path/
    spechard.urlField.setText( baseURL.toString() );
//---***---------------------------------------------------------------------------------
//---***    URL romZURL = new URL( baseURL, rom ); // "ramfos.rom"
//---***    spechard.loadROMZ( romZURL.toString(), romZURL.openStream() );

//--- для ПК "ЛИК" ---------------------------------------------------------------
    boolean lik = false; // ЛИК или Специалист
    if (lik) {
        URL likRom1URL = new URL(baseURL, "lik/01_zagr.BIN");
        spechard.loadROM(likRom1URL.toString(), likRom1URL.openStream(), 0xC000);

        URL likRom2URL = new URL(baseURL, "lik/02_mon-1m.BIN");
        spechard.loadROM(likRom2URL.toString(), likRom2URL.openStream(), 0xC800);

        URL likRom3URL = new URL(baseURL, "lik/03_mon-1m_basicLik.BIN");
        spechard.loadROM(likRom3URL.toString(), likRom3URL.openStream(), 0xD000);

        URL likRom4URL = new URL(baseURL, "lik/04_basicLik.BIN");
        spechard.loadROM(likRom4URL.toString(), likRom4URL.openStream(), 0xD800);

        URL likRom5URL = new URL(baseURL, "lik/05_basicLik.BIN");
        spechard.loadROM(likRom5URL.toString(), likRom5URL.openStream(), 0xE000);

        URL likRom6URL = new URL(baseURL, "lik/06_basicLik.BIN");
        spechard.loadROM(likRom6URL.toString(), likRom6URL.openStream(), 0xE800);
    } else {
//--- для ПК "Специалист" ---------------------------------------------------------------
        URL specRom0URL = new URL(baseURL, "specialist/monitor0.rom");
        spechard.loadROM(specRom0URL.toString(), specRom0URL.openStream(), 0xC000);

        URL specRom1URL = new URL(baseURL, "specialist/monitor1.rom");
        spechard.loadROM(specRom1URL.toString(), specRom1URL.openStream(), 0xC800);
    }
//--- для ПК "Специалист" ---------------------------------------------------------------

    String snapshot = getParameter( "snapshot" );
    snapshot = ((snapshot == null) ? getParameter( "sna" ) : snapshot);
    snapshot = ((snapshot == null) ? getParameter( "z80" ) : snapshot);

if( snapshot != null )
  {
      URL url  = new URL( baseURL, snapshot );
      URLConnection snap = url.openConnection();

      InputStream input = snap.getInputStream();
      spechard.loadSnapshot( url.toString(), input, snap.getContentLength() );
      input.close();
    }
    else
    {
      spechard.reset();
      spechard.refreshWholeScreen();
    }
  }

//--------------------------------------------------------------------------------------------
  // Handle integer parameters in a range with defaults.
  // Вызывается из метода -------------------------------- readParameters()
public int getIntParameter( String name, int ifUndef, int min, int max ) {
    String param = getParameter( name );
  if( param == null ) {
      return ifUndef;
    }
    try {
      int n = Integer.parseInt( param );
      if ( n < min ) return min;
      if ( n > max ) return max;
      return n;
    }
    catch ( Exception e ) {
      return ifUndef;
    }
  }
// 4 ========================================================================================
  // Refresh handling.
  // метод update класса Applet сначала закрашивает апплет цветом фона по умолчанию, после
  // чего вызывает метод paint. Если вы в методе paint заполняете фон другим цветом,
  // пользователь будет видеть вспышку цвета по умолчанию при каждом вызове метода update —
  // то есть, всякий раз, когда вы перерисовываете апплет. Чтобы избежать этого, нужно
  // заместить метод update. В общем случае нужно выполнять операции рисования в методе
  // update, а в методе paint, к которому будет обращаться AWT, просто вызвать update.
public void update( Graphics g ) {
// showStatus(" Happenned UPDATE !");
    paint( g );
  }

// 3 ========================================================================================
  // Метод paint вызывается, когда необходимо перерисовать окно аплета.
  // Методу paint в качестве параметра передается ссылка на объект Graphics: g
  // По своему смыслу этот объект напоминает контекст отображения, с которым хорошо
  // знакомы создатели приложений Windows. Контекст отображения - это как бы холст,
  // на котором аплет может рисовать изображение или писать текст.
  // Многочисленные методы класса Graphics позволяют задавать различные параметры холста,
  // такие, например, как цвет или шрифт.
  // Для окна аплета создается объект класса Graphics, ссылка на который передается методу
  // "paint".
  // Paint sets a flag on the Spechard to tell it to redraw the
  // screen on the next Z80 interrupt.
  // Метод paint вызывается каждый раз при повреждении апплета. В таких случаях, как,
  // например, перекрытие окна апплета другим окном. В таких случаях, после того, как апплет
  // снова оказывается видимым, для восстановления его изображения вызывается метод paint.
public void paint( Graphics g ) {
// showStatus(" Happenned PAINT !");
    if ( spechard != null ) {
         spechard.repaint();  //  Выззвали метод  repaint() класса Spechard.
    }
  }

// *** ======================================================================================
  // Event handling.
  // Когда возникает событие, управление получает метод handleEvent из класса Component.
  // Класс Applet является дочерним по отношению к классу Component.

  // Вызываемый используемой по умолчанию реализацией метод handleEvents класса Component.
  // Этот метод вызывается с объектом класса Event, описывающего все возможные типы событий.
  // Наиболее часто используемые события, например, те, что связаны с мышью и клавиатурой,
  // диспетчеризируются другим методам класса Component.

  // приложение может переопределить метод handleEvent и обрабатывать события самостоятельно
//-- boolean handleEvent(Event)   replaced by void processEvent(AWTEvent e).
public boolean handleEvent(Event e) {
// showStatus(" Happenned Event !" + e );
  if( spechard != null )
    {
      return spechard.handleEvent( e ); // Вызывает handleEvent( e ) класса Spechard.
    }
    return super.handleEvent( e ); // Если класса Spechard нет вызовем super.handleEvent( e ).
  }
//--------------------------------------------------------------------------------------------
  // Applet size.
public Dimension minimumSize() {
    int scale  = spechard.pixelScale;
    int border = (spechard == null) ? 20 : spechard.borderWidth;
        //                          ^---- тернарный оператор if-then-else

    return new Dimension(
      spechard.nPixelsWide * scale + border*2,
      spechard.nPixelsHigh * scale + border*2);
  }
//--------------------------------------------------------------------------------------------
  // Returns Spec1987.minimumSize().
public Dimension preferredSize() {
    return minimumSize();
  }
}
//----------------------------------------- END ----------------------------------------------