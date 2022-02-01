// Надо обозначить все константы "Специалист" в портах ОЗУ ПЗУ и экране
// Заменены константами все характерные адреса типа 0xffe0 чтобы читалось
// удобнее и легче было переходить к внедрению нового экрана.
// Всё фактически готово! (Может ещё проверю подпрограммы).

// Уточнить вопрос с прерываниями и с адресом старта!!!
// protected int( Z80.java)_SP = 0, _PC = 0; //---программный счётчик = C000h - адрес старта!
//    public int( Z80.java)interrupt() - здесь заглушить переход на адреса прерываний.

// переписал функции pokeb(), pokew();
// ЭТО ПОСЛЕДНЯЯ РАБОЧАЯ ВЕРСИЯ, ГДЕ "СПЕЦИАЛИСТ" и "ZX" ВМЕСТЕ.

// УДАЛЯЮ ВЕСЬ "ZX" КОММЕНТАРИЯМИ, --- помечаю //---***

// ЭТО ПЕРВАЯ РАБОЧАЯ ВЕРСИЯ "СПЕЦИАЛИСТ" ГДЕ "ZX"-код ПРАКТИЧЕСКИ ВЕСЬ НА МЕСТЕ!
// СДЕЛАНА ЗАГРУЗКА ФАЙЛА *.RKS - работает! Подглюкивает игра Бабник...
// СДЕЛАНО МИГАНИЕ БОРДЮРА ПРИ ПОТЕРЕ ФОКУСА ОКНОМ АППЛЕТА.
// ------------------------------------------------------------------------------------------
/* Версия от 21.05.2011 19:11:48, реализован К580ВВ55А рабочая копия
 * прототип для ПК "Специалист".
 * Вариант с заменой: next[], last[] на nextAddr[], lastByte[].
 * @(#)Spechard.java 1.1 27/04/97 Adam Davidson & Andrew Pollard.
 */
import javax.swing.*;
import java.awt.*; //---(инструментарий для работы с абстрактными окнами).
import java.util.*;//---
import java.io.*;  //---
import java.net.*; //---

/**
 * The Spechard class extends the Z80 class implementing the supporting
 * hardware emulation which was specific to the 'Specialist' computer.
 * This includes the memory mapped screen and the IO ports which were
 * used to read the keyboard, change the border color and turn the
 * speaker on/off. There is no sound support in this version.<P>
 *
 *
 * @version 1.1 27 Apr 1997
 * @author <A HREF="http://www.odie.demon.co.uk/spectrum">Adam Davidson & Andrew Pollard</A>
 *
 * @see Spec1987
 * @see Z80
 */

//---- class Spechard наследует все методы Z80.class
public class Spechard extends Z80 {
  public Graphics parentGraphics = null;
  public Graphics canvasGraphics = null;
  public Graphics bufferGraphics = null;
  public Image    bufferImage = null;

  public Container parent   = null; // SpecApp usually (where border is drawn)
  public Canvas canvas      = null; // Main screen
  public TextField urlField = null; // ESC shows a URL popup
  public AMDProgressBar progressBar = null; // How much loaded/how fast?
//--- для ПК "Специалист" ---------------------------------
  public int rgb[] = new int[ 12288 ]; // массив байт цвета контроллера цвета 0C000h-9000h
  /** Memory */
// public final int  mem[] = new int[ 65536 ]; // массив байт памяти оределён в Z80.class

//------------------------------------------------------------------------------------------
//---- методы инициаллизации class Spechard ----
// Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
// которые дают возможность помещать в него другие компоненты, что дает возможность построения
// иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
// в нем компонентов с помощью интерфейса LayoutManager.
public Spechard( Container _parent ) throws Exception
  {      //--- new Spechard( this ) --- создание экземпляра Spechard.class
         //--- Конструктору класса Spechard() из Spec1987.class передается ссылка на компонент,
         //--- для которого необходимо отслеживать загрузку изображений (или что-то?).
         //--- В данном случае это наш аплет, --> мы передаем конструктору значение (this)!!!
//---***
    super( 1.6 ); //'Specialist' runs at 3.5Mhz; super=public Z80( double clockFrequencyInMHz )

//---  showStatus(" Execute on 3.5 Mhz!");
    parent = _parent; //--- = (this)?!!!

//- добавили к class Z80 ??? (а может к апплету this) Canvas - "холст" для рисования
    parent.add( canvas = new Canvas() );

//- warning: resize(int,int) in java.awt.Component has been deprecated
//- canvas.resize( nPixelsWide*pixelScale, nPixelsHigh*pixelScale ); //-=setSize
    canvas.setSize( nPixelsWide*pixelScale, nPixelsHigh*pixelScale );//- размер canvas

//- warning: show() in java.awt.Component has been deprecated
//- canvas.show();//-= setVisible(true);
    canvas.setVisible(true); //- сделали canvas видимым.

       bufferImage = parent.createImage( nPixelsWide*pixelScale, nPixelsHigh*pixelScale );

    bufferGraphics = bufferImage.getGraphics(); //- контенты графики
    parentGraphics = parent.getGraphics();      //-
    canvasGraphics = canvas.getGraphics();      //-

//- добавили к class Z80 AMDProgressBar
    parent.add( progressBar = new AMDProgressBar() );
    progressBar.setBarColor( new Color(192, 52, 4) ); //  192, 52, 4
                                  //--- R   G   B
    progressBar.setVisible(true); //--- show();
    progressBar.setVisible(false);//--- hide();

//- добавили к class Z80 TextField - поле ввода url файла для загрузки
    parent.add( urlField = new TextField() );
    urlField.setVisible(true);   //--- show();
    urlField.setVisible(false);  //--- hide();
    autor =  bytesToMes();
  }

//------------------------------------------------------------------------------------------
public void setBorderWidth( int width ) {
    borderWidth = width;
//- warning: move(int,int) in java.awt.Component has been deprecated
//- canvas.move( borderWidth, borderWidth ); //-= setLocation(
    canvas.setLocation( borderWidth, borderWidth );


//- reshape() = setBounds(int, int, int, int)
//- parent.preferredSize().width = getPreferredSize(). установить границы.
    urlField.setBounds( 0, 0, parent.getPreferredSize().width,
                            urlField.getPreferredSize().height );

    progressBar.setBounds(1, (borderWidth + nPixelsHigh * pixelScale) + 2,
                             nPixelsWide * pixelScale + borderWidth * 2 - 2, borderWidth - 2 );

    progressBar.setFont( urlField.getFont() ); //-  установить шрифт.
  }

//-------------------------- Z80 hardware interface --------------------------------------
//--- для ПК "Специалист" ---------------------------------
   public boolean pbaron = false;//--- progbar - невидим.
  private boolean wfocus = false;//--- фокус окном не получен
  private boolean invfoc = false;//--- моргание бордюром
  private boolean PrtAIN = true; //--- порт A - на ввод
  private boolean PrtBIN = true; //--- порт B - на ввод
  private boolean PrC0IN = true; //--- порт C0- на ввод
  private boolean PrC1IN = true; //--- порт C1- на ввод
  private boolean Shiftk = false; //--- Shift не нажат

  private String autor = "";
//--- ЭТИ ПОРТЫ ЛУЧШЕ СДЕЛАТЬ АДРЕСМИ, ЧТОБЫ МЕНЯТЬ ИХ ТОЛЬКО ЗДЕСЬ,,,
// Пользуясь ключевыми словами static и final, можно определять внутри классов
// глобальные константы.

  private static final int BASEnd = 0x4000; //------ ROM BASIC END.

  private static final int ATRBeg = 0x5800; //------ начало области аттрибутов
  private static final int ATREnd = 0x5aff; //------ конец области аттрибутов


  private static final int ScrBeg = 0x9000; //------ 0x9000; начало области экрана 0x4000;
  private static final int ScrEnd = 0xbfff; //------ 0xbfff; конец области экрана  0x57ff;

  private static final int ROMBeg = 0xc000; //------ 0C000H; начало ПЗУ.
  private static final int ROMEnd = 0xf7ff; //------ 0FFDFH; конец  ПЗУ. 0xffdf

  private static final int PortBeg = 0xf800;//------ начало портов: 0xffe0
  private static final int PortEnd = 0xfffe;//------ конец портов: 0xffff - память

  private static final int HiMem  = 0x10000;//------ запредел памяти.

  private static final int PortA  = 0xffe0; //------ Порт А ППА
  private static final int PortB  = 0xffe1; //------ Порт В ППА
  private static final int PortC  = 0xffe2; //------ Порт С ППА
  private static final int RgRYS  = 0xffe3; //------ рег. Упр.Слова ППА
  private static final int RgRGB  = 0xfff8; //------ порт контроллера цвета

  private int msk[] = {0x0001, 0x0002, 0x0004, 0x0008, 0x0010, 0x0020, 0x0040, 0x0080}; //--- маски битов
  private int bit[] = {0x00fe, 0x00fd, 0x00fb, 0x00f7, 0x00ef, 0x00df, 0x00bf, 0x007f}; //--- биты установки
  private int ptr[] = {0x0074, 0x0023, 0x0015, 0x0006, 0x000A, 0x0008, 0x000D, 0x0005, 0x001A, 0x0007,
                       0x0053, 0x0007, 0x0042, 0x001B, 0x0059, 0x0061, 0x002F, 0x000A, 0x0016, 0x0017,
                       0x001C, 0x0059, 0x0061, 0x006F, 0x000E, 0x0073, 0x0036, 0x0008, 0x0008, 0x000B,
                       0x0001, 0x0009, 0x0000, 0x006C, 0x00A3, 0x0089, 0x0012, 0x0002, 0x0001, 0x0000,
                       0x0011, 0x0073, 0x0032, 0x000C, 0x0032, 0x001C, 0x002C, 0x0002, 0x001D, 0x0005,
                       0x0001, 0x0011, 0x0017, 0x0001, 0x0053, 0x006C, 0x0018, 0x0010, 0x004E, 0x0059,
                       0x0023, 0x0015, 0x0006, 0x000A, 0x0008, 0x000D, 0x004C, 0x0074, 0x003C, 0x0009,
                       0x000F, 0x0005, 0x0018, 0x0053, 0x0074, 0x003B, 0x0065, 0x004B, 0x0025, 0x0005,
                       0x000C, 0x004D, 0x0064, 0x0025, 0x0017, 0x001F, 0x000D, 0x0017, 0x001C, 0x0001,
                       0x004E, 0x0006, 0x0006, 0x0061, 0x002F, 0x000A, 0x0016, 0x0017, 0x0012, 0x0057,
                       0x0070, 0x003F, 0x0003, 0x0000, 0x000D, 0x0013, 0x0016, 0x009B, 0x00ff
                      };
 //------     массив возможных ascii-кодов нажатых клавиш...
                            /** <BckSpc> <Tab> <Enter> <Esc>                             **/
  private     int ascii_keys[]= {0x0008,0x0009,0x000a,0x001b,
                            /**  <Space> | ! |  | " |  | # |  | $ |  | % |  | & |  | ' |**/
                                 0x0020,0x0021,0x0022,0x0023,0x0024,0x0025,0x0026,0x0027,
                            /**   | ( |  | ) |  | * |  | + |  | , |  | - |  | . |  | / |**/
                                 0x0028,0x0029,0x002a,0x002b,0x002c,0x002d,0x002e,0x002f,
                            /**   | 0 |  | 1 |  | 2 |  | 3 |  | 4 |  | 5 |  | 6 |  | 7 |**/
                                 0x0030,0x0031,0x0032,0x0033,0x0034,0x0035,0x0036,0x0037,
                            /**   | 8 |  | 9 |  | : |  | ; |  | < |  | = |  | > |  | ? |**/
                                 0x0038,0x0039,0x003a,0x003b,0x003c,0x003d,0x003e,0x003f,
                            /**   | @ |  | A |  | B |  | C |  | D |  | E |  | F |  | G |**/
                                 0x0040,0x0041,0x0042,0x0043,0x0044,0x0045,0x0046,0x0047,
                            /**   | H |  | I |  | J |  | K |  | L |  | M |  | N |  | O |**/
                                 0x0048,0x0049,0x004a,0x004b,0x004c,0x004d,0x004e,0x004f,
                            /**   | P |  | Q |  | R |  | S |  | T |  | U |  | V |  | W |**/
                                 0x0050,0x0051,0x0052,0x0053,0x0054,0x0055,0x0056,0x0057,
                            /**   | X |  | Y |  | Z |  | [ |  | \ |  | ] |  | ^ |  | _ |**/
                                 0x0058,0x0059,0x005a,0x005b,0x005c,0x005d,0x005e,0x005f,
                            /**   | ` |  | a |  | b |  | c |  | d |  | e |  | f |  | g |**/
                                 0x0060,0x0061,0x0062,0x0063,0x0064,0x0065,0x0066,0x0067,
                            /**   | h |  | i |  | j |  | k |  | l |  | m |  | n |  | o |**/
                                 0x0068,0x0069,0x006a,0x006b,0x006c,0x006d,0x006e,0x006f,
                            /**   | p |  | q |  | r |  | s |  | t |  | u |  | v |  | w |**/
                                 0x0070,0x0071,0x0072,0x0073,0x0074,0x0075,0x0076,0x0077,
                            /**   | x |  | y |  | z |  | { |  | | |  | } |  | ~ |  <Del>**/
                                 0x0078,0x0079,0x007a,0x007b,0x007c,0x007d,0x007e,0x007f,
                            /**  <Home>  <End> <PgUp> <PgDn> < Up > <Down> <Left> <Right>*/
                                 0x03e8,0x03e9,0x03ea,0x03eb,0x03ec,0x03ed,0x03ee,0x03ef,
                            /**   <F1>   <F2>   <F3>   <F4>   <F5>   <F6>   <F7>   <F8> **/
                                 0x03f0,0x03f1,0x03f2,0x03f3,0x03f4,0x03f5,0x03f6,0x03f7,
                            /**   <F9>   <F10>  <F11>  <F12> <CpsLk>                    **/
                                 0x03f8,0x03f9,0x03fa,0x03fb,0x03fe,
                            /**  <Insert> = ПВ [ Ё ]!;                                  **/
                                 0x0401,
                            /**   | А |  | Б |  | В |  | Г |  | Д |  | Е |  | Ж |  | З |**/
                                 0x0410,0x0411,0x0412,0x0413,0x0414,0x0415,0x0416,0x0417,
                            /**   | И |  | Й |  | К |  | Л |  | М |  | Н |  | О |  | П |**/
                                 0x0418,0x0419,0x041a,0x041b,0x041c,0x041d,0x041e,0x041f,
                            /**   | Р |  | С |  | Т |  | У |  | Ф |  | Х |  | Ц |  | Ч |**/
                                 0x0420,0x0421,0x0422,0x0423,0x0424,0x0425,0x0426,0x0427,
                            /**   | Ш |  | Щ |  | Ъ |  | Ы |  | Ь |  | Э |  | Ю |  | Я |**/
                                 0x0428,0x0429,0x042a,0x042b,0x042c,0x042d,0x042e,0x042f,
                            /**   | а |  | б |  | в |  | г |  | д |  | е |  | ж |  | з |**/
                                 0x0430,0x0431,0x0432,0x0433,0x0434,0x0435,0x0436,0x0437,
                            /**   | и |  | й |  | к |  | л |  | м |  | н |  | о |  | п |**/
                                 0x0438,0x0439,0x043a,0x043b,0x043c,0x043d,0x043e,0x043f,
                            /**   | р |  | с |  | т |  | у |  | ф |  | х |  | ц |  | ч |**/
                                 0x0440,0x0441,0x0442,0x0443,0x0444,0x0445,0x0446,0x0447,
                            /**   | ш |  | щ |  | ъ |  | ы |  | ь |  | э |  | ю |  | я |**/
                                 0x0448,0x0449,0x044a,0x044b,0x044c,0x044d,0x044e,0x044f,
                            /**   | ё |  | № |                                          **/
                                 0x0451,0x2116
                                 }; //- 23 строки по 8 = 184 + 4 + 2 + 2 = 192 клавиши.

