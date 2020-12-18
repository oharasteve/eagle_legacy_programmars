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
import com.eagle.programmar.PHP.PHP_Program;
import com.eagle.programmar.PLI.PLI_Program;
import com.eagle.programmar.PPSM.PPSM_Program;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Property.Property_Program;
import com.eagle.programmar.Python.Python2_Program;
import com.eagle.programmar.Python.Python3_Program;
import com.eagle.programmar.RPG.RPG_Program.RPG_III_Program;
import com.eagle.programmar.RPG.RPG_Program.RPG_IV_Program;
import com.eagle.programmar.Rust.Rust_Program;
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
		// The 3+ arguments are suffixes for this language
		add(AWK_Program.NAME, AWK_Program.class, ".awk");
		// add(Bash_Program.NAME, Bash_Program.class, ".sh");
		add(BNF_Program.NAME, BNF_Program.class, ".bnf");
		add(C_Program.NAME, C_Program.class, ".c", ".h");
		add(CMacro_Program.NAME, CMacro_Program.class);
		add(CMD_Program.NAME, CMD_Program.class, ".bat");
		add(COBOL_Partial_Fixed_Format.NAME, COBOL_Partial_Fixed_Format.class);
		add(COBOL_Program_Fixed_Format.NAME, COBOL_Program_Fixed_Format.class, ".cob");
		add(COBOL_Program_Free_Format.NAME, COBOL_Program_Free_Format.class, ".cbl");
		add(CPlus_Program.NAME, CPlus_Program.class, ".cc", ".cpp", ".hh");
		add(CSharp_Program.NAME, CSharp_Program.class, ".cs");
		add(CSS_Program.NAME, CSS_Program.class, ".css");
		add(Delphi_Configuration.NAME, Delphi_Configuration.class);
		add(Delphi_Program.NAME, Delphi_Program.class, ".p", ".pas");
		add(Django_Program.NAME, Django_Program.class);
		add(Gupta_Program.NAME, Gupta_Program.class);
		add(HTML_Program.NAME, HTML_Program.class, ".htm", ".html");
		add(IBMASM_Program.NAME, IBMASM_Program.class);
		add(IntelASM_Program.NAME, IntelASM_Program.class);
		add(Java_Program.NAME, Java_Program.class, ".java");
		add(JavaP_Program.NAME, JavaP_Program.class, ".javap");
		add(Javascript_Program.NAME, Javascript_Program.class, ".js");
		add(JSON_Program.NAME, JSON_Program.class, ".json");
		add(Lisp_Program.NAME, Lisp_Program.class, ".lisp");
		add(Natural_Program.NAME, Natural_Program.class, ".ntf");
		add(Perl_Program.NAME, Perl_Program.class);
		add(PHP_Program.NAME, PHP_Program.class, ".php");
		add(PLI_Program.NAME, PLI_Program.class, ".pli");
		// add(Powershell_Program.NAME, Powershell_Program.class, ".ps1");
		add(PPSM_Program.NAME, PPSM_Program.class, ".ppsm");
		add(Property_Program.NAME, Property_Program.class, ".properties");
		add(Python2_Program.NAME, Python2_Program.class);
		add(Python3_Program.NAME, Python3_Program.class, ".py");
		add(RPG_III_Program.NAME, RPG_III_Program.class, ".rpg");
		add(RPG_IV_Program.NAME, RPG_IV_Program.class);
		add(Rust_Program.NAME, Rust_Program.class, ".rs");
		add(SQL_Program.NAME, SQL_Program.class, ".sql");
		// add(TCL_Program.NAME, TCL_Program.class, ".tcl");
		add(VB_Program.NAME, VB_Program.class, ".vb");
		add(XML_Program.NAME, XML_Program.class, ".xml", ".xsd");
	}
}
