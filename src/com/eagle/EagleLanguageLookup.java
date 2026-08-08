// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 3, 2015

package com.eagle;

import com.eagle.core.AbstractLanguage;
import com.eagle.core.LanguageLookup;
import com.eagle.programmar.AWK.AWK_Program;
import com.eagle.programmar.Ada.Ada_Program;
import com.eagle.programmar.Algol68.Algol68_Program;
import com.eagle.programmar.BNF.BNF_Program;
import com.eagle.programmar.Bash.Bash_Program;
import com.eagle.programmar.Basic.Basic_Program;
import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.CMD.CMD_Program;
import com.eagle.programmar.CMacro.CMacro_Program;
import com.eagle.programmar.COBOL.COBOL_Partial_Fixed_Format;
import com.eagle.programmar.COBOL.COBOL_Program_Fixed_Format;
import com.eagle.programmar.COBOL.COBOL_Program_Free_Format;
import com.eagle.programmar.CPlus.CPlus_Program;
import com.eagle.programmar.CSS.CSS_Program;
import com.eagle.programmar.CSharp.CSharp_Program;
import com.eagle.programmar.Delphi.Delphi_Configuration;
import com.eagle.programmar.Delphi.Delphi_Program;
import com.eagle.programmar.Django.Django_Program;
import com.eagle.programmar.Eaglish.Eaglish_Program;
import com.eagle.programmar.FSharp.FSharp_Program;
import com.eagle.programmar.Fortran.Fortran_Program;
import com.eagle.programmar.Go.Go_Program;
import com.eagle.programmar.Gupta.Gupta_Program;
import com.eagle.programmar.HTML.HTML_Program;
import com.eagle.programmar.Haskell.Haskell_Program;
import com.eagle.programmar.IBMASM.IBMASM_Program;
import com.eagle.programmar.IntelASM.IntelASM_Program;
import com.eagle.programmar.JSON.JSON_Program;
import com.eagle.programmar.Java.Java_Program;
import com.eagle.programmar.JavaP.JavaP_Program;
import com.eagle.programmar.Javascript.Javascript_Program;
import com.eagle.programmar.Julia.Julia_Program;
import com.eagle.programmar.Lisp.Lisp_Program;
import com.eagle.programmar.MSSolution.MSSolution_Program;
import com.eagle.programmar.Natural.Natural_Program;
import com.eagle.programmar.ObjectiveC.ObjectiveC_Program;
import com.eagle.programmar.PHP.PHP_Program;
import com.eagle.programmar.PLI.PLI_Program;
import com.eagle.programmar.PPSM.PPSM_Program;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Powershell.Powershell_Program;
import com.eagle.programmar.Property.Property_Program;
import com.eagle.programmar.Python.Python2_Program;
import com.eagle.programmar.Python.Python3_Program;
import com.eagle.programmar.Python.Python_Program;
import com.eagle.programmar.RPG.RPG_Program.RPG_III_Program;
import com.eagle.programmar.RPG.RPG_Program.RPG_IV_Program;
import com.eagle.programmar.RPGFree.RPGFree_Program;
import com.eagle.programmar.Rexx.Rexx_Program;
import com.eagle.programmar.Ruby.Ruby_Program;
import com.eagle.programmar.Rust.Rust_Program;
import com.eagle.programmar.SQL.SQL_Program;
import com.eagle.programmar.SQLite.SQLite_Program;
import com.eagle.programmar.Scala.Scala_Program;
import com.eagle.programmar.TCL.TCL_Program;
import com.eagle.programmar.VB.VB_Program;
import com.eagle.programmar.XML.XML_Program;

public class EagleLanguageLookup extends LanguageLookup
{
	public void add(String name, Class<? extends AbstractLanguage> lang, String... suffixes)
	{
		super.addLanguage(name, lang);
		for (String suffix : suffixes)
		{
			super.setLanguageSuffix(suffix, name);
		}
	}

