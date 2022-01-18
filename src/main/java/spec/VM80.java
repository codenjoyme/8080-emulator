package spec;

public abstract class VM80 extends Z80 {

    private static final int BASEnd = 0x4000; // ROM BASIC END.

    private static final int ATRBeg = 0x5800; // начало области аттрибутов
    private static final int ATREnd = 0x5aff; // конец области аттрибутов


    protected static final int ScrBeg = 0x9000; // 0x9000; начало области экрана 0x4000;
    private static final int ScrEnd = 0xbfff; // 0xbfff; конец области экрана  0x57ff;

    private static final int ROMBeg = 0xc000; // 0C000H; начало ПЗУ.
    protected static final int ROMEnd = 0xf7ff; // 0FFDFH; конец  ПЗУ. 0xffdf

    private static final int PortBeg = 0xf800;// начало портов: 0xffe0
    private static final int PortEnd = 0xfffe;// конец портов: 0xffff - память

    private static final int HiMem = 0x10000;// запредел памяти.

    public VM80(double clockFrequencyInMHz) {
        super(clockFrequencyInMHz);
    }

    private final String tmpstring = "";  // ВВЕЛИ ДЛЯ ОТЛАДКИ 

    // переопределим чтение памяти -
    @Override
    protected int peekb(int addr) {
        if (addr < (PortBeg))     // ПЗУ и ОЗУ Пользователя
        {
            //if( addr == (0xc037) ) {// Тест-перехват 0C037H - вывод симв.на экран [C]
            // String be = Integer.toHexString(_C) + "h, "; //
            // String be = ""+(char)(_C) + ",";
            // tmpstring = tmpstring + be.toUpperCase();
            //  tmpstring = tmpstring + be;
            //  showMessage(tmpstring); //
            // urlField.setVisible(true);   // show();
            // urlField.setText( tmpstring );
            //}
            return mem[addr];   // читаем байт
        }
        return inport(addr);     // возвращаем порт
    }

    protected abstract int inport(int addr);

    // ввод из порта
    // при вводе из порта клавиатуры записываем в ответ состояние переменных _B_SPC..._CAPS_V.
    // в зависимости от выбраной "0" линии адреса порта 7F.FE ... FE.FE.
    // сами значения переменных _B_SPC..._CAPS_V (строк матрицы) готовятся при обработке событий
    // клавиатуры handleEvent(Event e): Event.KEY_PRESS - КЛАВИША_НАЖАТА_, Event.KEY_RELEASE: -
    // КЛАВИША_ОТПУЩЕНА_. Эти события вызывают doKey( true/false, e.key, e.modifiers ), где
    // код клавиши (e.key) и служебные клавиши (e.modifiers) превращаются в значения переменных
    // _B_SPC..._CAPS_V.

    // ввод из порта
    @Override
    protected int inb(int port) {
        return 0xff;   
    }

    //***  pokeb для ПК "Специалист" 
    @Override
    protected void pokeb(int addr, int newByte) {
        if (addr > (ScrEnd))       // > 0xbfffh - выше Видео-ОЗУ = ПЗУ И ПОРТЫ.
        {
            // для ПК "Специалист" - ПОРТЫ 
            if (addr < (HiMem))      // <=0FFFFh - ПОРТЫ И ПЗУ
            {
                if (addr > (ROMEnd))   // > 0FFDFh - ПОРТЫ
                {
                    outPort(addr, newByte);// в ПОРТЫ пишем   0C000h < ПОРТЫ < 0FFFFh
                    return;
                } // ПОРТЫ кончились.
                else {
                    return; // в ПЗУ 0C000h-ROMEnd не пишем
                }
            } else {// addr вдруг больше 65536 (не может быть!) - укоротим его и продолжим.
                addr &= 0xffff;
            }
        }// далее - ОЗУ + Видео-ОЗУ ниже ПЗУ И ПОРТОВ.

        if (addr < ScrBeg)          // ОЗУ < 9000h
        {
            mem[addr] = newByte;    // в ОЗУ пишем   0000h < ОЗУ < 9000h
            return;                   // в ОЗУ пользователя пишем
        }// далее - только Видео-ОЗУ!

        if (mem[addr] != newByte) // правильно! повторно в Видео-ОЗУ записывать глупо!
        {//       newByte
            plot(addr, 0xffff);     // в Видео-ОЗУ рисуем  newByte не используется в plot()
            mem[addr] = newByte;    // в Видео-ОЗУ пишем как в ОЗУ чтобы читать
        }
    }

