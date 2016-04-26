EOPL
====

Working through "Essentials of Programming Languages" by Daniel P. Friedman and Mitchell Wand.

Chapter 1 - Summary: Inductive Sets of Data
===========================================
Inductive Sets :- 3 forms of induction: top-down, bottom-up, rules of inference

Context-Free Grammars :-

Recursive Functions :- 

Chapter 2 - Summary: Data Abstraction
=====================================

Specifying Data via Interfaces :-

Representation Strategies for Data Types :-

The Environment Interface :-

Procedural Representation :- The key to why this works is closures. In the Environment interface example,
empty-env and extend-env both return functions that behave as apply-env would with the given argument.
Since each closure saves the state of its lexical scope from when it was invoked, we can model the environment
as it is built up.

Interfaces for Recursive Data Types :- One constructor for each kind of data in the recursive data type. One Predicate for each kind of data in the data type. One 

Concrete Syntax vs Abstract Syntax and its Representation :-

Chapter 3 - Summary: Expressions
================================
