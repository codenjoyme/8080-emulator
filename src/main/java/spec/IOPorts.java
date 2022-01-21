package spec;

import java.util.Arrays;

import static spec.Constants.ROM;

public class IOPorts {

    private boolean PrtAIN = true;  // порт A - на ввод
    private boolean PrtBIN = true;  // порт B - на ввод
    private boolean PrC0IN = true;  // порт C0- на ввод
    private boolean PrC1IN = true;  // порт C1- на ввод
    private boolean Shiftk = false; // Shift не нажат

    // ЭТИ ПОРТЫ ЛУЧШЕ СДЕЛАТЬ АДРЕСМИ, ЧТОБЫ МЕНЯТЬ ИХ ТОЛЬКО ЗДЕСЬ,,,
    // Пользуясь ключевыми словами static и final, можно определять внутри классов
    // глобальные константы.

    private static final int PortA = 0xFFE0; // Порт А ППА
    private static final int PortB = 0xFFE1; // Порт В ППА
    private static final int PortC = 0xFFE2; // Порт С ППА
    private static final int RgRYS = 0xFFE3; // рег. Упр.Слова ППА
    public static final int RgRGB = 0xFFF8; // порт контроллера цвета

    // маски битов
    private final int[] bit = {0x00FE, 0x00FD, 0x00FB, 0x00F7, 0x00EF, 0x00DF, 0x00BF, 0x007F};

    // биты установки
    private final int[] msk = {0x0001, 0x0002, 0x0004, 0x0008, 0x0010, 0x0020, 0x0040, 0x0080};

