## Flujo de Caracteres
```
entero a = 15 + 12;

imprimir(a);
imprimir(7);
imprimir(a + 5 + 2 + 1 + 2);
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
<INICIO> ::= <INSTRUCCIONES> EOF

<INSTRUCCIONES> ::= <INSTRUCCION> <INSTRUCCIONES> | <INSTRUCCION>

<INSTRUCCION> ::= <DECLARACION> | <IMPRESION>

<DECLARACION> ::= 'entero' TK_id '=' <EXP> ';'

<IMPRESION> ::= 'imprimir' '(' <EXP> ')' ';'

<EXP> ::= <EXP1> '+' <EXP> | <EXP1>

<EXP1> ::= <VALOR> '*' <EXP1> | <VALOR>

<VALOR> ::= TK_id | TK_entero
```

## Forma de Trabajo Scanner-Parser
### 1. Primero Scanner, Luego Parser
* Se realiza todo el análisis léxico.
* Se pasa la lista de tokens al Parser.
* Se realiza todo el análisis sintáctico.

### 2. Parser-Scanner Conjunta
* Iniciar el análisis sintáctico.
* Cada vez que se analiza un token se extrae con el Scanner.