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
//            System.out.println(line);
            lines.add(line);
        }
        return lines;
    }

    // TODO #32 Добавить всех команд, сейчас тут не все присутствуют. Плюс проверить все 256 символов и посортировтаь
    private String decodeLine(Bites lineEncoded) {
        StringBuilder line = new StringBuilder();
        for (Integer bite : lineEncoded) {
//            if (bite == 0x00) {
//                line.append(" ");
//            } else if (bite == 0x01) {
//                line.append(" ");
//            } else if (bite == 0x02) {
//                line.append(" ");
//            } else if (bite == 0x03) {
//                line.append(" ");
//            } else if (bite == 0x04) {
//                line.append(" ");
//            } else if (bite == 0x05) {
//                line.append(" ");
//            } else if (bite == 0x06) {
//                line.append(" ");
//            } else if (bite == 0x07) {
//                line.append(" ");
//            } else if (bite == 0x08) {
//                line.append(" ");
//            } else if (bite == 0x09) {
//                line.append(" ");
//            } else if (bite == 0x0A) {
//                line.append(" ");
//            } else if (bite == 0x0B) {
//                line.append(" ");
//            } else if (bite == 0x0C) {
//                line.append(" ");
//            } else if (bite == 0x0D) {
//                line.append(" ");
//            } else if (bite == 0x0E) {
//                line.append(" ");
//            } else if (bite == 0x0F) {
//                line.append(" ");
//
//            } else if (bite == 0x10) {
//                line.append(" ");
//            } else if (bite == 0x11) {
//                line.append(" ");
//            } else if (bite == 0x12) {
//                line.append(" ");
//            } else if (bite == 0x13) {
//                line.append(" ");
//            } else if (bite == 0x14) {
//                line.append(" ");
//            } else if (bite == 0x15) {
//                line.append(" ");
//            } else if (bite == 0x16) {
//                line.append(" ");
//            } else if (bite == 0x17) {
//                line.append(" ");
//            } else if (bite == 0x18) {
//                line.append(" ");
//            } else if (bite == 0x19) {
//                line.append(" ");
//            } else if (bite == 0x1A) {
//                line.append(" ");
//            } else if (bite == 0x1B) {
//                line.append(" ");
//            } else if (bite == 0x1C) {
//                line.append(" ");
//            } else if (bite == 0x1D) {
//                line.append(" ");
//            } else if (bite == 0x1E) {
//                line.append(" ");
//            } else if (bite == 0x1F) {
//                line.append(" ");

            if (bite == 0x20) {
                line.append(" ");
            } else if (bite == 0x21) {
                line.append("!");
            } else if (bite == 0x22) {
                line.append("\"");
            } else if (bite == 0x23) {
                line.append("#");
            } else if (bite == 0x24) {
                line.append("$");
            } else if (bite == 0x25) {
                line.append("%");
            } else if (bite == 0x26) {
                line.append("&");
            } else if (bite == 0x27) {
                line.append("'");
            } else if (bite == 0x28) {
                line.append("(");
            } else if (bite == 0x29) {
                line.append(")");
            } else if (bite == 0x2A) {
                line.append("*");
            } else if (bite == 0x2B) {
                line.append("+");
            } else if (bite == 0x2C) {
                line.append(",");
            } else if (bite == 0x2D) {
                line.append("-");
            } else if (bite == 0x2E) {
                line.append(".");
            } else if (bite == 0x2F) {
                line.append("/");

            } else if (bite == 0x30) {
                line.append("0");
            } else if (bite == 0x31) {
                line.append("1");
            } else if (bite == 0x32) {
                line.append("2");
            } else if (bite == 0x33) {
                line.append("3");
            } else if (bite == 0x34) {
                line.append("4");
            } else if (bite == 0x35) {
                line.append("5");
            } else if (bite == 0x36) {
                line.append("6");
            } else if (bite == 0x37) {
                line.append("7");
            } else if (bite == 0x38) {
                line.append("8");
            } else if (bite == 0x39) {
                line.append("9");
            } else if (bite == 0x3A) {
                line.append(":");
            } else if (bite == 0x3B) {
                line.append(";");
            } else if (bite == 0x3C) {
                line.append("<");
            } else if (bite == 0x3D) {
                line.append("=");
            } else if (bite == 0x3E) {
                line.append(">");
            } else if (bite == 0x3F) {
                line.append("?");

            } else if (bite == 0x40) {
                line.append("@");
            } else if (bite == 0x41) {
                line.append("A");
            } else if (bite == 0x42) {
                line.append("B");
            } else if (bite == 0x43) {
                line.append("C");
            } else if (bite == 0x44) {
                line.append("D");
            } else if (bite == 0x45) {
                line.append("E");
            } else if (bite == 0x46) {
                line.append("F");
            } else if (bite == 0x47) {
                line.append("G");
            } else if (bite == 0x48) {
                line.append("H");
            } else if (bite == 0x49) {
                line.append("I");
            } else if (bite == 0x4A) {
                line.append("J");
            } else if (bite == 0x4B) {
                line.append("K");
            } else if (bite == 0x4C) {
                line.append("L");
            } else if (bite == 0x4D) {
                line.append("M");
            } else if (bite == 0x4E) {
                line.append("N");
            } else if (bite == 0x4F) {
                line.append("O");

            } else if (bite == 0x50) {
                line.append("P");
            } else if (bite == 0x51) {
                line.append("Q");
            } else if (bite == 0x52) {
                line.append("R");
            } else if (bite == 0x53) {
                line.append("S");
            } else if (bite == 0x54) {
                line.append("T");
            } else if (bite == 0x55) {
                line.append("U");
            } else if (bite == 0x56) {
                line.append("V");
            } else if (bite == 0x57) {
                line.append("W");
            } else if (bite == 0x58) {
                line.append("X");
            } else if (bite == 0x59) {
                line.append("Y");
            } else if (bite == 0x5A) {
                line.append("Z");
            } else if (bite == 0x5B) {
                line.append("[");
            } else if (bite == 0x5C) {
                line.append("\\");
            } else if (bite == 0x5D) {
                line.append("]");
            } else if (bite == 0x5E) {
                line.append("^");
            } else if (bite == 0x5F) {
                line.append("_");

            } else if (bite == 0x60) {
                line.append("Ю");
            } else if (bite == 0x61) {
                line.append("А");
            } else if (bite == 0x62) {
                line.append("Б");
            } else if (bite == 0x63) {
                line.append("Ц");
            } else if (bite == 0x64) {
                line.append("Д");
            } else if (bite == 0x65) {
                line.append("Е");
            } else if (bite == 0x66) {
                line.append("Ф");
            } else if (bite == 0x67) {
                line.append("Г");
            } else if (bite == 0x68) {
                line.append("Х");
            } else if (bite == 0x69) {
                line.append("И");
            } else if (bite == 0x6A) {
                line.append("Й");
            } else if (bite == 0x6B) {
                line.append("К");
            } else if (bite == 0x6C) {
                line.append("Л");
            } else if (bite == 0x6D) {
                line.append("М");
            } else if (bite == 0x6E) {
                line.append("Н");
            } else if (bite == 0x6F) {
                line.append("О");

            } else if (bite == 0x70) {
                line.append("П");
            } else if (bite == 0x71) {
                line.append("Я");
            } else if (bite == 0x72) {
                line.append("Р");
            } else if (bite == 0x73) {
                line.append("С");
            } else if (bite == 0x74) {
                line.append("Т");
            } else if (bite == 0x75) {
                line.append("У");
            } else if (bite == 0x76) {
                line.append("Ж");
            } else if (bite == 0x77) {
                line.append("В");
            } else if (bite == 0x78) {
                line.append("Ь");
            } else if (bite == 0x79) {
                line.append("Ы");
            } else if (bite == 0x7A) {
                line.append("З");
            } else if (bite == 0x7B) {
                line.append("Ш");
            } else if (bite == 0x7C) {
                line.append("Э");
            } else if (bite == 0x7D) {
                line.append("Щ");
            } else if (bite == 0x7E) {
                line.append("Ч");
            } else if (bite == 0x7F) {
                line.append(" ");

            } else if (bite == 0x80) {
                line.append("CLS");
            } else if (bite == 0x81) {
                line.append("FOR");
            } else if (bite == 0x82) {
                line.append("NEXT");
            } else if (bite == 0x83) {
                line.append("DATA");
            } else if (bite == 0x84) {
                line.append("INPUT");
            } else if (bite == 0x85) {
                line.append("DIM");
            } else if (bite == 0x86) {
                line.append("READ");
            } else if (bite == 0x87) {
                line.append("CUR");
            } else if (bite == 0x88) {
                line.append("GOTO");
            } else if (bite == 0x89) {
                line.append("RUN");
            } else if (bite == 0x8A) {
                line.append("IF");
            } else if (bite == 0x8B) {
                line.append("RESTORE");
            } else if (bite == 0x8C) {
                line.append("GOSUB");
            } else if (bite == 0x8D) {
                line.append("RETURN");
            } else if (bite == 0x8E) {
                line.append("REM");
            } else if (bite == 0x8F) {
                line.append("STOP");

            } else if (bite == 0x90) {
                line.append("DPL");
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
            } else if (bite == 0x96) {
                line.append("DEF");
            } else if (bite == 0x97) {
                line.append("CONT");
            } else if (bite == 0x98) {
                line.append("LIST");
            } else if (bite == 0x99) {
                line.append("CLEAR");
            } else if (bite == 0x9A) {
                line.append("MLOAD");
            } else if (bite == 0x9B) {
                line.append("MSAVE");
            } else if (bite == 0x9C) {
                line.append("NEW");
            } else if (bite == 0x9D) {
                line.append("TAB(");
            } else if (bite == 0x9E) {
                line.append("TO");
            } else if (bite == 0x9F) {
                line.append("SPC(");

            } else if (bite == 0xA0) {
                line.append("FN");
            } else if (bite == 0xA1) {
                line.append("THEN");
            } else if (bite == 0xA2) {
                line.append("NOT");
            } else if (bite == 0xA3) {
                line.append("STEP");
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
            } else if (bite == 0xA9) {
                line.append("AND");
            } else if (bite == 0xAA) {
                line.append("OR");
            } else if (bite == 0xAB) {
                line.append(">");
            } else if (bite == 0xAC) {
                line.append("=");
            } else if (bite == 0xAD) {
                line.append("<");
            } else if (bite == 0xAE) {
                line.append("SGN");
            } else if (bite == 0xAF) {
                line.append("INT");

            } else if (bite == 0xB0) {
                line.append("ABS");
            } else if (bite == 0xB1) {
                line.append("USR");
            } else if (bite == 0xB2) {
                line.append("FRE");
            } else if (bite == 0xB3) {
                line.append("INP");
            } else if (bite == 0xB4) {
                line.append("POS");
            } else if (bite == 0xB5) {
                line.append("SQR");
            } else if (bite == 0xB6) {
                line.append("RND");
            } else if (bite == 0xB7) {
                line.append("LOG");
            } else if (bite == 0xB8) {
                line.append("EXP");
            } else if (bite == 0xB9) {
                line.append("COS");
            } else if (bite == 0xBA) {
                line.append("SIN");
            } else if (bite == 0xBB) {
                line.append("TAN");
            } else if (bite == 0xBC) {
                line.append("ATN");
            } else if (bite == 0xBD) {
                line.append("PEEK");
            } else if (bite == 0xBE) {
                line.append("LEN");
            } else if (bite == 0xBF) {
                line.append("STR$");

            } else if (bite == 0xC0) {
                line.append("VAL");
            } else if (bite == 0xC1) {
                line.append("ASC");
            } else if (bite == 0xC2) {
                line.append("CHR$");
            } else if (bite == 0xC3) {
                line.append("LEFT$");
            } else if (bite == 0xC4) {
                line.append("RIGHT$");
            } else if (bite == 0xC5) {
                line.append("MID$");
//            } else if (bite == 0xC6) {
//                line.append(" ");
//            } else if (bite == 0xC7) {
//                line.append(" ");
//            } else if (bite == 0xC8) {
//                line.append(" ");
//            } else if (bite == 0xC9) {
//                line.append(" ");
//            } else if (bite == 0xCA) {
//                line.append(" ");
//            } else if (bite == 0xCB) {
//                line.append(" ");
//            } else if (bite == 0xCC) {
//                line.append(" ");
//            } else if (bite == 0xCD) {
//                line.append(" ");
//            } else if (bite == 0xCE) {
//                line.append(" ");
//            } else if (bite == 0xCF) {
//                line.append(" ");
//
//            } else if (bite == 0xD0) {
//                line.append(" ");
//            } else if (bite == 0xD1) {
//                line.append(" ");
//            } else if (bite == 0xD2) {
//                line.append(" ");
//            } else if (bite == 0xD3) {
//                line.append(" ");
//            } else if (bite == 0xD4) {
//                line.append(" ");
//            } else if (bite == 0xD5) {
//                line.append(" ");
//            } else if (bite == 0xD6) {
//                line.append(" ");
//            } else if (bite == 0xD7) {
//                line.append(" ");
//            } else if (bite == 0xD8) {
//                line.append(" ");
//            } else if (bite == 0xD9) {
//                line.append(" ");
//            } else if (bite == 0xDA) {
//                line.append(" ");
//            } else if (bite == 0xDB) {
//                line.append(" ");
//            } else if (bite == 0xDC) {
//                line.append(" ");
//            } else if (bite == 0xDD) {
//                line.append(" ");
//            } else if (bite == 0xDE) {
//                line.append(" ");
//            } else if (bite == 0xDF) {
//                line.append(" ");
//
//            } else if (bite == 0xE0) {
//                line.append(" ");
//            } else if (bite == 0xE1) {
//                line.append(" ");
//            } else if (bite == 0xE2) {
//                line.append(" ");
//            } else if (bite == 0xE3) {
//                line.append(" ");
//            } else if (bite == 0xE4) {
//                line.append(" ");
//            } else if (bite == 0xE5) {
//                line.append(" ");
//            } else if (bite == 0xE6) {
//                line.append(" ");
//            } else if (bite == 0xE7) {
//                line.append(" ");
//            } else if (bite == 0xE8) {
//                line.append(" ");
//            } else if (bite == 0xE9) {
//                line.append(" ");
//            } else if (bite == 0xEA) {
//                line.append(" ");
//            } else if (bite == 0xEB) {
//                line.append(" ");
//            } else if (bite == 0xEC) {
//                line.append(" ");
//            } else if (bite == 0xED) {
//                line.append(" ");
//            } else if (bite == 0xEE) {
//                line.append(" ");
//            } else if (bite == 0xEF) {
//                line.append(" ");
//
//            } else if (bite == 0xF0) {
//                line.append(" ");
//            } else if (bite == 0xF1) {
//                line.append(" ");
//            } else if (bite == 0xF2) {
//                line.append(" ");
//            } else if (bite == 0xF3) {
//                line.append(" ");
//            } else if (bite == 0xF4) {
//                line.append(" ");
//            } else if (bite == 0xF5) {
//                line.append(" ");
//            } else if (bite == 0xF6) {
//                line.append(" ");
//            } else if (bite == 0xF7) {
//                line.append(" ");
//            } else if (bite == 0xF8) {
//                line.append(" ");
//            } else if (bite == 0xF9) {
//                line.append(" ");
//            } else if (bite == 0xFA) {
//                line.append(" ");
//            } else if (bite == 0xFB) {
//                line.append(" ");
//            } else if (bite == 0xFC) {
//                line.append(" ");
//            } else if (bite == 0xFD) {
//                line.append(" ");
//            } else if (bite == 0xFE) {
//                line.append(" ");
//            } else if (bite == 0xFF) {
//                line.append(" ");
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
        Range range = roms.loadBSS(".", "src/main/resources/lik/apps/basic/program/president/president.bss");

        // получаем исходники
        List<String> source = basic.getSource(memory.all(), range);

        // сохраняем в файл
        String program = StringUtils.join(source, "\n");
        FileUtils.writeByteArrayToFile(new File("target/program.bas"), program.getBytes());

        // выводим на экран
        System.out.println(program);

        // напечатает
        // 0 REM   ..........................
        // 1 REM   : ** NEW   PRESIDENT **  :
        // 2 REM   :                        :
        // 3 REM   :      ВЕРСИЯ - II.      :
        // 4 REM   : ИСПРАВЛЕННАЯ ВЕРСИЯ-I. :
        // 5 REM   :  БОЛТАЕВСКИЙ АЛЕКСАНДР.:
        // 6 REM   : КРАСНОЯРСК-1992.  *BAI*:
        // 7 REM   :........................:
        // 8 GOSUB 7000
        // 10 CLS0:CUR40,120
        // 20 INPUT"ВВЕДИТЕ ВРЕМЯ ЧАС,МИН";X,Y:GOSUB9000
        // 30 FORI=0TOX+Y:Z=RND(1):NEXTI
        // ...
    }
}