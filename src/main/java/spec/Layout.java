package spec;

public class Layout {
    
    public void setup(Keyboard keyboard) {
        // первая линия стандартной клавиатуры

        //keyboard.putNorm('À', 0x00); // первая клавиша ~/[Ё]
        //keyboard.putShft('À', 0x00);
        //keyboard.putCyrl('ё', 0x00);
        //keyboard.putCrSh('ё', 0x00);

        keyboard.putNorm('1', 0xA4); // 1 -> 1
        keyboard.putShft('1', 0xA4); // shift 1 -> !

        keyboard.putNorm('2', 0x94); // 2 -> 2
        keyboard.putShft('2', 0x31); // shift 2 -> @ [Ю]
        keyboard.putAlLS('2', 0x94); // left_alt shift 2 -> "

        keyboard.putNorm('3', 0x84); // 3 -> 3
        keyboard.putShft('3', 0x84); // # -> #

        keyboard.putNorm('4', 0x74); // 4 -> 4
        keyboard.putShft('4', 0x74); // shift 4 -> $
        keyboard.putCtrl('4', 0x03); // ctrl 4 -> :
        keyboard.putAltL('4', 0xB4); // left_alt 4 -> ;

        keyboard.putNorm('5', 0x64); // 5 -> 5
        keyboard.putShft('5', 0x64); // shift 5 -> %

        keyboard.putNorm('6', 0x54); // 6 -> 6
        keyboard.putShft('6', 0xA1); // shift 6 -> ^ [Ч]
        keyboard.putCtrl('6', 0x03); // ctrl 6 -> :
        keyboard.putAltL('6', 0xB4); // left_alt 6 -> ;

        keyboard.putNorm('7', 0x44); // 7 -> 7
        keyboard.putShft('7', 0x54); // shift 7 -> &
        keyboard.putAlLS('7', 0x11); // left_alt shift 7 -> ?

        keyboard.putNorm('8', 0x34); // 8 -> 8
        keyboard.putShft('8', 0x03); // shift 8 -> *

        keyboard.putNorm('9', 0x24); // 9 -> 9
        keyboard.putShft('9', 0x34); // shift 9 -> (

        keyboard.putNorm('0', 0x14); // 0 -> 0
        keyboard.putShft('0', 0x24); // shift 0 -> )

        keyboard.putNorm('-', 0x04); // - -> -
        keyboard.putShft('-', 0x04); // shift - -> =

        keyboard.putNorm('=', 0xB4); // = -> ;
        keyboard.putShft('=', 0xB4); // shift = -> +
        keyboard.putCtrl('=', 0x03); // ctrl = -> :
        keyboard.putAltL('=', 0xB4); // left_alt = -> ;

        // вторая линия стандартной клавиатуры

        // Tab не определяется в Swing

        keyboard.putChar('Q', 0xB1,  //  Q  -> Q [Я]
                         'й', 0xB3); // (Й) -> J [Й]
                                     // ctrl  Q  -> J [Й]
                                     // ctrl (Й) -> Я [Q]

        keyboard.putChar('W', 0x92,  //  W  -> W [В]
                         'ц', 0xA3); // (Ц) -> С [Ц]
                                     // ctrl  W  -> С [Ц]
                                     // ctrl (Ц) -> В [W]

        keyboard.putChar('E', 0x73,  //  E  -> E [E]
                         'у', 0x93); // (У) -> U [У]
                                     // ctrl  E  -> U [У]
                                     // ctrl (У) -> E [E]

        keyboard.putChar('R', 0x62,  //  R  -> R [Р]
                         'к', 0x83); // (К) -> К [К]
                                     // ctrl  R  -> К [К]
                                     // ctrl (К) -> Р [R]

        keyboard.putChar('T', 0x61,  //  T  -> T [Т]
                         'е', 0x73); // (Е) -> Е [Е]
                                     // ctrl  T  -> Е [Е]
                                     // ctrl (Е) -> Т [T]

        keyboard.putChar('Y', 0xA2,  //  Y  -> Y [Ы]
                         'н', 0x63); // (Н) -> N [Н]
                                     // ctrl  Y  -> N [Н]
                                     // ctrl (Н) -> Ы [Y]

        keyboard.putChar('U', 0x93,  //  U  -> U [У]
                         'г', 0x53); // (Г) -> G [Г]
                                     // ctrl  U  -> G [Г]
                                     // ctrl (Г) -> У [U]

        keyboard.putChar('I', 0x71,  //  I  -> I [И]
                         'ш', 0x43); // (Ш) -> [ [Ш]
                                     // ctrl  I  -> [ [Ш]
                                     // ctrl (Ш) -> И [I]

        keyboard.putChar('O', 0x52,  //  O  -> O [О]
                         'щ', 0x33); // (Щ) -> ] [Щ]
                                     // ctrl  O  -> ] [Щ]
                                     // ctrl (Щ) -> О [O]

        keyboard.putChar('P', 0x72,  //  P  -> P [П]
                         'з', 0x23); // (З) -> Z [З]
                                     // ctrl  P  -> Z [З]
                                     // ctrl (З) -> П [P]

        keyboard.putChar('[', 0x43,  //  [  -> [ [Ш]
                         'х', 0x51); // (Х) -> Ь [Х]
                                     // ctrl  [  -> Ь [Х]
                                     // ctrl (Х) -> Ш [[]

        keyboard.putNorm(']', 0x33); //  ]  -> ] [Щ]
        //keyboard.putCyrl('ъ', 0x00);  // нет такого знака

        // третья линия стандартной клавиатуры

        keyboard.putChar('A', 0x82,  //  A  -> A [А]
                         'ф', 0xB2); // (Ф) -> F [Ф]
                                     // ctrl  A  -> F [Ф]
                                     // ctrl (Ф) -> А [A]

        keyboard.putChar('S', 0x91,  //  S  -> S [С]
                         'ы', 0xA2); // (Ы) -> Y [Ы]
                                     // ctrl  S  -> Y [Ы]
                                     // ctrl (Ы) -> С [S]

        keyboard.putChar('D', 0x32,  //  D  -> D [Д]
                         'в', 0x92); // (В) -> W [В]
                                     // ctrl  D  -> W [В]
                                     // ctrl (В) -> Д [D]

        keyboard.putChar('F', 0xB2,  //  F  -> F [Ф]
                         'а', 0x82); // (А) -> A [А]
                                     // ctrl  F  -> A [А]
                                     // ctrl (А) -> Ф [F]

        keyboard.putChar('G', 0x53,  //  G  -> G [Г]
                         'п', 0x72); // (П) -> P [П]
                                     // ctrl  G  -> P [П]
                                     // ctrl (П) -> Г [G]

        keyboard.putChar('H', 0x13,  //  H  -> H [Х]
                         'р', 0x62); // (Р) -> R [Р]
                                     // ctrl  H  -> R [Р]
                                     // ctrl (Р) -> Х [H]

        keyboard.putChar('J', 0xB3,  //  J  -> J [Й]
                         'о', 0x52); // (О) -> O [О]
                                     // ctrl  J  -> O [О]
                                     // ctrl (О) -> Й [J]

        keyboard.putChar('K', 0x83,  //  K  -> K [К]
                         'л', 0x42); // (Л) -> L [Л]
                                     // ctrl  K  -> L [Л]
                                     // ctrl (Л) -> К [K]

        keyboard.putChar('L', 0x42,  //  L  -> L [Л]
                         'д', 0x32); // (Д) -> D [Д]
                                     // ctrl  L  -> D [Д]
                                     // ctrl (Д) -> Л [L]

        keyboard.putChar(';', 0x03,  // ;   -> :
                         'ж', 0x22); // (Ж) -> V [Ж]
                                     // ctrl  ;  -> V [Ж]
                                     // ctrl (Ж) -> :
        keyboard.putShft(';', 0xB4); // shift ;  -> +
        keyboard.putAltL(';', 0xB4); // left_alt ; -> ;

        keyboard.putChar('Þ', 0xA1,  // ' -> ^ [Ч]
                         'э', 0x12); // Э -> \ [Э]
                                     // ctrl  '  -> \ [Э]
                                     // ctrl (Э) -> Ч [^]
        keyboard.putShft('Þ', 0x94); // shift ' -> "

        keyboard.putNorm('\\', 0x11); // \ -> /
        keyboard.putShft('\\', 0x12); // shift \ -> \ [Э]

        // четвертая линия стандартной клавиатуры

        // shift работает как системная клавиша

        keyboard.putChar('Z', 0x23,  //  Z  -> Z [З]
                         'я', 0xB1); // (Я) -> Q [Я]
                                     // ctrl  Z  -> Q [Я]
                                     // ctrl (Я) -> З [Z]

        keyboard.putChar('X', 0x51,  //  X  -> X [Ь]
                         'ч', 0xA1); // (Ч) -> ^ [Ч]
                                     // ctrl  X  -> ^ [Ч]
                                     // ctrl (Ч) -> Ь [X]

        keyboard.putChar('C', 0xA3,  //  C  -> C [Ц]
                         'с', 0x91); // (С) -> S [С]
                                     // ctrl  C  -> S [С]
                                     // ctrl (С) -> Ц [C]

        keyboard.putChar('V', 0x22,  //  V  -> V [Ж]
                         'м', 0x81); // (М) -> M [М]
                                     // ctrl  V  -> M [М]
                                     // ctrl (М) -> Ж [V]

        keyboard.putChar('B', 0x41,  //  B  -> B [Б]
                         'и', 0x71); // (И) -> I [И]
                                     // ctrl  B  -> I [И]
                                     // ctrl (И) -> Б [B]

        keyboard.putChar('N', 0x63,  //  N  -> N [Н]
                         'т', 0x61); // (Т) -> T [Т]
                                     // ctrl  N  -> T [Т]
                                     // ctrl (Т) -> Н [N]

        keyboard.putChar('M', 0x81,  //  M  -> M [М]
                         'ь', 0x51); // (Ь) -> X [Ь]
                                     // ctrl  M  -> X [Ь]
                                     // ctrl (Ь) -> М [M]

        keyboard.putChar(',', 0x21,  //  ,  -> ,
                         'б', 0x41); // (Б) -> B [Б]
                                     // ctrl  ,  -> B [Б]
                                     // ctrl (Б) -> ,
        keyboard.putShft(',', 0x21); // shift , -> <
        keyboard.putCtrl(',', 0x02); // ctrl , -> >

        keyboard.putChar('.', 0x02,  //  .  -> .
                         'ю', 0x31); // (Ю) -> @ [Ю]
                                     // ctrl  .  -> @ [Ю]
                                     // ctrl (Ю) -> .
        keyboard.putShft('.', 0x02); // shift . -> >
        keyboard.putCtrl('.', 0x21); // ctrl . -> <

        keyboard.putNorm('/', 0x11); // /  -> /
        keyboard.putCtrl('/', 0x12); // ctrl /  -> \
        keyboard.putShft('/', 0x11); // shift / -> ?

        // другие клавиши

        keyboard.putNorm(0x0A, 0x00); // enter -> enter
        keyboard.putNorm(0x08, 0x01); // backspace -> backspace
        keyboard.putNorm(0x14, 0xB0);  // caps_lock -> RusLat

        keyboard.putNorm(0x1B, 0x70); // esc -> esc
        keyboard.putNorm(' ', 0x50);  // space -> space

        keyboard.putNorm(0x9B, 0x30); // ins -> ПВ
        keyboard.putNorm(0x24, 0xA0); // home -> home
        keyboard.putNorm(0x24, 0x60); // page up -> tab
        keyboard.putNorm(0x7F, 0x01); // delete -> backspace
        keyboard.putNorm(0x23, 0x05); // end -> F11
        keyboard.putNorm(0x22, 0x15); // page down -> F10

        keyboard.putNorm(0x26, 0x90); // up -> up
        keyboard.putNorm(0x28, 0x80); // down -> down
        keyboard.putNorm(0x25, 0x40); // left -> left
        keyboard.putNorm(0x27, 0x20); // right -> right

        keyboard.putNorm(0x70, 0xA5); // F1 -> F1
        keyboard.putNorm(0x71, 0x95); // F2 -> F2
        keyboard.putNorm(0x72, 0x85); // F3 -> F3
        keyboard.putNorm(0x73, 0x75); // F4 -> F4
        keyboard.putNorm(0x74, 0x65); // F5 -> F5
        keyboard.putNorm(0x75, 0x55); // F6 -> F6
        keyboard.putNorm(0x76, 0x45); // F7 -> F7
        keyboard.putNorm(0x77, 0x35); // F8 -> F8
        keyboard.putNorm(0x78, 0x25); // F9 -> F9
        keyboard.putNorm(0x79, 0x15); // F10 -> F10
        keyboard.putNorm(0x7A, 0x05); // F11 -> F11
        keyboard.putNorm(0x7B, 0xB5); // F -> F12
    } 
}