 //------   массив позиций матрицы "Специалиста" для возможных ascii-кодов нажатых клавиш...
 //------   двухбайтное значение:0xXXYY, установка старшего бита = нажатию Shift(HP)
 //------   уточнить значения клавиш: <CapsLock> и Р/Л действуют в противофазе!
                            /** <BckSpc><Tab> <Enter> <Esc>                             **/
  private   short keybd_matr[]= {0x0001,0x0600,0x0000,0x0700,
                            /**  <Space> | ! |  | " |  | # |  | $ |  | % |  | & |  | ' |**/
                                 0x0500,0x7a04,0x7904,0x7804,0x7704,0x7604,0x7504,0x7404,
                            /**   | ( |  | ) |  | * |  | + |  | , |  | - |  | . |  | / |**/
                                 0x7304,0x7204,0x0003,0x7b04,0x0201,0x0004,0x0002,0x0101,
                            /**   | 0 |  | 1 |  | 2 |  | 3 |  | 4 |  | 5 |  | 6 |  | 7 |**/
                                 0x0104,0x0a04,0x0904,0x0804,0x0704,0x0604,0x0504,0x0404,
                            /**   | 8 |  | 9 |  | : |  | ; |  | < |  | = |  | > |  | ? |**/
                                 0x0304,0x0204,0x7003,0x0b03,0x7201,0x7004,0x0002,0x0101,
                            /**   | @ |  | A |  | B |  | C |  | D |  | E |  | F |  | G |**/
                                 0x0301,0x0802,0x0401,0x0a03,0x0302,0x0703,0x0b02,0x0503,
                            /**   | H |  | I |  | J |  | K |  | L |  | M |  | N |  | O |**/
                                 0x0103,0x0701,0x0b03,0x0803,0x0402,0x0801,0x0603,0x0502,
                            /**   | P |  | Q |  | R |  | S |  | T |  | U |  | V |  | W |**/
                                 0x0702,0x0b01,0x0602,0x0901,0x0601,0x0903,0x0202,0x0902,
                            /**   | X |  | Y |  | Z |  | [ |  | \ |  | ] |  | ^ |  | _ |**/
                                 0x0501,0x0a02,0x0203,0x0403,0x0102,0x0303,0x0a01,0x0001,
                            /**   | ` |  | a |  | b |  | c |  | d |  | e |  | f |  | g |**/
                                 0x0b04,0x7802,0x7401,0x7a03,0x7302,0x7703,0x7b02,0x7503,
                            /**   | h |  | i |  | j |  | k |  | l |  | m |  | n |  | o |**/
                                 0x7103,0x7701,0x7b03,0x7803,0x7402,0x7801,0x7603,0x7502,
                            /**   | p |  | q |  | r |  | s |  | t |  | u |  | v |  | w |**/
                                 0x7702,0x7b01,0x7602,0x7901,0x7601,0x7903,0x7202,0x7902,
                            /**   | x |  | y |  | z |  | { |  | | |  | } |  | ~ | <Del> **/
                                 0x7501,0x7a02,0x7203,0x7403,0x7102,0x7303,0x7b04,0x0005,
                            /**  <Home>  <End> <PgUp> <PgDn> < Up > <Down> <Left> <Right>*/
                                 0x0b05,0x0a05,0x0a00,0x0100,0x0900,0x0800,0x0400,0x0200,
                            /**   <F1>   <F2>   <F3>   <F4>   <F5>   <F6>   <F7>   <F8> **/
                                 0x0b05,0x0a05,0x0905,0x0805,0x0705,0x0605,0x0505,0x0405,
                            /**   <F9>   <F10>  <F11>  <F12> <CpsLk> = Р/Л              **/
                                 0x0305,0x0205,0x0105,0x0005,0x0700,
                            /**  <Insert> = ПВ [ Ё ]!;                                  **/
                                 0x0300,
                            /**   | А |  | Б |  | В |  | Г |  | Д |  | Е |  | Ж |  | З |**/
                                 0x0802,0x0401,0x0902,0x0503,0x0302,0x0703,0x0202,0x0203,
                            /**   | И |  | Й |  | К |  | Л |  | М |  | Н |  | О |  | П |**/
                                 0x0701,0x0b03,0x0803,0x0402,0x0801,0x0603,0x0502,0x0702,
                            /**   | Р |  | С |  | Т |  | У |  | Ф |  | Х |  | Ц |  | Ч |**/
                                 0x0602,0x0901,0x0601,0x0903,0x0b02,0x0103,0x0a03,0x0a01,
                            /**   | Ш |  | Щ |  | Ъ |  | Ы |  | Ь |  | Э |  | Ю |  | Я |**/
                                 0x0403,0x0303,0x0001,0x0a02,0x0501,0x0102,0x0301,0x0b01,
                            /**   | а |  | б |  | в |  | г |  | д |  | е |  | ж |  | з |**/
                                 0x7802,0x7401,0x7902,0x7503,0x7302,0x7703,0x7202,0x7203,
                            /**   | и |  | й |  | к |  | л |  | м |  | н |  | о |  | п |**/
                                 0x7701,0x7b03,0x7803,0x7402,0x7801,0x7603,0x7502,0x7702,
                            /**   | р |  | с |  | т |  | у |  | ф |  | х |  | ц |  | ч |**/
                                 0x7602,0x7901,0x7601,0x7903,0x7b02,0x7103,0x7a03,0x7a01,
                            /**   | ш |  | щ |  | ъ |  | ы |  | ь |  | э |  | ю |  | я |**/
                                 0x7403,0x7303,0x7001,0x7a02,0x7501,0x7102,0x7301,0x7b01,
                            /**   | ё |  | № |                                          **/
                                 0x7b04,0x7804
                                 }; //- 23 строки по 8 = 184 + 4 + 2 + 2 = 192 клавиши.
 //------  массив матрицы клавш "Специалиста" ( true - нажата, false - отпущена)
  private  boolean speci_matr[][]= new boolean[12][6];//- 12 x 6 + Shift + Reset .

/**  Матрица клавиш 12х6. True - замкнуто, False - разомкнуто
--------------------------------------------------------------------------------------
      C3    C2    C1    C0    A7    A6    A5    A4    A3    A2    A1    A0  <--:ПОРТ:|
----------------------------------------------------------------------------      |  |
   | X_B | X_A | X_9 | X_8 | X_7 | X_6 | X_5 | X_4 | X_3 | X_2 | X_1 | X_0 |      |  |
----------------------------------------------------------------------------------^--|
Y5 |  F  |  F1 |  F2 |  F3 |  F4 |  F5 |  F6 |  F7 |  F8 | [X] | [ ] | [/] | 05 | B5 |
Y4 | + ; | ! 1 | " 2 | # 3 | $ 4 | % 5 | & 6 | ' 7 | ( 8 | ) 9 |   0 | = - | 04 | B4 |
Y3 | J Й | C Ц | U У | K K | E E | N Н | G Г | [ Ш | ] Щ | Z З | H Х | : * | 03 | B3 |
Y2 | F Ф | Y Ы | W В | A A | P П | R Р | O O | L Л | D Д | V Ж | \ Э | . > | 02 | B2 |
Y1 | Q Я | ^ Ч | S С | M M | I И | T T | X Ь | B Б | @ Ю | < , | ? / |  ЗБ | 01 | B1 |
Y0 | Р/Л |HOME |  Up |Down | ESC | TAB | SPС |  <= |  ПВ |  => |  ПС |  ВК | 00 | B0 |
------------------------------------------------------------------------------^------|
     0b    0a    09    08    07    06    05    04    03    02    01    00 <-X/Y      |
**/

