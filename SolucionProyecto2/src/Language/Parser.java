package Language;

import java.util.ArrayList;
import java.util.Arrays;

import Classes.Abstracts.Component;
import Classes.Utils.Error;
import Classes.Utils.Outs;
import Classes.Utils.PathType;
import Classes.Utils.TypeError;
import Classes.Utils.ObjectType;
import Classes.Utils.PlaceType;
import Classes.World.Connect;
import Classes.World.Object;
import Classes.World.ObjectPlace;
import Classes.World.Place;
import Classes.World.World;

public class Parser {
    private Scanner sc;
    private Token token;

    public Parser(Scanner sc) {
        this.sc = sc;
    }

    public ArrayList<World> parser() {
        return START();
    }

    private ArrayList<World> START() {
        // <START> ::= <WORLD> EOF
        ArrayList<World> worlds = WORLDS();

        while((token = sc.next_token()).type != TOK.EOF) {
            Outs.errors.add(new Error(token.line, token.column, TypeError.SYNTAX, "No se esperaba «" + (token.lexeme != null ? token.lexeme : "EOF") + "»"));
        }

        return worlds;
    }

    private ArrayList<World> WORLDS() {
        World world = WORLD();

        if(match(TOK.EOF)) {
            return new ArrayList<>() {{add(world);}};
        }

        if(match(TOK.TK_comma)) {
            consume(TOK.TK_comma);
            ArrayList<World> worlds = WORLDS();
            return new ArrayList<>() {{add(world); addAll(worlds);}};
        }

        while(!Arrays.asList(
            TOK.TK_comma, TOK.EOF
        ).contains((token = sc.next_token()).type)) {
            Outs.errors.add(new Error(token.line, token.column, TypeError.SYNTAX, "No se esperaba «" + (token.lexeme != null ? token.lexeme : "EOF") + "»"));
        }
        Outs.errors.add(new Error(token.line, token.column, TypeError.SYNTAX, "No se esperaba «" + (token.lexeme != null ? token.lexeme : "EOF") + "»"));

        if(match(TOK.KW_world)) {
            ArrayList<World> worlds = WORLDS();
            return new ArrayList<>() {{add(world); addAll(worlds);}};
        }

        return new ArrayList<>() {{add(world);}};
    }

    private World WORLD() {
        // <WORLD> ::= 'world' TK_string '{' <COMPONENTS> '}'
        consume(TOK.KW_world);
        Token name = consume(TOK.TK_string);
        consume(TOK.TK_lBrc);
        ArrayList<Component> cmps = COMPONENTS();
        consume(TOK.TK_rBrc);
        return new World(name.lexeme, cmps);
    }

    private ArrayList<Component> COMPONENTS() {
        // <COMPONENTS> ::= <COMPONENT> <COMPONENTS> | <COMPONENT>
        Component cmp = COMPONENT();

        if(match(TOK.TK_rBrc)) {
            return new ArrayList<>() {{add(cmp);}};
        }

        if(match(TOK.KW_place, TOK.KW_connect, TOK.KW_object)) {
            ArrayList<Component> cmps = COMPONENTS();
            return new ArrayList<>() {{add(cmp); addAll(cmps);}};
        }

        while(!Arrays.asList(
            TOK.TK_rPar, TOK.KW_camino, TOK.KW_puente, TOK.KW_sendero, TOK.KW_carretera,
            TOK.KW_nado, TOK.KW_lancha, TOK.KW_teleferico, TOK.TK_id, TOK.EOF
        ).contains((token = sc.next_token()).type)) {
            Outs.errors.add(new Error(token.line, token.column, TypeError.SYNTAX, "No se esperaba «" + (token.lexeme != null ? token.lexeme : "EOF") + "»"));
        }
        Outs.errors.add(new Error(token.line, token.column, TypeError.SYNTAX, "No se esperaba «" + (token.lexeme != null ? token.lexeme : "EOF") + "»"));

        if(match(TOK.KW_place, TOK.KW_connect, TOK.KW_object)) {
            ArrayList<Component> cmps = COMPONENTS();
            return new ArrayList<>() {{add(cmp); addAll(cmps);}};
        }

        return new ArrayList<>() {{add(cmp);}};
    }

    private Component COMPONENT() {
        // <COMPONENT> ::= <PLACES> | <CONNECTS> | <OBJECTS>
        if(match(TOK.KW_place)) {
            return PLACE();
        }
        if(match(TOK.KW_connect)) {
            return CONNECT();
        }
        if(match(TOK.KW_object)) {
            return OBJECT();
        }
        return null;
    }

