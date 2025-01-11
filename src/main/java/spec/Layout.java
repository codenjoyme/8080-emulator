package spec;

import org.apache.commons.lang3.tuple.Pair;

import static spec.KeyCode.*;
import static spec.Keyboard.*;

public class Layout extends AbstractLayout {

    public Pair<Integer, Boolean> get(int code, boolean cyrLat) {
        Pair<Integer, Boolean> result = null;

        // первая линия стандартной клавиатуры

        //keyboard.putNorm('À', 0x00); // первая клавиша ~/[Ё]
        //keyboard.putShft('À', 0x00);
        //keyboard.putCyrl('ё', 0x00);
        //keyboard.putCrSh('ё', 0x00);

        //              cyr | lat
        // 1        ->   1     1
        // shift 1  ->   !     !
        // ctrl  1  ->
        // alt   1  ->
        if (code == non('1')) return HS_1;
        if (code == shf('1')) return HS_EXCLAMATION_MARK;
        if (code == ctr('1')) ;
        if (code == alt('1')) ;

        //              cyr | lat
        // 2        ->   2     2
        // shift 2  ->   "     @
        // ctrl  2  ->         "
        // alt   2  ->
        if (code == non('2')) return HS_2;
        if (cyrLat) {
            if (code == shf('2')) return HS_QUOTE;
            if (code == ctr('2')) ; // TODO #14 в кириллице нет символа @, то вместо него возвращается 'Ю'
            if (code == alt('2')) ;
        } else {
            if (code == shf('2')) return HS_LAT_AT;
            if (code == ctr('2')) return HS_QUOTE;
            if (code == alt('2')) ;
        }

        //              cyr | lat
        // 3        ->   3     3
        // shift 3  ->   #     #
        // ctrl  3  ->
        // alt   3  ->
        if (code == non('3')) return HS_3;
        if (code == shf('3')) return HS_NUMBER_SIGN; // TODO #15 в реальной клавиатуре в cyr должен быть '№' но в ЛИКе нет такого символа
        if (code == ctr('3')) ;
        if (code == alt('3')) ;

        //              cyr | lat
        // 4        ->   4     4
        // shift 4  ->   ;     $
        // ctrl  4  ->   :     ;
        // alt   4  ->   $     :
        if (code == non('4')) return HS_4;
        if (cyrLat) {
            if (code == shf('4')) return HS_SEMICOLON;
            if (code == ctr('4')) return HS_COLON;
            if (code == alt('4')) return HS_DOLLAR;
        } else {
            if (code == shf('4')) return HS_DOLLAR;
            if (code == ctr('4')) return HS_SEMICOLON;
            if (code == alt('4')) return HS_COLON;
        }

        //              cyr | lat
        // 5        ->   5     5
        // shift 5  ->   %     %
        // ctrl  5  ->
        // alt   5  ->
        if (code == non('5')) return HS_5;
        if (code == shf('5')) return HS_PERCENT;
        if (code == ctr('5')) ;
        if (code == alt('5')) ;

        //              cyr | lat
        // 6        ->   6     6
        // shift 6  ->   :     ^
        // ctrl  6  ->   ;     :
        // alt   6  ->         ;
        if (code == non('6')) return HS_6;
        if (cyrLat) {
            if (code == shf('6')) return HS_COLON;
            if (code == ctr('6')) return HS_SEMICOLON;
            if (code == alt('6')) ; // TODO #14 в кириллице нет символа '^', то вместо него возвращается 'Ч'
        } else {
            if (code == shf('6')) return HS_LAT_CIRCUMFLEX;
            if (code == ctr('6')) return HS_COLON;
            if (code == alt('6')) return HS_SEMICOLON;
        }

        //              cyr | lat
        // 7        ->   7     7
        // shift 7  ->   ?     &
        // ctrl  7  ->   &     ?
        // alt   7  ->   '     '
        if (code == non('7')) return HS_7;
        if (cyrLat) {
            if (code == shf('7')) return HS_QUESTION_MARK;
            if (code == ctr('7')) return HS_AMPERSAND;
            if (code == alt('7')) return HS_APOSTROPHE;
        } else {
            if (code == shf('7')) return HS_AMPERSAND;
            if (code == ctr('7')) return HS_QUESTION_MARK;
            if (code == alt('7')) return HS_APOSTROPHE;
        }

        //              cyr | lat
        // 8        ->   8     8
        // shift 8  ->   *     *
        // ctrl  8  ->
        // alt   8  ->
        if (code == non('8')) return HS_8;
        if (code == shf('8')) return HS_ASTERISK;
        if (code == ctr('8')) ;
        if (code == alt('8')) ;

        //              cyr | lat
        // 9        ->   9     9
        // shift 9  ->   (     (
        // ctrl  9  ->
        // alt   9  ->
        if (code == non('9')) return HS_9;
        if (code == shf('9')) return HS_LEFT_PARENTHESIS;
        if (code == ctr('9')) ;
        if (code == alt('9')) ;

        //              cyr | lat
        // 0        ->   0     0
        // shift 0  ->   )     )
        // ctrl  0  ->
        // alt 0    ->  ' '   ' '
        if (code == non('0')) return HS_ZERO;
        if (code == shf('0')) return HS_RIGHT_PARENTHESIS;
        if (code == ctr('0')) ;
        if (code == alt('0')) return HS_SPACE;  // TODO #16 так у самого ЛИКа c shift 0

        //              cyr | lat
        // -        ->   -
        // shift -  ->
        // ctrl  -  ->
        // alt   -  ->
        if (code == non('-')) return HS_MINUS;
        if (code == shf('-')) ; // TODO #17 должен быть прочерк, но его кода не нашел
        if (code == ctr('-')) ;
        if (code == alt('-')) ;

        //              cyr | lat
        // =        ->   =     =
        // shift =  ->   +     +
        // ctrl  =  ->
        // alt   =  ->
        if (code == non('=')) return HS_EQUALS;
        if (code == shf('=')) return HS_PLUS;
        if (code == ctr('=')) ;
        if (code == alt('=')) ;

        // вторая линия стандартной клавиатуры

        // Tab не определяется в Swing

        //              cyr | lat
        //  Q       ->   Й     Q
        // ctrl  Q  ->   Я     J
        // (Й)      ->   Я     J
        // ctrl (Й) ->   Й     Q
        result = is(code, cyrLat,
                'Q', H_cyr_Я_OR_lat_Q,
                'й', H_cyr_Й_OR_lat_J);
        if (result != null) return result;

        //              cyr | lat
        //  W       ->   Ц     W
        // ctrl  W  ->   В     C
        // (Ц)      ->   В     C
        // ctrl (Ц) ->   Ц     W
        result = is(code, cyrLat,
                'W', H_cyr_В_OR_lat_W,
                'ц', H_cyr_Ц_OR_lat_C);
        if (result != null) return result;

        //              cyr | lat
        //  E       ->   У     E
        // ctrl  E  ->   Е     U
        // (У)      ->   Е     U
        // ctrl (У) ->   У     E
        result = is(code, cyrLat,
                'E', H_cyr_Е_OR_lat_E,
                'у', H_cyr_У_OR_lat_U);
        if (result != null) return result;

        //              cyr | lat
        //  R       ->   К     R
        // ctrl  R  ->   Р     K    
        // (К)      ->   Р     K
        // ctrl (К) ->   К     R
        result = is(code, cyrLat,
                'R', H_cyr_Р_OR_lat_R,
                'к', H_cyr_К_OR_lat_K);
        if (result != null) return result;

        //              cyr | lat
        //  T       ->   Е     T
        // ctrl  T  ->   Т     E
        // (Е)      ->   Т     E
        // ctrl (Е) ->   Е     T
        result = is(code, cyrLat,
                'T', H_cyr_Т_OR_lat_T,
                'е', H_cyr_Е_OR_lat_E);
        if (result != null) return result;


        //              cyr | lat
        //  Y       ->   Н     Y
        // ctrl  Y  ->   Ы     N
        // (Н)      ->   Ы     N
        // ctrl (Н) ->   Н     Y
        result = is(code, cyrLat,
                'Y', H_cyr_Ы_OR_lat_Y,
                'н', H_cyr_Н_OR_lat_N);
        if (result != null) return result;

        //              cyr | lat
        //  U       ->   Г     U
        // ctrl  U  ->   У     G
        // (Г)      ->   У     G
        // ctrl (Г) ->   Г     U
        result = is(code, cyrLat,
                'U', H_cyr_У_OR_lat_U,
                'г', H_cyr_Г_OR_lat_G);
        if (result != null) return result;

        //              cyr | lat
        //  I       ->   Ш     I
        // ctrl  I  ->   И     [
        // (Ш)      ->   И     [
        // ctrl (Ш) ->   Ш     I
        result = is(code, cyrLat,
                'I', H_cyr_И_OR_lat_I,
                'ш', H_cyr_Ш_OR_lat_LeftSquareBracket);
        if (result != null) return result;

        //              cyr | lat
        //  O       ->   Щ     O
        // ctrl  O  ->   О     ]
        // (Щ)      ->   О     ]
        // ctrl (Щ) ->   Щ     O
        result = is(code, cyrLat,
                'O', H_cyr_О_OR_lat_O,
                'щ', H_cyr_Щ_OR_lat_RightSquareBracket);
        if (result != null) return result;

        //              cyr | lat
        //  P       ->   З     P
        // ctrl  P  ->   П     Z
        // (З)      ->   П     Z
        // ctrl (З) ->   З     P
        result = is(code, cyrLat,
                'P', H_cyr_П_OR_lat_P,
                'з', H_cyr_З_OR_lat_Z);
        if (result != null) return result;

        //              cyr | lat
        // [        ->   Ь     [
        // ctrl  ]  ->   Щ     X
        // (Ь)      ->   Щ     X
        // ctrl (Ь) ->   Ь     [
        result = is(code, cyrLat,
                '[', H_cyr_Ш_OR_lat_LeftSquareBracket,
                'х', H_cyr_Ь_OR_lat_X);
        if (result != null) return result;

        //              cyr | lat
        // ]        ->         ]
        if (cyrLat) {
            if (code == cyr('ъ')) ; // TODO #18 нет такого `ъ` знака в ЛИКе
        } else {
            if (code == non(']')) return HS_LAT_RIGHT_SQUARE_BRACKET;
        }

        // третья линия стандартной клавиатуры

        //              cyr | lat
        //  A       ->   Ф     A
        // ctrl  A  ->   А     F
        // (Ф)      ->   А     F
        // ctrl (Ф) ->   Ф     A
        result = is(code, cyrLat,
                'A', H_cyr_А_OR_lat_A,
                'ф', H_cyr_Ф_OR_lat_F);
        if (result != null) return result;

        //              cyr | lat
        //  S       ->   Ы     S
        // ctrl  S  ->   С     Y
        // (Ы)      ->   С     Y
        // ctrl (Ы) ->   Ы     S
        result = is(code, cyrLat,
                'S', H_cyr_С_OR_lat_S,
                'ы', H_cyr_Ы_OR_lat_Y);
        if (result != null) return result;

        //              cyr | lat
        //  D       ->   В     D
        // ctrl  D  ->   Д     W
        // (В)      ->   Д     W
        // ctrl (В) ->   В     D
        result = is(code, cyrLat,
                'D', H_cyr_Д_OR_lat_D,
                'в', H_cyr_В_OR_lat_W);
        if (result != null) return result;

        //              cyr | lat
        //  F       ->   А     F
        // ctrl  F  ->   Ф     A
        // (А)      ->   Ф     A
        // ctrl (А) ->   А     F
        result = is(code, cyrLat,
                'F', H_cyr_Ф_OR_lat_F,
                'а', H_cyr_А_OR_lat_A);
        if (result != null) return result;

        //              cyr | lat
        //  G       ->   П     G
        // ctrl  G  ->   Г     P
        // (П)      ->   Г     P
        // ctrl (П) ->   П     G
        result = is(code, cyrLat,
                'G', H_cyr_Г_OR_lat_G,
                'п', H_cyr_П_OR_lat_P);
        if (result != null) return result;

        //              cyr | lat
        //  H       ->   Р     H
        // ctrl  H  ->   Х     R
        // (Р)      ->   Х     R
        // ctrl (Р) ->   Р     H
        result = is(code, cyrLat,
                'H', H_cyr_Х_OR_lat_H,
                'р', H_cyr_Р_OR_lat_R);
        if (result != null) return result;

        //              cyr | lat
        //  J       ->   О     J
        // ctrl  J  ->   Й     O
        // (О)      ->   Й     O
        // ctrl (О) ->   О     J
        result = is(code, cyrLat,
                'J', H_cyr_Й_OR_lat_J,
                'о', H_cyr_О_OR_lat_O);
        if (result != null) return result;

        //              cyr | lat
        //  K       ->   Л     K
        // ctrl  K  ->   К     L
        // (Л)      ->   К     L
        // ctrl (Л) ->   Л     K
        result = is(code, cyrLat,
                'K', H_cyr_К_OR_lat_K,
                'л', H_cyr_Л_OR_lat_L);
        if (result != null) return result;

        //              cyr | lat
        //  L       ->   Д     L
        // ctrl  L  ->   Л     D
        // (Д)      ->   Л     D
        // ctrl (Д) ->   Д     L
        result = is(code, cyrLat,
                'L', H_cyr_Л_OR_lat_L,
                'д', H_cyr_Д_OR_lat_D);
        if (result != null) return result;

        //              cyr | lat
        // shift ;  ->   :     :
        // ;        ->   Ж     ;
        // ctrl  ;  ->   ;     V
        // (Ж)      ->   ;     V
        // ctrl (Ж) ->   Ж     ;
        result = is(code, cyrLat,
                ';', H_none_Semicolon_OR_shift_Plus,   // TODO #19 вот не совсем понятно надо ли тут так сложно
                'ж', H_cyr_Ж_OR_lat_V);
        if (result != null) return result;
        if (code == shf(';')) return HS_COLON;

        //              cyr | lat
        // '        ->   Э     '
        // shift '  ->         "
        // ctrl  '  ->
        // alt   '  ->
        if (cyrLat) {
            if (code == non('Þ')) return HS_CYR_Э;
            if (code == shf('Þ')) ;
            if (code == ctr('Þ')) ;
            if (code == alt('Þ')) ;
        } else {
            if (code == non('Þ')) return HS_APOSTROPHE;
            if (code == shf('Þ')) return HS_QUOTE;
            if (code == ctr('Þ')) ;
            if (code == alt('Þ')) ;
        }

        //              cyr | lat
        // \        ->   /     \
        // shift \  ->   /
        // ctrl  \  ->         /
        // alt   \  ->
        if (cyrLat) {
            if (code == non('\\')) return HS_SLASH; // TODO #20 тут должно быть '\' но в кириллице там вылетает 'Э' вместо '\'
            if (code == shf('\\')) return HS_SLASH;
            if (code == ctr('\\')) ;
            if (code == alt('\\')) ;
        } else {
            if (code == non('\\')) return HS_LAT_BACKSLASH;
            if (code == shf('\\')) ; // #21 TODO тут должно быть '|' но такого символа нет в ЛИКе
            if (code == ctr('\\')) return HS_SLASH;
            if (code == alt('\\')) ;
        }

        // четвертая линия стандартной клавиатуры

        //              cyr | lat
        //  H       ->   Р     H
        // ctrl  H  ->   Х     R
        // (Р)      ->   Х     R
        // ctrl (Р) ->   Р     H
        result = is(code, cyrLat,
                'H', H_cyr_Х_OR_lat_H,
                'р', H_cyr_Р_OR_lat_R);
        if (result != null) return result;

        //              cyr | lat
        //  Z       ->   Я     Z
        // ctrl  Z  ->   З     Q
        // (Я)      ->   З     Q
        // ctrl (Я) ->   Я     Z
        result = is(code, cyrLat,
                'Z', H_cyr_З_OR_lat_Z,
                'я', H_cyr_Я_OR_lat_Q);
        if (result != null) return result;

        //              cyr | lat
        //  X       ->   Ч     X
        // ctrl  X  ->   Ь     ^
        // (Ч)      ->   Ь     ^
        // ctrl (Ч) ->   Ч     X
        result = is(code, cyrLat,
                'X', H_cyr_Ь_OR_lat_X,
                'ч', H_cyr_Ч_OR_lat_Circumflex);
        if (result != null) return result;

        //              cyr | lat
        //  C       ->   С     C
        // ctrl  C  ->   Ц     S
        // (С)      ->   Ц     S
        // ctrl (С) ->   С     C
        result = is(code, cyrLat,
                'C', H_cyr_Ц_OR_lat_C,
                'с', H_cyr_С_OR_lat_S);
        if (result != null) return result;

        //              cyr | lat
        //  V       ->   М     V
        // ctrl  V  ->   Ж     M
        // (М)      ->   Ж     M
        // ctrl (М) ->   М     V
        result = is(code, cyrLat,
                'V', H_cyr_Ж_OR_lat_V,
                'м', H_cyr_М_OR_lat_M);
        if (result != null) return result;

        //              cyr | lat
        //  B       ->   И     B
        // ctrl  B  ->   Б     I
        // (И)      ->   Б     I
        // ctrl (И) ->   И     B
        result = is(code, cyrLat,
                'B', H_cyr_Б_OR_lat_B,
                'и', H_cyr_И_OR_lat_I);
        if (result != null) return result;

        //              cyr | lat
        //  N       ->   Т     N
        // ctrl  N  ->   Н     T
        // (Т)      ->   Н     T
        // ctrl (Т) ->   Т     N
        result = is(code, cyrLat,
                'N', H_cyr_Н_OR_lat_N,
                'т', H_cyr_Т_OR_lat_T);
        if (result != null) return result;

        //              cyr | lat
        //  M       ->   Ь     M
        // ctrl  M  ->   М     X
        // (Ь)      ->   М     X
        // ctrl (Ь) ->   Ь     M
        result = is(code, cyrLat,
                'M', H_cyr_М_OR_lat_M,
                'ь', H_cyr_Ь_OR_lat_X);
        if (result != null) return result;

        //              cyr | lat
        // ,        ->   Б     <
        // ctrl ,   ->   ,     B
        // (Б)      ->   ,     B
        // ctrl (Б) ->   Б     <
        // shift ,  ->   <     <
        // alt   ,  ->   .     .
        result = is(code, cyrLat,
                ',', H_none_Comma_OR_shift_LeftAngleBracket, // TODO #19 вот не совсем понятно надо ли тут так сложно
                'б', H_cyr_Б_OR_lat_B);
        if (result != null) return result;
        if (code == shf(',')) return HS_LEFT_ANGLE_BRACKET;
        if (code == alt(',')) return HS_DOT;

        //              cyr | lat
        // .        ->   Ю     >
        // ctrl .   ->   .     N
        // (Ю)      ->   .     N
        // ctrl (Ю) ->   Ю     >
        // shift .  ->   >     >
        // alt   .  ->   ,     ,
        result = is(code, cyrLat,
                '.', H_none_Dot_OR_shift_RightAngleBracket, // TODO #19 вот не совсем понятно надо ли тут так сложно
                'ю', H_cyr_Ю_OR_lat_At);
        if (result != null) return result;
        if (code == shf('.')) return HS_RIGHT_ANGLE_BRACKET;
        if (code == alt('.')) return HS_COMMA;

        //              cyr | lat
        // /        ->   .     /
        // shift /  ->   ,     ?
        // ctrl  /  ->         \
        // alt   /  ->
        if (cyrLat) {
            if (code == non('/')) return HS_DOT;
            if (code == shf('/')) return HS_COMMA;
            if (code == ctr('/')) ;
            if (code == alt('/')) ;
        } else {
            if (code == non('/')) return HS_SLASH;
            if (code == shf('/')) return HS_QUESTION_MARK;
            if (code == ctr('/')) return HS_LAT_BACKSLASH;
            if (code == alt('/')) ;
        }

        // другие клавиши

        if (code == non(ENTER)) return       nonH(H_ВК);      // enter     -> ввод каретки
        if (code == shf(ENTER)) return       nonH(H_ПС); // alt enter -> перевод строки
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