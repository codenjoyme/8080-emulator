/*
 * @(#)Spec1987.java V1.0 alpha, � 21/05/2011 Sam_Computers LTD
 * Special thanks to Adam Davidson & Andrew Pollard for Jasper sources.
 */

//--------------------------------------------------------------------------------------------
//     �������� import ������ ������������� � ����� ��������� ������ ����� ������� �����������
//     � �������� ��������� ��������� import ���������� ��� ������������� ������ ��
//     ���������� �������. ���� �� ���������� ���������� ��� ������ ������ ����������,
//      ������ ����� ������ ����������� ������ "*".
import java.applet.*;
//     java.applet.Applet �������� ������, ����������� ��� �������� �������, �� ����
//     ������������� ���������� Java, ������������ � ��������� HTML � ���������� ���
//     ����������� �������� Internet.
import java.awt.*;
//     java.awt. � �� ������� ����� ����� ��������� � ����� ���� ��������� ���������
//     ����������� ��� ������.
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
//---  ����� 'Spec1987' � ������� ��������� ����� {extends} ����������� �� ������ Applet.
//---  ��� ���� ������� ������ 'Spec1987' ���������� ���������� ��� ������ � ������ ������,
//---  �� ����������� ������������ ��� private. ����� 'Applet' ��������� � ����������
//---  ������� java.applet.Applet, ������� �� ���������� ���������� 'import'.
public class Spec1987 extends Applet implements Runnable {
  Spechard spechard = null; //--- ����� Spechard.class
    Thread thread   = null;

