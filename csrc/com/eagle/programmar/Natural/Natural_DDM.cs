// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.IO;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural
{

	using Natural_Identifier_Reference = com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
	using Natural_Float = com.eagle.programmar.Natural.Terminals.Natural_Float;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using Natural_Level = com.eagle.programmar.Natural.Terminals.Natural_Level;
	using Natural_Number = com.eagle.programmar.Natural.Terminals.Natural_Number;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_DDM : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference name;
		public Natural_Identifier_Reference name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<Natural_DDM_Line> lines;
		public TokenList<Natural_DDM_Line> lines;

		public class Natural_DDM_Line : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_KeywordChoice Ty = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("GR", "MU", "PE", "SB", "SP");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Level Level;
			public Natural_Level Level;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference FieldName;
			public Natural_Identifier_Reference FieldName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_KeywordChoice F = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("A", "B", "P", "U");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Natural_Float Length;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Natural_Number Occurs;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Natural_Keyword D = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("D");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Natural_Keyword U = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("U");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference DB;
			public Natural_Identifier_Reference DB;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT Natural_KeywordChoice S = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("F", "N");
			public  OPT;

			// These are special -- context-sensitive, must have smaller Level numbers
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @OPT TokenList<Natural_DDM_Line> children;
			public TokenList<Natural_DDM_Line> children;
		}

		/* 123456789.123456789.123456789.123456789.123456789.123456789.1234 */
		private readonly string HDR = "Ty L Field name                       F  Length   Occ   D U DB S";

		public virtual void customReader(string fileName)
		{
			try
			{
				StreamReader br = new StreamReader(fileName);
				string rec = br.ReadLine();
				if (!rec.StartsWith(HDR, StringComparison.Ordinal))
				{
					br.Close();
					throw new Exception("Invalid DDM file: " + fileName);
				}
				while (!string.ReferenceEquals((rec = br.ReadLine()), null))
				{
					if (rec.Trim().Length == 0)
					{
						continue;
					}
					string level = rec.Substring(3, 1);
					if (level.Equals("-"))
					{
						continue; // Skip header and footer markers
					}

					Natural_DDM_Line line = new Natural_DDM_Line(); // @SKIP -- this instance is ok

					string ty = rec.Substring(0, 2).Trim().ToUpper();
					if (ty.Length > 0)
					{
						line.Ty.setValue(ty);
					}

					line.Level.setValue(level);

					line.FieldName.setValue(rec.Substring(6, 32).Trim());

					string f = rec.Substring(39, 1).Trim().ToUpper();
					if (f.Length > 0)
					{
						line.F.setValue(f);
					}

					string len = rec.Substring(42, 8).Trim();
					if (len.Length > 0)
					{
						line.Length.setValue(len);
					}

					string occ = rec.Substring(51, 5).Trim();
					if (occ.Length > 0)
					{
						line.Occurs.setValue(occ);
					}

					string d = rec.Substring(57, 1).Trim().ToUpper();
					if (d.Length > 0)
					{
						line.D.setValue(d);
					}

					string u = rec.Substring(59, 1).Trim().ToUpper();
					if (u.Length > 0)
					{
						line.U.setValue(u);
					}

					string db = rec.Substring(61, 2).Trim().ToUpper();
					line.DB.setValue(db);

					string s = rec.Substring(64, 1).Trim().ToUpper();
					if (s.Length > 0)
					{
						line.D.setValue(s);
					}

					lines.addToken(line);
				}
				br.Close();
			}
			catch (IOException ex)
			{
				throw new Exception("Error reading " + fileName, ex);
			}
		}
	}

}
