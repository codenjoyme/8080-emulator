package spec;

public class Constants {

    public static final int      b11111011 = -5; // TODO перегнать в bits, я тут не уверен
    public static final int   i1_b00000001 = 1;
    public static final int   i2_b00000010 = 2;
    public static final int   i3_b00000011 = 3;
    public static final int   i4_b00000100 = 4;
    public static final int   i5_b00000101 = 5;
    public static final int   i6_b00000110 = 6;
    public static final int   i7_b00000111 = 7;
    public static final int   i8_b00001000 = 8;
    public static final int   i9_b00001001 = 9;
    public static final int  i10_b00001010 = 10;
    public static final int  i11_b00001011 = 11;
    public static final int  i12_b00001100 = 12;
    public static final int  i13_b00001101 = 13;
    public static final int  i14_b00001110 = 14;
    public static final int  i15_b00001111 = 15;
    public static final int  i16_b00010000 = 16;
    public static final int  i17_b00010001 = 17;
    public static final int  i18_b00010010 = 18;
    public static final int  i19_b00010011 = 19;
    public static final int  i20_b00010100 = 20;
    public static final int  i21_b00010101 = 21;
    public static final int  i22_b00010110 = 22;
    public static final int  i23_b00010111 = 23;
    public static final int  i32_b00100000 = 32;
    public static final int  i64_b01000000 = 64;
    public static final int  i96_b01100000 = 96;
    public static final int i127_b01111111 = 127;
    public static final int i128_b10000000 = 128;
    public static final int i143_b10001111 = 143;
    public static final int i153_b10011001 = 153;
    public static final int i159_b10011111 = 159;
    public static final int i192_b11000000 = 192;
    public static final int i240_b11110000 = 0xF0;
    public static final int i255_b11111111 = 0xFF;

    public static final int x01 =   i1_b00000001;
    public static final int x02 =   i2_b00000010;
    public static final int x04 =   i4_b00000100;
    public static final int x06 =   i6_b00000110;
    public static final int x08 =   i8_b00001000;
    public static final int x09 =   i9_b00001001;
    public static final int x0F =  i15_b00001111;
    public static final int x10 =  i16_b00010000;
    public static final int x20 =  i32_b00100000;
    public static final int x40 =  i64_b01000000;
    public static final int x60 =  i96_b01100000;
    public static final int x7F = i127_b01111111;
    public static final int x80 = i128_b10000000;
    public static final int x8F = i143_b10001111;
    public static final int x99 = i153_b10011001;
    public static final int x9F = i159_b10011111;
    public static final int xC0 = i192_b11000000;
    public static final int xF0 = i240_b11110000;
    public static final int xFF = 0xFF;
    public static final int x100 = 0x100;
    public static final int x0FFF = 0x0FFF;
    public static final int x1000 = 0x1000;
    public static final int x8000 = 0x8000;
    public static final int xFFFF = 0xFFFF;
    public static final int x10000 = 0x10000;
    
    public static final int START_POINT = 0xC000;  // старт процессора после ристарта
    public static final Range SCREEN = new Range(0x9000, 0xBFFF); // область экрана
    public static final Range ROM = new Range(0xC000, 0xF7FF); // ПЗУ
    public static final Range PORTS = new Range(0xF800, 0xFFFE); // порты

    public static final int OUT_OF_MEMORY = x10000; // запредел памяти
}
