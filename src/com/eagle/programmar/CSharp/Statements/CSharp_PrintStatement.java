// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.generate.Statements.Eagle_Generate_Print;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_IdList;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_MoreIds;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_TypeName;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_MethodInvocation;
import com.eagle.programmar.CSharp.Expressions.CSharp_SubfieldExpression;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_PrintStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable,
				Eagle_Generate_Print<CSharp_Statement, CSharp_Expression>
{
	public @S(10) @NEWLINE @OPT CSharp_Keyword SYSTEM = new CSharp_Keyword("System");
	public @S(20) @NOSPACE @OPT PunctuationPeriod dot1;
	public @S(30) @NOSPACE CSharp_Keyword CONSOLE = new CSharp_Keyword("Console");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE @OPT CSharp_KeywordChoice OUT = new CSharp_KeywordChoice("Error", "Out");
	public @S(60) @NOSPACE @OPT PunctuationPeriod dot3;
	public @S(70) @NOSPACE CSharp_KeywordChoice WRITE = new CSharp_KeywordChoice(
			"Flush", "ReadLine", "SetOut", "Write", "WriteLine");
	public @S(80) @NOSPACE PunctuationLeftParen leftParen;
	public @S(90) @NOSPACE @OPT SeparatedList<CSharp_Expression,PunctuationComma> exprs;
	public @S(100) @NOSPACE PunctuationRightParen rightParen;
	public @S(110) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(exprs.first());
		switch (WRITE.getValue())
		{
		case "Write":
			System.out.print(val);
			return;
		case "WriteLine":
			System.out.println(val);
			return;
		}
		
		throw new RuntimeException("Unexpected keyword: " + WRITE.getValue());
	}

	@Override
	public CSharp_Statement generatePrint(ArrayList<CSharp_Expression> pieces,
			boolean newLine, AbstractToken source)
	{
		if (pieces.size() == 1)
		{
			return generatePrint1(pieces.get(0), newLine, source);
		}

		CSharp_IdList ids = new CSharp_IdList();
		ids.typeName = new CSharp_Identifier_Reference();
		ids.typeName.setValue("System");
		CSharp_Type type = new CSharp_Type();
		type.typeName = new CSharp_TypeName();
		type.typeName.setWhich(ids);
		
		ids.moreIds = new TokenList<CSharp_MoreIds>();
		CSharp_MoreIds more2 = new CSharp_MoreIds();
		CSharp_IdList ids2 = new CSharp_IdList();
		ids2.typeName = new CSharp_Identifier_Reference();
		ids2.typeName.setValue("Text");
		more2.dot = new PunctuationPeriod();
		more2.nextId = new CSharp_TypeName();
		more2.nextId.setWhich(ids2);
		ids.moreIds.addToken(more2);
		
		CSharp_MoreIds more3 = new CSharp_MoreIds();
		CSharp_IdList ids3 = new CSharp_IdList();
		ids3.typeName = new CSharp_Identifier_Reference();
		ids3.typeName.setValue("StringBuilder");
		more3.dot = new PunctuationPeriod();
		more3.nextId = new CSharp_TypeName();
		more3.nextId.setWhich(ids3);
		ids.moreIds.addToken(more3);
	
		CSharp_ClassCreationExpression creat = new CSharp_ClassCreationExpression();
		creat.generateCreation(type, null, source);
		CSharp_Expression line = CSharp_Generator.wrapExpression(creat);
		
		for (CSharp_Expression piece : pieces)
		{
			ArrayList<CSharp_Expression> args = new ArrayList<CSharp_Expression>();
			args.add(piece);
			
			CSharp_Variable app = CSharp_Variable.newVariable("Append");
			CSharp_MethodInvocation meth = new CSharp_MethodInvocation();
			meth.generateInvocation(app, args, null);
			CSharp_Expression right = CSharp_Generator.wrapExpression(meth);

			CSharp_SubfieldExpression subf = new CSharp_SubfieldExpression();
			line = subf.generateSubfield(line, right, piece);
		}
		
		// new System.Text.StringBuilder().Append("abc").Append(3).Append("def");
		return generatePrint1(line, newLine, source);
	}
	
	@Override
	public CSharp_Statement generatePrint1(CSharp_Expression line, boolean newLine,
			AbstractToken source)
	{
		CSharp_PrintStatement stmt = new CSharp_PrintStatement();
		stmt.SYSTEM.setPresent(true);
		stmt.dot1 = new PunctuationPeriod();
		stmt.dot1.setPresent(true);
		stmt.dot2 = new PunctuationPeriod();
		stmt.dot2.setPresent(true);
		stmt.OUT = new CSharp_KeywordChoice("Out");
		stmt.OUT.setPresent(true);
		stmt.dot3 = new PunctuationPeriod();
		stmt.dot3.setPresent(true);
		
		if (newLine)
		{
			stmt.WRITE = new CSharp_KeywordChoice("WriteLine");
		}
		else
		{
			stmt.WRITE = new CSharp_KeywordChoice("Write");
		}
		
		stmt.dot3 = new PunctuationPeriod();
		stmt.dot3.setPresent(true);
		stmt.leftParen = new PunctuationLeftParen();
		stmt.rightParen = new PunctuationRightParen();
		
		stmt.exprs = new SeparatedList<CSharp_Expression,PunctuationComma>();
		stmt.exprs.addPrimaryElement(line);
		stmt.semicolon = new PunctuationSemicolon();
		
		stmt.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(stmt);
	}
}