    //  массив возможных ascii-кодов нажатых клавиш...
//  23 строки по 8 = 184 + 4 + 2 + 2 = 192 клавиши.
//                                   <BckSpc> <Tab>   <Enter> <Esc>
    private final int[] ascii_keys = {0x0008, 0x0009, 0x000A, 0x001B,
//          <Space>  | ! |   | " |   | # |   | $ |   | % |   | & |   | ' |
            0x0020, 0x0021, 0x0022, 0x0023, 0x0024, 0x0025, 0x0026, 0x0027,
//           | ( |   | ) |   | * |   | + |   | , |   | - |   | . |   | / |
            0x0028, 0x0029, 0x002A, 0x002B, 0x002C, 0x002D, 0x002E, 0x002F,
//           | 0 |   | 1 |   | 2 |   | 3 |   | 4 |   | 5 |   | 6 |   | 7 |
            0x0030, 0x0031, 0x0032, 0x0033, 0x0034, 0x0035, 0x0036, 0x0037,
//           | 8 |   | 9 |   | : |   | ; |   | < |   | = |   | > |   | ? |
            0x0038, 0x0039, 0x003A, 0x003B, 0x003C, 0x003D, 0x003E, 0x003F,
//           | @ |   | A |   | B |   | C |   | D |   | E |   | F |   | G |
            0x0040, 0x0041, 0x0042, 0x0043, 0x0044, 0x0045, 0x0046, 0x0047,
//           | H |   | I |   | J |   | K |   | L |   | M |   | N |   | O |
            0x0048, 0x0049, 0x004A, 0x004B, 0x004C, 0x004D, 0x004E, 0x004F,
//           | P |   | Q |   | R |   | S |   | T |   | U |   | V |   | W |
            0x0050, 0x0051, 0x0052, 0x0053, 0x0054, 0x0055, 0x0056, 0x0057,
//           | X |   | Y |   | Z |   | [ |   | \ |   | ] |   | ^ |   | _ |
            0x0058, 0x0059, 0x005A, 0x005B, 0x005C, 0x005D, 0x005E, 0x005F,
//           | ` |   | a |   | b |   | c |   | d |   | e |   | f |   | g |
            0x0060, 0x0061, 0x0062, 0x0063, 0x0064, 0x0065, 0x0066, 0x0067,
//           | h |   | i |   | j |   | k |   | l |   | m |   | n |   | o |
            0x0068, 0x0069, 0x006A, 0x006B, 0x006C, 0x006D, 0x006E, 0x006F,
//           | p |   | q |   | r |   | s |   | t |   | u |   | v |   | w |
            0x0070, 0x0071, 0x0072, 0x0073, 0x0074, 0x0075, 0x0076, 0x0077,
//           | x |   | y |   | z |   | { |   | | |   | } |   | ~ |   <Del>
            0x0078, 0x0079, 0x007A, 0x007B, 0x007C, 0x007D, 0x007E, 0x007F,
//           <Home>  <End>   <PgUp>  <PgDn>  < Up >  <Down>  <Left> <Right>
            0x03E8, 0x03E9, 0x03EA, 0x03EB, 0x03EC, 0x03ED, 0x03EE, 0x03EF,
//           <F1>    <F2>    <F3>    <F4>    <F5>    <F6>    <F7>    <F8>
            0x03F0, 0x03F1, 0x03F2, 0x03F3, 0x03F4, 0x03F5, 0x03F6, 0x03F7,
//           <F9>    <F10>   <F11>   <F12>  <CpsLk>
            0x03F8, 0x03F9, 0x03FA, 0x03FB, 0x03FE,
//         <Insert> = ПВ [ Ё ]!;
            0x0401,
//           | А |   | Б |   | В |   | Г |   | Д |   | Е |   | Ж |   | З |
            0x0410, 0x0411, 0x0412, 0x0413, 0x0414, 0x0415, 0x0416, 0x0417,
//           | И |   | Й |   | К |   | Л |   | М |   | Н |   | О |   | П |
            0x0418, 0x0419, 0x041A, 0x041B, 0x041C, 0x041D, 0x041E, 0x041F,
//           | Р |   | С |   | Т |   | У |   | Ф |   | Х |   | Ц |   | Ч |
            0x0420, 0x0421, 0x0422, 0x0423, 0x0424, 0x0425, 0x0426, 0x0427,
//           | Ш |   | Щ |   | Ъ |   | Ы |   | Ь |   | Э |   | Ю |   | Я |
            0x0428, 0x0429, 0x042A, 0x042B, 0x042C, 0x042D, 0x042E, 0x042F,
//           | а |   | б |   | в |   | г |   | д |   | е |   | ж |   | з |
            0x0430, 0x0431, 0x0432, 0x0433, 0x0434, 0x0435, 0x0436, 0x0437,
//           | и |   | й |   | к |   | л |   | м |   | н |   | о |   | п |
            0x0438, 0x0439, 0x043A, 0x043B, 0x043C, 0x043D, 0x043E, 0x043F,
//           | р |   | с |   | т |   | у |   | ф |   | х |   | ц |   | ч |
            0x0440, 0x0441, 0x0442, 0x0443, 0x0444, 0x0445, 0x0446, 0x0447,
//           | ш |   | щ |   | ъ |   | ы |   | ь |   | э |   | ю |   | я |
            0x0448, 0x0449, 0x044A, 0x044B, 0x044C, 0x044D, 0x044E, 0x044F,
//           | ё |   | № |
            0x0451, 0x2116
    };

//  массив позиций матрицы "Специалиста" для возможных ascii-кодов нажатых клавиш...
//  двухбайтное значение:0xXXYY, установка старшего бита = нажатию Shift(HP)
//  уточнить значения клавиш: <CapsLock> и Р/Л действуют в противофазе!
//  23 строки по 8 = 184 + 4 + 2 + 2 = 192 клавиши.