  private String tmpstring = "";  //--- ВВЕЛИ ДЛЯ ОТЛАДКИ ---

//--- переопределим чтение памяти -------------------------
  public int peekb( int addr ) {
     if( addr < (PortBeg) )     //--- ПЗУ и ОЗУ Пользователя
       {
      //if( addr == (0xc037) ) {//--- Тест-перехват 0C037H - вывод симв.на экран [C]
          //--- String be = Integer.toHexString(_C) + "h, "; //---
          //--- String be = ""+(char)(_C) + ",";
          //--- tmpstring = tmpstring + be.toUpperCase();
          //---  tmpstring = tmpstring + be;
          //---  showMessage(tmpstring); //---
          //--- urlField.setVisible(true);   //--- show();
          //--- urlField.setText( tmpstring );
       //}
         return mem[ addr ];   //--- читаем байт
       }
    return inport( addr );     //--- возвращаем порт
  }
//-------- ввод из порта памяти 580ВВ55 -------------------------------------------------
public int inport( int addr ) {
 //-- все порты ППА перенесём в область 0xffe0 - 0xffe3 ;
   if( addr <= RgRYS ) {
     int tma = (addr & 0x0003) | 0xffe0 ;
        addr = tma;
     }

       int res = 0x00ff; //--- предварительно ни одна кнопка не нажата
        if( (addr > ROMEnd) && (addr < RgRYS) ) { //-- port 580BB55
           switch( addr ) {//--- разбираем по каналам...
             case PortA: {//--- Порт А
               if(PrtAIN) {//--- если порт A - на ввод
                  if(!PrtBIN) {//--- если порт B - на вывод
                     for( int i = 0; i < 6; i++ )  //--- по битам порта B от 2 до 7
                        {
                          for( int j = 0; j < 8; j++ )  //--- по битам порта A от 0 до 7
                             {//-- если такая нажата  и  такой бит порта B = 0, ставим бит A = 0
                               if( (speci_matr[j][i]) && ((mem[PortB] & msk[i + 2]) == 0) ) {res &= bit[j];}
                             }//---  for( int j)
                        }//---  for( int i)
                      return res; //---  возвращаем состояние порта A
                    }//------------- если порт B - на вывод  --- закончился ---
                   //------------- если порт B - на ввод то и делать нечего
                 }//----- если порт A - на ввод  --- закончился ---
                   return mem[PortA]; //---  порта A - на вывод <-- последнее записанное
                 }//------------ Порт А --- закончился ---

             case PortB: {  //--- Порт В
               if(PrtBIN) {//--- если порта В - на ввод

                  if(!PrtAIN) {//--- если порт A - на вывод
                     for( int i = 0; i < 8; i++ )  //--- по битам порта A от 0 до 7
                        {
                          for( int j = 0; j < 6; j++ )  //--- по битам порта В от 2 до 7
                             {//-- если такая нажата  и  такой бит порта A = 0, ставим бит В = 0
                               if( (speci_matr[i][j]) && ((mem[PortA] & msk[i]) == 0) ) {res &= bit[j+2];}
                             }//---  for( int j)
                        }//---  for( int i)
                    }//------------- если порт A - на вывод  --- закончился ---

                  if(!PrC0IN) {//--- если порт CLow - на вывод
                     for( int i = 0; i < 4; i++ )  //--- по битам порта CLow от 0 до 3
                        {
                          for( int j = 0; j < 6; j++ )  //--- по битам порта В от 2 до 7
                             {//-- если такая нажата  и  такой бит порта C = 0, ставим бит В = 0
                               if( (speci_matr[i+8][j]) && ((mem[PortC] & msk[i]) == 0) ) {res &= bit[j+2];}
                             }//---  for( int j)
                        }//---  for( int i)
                    }//------------- если порт C - на вывод  --- закончился ---

                    //------------- если порт C - на ввод то и делать нечего
                   //------------- если порт A - на ввод то и делать нечего

//-- КАКОЙ_ТО КОНФЛИКТ СО <CapsLock> - он работает наоборот --------------------------------
//-- <CpsLk> = Р/Л ЭТО ДАЁТ СБОЙ ЗДЕСЬ Т.К. ВЛИЯЕТ НА КОД КЛАВИШИ!!!  ----------------------
//-- в Мониторе "Shift" не влияет на клавишу. Влияет РУС/ЛАТ (НР.ФИКС) !!!
                  if(Shiftk){//-  ПОРТ РАБОТАЕТ ПРАВИЛЬНО ---
                             //-  showMessage( "Shiftk TRUE!!! = SMALL !!!" );
                             //-  res &= 0xfffd; //- выставим состояние Shift: B1 = 0
                            }
                             else {
                              res |= 0x0002; //- выставим состояние Shift: B1 = 1
                            }
          //--- char se = (char)e.key; //--- Test ---------
          //--- String s = "PORT : "+ Integer.toHexString(res).toUpperCase() +"h  ";
          //--- showMessage( s );
          //---urlField.setVisible(true);   //--- show();
          //---urlField.setText( s );  //--- Test ---------

                     return res; //---  возвращаем состояние порта В
                 }//----- если порта В - на ввод  --- закончился ---
                  return mem[PortB]; //---  порта В - на вывод <-- последнее записанное
                 }//------------ Порт В --- закончился ---

            default: {//--- Порт С orphaned default
               if(PrC0IN) {//--- если порта CLow - на ввод
                  if(!PrtBIN) {//--- если порт B - на вывод
                     for( int i = 0; i < 6; i++ )  //--- по битам порта B от 2 до 7
                        {
                          for( int j = 0; j < 4; j++ )  //--- по битам порта CLow от 0 до 3
                             {//-- если такая нажата  и  такой бит порта В = 0, ставим бит C = 0
                               if( (speci_matr[j+8][i]) && ((mem[PortB] & msk[i+2]) == 0) ) {res = res & bit[j];}
                             }//--- for( int j)
                        }//--- for( int i)
                      return res; //---  возвращаем состояние порта C
                    }//------------- если порт B - на вывод  --- закончился ---
                   //------------- если порт B - на ввод то и делать нечего
                 }//----- если порта CLow - на ввод  --- закончился ---
                  else {//----- если порта CLow - на вывод
                        return ((mem[PortC] & 0x000f) | 0x00f0);
                       }
                 }//--- Порт С --- закончился ---
//--ЗДЕСЬ - ПОДУМАТЬ И ПРОВЕРИТЬ!!!
           }//--  switch  --- закончился ---
          }//-- port 580BB55  --- закончился ---
           else
          { //-- остальные порты и РУС ВРЕМЕННО считаем ячейками памяти!
            res =  mem[ addr ];   //--- читаем байт памяти!
          }
//--- Тест обращения к порту 580ВВ55 ----------
//---      String raddr = Integer.toHexString(addr);
//---      String rByte = Integer.toHexString(res);
//--- if( addr == (0xffe0) ){ //---
//---         showMessage( "Port inpByte: " + raddr.toUpperCase() + "h = " + rByte.toUpperCase() +"h");  //--- showStatus
//---         showMessage( "Port inpByte: " + Integer.toHexString(addr).toUpperCase() +"h");
//---     }
//--- Тест обращения к порту 580ВВ55 ----------
    return(res);
  }
//--- для ПК "Специалист" ----------------------------------------------------------------

//-------- ввод из порта -----------------------------------------------------------------
//--- при вводе из порта клавиатуры записываем в ответ состояние переменных _B_SPC..._CAPS_V.
//--- в зависимости от выбраной "0" линии адреса порта 7F.FE ... FE.FE.
//--- сами значения переменных _B_SPC..._CAPS_V (строк матрицы) готовятся при обработке событий
//--- клавиатуры handleEvent(Event e): Event.KEY_PRESS - КЛАВИША_НАЖАТА_, Event.KEY_RELEASE: -
//--- КЛАВИША_ОТПУЩЕНА_. Эти события вызывают doKey( true/false, e.key, e.modifiers ), где
//--- код клавиши (e.key) и служебные клавиши (e.modifiers) превращаются в значения переменных
//--- _B_SPC..._CAPS_V.

//-------- ввод из порта -----------------------------------------------------------------
public int inb( int port ) {
       int res = 0xff; //-- начальное состояние порта - "все не нажаты"
       return(res);    //---*** для ПК "Специалист" ---
/**
      if ( (port & 0x0001) == 0 ) //-- port xx.FEh
         {  //--  в зависимости от старшего байта "пробиваем" 0xff через &
                                      //-- _int - переменные
      if ( (port & 0x8000) == 0 ) { res &= _B_SPC; } //-- 1000.0000.0000.0000  7F.FE
      if ( (port & 0x4000) == 0 ) { res &= _H_ENT; } //-- 0100.0000.0000.0000  BF.FE
      if ( (port & 0x2000) == 0 ) { res &= _Y_P;   } //-- 0010.0000.0000.0000  DF.FE
      if ( (port & 0x1000) == 0 ) { res &= _6_0;   } //-- 0001.0000.0000.0000  EF.FE
      if ( (port & 0x0800) == 0 ) { res &= _1_5;   } //-- 0000.1000.0000.0000  F7.FE
      if ( (port & 0x0400) == 0 ) { res &= _Q_T;   } //-- 0000.0100.0000.0000  FB.FE
      if ( (port & 0x0200) == 0 ) { res &= _A_G;   } //-- 0000.0010.0000.0000  FD.FE
      if ( (port & 0x0100) == 0 ) { res &= _CAPS_V;} //-- 0000.0001.0000.0000  FE.FE
         }
    return(res); */
  }

//---------- вывод в порт Spectrum --------------------------------------------------------
public void outb( int port, int outByte, int tstates ) {
//     return;    //---*** для ПК "Специалист" --- а можно и оставить ему бордюр. :)
    if( (port & 0x0001) == 0 ) {   //-- port xx.FEh
      newBorder = (outByte & 0x000f); //-- 0000.0111 бордюр & 0x07
    }
  }

//--- для ПК "Специалист" 580ВВ55 ---------------------------------------------------------
public void outPort( int port, int outByte ) {
 //-- все порты ППА перенесём в область 0xffe0 - 0xffe3 ;
   if( port <= RgRYS ) {
      int tmp = (port & 0x0003) | 0xffe0 ;
         port = tmp ;
   }

   int tmpByte = 0x00ff;
 //--  String saddr = Integer.toHexString(port);
 //--  String sByte = Integer.toHexString(outByte);
 //--- Тест вывода в порт 580ВВ55 ---
 //---     if( newByte == (130) ) { //--- =82h
 //---         showMessage( "Port outByte: " + saddr.toUpperCase() + "h = " + sByte.toUpperCase() +"h");
 //--- Тест  }

 //----------- Разбор  управляющего слова ППА 580ВВ55 -----------
   if( port == (RgRYS) ) { //-- РУС ---
     if( (outByte & 0x0080) != 0 ) { //---- управляющие слова 1-старший бит
        if( (outByte & 0x0001) != 0 ) { //-- КАНАЛ_С(МЛ.)(РС0-РС3)
           PrC0IN = true; //---------------- порт C0- на ввод
          }
           else{
                PrC0IN = false; //---------- порт C0- на вывод
                tmpByte = mem[PortC];
                mem[PortC] = tmpByte & 0x00f0;//- порт C0 = 0
               }
        if( (outByte & 0x0002) != 0 ) { //-- КАНАЛ_В(РВ0-РВ7)
           PrtBIN = true; //---------------- порт B - на ввод
          }
           else{
                PrtBIN = false; //---------- порт B - на вывод
                mem[PortB] = 0;//---------- порт B = 0
               }
        if( (outByte & 0x0008) != 0 ) { //-- КАНАЛ_С(СТ.)(РС4-РС7)
           PrC1IN = true; //---------------- порт C1- на ввод
          }
           else{
                PrC1IN = false; //---------- порт C1- на вывод
                tmpByte = mem[PortC];
                mem[PortC] = tmpByte & 0x000f;//- порт C1 = 0
               }
        if( (outByte & 0x0010) != 0 ) { //-- КАНАЛ_A(РА0-РА7)
           PrtAIN = true;   //---------------- порт A - на ввод
          }
           else{
                PrtAIN = false; //---------- порт A - на вывод
                mem[PortA] = 0; //---------- порт A = 0
               }
         mem[ port ] = outByte; //--- в ПОРТ RYC запишем YC -----  ПОРТЫ 0FFE3h
      return;
       }
        else { //------------- побитное управление портом 0xffe3 --------------------------.
             if(!PrC0IN) { //---------- если порт C0- на вывод
                if( (outByte & 0x0001) == 1) {//--- уст. в 1
                   if(((outByte & 0x000e)>>1) < 4) {//--- биты 0-3
                      tmpByte = mem[PortC];
                      mem[PortC] = tmpByte | msk[((outByte & 0x000e)>>1)]; //--- уст. в 1
                     }
                  }else{//------------------------- уст. в 0
                   if(((outByte & 0x000e)>>1) < 4) {//--- биты 0-3
                      tmpByte = mem[PortC];
                      mem[PortC] = tmpByte & bit[((outByte & 0x000e)>>1)]; //--- уст. в 0
                     }
                  }//------------------------- уст. в X кончилися -------------------------
               } //---------- порт C0- на вывод кончился -------------------------
             if(!PrC1IN) { //---------- если порт C1- на вывод
                if( (outByte & 0x0001) == 1) {//--- уст. в 1
                   if(((outByte & 0x000e)>>1) > 3) {//--- биты 4-7
                      tmpByte = mem[PortC];
                      mem[PortC] = tmpByte | msk[((outByte & 0x000e)>>1)]; //--- уст. в 1
                     }
                  }else{//------------------------- уст. в 0
                   if(((outByte & 0x000e)>>1) > 3) {//--- биты 4-7
                      tmpByte = mem[PortC];
                      mem[PortC] = tmpByte & bit[((outByte & 0x000e)>>1)]; //--- уст. в 0
                     }
                  }
               } //---------- порт C1- на вывод кончился -------------------------
            }//-- побитное управление портом 0xffe3 кончилось --------------------
     } //------------------------- остальные ПОРТЫ: в том числе и 0xfff8- цвет.
    mem[ port ] = outByte; //--- в остальные ПОРТЫ пишем в память: 0C000h < ПОРТЫ < 0FFFFh
  }

//---*** --- pokeb для ПК "Специалист" ---------------------------------------------
public void pokeb( int addr, int newByte ) {
   if( addr > (ScrEnd) )       //--- > 0xbfffh - выше Видео-ОЗУ = ПЗУ И ПОРТЫ.
     {
        //--- для ПК "Специалист" - ПОРТЫ ------------------------------------------
     if( addr < (HiMem) )      //--- <=0FFFFh - ПОРТЫ И ПЗУ
       {
       if( addr > (ROMEnd) )   //--- > 0FFDFh - ПОРТЫ
         {
          outPort(addr, newByte);//--- в ПОРТЫ пишем -----  0C000h < ПОРТЫ < 0FFFFh
          return;
         } //--- ПОРТЫ кончились.
          else{
               return; //--- в ПЗУ 0C000h-ROMEnd не пишем -----
         }
       }
        else{//--- addr вдруг больше 65536 (не может быть!) - укоротим его и продолжим.
              addr &= 0xffff;
       }
     }//--- далее - ОЗУ + Видео-ОЗУ ниже ПЗУ И ПОРТОВ.

   if( addr < ScrBeg )          //----- ОЗУ < 9000h -----
     {
      mem[ addr ] = newByte;    //--- в ОЗУ пишем -----  0000h < ОЗУ < 9000h
      return;                   //--- в ОЗУ пользователя пишем -----
     }//--- далее - только Видео-ОЗУ!

   if( mem[ addr ] != newByte ) //----- правильно! повторно в Видео-ОЗУ записывать глупо!
     {//---       newByte
      plot( addr, 0xffff );     //--- в Видео-ОЗУ рисуем --- newByte не используется в plot()
      mem[ addr ] = newByte;    //--- в Видео-ОЗУ пишем как в ОЗУ чтобы читать -----
     }
  }

//--------- Byte access - запись байта в ОЗУ ZX_Spectrum -----------------------------------
/**public void pokeb( int addr, int newByte ) {

   if( addr > (ATREnd) )        //--- 5B00h = 5800h + 300h; выше ОЗУ аттрибутов.
     {                          //--- ячейки ОЗУ Пользователя выше Видео-ОЗУ и аттрибутов.
     if( addr < (ROMBeg) )      //--- 5B00h < 0C000h ОЗУ Пользователя
       {
         mem[ addr ] = newByte; //--- в ОЗУ пишем -----  4000h < ОЗУ < 0C000h
         return;
       }
        //--- для ПК "Специалист" - ПОРТЫ ------------------------------------------
     if( addr > (ROMEnd) )       //--- > 0FFDFh - ПОРТЫ   {АПТЕКИ САРАТОВА} - sarbc.ru
       {
       if( addr < (HiMem) )      //--- <=0FFFFh - ПОРТЫ
         {
          outPort(addr, newByte);//--- в ПОРТЫ пишем -----  0C000h < ПОРТЫ < 0FFFFh
          return;
         }
        //--- addr = addr & 0xffff; addr
        return;
       }//--- ПОРТЫ кончились.
//--- System.out.println("Pressed... "+Integer.toHexString(KeyCode)+"h"); //--- тест ---
//--- showMessage( "ROM Detected: " + saddr.toUpperCase() + "h = " + sByte.toUpperCase() +"h");  //---
      return;
     } //--- для ПК "Специалист" -------------------------------------------------

//--- ZX_Spectrum ROM - для ПК "Специалист" заглушим -------------------
   if( addr < BASEnd )          //----- ПЗУ < 4000h -----
     {
      return;                   //----- в ПЗУ ZX_Spectrum не пишем -----
     }
//----------------------------------------------------------------------

   if( mem[ addr ] != newByte ) //----- правильно! повторно в Видео-ОЗУ записывать глупо!
     {
      plot( addr, 0xffff );     //--- в Видео-ОЗУ рисуем --- newByte не используется в plot()
      mem[ addr ] = newByte;    //--- в Видео-ОЗУ пишем как в ОЗУ чтобы читать -----
     }
  }   */

//--------- Word access - запись слова в ОЗУ ----------------------------------------------
/* public void pokew( int addr, int word ) {
   int _mem[] = mem;

   if( addr > (ATREnd) )            //--- 5B00h = 5800h + 300h;  23296D = 22528D+768D
     {                              //--- ячейки ОЗУ Пользователя
     if( addr < (ROMBeg) )          //--- < 0C000h ОЗУ Пользователя
       {
           _mem[ addr ] = word & 0xff; //--- младший байт - пишем в ОЗУ -----
       if( ++addr != ROMBeg ) { // != 49152 0C000h
           _mem[ addr ] = word >> 8;   //--- старший байт - пишем в ОЗУ -----
         }
        return;
       }
        //--- для ПК "Специалист" -------------------------------------------------
     if( addr > (ROMEnd) )       //--- > 0FFDFh - ПОРТЫ   {АПТЕКИ САРАТОВА} - sarbc.ru
       {
         //_mem[ addr ] = word & 0xff; //--- младший байт - пишем в ПОРТ -----
            outPort(addr, word & 0xff);
       if( ++addr != HiMem ) { // != 65536
         //_mem[ addr ] = word >> 8;   //--- старший байт - пишем в ПОРТ -----
            outPort(addr, word >> 8);
         }
          else{ //--- старший байт вышел за HiMem -> addr=0000h;
               addr &= 0xffff;
          //  _mem[ addr ] = word >> 8;   //--- старший байт - не пишем в ПЗУ -----
         }
        return;  //---  не пишем в ПЗУ -----
       }
        //--- для ПК "Специалист" -------------------------------------------------
      return;
     }

//--- ZX_Spectrum ROM --------------------------------------------------
   if( addr < BASEnd ) {         //----- ПЗУ BAS< 4000h -----
      return;                    //----- в ПЗУ не пишем -----
     }
//----------------------------------------------------------------------

  int newByte0 = word & 0xff;
   if( _mem[ addr ] != newByte0 )
     {
       plot( addr, 0xffff ); //--- в Видео-ОЗУ рисуем ---  newByte0
       _mem[ addr ] = newByte0;//--- в Видео-ОЗУ пишем как в ОЗУ-----
     }

  int newByte1 = word >> 8;
   if( ++addr != (0x5b00) ) //--- 5B00h = 5800h + 300h;  23296D = 22528D+768D
     {
     if( _mem[ addr ] != newByte1 ) {
         plot( addr, 0xffff ); //--- в Видео-ОЗУ рисуем ---  newByte1
         _mem[ addr ] = newByte1;
       }
    }
     else {
           _mem[ addr ] = newByte1;
    }
  }  */

//---*** --- pokew для ПК "Специалист" ---------------------------------------------
public void pokew( int addr, int word ) {
  int _mem[] = mem;
   if( addr > ScrEnd )       //--- > 0xbfffh - выше Видео-ОЗУ = ПЗУ И ПОРТЫ.
     {
        //--- для ПК "Специалист" - ПОРТЫ ------------------------------------------
     if( addr < HiMem )        //--- <=0FFFFh - ПОРТЫ И ПЗУ
       {
       if( addr > ROMEnd )   //--- > 0FFDFh - ПОРТЫ
         {
           outPort(addr, word & 0xff);//--- младший байт - пишем в ПОРТ -----
         if( ++addr != HiMem ) { // != 65536
           outPort(addr, word >> 8); //--- старший байт - пишем в ПОРТ -----
           return; //--- старший байт обслужили - уходим!
          }
           else{ //--- старший байт вышел за HiMem -> addr=0000h;
                addr &= 0xffff;
                _mem[ addr ] = word >> 8; //--- старший байт - пишем в ОЗУ -----
                return; //--- старший байт обслужили - уходим!
               }
         }//--- ( addr > ROMEnd )
       }//--- ( addr < HiMem )
        else{//--- addr вдруг больше 65536 (не может быть!) - укоротим его.
               addr &= 0xffff; // продолжим с новым адресом
            }
     }//--- далее - ОЗУ + Видео-ОЗУ ниже ПЗУ И ПОРТОВ.


   if( addr < ScrBeg )            //--- ОЗУ < 9000h -------
     {
      _mem[ addr ] = word & 0xff; //--- младший байт - пишем в ОЗУ -----
      if( ++addr != ScrBeg ) {    //--- если старший байт не попал в видео-ОЗУ.
         _mem[ addr ] =  (word >> 8) & 0xff; //--- старший байт - пишем в ОЗУ.
         return;
        }
         else{//--- старший байт попал в видео-ОЗУ.
          int newByte1 = (word >> 8) & 0xff; //--- второй байт слова word
           if(_mem[ addr ] != newByte1) //----- правильно! повторно в Видео-ОЗУ записывать глупо!
             {
              plot( addr, 0xffff );   //--- в Видео-ОЗУ рисуем --- newByte не используется в plot()
              _mem[ addr ] = newByte1;//--- в Видео-ОЗУ пишем как в ОЗУ чтобы читать -----
             }
              return;//--- старший байт обслужили - уходим!
             }
     }
     //--- далее - только Видео-ОЗУ!

  int newByte0 = word & 0xff;   //--- второй байт слова word
   if(_mem[ addr ] != newByte0) //----- правильно! повторно в Видео-ОЗУ записывать глупо!
     {
      plot( addr, 0xffff );   //--- в Видео-ОЗУ рисуем --- newByte не используется в plot()
      _mem[ addr ] = newByte0;//--- в Видео-ОЗУ пишем как в ОЗУ чтобы читать -----
     }

  int newByte1 = word >> 8;//--- второй байт слова word
   if( ++addr < (ROMBeg) ) //--- ScrBeg < ++addr < ROMBeg;
     {
     if( _mem[ addr ] != newByte1 ) {
         plot( addr, 0xffff ); //--- в Видео-ОЗУ рисуем ---  newByte1
         _mem[ addr ] = newByte1;
       }
     }
      else { //--- второй байт слова попал на ROM
           return; //--- в ROM не пишем -----
     }

  }

//------------------------------------------------------------------------------------------
  // Поскольку исполнение проходит как плотный цикл, некоторые реализации виртуальной машины
  // Java не позволяют любым другим процессам получить доступ. Это даёт (GUI) Графическому
  // Интерфейсу Пользователя время для обновления.
//------------------------------------------------------------------------------------------
  // Since execute runs as a tight loop, some Java VM implementations
  //  don't allow any other threads to get a look in. This give the
  //  GUI time to update. If anyone has a better solution please
  //  email us at mailto:spectrum@odie.demon.co.uk
  //--------------------------------------------------------------------
  public  int     sleepHack = 0;
  public  int     refreshRate = 1;  // refresh every 'n' interrupts

