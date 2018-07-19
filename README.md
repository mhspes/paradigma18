# paradigma18

TASK LIST:  
~~Literaalit lukuarvoille  
Literaalit totuusarvoille  
Literaalit merkkionoille  
Laskutoimitukset (+,-,*,/)  
Logiikka (and, or, not) riittää, täydellinen konnektiivijoukko  
Vertailu (==,<,>,!=)~~  
~~Kontrollivuon ohjaus (IF, ElSE, DO, LOOP)  
Forth manipulointikäskyt (dup,rot,swap, drop, over, nip, tuck)~~
~~Syöttö/tulostuskäskyt (esim. 30 print)  ~~
Graafiset käskyt  
Demo-ohjelma  
Dokumentti  

Käyttö
Literaalin tallennus "literaali" ("Hello_world!" print drop) tulostaa 

Käsky   Parametrit  Selitys                    Esim
print     1         Tulostus
read                Pyytää merkkijonosyötteen
VARIABLE  1         Määrittää muuttujan        VARIABLE pvm


"15 7 1 1 + - / 3 * 2 1 1 + + - ."  = (3*(15/((7-(1+1))))-(2+(1+1)) = 9-4 = 5  
"5 6 < 4 7 < and"  = 5<6 and 4<7 = true  
"true false or false false or and print"  = ((true) or (false)) and ((false) or (false)) = false  

