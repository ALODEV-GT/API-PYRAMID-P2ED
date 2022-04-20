package midik.jsonDelete;
import java_cup.runtime.Symbol;

%%
%class lexerJsonDelete
%type Symbol
%unicode
%public
%cup
%line
%column

%{
    private boolean errores = false;

    public boolean isErrores(){
        return errores;
    }

    private Symbol symbol(int type) {
        return new Symbol(type, yycolumn+1, yyline+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yycolumn+1, yyline+1, value);
    }
%}

//Symbolos
LLAVE_A = "{"
LLAVE_C = "}"
COMILLAS = "\""
DOS_PUNTOS = ":"
COMA=","
NUMERO = [0-9]
ENTERO = {NUMERO}{NUMERO}*
CORAZON = "♥"
TREBOL ="♣"
DIAMANTE = "♦"
PICA = "♠"
DELETE_1 = "delete_1"
DELETE_2 = "delete_2"
FIN_LINEA = \r|\n|\r\n|\u2028|\u2029|\u000B|\u000C|\u0085
ESPACIO = " " | "\t"

%%
<YYINITIAL> {LLAVE_A}       {return symbol(sym.LLAVE_A, yytext());}
<YYINITIAL> {LLAVE_C}       {return symbol(sym.LLAVE_C, yytext());}
<YYINITIAL> {COMILLAS}      {return symbol(sym.COMILLAS, yytext());}
<YYINITIAL> {DOS_PUNTOS}    {return symbol(sym.DOS_PUNTOS, yytext());}
<YYINITIAL> {COMA}          {return symbol(sym.COMA, yytext());}
<YYINITIAL> {ENTERO}        {return symbol(sym.ENTERO, yytext());}
<YYINITIAL> {CORAZON}       {return symbol(sym.CORAZON, yytext());}
<YYINITIAL> {TREBOL}        {return symbol(sym.TREBOL, yytext());}
<YYINITIAL> {DIAMANTE}      {return symbol(sym.DIAMANTE, yytext());}
<YYINITIAL> {PICA}          {return symbol(sym.PICA, yytext());}
<YYINITIAL> {DELETE_1}      {return symbol(sym.DELETE_1, yytext());}
<YYINITIAL> {DELETE_2}      {return symbol(sym.DELETE_2, yytext());}
<YYINITIAL> {FIN_LINEA}     {}
<YYINITIAL> {ESPACIO}       {}

<<EOF>>                     {return symbol(sym.EOF, "FIN");}
<YYINITIAL> .               {System.out.println("El simbolo " + yytext() + " no existe en el lenguaje." + " Linea:" + (yyline+1) + " Columna:" + yycolumn + "\n"); errores = true;}