  private int     interruptCounter = 0;
  private boolean resetAtNextInterrupt = false;
  private boolean pauseAtNextInterrupt = false;
  private boolean refreshNextInterrupt = true;
  private boolean loadFromURLFieldNextInterrupt = false;

  public  Thread  pausedThread = null;
  public  long    timeOfLastInterrupt = 0;
  private long    timeOfLastSample = 0;

//------------------------------------------------------------------------------------------
private void loadFromURLField() {
  try{
      pauseOrResume();

      urlField.setVisible(false);//---  hide();
      URL  url = new URL( urlField.getText() );
      URLConnection snap = url.openConnection();

      InputStream input = snap.getInputStream();
      loadSnapshot( url.toString(), input, snap.getContentLength() );
      input.close();
     }
      catch ( Exception e ) {
//--- showMessage( "Error loading snapshot !!!");
      showMessage( e.toString() ); //--- Error loading
     }
  }

//-------------- прерывание ----------------------------------------------------------------
public final int interrupt() {

    if( pauseAtNextInterrupt )
      {
    // поле ввода url --------------
       urlField.setVisible(true);//---   show();

       pausedThread = Thread.currentThread();

    while ( pauseAtNextInterrupt ) {
//---***           showMessage( "Adam Davidson & Andrew Pollard" );
//---***
       if (!pbaron){
            showStats=true;
            progressBar.setVisible(true);//---   show();
          }
//---***  "©" = (char)(0x00a9)
            showMessage( "© 2011 Sam_Computers LTD");

       if ( refreshNextInterrupt ) //--- проверка флага - рисовать.
          {
            refreshNextInterrupt = false; //--- сбросим флаг рисовать.
            oldBorder = -1;//--- обновить Border ---
            paintBuffer(); //--- перерисовка экрана РИСУЕМ !!!
          }

       if ( loadFromURLFieldNextInterrupt )
          {
            loadFromURLFieldNextInterrupt = false;
            loadFromURLField();
          }
           else {
            try {
                 Thread.sleep( 500 ); //--- дали случиться внешним событиям ----------------
                }
                 catch( Exception ignored )
                      {
                      //--- Exception
                      }
                }
      }
      pausedThread = null;
   // поле ввода url --------------
      urlField.setVisible(false);//---  hide();
//---***
      if (!pbaron){
          showStats=false;
          progressBar.setVisible(false);//---   show();
         }
//---***
    }

    if( refreshNextInterrupt ) {
        refreshNextInterrupt = false;
        oldBorder = -1;//--- обновить Border ---
        //--- перерисовка экрана РИСУЕМ !!!
        paintBuffer(); //--- рисовать разрешили из repaint() <-- paint( Graphics g )
      }

    if( resetAtNextInterrupt )
       {
        showMessage( "Total RESET !!!");
        resetAtNextInterrupt = false;
        reset();
       }

    interruptCounter++;

    // Characters flash every 1/2 a second
//---***
/**    if ( (interruptCounter % 25) == 0 )
       {  // Этот оператор возвращает остаток от деления первого операнда на второй.
        refreshFlashChars(); //--- обновить мигающие символы ---
       } */

    //--- для ПК "Специалист" ---------------------------------
    if (!wfocus) //--- если потерян фокус - мигаем бордюром ---
       {
    if ( (interruptCounter % 200) == 0 ) // every 4 seconds of 'Spechard time'
       {  // Этот оператор возвращает остаток от деления первого операнда на второй.
        invfoc = !invfoc;
        if (invfoc){ //--- обновить мигающий бордюр ---
            outb( 254, 0x06, 0 ); //
           }
             else {
            outb( 254, 0x04, 0 ); //
           }
       }
       }

    // Update speed indicator every 2 seconds of 'Spechard time'
    if ( (interruptCounter % 100) == 0 )
       {
        refreshSpeed(); //--- обновить значение скорости эмуляции ---
       }

    // Refresh screen every interrupt by default
    // Обновлять экран каждое прерывание по умолчанию
    if ( (interruptCounter % refreshRate) == 0 )
       {
        screenPaint(); //------ вызывается из interrupt() ---------------------->>>
       }
       //--- возвращает текущее системное время в виде миллисекунд,
       //--- прошедших с 1 января 1970 года
       timeOfLastInterrupt = System.currentTimeMillis();

    // Trying to slow to 100%, browsers resolution on the system
    // time is not accurate enough to check every interrurpt. So
    // we check every 4 interrupts.

    if( (interruptCounter % 4) == 0 )
      {
      long durOfLastInterrupt = timeOfLastInterrupt - timeOfLastSample;
//---      showMessage(" Int = "+ durOfLastInterrupt);
           timeOfLastSample = timeOfLastInterrupt;
           //--- запомним текущее время, как предыдущее.
      if( !runAtFullSpeed && (durOfLastInterrupt < 40) )
        {
         try{
             Thread.sleep( 50 - durOfLastInterrupt ); //---*** 50
            }
             catch( Exception ignored )
                  {
                   //--- Exception
                  }
        }
      }
    //---------------------------------------------------------
    // This was put in to handle Netscape 2 which was prone to
    // locking up if one thread never gave up its time slice.
    if( sleepHack > 0 )
      {
       try
          {
           Thread.sleep( sleepHack );
          }
           catch ( Exception ignored ) {
                                       }
      }
    //----------------------------------------------------------
   return super.interrupt(); //--- вернули что-то int
  }

//------------------------------------------------------------------------------------------
public void pauseOrResume() {
      // Pause
    if( pausedThread == null ) {
        pauseAtNextInterrupt = true;
      }
      // Resume
       else {
        pauseAtNextInterrupt = false;
      }
  }

//---Spec1987 paint( Graphics g )-------------------------------------------------------------
//
 // Метод repaint используется для принудительного перерисовывания апплета.
 // Этот метод, в свою очередь, вызывает метод --------  update -------------
 // вызывается из класса "Spec1987" методом  paint( Graphics g ).
public void repaint() {
// System.out.println(" Happenned Repaint !");
//
    refreshNextInterrupt = true; // установили флаг - разрешить обновление экрана
  }

//================================== сброс =================================================
public void reset() {
      super.reset(); //--- reset() class Z80
//---***
   if(wfocus) {
      outb( 254, 0x02, 0 ); //
     }
      else {
      outb( 254, 0x06, 0 ); // White border on startup: port 0FEh ff
     }
//--- для ПК "Специалист" ---------------------------------
        Shiftk = false; //--- Shift не нажат
        PrtAIN = true; //--- порт A - на ввод
        PrtBIN = true; //--- порт B - на ввод
        PrC0IN = true; //--- порт C0- на ввод
        PrC1IN = true; //--- порт C1- на ввод
        Arrays.sort(ascii_keys); //--- сортируем массив ascii-массив кодов клавиш для поиска
        resetKeyboard();//--- все кнопки ненажаты
  mem[RgRYS] = 0x009b ;//--- порт RYC[0FFE3h] = 9Bh (все на ввод)
  mem[RgRGB] = 0x0020 ;//--- порт цвета - зелёный на черном.
//--- для ПК "Специалист" ---------------------------------
  }
 //----------------------------------------------------------------------------------
 // Пользуясь ключевыми словами static и final, можно определять внутри классов
 // глобальные константы. Внутри класса можно определять статические методы и поля
 // (с помощью ключевого слова static), которые будут играть роль глобальных методов
 // и данных. Если в базовом классе метод определен с ключевым словом {final}, его
 // нельзя переопределить в дочернем классе, созданном на базе данного метода.
 //----------------------------------------------------------------------------------
 // Screen stuff - метрики экрана
                                              // ширина бордюра ---
  public              int borderWidth = 20;   // absolute, not relative to pixelScale
  public static final int pixelScale  = 1;    // scales pixels in main screen, not border
//---***
  public static final int nPixelsWide = 384;  // точек по X 384 - Спец.; 256 - ZX;
  public static final int nPixelsHigh = 256;  // точек по Y 256 - Спец.; 192 - ZX;

  public static final int nCharsWide  = 48;   // 256/8=32 (Байт в строке); 48 - Спец.; 32 - ZX;
  public static final int nCharsHigh  =256;   // 192/8=24 строки в экране;256 - Спец.; 24 - ZX;
//---***
/**
  private static final int sat = 238;         // насыщенность ? (saturation)

  private static final Color brightColors[] = {// 16 цветов - массив ZX_Spectrum.
    //-------   R     G    B
    new Color(   0,   0,   0 ),  // 00 black   //---- яркие цвета
    new Color(   0,   0, sat ),  // 01 blue
    new Color( sat,   0,   0 ),  // 02 red
    new Color( sat,   0, sat ),  // 03 magenta
    new Color(   0, sat,   0 ),  // 04 green
    new Color(   0, sat, sat ),  // 05 cyan
    new Color( sat, sat,   0 ),  // 06 yellow
    new Color( sat, sat, sat ),  // 07 white
        Color.black, //----------//-08------------ тусклые цвета
        Color.blue,              // 09
        Color.red,               // 0A
        Color.magenta,           // 0B
        Color.green,             // 0C
        Color.cyan,              // 0D
        Color.yellow,            // 0E Возрастанию индекса соответствует
        Color.white              // 0F убывание цвета... В SP_580 - наоборот
  }; */

//--- для ПК "Специалист" ----------------------------------------------------------------
  private static final int str = 238;         // насыщенность (saturation) 0EEh
  private static final Color SpecMXColors[] = {// 16 цветов - массив цветов "Специалист"
        Color.black, //----------//-00-черный----- тусклые цвета
        Color.blue,              // 01 синий
        Color.green,             // 02 зелёный
        Color.cyan,              // 03 бирюзовый
        Color.red,               // 04 красный
        Color.magenta,           // 05 фиолетовый
        Color.yellow,            // 06 коричневый
        Color.white,             // 07 белый
    new Color(   0,   0,   0 ),  // 08 серый   //---- яркие цвета
    new Color(   0,   0, str ),  // 09 голубой
    new Color(   0, str,   0 ),  // 0A светло-зелёный
    new Color(   0, str, str ),  // 0B светло-бирюзовый
    new Color( str,   0,   0 ),  // 0C розовый
    new Color( str,   0, str ),  // 0D светло-фиолетовый
    new Color( str, str,   0 ),  // 0E желтый
    new Color( str, str, str )   // 0F ярко-белый
    //-------   R     G    B
  };
//--- для ПК "Специалист" ----------------------------------------------------------------

  // 4000h = 16384 - начало экрана;  6144 байт (1800h) - размер экрана
  // 32 байта в сроке * 192 строки = 1800 смещение 1 аттрибута = 5800h-4000h (22528)
  private static final int firstAttr = (nPixelsHigh*nCharsWide); // 768 байт аттрибутов(300h)

  // адрес 1 аттрибута + 24 строки * по 32 символа = адрес последнего аттрибута
  // 5B00h (23296) - конец аттрибутов               24 строки  32 символа
  private static final int lastAttr  = firstAttr + firstAttr;  // для "Специалист"
  //---*** (nCharsHigh*nCharsWide);

//--------------------- first screen line in linked list to be redrawn
//---         первая строка экрана в списке указателей, для перерисовки
  private int first = -1;

//--------------------- first attribute in linked list to be redrawn
//---         первый атрибут в списке указателей, для перерисовки
  private int FIRST = -1;

//--- массив БАЙТ ЭКРАНА[]    192 точек по Y + 24 строки * 32 Байт в строке
  private final int lastByte[] = new int[ (nPixelsHigh+nCharsHigh)*nCharsWide ];
//--- 192 точек по Y * 32 Байт в строке = 6144 байта в области экрана
//--- 24 строки символов * 32 Байт в строке = 768 байт аттрибутов(300h)
//--- 6144 байта в области экрана + 768 байт аттрибутов = 6912 в буфере

//---        массив смещений [] = 6912 в буфере - ФАКТИЧЕСКИ АДРЕСА В ЭКРАНЕ
  private final int nextAddr[] = new int[ (nPixelsHigh+nCharsHigh)*nCharsWide ];

  public int newBorder = 6;  // White border on startup  0x0111b

  public int oldBorder = -1; // -1 mean update screen

  public long oldTime = 0;
  public int oldSpeed = -1; // -1 mean update progressBar
  public int newSpeed = 0;
  public boolean showStats = true;
  public String statsMessage = null;

  private static final String cancelMessage = new String( "Click here at any time to cancel sleep" );