  // Version and author information.
  // �� �������������� ����� getAppletInfo �� �������� ������ ���, ��� getAppletInfo
  // ���������� ��������� ���������� �� ������ � ���� ������� ������ String.
public String getAppletInfo() {
    return "Specialist V1.0 alpha, � 2011 Sam_Computers LTD";
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
//  ����� init ���������� ������. � ��� �� ������ ���������������� ���� ����������,
//  ����� init ���������� ������ ������� � ��� �������� �������.
  // Initailize the applet.
  // ����� init ���������� �����, ����� ������� ��������� � ���� ���� �������� HTML �
  // ���������� <APPLET>, ����������� �� ������ �����. � ���� ������ ����� ����� ���������
  // �������������, ���, ��������, ��������� ������, ���� �� �������� � ������������� ������.
public void init() {
// System.out.println(" Happenned INIT !");
    setLayout( null ); // ����� ������ ����������� ��� ���������� ��������� ������ ����������
  }

// 2 ========================================================================================
  // Start the applet creating a new thread which invokes run().
  // ����� start ���������� ����� ������ init � ������, ����� ������������ ��������
  // ������������� �������� HTML � ���������� � ���� �������.
  // �� ����� ������������ � �������� ��������� ����� ��� ������������� ������ ����� ����,
  //  ��� ������ ��� ����������.
public void start() {
// System.out.println(" Happenned START !");
   if(thread == null ) {
      thread = new Thread( this, "Spec1987" );
      thread.start();
    }
  }

// 5 ========================================================================================
  // Stop the applet.
  // ����� stop. �� �������� ����������, ����� ������������ �������� �������� � �������
  // � ��������� � ���� �������� ������ ��������, stop ���������� ����� ������� destroy.
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
// ����� run �� ���������� �������� �������� ������� ��������. �� �������� ����������
// ��� ������� ������ ������� start.
//
// Read applet parameters and start the Spechard.
public void run() {
//  showStatus(" Happenned RUN !");
    showStatus( getAppletInfo() );  //--- "Specialist V1.0 � 2011 Sam_Computers LTD"

   if( spechard == null ) //--- ��� �� ������ ��������� Spechard.class
     {
      try {//- ������������ ������  Spechard()���������� ������ �� ���������,
           //- ��� �������� ���������� ����������� �������� ����������� (��� ���-��?).
           //- � ������ ������ ��� ��� �����, --> �� �������� ������������ �������� (this)!!!
           spechard = new Spechard( this ); //--- ������ ��������� Spechard.class
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
        // class Spechard extends Z80 -> ��� ������ Z80.class �������� Spechard.class-�
       }
  }

//--------------------------------------------------------------------------------------------
  // Parse available applet parameters.
  //  @exception Exception Problem loading ROM or snaphot.
  //  ���������� �� ������ ------------------------------ RUN -------
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

//--- ��� �� "����������" ---------------------------------------------------------------
    URL rom0URL = new URL( baseURL, "monitor0.rom" ); // "monitor0.rom"
    spechard.loadROM0( rom0URL.toString(), rom0URL.openStream() );

    URL rom1URL = new URL( baseURL, "monitor1.rom" ); // "monitor1.rom"
    spechard.loadROM1( rom1URL.toString(), rom1URL.openStream() );

//--- ��� �� "����������" ---------------------------------------------------------------

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
  // ���������� �� ������ -------------------------------- readParameters()
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
  // ����� update ������ Applet ������� ����������� ������ ������ ���� �� ���������, �����
  // ���� �������� ����� paint. ���� �� � ������ paint ���������� ��� ������ ������,
  // ������������ ����� ������ ������� ����� �� ��������� ��� ������ ������ ������ update �
  // �� ����, ������ ���, ����� �� ��������������� ������. ����� �������� �����, �����
  // ��������� ����� update. � ����� ������ ����� ��������� �������� ��������� � ������
  // update, � � ������ paint, � �������� ����� ���������� AWT, ������ ������� update.
public void update( Graphics g ) {
// showStatus(" Happenned UPDATE !");
    paint( g );
  }

// 3 ========================================================================================
  // ����� paint ����������, ����� ���������� ������������ ���� ������.
  // ������ paint � �������� ��������� ���������� ������ �� ������ Graphics: g
  // �� ������ ������ ���� ������ ���������� �������� �����������, � ������� ������
  // ������� ��������� ���������� Windows. �������� ����������� - ��� ��� �� �����,
  // �� ������� ����� ����� �������� ����������� ��� ������ �����.
  // �������������� ������ ������ Graphics ��������� �������� ��������� ��������� ������,
  // �����, ��������, ��� ���� ��� �����.
  // ��� ���� ������ ��������� ������ ������ Graphics, ������ �� ������� ���������� ������
  // "paint".
  // Paint sets a flag on the Spechard to tell it to redraw the
  // screen on the next Z80 interrupt.
  // ����� paint ���������� ������ ��� ��� ����������� �������. � ����� �������, ���,
  // ��������, ���������� ���� ������� ������ �����. � ����� �������, ����� ����, ��� ������
  // ����� ����������� �������, ��� �������������� ��� ����������� ���������� ����� paint.
public void paint( Graphics g ) {
// showStatus(" Happenned PAINT !");
    if ( spechard != null ) {
         spechard.repaint();  //  �������� �����  repaint() ������ Spechard.
    }
  }

// *** ======================================================================================
  // Event handling.
  // ����� ��������� �������, ���������� �������� ����� handleEvent �� ������ Component.
  // ����� Applet �������� �������� �� ��������� � ������ Component.

  // ���������� ������������ �� ��������� ����������� ����� handleEvents ������ Component.
  // ���� ����� ���������� � �������� ������ Event, ������������ ��� ��������� ���� �������.
  // �������� ����� ������������ �������, ��������, ��, ��� ������� � ����� � �����������,
  // ������������������ ������ ������� ������ Component.

  // ���������� ����� �������������� ����� handleEvent � ������������ ������� ��������������
//-- boolean handleEvent(Event)   replaced by void processEvent(AWTEvent e).
public boolean handleEvent(Event e) {
// showStatus(" Happenned Event !" + e );
  if( spechard != null )
    {
      return spechard.handleEvent( e ); // �������� handleEvent( e ) ������ Spechard.
    }
    return super.handleEvent( e ); // ���� ������ Spechard ��� ������� super.handleEvent( e ).
  }
//--------------------------------------------------------------------------------------------
  // Applet size.
public Dimension minimumSize() {
    int scale  = spechard.pixelScale;
    int border = (spechard == null) ? 20 : spechard.borderWidth;
        //                          ^---- ��������� �������� if-then-else

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