package spec;

import static spec.KeyCode.*;
import static spec.Keyboard.*;

public class Layout {

    public void setup(Keyboard keyboard) {
        // первая линия стандартной клавиатуры

        //keyboard.putNorm('À', 0x00); // первая клавиша ~/[Ё]
        //keyboard.putShft('À', 0x00);
        //keyboard.putCyrl('ё', 0x00);
        //keyboard.putCrSh('ё', 0x00);

        keyboard.putNorm('1',   H_1); // 1 -> 1
        keyboard.putShft('1',   H_1); // shift 1 -> !

        keyboard.putNorm('2',   H_2);     // 2 -> 2
        keyboard.putShft('2',   H_SYMB8); // shift 2 -> @ [Ю]
        keyboard.putAltS('2',   H_2);     // alt shift 2 -> "

        keyboard.putNorm('3',   H_3);     // 3 -> 3
        keyboard.putShft('3',   H_3);     // shift 3 -> #

        keyboard.putNorm('4',   H_4);     // 4 -> 4
        keyboard.putShft('4',   H_4);     // shift 4 -> $
        keyboard.putCtrl('4',   H_SYMB2); // ctrl 4 -> :
        keyboard.putAlt_('4',   H_SYMB3); // alt 4 -> ;

        keyboard.putNorm('5',   H_5); // 5 -> 5
        keyboard.putShft('5',   H_5); // shift 5 -> %

        keyboard.putNorm('6',   H_6); // 6 -> 6
        keyboard.putShft('6',   H_SYMB4); // shift 6 -> ^ [Ч]
        keyboard.putCtrl('6',   H_SYMB2); // ctrl 6 -> :
        keyboard.putAlt_('6',   H_SYMB3); // alt 6 -> ;

        keyboard.putNorm('7',   H_7);     // 7 -> 7
        keyboard.putShft('7',   H_6);     // shift 7 -> &
        keyboard.putAltS('7',   H_SYMB6); // alt shift 7 -> ?
        keyboard.putCtrlS('7',  H_7);     // ctrl shift 7 -> '

        keyboard.putNorm('8',   H_8);     // 8 -> 8
        keyboard.putShft('8',   H_SYMB2); // shift 8 -> *

        keyboard.putNorm('9',   H_9); // 9 -> 9
        keyboard.putShft('9',   H_8); // shift 9 -> (

        keyboard.putNorm('0',   H_SYMB11); // 0 -> 0
        keyboard.putShft('0',   H_9); // shift 0 -> )
        keyboard.putAltS('0',   H_SYMB11); // 0 -> ' '

        keyboard.putNorm('-',   H_SYMB9); // - -> -
        keyboard.putShft('-',   H_SYMB9); // shift - -> =

        keyboard.putNorm('=',   H_SYMB3); // = -> ;
        keyboard.putShft('=',   H_SYMB3); // shift = -> +
        keyboard.putCtrl('=',   H_SYMB2); // ctrl = -> :
        keyboard.putAlt_('=',   H_SYMB3); // alt = -> ;

        // вторая линия стандартной клавиатуры

        // Tab не определяется в Swing

        keyboard.putChar('Q',   H_Q,  //  Q  -> Q [Я]
                         'й',   H_J); // (Й) -> J [Й]
                                      // ctrl  Q  -> J [Й]
                                      // ctrl (Й) -> Я [Q]

        keyboard.putChar('W',   H_W,  //  W  -> W [В]
                         'ц',   H_C); // (Ц) -> С [Ц]
                                      // ctrl  W  -> С [Ц]
                                      // ctrl (Ц) -> В [W]

        keyboard.putChar('E',   H_E,  //  E  -> E [E]
                         'у',   H_U); // (У) -> U [У]
                                      // ctrl  E  -> U [У]
                                      // ctrl (У) -> E [E]

        keyboard.putChar('R',   H_R,  //  R  -> R [Р]
                         'к',   H_K); // (К) -> К [К]
                                      // ctrl  R  -> К [К]
                                      // ctrl (К) -> Р [R]

        keyboard.putChar('T',   H_T,  //  T  -> T [Т]
                         'е',   H_E); // (Е) -> Е [Е]
                                      // ctrl  T  -> Е [Е]
                                      // ctrl (Е) -> Т [T]

        keyboard.putChar('Y',   H_Y,  //  Y  -> Y [Ы]
                         'н',   H_N); // (Н) -> N [Н]
                                      // ctrl  Y  -> N [Н]
                                      // ctrl (Н) -> Ы [Y]

        keyboard.putChar('U',   H_U,  //  U  -> U [У]
                         'г',   H_G); // (Г) -> G [Г]
                                      // ctrl  U  -> G [Г]
                                      // ctrl (Г) -> У [U]

        keyboard.putChar('I',   H_I,  //  I  -> I [И]
                         'ш',   H_RIGHT_SQUARE_BRACKET); // (Ш) -> [ [Ш]
                                      // ctrl  I  -> [ [Ш]
                                      // ctrl (Ш) -> И [I]

        keyboard.putChar('O',   H_O,  //  O  -> O [О]
                         'щ',   H_LEFT_SQUARE_BRACKET); // (Щ) -> ] [Щ]
                                      // ctrl  O  -> ] [Щ]
                                      // ctrl (Щ) -> О [O]

        keyboard.putChar('P',   H_P,  //  P  -> P [П]
                         'з',   H_Z); // (З) -> Z [З]
                                      // ctrl  P  -> Z [З]
                                      // ctrl (З) -> П [P]

        keyboard.putChar('[',   H_RIGHT_SQUARE_BRACKET,  //  [  -> [ [Ш]
                         'х',   H_X); // (Х) -> Ь [Х]
                                      // ctrl  [  -> Ь [Х]
                                      // ctrl (Х) -> Ш [[]

        keyboard.putNorm(']',   H_LEFT_SQUARE_BRACKET); //  ]  -> ] [Щ]
        //keyboard.putCyrl('ъ', 0x00);  // нет такого знака

        // третья линия стандартной клавиатуры

        keyboard.putChar('A',   H_A,  //  A  -> A [А]
                         'ф',   H_F); // (Ф) -> F [Ф]
                                      // ctrl  A  -> F [Ф]
                                      // ctrl (Ф) -> А [A]

        keyboard.putChar('S',   H_S,  //  S  -> S [С]
                         'ы',   H_Y); // (Ы) -> Y [Ы]
                                      // ctrl  S  -> Y [Ы]
                                      // ctrl (Ы) -> С [S]

        keyboard.putChar('D',   H_D,  //  D  -> D [Д]
                         'в',   H_W); // (В) -> W [В]
                                      // ctrl  D  -> W [В]
                                      // ctrl (В) -> Д [D]

        keyboard.putChar('F',   H_F,  //  F  -> F [Ф]
                         'а',   H_A); // (А) -> A [А]
                                      // ctrl  F  -> A [А]
                                      // ctrl (А) -> Ф [F]

        keyboard.putChar('G',   H_G,  //  G  -> G [Г]
                         'п',   H_P); // (П) -> P [П]
                                      // ctrl  G  -> P [П]
                                      // ctrl (П) -> Г [G]

        keyboard.putChar('H',   H_H,  //  H  -> H [Х]
                         'р',   H_R); // (Р) -> R [Р]
                                      // ctrl  H  -> R [Р]
                                      // ctrl (Р) -> Х [H]

        keyboard.putChar('J',   H_J,  //  J  -> J [Й]
                         'о',   H_O); // (О) -> O [О]
                                      // ctrl  J  -> O [О]
                                      // ctrl (О) -> Й [J]

        keyboard.putChar('K',   H_K,  //  K  -> K [К]
                         'л',   H_L); // (Л) -> L [Л]
                                      // ctrl  K  -> L [Л]
                                      // ctrl (Л) -> К [K]

        keyboard.putChar('L',   H_L,  //  L  -> L [Л]
                         'д',   H_D); // (Д) -> D [Д]
                                      // ctrl  L  -> D [Д]
                                      // ctrl (Д) -> Л [L]

        keyboard.putChar(';',   H_SYMB2,  // ;   -> :
                         'ж',   H_V);     // (Ж) -> V [Ж]
                                          // ctrl  ;  -> V [Ж]
                                          // ctrl (Ж) -> :
        keyboard.putShft(';',   H_SYMB3); // shift ;  -> +
        keyboard.putAlt_(';',   H_SYMB3); // alt ; -> ;

        keyboard.putChar('Þ',   H_SYMB4,  // ' -> ^ [Ч]
                         'э',   H_SYMB5); // Э -> \ [Э]
                                          // ctrl  '  -> \ [Э]
                                          // ctrl (Э) -> Ч [^]
        keyboard.putShft('Þ',   H_2);     // shift ' -> "
        keyboard.putAltS('Þ',   H_7);     // alt shift ' -> '

        keyboard.putNorm('\\',  H_SYMB6); // \ -> /
        keyboard.putShft('\\',  H_SYMB5); // shift \ -> \ [Э]

        // четвертая линия стандартной клавиатуры

        // shift работает как системная клавиша

        keyboard.putChar('Z',   H_Z,  //  Z  -> Z [З]
                         'я',   H_Q); // (Я) -> Q [Я]
                                      // ctrl  Z  -> Q [Я]
                                      // ctrl (Я) -> З [Z]

        keyboard.putChar('X',   H_X,  //  X  -> X [Ь]
                         'ч',   H_SYMB4); // (Ч) -> ^ [Ч]
                                          // ctrl  X  -> ^ [Ч]
                                          // ctrl (Ч) -> Ь [X]

        keyboard.putChar('C',   H_C,  //  C  -> C [Ц]
                         'с',   H_S); // (С) -> S [С]
                                      // ctrl  C  -> S [С]
                                      // ctrl (С) -> Ц [C]

        keyboard.putChar('V',   H_V,  //  V  -> V [Ж]
                         'м',   H_M); // (М) -> M [М]
                                      // ctrl  V  -> M [М]
                                      // ctrl (М) -> Ж [V]

        keyboard.putChar('B',   H_B,  //  B  -> B [Б]
                         'и',   H_I); // (И) -> I [И]
                                      // ctrl  B  -> I [И]
                                      // ctrl (И) -> Б [B]

        keyboard.putChar('N',   H_N,  //  N  -> N [Н]
                         'т',   H_T); // (Т) -> T [Т]
                                      // ctrl  N  -> T [Т]
                                      // ctrl (Т) -> Н [N]

        keyboard.putChar('M',   H_M,  //  M  -> M [М]
                         'ь',   H_X); // (Ь) -> X [Ь]
                                      // ctrl  M  -> X [Ь]
                                      // ctrl (Ь) -> М [M]

        keyboard.putChar(',',   H_SYMB1,   //  ,  -> ,
                         'б',   H_B);      // (Б) -> B [Б]
                                           // ctrl  ,  -> B [Б]
                                           // ctrl (Б) -> ,
        keyboard.putShft(',',   H_SYMB1);  // shift , -> <
        keyboard.putCtrl(',',   H_SYMB7);  // ctrl , -> >

        keyboard.putChar('.',   H_SYMB7,   //  .  -> .
                         'ю',   H_SYMB8);  // (Ю) -> @ [Ю]
                                           // ctrl  .  -> @ [Ю]
                                           // ctrl (Ю) -> .
        keyboard.putShft('.',   H_SYMB7);  // shift . -> >
        keyboard.putCtrl('.',   H_SYMB1);  // ctrl . -> <

        keyboard.putNorm('/',   H_SYMB6); // /  -> /
        keyboard.putCtrl('/',   H_SYMB5); // ctrl /  -> \
        keyboard.putShft('/',   H_SYMB6); // shift / -> ?

        // другие клавиши

        keyboard.putNorm(ENTER,       H_ВК);      // enter -> ввод каретки
        keyboard.putAlt_((char)ENTER, H_ПС);      // enter -> перевод строки
        keyboard.putNorm(BACKSPACE,   H_ЗБ);      // backspace -> забой
        keyboard.putNorm(CAPS_LOCK,   H_РУС_ЛАТ); // caps_lock -> рус/лат

        keyboard.putNorm(ESC,     H_ESC);   // esc -> esc
        keyboard.putNorm(SPACE,   H_SPACE); // space -> space

        keyboard.putNorm(INS,     H_ПВ);    // ins -> повтор
        keyboard.putNorm(HOME,    H_HOME);  // home -> home
        keyboard.putNorm(PG_UP,   H_TAB);   // page up -> tab
        keyboard.putNorm(DEL,     H_ЗБ);    // delete -> забой
        keyboard.putNorm(END,     H_F11);   // end -> F11
        keyboard.putNorm(PG_DOWN, H_F10);   // page down -> F10

        keyboard.putNorm(UP,      H_UP);    // up -> вверх
        keyboard.putNorm(DOWN,    H_DOWN);  // down -> вних
        keyboard.putNorm(LEFT,    H_LEFT);  // left -> влево
        keyboard.putNorm(RIGHT,   H_RIGHT); // right -> вправо

        keyboard.putNorm(F1,  H_F1); // F1 -> F1
        keyboard.putNorm(F2,  H_F2); // F2 -> F2
        keyboard.putNorm(F3,  H_F3); // F3 -> F3
        keyboard.putNorm(F4,  H_F4); // F4 -> F4
        keyboard.putNorm(F5,  H_F5); // F5 -> F5
        keyboard.putNorm(F6,  H_F6); // F6 -> F6
        keyboard.putNorm(F7,  H_F7); // F7 -> F7
        keyboard.putNorm(F8,  H_F8); // F8 -> F8
        keyboard.putNorm(F9,  H_F9); // F9 -> F9
        keyboard.putNorm(F10, H_F10); // F10 -> F10
        keyboard.putNorm(F11, H_F11); // F11 -> F11
        keyboard.putNorm(F12, H_F12); // F -> F12

    } 
}