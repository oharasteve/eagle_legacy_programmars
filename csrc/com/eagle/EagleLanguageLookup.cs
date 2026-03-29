// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 3, 2015

namespace com.eagle
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using LanguageLookup = com.eagle.core.LanguageLookup;
	using AWK_Program = com.eagle.programmar.AWK.AWK_Program;
	using Ada_Program = com.eagle.programmar.Ada.Ada_Program;
	using Algol68_Program = com.eagle.programmar.Algol68.Algol68_Program;
	using BNF_Program = com.eagle.programmar.BNF.BNF_Program;
	using Bash_Program = com.eagle.programmar.Bash.Bash_Program;
	using Basic_Program = com.eagle.programmar.Basic.Basic_Program;
	using C_Program = com.eagle.programmar.C.C_Program;
	using CMD_Program = com.eagle.programmar.CMD.CMD_Program;
	using CMacro_Program = com.eagle.programmar.CMacro.CMacro_Program;
	using COBOL_Partial_Fixed_Format = com.eagle.programmar.COBOL.COBOL_Partial_Fixed_Format;
	using COBOL_Program_Fixed_Format = com.eagle.programmar.COBOL.COBOL_Program_Fixed_Format;
	using COBOL_Program_Free_Format = com.eagle.programmar.COBOL.COBOL_Program_Free_Format;
	using CPlus_Program = com.eagle.programmar.CPlus.CPlus_Program;
	using CSS_Program = com.eagle.programmar.CSS.CSS_Program;
	using CSharp_Program = com.eagle.programmar.CSharp.CSharp_Program;
	using Delphi_Configuration = com.eagle.programmar.Delphi.Delphi_Configuration;
	using Delphi_Program = com.eagle.programmar.Delphi.Delphi_Program;
	using Django_Program = com.eagle.programmar.Django.Django_Program;
	using Eaglish_Program = com.eagle.programmar.Eaglish.Eaglish_Program;
	using FSharp_Program = com.eagle.programmar.FSharp.FSharp_Program;
	using Fortran_Program = com.eagle.programmar.Fortran.Fortran_Program;
	using Go_Program = com.eagle.programmar.Go.Go_Program;
	using Gupta_Program = com.eagle.programmar.Gupta.Gupta_Program;
	using HTML_Program = com.eagle.programmar.HTML.HTML_Program;
	using IBMASM_Program = com.eagle.programmar.IBMASM.IBMASM_Program;
	using IntelASM_Program = com.eagle.programmar.IntelASM.IntelASM_Program;
	using JSON_Program = com.eagle.programmar.JSON.JSON_Program;
	using Java_Program = com.eagle.programmar.Java.Java_Program;
	using JavaP_Program = com.eagle.programmar.JavaP.JavaP_Program;
	using Javascript_Program = com.eagle.programmar.Javascript.Javascript_Program;
	using Julia_Program = com.eagle.programmar.Julia.Julia_Program;
	using Lisp_Program = com.eagle.programmar.Lisp.Lisp_Program;
	using MSSolution_Program = com.eagle.programmar.MSSolution.MSSolution_Program;
	using Natural_Program = com.eagle.programmar.Natural.Natural_Program;
	using ObjectiveC_Program = com.eagle.programmar.ObjectiveC.ObjectiveC_Program;
	using PHP_Program = com.eagle.programmar.PHP.PHP_Program;
	using PLI_Program = com.eagle.programmar.PLI.PLI_Program;
	using PPSM_Program = com.eagle.programmar.PPSM.PPSM_Program;
	using Perl_Program = com.eagle.programmar.Perl.Perl_Program;
	using Powershell_Program = com.eagle.programmar.Powershell.Powershell_Program;
	using Property_Program = com.eagle.programmar.Property.Property_Program;
	using Python2_Program = com.eagle.programmar.Python.Python2_Program;
	using Python3_Program = com.eagle.programmar.Python.Python3_Program;
	using RPG_III_Program = com.eagle.programmar.RPG.RPG_Program.RPG_III_Program;
	using RPG_IV_Program = com.eagle.programmar.RPG.RPG_Program.RPG_IV_Program;
	using RPGFree_Program = com.eagle.programmar.RPGFree.RPGFree_Program;
	using Rexx_Program = com.eagle.programmar.Rexx.Rexx_Program;
	using Ruby_Program = com.eagle.programmar.Ruby.Ruby_Program;
	using Rust_Program = com.eagle.programmar.Rust.Rust_Program;
	using SQL_Program = com.eagle.programmar.SQL.SQL_Program;
	using Scala_Program = com.eagle.programmar.Scala.Scala_Program;
	using TCL_Program = com.eagle.programmar.TCL.TCL_Program;
	using VB_Program = com.eagle.programmar.VB.VB_Program;
	using XML_Program = com.eagle.programmar.XML.XML_Program;

	public class EagleLanguageLookup : LanguageLookup
	{
		public virtual void add(string name, Type lang, params string[] suffixes)
		{
			addLanguage(name, lang);
			foreach (string suffix in suffixes)
			{
				setLanguageSuffix(suffix, name);
			}
		}

		public EagleLanguageLookup()
		{
			// The third, etc arguments are suffixes for this language
			add(Ada_Program.ADA, typeof(Ada_Program), ".ada", ".adb");
			add(Algol68_Program.ALGOL68, typeof(Algol68_Program), ".a68");
			add(AWK_Program.AWK, typeof(AWK_Program), ".awk");
			add(Bash_Program.BASH, typeof(Bash_Program), ".bash", ".sh");
			add(Basic_Program.BASIC, typeof(Basic_Program), ".bas");
			add(BNF_Program.BNF, typeof(BNF_Program), ".bnf");
			add(C_Program.C, typeof(C_Program), ".c", ".h");
			add(CMacro_Program.CMACRO, typeof(CMacro_Program));
			add(CMD_Program.CMD, typeof(CMD_Program), ".bat");
			add(COBOL_Partial_Fixed_Format.COBOLPartial, typeof(COBOL_Partial_Fixed_Format));
			add(COBOL_Program_Fixed_Format.COBOLFixed, typeof(COBOL_Program_Fixed_Format), ".cob");
			add(COBOL_Program_Free_Format.COBOLFree, typeof(COBOL_Program_Free_Format), ".cbl");
			add(CPlus_Program.CPP, typeof(CPlus_Program), ".cc", ".cpp", ".hh");
			add(CSharp_Program.CSHARP, typeof(CSharp_Program), ".cs");
			add(CSS_Program.CSS, typeof(CSS_Program), ".css");
			add(Delphi_Configuration.DELPHIConfig, typeof(Delphi_Configuration));
			add(Delphi_Program.DELPHI, typeof(Delphi_Program), ".dpr", ".p", ".pas");
			add(Django_Program.DJANGO, typeof(Django_Program));
			add(Eaglish_Program.EAGLISH, typeof(Eaglish_Program), ".eaglish");
			add(Fortran_Program.FORTRAN, typeof(Fortran_Program), ".for");
			add(FSharp_Program.FSHARP, typeof(FSharp_Program), ".fs");
			add(Go_Program.GO, typeof(Go_Program), ".go");
			add(Gupta_Program.GUPTA, typeof(Gupta_Program));
			add(HTML_Program.HTML, typeof(HTML_Program), ".htm", ".html");
			add(IBMASM_Program.IBMASM, typeof(IBMASM_Program));
			add(IntelASM_Program.INTELASM, typeof(IntelASM_Program), ".asm");
			add(Java_Program.JAVA, typeof(Java_Program), ".java");
			add(JavaP_Program.JAVAP, typeof(JavaP_Program), ".javap");
			add(Javascript_Program.JAVASCRIPT, typeof(Javascript_Program), ".js");
			add(JSON_Program.JSON, typeof(JSON_Program), ".json");
			add(Julia_Program.JULIA, typeof(Julia_Program), ".jl", ".julia");
			add(Lisp_Program.LISP, typeof(Lisp_Program), ".lisp");
			add(MSSolution_Program.MSSOLUTION, typeof(MSSolution_Program), ".sln");
			add(Natural_Program.NATURAL, typeof(Natural_Program), ".ntf");
			add(ObjectiveC_Program.OBJECTIVEC, typeof(ObjectiveC_Program));
			add(Perl_Program.PERL, typeof(Perl_Program), ".perl", ".pl");
			add(PHP_Program.PHP, typeof(PHP_Program), ".php");
			add(PLI_Program.PLI, typeof(PLI_Program), ".pli");
			add(Powershell_Program.POWERHSELL, typeof(Powershell_Program), ".ps1");
			add(PPSM_Program.PPSM, typeof(PPSM_Program), ".ppsm");
			add(Property_Program.PROPERTY, typeof(Property_Program), ".properties");
			add(Python2_Program.PYTHON2, typeof(Python2_Program));
			add(Python3_Program.PYTHON3, typeof(Python3_Program), ".py");
			add(Rexx_Program.REXX, typeof(Rexx_Program), ".rexx");
			add(RPG_III_Program.RPGIII, typeof(RPG_III_Program), ".rpg");
			add(RPG_IV_Program.RPGIV, typeof(RPG_IV_Program));
			add(RPGFree_Program.RPGFree, typeof(RPGFree_Program));
			add(Ruby_Program.RUBY, typeof(Ruby_Program), ".ruby");
			add(Rust_Program.RUST, typeof(Rust_Program), ".rs", ".rust");
			add(Scala_Program.SCALA, typeof(Scala_Program), ".scala");
			add(SQL_Program.SQL, typeof(SQL_Program), ".sql");
			add(TCL_Program.TCL, typeof(TCL_Program), ".tcl");
			add(VB_Program.VB, typeof(VB_Program), ".vb", ".vba", ".vbs");
			add(XML_Program.XML, typeof(XML_Program), ".xml", ".xsd");
		}
	}

}
