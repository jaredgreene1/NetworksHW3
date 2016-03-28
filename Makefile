JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Server.java \
		  Client.java \
		  AeSimpleSHA1.java 
default: classes

classes: $(CLASSES:.java=.class)

clear:
		$(RM) *.class *~
