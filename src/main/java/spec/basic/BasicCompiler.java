package spec.basic;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import spec.Memory;
import spec.Range;
import spec.RomLoader;
import spec.math.Bites;
import spec.math.WordMath;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import static spec.KeyCode.*;
import static spec.KeyCode.OPEN_RECT_BRACKET;

// TODO #33 Сделать обратнуую декомпиляцию программы на бейсике в машинный код
public class BasicCompiler {

    public List<String> getSource(Bites memory, Range range) {
        Bites program = memory.array(range);
        Bites.BitesReader reader = program.reader();

        List<String> lines = new LinkedList<>();
        while (reader.hasNext()) {
            int startMarker = reader.read(1).get(0);            // 0x00
            int nextLine = WordMath.merge(reader.read(2), 0);   // start of next line record [lo] [hi] 0x69 0x1E -> 0x1E69
            if (nextLine == 0) break;
            nextLine = nextLine - range.begin() - 1;
            int lineNumber = WordMath.merge(reader.read(2), 0); // number of line            [lo] [hi] 0x0A 0x00 -> 0x000A
            Bites lineEncoded = reader.readTill(nextLine);      // program line with commands encoded
            String line = lineNumber + " " + decodeLine(lineEncoded);
            lines.add(line);
        }
        return lines;
    }

    // TODO #32 Добавить всех команд, сейчас тут не все присутствуют. Плюс проверить все 256 символов и посортировтаь
    private String decodeLine(Bites lineEncoded) {
        StringBuilder line = new StringBuilder();
        for (Integer bite : lineEncoded) {
            if (bite == SPACE) {
                line.append(" ");

            } else if (bite == Q) {
                line.append("Q");
            } else if (bite == W) {
                line.append("В");
            } else if (bite == E) {
                line.append("E");
            } else if (bite == R) {
                line.append("R");
            } else if (bite == T) {
                line.append("T");
            } else if (bite == Y) {
                line.append("Y");
            } else if (bite == U) {
                line.append("U");
            } else if (bite == I) {
                line.append("I");
            } else if (bite == O) {
                line.append("O");
            } else if (bite == P) {
                line.append("P");

            } else if (bite == A) {
                line.append("A");
            } else if (bite == S) {
                line.append("S");
            } else if (bite == D) {
                line.append("D");
            } else if (bite == F) {
                line.append("F");
            } else if (bite == G) {
                line.append("G");
            } else if (bite == H) {
                line.append("H");
            } else if (bite == J) {
                line.append("J");
            } else if (bite == K) {
                line.append("K");
            } else if (bite == L) {
                line.append("L");

            } else if (bite == Z) {
                line.append("Z");
            } else if (bite == X) {
                line.append("X");
            } else if (bite == C) {
                line.append("C");
            } else if (bite == V) {
                line.append("V");
            } else if (bite == B) {
                line.append("B");
            } else if (bite == N) {
                line.append("N");
            } else if (bite == M) {
                line.append("M");

            } else if (bite == 'q') {
                line.append("Я");
            } else if (bite == 'w') {
                line.append("B");
            } else if (bite == 'e') {
                line.append("Е");
            } else if (bite == 'r') {
                line.append("Р");
            } else if (bite == 't') {
                line.append("Т");
            } else if (bite == 'y') {
                line.append("Ы");
            } else if (bite == 'u') {
                line.append("У");
            } else if (bite == 'i') {
                line.append("И");
            } else if (bite == 'o') {
                line.append("О");
            } else if (bite == 'p') {
                line.append("П");
            } else if (bite == '{') {
                line.append("Ш");

            } else if (bite == 'a') {
                line.append("А");
            } else if (bite == 's') {
                line.append("С");
            } else if (bite == 'd') {
                line.append("Д");
            } else if (bite == 'f') {
                line.append("Ф");
            } else if (bite == 'g') {
                line.append("Г");
            } else if (bite == 'h') {
                line.append("Х");
            } else if (bite == 'j') {
                line.append("Й");
            } else if (bite == 'k') {
                line.append("К");
            } else if (bite == 'l') {
                line.append("Л");

            } else if (bite == 'z') {
                line.append("З");
            } else if (bite == 'x') {
                line.append("Ь");
            } else if (bite == 'c') {
                line.append("Ц");
            } else if (bite == 'v') {
                line.append("Ж");
            } else if (bite == 'b') {
                line.append("Б");
            } else if (bite == 'n') {
                line.append("Н");
            } else if (bite == 'm') {
                line.append("М");

            } else if (bite == '~') {
                line.append("Ч");
            } else if (bite == 0x7D) {
                line.append("Щ");
            } else if (bite == 0x7C) {
                line.append("Э");
            } else if (bite == 0x60) {
                line.append("Ю");

            } else if (bite == COMMA) {
                line.append(",");
            } else if (bite == DOT) {
                line.append(".");
            } else if (bite == ONE) {
                line.append("1");
            } else if (bite == TWO) {
                line.append("2");
            } else if (bite == THREE) {
                line.append("3");
            } else if (bite == FOUR) {
                line.append("4");
            } else if (bite == FIVE) {
                line.append("5");
            } else if (bite == SIX) {
                line.append("6");
            } else if (bite == SEVEN) {
                line.append("7");
            } else if (bite == EIGHT) {
                line.append("8");
            } else if (bite == NINE) {
                line.append("9");
            } else if (bite == ZERO) {
                line.append("0");
            } else if (bite == MINUS) {
                line.append("-");
            } else if (bite == EQUAL) {
                line.append("=");
            } else if (bite == SEMICOLON) {
                line.append(";");
            } else if (bite == 0x3F) {
                line.append("?");
            } else if (bite == 0x3A) {
                line.append(":");
            } else if (bite == 0x2A) {
                line.append("*");
            } else if (bite == 0x24) {
                line.append("$");
            } else if (bite == 0x25) {
                line.append("%");
            } else if (bite == 0x26) {
                line.append("&");
            } else if (bite == 0x27) {
                line.append("'");
            } else if (bite == 0xA4) {
                line.append("+");
            } else if (bite == 0xA5) {
                line.append("-");
            } else if (bite == 0xA6) {
                line.append("*");
            } else if (bite == 0xA7) {
                line.append("/");
            } else if (bite == 0xA8) {
                line.append("^");
            } else if (bite == 0xAB) {
                line.append(">");
            } else if (bite == 0xAD) {
                line.append("<");
            } else if (bite == '"') {
                line.append("\"");
            } else if (bite == 0xAC) {
                line.append("=");
            } else if (bite == '(') {
                line.append("(");
            } else if (bite == ')') {
                line.append(")");
            } else if (bite == '!') {
                line.append("!");
            } else if (bite == APOSTROPHE) {
                line.append("'");
            } else if (bite == SLASH) {
                line.append("/");
            } else if (bite == CLOSED_RECT_BRACKET) {
                line.append("]");
            } else if (bite == OPEN_RECT_BRACKET) {
                line.append("[");
            } else if (bite == 0x80) {
                line.append("CLS");
            } else if (bite == 0x81) {
                line.append("FOR");
            } else if (bite == 0x82) {
                line.append("NEXT");
            } else if (bite == 0x84) {
                line.append("INPUT");
            } else if (bite == 0x85) {
                line.append("DIM");
            } else if (bite == 0x87) {
                line.append("CUR");
            } else if (bite == 0x88) {
                line.append("GOTO");
            } else if (bite == 0x8A) {
                line.append("IF");
            } else if (bite == 0x8C) {
                line.append("GOSUB");
            } else if (bite == 0x8D) {
                line.append("RETURN");
            } else if (bite == 0x8E) {
                line.append("REM");
            } else if (bite == 0x8F) {
                line.append("STOP");
            } else if (bite == 0x9E) {
                line.append("TO");
            } else if (bite == 0x91) {
                line.append("ON");
            } else if (bite == 0x92) {
                line.append("PLOT");
            } else if (bite == 0x93) {
                line.append("LINE");
            } else if (bite == 0x94) {
                line.append("POKE");
            } else if (bite == 0x95) {
                line.append("PRINT");
            } else if (bite == 0xA1) {
                line.append("THEN");
            } else if (bite == 0xA3) {
                line.append("STEP");
            } else if (bite == 0xA9) {
                line.append("AND");
            } else if (bite == 0xAF) {
                line.append("INT");
            } else if (bite == 0xB1) {
                line.append("USR");
            } else if (bite == 0xB3) {
                line.append("INP");
            } else if (bite == 0xB6) {
                line.append("RND");
            } else if (bite == 0xBD) {
                line.append("PEEK");
            } else {
                line.append("{0x").append(WordMath.hex8(bite)).append("}");
            }
        }
        return line.toString();
    }

