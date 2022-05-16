// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 3, 2015

package com.eagle;

import com.eagle.core.EagleLanguage;
import com.eagle.core.LanguageLookup;
import com.eagle.programmar.AWK.AWK_Program;
import com.eagle.programmar.BNF.BNF_Program;
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
import com.eagle.programmar.Gupta.Gupta_Program;
import com.eagle.programmar.HTML.HTML_Program;
import com.eagle.programmar.IBMASM.IBMASM_Program;
import com.eagle.programmar.IntelASM.IntelASM_Program;
import com.eagle.programmar.JSON.JSON_Program;
import com.eagle.programmar.Java.Java_Program;
import com.eagle.programmar.JavaP.JavaP_Program;
import com.eagle.programmar.Javascript.Javascript_Program;
import com.eagle.programmar.Lisp.Lisp_Program;
import com.eagle.programmar.Natural.Natural_Program;
import com.eagle.programmar.ObjectiveC.ObjectiveC_Program;
import com.eagle.programmar.PHP.PHP_Program;
import com.eagle.programmar.PLI.PLI_Program;
import com.eagle.programmar.PPSM.PPSM_Program;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Property.Property_Program;
import com.eagle.programmar.Python.Python2_Program;
import com.eagle.programmar.Python.Python3_Program;
import com.eagle.programmar.RPG.RPG_Program.RPG_III_Program;
import com.eagle.programmar.RPG.RPG_Program.RPG_IV_Program;
import com.eagle.programmar.SQL.SQL_Program;
import com.eagle.programmar.VB.VB_Program;
import com.eagle.programmar.XML.XML_Program;

public class EagleLanguageLookup extends LanguageLookup
{
	public void add(String name, Class<? extends EagleLanguage> lang, String... suffixes)
	{
		addLanguage(name, lang);
		for (String suffix : suffixes)
		{
			setLanguageSuffix(suffix, name);
		}
	}
	
	public EagleLanguageLookup()
	{
		// The third, etc arguments are suffixes for this language
		add(AWK_Program.AWK, AWK_Program.class, ".awk");
		// add(Bash_Program.NAME, Bash_Program.class, ".sh");
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
		add(Delphi_Program.DELPHI, Delphi_Program.class, ".p", ".pas");
		add(Django_Program.DJANGO, Django_Program.class);
		add(Gupta_Program.GUPTA, Gupta_Program.class);
		add(HTML_Program.HTML, HTML_Program.class, ".htm", ".html");
		add(IBMASM_Program.IBMASM, IBMASM_Program.class);
		add(IntelASM_Program.INTELASM, IntelASM_Program.class);
		add(Java_Program.JAVA, Java_Program.class, ".java");
		add(JavaP_Program.JAVAP, JavaP_Program.class, ".javap");
		add(Javascript_Program.JAVASCRIPT, Javascript_Program.class, ".js");
		add(JSON_Program.JSON, JSON_Program.class, ".json");
		add(Lisp_Program.LISP, Lisp_Program.class, ".lisp");
		add(Natural_Program.NATURAL, Natural_Program.class, ".ntf");
		add(ObjectiveC_Program.OBJECTIVEC, ObjectiveC_Program.class);
		add(Perl_Program.PERL, Perl_Program.class);
		add(PHP_Program.PHP, PHP_Program.class, ".php");
		add(PLI_Program.PLI, PLI_Program.class, ".pli");
		// add(Powershell_Program.POWERSHELL, Powershell_Program.class, ".ps1");
		add(PPSM_Program.PPSM, PPSM_Program.class, ".ppsm");
		add(Property_Program.PROPERTY, Property_Program.class, ".properties");
		add(Python2_Program.PYTHON2, Python2_Program.class);
		add(Python3_Program.PYTHON3, Python3_Program.class, ".py");
		add(RPG_III_Program.RPGIII, RPG_III_Program.class, ".rpg");
		add(RPG_IV_Program.RPGIV, RPG_IV_Program.class);
		// add(Rust_Program.RUST, Rust_Program.class, ".rs");
		add(SQL_Program.SQL, SQL_Program.class, ".sql");
		// add(TCL_Program.TCL, TCL_Program.class, ".tcl");
		add(VB_Program.VB, VB_Program.class, ".vb");
		add(XML_Program.XML, XML_Program.class, ".xml", ".xsd");
	}
}
