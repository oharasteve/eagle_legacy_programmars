// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.Java.Terminals
{
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Java_Literal : TerminalLiteralToken
	{
		public Java_Literal() : base("\"", true, '\\', false, false)
		{
		}

		public static Java_Literal generateLiteral(string value, AbstractToken source)
		{
			Java_Literal lit = new Java_Literal();
			string val = '"' + value.replaceAll("\\\\n", "\\\\n").replaceAll("\"", "\\\\\"") + '"'; lit.setValue(val); lit.setTransformationSource(source); return lit;
			}
			public static Java_Expression generateLiteralExpression(string value, AbstractToken source) {return Java_Generator.wrapExpression(generateLiteral(value, source));}
			}

		}
