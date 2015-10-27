package com.strath;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

// Simple illustrate of how much UML stuff can be extracted using JP

public class SimpleUMLJPv2 {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("test.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        new ClassDiagramVisitor().visit(cu, null);

    }

    /**
     * What about the fields... 
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
 
//        @Override
//        public void visit(FieldDeclaration n, Object arg) {
//        	System.out.println("Fields...");
//            for (VariableDeclarator var : n.getVariables()) {
//                var.accept(this, arg);
//            }
//        }
        
        @Override
        public void visit(VariableDeclarator n, Object arg) {
            n.getId().accept(this, arg);
        }
        
//        @Override
//        public void visit(VariableDeclaratorId n, Object arg) {
//            System.out.println("Field: " + n.getName());
//        }
        
        @Override
        public void visit(FieldDeclaration n, Object arg) {
          System.out.println("Fields...");
          for (VariableDeclarator var : n.getVariables()) {
          	System.out.println(var.getId().getName());
          }
      	  System.out.println("Field type: " + n.getType());
      	  System.out.println("Field modifier: " + n.getModifiers() + " " + decodeModifiers(n.getModifiers()));
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