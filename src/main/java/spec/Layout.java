package spec;

import org.apache.commons.lang3.tuple.Pair;

import static spec.KeyCode.*;
import static spec.Keyboard.*;

public class Layout extends AbstractLayout {

    public Pair<Integer, Boolean> get(int code, boolean rusLat) {
        Pair<Integer, Boolean> result = null;

        // первая линия стандартной клавиатуры

        //keyboard.putNorm('À', 0x00); // первая клавиша ~/[Ё]
        //keyboard.putShft('À', 0x00);
        //keyboard.putCyrl('ё', 0x00);
        //keyboard.putCrSh('ё', 0x00);

        // 1        ->  1
        // shift 1  ->  !
        if (code == non('1')) return nonH(H_1);
        if (code == shf('1')) return shfH(H_1);

        // 2        ->  2
        // shift 2  ->  @ [Ю]                  // TODO от Ю надо как-то избавиться
        // alt   2  ->  "
        if (code == non('2')) return nonH(H_2);
        if (code == shf('2')) return nonH(H_SYMB8);
        if (code == alt('2')) return shfH(H_2);

        // 3        ->  3
        // shift 3  ->  #
        if (code == non('3')) return nonH(H_3);
        if (code == shf('3')) return shfH(H_3);

        // 4        ->  4
        // shift 4  ->  $
        // ctrl  4  ->  ;
        // alt   4  ->  :
        if (code == non('4')) return nonH(H_4);
        if (code == shf('4')) return shfH(H_4);
        if (code == ctr('4')) return nonH(H_SYMB3);
        if (code == alt('4')) return nonH(H_SYMB2);

        // 5        ->  5
        // shift 5  ->  %
        if (code == non('5')) return nonH(H_5);
        if (code == shf('5')) return shfH(H_5);

        // 6        ->  6
        // shift 6  ->  ^ [Ч]                   // TODO от Ч надо как-то избавиться
        // ctrl  6  ->  :
        // alt   6  ->  ;
        if (code == non('6')) return nonH(H_6);
        if (code == shf('6')) return nonH(H_SYMB4);
        if (code == ctr('6')) return nonH(H_SYMB2);
        if (code == alt('6')) return nonH(H_SYMB3);

        // 7        ->  7
        // shift 7  ->  &
        // alt   7  ->  ?
        // ctrl  7  ->  '
        if (code == non('7')) return nonH(H_7);
        if (code == shf('7')) return shfH(H_6);
        if (code == alt('7')) return shfH(H_SYMB6);
        if (code == ctr('7')) return shfH(H_7);

        // 8        ->  8
        // shift 8  ->  *
        if (code == non('8')) return nonH(H_8);
        if (code == shf('8')) return shfH(H_SYMB2);

        // 9        ->  9
        // shift 9  ->  (
        if (code == non('9')) return nonH(H_9);
        if (code == shf('9')) return shfH(H_8);

        // 0        ->  0
        // shift 0  ->  )
        // alt 0    ->  ' '
        if (code == non('0')) return nonH(H_SYMB11);
        if (code == shf('0')) return shfH(H_9);
        if (code == alt('0')) return shfH(H_SYMB11);

        // -        ->  -
        // shift -  ->  _ TODO должен быть прочерк, но его кода не нашел
        if (code == non('-')) return nonH(H_SYMB9);

        // =        ->  =
        // shift =  ->  +
        if (code == non('=')) return shfH(H_SYMB9);
        if (code == shf('=')) return shfH(H_SYMB3);

        // вторая линия стандартной клавиатуры

        // Tab не определяется в Swing

        //  Q       ->  Q [Я]
        // (Й)      ->  J [Й]
        // ctrl  Q  ->  J [Й]
        // ctrl (Й) ->  Я [Q]
        result = is(code, rusLat, 
                'Q', H_Q,
                'й', H_J);
        if (result != null) return result;

        //  W       ->  W [В]
        // (Ц)      ->  С [Ц]
        // ctrl  W  ->  С [Ц]
        // ctrl (Ц) ->  В [W]
        result = is(code, rusLat, 
                'W', H_W,
                'ц', H_C);
        if (result != null) return result;

        //  E       ->  E [E]
        // (У)      ->  U [У]
        // ctrl  E  ->  U [У]
        // ctrl (У) ->  E [E]
        result = is(code, rusLat, 
                'E', H_E,
                'у', H_U);
        if (result != null) return result;

        //  R       ->  R [Р]
        // (К)      ->  К [К]
        // ctrl  R  ->  К [К]
        // ctrl (К) ->  Р [R]
        result = is(code, rusLat, 
                'R', H_R,
                'к', H_K);
        if (result != null) return result;

        //  T       ->  T [Т]
        // (Е)      ->  Е [Е]
        // ctrl  T  ->  Е [Е]
        // ctrl (Е) ->  Т [T]
        result = is(code, rusLat, 
                'T', H_T,
                'е', H_E);
        if (result != null) return result;

        //  Y       ->  Y [Ы]
        // (Н)      ->  N [Н]
        // ctrl  Y  ->  N [Н]
        // ctrl (Н) ->  Ы [Y]
        result = is(code, rusLat, 
                'Y', H_Y,
                'н', H_N);
        if (result != null) return result;

        //  U       ->  U [У]
        // (Г)      ->  G [Г]
        // ctrl  U  ->  G [Г]
        // ctrl (Г) ->  У [U]
        result = is(code, rusLat, 
                'U', H_U,
                'г', H_G);
        if (result != null) return result;

        //  I       ->  I [И]
        // (Ш)      ->  [ [Ш]
        // ctrl  I  ->  [ [Ш]
        // ctrl (Ш) ->  И [I]
        result = is(code, rusLat, 
                'I', H_I,
                'ш', H_RIGHT_SQUARE_BRACKET);
        if (result != null) return result;

        //  O       ->  O [О]
        // (Щ)      ->  ] [Щ]
        // ctrl  O  ->  ] [Щ]
        // ctrl (Щ) ->  О [O]
        result = is(code, rusLat, 
                'O', H_O,
                'щ', H_LEFT_SQUARE_BRACKET);
        if (result != null) return result;

        //  P       ->  P [П]
        // (З)      ->  Z [З]
        // ctrl  P  ->  Z [З]
        // ctrl (З) ->  П [P]
        result = is(code, rusLat, 
                'P', H_P,
                'з', H_Z);
        if (result != null) return result;

        //  [       ->  [ [Ш]
        // (Х)      ->  Ь [Х]
        // ctrl  [  ->  Ь [Х]
        // ctrl (Х) ->  Ш [[]
        result = is(code, rusLat, 
                '[', H_RIGHT_SQUARE_BRACKET,
                'х', H_X);
        if (result != null) return result;

        //  ]       ->  ] [Щ]
        if (code == non(']')) return nonH(H_LEFT_SQUARE_BRACKET);
        // if (code == cyr('ъ')) return 0x00);  // нет такого знака

        // третья линия стандартной клавиатуры

        //  A       ->  A [А]
        // (Ф)      ->  F [Ф]
        // ctrl  A  ->  F [Ф]
        // ctrl (Ф) ->  А [A]
        result = is(code, rusLat, 
                'A', H_A,
                'ф', H_F);
        if (result != null) return result;

        //  S       ->  S [С]
        // (Ы)      ->  Y [Ы]
        // ctrl  S  ->  Y [Ы]
        // ctrl (Ы) ->  С [S]
        result = is(code, rusLat, 
                'S', H_S,
                'ы', H_Y);
        if (result != null) return result;

        //  D       ->  D [Д]
        // (В)      ->  W [В]
        // ctrl  D  ->  W [В]
        // ctrl (В) ->  Д [D]
        result = is(code, rusLat, 
                'D', H_D,
                'в', H_W);
        if (result != null) return result;

        //  F       ->  F [Ф]
        // (А)      ->  A [А]
        // ctrl  F  ->  A [А]
        // ctrl (А) ->  Ф [F]
        result = is(code, rusLat, 
                'F', H_F,
                'а', H_A);
        if (result != null) return result;

        //  G       ->  G [Г]
        // (П)      ->  P [П]
        // ctrl  G  ->  P [П]
        // ctrl (П) ->  Г [G]
        result = is(code, rusLat, 
                'G', H_G,
                'п', H_P);
        if (result != null) return result;

        //  H       ->  H [Х]
        // (Р)      ->  R [Р]
        // ctrl  H  ->  R [Р]
        // ctrl (Р) ->  Х [H]
        result = is(code, rusLat, 
                'H', H_H,
                'р', H_R);
        if (result != null) return result;

        //  J       ->  J [Й]
        // (О)      ->  O [О]
        // ctrl  J  ->  O [О]
        // ctrl (О) ->  Й [J]
        result = is(code, rusLat, 
                'J', H_J,
                'о', H_O);
        if (result != null) return result;

        //  K       ->  K [К]
        // (Л)      ->  L [Л]
        // ctrl  K  ->  L [Л]
        // ctrl (Л) ->  К [K]
        result = is(code, rusLat, 
                'K', H_K,
                'л', H_L);
        if (result != null) return result;

        //  L       ->  L [Л]
        // (Д)      ->  D [Д]
        // ctrl  L  ->  D [Д]
        // ctrl (Д) ->  Л [L]
        result = is(code, rusLat, 
                'L', H_L,
                'д', H_D);
        if (result != null) return result;

        //  ;       ->  ;
        // shift ;  ->  :
        // (Ж)      ->  V [Ж]
        // ctrl  ;  ->  V [Ж]
        // ctrl (Ж) ->  : [;]
        result = is(code, rusLat, 
                ';', H_SYMB3,          // TODO тут с Ж проблемы вместо нее печатается ;
                'ж', H_V);
        if (result != null) return result;
        if (code == shf(';')) return nonH(H_SYMB2);

        // '        ->  '
        // shift '  ->  "
        if (code == non('Þ')) return shfH(H_7);
        if (code == shf('Þ')) return shfH(H_2);
        //if (code == cyr('э')) return      nonH(H_SYMB5);  // TODO разобраться с Э
        //if (code == ctr(cyr('э'))) return nonH(H_SYMB4);

        // \        ->  \ [Э]
        // ctrl \   ->  /
        if (code == non('\\')) return shfH(H_SYMB5);
        if (code == ctr('\\')) return nonH(H_SYMB6);

        // четвертая линия стандартной клавиатуры

        //  Z       ->  Z [З]
        // (Я)      ->  Q [Я]
        // ctrl  Z  ->  Q [Я]
        // ctrl (Я) ->  З [Z]
        result = is(code, rusLat, 
                'Z', H_Z,
                'я', H_Q);
        if (result != null) return result;

        //  X       ->  X [Ь]
        // (Ч)      ->  ^ [Ч]
        // ctrl  X  ->  ^ [Ч]
        // ctrl (Ч) ->  Ь [X]
        result = is(code, rusLat, 
                'X', H_X,
                'ч', H_SYMB4);
        if (result != null) return result;

        //  C       ->  C [Ц]
        // (С)      ->  S [С]
        // ctrl  C  ->  S [С]
        // ctrl (С) ->  Ц [C]
        result = is(code, rusLat, 
                'C', H_C,
                'с', H_S);
        if (result != null) return result;

        //  V       ->  V [Ж]
        // (М)      ->  M [М]
        // ctrl  V  ->  M [М]
        // ctrl (М) ->  Ж [V]
        result = is(code, rusLat, 
                'V', H_V,
                'м', H_M);
        if (result != null) return result;

        //  B       ->  B [Б]
        // (И)      ->  I [И]
        // ctrl  B  ->  I [И]
        // ctrl (И) ->  Б [B]
        result = is(code, rusLat, 
                'B', H_B,
                'и', H_I);
        if (result != null) return result;

        //  N       ->  N [Н]
        // (Т)      ->  T [Т]
        // ctrl  N  ->  T [Т]
        // ctrl (Т) ->  Н [N]
        result = is(code, rusLat, 
                'N', H_N,
                'т', H_T);
        if (result != null) return result;

        //  M       ->  M [М]
        // (Ь)      ->  X [Ь]
        // ctrl  M  ->  X [Ь]
        // ctrl (Ь) ->  М [M]
        result = is(code, rusLat, 
                'M', H_M,
                'ь', H_X);
        if (result != null) return result;

        //  ,       ->  ,
        // (Б)      ->  B [Б]
        // ctrl  ,  ->  B [Б]
        // ctrl (Б) ->  ,
        // shift ,  ->  <
        // ctrl  ,  ->  >
        result = is(code, rusLat, 
                ',', H_SYMB1,
                'б', H_B);
        if (result != null) return result;
        if (code == shf(',')) return shfH(H_SYMB1);
        if (code == ctr(',')) return nonH(H_SYMB7);

        //  .       ->  .
        // (Ю)      ->  @ [Ю]
        // ctrl  .  ->  @ [Ю]
        // ctrl (Ю) ->  .
        // shift .  ->  >
        // ctrl .   ->  <
        result = is(code, rusLat, 
                '.', H_SYMB7,
                'ю', H_SYMB8);
        if (result != null) return result;
        if (code == shf('.')) return shfH(H_SYMB7);
        if (code == ctr('.')) return nonH(H_SYMB1);

        // /        ->  /
        // ctrl  /  ->  \
        // shift /  ->  ?
        if (code == non('/')) return nonH(H_SYMB6);
        if (code == ctr('/')) return nonH(H_SYMB5);
        if (code == shf('/')) return shfH(H_SYMB6);

        // другие клавиши

        if (code == non(ENTER)) return       nonH(H_ВК);      // enter     -> ввод каретки
        if (code == alt(ENTER)) return       nonH(H_ПС); // alt enter -> перевод строки
        if (code == non(BACKSPACE)) return   nonH(H_ЗБ);      // backspace -> забой
        if (code == non(CAPS_LOCK)) return   nonH(H_РУС_ЛАТ); // caps_lock -> рус/лат

        if (code == non(ESC)) return     nonH(H_ESC);   // esc   ->  esc
        if (code == non(SPACE)) return   nonH(H_SPACE); // space ->  space

        if (code == non(INS)) return     nonH(H_ПВ);    // ins       ->  повтор
        if (code == non(HOME)) return    nonH(H_HOME);  // home      ->  home
        if (code == non(PG_UP)) return   nonH(H_TAB);   // page up   ->  tab
        if (code == non(DEL)) return     nonH(H_ЗБ);    // delete    ->  забой
        if (code == non(END)) return     nonH(H_F11);   // end       ->  F11
        if (code == non(PG_DOWN)) return nonH(H_F10);   // page down ->  F10

        if (code == non(UP)) return      nonH(H_UP);    // up    ->  вверх
        if (code == non(DOWN)) return    nonH(H_DOWN);  // down  ->  вних
        if (code == non(LEFT)) return    nonH(H_LEFT);  // left  ->  влево
        if (code == non(RIGHT)) return   nonH(H_RIGHT); // right ->  вправо

        if (code == non(F1)) return  nonH(H_F1);  // F1  ->  F1
        if (code == non(F2)) return  nonH(H_F2);  // F2  ->  F2
        if (code == non(F3)) return  nonH(H_F3);  // F3  ->  F3
        if (code == non(F4)) return  nonH(H_F4);  // F4  ->  F4
        if (code == non(F5)) return  nonH(H_F5);  // F5  ->  F5
        if (code == non(F6)) return  nonH(H_F6);  // F6  ->  F6
        if (code == non(F7)) return  nonH(H_F7);  // F7  ->  F7
        if (code == non(F8)) return  nonH(H_F8);  // F8  ->  F8
        if (code == non(F9)) return  nonH(H_F9);  // F9  ->  F9
        if (code == non(F10)) return nonH(H_F10); // F10 ->  F10
        if (code == non(F11)) return nonH(H_F11); // F11 ->  F11
        if (code == non(F12)) return nonH(H_F12); // F12 ->  F12

        return null;
    }
}