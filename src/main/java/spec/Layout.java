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

        // 1        ->  1
        // shift 1  ->  !
        keyboard.put(non('1'), non(H_1));
        keyboard.put(shf('1'), shf(H_1));

        // 2        ->  2
        // shift 2  ->  @ [Ю]                  // TODO от Ю надо как-то избавиться
        // alt   2  ->  "
        keyboard.put(non('2'), non(H_2));
        keyboard.put(shf('2'), non(H_SYMB8));
        keyboard.put(alt('2'), shf(H_2));      // TODO не работает

        // 3        ->  3
        // shift 3  ->  #
        keyboard.put(non('3'), non(H_3));
        keyboard.put(shf('3'), shf(H_3));

        // 4        ->  4
        // shift 4  ->  $
        // ctrl  4  ->  :
        // alt   4  ->  ;
        keyboard.put(non('4'), non(H_4));
        keyboard.put(shf('4'), shf(H_4));
        keyboard.put(ctr('4'), non(H_SYMB2));
        keyboard.put(alt('4'), non(H_SYMB3));

        // 5        ->  5
        // shift 5  ->  %
        keyboard.put(non('5'), non(H_5));
        keyboard.put(shf('5'), shf(H_5));

        // 6        ->  6
        // shift 6  ->  ^ [Ч]                   // TODO от Ч надо как-то избавиться
        // ctrl  6  ->  :
        // alt   6  ->  ;
        keyboard.put(non('6'), non(H_6));
        keyboard.put(shf('6'), non(H_SYMB4));
        keyboard.put(ctr('6'), non(H_SYMB2));
        keyboard.put(alt('6'), non(H_SYMB3));

        // 7        ->  7
        // shift 7  ->  &
        // alt   7  ->  ?
        // ctrl  7  ->  '
        keyboard.put(non('7'), non(H_7));
        keyboard.put(shf('7'), shf(H_6));
        keyboard.put(alt('7'), shf(H_SYMB6));
        keyboard.put(ctr('7'), shf(H_7));

        // 8        ->  8
        // shift 8  ->  *
        keyboard.put(non('8'), non(H_8));
        keyboard.put(shf('8'), non(H_SYMB2));

        // 9        ->  9
        // shift 9  ->  (
        keyboard.put(non('9'), non(H_9));
        keyboard.put(shf('9'), shf(H_8));

        // 0        ->  0
        // shift 0  ->  )
        // alt 0    ->  ' '
        keyboard.put(non('0'), non(H_SYMB11));
        keyboard.put(shf('0'), shf(H_9));
        keyboard.put(alt('0'), shf(H_SYMB11));

        // -        ->  -
        // shift -  ->  _ TODO должен быть прочерк, но его кода не нашел
        keyboard.put(non('-'), non(H_SYMB9));

        // =        ->  ;
        // shift =  ->  +
        // ctrl  =  ->  :
        // alt   =  ->  ;
        keyboard.put(non('='), non(H_SYMB3));
        keyboard.put(shf('='), non(H_SYMB3));
        keyboard.put(ctr('='), non(H_SYMB2));
        keyboard.put(alt('='), non(H_SYMB3));

        // вторая линия стандартной клавиатуры

        // Tab не определяется в Swing

        //  Q       ->  Q [Я]
        // (Й)      ->  J [Й]
        // ctrl  Q  ->  J [Й]
        // ctrl (Й) ->  Я [Q]
        keyboard.put('Q', H_Q,
                     'й', H_J);

        //  W       ->  W [В]
        // (Ц)      ->  С [Ц]
        // ctrl  W  ->  С [Ц]
        // ctrl (Ц) ->  В [W]
        keyboard.put('W', H_W,
                     'ц', H_C);

        //  E       ->  E [E]
        // (У)      ->  U [У]
        // ctrl  E  ->  U [У]
        // ctrl (У) ->  E [E]
        keyboard.put('E', H_E,
                     'у', H_U);

        //  R       ->  R [Р]
        // (К)      ->  К [К]
        // ctrl  R  ->  К [К]
        // ctrl (К) ->  Р [R]
        keyboard.put('R', H_R,
                     'к', H_K);

        //  T       ->  T [Т]
        // (Е)      ->  Е [Е]
        // ctrl  T  ->  Е [Е]
        // ctrl (Е) ->  Т [T]
        keyboard.put('T', H_T,
                     'е', H_E);

        //  Y       ->  Y [Ы]
        // (Н)      ->  N [Н]
        // ctrl  Y  ->  N [Н]
        // ctrl (Н) ->  Ы [Y]
        keyboard.put('Y', H_Y,
                     'н', H_N);

        //  U       ->  U [У]
        // (Г)      ->  G [Г]
        // ctrl  U  ->  G [Г]
        // ctrl (Г) ->  У [U]
        keyboard.put('U', H_U,
                     'г', H_G);

        //  I       ->  I [И]
        // (Ш)      ->  [ [Ш]
        // ctrl  I  ->  [ [Ш]
        // ctrl (Ш) ->  И [I]
        keyboard.put('I', H_I,
                     'ш', H_RIGHT_SQUARE_BRACKET);

        //  O       ->  O [О]
        // (Щ)      ->  ] [Щ]
        // ctrl  O  ->  ] [Щ]
        // ctrl (Щ) ->  О [O]
        keyboard.put('O', H_O,
                     'щ', H_LEFT_SQUARE_BRACKET);

        //  P       ->  P [П]
        // (З)      ->  Z [З]
        // ctrl  P  ->  Z [З]
        // ctrl (З) ->  П [P]
        keyboard.put('P', H_P,
                     'з', H_Z);

        //  [       ->  [ [Ш]
        // (Х)      ->  Ь [Х]
        // ctrl  [  ->  Ь [Х]
        // ctrl (Х) ->  Ш [[]
        keyboard.put('[', H_RIGHT_SQUARE_BRACKET,
                     'х', H_X);

        //  ]       ->  ] [Щ]
        keyboard.put(non(']'), non(H_LEFT_SQUARE_BRACKET));
        // keyboard.put(cyr('ъ'), 0x00);  // нет такого знака

        // третья линия стандартной клавиатуры

        //  A       ->  A [А]
        // (Ф)      ->  F [Ф]
        // ctrl  A  ->  F [Ф]
        // ctrl (Ф) ->  А [A]
        keyboard.put('A', H_A,
                     'ф', H_F);

        //  S       ->  S [С]
        // (Ы)      ->  Y [Ы]
        // ctrl  S  ->  Y [Ы]
        // ctrl (Ы) ->  С [S]
        keyboard.put('S', H_S,
                     'ы', H_Y);

        //  D       ->  D [Д]
        // (В)      ->  W [В]
        // ctrl  D  ->  W [В]
        // ctrl (В) ->  Д [D]
        keyboard.put('D', H_D,
                     'в', H_W);

        //  F       ->  F [Ф]
        // (А)      ->  A [А]
        // ctrl  F  ->  A [А]
        // ctrl (А) ->  Ф [F]
        keyboard.put('F', H_F,
                     'а', H_A);

        //  G       ->  G [Г]
        // (П)      ->  P [П]
        // ctrl  G  ->  P [П]
        // ctrl (П) ->  Г [G]
        keyboard.put('G', H_G,
                     'п', H_P);

        //  H       ->  H [Х]
        // (Р)      ->  R [Р]
        // ctrl  H  ->  R [Р]
        // ctrl (Р) ->  Х [H]
        keyboard.put('H', H_H,
                     'р', H_R);

        //  J       ->  J [Й]
        // (О)      ->  O [О]
        // ctrl  J  ->  O [О]
        // ctrl (О) ->  Й [J]
        keyboard.put('J', H_J,
                     'о', H_O);

        //  K       ->  K [К]
        // (Л)      ->  L [Л]
        // ctrl  K  ->  L [Л]
        // ctrl (Л) ->  К [K]
        keyboard.put('K', H_K,
                     'л', H_L);

        //  L       ->  L [Л]
        // (Д)      ->  D [Д]
        // ctrl  L  ->  D [Д]
        // ctrl (Д) ->  Л [L]
        keyboard.put('L', H_L,
                     'д', H_D);

        //  ;       ->  : [Ж]
        // (Ж)      ->  V [Ж]
        // ctrl  ;  ->  V [Ж]
        // ctrl (Ж) ->  : [;]
        // shift ;  ->  +
        // alt   ;  ->  ;
        keyboard.put(';', H_SYMB2,
                     'ж', H_V);
        keyboard.put(shf(';'), non(H_SYMB3));
        keyboard.put(alt(';'), non(H_SYMB3));

        // '        ->  ^ [Ч]
        // (Э)      ->  \ [Э]
        // ctrl  '  ->  \ [Э]
        // ctrl (Э) ->  Ч [^]
        // shift '  ->  "
        // alt   '  ->  '
        keyboard.put('Þ', H_SYMB4,
                     'э', H_SYMB5);
        keyboard.put(shf('Þ'), shf(H_2));
        keyboard.put(alt('Þ'), shf(H_7));

        // \        ->  /
        // shift \  ->  \ [Э]
        keyboard.put(non('\\'), non(H_SYMB6));
        keyboard.put(shf('\\'), shf(H_SYMB5));

        // четвертая линия стандартной клавиатуры

        //  Z       ->  Z [З]
        // (Я)      ->  Q [Я]
        // ctrl  Z  ->  Q [Я]
        // ctrl (Я) ->  З [Z]
        keyboard.put('Z', H_Z,
                     'я', H_Q);

        //  X       ->  X [Ь]
        // (Ч)      ->  ^ [Ч]
        // ctrl  X  ->  ^ [Ч]
        // ctrl (Ч) ->  Ь [X]
        keyboard.put('X', H_X,
                     'ч', H_SYMB4);

        //  C       ->  C [Ц]
        // (С)      ->  S [С]
        // ctrl  C  ->  S [С]
        // ctrl (С) ->  Ц [C]
        keyboard.put('C', H_C,
                     'с', H_S);

        //  V       ->  V [Ж]
        // (М)      ->  M [М]
        // ctrl  V  ->  M [М]
        // ctrl (М) ->  Ж [V]
        keyboard.put('V', H_V,
                     'м', H_M);

        //  B       ->  B [Б]
        // (И)      ->  I [И]
        // ctrl  B  ->  I [И]
        // ctrl (И) ->  Б [B]
        keyboard.put('B', H_B,
                     'и', H_I);

        //  N       ->  N [Н]
        // (Т)      ->  T [Т]
        // ctrl  N  ->  T [Т]
        // ctrl (Т) ->  Н [N]
        keyboard.put('N', H_N,
                     'т', H_T);

        //  M       ->  M [М]
        // (Ь)      ->  X [Ь]
        // ctrl  M  ->  X [Ь]
        // ctrl (Ь) ->  М [M]
        keyboard.put('M', H_M,
                     'ь', H_X);

        //  ,       ->  ,
        // (Б)      ->  B [Б]
        // ctrl  ,  ->  B [Б]
        // ctrl (Б) ->  ,
        // shift ,  ->  <
        // ctrl  ,  ->  >
        keyboard.put(',', H_SYMB1,
                     'б', H_B);
        keyboard.put(shf(','), shf(H_SYMB1));
        keyboard.put(ctr(','), non(H_SYMB7));

        //  .       ->  .
        // (Ю)      ->  @ [Ю]
        // ctrl  .  ->  @ [Ю]
        // ctrl (Ю) ->  .
        // shift .  ->  >
        // ctrl .   ->  <
        keyboard.put('.', H_SYMB7,
                     'ю', H_SYMB8);
        keyboard.put(shf('.'), shf(H_SYMB7));
        keyboard.put(ctr('.'), non(H_SYMB1));

        // /        ->  /
        // ctrl  /  ->  \
        // shift /  ->  ?
        keyboard.put(non('/'), non(H_SYMB6));
        keyboard.put(ctr('/'), non(H_SYMB5));
        keyboard.put(shf('/'), shf(H_SYMB6));

        // другие клавиши

        keyboard.put(ENTER,       non(H_ВК));      // enter     -> ввод каретки
        keyboard.put(alt((char)ENTER), non(H_ПС)); // alt enter -> перевод строки
        keyboard.put(BACKSPACE,   non(H_ЗБ));      // backspace -> забой
        keyboard.put(CAPS_LOCK,   non(H_РУС_ЛАТ)); // caps_lock -> рус/лат

        keyboard.put(ESC,     non(H_ESC));   // esc   ->  esc
        keyboard.put(SPACE,   non(H_SPACE)); // space ->  space

        keyboard.put(INS,     non(H_ПВ));    // ins       ->  повтор
        keyboard.put(HOME,    non(H_HOME));  // home      ->  home
        keyboard.put(PG_UP,   non(H_TAB));   // page up   ->  tab
        keyboard.put(DEL,     non(H_ЗБ));    // delete    ->  забой
        keyboard.put(END,     non(H_F11));   // end       ->  F11
        keyboard.put(PG_DOWN, non(H_F10));   // page down ->  F10

        keyboard.put(UP,      non(H_UP));    // up    ->  вверх
        keyboard.put(DOWN,    non(H_DOWN));  // down  ->  вних
        keyboard.put(LEFT,    non(H_LEFT));  // left  ->  влево
        keyboard.put(RIGHT,   non(H_RIGHT)); // right ->  вправо

        keyboard.put(F1,  non(H_F1));  // F1  ->  F1
        keyboard.put(F2,  non(H_F2));  // F2  ->  F2
        keyboard.put(F3,  non(H_F3));  // F3  ->  F3
        keyboard.put(F4,  non(H_F4));  // F4  ->  F4
        keyboard.put(F5,  non(H_F5));  // F5  ->  F5
        keyboard.put(F6,  non(H_F6));  // F6  ->  F6
        keyboard.put(F7,  non(H_F7));  // F7  ->  F7
        keyboard.put(F8,  non(H_F8));  // F8  ->  F8
        keyboard.put(F9,  non(H_F9));  // F9  ->  F9
        keyboard.put(F10, non(H_F10)); // F10 ->  F10
        keyboard.put(F11, non(H_F11)); // F11 ->  F11
        keyboard.put(F12, non(H_F12)); // F12 ->  F12

    } 
}