    private Place PLACE() {
        // <PLACE> ::= 'place' TK_id ':' <PLACETYPE> 'at' '(' TK_int ',' TK_int ')'
        consume(TOK.KW_place);
        Token name = consume(TOK.TK_id);
        consume(TOK.TK_colon);
        PlaceType type = PLACETYPE();
        consume(TOK.KW_at);
        consume(TOK.TK_lPar);
        Token x = consume(TOK.TK_int);
        consume(TOK.TK_comma);
        Token y = consume(TOK.TK_int);
        consume(TOK.TK_rPar);
        return new Place(name.lexeme, type, Integer.parseInt(x.lexeme), Integer.parseInt(y.lexeme));
    }

    private PlaceType PLACETYPE() {
        // <PLACETYPE> ::= 'playa' | 'cueva' | 'templo' | 'jungla' | 'montana' | 'pueblo' | 'isla' | 'rio' | 'volcan' | 'pantano'
        if(match(TOK.KW_playa, TOK.KW_cueva, TOK.KW_templo, TOK.KW_jungla, TOK.KW_montana, TOK.KW_pueblo, TOK.KW_isla, TOK.KW_rio, TOK.KW_volcan, TOK.KW_pantano)) {
            Token t = consume(token.type);
            return PlaceType.get(t.type);
        }
        return null;
    }

    private Connect CONNECT() {
        // <CONNECT> ::= 'connect' TK_id 'to' TK_id 'with' <PATHTYPE>
        consume(TOK.KW_connect);
        Token placeA = consume(TOK.TK_id);
        consume(TOK.KW_to);
        Token placeB = consume(TOK.TK_id);
        consume(TOK.KW_with);
        PathType type = PATHTYPE();
        return new Connect(placeA.lexeme, placeB.lexeme, type);
    }

    private PathType PATHTYPE() {
        // <PATHTYPE> ::= '"camino"' | '"puente"' | '"sendero"' | '"carretera"' | '"nado"' | '"lancha"' | '"teleferico"'
        if(match(TOK.KW_camino, TOK.KW_puente, TOK.KW_sendero, TOK.KW_carretera, TOK.KW_nado, TOK.KW_lancha, TOK.KW_teleferico)) {
            Token t = consume(token.type);
            return PathType.get(t.type);
        }
        return null;
    }

    private Object OBJECT() {
        // <OBJECT> ::= 'object' TK_string ':' <OBJECTTYPE> 'at' <OBJECTPLACE>
        consume(TOK.KW_object);
        Token name = consume(TOK.TK_string);
        consume(TOK.TK_colon);
        ObjectType type = OBJECTTYPE();
        consume(TOK.KW_at);
        ObjectPlace place = OBJECTPLACE();
        return new Object(name.lexeme, type, place);
    }

    private ObjectType OBJECTTYPE() {
        // <OBJECTTYPE> ::= 'tesoro' | 'llave' | 'arma' | 'objeto-magico' | 'pocion' | 'trampa' | 'libro' | 'herramienta' | 'bandera' | 'gema'
        if(match(TOK.KW_tesoro, TOK.KW_llave, TOK.KW_arma, TOK.KW_objetomagico, TOK.KW_pocion, TOK.KW_trampa, TOK.KW_libro, TOK.KW_herramienta, TOK.KW_bandera, TOK.KW_gema)) {
            Token t = consume(token.type);
            return ObjectType.get(t.type);
        }
        return null;
    }

    private ObjectPlace OBJECTPLACE() {
        if(match(TOK.TK_lPar)) { // <OBJECTPLACE> ::= '(' TK_int ',' TK_int ')'
            consume(TOK.TK_lPar);
            Token x = consume(TOK.TK_int);
            consume(TOK.TK_comma);
            Token y = consume(TOK.TK_int);
            consume(TOK.TK_rPar);
            return new ObjectPlace(Integer.valueOf(x.lexeme), Integer.valueOf(y.lexeme));
        }
        if(match(TOK.TK_id)) { // <OBJECTPLACE> ::= TK_id
            Token id = consume(TOK.TK_id);
            return new ObjectPlace(id.lexeme);
        }
        return null;
    }

    private Token consume(TOK type) {
        if(match(type)) {
            return sc.next_token();
        }
        Outs.errors.add(new Error(token.line, token.column, TypeError.SYNTAX, "No se esperaba «" + (token.lexeme != null ? token.lexeme : "EOF") + "»"));
        // sc.next_token();
        return null;
    }

    private boolean match(TOK ... types) {
        token = sc.look_ahead();
        for(TOK type : types) {
            if(type == token.type) {
                return true;
            }
        }
        return false;
    }
}