  private boolean flashInvert = false;

//-------------------------------------------------------------------------------------------
public final void refreshSpeed() {
  long newTime = timeOfLastInterrupt;

    if ( oldTime != 0 )
       {
        newSpeed = (int) (200000.0 / (newTime - oldTime));
       }

         oldTime = newTime;

    if ( (statsMessage != null) && (sleepHack > 0) && (statsMessage != cancelMessage) )
       {
        showMessage( cancelMessage ); //--- "Click here at any time to cancel sleep"
       }
       else
          {
           showMessage( null );
          }
  }

//-------------------------------------------------------------------------------------------
/** private final void refreshFlashChars() { //--- мерцание символов ---- Специалисту не нужно
    flashInvert = !flashInvert;
    //---        по области аттрибутов цвета ----
    for( int i = firstAttr; i < lastAttr; i++ ) {

      int attr = mem[ i + ScrBeg ];  //--- текущий просматриваемый аттрибут: attr

      if ( (attr & 0x80) != 0 ) { //-------- если в attr установлен бит мерцания, то: ---
            lastByte[i] = (~attr) & 0xff; //--- инвертировать цвет и выделить только байт из int
        //              ^--- побитовое унарное отрицание аттрибута (NOT)

        // Only add to update list if not already marked.
        // Только добавить в список коррекции если ещё не было выделено.
      if ( nextAddr[i] == -1 ) { //--- если указано: обновить
           nextAddr[i] = FIRST;  //--- то указать смещение ????????????
             FIRST = i;      //--- FIRST присвоить смещение в области аттрибутов
        }//--- nextAddr[i] ---
      }//--- if ( (attr & 0x80) ---
    }//--- for( int i = firstAttr ---
  }  */

//---- стартовый refresh экрана ------------
public final void refreshWholeScreen()
  {//-   первый раз вызывается из Spec1987.class после вызова reset();
//---    showMessage( "Drawing Off-Screen Buffer" );
//---      от  0  до  5800Н
 for ( int i = 0; i < firstAttr; i++ )
     {//  [ 0 ] = -1, 0, 1 ...  firstAttr - 1
      nextAddr[ i ] = i - 1; //- ВСЕ смещения в области экрана: -1, 0, 1 ...  firstAttr - 1
      lastByte[ i ] = (~mem[ i + ScrBeg ]) & 0xff; //--- ВСЕ инвертированные байты из видео-ОЗУ.
    //lastByte[ i ] = (mem[ i + 16384 ]) & 0xff;
     }
     first = firstAttr - 1; //--- последнее смещение байта буфера экрана = 57FFH  != -1 !!!

      //-----   по области аттрибутов ----
//---***
//for ( int i = firstAttr; i < lastAttr; i++ )
//---***
  for ( int i = 0; i < firstAttr; i++  ) //--- у "Специалист" область аттрибутов отдельная.
      {
       nextAddr[ i + firstAttr ] = -1; //--- во все адреса аттрибутов заносим -1.
//---***                 буфер аттрибутов
//     lastByte[ i ] = mem[ i + ScrBeg ]; //--- в буфер аттрибутов - байты из ОЗУ аттрибутов.
//---***
       lastByte[ i + firstAttr ] = rgb[ i ];
      }
      FIRST = -1; //-------------- признак обновления

    oldBorder = -1; //--- -1 mean update screen, newBorder - текущий цвет Border.
    oldSpeed  = -1; //--- update progressBar
  }

//------ запись в видео-ОЗУ ----------------------
private final void plot( int addr, int newByte )   // график ?
  {//---        addr в видео-ОЗУ;      newByte - байт ОЗУ, он здесь не используется...
   int offset = addr - ScrBeg;  //----- смещение в видео-ОЗУ: (адрес в ОЗУ) - 4000h

    if( nextAddr[ offset ] == -1 ) //----- если по ЭТОМУ адресу в видео-ОЗУ есть признак ОБНОВИТЬ,
      {
//--- для ПК "Специалист" ----------------------------------------------------------------
        rgb[ offset ] = mem[ RgRGB ]; //--- по смещению видео-ОЗУ пишем код в ОЗУ цвета
//--- для ПК "Специалист" ----------------------------------------------------------------
//---*** ЗДЕСЬ ВРОДЕ КАК ВСЁ ПРАВИЛЬНО ---
     if( offset < firstAttr )  //----- если ЭТОТ адрес в видео-ОЗУ, а не в аттрибутах,
       {
        nextAddr[ offset ] = first;//----- указали в буфере смещений по ЭТОМУ адресу: first
        first = offset;        //----- first присвоили значение ЭТОГО адреса смещения в экране
       }
        else                   //----- адрес в ОЗУ аттрибутов:
       {
        nextAddr[ offset ] = FIRST;//-----
        FIRST = offset; //----- значит обработается в screenPaint()
       }
     }
  }

//-------------------------------------------------------------------------------------------
public final void borderPaint()//----- может понадобиться в Специалист для фокуса экрана
  {
   if( oldBorder == newBorder )//--- если признак равен цвету Border - ничего не делать!
     {
      return;
     }

       oldBorder = newBorder; //--- иначе - присвоить признаку цвет Border

   if( borderWidth == 0 ) //--- если бордюра нет - ничего не делать! ---
     {
      return;
     }
  //--- если бордюр есть - перерисовать его цветом  newBorder;
  // parentGraphics.setColor( brightColors[ newBorder ] );
     parentGraphics.setColor( SpecMXColors[ newBorder ] );

  //   обновить Border - это нагло нарисовать прямоугольник цвета newBorder?!!!  Font.PLAIN|Font.BOLD
     parentGraphics.fillRect( 0, 0, (nPixelsWide*pixelScale) + borderWidth * 2,
                                    (nPixelsHigh*pixelScale) + borderWidth * 2 );
   if(wfocus) {
     parentGraphics.setFont( urlField.getFont()); //-  установить шрифт.
     parentGraphics.setColor( SpecMXColors[ 0x01 ] );
     parentGraphics.drawString("'Specialist'", 24, 16);
     parentGraphics.setColor( SpecMXColors[ newBorder ] );  /** */
     }
      else {
     parentGraphics.setFont( urlField.getFont()); //-  установить шрифт.
     parentGraphics.setColor( SpecMXColors[ 0 ] );
     parentGraphics.drawString("'Specialist' - old Russian 8-bit computer emulator. (Click here to test it.)", 24, 16);
     parentGraphics.setColor( SpecMXColors[ newBorder ] );  /** */
     }

  }
//-------------------------------------------------------------------------------------------

