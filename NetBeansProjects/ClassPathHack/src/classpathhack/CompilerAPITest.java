/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classpathhack;

/**
 *
 * @author guestu
 */
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Logger;
 
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
 
/**
 * A test class to test dynamic compilation API.
 *
 */
public class CompilerAPITest {
    static final Logger logger = Logger.getLogger(CompilerAPITest.class.getName()) ;
 
    /**Java source code to be compiled dynamically*/
    static String sourceCode = "class GeneratedClass{}" ;
 
    /**" 
     * Does the required object initialisation and compilation.
     */
    public void doCompilation (){
       File[] files = {new File("/home/guestu/GeneratedClass.java")};
 
        /*Instantiating the java compiler*/
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
 
        StandardJavaFileManager stdFileManager = compiler.getStandardFileManager(null, Locale.getDefault(), null);
 
        /* Prepare a list of compilation units (java source code file objects) to input to compilation task*/
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(javaFileObjects);
 
        /*Create a diagnostic controller, which holds the compilation problems*/
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
 
        CompilationTask compilerTask = compiler.getTask(null, stdFileManager, diagnostics, null, null, compilationUnits) ;
 
        //Perform the compilation by calling the call method on compilerTask object.
        boolean status = compilerTask.call();
 
        if (!status){
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()){
                System.out.format("Error on line %d in %s", diagnostic.getLineNumber(), diagnostic);
            }
        }
        try {
            stdFileManager.close() ;//Close the file manager
        } catch (IOException e) {
        }
    }
 
    public static void main(String args[]){
        new CompilerAPITest().doCompilation() ;
       
    }
 
}