    protected abstract void plot(int addr, int newByte);

    protected abstract void outPort(int addr, int newByte);

    //***  pokew для ПК "Специалист" 
    @Override
    protected void pokew(int addr, int word) {
        int[] _mem = mem;
        if (addr > ScrEnd)       // > 0xbfffh - выше Видео-ОЗУ = ПЗУ И ПОРТЫ.
        {
            // для ПК "Специалист" - ПОРТЫ 
            if (addr < HiMem)        // <=0FFFFh - ПОРТЫ И ПЗУ
            {
                if (addr > ROMEnd)   // > 0FFDFh - ПОРТЫ
                {
                    outPort(addr, word & 0xff);// младший байт - пишем в ПОРТ
                    if (++addr != HiMem) { // != 65536
                        outPort(addr, word >> 8); // старший байт - пишем в ПОРТ
                        return; // старший байт обслужили - уходим!
                    } else { // старший байт вышел за HiMem -> addr=0000h;
                        addr &= 0xffff;
                        _mem[addr] = word >> 8; // старший байт - пишем в ОЗУ
                        return; // старший байт обслужили - уходим!
                    }
                }// ( addr > ROMEnd )
            }// ( addr < HiMem )
            else {// addr вдруг больше 65536 (не может быть!) - укоротим его.
                addr &= 0xffff; // продолжим с новым адресом
            }
        }// далее - ОЗУ + Видео-ОЗУ ниже ПЗУ И ПОРТОВ.


        if (addr < ScrBeg)            // ОЗУ < 9000h -
        {
            _mem[addr] = word & 0xff; // младший байт - пишем в ОЗУ
            if (++addr != ScrBeg) {    // если старший байт не попал в видео-ОЗУ.
                _mem[addr] = (word >> 8) & 0xff; // старший байт - пишем в ОЗУ.
                return;
            } else {// старший байт попал в видео-ОЗУ.
                int newByte1 = (word >> 8) & 0xff; // второй байт слова word
                if (_mem[addr] != newByte1) // правильно! повторно в Видео-ОЗУ записывать глупо!
                {
                    plot(addr, 0xffff);   // в Видео-ОЗУ рисуем  newByte не используется в plot()
                    _mem[addr] = newByte1;// в Видео-ОЗУ пишем как в ОЗУ чтобы читать
                }
                return;// старший байт обслужили - уходим!
            }
        }
        // далее - только Видео-ОЗУ!

        int newByte0 = word & 0xff;   // второй байт слова word
        if (_mem[addr] != newByte0) // правильно! повторно в Видео-ОЗУ записывать глупо!
        {
            plot(addr, 0xffff);   // в Видео-ОЗУ рисуем  newByte не используется в plot()
            _mem[addr] = newByte0;// в Видео-ОЗУ пишем как в ОЗУ чтобы читать
        }

        int newByte1 = word >> 8;// второй байт слова word
        if (++addr < (ROMBeg)) // ScrBeg < ++addr < ROMBeg;
        {
            if (_mem[addr] != newByte1) {
                plot(addr, 0xffff); // в Видео-ОЗУ рисуем   newByte1
                _mem[addr] = newByte1;
            }
        } else { // второй байт слова попал на ROM
            return; // в ROM не пишем
        }
    }
}