    //                                     <BckSpc>  <Tab>  <Enter> <Esc>
    private final short[] keybd_matr = {0x0001, 0x0600, 0x0000, 0x0700,
//          <Space>  | ! |   | " |   | # |   | $ |   | % |   | & |   | ' |
            0x0500, 0x7a04, 0x7904, 0x7804, 0x7704, 0x7604, 0x7504, 0x7404,
//           | ( |   | ) |   | * |   | + |   | , |   | - |   | . |   | / |
            0x7304, 0x7204, 0x0003, 0x7b04, 0x0201, 0x0004, 0x0002, 0x0101,
//           | 0 |   | 1 |   | 2 |   | 3 |   | 4 |   | 5 |   | 6 |   | 7 |
            0x0104, 0x0a04, 0x0904, 0x0804, 0x0704, 0x0604, 0x0504, 0x0404,
//           | 8 |   | 9 |   | : |   | ; |   | < |   | = |   | > |   | ? |
            0x0304, 0x0204, 0x7003, 0x0b03, 0x7201, 0x7004, 0x0002, 0x0101,
//           | @ |   | A |   | B |   | C |   | D |   | E |   | F |   | G |
            0x0301, 0x0802, 0x0401, 0x0a03, 0x0302, 0x0703, 0x0b02, 0x0503,
//           | H |   | I |   | J |   | K |   | L |   | M |   | N |   | O |
            0x0103, 0x0701, 0x0b03, 0x0803, 0x0402, 0x0801, 0x0603, 0x0502,
//           | P |   | Q |   | R |   | S |   | T |   | U |   | V |   | W |
            0x0702, 0x0b01, 0x0602, 0x0901, 0x0601, 0x0903, 0x0202, 0x0902,
//           | X |   | Y |   | Z |   | [ |   | \ |   | ] |   | ^ |   | _ |
            0x0501, 0x0a02, 0x0203, 0x0403, 0x0102, 0x0303, 0x0A01, 0x0001,
//           | ` |   | a |   | b |   | c |   | d |   | e |   | f |   | g |
            0x0b04, 0x7802, 0x7401, 0x7a03, 0x7302, 0x7703, 0x7B02, 0x7503,
//           | h |   | i |   | j |   | k |   | l |   | m |   | n |   | o |
            0x7103, 0x7701, 0x7B03, 0x7803, 0x7402, 0x7801, 0x7603, 0x7502,
//           | p |   | q |   | r |   | s |   | t |   | u |   | v |   | w |
            0x7702, 0x7B01, 0x7602, 0x7901, 0x7601, 0x7903, 0x7202, 0x7902,
//           | x |   | y |   | z |   | { |   | | |   | } |   | ~ | <Del>
            0x7501, 0x7A02, 0x7203, 0x7403, 0x7102, 0x7303, 0x7B04, 0x0005,
//          <Home>  <End>   <PgUp>  <PgDn>  < Up >  <Down> <Left> <Right>*/
            0x0B05, 0x0A05, 0x0A00, 0x0100, 0x0900, 0x0800, 0x0400, 0x0200,
//           <F1>    <F2>    <F3>    <F4>    <F5>    <F6>   <F7>   <F8>
            0x0B05, 0x0A05, 0x0905, 0x0805, 0x0705, 0x0605, 0x0505, 0x0405,
//           <F9>    <F10>   <F11>   <F12>  <CpsLk> = Р/Л
            0x0305, 0x0205, 0x0105, 0x0005, 0x0700,
//          <Insert> = ПВ [ Ё ]!;
            0x0300,
//           | А |   | Б |   | В |   | Г |   | Д |   | Е |   | Ж |   | З |
            0x0802, 0x0401, 0x0902, 0x0503, 0x0302, 0x0703, 0x0202, 0x0203,
//           | И |   | Й |   | К |   | Л |   | М |   | Н |   | О |   | П |
            0x0701, 0x0B03, 0x0803, 0x0402, 0x0801, 0x0603, 0x0502, 0x0702,
//           | Р |   | С |   | Т |   | У |   | Ф |   | Х |   | Ц |   | Ч |
            0x0602, 0x0901, 0x0601, 0x0903, 0x0B02, 0x0103, 0x0A03, 0x0A01,
//           | Ш |   | Щ |   | Ъ |   | Ы |   | Ь |   | Э |   | Ю |   | Я |
            0x0403, 0x0303, 0x0001, 0x0A02, 0x0501, 0x0102, 0x0301, 0x0B01,
//           | а |   | б |   | в |   | г |   | д |   | е |   | ж |   | з |
            0x7802, 0x7401, 0x7902, 0x7503, 0x7302, 0x7703, 0x7202, 0x7203,
//           | и |   | й |   | к |   | л |   | м |   | н |   | о |   | п |
            0x7701, 0x7B03, 0x7803, 0x7402, 0x7801, 0x7603, 0x7502, 0x7702,
//           | р |   | с |   | т |   | у |   | ф |   | х |   | ц |   | ч |
            0x7602, 0x7901, 0x7601, 0x7903, 0x7B02, 0x7103, 0x7A03, 0x7A01,
//           | ш |   | щ |   | ъ |   | ы |   | ь |   | э |   | ю |   | я |
            0x7403, 0x7303, 0x7001, 0x7A02, 0x7501, 0x7102, 0x7301, 0x7B01,
//           | ё |   | № |
            0x7b04, 0x7804
    };

    // массив матрицы клавш "Специалиста" ( true - нажата, false - отпущена)
    // 12 x 6 + Shift + Reset
    private boolean[][] speci_matr = new boolean[12][6];

    private Memory memory;

    public IOPorts(Memory memory) {
        this.memory = memory;
    }