    public static void main(String[] args) throws IOException {
        BasicCompiler basic = new BasicCompiler();

        // загружаем программу в память
        Memory memory = new Memory(0x10000);
        RomLoader roms = new RomLoader(memory);
        URL base = new File(".").toURI().toURL();
        Range range = roms.loadBSS(base, "src/main/resources/lik/apps/basic/program/president/president.bss");

        // получаем исходники
        List<String> source = basic.getSource(memory.all(), range);

        // сохраняем в файл
        String program = StringUtils.join(source, "\n");
        FileUtils.writeByteArrayToFile(new File("target/program.bas"), program.getBytes());

        // выводим на экран
        System.out.println(program);

        // напечатает
        // 0 REM   ..........................
        // 1 REM   : ** NEВ   PRESIDENT **  :
        // 2 REM   :                        :
        // 3 REM   :      BЕРСИЯ - II.      :
        // 4 REM   : ИСПРАBЛЕННАЯ BЕРСИЯ-I. :
        // 5 REM   :  БОЛТАЕBСКИЙ АЛЕКСАНДР.:
        // 6 REM   : КРАСНОЯРСК-1992.  *BAI*:
        // 7 REM   :........................:
        // 8 GOSUB 7000
        // 10 CLS0:CUR40,120
        // 20 INPUT"BBЕДИТЕ BРЕМЯ ЧАС,МИН";X,Y:GOSUB9000
        // 30 FORI=0TOX+Y:Z=RND(1):NEXTI
        // ...
    }
}