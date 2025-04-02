## Flujo de Caracteres
```
entero a = 15 + 12;

imprimir(a);
imprimir(7);
imprimir(a + 5);
```

## Flujo de Tokens
```
LEXEMA                   LINEA COLUMNA TIPO
entero                   1     7       KW_entero
a                        1     9       TK_id
=                        1     11      TK_igual
15                       1     14      TK_entero
;                        1     15      TK_ptComa 
imprimir                 3     9       KW_imprimir
(                        3     10      TK_parIzq
a                        3     11      TK_id
)                        3     12      TK_parDer
;                        3     13      TK_ptComa
imprimir                 4     9       KW_imprimir
(                        4     10      TK_parIzq
7                        4     11      TK_entero
)                        4     12      TK_parDer
;                        4     13      TK_ptComa
imprimir                 5     9       KW_imprimir
(                        5     10      TK_parIzq
a                        5     11      TK_id
+                        5     13      TK_mas
5                        5     15      TK_entero
)                        5     16      TK_parDer
;                        5     17      TK_ptComa
```

## Gramática Libre del Contexto
```html
<inicio> ::= <instrucciones> EOF

<instrucciones> ::= <instruccion> <instrucciones> | <instruccion>

<instruccion> ::= <declaracion> | <impresion>

<declaracion> ::= 'entero' TK_id '=' <expresion> ';'

<impresion> ::= 'imprimir' '(' <expresion> ')' ';'

<expresion> ::= <operacion> | <valor>

<operacion> ::= <valor> '+' <valor>

<valor> ::= TK_id | TK_entero
```