// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

namespace com.eagle.programmar.COBOL
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleString = com.eagle.math.EagleString;
	using EagleValue = com.eagle.math.EagleValue;
	using COBOL_Picture_Value_Literal = com.eagle.programmar.COBOL.COBOL_Picture_Value.COBOL_Picture_Value_Literal;
	using COBOL_Picture_Value_Number = com.eagle.programmar.COBOL.COBOL_Picture_Value.COBOL_Picture_Value_Number;
	using COBOL_CopyOrDataDeclaration = com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
	using COBOL_BlankWhenZero = com.eagle.programmar.COBOL.Picture.COBOL_BlankWhenZero;
	using COBOL_ObjectReference = com.eagle.programmar.COBOL.Picture.COBOL_ObjectReference;
	using COBOL_PictureClause = com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
	using COBOL_RedefinesClause = com.eagle.programmar.COBOL.Picture.COBOL_RedefinesClause;
	using COBOL_RenamesClause = com.eagle.programmar.COBOL.Picture.COBOL_RenamesClause;
	using COBOL_Sign = com.eagle.programmar.COBOL.Picture.COBOL_Sign;
	using COBOL_ThruClause = com.eagle.programmar.COBOL.Picture.COBOL_ThruClause;
	using COBOL_Typedef = com.eagle.programmar.COBOL.Picture.COBOL_Typedef;
	using COBOL_Usage = com.eagle.programmar.COBOL.Picture.COBOL_Usage;
	using COBOL_ValueClause = com.eagle.programmar.COBOL.Picture.COBOL_ValueClause;
	using COBOL_Data_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Index_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Index_Definition;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_CommentToEndOfLine = com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_Level = com.eagle.programmar.COBOL.Terminals.COBOL_Level;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_DataDeclaration : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Level level;
		public COBOL_Level level;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_DataFieldName fieldName;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<COBOL_DataClause> clauses;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_DataComment comment;
		public  OPT;

		// These are special -- context-sensitive, must have
		// larger (deeper) Level numbers
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration> children;
		public  OPT;

		public class COBOL_DataClause : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Type XXprimitive;
			public COBOL_Type XXprimitive;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_BlankWhenZero XXblankWhenZero;
			public COBOL_BlankWhenZero XXblankWhenZero;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Justified XXjustified;
			public COBOL_Justified XXjustified;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ObjectReference XXobjectReference;
			public COBOL_ObjectReference XXobjectReference;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_OccursClause XXoccurs;
			public COBOL_OccursClause XXoccurs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_PictureClause XXpictureClause;
			public COBOL_PictureClause XXpictureClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_RedefinesClause XXredefinesClause;
			public COBOL_RedefinesClause XXredefinesClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_RenamesClause XXrenamesClause;
			public COBOL_RenamesClause XXrenamesClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Sign XXsign;
			public COBOL_Sign XXsign;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ThruClause XXthruClause;
			public COBOL_ThruClause XXthruClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_TypeLiteral XXtype;
			public COBOL_TypeLiteral XXtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Typedef XXtypedef;
			public COBOL_Typedef XXtypedef;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Usage XXusage;
			public COBOL_Usage XXusage;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ValueClause XXvalueClause;
			public COBOL_ValueClause XXvalueClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ValueIsGlobal XXisGlobal;
			public COBOL_ValueIsGlobal XXisGlobal;
		}

		public class COBOL_Justified : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword JUSTIFIED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("JUSTIFIED");
			public COBOL_Keyword JUSTIFIED = new COBOL_Keyword("JUSTIFIED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RIGHT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RIGHT");
			public COBOL_Keyword RIGHT = new COBOL_Keyword("RIGHT");
		}

		public class COBOL_DataFieldName : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Keyword XXFILLER = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FILLER");
			public COBOL_Keyword XXFILLER = new COBOL_Keyword("FILLER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Data_Definition XXid;
			public COBOL_Data_Definition XXid;
		}

		public class COBOL_TypeLiteral : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Keyword XXTYPE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TYPE");
			public COBOL_Keyword XXTYPE = new COBOL_Keyword("TYPE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXtype;
			public COBOL_Literal XXtype;
		}

		public class COBOL_OccursClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword OCCURS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OCCURS");
			public COBOL_Keyword OCCURS = new COBOL_Keyword("OCCURS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_Expression count;
			public COBOL_Expression count;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_OccursTo to;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Keyword TIMES = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TIMES");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_Depending depends;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_OccursKey key;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT COBOL_IndexedBy indexedBy;
			public  OPT;

			public class COBOL_Depending : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DEPENDING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DEPENDING");
				public COBOL_Keyword DEPENDING = new COBOL_Keyword("DEPENDING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ON = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ON");
				public COBOL_Keyword ON = new COBOL_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference index;
				public COBOL_Identifier_Reference index;
			}

			public class COBOL_OccursTo : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
				public COBOL_Keyword TO = new COBOL_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_Expression count;
				public COBOL_Expression count;
			}

			public class COBOL_OccursKey : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ASCENDING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ASCENDING");
				public COBOL_Keyword ASCENDING = new COBOL_Keyword("ASCENDING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword KEY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("KEY");
				public COBOL_Keyword KEY = new COBOL_Keyword("KEY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference index;
				public COBOL_Identifier_Reference index;
			}

			public class COBOL_IndexedBy : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INDEXED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INDEXED");
				public COBOL_Keyword INDEXED = new COBOL_Keyword("INDEXED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword BY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BY");
				public COBOL_Keyword BY = new COBOL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Index_Definition index;
				public COBOL_Index_Definition index;
			}
		}

		public class COBOL_ValueIsGlobal : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword GLOBAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("GLOBAL");
			public COBOL_Keyword GLOBAL = new COBOL_Keyword("GLOBAL");
		}

		public class COBOL_DataComment : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationStar star;
			public PunctuationStar star;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine comment;
			public COBOL_CommentToEndOfLine comment;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (fieldName.getWhich() is COBOL_Data_Definition)
			{
				COBOL_Data_Definition dataDef = (COBOL_Data_Definition) fieldName.getWhich();
				string varName = dataDef.getValue();
				string pic = null;
				string redefines = null;
				EagleInteger initInteger = null;
				EagleString initString = null;
				bool isComp = false;
				if (clauses != null)
				{
					foreach (COBOL_DataClause clause in clauses._elements)
					{
						AbstractToken which = clause.getWhich();
						if (which is COBOL_PictureClause)
						{
							COBOL_PictureClause picClause = (COBOL_PictureClause) which;
							pic = picClause.picture.getValue().ToUpper();
						}
						if (which is COBOL_RedefinesClause)
						{
							COBOL_RedefinesClause redefinesClause = (COBOL_RedefinesClause) which;
							redefines = redefinesClause.id.getValue();
						}
						if (which is COBOL_Usage)
						{
							COBOL_Usage usage = (COBOL_Usage) which;
							if (usage.type.getValue().ToUpper().StartsWith("COMP", StringComparison.Ordinal))
							{
								isComp = true;
							}
						}
						if (which is COBOL_ValueClause)
						{
							COBOL_ValueClause valueClause = (COBOL_ValueClause) which;
							COBOL_Picture_Value picValue = valueClause.values.first();
							if (picValue.getWhich() is COBOL_Picture_Value_Number)
							{
								COBOL_Picture_Value_Number num = (COBOL_Picture_Value_Number) picValue.getWhich();
								initInteger = new EagleInteger(int.Parse(num.number.getValue()));
							}
							else if (picValue.getWhich() is COBOL_Picture_Value_Literal)
							{
								COBOL_Picture_Value_Literal lit = (COBOL_Picture_Value_Literal) picValue.getWhich();
								initString = new EagleString(lit.literal.getValue());
							}
						}

					}
				}

				// Check for REDEFINES first
				EagleValue value = null;
				if (!string.ReferenceEquals(redefines, null))
				{
					value = interpreter.findSymbol(redefines);
					if (value != null)
					{
						// Change the name of the symbol
						interpreter.removeSymbol(redefines);
					}
				}

				else if (isComp)
				{
					value = initInteger;
				}
				else if (string.ReferenceEquals(pic, null))
				{
					value = collectArrayValues();
				}
				else if (pic.StartsWith("X", StringComparison.Ordinal) || pic.StartsWith("Z", StringComparison.Ordinal) || pic.StartsWith("9", StringComparison.Ordinal))
				{
					value = initString;
				}
				else
				{
					Console.Error.WriteLine("*** data " + level + " " + varName + " " + pic);
				}
				interpreter.setSymbol(dataDef, varName, value);
			}
		}

		private EagleArray collectArrayValues()
		{
			// Look at all the children
			EagleArray array = new EagleArray();
			foreach (COBOL_CopyOrDataDeclaration child in this.children._elements)
			{
				AbstractToken which = child.getWhich();
				if (which is COBOL_DataDeclaration)
				{
					COBOL_DataDeclaration dataDeclaration2 = (COBOL_DataDeclaration) which;
					foreach (COBOL_DataClause clause2 in dataDeclaration2.clauses._elements)
					{
						AbstractToken whichValue = clause2.getWhich();
						if (whichValue is COBOL_ValueClause)
						{
							COBOL_ValueClause valueClause = (COBOL_ValueClause) whichValue;
							COBOL_Picture_Value picValue = valueClause.values.first();
							if (picValue.getWhich() is COBOL_Picture_Value_Literal)
							{
								COBOL_Picture_Value_Literal lit = (COBOL_Picture_Value_Literal) picValue.getWhich();
								EagleString str = new EagleString(lit.literal.removeQuotes());
								// System.err.println("************** Adding " + str.toString());
								array.addValue(str);
							}
						}
					}
				}
			}
			return array;
		}

		public virtual void transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (fieldName.getWhich() is COBOL_Data_Definition)
			{
				COBOL_Data_Definition dataDef = (COBOL_Data_Definition) fieldName.getWhich();
				string varName = COBOL_Variable.repairName(dataDef.getValue());
				AbstractType newType = null;
				AbstractExpression expression = null;
				bool isComp = false;
				if (clauses != null)
				{
					foreach (COBOL_DataClause clause in clauses._elements)
					{
						AbstractToken which = clause.getWhich();
						if (which is COBOL_PictureClause)
						{
							COBOL_PictureClause picClause = (COBOL_PictureClause) which;
							string pic = picClause.picture.getValue().ToUpper();
							if (pic.StartsWith("9", StringComparison.Ordinal) || pic.StartsWith("X", StringComparison.Ordinal) || pic.StartsWith("Z", StringComparison.Ordinal))
							{
								newType = generator.transformType(EagleGenerator.TypeEnum.STRING, null, picClause);
								// Will get replaced by INTEGER if USAGE COMP is present
							}
						}
						if (which is COBOL_RedefinesClause)
						{
							COBOL_RedefinesClause redefClause = (COBOL_RedefinesClause) which;
							string redefWhat = redefClause.id.getValue();
							AbstractExpression redef = generator.newVariableExpression(redefWhat, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, this);
							newType = generator.transformType(EagleGenerator.TypeEnum.ARRAY, varName, this);
							AbstractStatement data = generator.newDataDeclaration(false, varName, null, newType, redef, this);
							generator.addStatement(data, this);
							return;
						}
						if (which is COBOL_ValueClause)
						{
							COBOL_ValueClause valueClause = (COBOL_ValueClause) which;
							COBOL_Picture_Value picValue = valueClause.values.first();
							if (picValue.getWhich() is COBOL_Picture_Value_Number)
							{
								COBOL_Picture_Value_Number num = (COBOL_Picture_Value_Number) picValue.getWhich();
								expression = generator.newNumberExpression(num.number.getValue(), num);
							}
							else if (picValue.getWhich() is COBOL_Picture_Value_Literal)
							{
								COBOL_Picture_Value_Literal lit = (COBOL_Picture_Value_Literal) picValue.getWhich();
								expression = generator.newLiteralExpression(lit.literal.getValue(), lit);
							}
						}
						if (which is COBOL_Usage)
						{
							COBOL_Usage usage = (COBOL_Usage) which;
							if (usage.type.getValue().ToUpper().StartsWith("COMP", StringComparison.Ordinal))
							{
								isComp = true;
							}
						}
					}
				}

				if (isComp) // COMP, COMP-1, etc.
				{
					newType = generator.transformType(EagleGenerator.TypeEnum.INTEGER, null, null);
				}
				if (newType != null)
				{
					AbstractStatement data = generator.newDataDeclaration(false, varName, null, newType, expression, this);
					generator.addStatement(data, this);
					return;
				}

				// Maybe it is an array definition
				EagleArray array = this.collectArrayValues();
				if (array != null)
				{
					List<EagleValue> values = array.getArrayValue();
					if (values != null && values.Count > 0)
					{
						List<AbstractExpression> newValues = new List<AbstractExpression>();
						foreach (EagleValue val in values)
						{
							string str = val.forceStringValue();
							AbstractExpression newExpr = generator.newLiteralExpression(str, this);
							newValues.Add(newExpr);
						}

						AbstractExpression arrayExpr = generator.newArrayExpression(newValues, this);
						newType = generator.transformType(EagleGenerator.TypeEnum.ARRAY, varName, this);
						AbstractStatement data = generator.newDataDeclaration(false, varName, null, newType, arrayExpr, this);
						generator.addStatement(data, this);
						return;
					}
				}
			}

			throw new Exception("Unable to process: " + this);
		}
	}

}
