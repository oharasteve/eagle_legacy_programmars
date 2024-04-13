// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 25, 2013

package com.eagle.programmar.COBOL;

import java.util.HashMap;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.ArrayValue;
import com.eagle.math.EagleValue;
import com.eagle.math.IntegerValue;
import com.eagle.math.StringValue;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_DataClause;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_DataSection;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_WorkingStorageSection;
import com.eagle.programmar.COBOL.COBOL_Picture_Value.COBOL_Picture_Value_Literal;
import com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
import com.eagle.programmar.COBOL.Picture.COBOL_RedefinesClause;
import com.eagle.programmar.COBOL.Picture.COBOL_ValueClause;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public abstract class COBOL_Program_Complete extends COBOL_Program implements EagleRunnable
{
	public COBOL_Program_Complete(String name, COBOL_Syntax syntax)
	{
		super(name, syntax);
	}
	
	// Components of a complete COBOL Program
	public @S(10) @OPT TokenList<COBOL_Comment> comments1;
	public @S(20) @OPT TokenList<COBOL_Directive> directives;
	public @S(30) @OPT TokenList<COBOL_Comment> comments2;
	public @S(40) @OPT COBOL_SpecialNames specialNames;
	public @S(50) @OPT COBOL_IdentificationDivision identificationDiv;
	public @S(60) @OPT COBOL_EnvironmentDivision environmentDiv;
	public @S(70) @OPT TokenList<COBOL_Comment> comments3;
	public @S(80) @OPT COBOL_DataDivision dataDiv;
	public @S(90) COBOL_ProcedureDivision procedureDiv;
	
	public @S(100) @OPT TokenList<COBOL_Program_Free_Format> nestedPrograms;

	public @S(110) @OPT COBOL_EndProgram endProgram;
	
	public static class COBOL_EndProgram extends TokenSequence
	{
		public @S(10) COBOL_Keyword END = new COBOL_Keyword("END");
		public @S(20) COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
		public @S(30) COBOL_Identifier_Reference programId;
		public @S(40) PunctuationPeriod dot;
	}
	
	@Override
	public void interpret(EagleInterpreter interp)
	{
		COBOL_Interpreter interpreter = (COBOL_Interpreter) interp;
		
		// Pass 1 : Collect all the variables in Working Storage
		collectDataVariables(interpreter);
		
		// Pass 2 : Collect all the paragraph names
		collectParagraphNames(interpreter);
		
		// Pass 3 -- now run it
		interpreter.tryToInterpret(procedureDiv);
	}
	
	private void collectDataVariables(COBOL_Interpreter interpreter)
	{
		for (COBOL_DataSection section : dataDiv.sections._elements)
		{
			AbstractToken which = section.getWhich();
			if (which instanceof COBOL_WorkingStorageSection)
			{
				COBOL_WorkingStorageSection workingStorage = (COBOL_WorkingStorageSection) which;
				for (COBOL_CopyOrDataDeclaration decl : workingStorage.dataDeclarations._elements)
				{
					which = decl.getWhich();
					if (which instanceof COBOL_DataDeclaration)
					{
						// String piece = interpreter._parser.extractToken(which);
						// System.err.println("*** Token = " + piece);
						COBOL_DataDeclaration dataDeclaration = (COBOL_DataDeclaration) which;
						// COBOL_DataDeclaration tempDecl = new COBOL_DataDeclaration();
						// boolean ok = interpreter._parser.parseLine("        77 X PIC 9(5) COMP.", null, tempDecl);
						// System.err.println("Parse result = " + ok);
						
						which = dataDeclaration.fieldName.getWhich();
						if (which instanceof COBOL_Data_Definition)
						{
							COBOL_Data_Definition dataDef = (COBOL_Data_Definition) which;
							String varName = dataDef.getValue();
							String pic = null;
							String redefines = null;
							for (COBOL_DataClause clause : dataDeclaration.clauses._elements)
							{
								which = clause.getWhich();
								if (which instanceof COBOL_PictureClause)
								{
									COBOL_PictureClause picClause = (COBOL_PictureClause) which;
									pic = picClause.picture.getValue().toUpperCase();
								}
								if (which instanceof COBOL_RedefinesClause)
								{
									COBOL_RedefinesClause redefinesClause = (COBOL_RedefinesClause) which;
									redefines = redefinesClause.id.getValue();
								}
							}
							
							// Check for REDEFINES first
							if (redefines != null)
							{
								EagleValue value = interpreter._symbolTable.findSymbol(redefines);
								if (value != null)
								{
									// Change the name of the symbol
									interpreter._symbolTable.removeSymbols(redefines);
									interpreter._symbolTable.setSymbol(varName, value);
								}
							}
							
							// Noww check for PICTURE
							else if (pic == null)
							{
								ArrayValue array = collectArrayValues(dataDeclaration);
								interpreter._symbolTable.setSymbol(varName, array);
							}
							else if (pic.startsWith("X"))
							{
								interpreter._symbolTable.setSymbol(varName, new StringValue(""));
							}
							else if (pic.startsWith("Z") || pic.startsWith("9"))
							{
								interpreter._symbolTable.setSymbol(varName, new IntegerValue(0));
							}
							else
							{
								System.err.println("*** data " + dataDeclaration.level + " " + varName + " " + pic);
							}
						}
					}
				}
			}
		}
	}
	
	private static ArrayValue collectArrayValues(COBOL_DataDeclaration dataDeclaration)
	{
		// Look at all the children
		ArrayValue array = new ArrayValue();
		for (COBOL_CopyOrDataDeclaration child : dataDeclaration.children._elements)
		{
			AbstractToken which = child.getWhich();
			if (which instanceof COBOL_DataDeclaration)
			{
				COBOL_DataDeclaration dataDeclaration2 = (COBOL_DataDeclaration) which;
				for (COBOL_DataClause clause2 : dataDeclaration2.clauses._elements)
				{
					AbstractToken whichValue = clause2.getWhich();
					if (whichValue instanceof COBOL_ValueClause)
					{
						COBOL_ValueClause valueClause = (COBOL_ValueClause) whichValue;
						COBOL_Picture_Value picValue = valueClause.values.first();
						if (picValue.getWhich() instanceof COBOL_Picture_Value_Literal)
						{
							COBOL_Picture_Value_Literal lit = (COBOL_Picture_Value_Literal) picValue.getWhich();
							StringValue str = new StringValue(lit.literal.getValue());
							// System.err.println("************** Adding " + str.toString());
							array.addValue(str);
						}
						break;
					}
				}
			}
		}
		return array;
	}
	
	private void collectParagraphNames(COBOL_Interpreter interpreter)
	{
		interpreter._paragraphs = new HashMap<String,COBOL_Paragraph>();
		for (COBOL_Section section : procedureDiv.sections._elements)
		{
			for (COBOL_Paragraph paragraph : section.paragraphs._elements)
			{
				if (paragraph.paragraphHeaders._elements.size() > 0)
				{
					String paragraphName = paragraph.paragraphHeaders._elements.get(0).paragraphName.getValue();
					interpreter._paragraphs.put(paragraphName, paragraph);
					if (interpreter._TRACE) System.err.println("*** Found paragraph " + paragraphName);
				}
			}
		}
	}
}