  private static final String fullSpeed = "Full Speed: ";
  private static final String slowSpeed = "Slow Speed: ";
  public boolean  runAtFullSpeed = true;

//-------------------------------------------------------------------------------------------
private final void toggleSpeed() {
    runAtFullSpeed = !runAtFullSpeed;
    showMessage( statsMessage ); //---
  }

//-------------------------------------------------------------------------------------------
public void showMessage( String m ) {
    statsMessage = m;
    oldSpeed = -1; // Force update of progressBar
    statsPaint();
  }

//-------------------------------------------------------------------------------------------
public final void statsPaint() {
  if( oldSpeed == newSpeed ) {
      return;
    }
      oldSpeed = newSpeed;

  if( (!showStats) || (borderWidth < 10) ) {
      return;
    }

        String stats = statsMessage;
    if( stats == null ) {
        String  speedString = runAtFullSpeed ? fullSpeed : slowSpeed;

      if( newSpeed > 0 ) {
          stats = speedString + String.valueOf( newSpeed ) + "%";
        }
         else {
         stats = "Speed: calculating";
        }
      if( sleepHack > 0 ) {
          stats = stats + ", Sleep: " + String.valueOf( sleepHack );
        }
      }
    progressBar.setText( stats );
    progressBar.setVisible(true);//---   show();
  }

//===========================================================================================
public static synchronized Image getImage( Component comp, int attr, int pattern ) {
try {//---                 аттрибут, ниббл = 4 бита.
     return tryGetImage( comp, attr, pattern );
    }
     catch ( OutOfMemoryError e ) //--- может и не хватить памяти на все нибблы...
    {
        imageMap = null;          //--- в таком случае обнулим imageMap
      patternMap = null;          //---                обнулим patternMap

      System.gc();                //--- подчистим мусор после ошибки

//    HashTable — это подкласс Dictionary, являющийся конкретной реализацией словаря.
//    Представителя класса HashTable можно использовать для хранения произвольных объектов,
//    причем для индексации в этой коллекции также годятся любые объекты.

  //- после ошибки придётся заново создать patternMap
      patternMap = new Hashtable();
  //- и заново создать imageMap       // <10987654321<
  //---***
        imageMap = new Image[ 1<<12 ];// 100000000000b = 2048d = 800h  Image[ 1<<11 ]; для "ZX"
  //- и попытаемся создать с теми-же параметрами Image...
     return tryGetImage( comp, attr, pattern );
    }
  }

//---  создали первоначально словарь patternMap
public static Hashtable patternMap = new Hashtable();
//---  создали первоначально массив рисунков в 2048 экземпляров
//---***
public static Image imageMap[] = new Image[ 1<<12 ];// 100000000000b = 2048d = 800h
               // 7 bits for attr, 4 bits for pattern = 11 bits --> 2^11=2048 элементов
//- "Специалист": 8 bits for attr, 4 bits for pattern = 12 bits --> 2^12=4096 элементов

//===========================================================================================
private static Image tryGetImage( Component comp, int attr, int pattern )
  {
//---***
//--- для ПК "Специалист" ----------------------------------------------------------------
   int    ink = ((attr>>4) & 0x000f); //--- старший ниббл - цвет (ink)
   int    pap = ((attr   ) & 0x000f); //--- младший ниббл - фон  (paper)
//--- для ПК "Специалист" ----------------------------------------------------------------

//--- для ZX_Spectrum -----------------------------------------------
// int bright = ((attr>>3) & 0x08);          //--- бит яркости
// int    ink = ((attr   ) & 0x07) | bright; //--- цвет (ink)
// int    pap = ((attr>>3) & 0x07) | bright; //--- фон  (paper)
//-------------------------------------------------------------------
   int hashValue = 0;
//---***                  ЗДЕСЬ ВСЁ ПРАВИЛЬНО !!!
   for( int i = 0; i < 4; i++ ) //--- побитно просматриваем ниббл из ОЗУ экрана
      {
        int col = ((pattern & (1 << i)) == 0) ? pap : ink; //--- присваиваем цвета

        hashValue |= (col << (i << 2)); //---? ? ?
      }//         or
//  Что же касается данных базовых типов, если вам нужно передавать на них ссылки,
//  то следует заменить базовые типы на соответствующие   замещающие классы.
//  Например, вместо типа int используйте класс Integer, вместо типа long - класс Long
//  и так далее.
//  Инициализация таких объектов должна выполняться с помощью конструктора,
//  как это показано ниже:

    Integer imageKey = new Integer( hashValue );
//           ^ это будет ссылкой на объект( hashValue ).

//                             возвращает запись, соответствующую ключу( imageKey )
         Image image = (Image) patternMap.get( imageKey );

    if ( image == null ) { //--- если готового image в словаре нет, сделаем его...
//---*** СМЕНИЛИ ПАЛИТРУ --------------
//       Color colors[] = brightColors;
//--- для ПК "Специалист" ----------------------------------------------------------------
         Color colors[] = SpecMXColors;
//--- для ПК "Специалист" ----------------------------------------------------------------
         image = comp.createImage( 4, 1 ); //--- создали Image 4 х 1 точек: ниббл.
         Graphics g = image.getGraphics(); //--- создали для него графический контент

     for( int i = 0; i < 4; i++ ) { //--- по 4-м точкам ниббла:
          int col = ((pattern & (1 << i)) == 0) ? pap : ink; //--- вычисляем цвет.

        g.setColor( colors[ col ] ); //--- выставляем для точки цвет из  SpecMXColors[];
      //  setColor(Color c);
        g.fillRect( (3 - i), 0, 1, 1 ); //--- ставим точку на графический контент
      //  fillRect(int x, int y, int width, int height);
      // public abstract void fillRect(int x, int y, int width, int height);
      }
      //Hashtable добавляем запись: ключ(imageKey=Integer( hashValue )),значение (image)
      patternMap.put( imageKey, image ); //--- положим image в словарь под ключом imageKey
    }
    return image; //--- вернём созданный или считанный image
  }

//--------------- Отрисовка экрана в буфере -------------------------------------------------
// Основная идея - как можно меньше РИСОВАТЬ, для этого отслеживаются изменения и
// ОТРИСОВЫВАЮТСЯ только они.
//===========================================================================================
public final void screenPaint() //---------------------- вызывается из interrupt()
  {
   int addr = FIRST; //--- часто = -1 -> не обновляем...
                     //--- в первый заход FIRST = -1 => цикл  while( addr >= 0 ) пропускаем.
//--- тест ---
//if( addr >= 0 ) {
//   showMessage( "addr = " + Integer.toHexString(addr + 16384).toUpperCase() +"h;");
//  }
//--- тест ---
//--- СНАЧАЛА ИЗМЕНИМ ВСЕ АТТРИБУТЫ ЦВЕТА ----
 //------------------------ Update attribute affected pixels
 while( addr >= 0 ) { //--- если FIRST = -1 то и не делаем ничего.

//---***
//    int oldAttr = lastByte[ addr ]; //--- байт из буфера байтов по смещению "FIRST"
//    int newAttr = mem[ addr + ScrBeg ]; //--- байт из ОЗУ аттриб.по смещению "FIRST"
//   lastByte[ addr ] =  newAttr; //--- байт из буфера байтов заменим байтом из ОЗУ аттриб.
//---***
      int oldAttr = lastByte[ addr + firstAttr ];// байт из буфера байтов по смещению "FIRST"
      int newAttr = rgb[ addr ]; //--- байт из ОЗУ аттриб.по смещению "FIRST"
     lastByte[ addr + firstAttr ] =  newAttr; //--- байт из буфера байтов заменим байтом из ОЗУ аттриб.

 //---***                        Бит 6 - бит яркости. цвета фона и чернил - высокой яркости.
 //---                              0100.0111b Биты 2, 1, 0 цвет чернил: зелёный,красный,синий.
 //   boolean inkChange    = ((oldAttr & 0x47) != (newAttr & 0x47));
 //---                              0111.1000b Биты 5, 4, 3 цвета фона:  зелёный,красный,синий.
 //   boolean papChange    = ((oldAttr & 0x78) != (newAttr & 0x78));
 //---                              1000.0000b Бит 7 - признак мерцания.
 //   boolean flashChange  = ((oldAttr & 0x80) != (newAttr & 0x80));
 //---***
      boolean inkChange    = ((oldAttr & 0x00f0) != (newAttr & 0x00f0)); //--- цвет чернил
      boolean papChange    = ((oldAttr & 0x000f) != (newAttr & 0x000f)); //--- цвет фона


 //--- отличие ПК "Специалист" в том, что аттрибут = код цвета нельзя изменить отдельно от
 //--- байта графики. Они всегда пишутся синхронно. Код цвета выставляется в порт 0xfff8h и
 //--- запись байта по адресу в ОЗУ экрана запишет цвет по этому же адресу в ОЗУ цвета.
 //--- Код цвета единожды выставленный красит все байты экрана. Так что изменений ink и
 //--- paper может при записи и не быть. (Вероятно надо следить за изменением 0xfff8h.)
 //---***
    if( inkChange || papChange ) { //--- если случились изменения:   || flashChange
 //---***     boolean allChange = ((inkChange && papChange) || flashChange); //---***
        //--- изменения:       чернил и фона       или мерцания
        //-------------------------------------------------------------------------------------
        //--- экранный адрес точки Специалист вычисляется по адресу памяти из след.соотношения:
        //--- если адрес как А: 15 14 13 12 11 10  9  8__7  6  5  4  3  2  1  0 ; то
        //---                  CS1CS0 X8 X7 X6 X5 X4 X3|Y7 Y6 Y5 Y4 Y3 Y2 Y1 Y0
        //--- где X2...X0 - координаты бита в байте;
        //---     X7...X3 - координаты байта в строке: 2^6=64 возможных байта в строке,
        //---               из которых отображаем только 48 байт: 00H - 2Fh.
        //---     Y7...Y0 - координаты строки: 2^8=256, (00h — верх, 0FFh=255 — низ);
        //---    CS1 -CS0 - область памяти, где расположен экран: 9000h - текущий.
        //-------------------------------------------------------------------------------------
        //--- экранный адрес точки ZX_Spec вычисляется по адресу памяти из след.соотношения:
        //--- если адрес как А: 15 14 13 12 11 10  9  8__7  6  5  4  3  2  1  0 ; то
        //---                   CS 01 00 y7 y6 Y2 Y1 Y0 y5 y4 y3|X7 X6 X5 X4 X3 |
        //--- где X2...X0 - координаты бита в байте;
        //---     X7...X3 - координаты байта в строке: 2^5=32 байта в строке;
        //---     Y7...Y0 - координаты строки: 2^8=256, но только (0 — верх, 0BFh=191 — низ);
        //---          CS - область памяти (=0 — 4000h...5AFFh, =1 — C000h...DAFFh).
        //-------------------------------------------------------------------------------------
        //---  Соответствие битов адреса в области атрибутов координатам экрана
        //--- если адрес как А: 15 14 13 12 11 10  9  8__7  6  5  4  3  2  1  0 ; то
        //---                   CS  1  0  1  1  0 Y7 Y6 Y5 Y4 Y3 X7 X6 X5 X4 X3
        //--- где X7...X3 — биты горизонт. координаты (0 — левая сторона, 0FFh — правая);
        //---     Y7...Y3 — биты вертикальной координаты (0 — верх, 0BFh — низ);
        //--- Так как атрибут покрывает целое поле 8?8, то 3 младших бита координаты
        //--- X и Y не используются.
        //---  это адрес аттрибута, а надо попасть в первую строку его знакоместа.
        //--- ***
        //int scrAddr   = ((addr & 0x0300) << 3) | (addr & 0xff);
          int scrAddr   = ( addr );

        //---                  54321098.76543210    .765_43210
        //---                  00000011.00000000   Y.543_76543—X
        //---                       Y76 - старшая координата аттрибута
        //---             3 << 00011000.00000000 = 00011000.543_76543
        //---                    Y76 - сдвинута в старшую координату экрана.
//---***
/**   for ( int i = 8; i != 0; i-- ) {//--- 1 аттрибут - на 8 байт в столбик. Вносим изменения

       if ( allChange ) //--- если изменения в аттрибутах
          {
            lastByte[ scrAddr ] = (~(mem[ scrAddr + ScrBeg ]) & 0xff); //---где моргает курсор
          }
           else //---
          {
               int oldPixels = lastByte[ scrAddr ]; //--- байт из буфера байтов по смещению ""
               int newPixels = mem[ scrAddr + ScrBeg ]; //--- байт из ОЗУ аттриб.по смещению ""
               int   changes = oldPixels ^ newPixels; //--- changes = 0, если они одинаковы...
                  //---                 XOR
              if ( inkChange ) //--- если изменения чернил:
                 {
                   changes |=  newPixels;
                 }
                  else //--- если не менялись чернила?
                 {
                   changes |= ((~newPixels) & 0xff);
                 }//inkChange

              if ( changes == 0 )  //--- если изменений нет - просто продолжаем...
                 {
                   scrAddr += 256; //--- = 0FFH - сдвиг к следующей строке знакоместа;
                   continue;       //--- продолжаем цикл for () - всё ниже в цикле не делаем.
                 }
            lastByte[ scrAddr ] = changes ^ newPixels;
          } //--- if ( allChange )

          if ( nextAddr[ scrAddr ] == -1 )
             {
               nextAddr[ scrAddr ] = first;
              first = scrAddr;
             }
          scrAddr += 256; //--- 256 = 0FFH;
        }//--- for ( int i = 8 ...) --------------- конец цикла по знакоместу, а у нас их нет!
//---***  */

//---*** ПЕРВЫЙ ЗАПУСК - ИЗБРАЖЕНИЕ ЕСТЬ !!!
         int oldPixels = lastByte[ scrAddr + firstAttr ]; //--- байт из буфера байтов по смещению ""
         int newPixels = rgb[ scrAddr ]; //--- байт из ОЗУ аттриб.по смещению ""
         int   changes = oldPixels ^ newPixels; //--- changes = 0, если они одинаковы...
          if( inkChange ) //--- если изменения чернил:
                 {
                   changes |=  newPixels;
                 }
                  else //--- если не менялись чернила?
                 {
                   changes |= ((~newPixels) & 0xff);
                 }//inkChange

              if ( changes != 0 )  //--- если изменений нет - просто продолжаем...
                 {
                    lastByte[ scrAddr + firstAttr ] = changes ^ newPixels;
               if ( nextAddr[ scrAddr + firstAttr ] == -1 )
                  {
                    nextAddr[ scrAddr ] = first; //---***   + firstAttr  ????
                      first = scrAddr;
                  }
                 }

      } //--- if( inkChange || papChange )

      int newAddr = nextAddr[ addr ];
             nextAddr[ addr ] = -1;
             addr = newAddr;
    } //---  while( addr >= 0 )
    FIRST = -1;

//------------------- Only update screen if necessary
    if ( first < 0 ) //--- =-1
       {
        return;
       }
//--- ДАЛЕЕ ИЗМЕНИМ ВСЕ НУЖНЫЕ ПИКСЕЛИ ЭКРАНА ----
//------------------- Update affected pixels - ПИКСЕЛЫ ПОХОЖЕ ОБРАБАТЫВАЮТСЯ ВЕРНО!!!
        addr  = first;  //--- в первый заход first = адресу последнего байта экрана...
while ( addr >= 0 ) //--- !=-1
      {
      int oldPixels = lastByte[ addr ];   //--- байт из буфера байтов по смещению "first"
      int newPixels = mem[ addr + ScrBeg ]; //--- байт из видео-ОЗУ по смещению "first"
      int changes = oldPixels ^ newPixels; //--- changes = 0, если они одинаковы...
      //---                   ^-XOR

       lastByte[ addr ] = newPixels; //--- в буфер байтов по смещению "first" запишем -
                                     //--- байт из видео-ОЗУ по смещению "first"
      //-------------------------------------------------------------------------------------
      //--- экранный адрес точки Специалист вычисляется по адресу памяти из след.соотношения:
      //--- если адрес как А: 15 14 13 12 11 10  9  8__7  6  5  4  3  2  1  0 ; то
      //---                  CS1CS0 X8 X7 X6 X5 X4 X3|Y7 Y6 Y5 Y4 Y3 Y2 Y1 Y0
      //--- где X2...X0 - координаты бита в байте;
      //---     X7...X3 - координаты байта в строке: 2^6=64 возможных байта в строке,
      //---               из которых отображаем только 48 байт: 00H - 2Fh.
      //---     Y7...Y0 - координаты строки: 2^8=256, (00h — верх, 0FFh=255 — низ);
      //---    CS1 -CS0 - область памяти, где расположен экран: 9000h - текущий.
      //-------------------------------------------------------------------------------------

      //- выделим из адреса памяти координату X = А4...А0
//---***
//    int x = ((addr & 0x1f) << 3); //--- сдвиг на 3 = * 8 (т.к. x2...x0 - адрес бита в байте)
//---***
      int x = ((addr & 0x3f00) >> 5); //- сдвиг на 5 (т.к. x2...x0 - адрес бита в байте)
//                    0011.1111.0000.0000
//                    0000.0001.1111.1000

      //- выделим из адреса памяти координату Y:
//---***
//    int y = (((int)(addr&0x00e0))>>2)+(((int)(addr&0x0700))>>8)+(((int)(addr&0x1800))>>5);
      //-                0000.0000.1110.0000 >>2 = 0000.0000.0011.1000 = 0000.0000.0054.3000
      //-                0000.0111.0000.0000 >>8 = 0000.0000.0000.0111 = 0000.0000.0000.0210
      //-                0001.1000.0000.0000 >>5 = 0000.0000.1100.0000 = 0000.0000.7600.0000
      //- полный экранный адрес по координате Y: = 0000.0000.7654.3210
      int y = ((int)(addr&0x00ff));

      int X = (x * pixelScale); //--- Учтём_
      int Y = (y * pixelScale); //--- масштаб.

//---***
      //------------------------------------ адрес 1 аттрибута 22528
//    int attr = mem[ ATRBeg + (addr & 0x1f) + ((y >> 3) * nCharsWide) ];
      //---           5800h        0001.1111b
      int attr = rgb[ addr ];

//---***
      //------------------------------------ Swap colors around if doing flash
//   if ( flashInvert && ((attr & 0x80) != 0) )
//      {
//       newPixels = (~newPixels & 0xff); //--- байт из видео-ОЗУ по смещению "first"
//      } //------------------------------- у "Специалиста" этого нет.

      //------------------------------------ Redraw left nibble if necessary
     if ( (changes & 0xf0) != 0 ) //--- если есть изменения в старшем ниббле:
        {//- старший ниббл байта сдвинули в младший - 0000.0000bbbb
         int newPixels1 = (newPixels & 0xf0)>>4;
         //- аттрибуты кроме мерц. сдвинули выше младшего ниббла 0aaa.aaaa.0000
         int imageMapEntry1 = (((attr & 0x7f)<<4) | newPixels1);
         //- получили хитрый индекс: 0aaa.aaaabbbb
       Image image1 = imageMap[ imageMapEntry1 ]; //--- по индексу ищем image1

         if( image1 == null ) //--- если такого image1 нет
           { //--- получим новый:    аттрибут, младший ниббл
             image1 = getImage( parent, attr, newPixels1 ); //--- новый image1
             imageMap[ imageMapEntry1 ] = image1; //--- занесём в массив imageMap
           } //--- похоже - это убыстряет всё; если есть рисунок полубайта, то
             //--- берём из массива (это быстро), если нет - создадим его, но в
             //--- другой раз - не создаём, а быстро берём из массива.

        //--- Метод paint использует drawlmage с четырьмя аргументами:
        //--- это ссылка на изображение art, координаты левого верхнего угла рисунка х, у
        //--- и объект типа ImageObserver.
         bufferGraphics.drawImage( image1, X, Y, null ); //--- рисуем старшие нибблы байта
        }

      //------------------------------------- Redraw right nibble if necessary
     if ( (changes & 0x0f) != 0 ) //--- если есть изменения в младшем ниббле:
        {//------------- выделили младший ниббл байта - 0000.0000bbbb
        int newPixels2 = ( newPixels & 0x0f);
        //- аттрибуты кроме мерц. сдвинули выше младшего ниббла 0aaa.aaaa.0000
        int imageMapEntry2 = (((attr & 0x7f)<<4) | newPixels2);
        //- получили хитрый индекс: 0aaa.aaaabbbb
      Image image2 = imageMap[ imageMapEntry2 ]; //---  по индексу ищем image2

       if ( image2 == null ) //--- если такого image2 нет
          { //--- получим новый:    аттрибут, младший ниббл
            image2 = getImage( parent, attr, newPixels2 ); //--- новый image2
            imageMap[ imageMapEntry2 ] = image2; //--- занесём в массив imageMap
          }//--- похоже - это убыстряет всё; рисунок есть - берём из массива,
           //--- если нет - создадим его, но в другой раз - быстро берём из массива.

        //--- Метод paint использует drawlmage с четырьмя аргументами:
        //--- это ссылка на изображение art, координаты левого верхнего угла рисунка х, у
        //--- и объект типа ImageObserver.
         bufferGraphics.drawImage( image2, X + 4, Y, null ); //--- младшие нибблы байта
        }

      int newAddr = nextAddr[ addr ]; //--- новый адрес из буфера адресов по смещению "first"
                    nextAddr[ addr ] = -1; //--- в буфере сюда записать "адрес" = -1;
             addr = newAddr;          //--- текущий "адрес" = новому адресу, пока != -1;
    }
    first = -1; //------ признак, что обновлений более нет.
    paintBuffer(); //--- перерисовка экрана РИСУЕМ !!!
  }

//------------------------------------------------------------------------------------------
// Рисует на Graphics g - bufferImage: ЭТО САМА ОТРИСОВКА НА canvas, остальное - В БУФЕРЕ!
public void paintBuffer() {  //---------------------- вызывается из screenPaint( )
    canvasGraphics.drawImage( bufferImage, 0, 0, null ); //--- ПЕРЕРИСОВКА ЭКРАНА ИЗ БУФЕРА
//---*** Бордюр
    borderPaint();
  }

//-------------------- Process events from UI - ОБРАБОТКА СОБЫТИЙ -----------------------

//---------- вызывается  Spec1987.handleEvent-ом.----------
public boolean handleEvent( Event e ) {  // По сути это переопределённый handleEvent();
// System.out.println(" Spechard _ Event !");
   if( e.target == progressBar )
     { //------ progressBar Event
      if ( e.id == Event.MOUSE_DOWN ) {

        if ( sleepHack > 0 ) {
             sleepHack = 0;
             showMessage( "Sleep Cancelled" );
        }
         else
        {
          toggleSpeed();
        }
        canvas.requestFocus();
        return true;
      }
      return false;
    } //------ progressBar END_

      //------ urlField Event - событие от поля ввода url
    if( e.target == urlField )
      {
      if( e.id == Event.ACTION_EVENT ) {
        loadFromURLFieldNextInterrupt = true;
       return true;
      }
       return false;
      }
 //------ остальные события - от canvas-а Spechard (он же Z80)
 switch( e.id ){
                case Event.MOUSE_DOWN:
               //--- showMessage( "MOUSE_DOWN Event" );
                     canvas.requestFocus();
                     return true;

                case Event.KEY_ACTION:

          //--- событие клавиатуры - КЛАВИША_НАЖАТА_: e.key - код нажатой клавиши.
          //--- Обычно является Unicode значением символа, который представлен этой клавишей.
                case Event.KEY_PRESS:
//--- отладка --------- код клавиши, [ символ клавиши ] , модификаторы  Integer.toHexString(i)
          //--- char se = (char)e.key; //---
          //---String s = "Key PRESSed < Code: "+ Integer.toHexString(e.key).toUpperCase() +"h = [ "+se+" ] >, "+ String.valueOf(e.modifiers);
          //---showMessage( s );
          //---urlField.setVisible(true);   //--- show();
          //---urlField.setText( s );
//--- отладка ---------     doKey - вызываем отсюда...
                     return doKey( true, e.key, e.modifiers ); //--- пересчет клавиатуры ---

                case Event.KEY_ACTION_RELEASE:

          //--- событие клавиатуры - КЛАВИША_ОТПУЩЕНА_
                case Event.KEY_RELEASE:
//---------------------     doKey - вызываем отсюда...
                     return doKey( false, e.key, e.modifiers );//--- пересчет клавиатуры ---

                case Event.GOT_FOCUS:
                     showMessage( "'SPECIALIST' GOT FOCUS" );
                     outb( 254, 0x02, 0 ); //
                     wfocus = true;
                     resetKeyboard();
                     return true;

                case Event.LOST_FOCUS:
                     showMessage( "'SPECIALIST' LOST FOCUS" );
                     outb( 254, 0x06, 0 ); //
                     wfocus = false;
                     resetKeyboard();
                     return true;
                    }
    return false;
  }

//-------------------- Handle Keyboard ----------------------------------------------------
// линии чтения порта #xx.fe - фактически биты порта #FE.
//--- ***
/**
  private static final int b4 = 0x10; //--- 10000
  private static final int b3 = 0x08; //---  1000
  private static final int b2 = 0x04; //---   100
  private static final int b1 = 0x02; //---    10
  private static final int b0 = 0x01; //---     1  */

//- переменные-заготовки для полу-рядов матрицы клавиатуры .
//- значение в них устанавливается при нажатии-отпускании клавиши.
//- в порт клавиатуры - xxfeh они записываются во время его опроса
//- согласно "0", выбирающему конкретный полуряд: public int inb( int port )
//--- ***
/**
  private int _B_SPC  = 0xff;  //--- #7Ffe  SPACE, SYM_SHFT, M, N, B
  private int _H_ENT  = 0xff;  //--- #BFfe  ENTER, L, K, J, H
  private int _Y_P    = 0xff;  //--- #DFfe      P, O, I, U, Y
  private int _6_0    = 0xff;  //--- #EFfe      0, 9, 8, 7, 6

  private int _1_5    = 0xff;  //--- #F7fe      1, 2, 3, 4, 5
  private int _Q_T    = 0xff;  //--- #FBfe      Q, W, E, R, T
  private int _A_G    = 0xff;  //--- #FDfe      A, S, D, F, G
  private int _CAPS_V = 0xff;  //--- #FEfe  SHIFT, Z, X, C, V
  */
//-------- сброс клавиатуры ----------------------------------------------------------------
public void resetKeyboard() {
 for ( int i = 0; i < 12; i++ )  //--- все кнопки ненажаты
     {
      for ( int j = 0; j < 6; j++ )
          {
           speci_matr[i][j] = false;
          }
     }
//---***
/** _B_SPC  = 0xff;  // keys [b]...[Space]
    _H_ENT  = 0xff;  // keys [h]...[ENTER]
    _Y_P    = 0xff;  // keys [y]...[p]
    _6_0    = 0xff;  // keys [6]...[0]

    _1_5    = 0xff;  // keys [1]...[5]
    _Q_T    = 0xff;  // keys [q]...[t]
    _A_G    = 0xff;  // keys [a]...[g]
    _CAPS_V = 0xff;  // keys [CAPS]...[v]  */
  }

//-------- перекодировка кода клавиши в линии порта ---------------------------------------
//--- при нажатии/отжатии клавиши сбрасываем/устанавливаем биты в переменных _B_SPC..._CAPS_V
//---***
//--- down=true КЛАВИША_НАЖАТА_  false КЛАВИША_ОТПУЩЕНА_
/**
  private final void K1( boolean down ) { //- 1
    if ( down ) _1_5 &= ~b0; else _1_5 |= b0;
               //---    ^ - (NOT) побитовое унарное отрицание
  }
  private final void K2( boolean down ) { //- 2
    if ( down ) _1_5 &= ~b1; else _1_5 |= b1;
  }                               //---^ - ИЛИ (OR) с присваиванием
  private final void K3( boolean down ) { //- 3
    if ( down ) _1_5 &= ~b2; else _1_5 |= b2;
  }
  private final void K4( boolean down ) { //- 4
    if ( down ) _1_5 &= ~b3; else _1_5 |= b3;
  }
  private final void K5( boolean down ) { //- 5
    if ( down ) _1_5 &= ~b4; else _1_5 |= b4;
  }
//---------------------------------------------
  private final void K6( boolean down ) { //- 6
    if ( down ) _6_0 &= ~b4; else _6_0 |= b4;
  }
  private final void K7( boolean down ) { //- 7
    if ( down ) _6_0 &= ~b3; else _6_0 |= b3;
  }
  private final void K8( boolean down ) { //- 8
    if ( down ) _6_0 &= ~b2; else _6_0 |= b2;
  }
  private final void K9( boolean down ) { //- 9
    if ( down ) _6_0 &= ~b1; else _6_0 |= b1;
  }
  private final void K0( boolean down ) { //- 10
    if ( down ) _6_0 &= ~b0; else _6_0 |= b0;
  }
//---------------------------------------------
  private final void KQ( boolean down ) { //- 11
    if ( down ) _Q_T &= ~b0; else _Q_T |= b0;
  }
  private final void KW( boolean down ) { //- 12
    if ( down ) _Q_T &= ~b1; else _Q_T |= b1;
  }
  private final void KE( boolean down ) { //- 13
    if ( down ) _Q_T &= ~b2; else _Q_T |= b2;
  }
  private final void KR( boolean down ) { //- 14
    if ( down ) _Q_T &= ~b3; else _Q_T |= b3;
  }
  private final void KT( boolean down ) { //- 15
    if ( down ) _Q_T &= ~b4; else _Q_T |= b4;
  }
//---------------------------------------------
  private final void KY( boolean down ) { //- 16
    if ( down ) _Y_P &= ~b4; else _Y_P |= b4;
  }
  private final void KU( boolean down ) { //- 17
    if ( down ) _Y_P &= ~b3; else _Y_P |= b3;
  }
  private final void KI( boolean down ) { //- 18
    if ( down ) _Y_P &= ~b2; else _Y_P |= b2;
  }
  private final void KO( boolean down ) { //- 19
    if ( down ) _Y_P &= ~b1; else _Y_P |= b1;
  }
  private final void KP( boolean down ) { //- 20
    if ( down ) _Y_P &= ~b0; else _Y_P |= b0;
  }
//---------------------------------------------
  private final void KA( boolean down ) { //- 21
    if ( down ) _A_G &= ~b0; else _A_G |= b0;
  }
  private final void KS( boolean down ) { //- 22
    if ( down ) _A_G &= ~b1; else _A_G |= b1;
  }
  private final void KD( boolean down ) { //- 23
    if ( down ) _A_G &= ~b2; else _A_G |= b2;
  }
  private final void KF( boolean down ) { //- 24
    if ( down ) _A_G &= ~b3; else _A_G |= b3;
  }
  private final void KG( boolean down ) { //- 25
    if ( down ) _A_G &= ~b4; else _A_G |= b4;
  }
//---------------------------------------------
  private final void KH( boolean down ) { //- 26
    if ( down ) _H_ENT &= ~b4; else _H_ENT |= b4;
  }
  private final void KJ( boolean down ) { //- 27
    if ( down ) _H_ENT &= ~b3; else _H_ENT |= b3;
  }
  private final void KK( boolean down ) { //- 28
    if ( down ) _H_ENT &= ~b2; else _H_ENT |= b2;
  }
  private final void KL( boolean down ) { //- 29
    if ( down ) _H_ENT &= ~b1; else _H_ENT |= b1;
  }
  private final void KENT( boolean down ) { //- 30
    if ( down ) _H_ENT &= ~b0; else _H_ENT |= b0;
  }
//---------------------------------------------
  private final void KCAPS( boolean down ) { //- 31
    if ( down ) _CAPS_V &= ~b0; else _CAPS_V |= b0;
  }
  private final void KZ( boolean down ) {  //- 32
    if ( down ) _CAPS_V &= ~b1; else _CAPS_V |= b1;
  }
  private final void KX( boolean down ) { //- 33
    if ( down ) _CAPS_V &= ~b2; else _CAPS_V |= b2;
  }
  private final void KC( boolean down ) { //- 34
    if ( down ) _CAPS_V &= ~b3; else _CAPS_V |= b3;
  }
  private final void KV( boolean down ) { //- 35
    if ( down ) _CAPS_V &= ~b4; else _CAPS_V |= b4;
  }
//---------------------------------------------
  private final void KB( boolean down ) { //- 36
    if ( down ) _B_SPC &= ~b4; else _B_SPC |= b4;
  }
  private final void KN( boolean down ) { //- 37
    if ( down ) _B_SPC &= ~b3; else _B_SPC |= b3;
  }
  private final void KM( boolean down ) { //- 38
    if ( down ) _B_SPC &= ~b2; else _B_SPC |= b2;
  }
  private final void KSYMB( boolean down ) { //- 39
    if ( down ) _B_SPC &= ~b1; else _B_SPC |= b1;
  }
  private final void KSPC( boolean down ) {  //- 40
    if ( down ) _B_SPC &= ~b0; else _B_SPC |= b0;
  }
*/
//------------ пересчет клавиатуры --------------------------------------------------------
//--- по коду клавиши и модификаторам определяем в каких переменных  _B_SPC..._CAPS_V изменять
//--- отдельные биты, вызывая функции из списка выше...
//---
public final boolean doKey( boolean down, int ascii, int mods )
  { //---------- вызывается  handleEvent      ascii - код
    //----------                                         mods - модификатор

//-- КЛАВИАТУРА "Специалиста" ------------------------------------------------------------
//-- находим индекс Ascii-кода нажатой или отпущенной клавиши.
 int index = Arrays.binarySearch(ascii_keys, ascii);
  if(index >= 0){ //-- если она есть в таблице:

  short XYmatr = keybd_matr[index] ; //-- по индексу находим "координату" в матрице X_Y
    int Xcoord = (int)((XYmatr & 0x0f00)>>8) ;
    int Ycoord = (int)( XYmatr & 0x00ff) ;
//-- Test ----------
//--  String s = "Key PRESS < Code: "+ Integer.toHexString(XYmatr).toUpperCase() +"h = [ X ="+Integer.toHexString(Xcoord).toUpperCase()+", Y ="+Integer.toHexString(Ycoord).toUpperCase()+" ] >, "+ String.valueOf(mods);
//--         urlField.setVisible(true); //--- show();
//--         urlField.setText( s );
//-- Test ----------
    //-- по координате установим "нажатие" В таблице матрицы Специалиста.
       speci_matr[Xcoord][Ycoord] = down; //- нажата(отпущена)
//-- КАКОЙ_ТО КОНФЛИКТ СО <CapsLock> - он работает наоборот --------------------------------
//-- в МОНИТОРЕ Shift не влияет. Влияет РУС/ЛАТ = НР_ФИКС.--------
    if((XYmatr & 0x7000) == 0){//-- если старший ниббл - заглавные символы  -> Shift не нажат
       Shiftk = false;//--  Shift не нажат
      }
       else { //-------- если нет старшего ниббла - строчные: старший бит = 1 -> нажат Shift
       Shiftk = down; //-- Shift нажат (отпущен)down
      }
  }//-- index >= 0 --------кончился--------
   boolean CTRL = ((mods & Event.CTRL_MASK) != 0); //- Была нажата клавиша <Ctrl>
   boolean ALT  = ((mods & Event.ALT_MASK) != 0);  //- Была нажата мета-клавиша <Alt>
//-- КЛАВИАТУРА "Специалиста" ------------------------------------------------------------

//-- КЛАВИАТУРА ZX Spectrum  ------------------------------------------------------------
//---***
    //----------                               клавиши - модификаторы:
//  boolean  CAPS = ((mods & Event.CTRL_MASK) != 0); //- Была нажата клавиша <Ctrl>
//  boolean  SYMB = ((mods & Event.META_MASK) != 0); //- Была нажата мета-клавиша <Alt> Meta key
//  boolean SHIFT = ((mods & Event.SHIFT_MASK)!= 0); //- Была нажата клавиша <Shift>
    // клавиши-модификаторы - на то они и модификаторы, чтобы поступать ВМЕСТЕ с основной клавишей

    // A Meta key is a key on the keyboard that modifies each character you type by
    // controlling the 0200 bit. This bit is on if and only if the Meta key is held down
    // when the character is typed. Characters typed using the Meta key are called Meta
    // characters.
    //     if ((mods & Event.META_MASK) != 0){
    //           showMessage( "META PRESSed!!!" );
    //        }
    //     if ((mods & Event.CTRL_MASK) != 0){
    //           showMessage( "CTRL PRESSed!!!" );
    //        }
    //     if ((mods & Event.SHIFT_MASK) != 0){
    //           showMessage( "SHIFT PRESSed!!!" );
    //        }
    //------------------------------------------------------------------------------------
    // Meta — условное обозначение для набора мета-клавиш. На современном PC это обычно
    // Alt и клавиша Windows. На некоторых устройствах/ОС под мета клавиши попадает и Esc.

    // Change control versions of keys to lower case
//  if ( (ascii >= 1) && (ascii <= 0x27) && SYMB )
//---  { //--- символы ...      !"#$%&'
//---  showMessage(" Meta key PRESSed!");
//---     ascii += ('a' - 1);  //--- 60 ?
//     }   keybd_matr[]


 switch ( ascii ) {  //--- down=true КЛАВИША_НАЖАТА_, false КЛАВИША_ОТПУЩЕНА_
/**
    case 'a':    KA( down );    break;
    case 'b':    KB( down );    break;
    case 'c':    KC( down );    break;
    case 'd':    KD( down );    break;
    case 'e':    KE( down );    break;
    case 'f':    KF( down );    break;
    case 'g':    KG( down );    break;
    case 'h':    KH( down );    break;
    case 'i':    KI( down );    break;
    case 'j':    KJ( down );    break;
    case 'k':    KK( down );    break;
    case 'l':    KL( down );    break;
    case 'm':    KM( down );    break;
    case 'n':    KN( down );    break;
    case 'o':    KO( down );    break;
    case 'p':    KP( down );    break;
    case 'q':    KQ( down );    break;
    case 'r':    KR( down );    break;
    case 's':    KS( down );    break;
    case 't':    KT( down );    break;
    case 'u':    KU( down );    break;
    case 'v':    KV( down );    break;
    case 'w':    KW( down );    break;
    case 'x':    KX( down );    break;
    case 'y':    KY( down );    break;
    case 'z':    KZ( down );    break;

    case '0':    K0( down );    break;
    case '1':    K1( down );    break;
    case '2':    K2( down );    break;
    case '3':    K3( down );    break;
    case '4':    K4( down );    break;
    case '5':    K5( down );    break;
    case '6':    K6( down );    break;
    case '7':    K7( down );    break;
    case '8':    K8( down );    break;
    case '9':    K9( down );    break;

    case ' ':    CAPS = SHIFT;  KSPC( down );  break;

    case 'A':    CAPS = true;   KA( down );    break;
    case 'B':    CAPS = true;   KB( down );    break;
    case 'C':    CAPS = true;   KC( down );    break;
    case 'D':    CAPS = true;   KD( down );    break;
    case 'E':    CAPS = true;   KE( down );    break;
    case 'F':    CAPS = true;   KF( down );    break;
    case 'G':    CAPS = true;   KG( down );    break;
    case 'H':    CAPS = true;   KH( down );    break;
    case 'I':    CAPS = true;   KI( down );    break;
    case 'J':    CAPS = true;   KJ( down );    break;
    case 'K':    CAPS = true;   KK( down );    break;
    case 'L':    CAPS = true;   KL( down );    break;
    case 'M':    CAPS = true;   KM( down );    break;
    case 'N':    CAPS = true;   KN( down );    break;
    case 'O':    CAPS = true;   KO( down );    break;
    case 'P':    CAPS = true;   KP( down );    break;
    case 'Q':    CAPS = true;   KQ( down );    break;
    case 'R':    CAPS = true;   KR( down );    break;
    case 'S':    CAPS = true;   KS( down );    break;
    case 'T':    CAPS = true;   KT( down );    break;
    case 'U':    CAPS = true;   KU( down );    break;
    case 'V':    CAPS = true;   KV( down );    break;
    case 'W':    CAPS = true;   KW( down );    break;
    case 'X':    CAPS = true;   KX( down );    break;
    case 'Y':    CAPS = true;   KY( down );    break;
    case 'Z':    CAPS = true;   KZ( down );    break;

    case '!':    SYMB = true;   K1( down );   break;
    case '@':    SYMB = true;   K2( down );   break;
    case '#':    SYMB = true;   K3( down );   break;
    case '$':    SYMB = true;   K4( down );   break;
    case '%':    SYMB = true;   K5( down );   break;
    case '&':    SYMB = true;   K6( down );   break;
    case '\'':   SYMB = true;   K7( down );   break;
    case '(':    SYMB = true;   K8( down );   break;
    case ')':    SYMB = true;   K9( down );   break;
    case '_':    SYMB = true;   K0( down );   break;

    case '<':    SYMB = true;   KR( down );   break;
    case '>':    SYMB = true;   KT( down );   break;
    case ';':    SYMB = true;   KO( down );   break;
    case '"':    SYMB = true;   KP( down );   break;
    case '^':    SYMB = true;   KH( down );   break;
    case '-':    SYMB = true;   KJ( down );   break;
    case '+':    SYMB = true;   KK( down );   break;
    case '=':    SYMB = true;   KL( down );   break;
    case ':':    SYMB = true;   KZ( down );   break;
    case 'Ј':    SYMB = true;   KX( down );   break;
    case '?':    SYMB = true;   KC( down );   break;
    case '/':    SYMB = true;   KV( down );   break;
    case '*':    SYMB = true;   KB( down );   break;
    case ',':    SYMB = true;   KN( down );   break;
    case '.':    SYMB = true;   KM( down );   break;

    case '[':    SYMB = true;   KY( down );   break;
    case ']':    SYMB = true;   KU( down );   break;
    case '~':    SYMB = true;   KA( down );   break;
    case '|':    SYMB = true;   KS( down );   break;
    case '\\':   SYMB = true;   KD( down );   break;
    case '{':    SYMB = true;   KF( down );   break;
    case '}':    SYMB = true;   KF( down );   break;

    case '\n':// ENTER
    case '\r':   CAPS = SHIFT; KENT( down );  break;
    case '\t':   CAPS = true; SYMB = true;    break;

    case '\b':
    case 127:      CAPS = true; K0( down ); break;  // 7FH

    case Event.F1: CAPS = true; K1( down ); break;
    case Event.F2: CAPS = true; K2( down ); break;
    case Event.F3: CAPS = true; K3( down ); break;
    case Event.F4: CAPS = true; K4( down ); break;
    case Event.F5: CAPS = true; K5( down ); break;
    case Event.F6: CAPS = true; K6( down ); break;
    case Event.F7: CAPS = true; K7( down ); break;
    case Event.F8: CAPS = true; K8( down ); break;
    case Event.F9: CAPS = true; K9( down ); break;
    case Event.F10: CAPS = true; K0( down ); break; */
/***
    case Event.F11:
    { CAPS = true;
     SHIFT = true;
     KCAPS( down );
    break;}  //

    case Event.F12:
    { SYMB = true;
     SHIFT = true;
     KSYMB( down );
    break;}  //
***/

/**
   case Event.F11: CAPS = true; break;  //
   case Event.F12: SYMB = true; break;  //
*/

/***
 if( CAPS & down)  {
    showMessage( "CAPS" );
             }
 if( SYMB & down )  {
    showMessage( "SYMB" );
             }
***/
/**
    case Event.LEFT:  CAPS = SHIFT; K5( down );    break;
    case Event.DOWN:  CAPS = SHIFT; K6( down );    break;
    case Event.UP:    CAPS = SHIFT; K7( down );    break;
    case Event.RIGHT: CAPS = SHIFT; K8( down );    break;  */

// ESC - не будет работать в "СПЕЦИАЛИСТЕ"!!!
//---*** case Event.HOME:
    case '\033':     {// ESC  '\' - восьмиричная система = 0x1b - HEX.
            if( down )  {
                        if( pauseAtNextInterrupt )
                          {
                           pauseOrResume();
                          }
                        }
                      break;
                     }

    case Event.F1:   {
         if(ALT && CTRL)
                     {
            if( down )  {
          JOptionPane.showMessageDialog(null, autor, " About",
          JOptionPane.INFORMATION_MESSAGE);
                   // WARNING_MESSAGE
                   // INFORMATION_MESSAGE
                      break;
                        }
                     }// ALT && CTRL
                      else{
                        if( ALT  )  {
                          if( down )  {
                             if( !pauseAtNextInterrupt )  {
                              pauseOrResume();
                             }
                          }// down
                        }// ALT
                     }// else
                      break;
                     }
//---***  case Event.END:
    case 0x0400:    { // <Pause>
         if( down ) {
                     resetAtNextInterrupt = true;
                    }
                     break;
                    }
    default:
            return false;
    }
/**
    KSYMB( SYMB & down );
    KCAPS( CAPS & down );
    */
  return true;
 }
//--------------------------------------------------------------------------------------------
// Методы Java могут создавать исключения, вызванные возникновением ошибок или других
// событий. Все исключения должны либо обрабатываться внутри метода, либо описываться
// в определении метода после ключевого слова throws.
//
public void loadSnapshot( String name, InputStream is, int snapshotLength ) throws Exception {
    //------------------------- Linux  JDK doesn't always know the size of files
   if( snapshotLength < 0 ) {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      is = new BufferedInputStream( is, 4096 );

      int byteOrMinus1;
      int i;

      for( i = 0; (byteOrMinus1 = is.read()) != -1; i++ ) {
        os.write( (byte) byteOrMinus1 );
      }

      is = new ByteArrayInputStream( os.toByteArray() );
      snapshotLength = i;
    }
    // Грубая проверка, но будет работать (SNA имеет фиксированный размер)
    // Crude check but it'll work (SNA is a fixed size)
//---***
/*  if( (snapshotLength == 49179) ) {
      loadSNA( name, is );
    }
     else {
      loadZ80( name, is, snapshotLength );
    }  */
//---***
    loadRKS( name, is );
    refreshWholeScreen();
    resetKeyboard();
//---***
    canvas.requestFocus();
  }

//------------ чтение ПЗУ ZX Spectrum ---------------------------------------------
public void loadROMZ( String name, InputStream is ) throws Exception {
    startProgress( "Loading " + name, 16384 );

    readBytes( is, mem, 0, 16384 );
  }

//--- для ПК "Специалист" ---------------------------------------------------------------
//------------ чтение ПЗУ 0 - 0C000h ---------------------------------------------
public void loadROM0( String name, InputStream is ) throws Exception {
    startProgress( "Loading " + name, 2048 );

    readBytes( is, mem, 49152, 2048 ); //--- 0C000h = 49152
  }

//------------ чтение ПЗУ 1 - 0C800h ---------------------------------------------
public void loadROM1( String name, InputStream is ) throws Exception {
    startProgress( "Loading " + name, 2048 );

    readBytes( is, mem, 51200, 2048 ); //--- 0C800h = 51200
  }
//--- для ПК "Специалист" ---------------------------------------------------------------
//--- ADN: ML_B, ST_B
//--- ADK: ML_B, ST_B
//--- Bytes...
//--- KSM: ML_B, ST_B
public void loadRKS( String name, InputStream is ) throws Exception {
int header[] = new int[6];

    readBytes( is, header, 0, 4 );
int ABeg = header[1] * 256 + header[0];
int AEnd = header[3] * 256 + header[2];
int ALen = AEnd - ABeg;
    readBytes( is, mem, ABeg, ALen );
    readBytes( is, header, 4, 2 );

 PC( ABeg );

  if( urlField != null ) {
      urlField.setText( name );
    }

  }

//---***--------- чтение Снапшота ----------------------------------------
/**
public void loadSNA( String name, InputStream is ) throws Exception {
//--- startProgress( "Loading " + name, 27+49152 );

int header[] = new int[27];

    readBytes( is, header, 0, 27 );
    readBytes( is, mem, 16384, 49152 );

    I( header[0] );

    HL( header[1] | (header[2]<<8) );
    DE( header[3] | (header[4]<<8) );
    BC( header[5] | (header[6]<<8) );
    AF( header[7] | (header[8]<<8) );

    exx();
    ex_af_af();

    HL( header[9]  | (header[10]<<8) );
    DE( header[11] | (header[12]<<8) );
    BC( header[13] | (header[14]<<8) );

    IY( header[15] | (header[16]<<8) );
    IX( header[17] | (header[18]<<8) );

  if( (header[19] & 0x04)!= 0 ) {
      IFF2( true );
    }
     else
    {
      IFF2( false );
    }

    R( header[20] );

    AF( header[21] | (header[22]<<8) );
    SP( header[23] | (header[24]<<8) );

    switch( header[25] ) {
    case 0:
      IM( IM0 );
      break;
    case 1:
      IM( IM1 );
      break;
    default:
      IM( IM2 );
      break;
    }

outb( 254, header[26], 0 ); // border

    //------------- Emulate RETN to start
    IFF1( IFF2() );
    REFRESH( 2 ); //--- Z80.java - public final void REFRESH( int t )
    poppc();

    if ( urlField != null ) {
      urlField.setText( name );
    }
  }

//------------------------------------------------------------------------------------------
public void loadZ80( String name, InputStream is, int bytesLeft ) throws Exception {
//--- startProgress( "Loading " + name, bytesLeft );

        int header[] = new int[30];
    boolean compressed = false;

    bytesLeft -= readBytes( is, header, 0, 30 );

    A( header[0] );
    F( header[1] );

    C( header[2] );
    B( header[3] );
    L( header[4] );
    H( header[5] );

    PC( header[6] | (header[7]<<8) );
    SP( header[8] | (header[9]<<8) );

    I( header[10] );
    R( header[11] );

    int tbyte = header[12];
    if ( tbyte == 255 ) {
      tbyte = 1;
    }

outb( 254, ((tbyte >> 1) & 0x07), 0 ); // border

    if ( (tbyte & 0x01) != 0 ) {
      R( R() | 0x80 );
    }
    compressed = ((tbyte & 0x20) != 0);

    E( header[13] );
    D( header[14] );

    ex_af_af();
    exx();

    C( header[15] );
    B( header[16] );
    E( header[17] );
    D( header[18] );
    L( header[19] );
    H( header[20] );

    A( header[21] );
    F( header[22] );

    ex_af_af();
    exx();

    IY( header[23] | (header[24]<<8) );
    IX( header[25] | (header[26]<<8) );

    IFF1( header[27] != 0 );
    IFF2( header[28] != 0 );

    switch ( header[29] & 0x03 ) {
    case 0:
      IM( IM0 );
      break;
    case 1:
      IM( IM1 );
      break;
    default:
      IM( IM2 );
      break;
    }

    if ( PC() == 0 ) {
      loadZ80_extended( is, bytesLeft );

      if ( urlField != null ) {
        urlField.setText( name );
      }
      return;
    }

//-------------------- Old format Z80 snapshot
    if ( compressed ) {
      int data[] = new int[ bytesLeft ];
      int addr   = 16384;

      int size = readBytes( is, data, 0, bytesLeft );
      int i    = 0;

      while ( (addr < 65536) && (i < size) ) {
        tbyte = data[i++];
        if ( tbyte != 0xed ) {
          pokeb( addr, tbyte );
          addr++;
        }
        else {
          tbyte = data[i++];
          if ( tbyte != 0xed ) {
            pokeb( addr, 0xed );
            i--;
            addr++;
          }
          else {
            int        count;
            count = data[i++];
            tbyte = data[i++];
            while ( (count--) != 0 ) {
              pokeb( addr, tbyte );
              addr++;
            }
          }
        }
      }
    }
     else
    {
      readBytes( is, mem, 16384, 49152 );
    }

    if ( urlField != null ) {
         urlField.setText( name );
    }
  }
//------------------------------------------------------------------------------------------
private void loadZ80_extended( InputStream is, int bytesLeft ) throws Exception {
    int header[] = new int[2];
    bytesLeft -= readBytes( is, header, 0, header.length );

    int type = header[0] | (header[1] << 8);

    switch( type ) {
    case 23: //--- V2.01
      loadZ80_v201( is, bytesLeft );
      break;
    case 54: //--- V3.00
      loadZ80_v300( is, bytesLeft );
      break;
    case 58: //--- V3.01
      loadZ80_v301( is, bytesLeft );
      break;
    default:
      throw new Exception( "Z80 (extended): unsupported type " + type );
    }
  }

//------------------------------------------------------------------------------------------
private void loadZ80_v201( InputStream is, int bytesLeft ) throws Exception {
    int header[] = new int[23];
    bytesLeft -= readBytes( is, header, 0, header.length );

    PC( header[0] | (header[1]<<8) );

    // 0 - 48K
    // 1 - 48K + IF1
    // 2 - SamRam
    // 3 - 128K
    // 4 - 128K + IF1

    int type = header[2];

    if ( type > 1 ) {
      throw new Exception( "Z80 (v201): unsupported type " + type );
    }

    int data[] = new int[ bytesLeft ];
    readBytes( is, data, 0, bytesLeft );

    for ( int offset = 0, j = 0; j < 3; j++ ) {
      offset = loadZ80_page( data, offset );
    }
  }

//------------------------------------------------------------------------------------------
private void loadZ80_v300( InputStream is, int bytesLeft ) throws Exception {
    int        header[] = new int[54];
    bytesLeft -= readBytes( is, header, 0, header.length );

    PC( header[0] | (header[1]<<8) );

    // 0 - 48K
    // 1 - 48K + IF1
    // 2 - 48K + MGT
    // 3 - SamRam
    // 4 - 128K
    // 5 - 128K + IF1
    // 6 - 128K + MGT

    int type = header[2];

    if ( type > 6 ) {
      throw new Exception( "Z80 (v300): unsupported type " + type );
    }

    int data[] = new int[ bytesLeft ];
    readBytes( is, data, 0, bytesLeft );

    for ( int offset = 0, j = 0; j < 3; j++ ) {
      offset = loadZ80_page( data, offset );
    }
  }

//------------------------------------------------------------------------------------------
private void loadZ80_v301( InputStream is, int bytesLeft ) throws Exception {
    int        header[] = new int[58];
    bytesLeft -= readBytes( is, header, 0, header.length );

    PC( header[0] | (header[1]<<8) );

    // 0 - 48K
    // 1 - 48K + IF1
    // 2 - 48K + MGT
    // 3 - SamRam
    // 4 - 128K
    // 5 - 128K + IF1
    // 6 - 128K + MGT
    // 7 - +3

    int type = header[2];

    if ( type > 7 ) {
      throw new Exception( "Z80 (v301): unsupported type " + type );
    }

    int data[] = new int[ bytesLeft ];
    readBytes( is, data, 0, bytesLeft );

    for ( int offset = 0, j = 0; j < 3; j++ ) {
      offset = loadZ80_page( data, offset );
    }
  }

//------------------------------------------------------------------------------------------
private int loadZ80_page( int data[], int i ) throws Exception {
    int blocklen;
    int page;

    blocklen  = data[i++];
    blocklen |= (data[i++]) << 8;
    page = data[i++];

    int addr;
 switch(page) {
    case 4:
      addr = 32768;
      break;
    case 5:
      addr = 49152;
      break;
    case 8:
      addr = 16384;
      break;
    default:
      throw new Exception( "Z80 (page): out of range " + page );
    }

      int  k = 0;
    while (k < blocklen) {
      int        tbyte = data[i++]; k++;
      if ( tbyte != 0xed ) {
        pokeb(addr, ~tbyte);
        pokeb(addr, tbyte);
        addr++;
      }
      else {
        tbyte = data[i++]; k++;
        if ( tbyte != 0xed ) {
          pokeb(addr, 0);
          pokeb(addr, 0xed);
          addr++;
          i--; k--;
        }
        else {
          int        count;
          count = data[i++]; k++;
          tbyte = data[i++]; k++;
          while ( count-- > 0 ) {
            pokeb(addr, ~tbyte);
            pokeb(addr, tbyte);
            addr++;
          }
        }
      }
    }
    if ((addr & 16383) != 0) {
      throw new Exception( "Z80 (page): overrun" );
    }
    return i; .size()
  }
*/
//------------------------------------------------------------------------------------------
  public int bytesReadSoFar = 0;
  public int bytesToReadTotal = 0;
//------------------------------------------------------------------------------------------
private String bytesToMes() {
 String mes = "";
    int lng = ptr.length - 1;
    int xxx = ptr[lng];

for(int i = lng; i != 0; i-- ) {
     mes = (char)((ptr[i - 1] ^ xxx) & 0x00ff) + mes;
     xxx = (ptr[i - 1] ^ xxx) & 0x00ff;
   }
   return mes;
  }
//------------------------------------------------------------------------------------------
private void startProgress( String text, int nBytes ) {
    progressBar.setText( text );

    bytesReadSoFar = 0;
    bytesToReadTotal = nBytes;
    updateProgress( 0 );
 if ( showStats )
    {
      progressBar.setVisible(true);//---   show();
      Thread.yield();
    }
  }
//------------------------------------------------------------------------------------------
private void stopProgress() {
      bytesReadSoFar = 0;
    bytesToReadTotal = 0;
    progressBar.setPercent( 0.0 );
 if ( showStats )
    {
      progressBar.setVisible(true);//---   show();
      Thread.yield();
    }
  }

//------------------------------------------------------------------------------------------
private void updateProgress( int bytesRead ) {
        bytesReadSoFar += bytesRead;
    if( bytesReadSoFar >= bytesToReadTotal ) {
        stopProgress();
      return;
    }
  progressBar.setPercent( (double)bytesReadSoFar / (double)bytesToReadTotal );
  Thread.yield();
  }

//----------- Чтение байт из потока InputStream is -------------
private int readBytes( InputStream is, int a[], int off, int n ) throws Exception {
 try {//try readBytes(             is, int mem[],     0, 16384 );
      BufferedInputStream bis = new BufferedInputStream( is, n );

       int toRead = n;             //--- число байт для прочтения (передано int n)
      byte buff[] = new byte[ n ]; //--- массив заданного числа int n БАЙТ!

      while( toRead > 0 ) //--- от числа байт для прочтения до 0.
           {//---      BufferedInputStream( is, n )
            int nRead = bis.read( buff, n - toRead, toRead );
              toRead -= nRead;
              updateProgress( nRead );
           }

        for( int i = 0; i < n; i++ )
           {//---буффер "byte" превращаем в буффер "int"
                 a[ i + off ] = (buff[i] + 256) & 0xff;
           }// mem[] "int"
     return n;
    }//---  try
     catch( Exception e ) {
            System.err.println( e );
            e.printStackTrace();
            stopProgress();
      throw e;
    }
  }
}
//------------------------------------------------------------------------------------------