	public EagleLanguageLookup()
	{
		// The third, etc arguments are suffixes for this language
		add(Ada_Program.ADA, Ada_Program.class, ".ada", ".adb");
		add(Algol68_Program.ALGOL68, Algol68_Program.class, ".a68");
		add(AWK_Program.AWK, AWK_Program.class, ".awk");
		add(Bash_Program.BASH, Bash_Program.class, ".bash", ".sh");
		add(Basic_Program.BASIC, Basic_Program.class, ".bas");
		add(BNF_Program.BNF, BNF_Program.class, ".bnf");
		add(C_Program.C, C_Program.class, ".c", ".h");
		add(CMacro_Program.CMACRO, CMacro_Program.class);
		add(CMD_Program.CMD, CMD_Program.class, ".bat");
		add(COBOL_Partial_Fixed_Format.COBOLPartial, COBOL_Partial_Fixed_Format.class);
		add(COBOL_Program_Fixed_Format.COBOLFixed, COBOL_Program_Fixed_Format.class, ".cob");
		add(COBOL_Program_Free_Format.COBOLFree, COBOL_Program_Free_Format.class, ".cbl");
		add(CPlus_Program.CPP, CPlus_Program.class, ".cc", ".cpp", ".hh");
		add(CSharp_Program.CSHARP, CSharp_Program.class, ".cs");
		add(CSS_Program.CSS, CSS_Program.class, ".css");
		add(Delphi_Configuration.DELPHIConfig, Delphi_Configuration.class);
		add(Delphi_Program.DELPHI, Delphi_Program.class, ".dpr", ".p", ".pas");
		add(Django_Program.DJANGO, Django_Program.class);
		add(Eaglish_Program.EAGLISH, Eaglish_Program.class, ".eaglish");
		add(Fortran_Program.FORTRAN, Fortran_Program.class, ".for");
		add(FSharp_Program.FSHARP, FSharp_Program.class, ".fs");
		add(Go_Program.GO, Go_Program.class, ".go");
		add(Gupta_Program.GUPTA, Gupta_Program.class);
		add(Haskell_Program.HASKELL, Haskell_Program.class, ".hs");
		add(HTML_Program.HTML, HTML_Program.class, ".htm", ".html");
		add(IBMASM_Program.IBMASM, IBMASM_Program.class);
		add(IntelASM_Program.INTELASM, IntelASM_Program.class, ".asm");
		add(Java_Program.JAVA, Java_Program.class, ".java");
		add(JavaP_Program.JAVAP, JavaP_Program.class, ".javap");
		add(Javascript_Program.JAVASCRIPT, Javascript_Program.class, ".js");
		add(JSON_Program.JSON, JSON_Program.class, ".json");
		add(Julia_Program.JULIA, Julia_Program.class, ".jl", ".julia");
		add(Lisp_Program.LISP, Lisp_Program.class, ".lisp");
		add(MSSolution_Program.MSSOLUTION, MSSolution_Program.class, ".sln");
		add(Natural_Program.NATURAL, Natural_Program.class, ".ntf");
		add(ObjectiveC_Program.OBJECTIVEC, ObjectiveC_Program.class);
		add(Perl_Program.PERL, Perl_Program.class, ".perl", ".pl");
		add(PHP_Program.PHP, PHP_Program.class, ".php");
		add(PLI_Program.PLI, PLI_Program.class, ".pli");
		add(Powershell_Program.POWERHSELL, Powershell_Program.class, ".ps1");
		add(PPSM_Program.PPSM, PPSM_Program.class, ".ppsm");
		add(Property_Program.PROPERTY, Property_Program.class, ".properties");
		add(Python2_Program.PYTHON2, Python2_Program.class);
		add(Python3_Program.PYTHON3, Python3_Program.class, ".py");
		add(Python_Program.PYTHON, Python3_Program.class, ".py");
		add(Rexx_Program.REXX, Rexx_Program.class, ".rexx");
		add(RPG_III_Program.RPGIII, RPG_III_Program.class, ".rpg");
		add(RPG_IV_Program.RPGIV, RPG_IV_Program.class);
		add(RPGFree_Program.RPGFree, RPGFree_Program.class);
		add(Ruby_Program.RUBY, Ruby_Program.class, ".ruby");
		add(Rust_Program.RUST, Rust_Program.class, ".rs", ".rust");
		add(Scala_Program.SCALA, Scala_Program.class, ".scala");
		add(SQL_Program.SQL, SQL_Program.class, ".sql");
		add(SQLite_Program.SQLITE, SQLite_Program.class);
		add(TCL_Program.TCL, TCL_Program.class, ".tcl");
		add(VB_Program.VB, VB_Program.class, ".vb", ".vba", ".vbs");
		add(XML_Program.XML, XML_Program.class, ".xml", ".xsd");
	}
}