    /**
     * Матрица клавиш 12х6. True = замкнуто, False = разомкнуто
     * ======================================================================================
     *       C3    C2    C1    C0    A7    A6    A5    A4    A3    A2    A1    A0  <==:ПОРТ:|
     * ============================================================================      |  |
     *    | X_B | X_A | X_9 | X_8 | X_7 | X_6 | X_5 | X_4 | X_3 | X_2 | X_1 | X_0 |      |  |
     * ==================================================================================^==|
     * Y5 |  F  |  F1 |  F2 |  F3 |  F4 |  F5 |  F6 |  F7 |  F8 | [X] | [ ] | [/] | 05 | B5 |
     * Y4 | + ; | ! 1 | " 2 | # 3 | $ 4 | % 5 | & 6 | ' 7 | ( 8 | ) 9 |   0 | = = | 04 | B4 |
     * Y3 | J Й | C Ц | U У | K K | E E | N Н | G Г | [ Ш | ] Щ | Z З | H Х | : * | 03 | B3 |
     * Y2 | F Ф | Y Ы | W В | A A | P П | R Р | O O | L Л | D Д | V Ж | \ Э | . > | 02 | B2 |
     * Y1 | Q Я | ^ Ч | S С | M M | I И | T T | X Ь | B Б | @ Ю | < , | ? / |  ЗБ | 01 | B1 |
     * Y0 | Р/Л |HOME |  Up |Down | ESC | TAB | SPС |  <= |  ПВ |  => |  ПС |  ВК | 00 | B0 |
     * ==============================================================================^======|
     *      0b    0a    09    08    07    06    05    04    03    02    01    00 <=X/Y      |
     **/

    // ввод из порта памяти 580ВВ55
    public int inPort(int addr) {
        // все порты ППА перенесём в область 0xFFE0 - 0xFFE3 ;
        if (addr <= RgRYS) {
            addr = shiftPortAddress(addr);
        }

        int res = 0x00FF; // предварительно ни одна кнопка не нажата
        if (addr > ROM.end() && addr < RgRYS) { // port 580BB55
            switch (addr) {// разбираем по каналам...
                case PortA: {// Порт А
                    if (PrtAIN) {// если порт A - на ввод
                        if (!PrtBIN) {// если порт B - на вывод
                            for (int i = 0; i < 6; i++)  // по битам порта B от 2 до 7
                            {
                                for (int j = 0; j < 8; j++)  // по битам порта A от 0 до 7
                                {// если такая нажата  и  такой бит порта B = 0, ставим бит A = 0
                                    if (speci_matr[j][i] && (memory.read8(PortB) & msk[i + 2]) == 0) {
                                        res &= bit[j];
                                    }
                                }//  for( int j)
                            }//  for( int i)
                            return res; //  возвращаем состояние порта A
                        }// если порт B - на вывод   закончился
                        // если порт B - на ввод то и делать нечего
                    }// если порт A - на ввод   закончился
                    return memory.read8(PortA); //  порта A - на вывод < последнее записанное
                }// Порт А  закончился

                case PortB: {  // Порт В
                    if (PrtBIN) {// если порта В - на ввод

                        if (!PrtAIN) {// если порт A - на вывод
                            for (int i = 0; i < 8; i++)  // по битам порта A от 0 до 7
                            {
                                for (int j = 0; j < 6; j++)  // по битам порта В от 2 до 7
                                {// если такая нажата  и  такой бит порта A = 0, ставим бит В = 0
                                    if (speci_matr[i][j] && (memory.read8(PortA) & msk[i]) == 0) {
                                        res &= bit[j + 2];
                                    }
                                }//  for( int j)
                            }//  for( int i)
                        }// если порт A - на вывод   закончился

                        if (!PrC0IN) {// если порт CLow - на вывод
                            // по битам порта CLow от 0 до 3
                            for (int i = 0; i < 4; i++) {
                                // по битам порта В от 2 до 7
                                for (int j = 0; j < 6; j++) {
                                    // если такая нажата  и  такой бит порта C = 0, ставим бит В = 0
                                    if (speci_matr[i + 8][j] && (memory.read8(PortC) & msk[i]) == 0) {
                                        res &= bit[j + 2];
                                    }
                                }
                            }
                        }// если порт C - на вывод   закончился

                        // если порт C - на ввод то и делать нечего
                        // если порт A - на ввод то и делать нечего

                        // КАКОЙ_ТО КОНФЛИКТ СО <CapsLock> - он работает наоборот
                        // <CpsLk> = Р/Л ЭТО ДАЁТ СБОЙ ЗДЕСЬ Т.К. ВЛИЯЕТ НА КОД КЛАВИШИ!!!
                        // в Мониторе "Shift" не влияет на клавишу. Влияет РУС/ЛАТ (НР.ФИКС) !!!
                        if (Shiftk) {//  ПОРТ РАБОТАЕТ ПРАВИЛЬНО
                            //  showMessage( "Shiftk TRUE!!! = SMALL !!!" );
                            //  res &= 0xFFFD; // выставим состояние Shift: B1 = 0
                        } else {
                            res |= 0x0002; // выставим состояние Shift: B1 = 1
                        }
                        // char se = (char)e.key; // Test
                        // String s = "PORT : "+ Integer.toHexString(res).toUpperCase() +"h  ";
                        // showMessage( s );
                        //urlField.setVisible(true);   // show();
                        //urlField.setText( s );  // Test

                        return res; //  возвращаем состояние порта В
                    }// если порта В - на ввод   закончился
                    return memory.read8(PortB); //  порта В - на вывод < последнее записанное
                }// Порт В  закончился

                default: {// Порт С orphaned default
                    if (PrC0IN) {// если порта CLow - на ввод
                        if (!PrtBIN) {// если порт B - на вывод
                            for (int i = 0; i < 6; i++)  // по битам порта B от 2 до 7
                            {
                                for (int j = 0; j < 4; j++)  // по битам порта CLow от 0 до 3
                                {// если такая нажата  и  такой бит порта В = 0, ставим бит C = 0
                                    if (speci_matr[j + 8][i] && (memory.read8(PortB) & msk[i + 2]) == 0) {
                                        res = res & bit[j];
                                    }
                                }// for( int j)
                            }// for( int i)
                            return res; //  возвращаем состояние порта C
                        }// если порт B - на вывод   закончился
                        // если порт B - на ввод то и делать нечего
                    }// если порта CLow - на ввод   закончился
                    else {// если порта CLow - на вывод
                        return memory.read8(PortC) & 0x000F | 0x00F0;
                    }
                }// Порт С  закончился
//ЗДЕСЬ - ПОДУМАТЬ И ПРОВЕРИТЬ!!!
            }//  switch   закончился
        }// port 580BB55   закончился
        else { // остальные порты и РУС ВРЕМЕННО считаем ячейками памяти!
            res = memory.read8(addr);   // читаем байт памяти!
        }
// Тест обращения к порту 580ВВ55 -
//      String raddr = Integer.toHexString(addr);
//      String rByte = Integer.toHexString(res);
// if( addr == (0xFFE0) ){ //
//         showMessage( "Port inpByte: " + raddr.toUpperCase() + "h = " + rByte.toUpperCase() +"h");  // showStatus
//         showMessage( "Port inpByte: " + Integer.toHexString(addr).toUpperCase() +"h");
//     }
// Тест обращения к порту 580ВВ55 -
        return (res);
    }

