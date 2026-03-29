// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 8, 2024

// So,
//
// I don't think this is the right way to do this.
// Point-to-point seems wrong.
// Need to use an abstraction in the middle.
//
// The original idea was to use Reflection and assume that Java and C#
// matched up 90% of the time, or more.
// But I'm not so sure that really works.

namespace com.eagle.transform
{

	using Java_Class = com.eagle.programmar.Java.Java_Class;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Method = com.eagle.programmar.Java.Java_Method;
	using Java_Program = com.eagle.programmar.Java.Java_Program;
	using Java_Statement = com.eagle.programmar.Java.Java_Statement;
	using Java_Type = com.eagle.programmar.Java.Java_Type;
	using Java_Variable = com.eagle.programmar.Java.Java_Variable;
	using EagleEnvironment = com.eagle.utils.EagleEnvironment;
	using EaglePath = com.eagle.utils.EaglePath;

	public class Java_CSharp
	{
		private readonly string JAVA_ROOT = EaglePath.combinePaths(EagleEnvironment.GITHOME + "/");
		private readonly string CS_ROOT = EaglePath.combinePaths(EagleEnvironment.GITHOME + "/");
		private readonly string XML_ROOT = EaglePath.combinePaths(EagleEnvironment.ARTIFACTDIR, "Eagle", EagleEnvironment.PARSED + "/");
		private readonly string HTML_ROOT = EaglePath.combinePaths(EagleEnvironment.ARTIFACTDIR, "Eagle", EagleEnvironment.HTML + "/");

		private int _count = 0;
		private int _failed = 0;

		private void processDir(File dir, string javaDir, string csDir, string xmlDir, string htmlDir)
		{
			foreach (File fil in dir.listFiles())
			{
				string name = fil.getName();
				if (name.StartsWith(".", StringComparison.Ordinal))
				{
					continue;
				}

				if (fil.isDirectory())
				{
					// Process sub-directories
					string slashName = name + "/";
					processDir(fil, javaDir + slashName, (csDir + slashName).replace("/src/", "/csrc/"), xmlDir + slashName, htmlDir + slashName);
				}
				else if (name.EndsWith(".java", StringComparison.Ordinal) && name.StartsWith("DeTabber", StringComparison.Ordinal))
				{
					// Process java files
					string @base = name.Replace(".java", "");
					_count++;

					string javaFile = javaDir + @base + ".java";
					string xmlFile = xmlDir + @base + "_java.xml";
					string htmlFile = htmlDir + @base + ".html";
					string csFile = csDir + @base + ".cs";

					Console.WriteLine(_count + ". Converting " + javaFile + "\n    to " + csFile);

					try
					{
						File csDirFile = new File(csDir);
						if (!csDirFile.exists())
						{
							Console.WriteLine("Creating directory " + csDirFile.getCanonicalPath());
							csDirFile.mkdirs();
						}

						File htmlDirFile = new File(htmlDir);
						if (!htmlDirFile.exists())
						{
							Console.WriteLine("Creating directory " + htmlDirFile.getCanonicalPath());
							htmlDirFile.mkdirs();
						}

						EagleTransformMain<Java_Program, Java_Class, Java_Statement, Java_Method, Java_Expression, Java_Variable, Java_Type> etm = new EagleTransformMain<Java_Program, Java_Class, Java_Statement, Java_Method, Java_Expression, Java_Variable, Java_Type>();
						etm.doTransform(xmlFile, javaFile, csFile, htmlFile);
					}
					catch (Exception ex)
					{
						_failed++;
						Console.Error.WriteLine("Error converting " + javaFile + "\n" + ex.Message);
					}
				}
			}
		}

		public static void Main(string[] args)
		{
			Java_CSharp jcs = new Java_CSharp();
			jcs.processDir(new File(jcs.JAVA_ROOT), jcs.JAVA_ROOT, jcs.CS_ROOT, jcs.XML_ROOT, jcs.HTML_ROOT);
			Console.WriteLine("Transformed " + jcs._count + " files from Java to C#");
			if (jcs._failed > 0)
			{
				Console.WriteLine("  Failures: " + jcs._failed);
			}
		}
	}

}
