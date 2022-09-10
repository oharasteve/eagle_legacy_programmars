// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.CPlus;

import com.eagle.parsers.EagleOverrideManager;
import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.C.C_Syntax;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.programmar.CPlus.Terminals.CPlus_Literal;
import com.eagle.programmar.ObjectiveC.ObjectiveC_Class;
import com.eagle.programmar.ObjectiveC.ObjectiveC_Interface;
import com.eagle.programmar.ObjectiveC.ObjectiveC_Protocol;
import com.eagle.programmar.ObjectiveC.ObjectiveC_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class CPlus_Program extends C_Program
{
	public static final String CPP = "Cpp";
//	private static C_Expression _fakeExpr = null;
	
	public CPlus_Program()
	{
		super(CPP, new CPlus_Syntax());
		
//		// Can't easily have CPlus_Expression extend C_Expression. It's that old robot leg problem.
//		if (_fakeExpr == null)
//		{
//			_fakeExpr = new C_Expression();		// Prime the _operators._list structure
//			_fakeExpr.addOperator(CPlus_Expression.CPlus_NewExpression.class);
//			fake.addOperator(CPlus_Expression.CPlus_NamespaceGlobal.clas);
//			fake.addOperator(CPlus_Expression.CPlus_NamespaceSub.class);
//		}
		
		C_Program.addPrimitive("BOOL");		// Might be ObjectiveC only
		
		TokenChooser.addChoice(C_StatementOrComment.class, CPlus_Extern.class);
		TokenChooser.addChoice(C_StatementOrComment.class, CPlus_Constructor.class);
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	@Override
	public void findLanguageOverrides(EagleOverrideManager overrider)
	{
		// overrider.override(C_Expression.class, CPlus_Expression.class); // Times out at 60 seconds. Why?
		overrider.override(C_Literal.class, CPlus_Literal.class);
	}
	
	// Step is 9 to avoid duplicate @S(10) in C_Program
	public @S(9) @OPT TokenList<CPlus_Element> items;

	public static class CPlus_Element extends TokenChooser
	{
		public @FIRST @SYNTAX(C_Syntax.class) C_Comment comment;
		public @CHOICE CPlus_Namespace namespace;
		public @CHOICE CPlus_Class classDefinition;
		public @CHOICE CPlus_Using using;
		public @CHOICE CPlus_Method method;
		public @CHOICE CPlus_Extern extern;
		public @CHOICE @SYNTAX(ObjectiveC_Syntax.class) ObjectiveC_Class objCclass;
		public @CHOICE @SYNTAX(ObjectiveC_Syntax.class) ObjectiveC_Interface objCinterface;
		public @CHOICE @SYNTAX(ObjectiveC_Syntax.class) ObjectiveC_Protocol objCprotocol;
		public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro;
		public @LAST @SYNTAX(C_Syntax.class) C_StatementOrComment statementOrComment;
	}
}