    public void outPort(int addr, int outByte) {
        // все порты ППА перенесём в область 0xFFE0 - 0xFFE3
        if (addr <= RgRYS) {
            addr = shiftPortAddress(addr);
        }

        int tmpByte;
        // Разбор  управляющего слова ППА 580ВВ55
        if (addr == RgRYS) { // РУС
            if ((outByte & 0x0080) != 0) { // управляющие слова 1-старший бит
                if ((outByte & 0x0001) != 0) { // КАНАЛ_С(МЛ.)(РС0-РС3)
                    PrC0IN = true; // порт C0- на ввод
                } else {
                    PrC0IN = false; // порт C0- на вывод
                    tmpByte = memory.read8(PortC);
                    memory.write8(PortC, tmpByte & 0x00F0);// порт C0 = 0
                }
                if ((outByte & 0x0002) != 0) { // КАНАЛ_В(РВ0-РВ7)
                    PrtBIN = true; // порт B - на ввод
                } else {
                    PrtBIN = false; // порт B - на вывод
                    memory.write8(PortB, 0);// порт B = 0
                }
                if ((outByte & 0x0008) != 0) { // КАНАЛ_С(СТ.)(РС4-РС7)
                    PrC1IN = true; // порт C1- на ввод
                } else {
                    PrC1IN = false; // порт C1- на вывод
                    tmpByte = memory.read8(PortC);
                    memory.write8(PortC, tmpByte & 0x000F);// порт C1 = 0
                }
                if ((outByte & 0x0010) != 0) { // КАНАЛ_A(РА0-РА7)
                    PrtAIN = true;   // порт A - на ввод
                } else {
                    PrtAIN = false; // порт A - на вывод
                    memory.write8(PortA, 0); // порт A = 0
                }
                memory.write8(addr, outByte); // в ПОРТ RYC запишем YC   ПОРТЫ 0xFFE3
                return;
            } else { // побитное управление портом 0xFFE3 .
                if (!PrC0IN) { // если порт C0- на вывод
                    if ((outByte & 0x0001) == 1) {// уст. в 1
                        if (((outByte & 0x000E) >> 1) < 4) {// биты 0-3
                            tmpByte = memory.read8(PortC);
                            memory.write8(PortC, tmpByte | msk[((outByte & 0x000E) >> 1)]); // уст. в 1
                        }
                    } else {// уст. в 0
                        if (((outByte & 0x000e) >> 1) < 4) {// биты 0-3
                            tmpByte = memory.read8(PortC);
                            memory.write8(PortC, tmpByte & bit[((outByte & 0x000E) >> 1)]); // уст. в 0
                        }
                    }// уст. в X кончилися
                } // порт C0- на вывод кончился
                if (!PrC1IN) { // если порт C1- на вывод
                    if ((outByte & 0x0001) == 1) {// уст. в 1
                        if (((outByte & 0x000E) >> 1) > 3) {// биты 4-7
                            tmpByte = memory.read8(PortC);
                            memory.write8(PortC, tmpByte | msk[((outByte & 0x000E) >> 1)]); // уст. в 1
                        }
                    } else {// уст. в 0
                        if (((outByte & 0x000E) >> 1) > 3) {// биты 4-7
                            tmpByte = memory.read8(PortC);
                            memory.write8(PortC, tmpByte & bit[((outByte & 0x000E) >> 1)]); // уст. в 0
                        }
                    }
                } // порт C1- на вывод кончился
            }// побитное управление портом 0xFFE3 кончилось
        } // остальные ПОРТЫ: в том числе и 0xFFF8- цвет.
        memory.write8(addr, outByte); // в остальные ПОРТЫ пишем в память: 0xC000 < ПОРТЫ < 0xFFFF
    }

