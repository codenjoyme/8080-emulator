0 REM   ..........................
1 REM   : ** NEW   PRESIDENT **  :
2 REM   :                        :
3 REM   :      ВЕРСИЯ - II.      :
4 REM   : ИСПРАВЛЕННАЯ ВЕРСИЯ-I. :
5 REM   :  БОЛТАЕВСКИЙ АЛЕКСАНДР.:
6 REM   : КРАСНОЯРСК-1992.  *BAI*:
7 REM   :........................:
8 GOSUB 7000
10 CLS0:CUR40,120
20 INPUT"ВВЕДИТЕ ВРЕМЯ ЧАС,МИН";X,Y:GOSUB9000
30 FORI=0TOX+Y:Z=RND(1):NEXTI
40 DIMA(4),B(4),C(4),C1(4),C2(4),C3(4),D(4),D1(4),E(4),E1(4),E2(4)
50 DIME3(4),F(4),F1(4),F2(4),G(4),G1(4),G3(4),G2(4),J(4)
60 DIMB1(4),B2(4),R1(4),R2(4),R3(4)
70 B3=0:N=0:S=1:A1=0:K=0:S4=0:K4=0:B(0)=1:N1=0
80 CLS0  
90 PRINT"ПОЗДРАВЛЯЕМ ВАС С ПРИБЫТИЕМ В НАШУ СТРАНУ,"
100 PRINT"ГДЕ КАЖДЫЙ МОЖЕТ СТАТЬ ПРЕЗИДЕНТОМ !!!":PRINT
110 GOSUB2670
120 INPUT "ВАШ ВОЗРАСТ";N2:CLS0:CUR40,120
130 IFN2>60THENPRINT"ЗРЯ ЕХАЛИ, В МУМИЯХ НЕ НУЖДАЕМСЯ":GOTO5400
140 IFN2<18THENPRINT"МЛАДЕНЦАМ У НАС ДЕЛАТЬ НЕЧЕГО":GOTO5400
150 GOSUB4710
160 X=1000+500*Y
170 INPUT"СКОЛЬКО ИМЕЕТЕ НАЛИЧНЫМИ";Y
180 CLS0
190 IFY<=XTHENX=Y:GOTO210
200 PRINT"ГДЕ ВЫ СТОЛЬКО ДОБЫЛИ ? ПО НАШИМ ДАННЫМ У ВАС ";
210 A(0)=X
220 GOSUB4710
230 N3=60+20*Y
240 GOSUB4710
250 N4=2+3*Y
260 REM
270 GOSUB420
280 GOSUB860
290 X=INP(0):X=X-48
300 CLS0 
310 IFX=8THEN4360
320 IFX=7THEN2750
330 IFX=6THEN3850
340 IFX=5THEN1390
350 IFX=4THEN1850
360 IFX=3THEN960
370 IFX=2THEN3280
380 IFX<>1THEN260
390 GOSUB6610
400 GOSUB440
410 GOTO280
420 PRINT:A(0)=INT(A(0)):PRINT"НАЛИЧНЫЕ СРЕДСТВА ";A(0)
430 PRINT:RETURN
440 CLS0
450 PRINT:PRINT"НАЛИЧНЫЕ СРЕДСТВА ";A(0):PRINT
460 PRINT"В АКЦИЯХ      ";A(1)
470 PRINT"СЧЕТ В БАНКЕ  ";A1
480 PRINT"НЕДВИЖИМОСТЬ  ";A(2)
490 PRINT"ГОДОВОЙ ДОХОД ";A(3)
500 PRINT"РАСХОД ЗА ГОД ";A(4)
510 X=A(0)+A(1)+A(2)+A(3)-A(4)
520 PRINT:PRINT"ВЕСЬ КАПИТАЛ В НАСТОЯЩЕЕ ВРЕМЯ СОСТАВЛЯЕТ";X:PRINT
530 RETURN
540 REM
550 PRINT:PRINT"В НАСТОЯЩЕЕ ВРЕМЯ ВЫ ";
560 IFB(0)=1THENPRINT"ТОЛЬКО БИЗНЕСМЕН"
570 IFB(1)=1THENPRINT"ЛИДЕР ПРОФСОЮЗА"
580 IFB(2)=1THENPRINT"ШЕРИФ"
590 IFB(3)=1THENPRINT"СЕНАТОР"
600 IFB(4)=1THENPRINT"ПРЕЗИДЕНТ"
610 RETURN
620 IFC(0)+C(1)+C(2)+C(3)+C(4)=0THEN690
630 PRINT"ВЫ ИМЕЕТЕ"
640 IFC(0)=1THENPRINT"КВАРТИРУ"
650 IFC(1)=1THENPRINT"МАШИНУ"
660 IFC(2)=1THENPRINT"ВИЛЛУ"
670 IFC(3)=1THENPRINT"ЯХТУ"
680 IFC(4)=1THENPRINT"СОБСТВЕННЫЙ САМОЛЕТ"
690 RETURN
700 IFD(0)+D(1)+D(2)+D(3)+D(4)=0THEN770
710 PRINT"ВЫ ОПЛАЧИВАЕТЕ УСЛУГИ"
720 IFD(0)=1THENPRINT"МАКЛЕРА"
730 IFD(1)=1THENPRINT"ВРАЧА"
740 IFD(2)=1THENPRINT"АДВОКАТА"
750 IFD(3)=1THENPRINT"ДЕТЕКТИВА"
760 IFD(4)=1THENPRINT"ЛИЧНОЙ ОХРАНЫ"
770 RETURN
780 IFE(0)+E(1)+E(2)+E(3)+E(4)=0THEN850
790 PRINT"ВЫ ВЛАДЕЛЕЦ"
800 IFE(0)=1THENPRINT"БАРА"
810 IFE(1)=1THENPRINT"РЕСТОРАНА"
820 IFE(2)=1THENPRINT"МАГАЗИНА"
830 IFE(3)=1THENPRINT"ОТЕЛЯ"
840 IFE(4)=1THENPRINT"ЗАВОДА"
850 RETURN
860 PRINT"         ЧТО ВАС ИНТЕРЕСУЕТ ?":PRINT
870 PRINT"      1-ФИНАНСОВОЕ ПОЛОЖЕНИЕ"
880 PRINT"      2-ОБЩЕСТВЕННОЕ ПОЛОЖЕНИЕ"
890 PRINT"      3-ЛИЧНОЕ ИМУЩЕСТВО"
900 PRINT"      4-ВАШИ ПОДЧИНЕННЫЕ"
910 PRINT"      5-ВАШ БИЗНЕС"
920 PRINT"      6-БИРЖЕВЫЕ ОПЕРАЦИИ"
930 PRINT"      7-БАНКОВСКИЕ ОПЕРАЦИИ"
940 PRINT"      8-РАЗВЛЕЧЕНИЯ" 
950 RETURN
960 GOSUB420
970 N1=N1+1
980 FORI=0TO4
990 GOSUB4750
1000 C3(I)=X:C2(I)=Z
1010 NEXTI
1020 IFC(0)+C(1)+C(2)+C(3)+C(4)=0THENGOTO1100
1030 PRINT"ВЫ МОЖЕТЕ ПРОДАТЬ:" ,"СТОИМОСТЬ"
1040 IFC(0)=1THENPRINT"1-КВАРТИРУ",,C2(0)
1050 IFC(1)=1THENPRINT"2-МАШИНУ",,C2(1)
1060 IFC(2)=1THENPRINT"3-ВИЛЛУ",,C2(2)
1070 IFC(3)=1THENPRINT"4-ЯХТУ",,C2(3) 
1080 IFC(4)=1THENPRINT"5-САМОЛЕТ",,C2(4)
1090 IFK=1THENRETURN
1100 IFC(0)+C(1)+C(2)+C(3)+C(4)=5THEN1200
1110 PRINT:PRINT"ВЫ МОЖЕТЕ КУПИТЬ:" ,"ЦЕНА"
1120 IFC(0)=0THENPRINT"1-КВАРТИРУ",,C3(0)
1130 IFC(1)=0THENPRINT"2-МАШИНУ",,C3(1)
1140 IFC(2)=0THENPRINT"3-ВИЛЛУ",,C3(2)
1150 IFC(3)=0THENPRINT"4-ЯХТУ",,C3(3)
1160 IFC(4)=0THENPRINT"5-САМОЛЕТ",,C3(4)
1170 PRINT"РАСХОДЫ НА СОДЕРЖАНИЕ - 45 % В ГОД'"
1180 PRINT:PRINT"ПОКУПАЕТЕ?":X=INP(0)
1190 IFX<>48  THENZ=1:GOTO1230
1200 PRINT"ПРОДАЕТЕ?":X=INP(0)
1210 IFX<>48THENZ=-1:GOTO1230
1220 GOTO1380
1230 PRINT"ЧТО?":I=INP(0):I=I-48:I=I-1:IFI<0THEN1270
1240 IFI>4THEN1270 
1250 IFZ=-1THEN1310
1260 IFC(I)=0THEN1290
1270 PRINT:PRINT"НЕ ГЛУПИТЕ"
1280 GOSUB620:GOTO4890
1290 IFA(0)>=C2(I)THEN1330
1300 GOSUB4780:GOTO4890
1310 IFC(I)=1THEN1330
1320 GOSUB4850:GOTO4890 
1330 Y=C2(I)*C(I)-C3(I)*(1-C(I))
1340 C(I)=C(I)+Z:A(0)=A(0)+Y
1350 PRINT"ЕЩЕ ОДНА СДЕЛКА?":X=INP(0):CLS0
1360 IFX=48THEN1380
1370 GOSUB420:GOTO1020
1380 CLS0:GOTO4910
1390 GOSUB420:GOSUB1400:GOTO1440
1400 FORI=0TO4
1410 GOSUB4750
1420 E3(I)=X:E2(I)=Z:E1(I)=Z*(Y-.3)
1430 NEXTI:RETURN
1440 N1=N1+1
1450 IFE(0)+E(1)+E(2)+E(3)+E(4)=0THEN1520
1460 PRINT"ВЫ МОЖЕТЕ ПРОДАТЬ","СТОИМОСТЬ","ГОДОВОЙ ДОХОД"
1470 IFE(0)=1THENPRINT"1-БАР     ",,E2(0), E1(0)
1480 IFE(1)=1THENPRINT"2-РЕСТОРАН",,E2(1), E1(1) 
1490 IFE(2)=1THENPRINT"3-МАГАЗИН ",,E2(2), E1(2)
1500 IFE(3)=1THENPRINT"4-ОТЕЛЬ   ",,E2(3), E1(3)
1510 IFE(4)=1THENPRINT"5-ЗАВОД   ",,E2(4), E1(4)
1520 IFE(0)+E(1)+E(2)+E(3)+E(4)=5THEN1610
1530 PRINT:PRINT
1540 PRINT"ВЫ МОЖЕТЕ КУПИТЬ","ЦЕНА","ГОДОВОЙ ДОХОД"
1550 PRINT:PRINT
1560 IFE(0)=0THENPRINT"1-БАР",,E3(0), E1(0)
1570 IFE(1)=0THENPRINT"2-РЕСТОРАН",,E3(1), E1(1)
1580 IFE(2)=0THENPRINT"3-МАГАЗИН ",,E3(2), E1(2)
1590 IFE(3)=0THENPRINT"4-ОТЕЛЬ   ",,E3(3), E1(3)
1600 IFE(4)=0THENPRINT"5-ЗАВОД   ",,E3(4), E1(4)
1610 REM
1620 PRINT:PRINT"ПОКУПАЕТЕ?":X=INP(0)
1630 IFX<>48THENZ=1:GOTO1670
1640 PRINT"ПРОДАЕТЕ ?":X=INP(0)
1650 IFX<>48THENZ=-1:GOTO1670
1660 GOTO1840
1670 PRINT"ЧТО ?":I=INP(0):I=I-48:I=I-1
1680 IFI<0THEN1730
1690 IFI>4THEN1730
1700 IFZ=-1THEN1760
1710 IFE(I)=0THEN1740
1720 PRINT:PRINT"ВЫ СПЯТИЛИ?"
1730 GOSUB780:GOTO4910
1740 IFA(0)>=E2(I)THEN1780
1750 GOSUB4850:GOTO4910
1760 IFE(I)=1THEN1780
1770 GOSUB4850:GOTO4910
1780 Y=E2(I)*E(I)-E3(I)*(1-E(I))
1790 E(I)=E(I)+Z
1800 A(0)=A(0)+Y
1810 PRINT"ЕЩЕ ОДНА СДЕЛКА?":X=INP(0)
1820 IFX<=48THEN1840
1830 CLS0:GOSUB420:GOTO1450
1840 GOTO4910
1850 GOSUB4710
1860 FORI=0TO4
1870 GOSUB4710
1880 D1(I)=4500*Y+2000*(I+1)
1890 NEXTI
1900 N1=N1+1
1910 IFD(0)+D(1)+D(2)+D(3)+D(4)=0THEN1990
1920 PRINT"ВЫ МОЖЕТЕ УВОЛИТЬ","ЗАПЛАТИВ НЕУСТОЙКУ"
1930 IFD(0)=1THENPRINT"1-МАКЛЕРА         ",,D1(0)*2
1940 IFD(1)=1THENPRINT"2-ВРАЧА           " ,,D1(1)*2
1950 IFD(2)=1THENPRINT"3-АДВОКАТА        "  ,,D1(2)*2
1960 IFD(3)=1THENPRINT"4-ДЕТЕКТИВА       ",,D1(3)*2
1970 IFD(4)=1THENPRINT"5-ПАРНЕЙ ИЗ ОХРАНЫ",,D1(4)*2
1980 PRINT
1990 IFD(0)+D(1)+D(2)+D(3)+D(4)=5THEN2070
2000 PRINT
2010 PRINT"ВЫ МОЖЕТЕ НАНЯТЬ";"      УПЛАТИВ В ГОД"
2020 IFD(0)=0THENPRINT"1-МАКЛЕРА         " ,D1(0)
2030 IFD(1)=0THENPRINT"2-ВРАЧА           " ,D1(1)
2040 IFD(2)=0THENPRINT"3-АДВОКАТА        " ,D1(2)
2050 IFD(3)=0THENPRINT"4-ДЕТЕКТИВА       " ,D1(3)
2060 IFD(4)=0THENPRINT"5-ЛИЧНУЮ ОХРАНУ   " ,D1(4)
2070 PRINT:PRINT"НАНИМАЕТЕ ?":X=INP(0)
2080 IFX<>48THENZ=1:GOTO2120
2090 PRINT"УВОЛЬНЯЕТЕ ?":X=INP(0)
2100 IFX<>48THENZ=-1:GOTO2120
2110 GOTO2270
2120 PRINT"КОГО ?":I=INP(0):I=I-48:I=I-1
2130 IFI<0THEN2180
2140 IFI>4THEN2180
2150 IFZ=-1THEN2190
2160 IFD(I)=0THEN2230
2170 PRINT:PRINT"У ВАС СКЛЕРОЗ ?"
2180 GOSUB700:GOTO4890
2190 IFD(I)=1THEN2220
2200 PRINT:PRINT"ВЫ РЕХНУЛИСЬ ?"
2210 GOTO2180
2220 A(0)=A(0)-D1(I)*.2
2230 D(I)=D(I)+Z
2240 PRINT"ЕЩЕ ОДНА СДЕЛКА ?":X=INP(0):CLS0
2250 IFX<=48THEN190
2260 CLS0:GOSUB420:GOTO1910
2270 GOTO4910
2280 REM
2290 GOSUB4710
2300 Y=Y*10:Y=INT(Y)
2310 IFY>=5THENRETURN
2320 I=Y
2330 IFD(I)=1THENRETURN
2340 GOSUB4710
2350 X=A(0)+Y
2360 IFI>0THEN2410
2370 PRINT"ИГРАЯ НА БИРЖЕ, ВЫ НЕПРАВИЛЬНО ОФОРМЛЯЛИ СДЕЛКИ"
2380 PRINT"    УБЫТОК СОСТАВИЛ";X:FORH=0TO500:NEXTH
2390 PRINT" ЗАВЕДИТЕ ХОРОШЕГО МАКЛЕРА"
2400 GOTO2590
2410 IFI>1THEN2450
2420 PRINT"ЗАНИМАЯСЬ БИЗНЕСОМ, ВЫ ЗАБЫВАЕТЕ О ЗДОРОВЬЕ" 
2430 PRINT"ПРЕБЫВАНИЕ В БОЛЬНИЦЕ ОБОШЛОСЬ ВАМ В ";X
2440 GOTO2590
2450 IFI>2THEN2550
2460 PRINT"ФИРМА  IBM  ВОЗБУЛИЛА ПРОТИВ ВАС СУДЕБНОЕ ДЕЛО"
2470 PRINT"НАНИМАЕТЕ АДВОКАТА ?"
2480 PRINT"ЕГО УСЛУГИ ОБОЙДУТСЯ ВАМ В";X*.5
2490 Z=INP(0):Z=Z-48
2500 IFZ<=0THEN2530
2510 PRINT"ВЫ ВЫИГРАЛИ ДЕЛО, СОВЕТУЕМ ЗАИМЕТЬ СВОЕГО ЮРИСТА"
2520 X=X*.5:GOTO2590
2530 PRINT"СУДЕБНЫЙ ИСК ФИРМЫ К ВАМ УДОВЛЕТВОРЕН ВЫ ПОТЕРЯЛИ";X
2540 GOTO2590
2550 IFI>3THEN2600
2560 PRINT"ВАС ШАНТАЖИРУЮТ, ВЫМОГАЯ";X:FORH=0TO500:NEXTH
2570 PRINT"СОЧУВСТВУЕМ, НО ВЫ ВОВРЕМЯ НЕ ОБРАТИЛИСЬ"
2580 PRINT"К УСЛУГАМ СЫСКНОГО БЮРО, ПРИДЕТСЯ ПЛАТИТЬ"
2590 A(0)=A(0)-X:GOTO2660
2600 X=0
2610 FORI=0TO4
2620 X=X+F(I)*F1(I):F(I)=0
2630 NEXTI
2640 IFX=0THENRETURN
2650 PRINT"ВАС ОБЧИСТИЛИ, ЗАБРАВ ВСЕ ЦЕННЫЕ БУМАГИ.УБЫТОК СОСТАВИЛ";X
2660 PRINT"УЧТИТЕ НА БУДУЩЕЕ. ":RETURN
2670 PRINT"MOЖНО ДАЖЕ ПРЕДСТАВИТЬ , ЧТО ..."
2680 FORI=0TO4:C(I)=1:D(I)=1:E(I)=1:NEXTI
2690 GOSUB630
2700 GOSUB710
2710 GOSUB790
2720 PRINT"И СЧЕТ В БАНКЕ-1000000, НО ПОКА ЭТО МЕЧТЫ.ОСУЩЕСТВИТЕ ИХ"
2730 FORI=0TO4:C(I)=0:D(I)=0:E(I)=0:NEXTI
2740 RETURN
2750 IF RND(1)<.97THEN2780
2760 PRINT"БАНК- Б А Н К Р О Т ,ВАМ ВЫПЛАЧЕНА КОМПЕНСАЦИЯ";
2770 PRINTA1*.5:A(0)=INT(A(0)+A1*.05):A1=0:GOTO4900
2780 PRINT"            --------------------------------------"
2781 PRINT"              O   O   O OOOOO O O   O   O   OOO  O"
2782 PRINT"             O O  OO  O   O   O OO  O  O O  O  O O"
2783 PRINT"            O   O O O O   O   O O O O O   O O  O O"
2784 PRINT"            OOOOO O  OO   O   O O  OO OOOOO OOO  O"
2785 PRINT"            O   O O   O   O   O O   O O   O O  O O "
2786 PRINT 
2787 PRINT"            ------------- B A N K ----------------"
2788 PRINT 
2789 PRINT"            ПРИВЕТСТВУЕТ СВОЕГО КЛИЕНТА , ЧТО ЖЕЛАЕТЕ"
2790 PRINT:PRINT"1-ЗАСТРАХОВАТЬ ИМУЩЕСТВО":PRINT"2-СДЕЛАТЬ ВКЛАД"
2800 PRINT"3-СНЯТЬ СО СЧЕТА":N1=N1+1
2810 IFS4=0THENPRINT"4-ДАТЬ ССУДУ"
2820 IFK4=0THENPRINT"5-ПОЛУЧИТЬ КРЕДИТ"
2830 A1=INT(A1):X=INP(0):X=X-48
2840 IFX=0THEN4910
2850 IFX>5THEN4910
2860 IFX=1THEN3080
2870 IFX=2THEN2980
2880 IFX=4THEN5910
2890 IFX=5THEN6070
2900 A1=INT(A1):PRINT"СЧЕТ В БАНКЕ",A1
2910 PRINT"СТОИМОСТЬ ОПЕРАЦИИ 5 %"
2920 INPUT"СКОЛЬКО БЕРЕТЕ";X
2930 IFX<=A1THEN2950
2940 GOTO3030
2950 A1=A1-X:A(0)=A(0)+X*.95
2960 IFK=1THEN RETURN
2970 GOTO4910
2980 PRINT:GOSUB420
2990 PRINT"ГАРАНТИРУЕМ ";2*S"% ГОДОВОГО ДОХОДА"
3000 PRINT"СТОИМОСТЬ ОПЕРАЦИИ    5 % "
3010 INPUT"СКОЛЬКО ПОМЕЩАЕТЕ";X
3020 IFX<A(0)THEN3070
3030 GOSUB4780
3040 PRINT"ЯСНО ?":PRINT"ПРОДОЛЖЕНИЕ - ЛЮБАЯ КЛАВИША":A=INP(0)
3050 IFK=1THENRETURN
3060 GOTO4910
3070 A(0)=A(0)-X:A1=A1+X*.95:GOTO4910 
3080 PRINT"МОЖНО ЗАСТРАХОВАТЬ ТО,ЧТО"
3090 GOSUB630:K=0
3100 PRINT"ГОДОВОЙ СТРАХОВОЙ ВЗНОС - 5 %"
3110 PRINT"ОФОРМЛЯЕТЕ СТРАХОВКУ ?":X=INP(0):X=X-48
3120 CLS0
3130 IFX<=0THEN4910
3140 K=1:PRINT"РЕАЛЬНО МЫ ОЦЕНИВАЕМ"
3150 GOSUB1040:K=0
3160 PRINT"ЧТО СТРАХУЕТЕ ?":I=INP(0):I=I-48 
3170 IFI>5THEN3270
3180 IFI=0THEN3270
3190 I=I-1:G2(I)=I:G3(I)=1
3200 INPUT"НА СУММУ";X
3210 G(I)=X
3220 INPUT"НА КАКОЙ СРОК";X
3230 G1(I)=X
3240 PRINT"ЕЩЕ ОДНА СТРАХОВКА ?":X=INP(0)
3250 CLS0 
3260 IFX<>48THEN3140
3270 GOTO4910
3280 IFB3=0THEN3310
3290 PRINT:PRINT"В ЭТОМ ГОДУ ВЫ УЖЕ УЧАВСТВОВАЛИ В ВЫБОРАХ"
3300 GOTO3840
3310 GOSUB420
3320 GOSUB540
3330 X=N
3340 IFX=0THEN3400
3350 IFX<1THEN3380
3360 X=X-2
3370 GOTO3340
3380 PRINT:PRINT"В ЭТОМ ГОДУ ВЫБОРЫ НЕ ПРОВОДЯТСЯ":PRINT
3390 GOTO3840
3400 FORI=0TO4
3410 GOSUB4750
3420 B1(I)=X
3430 NEXTI
3440 FORI=0TO4
3450 IFB(I)=1THENX=I+1
3460 NEXTI
3470 Y=0
3480 FORI=0TO4
3490 Y=Y+.5*C(I)+2*D(I)+.5*E(I)
3500 NEXTI
3510 I=X:B2(I)=Y/(5*X)
3520 IFB2(I)>1THENB2(I)=1
3530 PRINT"В ЭТОМ ГОДУ ВЫ МОЖЕТЕ ПРИНЯТЬ УЧАСТИЕ В ВЫБОРАХ"
3540 N1=N1+1:PRINT
3550 PRINT"ЕСТЬ","ПРЕДВЫБОРНАЯ КАМПАНИЯ ","ВЕРОЯТНОСТЬ УСПЕХА"
3560 PRINT"МЕСТО","ОБОЙДЕТСЯ В -",,"СОСТАВЛЯЕТ В %"
3570 PRINT:IFX=4THENPRINT"ПРЕЗИДЕНТА"
3580 IFX=3THENPRINT"СЕНАТОРА"
3590 IFX=2THENPRINT"ШЕРИФА"
3600 IFX=1THENPRINT"ЛИДЕРА ПРОФСОЮЗА"
3610 IFX=1THENPRINT"МУСОРЩИКОВ"
3620 PRINT,B1(I),,B2(I)*100
3630 PRINT
3640 PRINT"УЧАВСТВУЕТЕ В ВЫБОРАХ ?":Y=INP(0):Y=Y-48
3650 IFY<>0THEN3680
3660 PRINT:PRINT"ЗАПОМНИТЕ, СЛЕДУЮЩИЕ ВЫБОРЫ ЧЕРЕЗ 2 ГОДА" 
3670 GOTO3840
3680 GOSUB4710
3690 IFA(0)>=B1(I)THEN3720
3700 GOSUB4780
3710 GOTO3840
3720 I=X
3730 IFY<B2(I)THEN3770
3740 PRINT:PRINT"ВЫ НЕ ПОЛЬЗУЕТЕСЬ ПОПУЛЯРНОСТЬЮ У ИЗБЕРАТЕЛЕЙ"
3750 PRINT"ВАС ПРОКАТИЛИ. ВЫ НАБРАЛИ ТОЛЬКО",Y*50"% ГОЛОСОВ"
3760 PRINT:GOTO3830
3770 PRINT:PRINT"ПОЗДРАВЛЯЕМ ВАС, ТЕПЕРЬ ВЫ   ";
3780 S=S+1
3790 I=X:B(I)=1:I=I-1:B(I)=0
3800 GOSUB560
3810 IFB(4)=1THENPRINT"ВОТ ВЫ УЖЕ ПРЕЗИДЕНТ, НО СЧЕТ В БАНКЕ ЕЩЕ МАЛ";
3811 IFA1<1E+6THENPRINTA1
3820 IFB(4)=1ANDA1>1E+06  THEN5400
3830 I=X:A(0)=A(0)-B1(I)
3840 B3=1:GOTO4890
3850 IFN1>N4THEN4290
3860 GOSUB2290
3870 X=0:GOSUB4730
3880 N1=N1+1
3890 PRINT"ТЕКУЩЕЕ ПОЛОЖЕНИЕ БИРЖЕВЫХ ДЕЛ -":PRINT
3900 PRINT"АКЦИИ"
3910 PRINT"ФИРМЫ",,"ИМЕЕТЕ","КУРС","ДИВИД.%":PRINT
3920 PRINT"1-PLAY SCHOOL ", F(0),F1(0),F2(0)
3930 PRINT"2-TOYOTA      ", F(1),F1(1),F2(1)
3940 PRINT"3-I B M       ", F(2),F1(2),F2(2)
3950 PRINT"4-BOING       ", F(3),F1(3),F2(3)
3960 PRINT"5-KRUFT & CO. ", F(4),F1(4),F2(4)
3970 GOSUB420
3980 PRINT"ПОКУПАЕТЕ ?":X=INP(0):X=X-48
3990 IFX<>0THENZ=1:GOTO4030
4000 PRINT"ПРОДАЕТЕ ?":X=INP(0):X=X-48
4010 IFX<>0THENZ=-1:GOTO4030
4020 GOTO4910
4030 PRINT"КАКОЙ ФИРМЫ ?":I=INP(0):I=I-48:IFI<=0THEN4020
4040 IFI>5THEN4020
4050 I=I-1
4060 IFI=J(I)THEN4340
4070 J(I)=I
4080 INPUT"   СКОЛЬКО";X:X=INT(X)
4090 IFZ=-1THEN4180
4100 IFX*F1(I)>A(0)THEN4160
4110 W=F1(I)*5*S
4120 IFX<=WTHEN4140
4130 PRINT"УДАЛОСЬ СКУПИТЬ ТОЛЬКО ";W:X=W
4140 Y=F1(I)*X
4150 IFA(0)>=YTHEN4270
4160 CLS0:CUR40,170:PRINT"СДЕЛКА ЛИКВИДИРОВАНА":PRINT
4170 GOSUB4780:GOTO4890
4180 IFX<=F(I)+.1THEN4230
4190 PRINT"                ВЫ ИМЕЕТЕ",F(I);"АКЦИЙ"
4200 PRINT"                A ХОТИТЕ ПРОДАТЬ",X
4210 PRINT"                СДЕЛКА ЛИКВИДИРОВАНА"
4220 GOSUB4850:GOTO4890
4230 W=(105-F1(I))*5*S
4240 IFX<=WTHEN4260
4250 PRINT:PRINT"УДАЛОСЬ ПРОДАТЬ ТОЛЬКО";W:X=W
4260 Y=F1(I)*X
4270 F(I)=F(I)+X*Z:A(0)=A(0)-Y*Z
4280 IFN1<=N4THEN4310
4290 PRINT:PRINT"БИРЖА ЗАКРЫТА НА РОЖДЕСТВЕНСКИЕ КАНИКУЛЫ"
4300 GOTO4890
4310 PRINT"ЕЩЕ ОДНА СДЕЛКА ?":X=INP(0):X=X-48
4320 IFX<>0THENCLS0:GOTO3900
4330 GOTO4910
4340 IFZ=1THENPRINT"АКЦИЙ БОЛЬШЕ В ПРОДАЖЕ НЕТ":GOTO4310
4350 PRINT"СРЕДИ ДЕЛОВЫХ ЛЮДЕЙ ДУРАКОВ МАЛО":GOTO4310
4360 GOSUB420
4370 N1=N1+5
4380 FORI=0TO4
4390 GOSUB4750
4400 R1(I)=X:R2(I)=R1(I)*(2-Y):R3(I)=Y*100
4410 NEXTI
4420 PRINT"РАЗВЛЕКАЯСЬ С УМОМ МОЖНО ПОЛУЧИТЬ И БАРЫШ":PRINT
4430 PRINT"МОЖЕМ ПРЕДЛОЖИТЬ      ЗАТРАТЫ     ВОЗМОЖН ДОХОД";
4440 PRINT       "  ВЕРОЯТН УСПЕХА":PRINT
4450 PRINT"1-ПРЕФЕРАНC",R1(0),R2(0),R3(0)
4460 PRINT"2-МОНТЕ-КАРЛО",R1(1),R2(1),R3(1)
4470 PRINT"3-ЛЮБОВНИЦУ",R1(2),R2(2),R3(2)
4480 PRINT"4-БАНКЕТ",R1(3),R2(3),R3(3)
4490 PRINT"5-КРУИЗ",R1(4),R2(4),R3(4)
4500 PRINT:PRINT"ГУЛЯЕМ ?":X=INP(0):X=X-48
4510 IFX<>0THEN4550
4520 PRINT:PRINT"ВАМ ГОВОРИЛИ УЖЕ, ЧТО ВЫ  Ж М О Т  ?"
4530 FORH=1TO2000:NEXTH:REM *ЗАДЕРЖКА*
4540 CLS0:GOTO4910
4550 PRINT:PRINT"      ЧЕГО ИЗВОЛЕТЕ-С ?":X=INP(0):X=X-48
4560 I=X-1:IFI<0THEN4540
4570 IFI>4THEN4540
4580 IFA(0)+A1>R1(I)THEN4610
4590 GOSUB4780:GOSUB4890
4600 GOTO4540
4610 GOSUB4710
4620 Y=Y*100
4630 IFY<R3(I)THEN4670
4640 PRINT:PRINT"ВАМ НЕ ВЕЗЕТ ОДНИ РАСХОДЫ. ЯСНО ?"
4650 FORH=0TO 1000:NEXTH
4660 A(0)=A(0)-R1(I):GOTO4540
4670 PRINT:PRINT"НА ЭТОТ РАЗ ФОРТУНА ПОВЕРНУЛАСЬ К ВАМ ЛИЦОМ"
4680 PRINT"       ДОВОЛЬНЫ ?"
4690 FORH=0TO2000:NEXTH
4700 A(0)=A(0)-R1(I)+R2(I):GOTO4540
4710 Y=INT(100*RND(1))/100:IFY<.05THENY=.05
4720 RETURN
4730 FORI=0TO4:GOSUB4710:F1(I)=100*Y:J(I)=-1:GOSUB4710
4740 F2(I)=20*Y:NEXTI:RETURN
4750 GOSUB4710
4760 X=(200+500*Y)*(5^I)
4770 Z=X*Y:RETURN
4780 PRINT:PRINT"ВЫ НЕ ИМЕЕТЕ ТРЕБУЕМОГО КОЛИЧЕСТВА НАЛИЧНЫХ СРЕДСТВ"
4790 PRINT"ЗА ПОПЫТКУ МОШЕННИЧЕСТВА"
4800 GOSUB4710
4810 Q=1000+500*Y
4820 PRINT" ВЫ ОШТРАФОВАНЫ НА ";Q
4830 A(0)=A(0)-Q
4840 RETURN
4850 PRINT:PRINT"ВАМ НЕЧЕГО ПРОДАВАТЬ"
4860 PRINT"ЖУЛИКОВ И У НАС НАКАЗЫВАЮТ"
4870 GOSUB4790
4880 RETURN
4890 PRINT"УСВОИЛИ ?"
4900 FORH=0TO3000:NEXTH
4910 CLS0
4920 GOSUB6350
4930 IFA(0)<0THEN4950
4940 IFN1<N4THEN260
4950 PRINT"ЗАКОНЧИЛСЯ ";1991+N;" ГОД"
4960 PRINT"ПОДВЕДЕМ ИТОГИ"
4970 N=N+1:N2=N2+1
4980 GOSUB550
4990 GOSUB620 
5000 GOSUB700
5010 GOSUB780
5020 GOSUB6610:FORH=0TO1500:NEXTH
5030 A1=A1+A1*S*.02
5040 GOSUB440
5050 PRINT"БУДЕТЕ ОПЛАЧИВАТЬ РАСХОДЫ ?":Z=INP(0):Z=Z-48
5060 CLS0
5070 IFC(0)+C(2)>0THEN5140
5080 GOSUB4710
5090 X=Y*A(0)+1000:IFX<0THENA(0)=A(0)+X:GOTO5110
5100 A(0)=A(0)-X
5110 PRINT"ЗА БРОДЯЖНИЧЕСТВО ВЫ ОШТРАФОВАНЫ НА";X
5120 PRINT"ВАМ ВСЕ ЯСНО ?"
5130 FORH=0TO2000:NEXTH
5140 IFZ<>0THEN5210
5150 GOSUB4710
5160 X=Y*A(0)+1000:IFX<0THENA(0)=A(0)+X:GOTO5180
5170 A(0)=A(0)-X
5180 PRINT" ОШТРАФОВАНЫ НАЛОГОВЫМ УПРАВЛЕНИЕМ НА СУММУ";X
5190 PRINT"        ЗА СОКРЫТИЕ ДОХОДОВ . ПОНЯТНО ?"
5200 FORH=0TO2000:NEXTH
5210 A(0)=A(0)+A(3)-A(4)
5220 IF A(0)<0 THEN PRINT"У ВАС ДЕФИЦИТ СРЕДСТВ";A(0)
5230 IFA(0)>0THEN5270
5240 IFA1>0THENK=1:GOSUB2900
5250 K=0
5260 IFA(0)<0THEN5480
5270 CLS0
5280 IFN2>=N3THEN5360
5290 PRINT"НАСТУПИЛ НОВЫЙ ";1991+N;"ГОД"
5300 N1=0:B3=0:GOSUB1400
5310 GOSUB6700
5320 GOSUB6220
5330 GOSUB6280
5340 IFA(0)<0THEN5220 
5350 GOTO240
5360 PRINT"МЕЖДУНАРОДНЫЙ БИЗНЕС ПОНЕС НЕВОСПОЛНИМУЮ УТРАТУ"
5370 PRINT"НА ";N2;"ГОДУ ЗАКОНЧИЛАСЬ ДЕЯТЕЛЬНОСТЬ НАШЕГО КОЛЛЕГИ"
5380 PRINT"ПРИМИТЕ СОБОЛЕЗНОВАНИЯ"
5390 FORH=0TO2000:NEXTH:PRINT
5400 INPUT"НАЧНЕМ СНАЧАЛА";X
5410 IFX=0THENGOSUB9500:STOP
5420 FORI=0TO4
5430 A(I)=0:B(I)=0:C(I)=0:C1(I)=0:C2(I)=0:C3(I)=0:D(I)=0:D1(I)=0
5440 E(I)=0:E1(I)=0:E2(I)=0:E3(I)=0:F(I)=0:F1(I)=0:F2(I)=0:G(I)=0
5450 G1(1)=0:G3(I)=0:G2(I)=0:J(I)=0:B1(I)=0:B2(I)=0
5460 NEXTI
5470 GOTO70
5480 IFA(1)>0THEN5510  
5490 IFA(2)>0THEN5600
5500 GOTO5700
5510 GOSUB4710
5520 X=A(1)*Y:A(0)=A(0)+X:A(1)=0
5530 FORI=0TO4
5540 F(I)=0
5550 NEXTI
5560 PRINT"ВАШИ АКЦИИ РАСПРОДАНЫ НА СУММУ ";X
5570 PRINT" ДОПРЫГАЛИСЬ ?"
5580 FORH=0TO2000:NEXTH
5590 GOTO5260
5600 GOSUB4710
5610 X=A(2)*Y:A(0)=A(0)+X:A(2)=0
5620 FORI=0TO4
5630 C(I)=0:E(I)=0:D(I)=0
5640 NEXTI
5650 PRINT"ВАШЕ ИМУЩЕСТВО ПОШЛО С МОЛОТКА,УДАЛОСЬ ВЫРУЧИТЬ";X
5660 PRINT"ВАШИ ПОДЧИНЕННЫЕ ВАС БРОСИЛИ"
5670 PRINT"ДОИГРАЛИСЬ ?"
5680 FORH=0TO1700:NEXTH
5690 GOTO5260
5700 X=-A(0)/500:X=INT(X)
5710 X=X+1
5720 IFX>15THEN5890
5730 N2=N2+X:N=N+X:A(0)=0
5740 FORI=0TO4
5750 B(I)=0:S=1
5760 NEXTI    
5770 B(0)=1
5780 PRINT"ЗА ДОЛГИ И НЕОПЛАЧЕННЫЕ СЧЕТА ВЫ ПЕРЕЕЗЖАЕТЕ"
5790 PRINT"НА КАЗЕННУЮ КВАРТИРУ В FORT BASTILIA СРОКОМ НА";X
5800 K2=K2-X:S6=S6-X
5810 PRINT"ПОСИДИМ ?":FORH=0TO3100:NEXTH:CLS0
5820 GOSUB4710
5830 X=1000+500*Y:A(0)=X
5840 A(3)=0:A(4)=0
5850 PRINT"ПОСЛЕ ОСВОБОЖДЕНИЯ ВАМ ВЫДАЛИ ПОД'ЕМНЫЕ ";X;"$"
5860 PRINT"ПОЛУЧИЛИ УРОК ?"
5870 FORH=0TO2000:NEXTH
5880 GOTO5270
5890 PRINT"ЗА ОГРОМНЫЕ ДОЛГИ, ВЫ ПРИГОВОРЕНЫ К ВЫСШЕЙ МЕРЕ"
5900 GOTO5360
5910 CLS0:GOSUB420
5920 PRINT"СНАЧАЛА ДОГОВОРИМСЯ ОБ УСЛОВИЯХ":PRINT
5930 INPUT"СКОЛЬКО ХОТИТЕ ДАТЬ";S1
5940 IFS1<A(0)+A1THEN5960
5950 GOSUB4780:GOTO4910
5960 INPUT"НА КАКОЙ СРОК";S6
5970 IFS6>10THENPRINT"СДУРЕЛИ ?":GOSUB5960
5980 INPUT"ПОД КАКИЕ ПРОЦЕНТЫ";S2
5990 S3=50/(60+S2):S2=S2/100
6000 PRINT"ШАНС ЗАРАБОТАТЬ";S3*100;"ПРОЦЕНТОВ"
6010 PRINT"ПО РУКАМ ?":X=INP(0):X=X-48
6020 IFX<=0THEN4910
6030 S4=1:A(0)=A(0)-S1
6040 GOSUB4710
6050 IFY<S3THENS5=1:GOTO4910
6060 S5=0:GOTO4910
6070 CLS0:GOSUB420
6080 K5=A(0)+A1+A(1)+A(2)+A(3)-A(4)
6090 IFK5<0THENPRINT"БАНКРОТАМ НЕ ДАЕМ":GOTO4900
6100 PRINT"СО ВСЕМИ ПОТРОХАМИ ВЫ СТОИТЕ";K5
6110 PRINT"ДАЕМ НА СРОК НЕ БОЛЕЕ 5 ЛЕТ"
6120 INPUT"СКОЛЬКО ПРОСИТЕ";K1
6130 PRINT"НА КАКОЙ СРОК ?":K2=INP(0):K2=K2-48
6140 IFK2>5THENPRINT"ОЧУМЕЛИ ? ЧИТАТЬ НЕ УМЕЕТЕ ?":GOTO6130
6150 K3=K1/K2
6160 PRINT"ДАЕМ ПОД";K3*100;"ПРОЦЕНТОВ"
6170 PRINT"БЕРЕТЕ ?":X=INP(0):X=X-48
6180 IFX=0THEN4910
6190 PRINT"ЗАПОМНИТЕ  ВРЕМЯ РАСПЛАТЫ";1991+N+K2;"ГОД"
6200 FORH=0TO1900:NEXTH
6210 A(0)=A(0)+K1:K4=1:GOTO4910
6220 IFK4=0THENRETURN
6230 K2=K2-1
6240 IFK2>0THENRETURN
6250 X=K1+K1*K3:A(0)=A(0)-X:K4=0
6260 PRINT"С ВАС УДЕРЖАЛИ КРЕДИТ И ПРОЦЕНТЫ В РАЗМЕРЕ";X
6270 RETURN
6280 IFS4=0THENRETURN
6290 S6=S6-1
6300 IFS6>0THENRETURN
6310 S4=0
6320 IFS5=0THENPRINT"АФЕРА С ССУДОЙ НЕ УДАЛАСЬ, ВАС НАДУЛИ":RETURN
6330 X=S1+S1*S2:A(0)=A(0)+X
6340 PRINT"ВЫ УДАЧНО ССУДИЛИ ДЕНЬГИ, БАРЫШ СОСТАВИЛ";X:RETURN
6350 GOSUB4710
6360 IFY>.2 THEN RETURN
6370 GOSUB4710
6380 Y=Y*10:Y=INT(Y)
6390 IFY=0THEN RETURN
6400 IFY>=5 THEN RETURN
6410 I=Y
6420 IFC(I)=0 THEN RETURN
6430 IFG3(I)=0 THEN 6450 
6440 IFG(I)>C2(I)*1.5 THEN RETURN
6450 X=C2(I)
6460 IFY=1 THEN 6510
6470 IFY=2 THEN 6530
6480 IFY=3 THEN 6540
6490 PRINT"НА СВОЕМ САМОЛЕТЕ ВЫ ПОПАЛИ В КАТАСТРОФУ":FORH=0TO500:NEXTH
6500 PRINT"ЛЕТАЙТЕ САМОЛЕТАМИ *АЭРОФЛОТА*":PRINT:GOTO5360
6510 PRINT"ПОПАЛИ В АВТОКАТАСТРОФУ И ЧУДОМ ОСТАЛИСЬ ЖИВЫ"
6520 PRINT"ОСТАТКИ МАШИНЫ МОЖЕТЕ ВЫБРОСИТЬ":GOTO6550
6530 PRINT"СИОНИСТЫ ПОДОЖГЛИ ВАШУ ВИЛЛУ":GOTO6550
6540 PRINT"ЭКСТРЕМИСТЫ УВЕЛИ И ПОТОПИЛИ ВАШУ ЯХТУ"
6550 PRINT"НАНЕСЕН УЩЕРБ В РАЗМЕРЕ";X
6560 IFG3(I)=0THEN6580
6570 PRINT"СТРАХОВАЯ КОМПАНИЯ ВАМ ВЫПЛАТИЛА";G(I)
6580 A(0)=A(0)-X+G3(I)*G(I)
6590 C(I)=0:G(I)=0:G1(I)=0:G2(I)=0:G3(I)=0
6600 RETURN
6610 A(1)=0:A(2)=0:A(3)=0:A(4)=0
6620 FORI=0TO4
6630 A(1)=F(I)*F1(I)+A(1)
6640 A(2)=C(I)*C3(I)+E(I)*E3(I)+A(2)
6650 A(3)=E(I)*E1(I)+F(I)*F2(I)*0.5+A(3)
6660 A(4)=D(I)*D1(I)+C(I)*C2(I)*0.45+G3(I)*G(I)*.05+A(4):NEXTI
6670 GOSUB4710
6680 A(4)=A(4)+1000+500*Y
6690 RETURN
6700 FORI=0TO4
6710 G1(I)=G1(I)-1
6720 IFG1(I)>0THEN RETURN
6730 G(I)=0:G1(I)=0:G2(I)=0:G3(I)=0:NEXTI:RETURN
6740 GOSUB6350
6750 PRINT
6760 GOTO6740
6800 REM ЗАСТАВКА 
7000 I=INT(RND(1)*200)+2:II=INT(RND(1)*240)+5
7010 POKE -28678,I:POKE -28677,II:CLS0
7020 FOR I=100 TO 260:PLOTI,100,1:LINEI,160:NEXT
7030 CUR 80,140:PRINT"N E W"
7040 CUR 65,120:PRINT"P R E S I D E N T"
7050 FOR I=1 TO 29
7055 IF PEEK(-3)<254 THEN 7200
7060 PLOT 100+I,100+I,3:LINE 100+I,160-I:LINE260-I,160-I
7070 LINE 260-I,100+I:LINE 101+I,100+I
7075 IF PEEK(-3)<254 THEN 7200
7080 NEXT
7090 FOR I=29 TO 1 STEP -1
7095 IF PEEK(-3)<254 THEN 7200
7100 PLOT 100-I,100-I,3:LINE 100-I,160+I:LINE 260+I,160+I
7110 LINE 260+I,100-I:LINE 101-I,100-I
7115 IF PEEK(-3)<254 THEN 7200
7120 NEXT
7130 IF PEEK(-3)<254 THEN 7200
7140 G=INT(RND(1)*2)+1
7150 ON G GOTO 7000,7050
7200 CLS1:RETURN
9000 FOR I=1 TO 95 :POKE -28687,I:POKE -28686,56:A=USR(-16016)
9010 A=USR(-16016):NEXT
9020 FOR I=1 TO 10:POKE -28687,10:POKE-28686,20:A=USR(-16016):NEXT
9030 RETURN
9500 FORH=1TO3:POKE-28686,200:POKE-28687,200:A=USR(-16016):NEXT
9510 RETURN