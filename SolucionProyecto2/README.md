```html
<START> ::= <WORLDS> EOF

<WORLDS> ::=
    <WORLD> ',' <WORLDS> |
    <WORLD>

<WORLD> ::= 'world' TK_string '{' <COMPONENTS> '}'

<COMPONENTS> ::=
    <COMPONENT> <COMPONENTS> |
    <COMPONENT>

<COMPONENT> ::=
    <PLACE>   |
    <CONNECT> |
    <OBJECT>

<PLACE> ::=
    'place' TK_id ':' <PLACETYPE> 'at' '(' TK_int ',' TK_int ')'

<PLACETYPE> ::=
    'playa'   |
    'cueva'   |
    'templo'  |
    'jungla'  |
    'montana' |
    'pueblo'  |
    'isla'    |
    'rio'     |
    'volcan'  |
    'pantano'

<CONNECT> ::=
    'connect' TK_id 'to' TK_id 'with' <PATHTYPE>

<PATHTYPE> ::=
    '"camino"'     |
    '"puente"'     |
    '"sendero"'    |
    '"carretera"'  |
    '"nado"'       |
    '"lancha"'     |
    '"teleferico"'

<OBJECT> ::=
    'object' TK_string ':' <OBJECTTYPE> 'at' <OBJECTPLACE>

<OBJECTTYPE> ::=
    'tesoro'        |
    'llave'         |
    'arma'          |
    'objeto-magico' |
    'pocion'        |
    'trampa'        |
    'libro'         |
    'herramienta'   |
    'bandera'       |
    'gema'

<OBJECTPLACE> ::=
    '(' TK_int ',' TK_int ')' |
    TK_id
```