    private int shiftPortAddress(int addr) {
        return (addr & 0x0003) | 0xFFE0;
    }

    // переменные-заготовки для полу-рядов матрицы клавиатуры .
    // значение в них устанавливается при нажатии-отпускании клавиши.
    // в порт клавиатуры - xxfeh они записываются во время его опроса
    // согласно "0", выбирающему конкретный полуряд: public int inb( int port )
    // ***

    // сброс клавиатуры -
    public void resetKeyboard() {
        for (int i = 0; i < 12; i++) {  // все кнопки ненажаты
            for (int j = 0; j < 6; j++) {
                speci_matr[i][j] = false;
            }
        }
    }

    public void processKey(boolean down, int ascii) {
        // находим индекс Ascii-кода нажатой или отпущенной клавиши.
        int index = Arrays.binarySearch(ascii_keys, ascii);
        if (index >= 0) { // если она есть в таблице:

            short XYmatr = keybd_matr[index]; // по индексу находим "координату" в матрице X_Y
            int Xcoord = (XYmatr & 0x0F00) >> 8;
            int Ycoord = XYmatr & 0x00FF;

            // по координате установим "нажатие" В таблице матрицы Специалиста.
            speci_matr[Xcoord][Ycoord] = down; // нажата(отпущена)
            // какой_то конфликт СО <CapsLock> - он работает наоборот
            // в МОНИТОРЕ Shift не влияет. Влияет РУС/ЛАТ = НР_ФИКС.
            if ((XYmatr & 0x7000) == 0) {// если старший ниббл - заглавные символы  -> Shift не нажат
                Shiftk = false;//  Shift не нажат
            } else { // если нет старшего ниббла - строчные: старший бит = 1 -> нажат Shift
                Shiftk = down; // Shift нажат (отпущен)down
            }
        }
    }

    public void reset() {
        Shiftk = false; // Shift не нажат
        PrtAIN = true; // порт A - на ввод
        PrtBIN = true; // порт B - на ввод
        PrC0IN = true; // порт C0- на ввод
        PrC1IN = true; // порт C1- на ввод
        Arrays.sort(ascii_keys); // сортируем массив ascii-массив кодов клавиш для поиска
        resetKeyboard();// все кнопки ненажаты
        memory.write16(RgRYS, 0x009b);// порт RYC[0xFFE3] = 9Bh (все на ввод)
        memory.write16(RgRGB, 0x0020);// порт цвета - зелёный на черном.
    }
}