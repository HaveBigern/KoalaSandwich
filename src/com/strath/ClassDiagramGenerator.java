package com.strath;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassDiagramGenerator {
	
	public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("test.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        new ClassDiagramVisitor().visit(cu, null);
        System.out.println("-------------");
    }

    /**
     * Simple visitor implementation for extracting class information 
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    	
    	public void visit(ClassOrInterfaceDeclaration n, Object arg){
    		System.out.println("-------------");
    		System.out.println("| " + n.getName() + " |");
    		System.out.println("-------------");
    		super.visit(n, arg);
    	}
    	
    	@Override
        public void visit(FieldDeclaration n, Object arg) {
          for (VariableDeclarator var : n.getVariables()) {
        	  System.out.println("| -" + var.getId().getName() + " : " + n.getType() + " |");  
          }
          System.out.println("-------------");
        }
    	
    	@Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("| +" + n.getName() + "() |");
        }
        
        // Adapted from DumpVisitor
        private String decodeModifiers(final int modifiers) {
    		if (ModifierSet.isPrivate(modifiers)) {
    			return("private ");
    		}
    		if (ModifierSet.isProtected(modifiers)) {
    			return("protected ");
    		}
    		if (ModifierSet.isPublic(modifiers)) {
    			return("public ");
    		}
    		if (ModifierSet.isAbstract(modifiers)) {
    			return("abstract ");
    		}
    		if (ModifierSet.isStatic(modifiers)) {
    			return("static ");
    		}
    		if (ModifierSet.isFinal(modifiers)) {
    			return("final ");
    		}
    		if (ModifierSet.isNative(modifiers)) {
    			return("native ");
    		}
    		if (ModifierSet.isStrictfp(modifiers)) {
    			return("strictfp ");
    		}
    		if (ModifierSet.isSynchronized(modifiers)) {
    			return("synchronized ");
    		}
    		if (ModifierSet.isTransient(modifiers)) {
    			return("transient ");
    		}
    		if (ModifierSet.isVolatile(modifiers)) {
    			return("volatile ");
    		}
    		return "";
    	}
    }
    
    
}
