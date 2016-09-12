/* Generated By:JJTree&JavaCC: Do not edit this line. TexParserConstants.java */
package au.edu.uq.smartass.templates.texparser;

public interface TexParserConstants {

  int EOF = 0;
  int SPACE = 3;
  int BREAK = 4;
  int NUMBER = 5;
  int ALPHA = 6;
  int FILEDELIMITER = 7;
  int ANYCHAR = 8;
  int NAME = 9;
  int INT = 10;
  int FILENAME = 11;
  int BEGINDOC = 12;
  int ENDDOC = 13;
  int BEGINREPEAT = 14;
  int ENDREPEAT = 15;
  int BEGINMULTI = 16;
  int MULTICHOICE = 17;
  int ENDMULTI = 18;
  int ENDSECTION = 19;
  int BEGINSCRIPT = 20;
  int ENDSCRIPT = 21;

  int DEFAULT = 0;
  int SCRIPT = 1;

  String[] tokenImage = {
    "<EOF>",
    "\"%%CALL\"",
    "\"%%BEGIN\"",
    "<SPACE>",
    "<BREAK>",
    "<NUMBER>",
    "<ALPHA>",
    "\"/\"",
    "<ANYCHAR>",
    "<NAME>",
    "<INT>",
    "<FILENAME>",
    "\"\\\\begin{document}\"",
    "\"\\\\end{document}\"",
    "<BEGINREPEAT>",
    "<ENDREPEAT>",
    "<BEGINMULTI>",
    "<MULTICHOICE>",
    "<ENDMULTI>",
    "<ENDSECTION>",
    "\"#<\"",
    "\"#>\"",
    "<token of kind 22>",
  };

}
